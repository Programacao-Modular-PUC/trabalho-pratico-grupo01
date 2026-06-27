const dadosResidencia = {
    1: { endereco: 'Rua dos Coqueiros', numero: '450', bairro: 'Barra Grande', cep: '45520-000', telefone: '(73) 99876-5432', email: 'reserva@residenciadocarlos.com.br' },
    2: { endereco: 'Av. Beira Mar', numero: '120', bairro: 'Centro', cep: '45520-000', telefone: '(73) 99876-1111', email: 'contato@maraberto.com.br' },
    3: { endereco: 'Rua das Flores', numero: '88', bairro: 'Barra Grande', cep: '45520-000', telefone: '(73) 99876-2222', email: 'ana@pousada.com.br' }
};

document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const quartoId = parseInt(params.get('id')) || 1;

    try {
        const quarto = await api.buscarQuarto(quartoId);
        preencherDados(quarto, quartoId);
    } catch (err) {
        console.error('Erro ao carregar quarto:', err);
    }
});

function preencherDados(quarto, quartoId) {
    const residencia = dadosResidencia[quartoId] || {};

    const tituloEl = document.querySelector('.quarto-titulo');
    if (tituloEl) tituloEl.textContent = quarto.tipo || 'Quarto';

    const descEl = document.querySelector('.quarto-descricao');
    if (descEl) {
        descEl.textContent = `Aproveite o conforto e a tranquilidade deste quarto ${quarto.tipo || 'exclusivo'}, localizado no coração da Península de Maraú. Ideal para quem busca relaxar próximo às piscinas naturais e desfrutar da natureza preservada da Mata Atlântica.`;
    }

    const localEl = document.querySelector('.localizacao span');
    if (localEl) localEl.textContent = 'Maraú, BA';

    const dadosList = document.querySelector('.dados-residencia ul');
    if (dadosList && residencia.endereco) {
        dadosList.innerHTML = `
            <li>Endereço: ${residencia.endereco}, nº ${residencia.numero}</li>
            <li>Bairro: ${residencia.bairro}</li>
            <li>CEP: ${residencia.cep}</li>
            <li>Telefone: ${residencia.telefone}</li>
            <li>E-mail: ${residencia.email}</li>
        `;
    }

    const comodidadesContainer = document.querySelector('.comodidades-tags');
    if (comodidadesContainer) {
        let html = '';
        if (quarto.possuiAr) {
            html += `
                <span class="comodidade-tag">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none">
                        <path d="M12 2L15.09 8.26L22 9.27L17 14.14L18.18 21.02L12 17.77L5.82 21.02L7 14.14L2 9.27L8.91 8.26L12 2Z" fill="#60a5fa" stroke="#60a5fa" stroke-width="1"/>
                    </svg>
                    Ar Condicionado
                </span>`;
        }
        if (quarto.possuiHidro) {
            html += `
                <span class="comodidade-tag">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none">
                        <rect x="2" y="6" width="20" height="12" rx="2" stroke="#a78bfa" stroke-width="2"/>
                        <path d="M6 10V14M10 10V14M14 10V14M18 10V14" stroke="#a78bfa" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                    Hidromassagem
                </span>`;
        }
        if (!html) {
            html = '<span class="comodidade-tag">Comodidades básicas</span>';
        }
        comodidadesContainer.innerHTML = html;
    }

    const checkinInput = document.querySelector('.reserva-form .form-row .form-group:nth-child(1) input');
    const checkoutInput = document.querySelector('.reserva-form .form-row .form-group:nth-child(2) input');
    const totalValorEl = document.getElementById('totalValor');
    const reservaInfo = document.querySelector('.reserva-info');

    function formatarData(d) {
        return d ? d.toLocaleDateString('pt-BR') : '-';
    }

    function calcularTotal() {
        const checkin = checkinInput?.value ? new Date(checkinInput.value + 'T12:00:00') : null;
        const checkout = checkoutInput?.value ? new Date(checkoutInput.value + 'T12:00:00') : null;

        if (checkin && checkout && checkout > checkin) {
            const diff = Math.ceil((checkout - checkin) / (1000 * 60 * 60 * 24));
            const total = quarto.valorBase * diff;

            if (totalValorEl) totalValorEl.textContent = `R$ ${total.toLocaleString('pt-BR', {minimumFractionDigits: 2})}`;
            if (reservaInfo) {
                reservaInfo.innerHTML = `
                    <p>Entrada: ${formatarData(checkin)} às 12h</p>
                    <p>Saída: ${formatarData(checkout)} às 12h</p>
                    <p>Número de diárias: ${diff}</p>
                `;
            }
        } else {
            if (totalValorEl) totalValorEl.textContent = `R$ ${quarto.valorBase.toLocaleString('pt-BR', {minimumFractionDigits: 2})}`;
        }
    }

    if (checkinInput) checkinInput.addEventListener('change', calcularTotal);
    if (checkoutInput) checkoutInput.addEventListener('change', calcularTotal);

    const form = document.querySelector('.reserva-form');
    if (form) {
        form.addEventListener('submit', async (e) => {
            e.preventDefault();

            const cliente = session.get();
            if (!cliente) {
                alert('Faça login antes de finalizar a reserva.');
                abrirLogin();
                return;
            }

            const checkin = document.querySelector('.reserva-form .form-row .form-group:nth-child(1) input')?.value;
            const checkout = document.querySelector('.reserva-form .form-row .form-group:nth-child(2) input')?.value;

            if (!checkin || !checkout) {
                alert('Selecione as datas de check-in e check-out.');
                return;
            }

            try {
                const dto = {
                    clienteId: cliente.id,
                    quartoId: quarto.id,
                    dataEntrada: checkin,
                    dataSaida: checkout
                };

                await api.criarReserva(dto);
                alert('Reserva realizada com sucesso!');
                window.location.href = 'reservas.html';
            } catch (err) {
                alert('Erro ao criar reserva: ' + (err.message || 'Erro desconhecido'));
            }
        });
    }

    calcularTotal();
}
