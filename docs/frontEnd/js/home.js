document.addEventListener('DOMContentLoaded', async () => {
    const grid = document.getElementById('gridQuartos');
    if (!grid) return;

    const nomesResidencias = {
        1: 'Residência do Carlos',
        2: 'Residência Mar Aberto',
        3: 'Residência da Dona Ana'
    };

    try {
        const quartos = await api.listarQuartos();
        grid.innerHTML = quartos.map(q => {
            const nomeResidencia = nomesResidencias[q.id] || 'Residência em Maraú';
            const tags = [];
            if (q.possuiAr) tags.push('<span class="tag"><span class="tag-icon">&#10052;</span> Ar Condicionado</span>');
            if (q.possuiHidro) tags.push('<span class="tag"><span class="tag-icon-hidro"></span> Hidro</span>');

            return `
                <div class="card-quarto" onclick="location.href='acomodacoes.html?id=${q.id}'" style="cursor:pointer">
                    <div class="card-image-container">
                        <img src="../img/Quarto${q.id % 3 + 1}.png" alt="${q.tipo}" onerror="this.src='../img/Quarto1.png'">
                        <button class="btn-favorito">
                            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M12 21.35L10.55 20.03C5.4 15.36 2 12.28 2 8.5C2 5.42 4.42 3 7.5 3C9.24 3 10.91 3.81 12 5.09C13.09 3.81 14.76 3 16.5 3C19.58 3 22 5.42 22 8.5C22 12.28 18.6 15.36 13.45 20.04L12 21.35Z"/>
                            </svg>
                        </button>
                    </div>
                    <div class="card-info">
                        <div class="card-info-header">
                            <h3>${q.tipo}</h3>
                            <div class="preco-container">
                                <span class="preco">R$ ${q.valorBase.toLocaleString('pt-BR', {minimumFractionDigits: 2})}</span>
                                <span class="diaria">POR DIÁRIA</span>
                            </div>
                        </div>
                        <p class="subtitulo">${nomeResidencia} - Maraú, BA</p>
                        <div class="tags">${tags.join('')}</div>
                    </div>
                </div>
            `;
        }).join('');
    } catch (err) {
        console.error('Erro ao carregar quartos:', err);
    }

    const buscarBtn = document.querySelector('.btn-buscar');
    const checkinInput = document.querySelector('.search-field:nth-child(1) input');
    const checkoutInput = document.querySelector('.search-field:nth-child(3) input');
    const tipoSelect = document.querySelector('.search-field select');

    if (buscarBtn && checkinInput && checkoutInput) {
        buscarBtn.addEventListener('click', async () => {
            const entrada = checkinInput.value;
            const saida = checkoutInput.value;
            const tipo = tipoSelect ? tipoSelect.value : '';

            try {
                let quartos;
                if (entrada && saida) {
                    quartos = await api.buscarQuartosDisponiveis(entrada, saida);
                } else {
                    quartos = await api.listarQuartos();
                }

                if (tipo && tipo !== 'Solteiro/Casal') {
                    const mapTipo = { 'Solteiro': 'Solteiro', 'Casal': 'Casal' };
                    quartos = quartos.filter(q => q.tipo === (mapTipo[tipo] || tipo));
                }

                grid.innerHTML = quartos.map(q => {
                    const nomeResidencia = nomesResidencias[q.id] || 'Residência em Maraú';
                    const tags = [];
                    if (q.possuiAr) tags.push('<span class="tag"><span class="tag-icon">&#10052;</span> Ar Condicionado</span>');
                    if (q.possuiHidro) tags.push('<span class="tag"><span class="tag-icon-hidro"></span> Hidro</span>');

                    return `
                        <div class="card-quarto" onclick="location.href='acomodacoes.html?id=${q.id}'" style="cursor:pointer">
                            <div class="card-image-container">
                                <img src="../img/Quarto${q.id % 3 + 1}.png" alt="${q.tipo}" onerror="this.src='../img/Quarto1.png'">
                                <button class="btn-favorito">
                                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                        <path d="M12 21.35L10.55 20.03C5.4 15.36 2 12.28 2 8.5C2 5.42 4.42 3 7.5 3C9.24 3 10.91 3.81 12 5.09C13.09 3.81 14.76 3 16.5 3C19.58 3 22 5.42 22 8.5C22 12.28 18.6 15.36 13.45 20.04L12 21.35Z"/>
                                    </svg>
                                </button>
                            </div>
                            <div class="card-info">
                                <div class="card-info-header">
                                    <h3>${q.tipo}</h3>
                                    <div class="preco-container">
                                        <span class="preco">R$ ${q.valorBase.toLocaleString('pt-BR', {minimumFractionDigits: 2})}</span>
                                        <span class="diaria">POR DIÁRIA</span>
                                    </div>
                                </div>
                                <p class="subtitulo">${nomeResidencia} - Maraú, BA</p>
                                <div class="tags">${tags.join('')}</div>
                            </div>
                        </div>
                    `;
                }).join('');
            } catch (err) {
                console.error('Erro na busca:', err);
            }
        });
    }

    if (checkinInput) { checkinInput.type = 'date'; checkinInput.placeholder = ''; }
    if (checkoutInput) { checkoutInput.type = 'date'; checkoutInput.placeholder = ''; }
});
