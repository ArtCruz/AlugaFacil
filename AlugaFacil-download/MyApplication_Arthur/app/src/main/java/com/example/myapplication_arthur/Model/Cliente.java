package com.example.myapplication_arthur.Model;

public class Cliente {

    private long codigo;
    private String nome;
    private String cpf;
    private String cnh;
    private String telefone;
    private String endereco;
    private String imagem;

    public Cliente() {
    }

    public Cliente(long codigo, String nome, String cpf, String cnh, String telefone, String endereco, String imagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.telefone = telefone;
        this.endereco = endereco;
        this.imagem = imagem;
    }

    public Cliente(String nome, String cpf, String cnh, String telefone, String endereco, String imagem) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.telefone = telefone;
        this.endereco = endereco;
        this.imagem = imagem;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
