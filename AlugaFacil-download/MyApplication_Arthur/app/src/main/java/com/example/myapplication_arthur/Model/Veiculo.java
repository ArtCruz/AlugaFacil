package com.example.myapplication_arthur.Model;

import com.example.myapplication_arthur.Exceptions.*;

public class Veiculo {

    private long codigo;
    private String marca;
    private String modelo;
    private String especificacoes;
    private String chassi;
    private String placa;
    private float valorDiaria;
    private String imagem;

    public Veiculo() {
    }

    public Veiculo(long codigo, String marca, String modelo, String especificacoes, String chassi, String placa, float valorDiaria, String imagem) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.especificacoes = especificacoes;
        this.chassi = chassi;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
        this.imagem = imagem;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setEspecificacoes(String especificacoes){
        this.especificacoes = especificacoes;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getChassi() {
        return chassi;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public float getValorDiaria() {
        return valorDiaria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return marca + ", " + modelo;
    }
}
