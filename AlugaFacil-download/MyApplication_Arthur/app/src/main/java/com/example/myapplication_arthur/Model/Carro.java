package com.example.myapplication_arthur.Model;

import android.widget.ImageView;

import com.example.myapplication_arthur.Exception.CampoVazioException;
import com.example.myapplication_arthur.Exception.NumeroInvalidoException;

public class Carro {
        private String marca;
        private String modelo;
        private String especificacoes;
        private String chassi;
        private String placa;
        private float valorDiaria;
        private ImageView foto;


        public Carro(String marca, String modelo, String especificacoes, String chassi, String placa, float valorDiaria, ImageView foto){
            this.marca = marca;
            this.modelo = modelo;
            this.especificacoes = especificacoes;
            this.chassi = chassi;
            this.placa = placa;
            this.valorDiaria = valorDiaria;
            this.foto = foto;

        }

    @Override
    public String toString() {
        return marca + ", " + modelo ;
    }


    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public void setMarca(String marca) throws CampoVazioException {
            if(marca.isEmpty()) {
                throw new CampoVazioException();
            }
            this.marca = marca;
        }

        public String getMarca() {
            return marca;
        }

        public void setModelo(String modelo) throws CampoVazioException {
            if(modelo.isEmpty()) {
                throw new CampoVazioException();
            }
            this.modelo = modelo;
        }

        public String getModelo() {
            return modelo;
        }

        public void setEspecificacoes(String especificacoes) throws CampoVazioException {
            if(especificacoes.isEmpty()) {
                throw new CampoVazioException();
            }
            this.especificacoes = especificacoes;
        }

        public String getEspecificacoes() {
            return especificacoes;
        }

        public void setChassi(String chassi) throws CampoVazioException {
            if(chassi.isEmpty()) {
                throw new CampoVazioException();
            }
            this.chassi = chassi;
        }

        public String getChassi() {
            return chassi;
        }

        public void setPlaca(String placa) throws CampoVazioException {
            if(placa.isEmpty()) {
                throw new CampoVazioException();
            }
            this.placa = placa;
        }

        public String getPlaca() {
            return placa;
        }

        public void setValorDiaria(float valorDiaria) throws NumeroInvalidoException {
            if(valorDiaria<=0){
                throw  new NumeroInvalidoException();
            }
            this.valorDiaria = valorDiaria;
        }

        public float getValorDiaria() {
            return valorDiaria;
        }
    }

