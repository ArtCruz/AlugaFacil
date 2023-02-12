package com.example.myapplication_arthur.service;

import com.example.myapplication_arthur.DAO.VeiculoAlugadoDAO;
import com.example.myapplication_arthur.Model.Cliente;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.Model.VeiculoAlugado;
import com.example.myapplication_arthur.util.DateUtil;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;


public class VeiculoAlugadoServiceTest {

    private VeiculoAlugado veiculoAlugado;
    private VeiculoAlugadoService veiculoAlugadoService;

    @Before
    public void inicializaTeste(){
        veiculoAlugado = new VeiculoAlugado();
        veiculoAlugadoService = new VeiculoAlugadoService(veiculoAlugado);

        veiculoAlugado.setVeiculo(getVeiculoInicial());
        veiculoAlugado.setCliente(getClienteInicial());
        veiculoAlugado.setDataRetirada(DateUtil.converteStringToDate("11/12/2022", DateUtil.FORMATO_DIA_MES_ANO));
        veiculoAlugado.setDataDevolucao(DateUtil.converteStringToDate("14/12/2022", DateUtil.FORMATO_DIA_MES_ANO));
        veiculoAlugado.setStatus(VeiculoAlugadoDAO.STATUS_VEICULO_ALUGADO);

        float valorDiaria = veiculoAlugado.getVeiculo().getValorDiaria();
        String dataRetirada = DateUtil.converteDateToString(veiculoAlugado.getDataRetirada(), DateUtil.FORMATO_DIA_MES_ANO);
        String dataDevolucao = DateUtil.converteDateToString(veiculoAlugado.getDataDevolucao(), DateUtil.FORMATO_DIA_MES_ANO);
        veiculoAlugado.setValorPagar(veiculoAlugadoService.calculaValorPagar(valorDiaria, dataRetirada, dataDevolucao));
    }

    //Teste sem data de devolução
    @Test
    public void testDataDevolucaoInformada(){
        String dataDevolucaoVazia = "14/12/2022";
        assertTrue(veiculoAlugadoService.isDataDevolucaoInformada(dataDevolucaoVazia));
    }

    //Teste com nova data de devolução e sem valor adicional
    @Test
    public void testNovaDataDevolucaoSemValorAdicional(){
        String strDataDevolucaoNova = "13/12/2022";
        float valorAdicional = 0f;
        float valorPagar = veiculoAlugado.getValorPagar();
        if(veiculoAlugadoService.isDataDevolucaoInformada(strDataDevolucaoNova)){
            Date dataDevolucaoNova = veiculoAlugadoService.getDataDevolucaoValidada(strDataDevolucaoNova);
            valorAdicional = veiculoAlugadoService.calculaValorAdicional(dataDevolucaoNova);
        }
        float valorPagarAtualizado = veiculoAlugadoService.calculaNovoValorPagar(valorAdicional);
        Float comparar = new Float(valorPagar);
        assertTrue(comparar.equals(new Float(valorPagarAtualizado)));
    }

    //Teste com nova data de devolução valor do veiculo com valor adicional
    @Test
    public void testNovaDataDevolucaoComValorAdicional(){
        String strDataDevolucaoNova = "16/12/2022";
        float valorAdicional = 0f;
        float valorPagar = veiculoAlugado.getValorPagar();
        if(veiculoAlugadoService.isDataDevolucaoInformada(strDataDevolucaoNova)){
            Date dataDevolucaoNova = veiculoAlugadoService.getDataDevolucaoValidada(strDataDevolucaoNova);
            valorAdicional = veiculoAlugadoService.calculaValorAdicional(dataDevolucaoNova);
        }
        float valorPagarAtualizado = veiculoAlugadoService.calculaNovoValorPagar(valorAdicional);
        assertNotEquals(valorPagar, valorPagarAtualizado);
    }

    private Veiculo getVeiculoInicial(){
        Veiculo veiculo = new Veiculo();

        veiculo.setCodigo(1);
        veiculo.setImagem("");
        veiculo.setValorDiaria(90.00f);
        veiculo.setPlaca("LNM1234");
        veiculo.setChassi("12345678909876543");
        veiculo.setEspecificacoes("4 portas, azul, hatchback");
        veiculo.setMarca("Ford");
        veiculo.setModelo("Fiesta");

        return veiculo;
    }

    private Cliente getClienteInicial(){
        Cliente cliente = new Cliente();

        cliente.setImagem("");
        cliente.setEndereco("Rua Dos Bobos, nº 0, Laugh Tale/Grand Line");
        cliente.setNome("Lucas Eduardo Nogueira");
        cliente.setCnh("12345678909");
        cliente.setCodigo(1);
        cliente.setCpf("123.456.789-09");
        cliente.setTelefone("(99)99999-9999");

        return cliente;
    }
}
