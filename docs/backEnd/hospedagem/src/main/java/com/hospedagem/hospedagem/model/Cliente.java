package com.hospedagem.hospedagem.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

    private static List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrar(String nome, String cpf, String endereco, String telefone, String email) {

        Cliente cliente = new Cliente();

        cliente.id = clientes.size() + 1;
        cliente.nome = nome;
        cliente.cpf = cpf;
        cliente.endereco = endereco;
        cliente.telefone = telefone;
        cliente.email = email;

        clientes.add(cliente);

        return cliente;
    }

    public void atualizar(int id, String nome, String cpf, String endereco, String telefone, String email) {
        for (Cliente cliente : clientes) {

            if (cliente.id == id) {
                cliente.nome = nome;
                cliente.cpf = cpf;
                cliente.endereco = endereco;
                cliente.telefone = telefone;
                cliente.email = email;
                break;
            }
        }
    }

    public void excluir(int id) {
        for (Cliente cliente : clientes) 
            {
            if (cliente.id == id) 
                {
                clientes.remove(cliente);
                break;
            }
        }
    }

    public Cliente consultar(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.id == id) {
                return cliente;
            }
        }
        return null;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
