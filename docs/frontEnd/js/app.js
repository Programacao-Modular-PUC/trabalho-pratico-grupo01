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
        <div class="modal-content">
            <button onclick="fecharLogin()" class="modal-close">&times;</button>
            
            <!-- Formulário de Login -->
            <div id="loginFormContainer">
                <h2>Entrar</h2>
                <p>Informe seu email e senha para acessar suas reservas.</p>
                <form id="loginForm">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" id="loginEmail" placeholder="seu@email.com" required>
                    </div>
                    <div class="form-group">
                        <label>Senha</label>
                        <input type="password" id="loginSenha" placeholder="Sua senha" required>
                    </div>
                    <div id="loginError" class="error-message"></div>
                    <button type="submit" class="btn-submit">Entrar</button>
                </form>
                <p class="switch-link">
                    Novo por aqui? <a href="#" onclick="mostrarCadastro()">Cadastre-se</a>
                </p>
            </div>
            
            <!-- Formulário de Cadastro -->
            <div id="cadastroFormContainer" style="display: none;">
                <h2>Cadastre-se</h2>
                <p>Crie sua conta para começar a reservar.</p>
                <form id="signupForm">
                    <div class="form-group">
                        <label>Nome completo</label>
                        <input type="text" id="cadNome" required>
                    </div>
                    <div class="form-group">
                        <label>CPF</label>
                        <input type="text" id="cadCpf" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" id="cadEmail" required>
                    </div>
                    <div class="form-group">
                        <label>Senha</label>
                        <input type="password" id="cadSenha" required minlength="4">
                    </div>
                    <div class="form-group">
                        <label>Telefone</label>
                        <input type="text" id="cadTelefone">
                    </div>
                    <div class="form-group">
                        <label>Endereço</label>
                        <input type="text" id="cadEndereco">
                    </div>
                    <div id="cadError" class="error-message"></div>
                    <button type="submit" class="btn-submit btn-cadastrar">Cadastrar</button>
                </form>
                <p class="switch-link">
                    Já tem uma conta? <a href="#" onclick="mostrarLogin()">Entrar</a>
                </p>
            </div>
        </div>
    `;
    document.body.appendChild(div);

    document.getElementById('loginForm').addEventListener('submit', handleLogin);
    document.getElementById('signupForm').addEventListener('submit', handleSignup);
}

function abrirLogin() {
    injectLoginModal();
    const modal = document.getElementById('loginModal');
    modal.classList.add('active');
    document.body.classList.add('modal-open');
    mostrarLogin();
}

function fecharLogin() {
    const modal = document.getElementById('loginModal');
    if (modal) modal.classList.remove('active');
    document.body.classList.remove('modal-open');
}

function mostrarLogin() {
    document.getElementById('loginFormContainer').style.display = 'block';
    document.getElementById('cadastroFormContainer').style.display = 'none';
    document.getElementById('loginError').style.display = 'none';
    document.getElementById('cadError').style.display = 'none';
}

function mostrarCadastro() {
    document.getElementById('loginFormContainer').style.display = 'none';
    document.getElementById('cadastroFormContainer').style.display = 'block';
    document.getElementById('loginError').style.display = 'none';
    document.getElementById('cadError').style.display = 'none';
}

async function handleLogin(e) {
    e.preventDefault();
    const email = document.getElementById('loginEmail').value.trim();
    const senha = document.getElementById('loginSenha').value.trim();
    const errorEl = document.getElementById('loginError');
    errorEl.style.display = 'none';

    try {
        const cliente = await api.login({ email, senha });
        session.set(cliente);
        fecharLogin();
        atualizarHeaderLogin();
    } catch (err) {
        errorEl.textContent = err.message || 'Erro ao fazer login. Verifique seus dados.';
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
        senha: document.getElementById('cadSenha').value.trim(),
        telefone: document.getElementById('cadTelefone').value.trim(),
        endereco: document.getElementById('cadEndereco').value.trim()
    };

    try {
        const cliente = await api.criarCliente(dados);
        session.set(cliente);
        fecharLogin();
        atualizarHeaderLogin();
    } catch (err) {
        errorEl.textContent = err.message || 'Erro ao cadastrar. Verifique seus dados.';
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
