const SESSION_KEY = 'peninsula_cliente';

const session = {
    get() {
        const data = localStorage.getItem(SESSION_KEY);
        return data ? JSON.parse(data) : null;
    },
    set(cliente) {
        localStorage.setItem(SESSION_KEY, JSON.stringify(cliente));
    },
    clear() {
        localStorage.removeItem(SESSION_KEY);
    },
    isLogged() {
        return !!this.get();
    }
};

function injectLoginModal() {
    if (document.getElementById('loginModal')) return;

    const div = document.createElement('div');
    div.id = 'loginModal';
    div.className = 'modal-overlay';
    div.innerHTML = `
        <div class="modal-content" style="
            background: #fff; border-radius: 16px; padding: 40px; width: 100%; max-width: 400px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.2); position: relative;
        ">
            <button onclick="fecharLogin()" style="
                position: absolute; top: 16px; right: 16px; background: none; border: none;
                font-size: 22px; cursor: pointer; color: #666;
            ">&times;</button>
            <h2 style="font-family: 'Plus Jakarta Sans', sans-serif; color: #00236f; margin-bottom: 8px;">Entrar</h2>
            <p style="color: #6b7280; font-size: 14px; margin-bottom: 24px;">
                Informe seu nome para acessar suas reservas.
            </p>
            <form id="loginForm">
                <div style="margin-bottom: 16px;">
                    <label style="font-size: 13px; font-weight: 500; color: #1a1c1c; display: block; margin-bottom: 6px;">Nome</label>
                    <input type="text" id="loginNome" placeholder="Seu nome completo" required
                        style="width: 100%; padding: 12px 14px; border: 1px solid #e5e5e5; border-radius: 8px; font-size: 14px;">
                </div>
                <div id="loginError" style="color: #dc2626; font-size: 13px; margin-bottom: 12px; display: none;"></div>
                <button type="submit" style="
                    width: 100%; background: #006c4a; color: #fff; border: none;
                    padding: 14px; border-radius: 8px; font-size: 15px; font-weight: 600; cursor: pointer;
                ">Entrar</button>
            </form>
            <p style="text-align: center; margin-top: 16px; font-size: 13px; color: #6b7280;">
                Novo por aqui? <a href="#" onclick="abrirCadastro()" style="color: #006c4a; font-weight: 600;">Cadastre-se</a>
            </p>
            <div id="cadastroForm" style="display: none; margin-top: 20px; border-top: 1px solid #e5e5e5; padding-top: 20px;">
                <h3 style="font-family: 'Plus Jakarta Sans', sans-serif; color: #00236f; margin-bottom: 16px;">Cadastro</h3>
                <form id="signupForm">
                    <div style="margin-bottom: 12px;">
                        <label style="font-size: 13px; font-weight: 500; display: block; margin-bottom: 4px;">Nome completo</label>
                        <input type="text" id="cadNome" required style="width: 100%; padding: 10px 12px; border: 1px solid #e5e5e5; border-radius: 8px; font-size: 14px;">
                    </div>
                    <div style="margin-bottom: 12px;">
                        <label style="font-size: 13px; font-weight: 500; display: block; margin-bottom: 4px;">CPF</label>
                        <input type="text" id="cadCpf" required style="width: 100%; padding: 10px 12px; border: 1px solid #e5e5e5; border-radius: 8px; font-size: 14px;">
                    </div>
                    <div style="margin-bottom: 12px;">
                        <label style="font-size: 13px; font-weight: 500; display: block; margin-bottom: 4px;">Email</label>
                        <input type="email" id="cadEmail" required style="width: 100%; padding: 10px 12px; border: 1px solid #e5e5e5; border-radius: 8px; font-size: 14px;">
                    </div>
                    <div style="margin-bottom: 12px;">
                        <label style="font-size: 13px; font-weight: 500; display: block; margin-bottom: 4px;">Telefone</label>
                        <input type="text" id="cadTelefone" style="width: 100%; padding: 10px 12px; border: 1px solid #e5e5e5; border-radius: 8px; font-size: 14px;">
                    </div>
                    <div style="margin-bottom: 12px;">
                        <label style="font-size: 13px; font-weight: 500; display: block; margin-bottom: 4px;">Endereço</label>
                        <input type="text" id="cadEndereco" style="width: 100%; padding: 10px 12px; border: 1px solid #e5e5e5; border-radius: 8px; font-size: 14px;">
                    </div>
                    <div id="cadError" style="color: #dc2626; font-size: 13px; margin-bottom: 8px; display: none;"></div>
                    <button type="submit" style="
                        width: 100%; background: #00236f; color: #fff; border: none;
                        padding: 12px; border-radius: 8px; font-size: 14px; font-weight: 600; cursor: pointer;
                    ">Cadastrar</button>
                </form>
            </div>
        </div>
    `;
    document.body.appendChild(div);

    document.getElementById('loginForm').addEventListener('submit', handleLogin);
    document.getElementById('signupForm').addEventListener('submit', handleSignup);
}

function abrirLogin() {
    injectLoginModal();
    document.getElementById('loginModal').classList.add('active');
    document.getElementById('loginError').style.display = 'none';
    document.body.classList.add('modal-open');
}

function fecharLogin() {
    const modal = document.getElementById('loginModal');
    if (modal) modal.classList.remove('active');
    document.body.classList.remove('modal-open');
}

function abrirCadastro() {
    document.getElementById('cadastroForm').style.display = 'block';
}

async function handleLogin(e) {
    e.preventDefault();
    const nome = document.getElementById('loginNome').value.trim();
    const errorEl = document.getElementById('loginError');
    errorEl.style.display = 'none';

    try {
        const clientes = await api.buscarClientePorNome(nome);
        if (!clientes || clientes.length === 0) {
            errorEl.textContent = 'Nome não encontrado. Cadastre-se primeiro.';
            errorEl.style.display = 'block';
            return;
        }
        session.set(clientes[0]);
        fecharLogin();
        atualizarHeaderLogin();
    } catch (err) {
        errorEl.textContent = 'Erro ao conectar com o servidor.';
        errorEl.style.display = 'block';
    }
}

async function handleSignup(e) {
    e.preventDefault();
    const errorEl = document.getElementById('cadError');
    errorEl.style.display = 'none';

    const dados = {
        nome: document.getElementById('cadNome').value.trim(),
        cpf: document.getElementById('cadCpf').value.trim(),
        email: document.getElementById('cadEmail').value.trim(),
        telefone: document.getElementById('cadTelefone').value.trim(),
        endereco: document.getElementById('cadEndereco').value.trim()
    };

    try {
        const cliente = await api.criarCliente(dados);
        session.set(cliente);
        fecharLogin();
        atualizarHeaderLogin();
    } catch (err) {
        errorEl.textContent = err.message || 'Erro ao cadastrar.';
        errorEl.style.display = 'block';
    }
}

function atualizarHeaderLogin() {
    const cliente = session.get();
    const botoes = document.querySelectorAll('.btn-login');
    botoes.forEach(btn => {
        if (cliente) {
            btn.textContent = cliente.nome.split(' ')[0];
            btn.style.background = '#00236f';
            btn.onclick = () => {
                if (confirm('Deseja sair?')) {
                    session.clear();
                    window.location.reload();
                }
            };
        } else {
            btn.textContent = 'Login';
            btn.style.background = '#006c4a';
            btn.onclick = abrirLogin;
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    injectLoginModal();
    const botoes = document.querySelectorAll('.btn-login');
    botoes.forEach(btn => {
        if (!session.get()) {
            btn.onclick = abrirLogin;
        }
    });
    atualizarHeaderLogin();
});
