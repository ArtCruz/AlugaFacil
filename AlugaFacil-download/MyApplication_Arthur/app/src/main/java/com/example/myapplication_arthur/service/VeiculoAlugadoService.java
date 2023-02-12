package com.example.myapplication_arthur.service;

import com.example.myapplication_arthur.Model.VeiculoAlugado;
import com.example.myapplication_arthur.util.DateUtil;

import java.util.Date;

public class VeiculoAlugadoService {

    private VeiculoAlugado veiculoAlugado;

    public VeiculoAlugadoService(VeiculoAlugado veiculoAlugado) {
        this.veiculoAlugado = veiculoAlugado;
    }

    public float calculaValorPagar(float valorDiaria, String strDataRetirada, String strDataDevolucao){
        long dif = DateUtil.diferencaDias(strDataRetirada, strDataDevolucao);
        return valorDiaria * dif;
    }

    public boolean isDataDevolucaoInformada(String dataDevolucao) {
        return !dataDevolucao.isEmpty() || DateUtil.isDataValida(dataDevolucao, DateUtil.FORMATO_DIA_MES_ANO);
    }

    public Date getDataDevolucaoValidada(String strDataDevolucaoNova) {
        String dataRetirada = DateUtil.converteDateToString(veiculoAlugado.getDataRetirada(), DateUtil.FORMATO_DIA_MES_ANO);
        Date retorno = veiculoAlugado.getDataDevolucao();
        if(DateUtil.compararDatas(strDataDevolucaoNova, dataRetirada)){
            retorno = DateUtil.converteStringToDate(strDataDevolucaoNova, DateUtil.FORMATO_DIA_MES_ANO);
        }

        return retorno;
    }

    public float calculaValorAdicional(Date dataDevolucaoNova) {
        long qtdaDias = 0;
        if(DateUtil.compararDatas(dataDevolucaoNova, veiculoAlugado.getDataDevolucao())) {
            qtdaDias = DateUtil.diferencaDias(veiculoAlugado.getDataRetirada(), dataDevolucaoNova);
        }
        return (veiculoAlugado.getValorPagar()/2) * qtdaDias;
    }

    public float calculaNovoValorPagar(float valorAdicional) {
        return veiculoAlugado.getValorPagar() + valorAdicional;
    }
}
