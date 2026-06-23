#!/bin/sh
# Maven Wrapper Script
set -e

if [ -z "$JAVA_HOME" ]; then
  JAVA_CMD="java"
else
  JAVA_CMD="$JAVA_HOME/bin/java"
fi

MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-$(dirname "$0")}"
MAVEN_WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"

DOWNLOAD_URL=$(grep distributionUrl "$MAVEN_WRAPPER_PROPERTIES" | cut -d= -f2)

MAVEN_USER_HOME="${MAVEN_USER_HOME:-$HOME/.m2}"
MAVEN_OPTS="${MAVEN_OPTS:-""}"

# Download Maven if not cached
MAVEN_ZIP_NAME=$(basename "$DOWNLOAD_URL")
MAVEN_HOME="$MAVEN_USER_HOME/wrapper/dists/${MAVEN_ZIP_NAME%.zip}"

if [ ! -d "$MAVEN_HOME" ]; then
  mkdir -p "$MAVEN_HOME"
  echo "Downloading Maven: $DOWNLOAD_URL"
  if command -v curl > /dev/null; then
    curl -fsSL -o "$MAVEN_HOME/$MAVEN_ZIP_NAME" "$DOWNLOAD_URL"
  elif command -v wget > /dev/null; then
    wget -q -O "$MAVEN_HOME/$MAVEN_ZIP_NAME" "$DOWNLOAD_URL"
  else
    echo "ERROR: curl or wget required"
    exit 1
  fi
  unzip -q "$MAVEN_HOME/$MAVEN_ZIP_NAME" -d "$MAVEN_HOME"
  rm "$MAVEN_HOME/$MAVEN_ZIP_NAME"
fi

MAVEN_BIN=$(find "$MAVEN_HOME" -name "mvn" -type f | head -1)
exec "$MAVEN_BIN" "$@"
