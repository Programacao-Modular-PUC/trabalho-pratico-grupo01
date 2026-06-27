document.addEventListener('DOMContentLoaded', async () => {
    const cliente = session.get();
    if (!cliente) {
        document.querySelector('.dashboard-title').textContent = 'Faça login para ver suas reservas';
        document.querySelector('.dashboard-description').textContent = 'Clique em "Login" no menu superior para acessar seu histórico.';
        document.querySelector('.reservations-list').innerHTML = '';
        document.querySelector('.stats-cards').innerHTML = '';
        document.querySelector('.pagination-section').innerHTML = '';
        return;
    }

    try {
        const reservas = await api.listarReservasPorCliente(cliente.id);
        preencherReservas(reservas);
        preencherStats(reservas);
    } catch (err) {
        console.error('Erro ao carregar reservas:', err);
        document.querySelector('.reservations-list').innerHTML = '<p style="color:#999;padding:20px;">Erro ao carregar reservas.</p>';
    }
});

function formatarData(dataStr) {
    if (!dataStr) return '-';
    const d = new Date(dataStr + 'T12:00:00');
    return d.toLocaleDateString('pt-BR', { day: '2-digit', month: 'short', year: 'numeric' }).replace('.', '');
}

function preencherStats(reservas) {
    const statsContainer = document.querySelector('.stats-cards');
    if (!statsContainer) return;

    const totalReceita = reservas
        .filter(r => r.status !== 'CANCELADA')
        .reduce((sum, r) => sum + (r.valorFinal || 0), 0);

    const totalReservas = reservas.length;
    const ativas = reservas.filter(r => r.status === 'ATIVA').length;
    const ocupacao = totalReservas > 0 ? Math.round((ativas / totalReservas) * 100) : 0;

    statsContainer.innerHTML = `
        <div class="stat-card">
            <span class="stat-label">RECEITA TOTAL</span>
            <span class="stat-value">R$ ${totalReceita.toLocaleString('pt-BR', {minimumFractionDigits: 2})}</span>
        </div>
        <div class="stat-card">
            <span class="stat-label">RESERVAS ATIVAS</span>
            <span class="stat-value stat-percentage">${ativas}</span>
        </div>
    `;
}

function statusBadgeClass(status) {
    switch (status) {
        case 'ATIVA': return 'confirmed';
        case 'CONCLUIDA': return 'confirmed';
        case 'CANCELADA': return 'cancelled';
        default: return '';
    }
}

function statusLabel(status) {
    switch (status) {
        case 'ATIVA': return 'Ativa';
        case 'CONCLUIDA': return 'Concluída';
        case 'CANCELADA': return 'Cancelada';
        default: return status;
    }
}

function preencherReservas(reservas) {
    const list = document.querySelector('.reservations-list');
    const pagination = document.querySelector('.pagination-section');
    if (!list) return;

    if (reservas.length === 0) {
        list.innerHTML = '<p style="color:#999;padding:40px;text-align:center;">Nenhuma reserva encontrada.</p>';
        if (pagination) pagination.innerHTML = '';
        return;
    }

    list.innerHTML = reservas.map(r => {
        const imagem = r.quarto ? `../img/Quarto${(r.quarto.id % 3) + 1}.png` : '../img/quartoreserva1.png';
        const nomeQuarto = r.quarto ? (r.quarto.tipo || 'Quarto') : 'Quarto';
        const tipoQuarto = r.quarto ? (r.quarto.tipo || '') : '';
        const hospede = r.cliente ? r.cliente.nome : 'Hóspede';
        const diarias = r.qtdDiarias || 0;

        return `
            <div class="reservation-card">
                <img src="${imagem}" alt="${nomeQuarto}" class="reservation-image" onerror="this.src='../img/quartoreserva1.png'">
                <div class="reservation-info">
                    <div class="info-column">
                        <span class="info-label">HÓSPEDE</span>
                        <span class="info-value guest-name">${hospede}</span>
                    </div>
                    <div class="info-column">
                        <span class="info-label">ACOMODAÇÃO</span>
                        <span class="info-value">${nomeQuarto}</span>
                        <span class="info-subvalue">${tipoQuarto}</span>
                    </div>
                    <div class="info-column">
                        <span class="info-label">PERÍODO</span>
                        <span class="info-value">${formatarData(r.dataEntrada)} - ${formatarData(r.dataSaida)}</span>
                        <span class="info-subvalue">${diarias} Noite${diarias !== 1 ? 's' : ''}</span>
                    </div>
                    <div class="info-column">
                        <span class="info-label">VALOR FINAL.</span>
                        <span class="info-value price">R$ ${(r.valorFinal || 0).toLocaleString('pt-BR', {minimumFractionDigits: 2})}</span>
                    </div>
                </div>
                <div class="reservation-actions">
                    <span class="status-badge ${statusBadgeClass(r.status)}">${statusLabel(r.status)}</span>
                    ${r.status === 'ATIVA' ? `
                        <button class="more-btn" onclick="cancelarReserva(${r.id})" title="Cancelar reserva">
                            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#dc2626" stroke-width="2">
                                <line x1="18" y1="6" x2="6" y2="18"></line>
                                <line x1="6" y1="6" x2="18" y2="18"></line>
                            </svg>
                        </button>
                    ` : ''}
                </div>
            </div>
        `;
    }).join('');

    if (pagination) {
        const total = reservas.length;
        pagination.innerHTML = `
            <span class="pagination-info">Mostrando ${total} reserva${total !== 1 ? 's' : ''}</span>
        `;
    }
}

async function cancelarReserva(id) {
    if (!confirm('Tem certeza que deseja cancelar esta reserva?')) return;
    try {
        await api.del(`/reservas/${id}`);
        window.location.reload();
    } catch (err) {
        alert('Erro ao cancelar reserva: ' + (err.message || 'Erro desconhecido'));
    }
}
