# 🌴 Sistema de Hospedagem - Península de Maraú

Projeto desenvolvido para a disciplina de Programação Modular (PUC). Trata-se de um Sistema de Informação Modular com API REST para gerenciamento de hospedagens na região de Maraú – BA.

## 🎯 Objetivo do Projeto
Desenvolver um sistema completo e escalável de gerenciamento de hospedagens para atender ao aumento da demanda turística incentivada pelo Ministério do Turismo, contemplando:

- Modelagem Orientada a Objetos
- Arquitetura em camadas (Controller, Service, Repository, Model)
- API REST com Spring Boot
- Persistência em banco de dados (MySQL)
- Testes automatizados
- Aplicação de padrões de projeto

## ⚙️ Escopo do Sistema
O sistema permite realizar as seguintes operações:
- Gerenciamento de residências e quartos.
- Cadastro e autenticação de clientes.
- Realização de reservas e aluguéis.
- Cálculo automático de diárias.
- Emissão de recibos.
- Controle de disponibilidade.
- Histórico de hospedagens.

## 📋 Regras de Negócio e Requisitos
1. **Diárias:** Iniciam sempre às 12h. Entrada após 12h conta como diária completa; saída após 12h adiciona nova diária.
2. **Valor da Diária:** Calculado dinamicamente com base no valor base (definido pelo proprietário) + tipo do quarto (Solteiro/Casal) + itens adicionais (Ar-condicionado, Hidromassagem).
3. **Disponibilidade:** Um quarto não pode ser alugado se já estiver ocupado no período solicitado.
4. **Reservas Futuras:** O sistema permite agendamentos futuros.
5. **Estrutura de Entidades:**
   - **Residência:** Endereço, número, bairro, cep, telefone, email e lista de quartos.
   - **Quarto:** Tipo (individual/casal), valor base, possui ar-condicionado (boolean), possui hidromassagem (boolean).
   - **Cliente:** Nome, CPF, endereço, telefone, email.
   - **Aluguel:** Residência, quarto, cliente, data de entrada/saída, quantidade de diárias e valor final.

## 🎨 Protótipos (UI/UX)
Os wireframes e o design das telas estão sendo desenvolvidos iterativamente, focando em uma experiência limpa (Clean UI) e alinhada à identidade visual da região.
*(Imagens das telas serão adicionadas na pasta `/docs` do repositório).*
