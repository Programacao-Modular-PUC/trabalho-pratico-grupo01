const API_BASE = 'http://localhost:8080';

const api = {
    async request(method, path, body) {
        const opts = { method, headers: { 'Content-Type': 'application/json' } };
        if (body) opts.body = JSON.stringify(body);
        const res = await fetch(`${API_BASE}${path}`, opts);
        if (!res.ok) {
            const err = await res.text().catch(() => '');
            throw new Error(err || `HTTP ${res.status}`);
        }
        if (res.status === 204) return null;
        return res.json();
    },

    get(path) { return this.request('GET', path); },
    post(path, body) { return this.request('POST', path, body); },
    put(path, body) { return this.request('PUT', path, body); },
    del(path) { return this.request('DELETE', path); },

    // Quartos
    listarQuartos() { return this.get('/quartos/ativos'); },
    buscarQuarto(id) { return this.get(`/quartos/${id}`); },
    buscarQuartosDisponiveis(entrada, saida) {
        return this.get(`/quartos/disponiveis?entrada=${entrada}&saida=${saida}`);
    },

    // Clientes
    buscarClientePorNome(nome) { return this.get(`/clientes/buscar?nome=${encodeURIComponent(nome)}`); },
    criarCliente(dados) { return this.post('/clientes', dados); },
    login(dados) { return this.post('/clientes/login', dados); },

    // Reservas
    listarReservasPorCliente(clienteId) { return this.get(`/reservas/cliente/${clienteId}`); },
    criarReserva(dto) { return this.post('/reservas', dto); },

    // Residencias
    listarResidencias() { return this.get('/residencias'); },
};
