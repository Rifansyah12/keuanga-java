const BASE = '';

const BULAN_NAMA = ['','Januari','Februari','Maret','April','Mei','Juni',
    'Juli','Agustus','September','Oktober','November','Desember'];

function checkLogin() {
    if (localStorage.getItem('loggedIn') !== 'true') {
        window.location.href = '/login.html';
        return;
    }
}

function logout() {
    localStorage.clear();
    window.location.href = '/login.html';
}

// Inject sidebar
function renderSidebar(activePage) {
    const links = [
        { href: '/index.html',     icon: 'bi-grid-1x2-fill',   label: 'Dashboard',   id: 'dashboard' },
        { href: '/transaksi.html', icon: 'bi-arrow-left-right', label: 'Transaksi',   id: 'transaksi' },
        { href: '/kategori.html',  icon: 'bi-tags-fill',        label: 'Kategori',    id: 'kategori' },
        { href: '/budget.html',    icon: 'bi-piggy-bank-fill',  label: 'Budget',      id: 'budget' },
    ];

    const username = localStorage.getItem('username') || 'Admin';
    const initial  = username.charAt(0).toUpperCase();

    document.getElementById('sidebarMount').innerHTML = `
    <div class="sidebar">
        <div class="sidebar-brand">
            <div class="brand-icon"><i class="bi bi-wallet2"></i></div>
            <h6>Catatan Keuangan</h6>
            <p>Personal Finance</p>
        </div>
        <nav class="sidebar-nav">
            <div class="nav-label">Menu</div>
            ${links.map(l => `
                <a href="${l.href}" class="${l.id === activePage ? 'active' : ''}">
                    <i class="bi ${l.icon}"></i> ${l.label}
                </a>
            `).join('')}
        </nav>
        <div class="sidebar-footer">
            <div class="user-info">
                <div class="user-avatar">${initial}</div>
                <div class="flex-grow-1">
                    <div class="user-name">${username}</div>
                    <div class="user-role">Administrator</div>
                </div>
                <button class="btn-logout" onclick="logout()" title="Keluar">
                    <i class="bi bi-box-arrow-right"></i>
                </button>
            </div>
        </div>
    </div>`;
}

// Toast notifications
let toastContainer = null;
function showToast(msg, type = 'success') {
    if (!toastContainer) {
        toastContainer = document.createElement('div');
        toastContainer.className = 'toast-container';
        document.body.appendChild(toastContainer);
    }
    const el = document.createElement('div');
    el.className = `toast-item ${type}`;
    el.textContent = msg;
    toastContainer.appendChild(el);
    setTimeout(() => el.remove(), 3000);
}

// API helpers
async function apiGet(url) {
    const res = await fetch(BASE + url);
    if (!res.ok) throw new Error('Gagal mengambil data');
    return res.json();
}

async function apiPost(url, data) {
    const res = await fetch(BASE + url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });
    if (!res.ok) throw new Error('Gagal menyimpan data');
    return res.json();
}

async function apiPut(url, data) {
    const res = await fetch(BASE + url, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });
    if (!res.ok) throw new Error('Gagal update data');
    return res.json();
}

async function apiDelete(url) {
    const res = await fetch(BASE + url, { method: 'DELETE' });
    if (!res.ok) throw new Error('Gagal menghapus data');
}

function formatRupiah(n) {
    return new Intl.NumberFormat('id-ID', {
        style: 'currency', currency: 'IDR', minimumFractionDigits: 0
    }).format(n || 0);
}

function fillBulanSelect(el, selectedBulan) {
    el.innerHTML = '';
    BULAN_NAMA.slice(1).forEach((n, i) => {
        const o = new Option(n, i + 1);
        if ((i + 1) === selectedBulan) o.selected = true;
        el.add(o);
    });
}

function fillTahunSelect(el, selectedTahun) {
    const now = new Date().getFullYear();
    el.innerHTML = '';
    for (let y = now; y >= now - 4; y--) {
        const o = new Option(y, y);
        if (y === selectedTahun) o.selected = true;
        el.add(o);
    }
}
