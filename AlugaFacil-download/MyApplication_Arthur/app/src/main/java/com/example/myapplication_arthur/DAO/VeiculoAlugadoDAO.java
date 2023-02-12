package com.example.myapplication_arthur.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.myapplication_arthur.Model.VeiculoAlugado;
import com.example.myapplication_arthur.util.DateUtil;

import java.util.ArrayList;

public class VeiculoAlugadoDAO extends DAOAbstract{

    public static final int STATUS_VEICULO_ALUGADO = 1;
    public static final int STATUS_VEICULO_DEVOLVIDO = 2;

    public VeiculoAlugadoDAO(Context context) {
        super(context);
    }

    public long adicionaVeiculoAlugado(VeiculoAlugado veiculoAlugado){
        try {
            ContentValues values = new ContentValues();

            values.put("veicodigo", veiculoAlugado.getVeiculo().getCodigo());
            values.put("clicodigo", veiculoAlugado.getCliente().getCodigo());
            values.put("valdataretirada", veiculoAlugado.getDataRetirada().toString());
            values.put("valdatadevolucao", veiculoAlugado.getDataDevolucao().toString());
            values.put("valvalorpagar", veiculoAlugado.getValorPagar());
            values.put("valvaloradicional", veiculoAlugado.getValorAdicional());
            values.put("valobservacao", veiculoAlugado.getObservacao());
            values.put("valstatus", veiculoAlugado.getStatus());

            return getQuery().insert("tbveiculoalugado", null, values);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        return 0;
    }

    public boolean atualizarVeiculoAlugado(VeiculoAlugado veiculoAlugado){
        try {
            ContentValues values = new ContentValues();

            values.put("veicodigo", veiculoAlugado.getVeiculo().getCodigo());
            values.put("clicodigo", veiculoAlugado.getCliente().getCodigo());
            values.put("valdataretirada", veiculoAlugado.getDataRetirada().toString());
            values.put("valdatadevolucao", veiculoAlugado.getDataDevolucao().toString());
            values.put("valvalorpagar", veiculoAlugado.getValorPagar());
            values.put("valvaloradicional", veiculoAlugado.getValorAdicional());
            values.put("valobservacao", veiculoAlugado.getObservacao());
            values.put("valstatus", veiculoAlugado.getStatus());

            getQuery().update("tbveiculoalugado", values, "valcodigo = ?", new String[]{String.valueOf(veiculoAlugado.getCodigo())});

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<VeiculoAlugado> consultaVeiculoAlugado(){
        ArrayList<VeiculoAlugado> itens = new ArrayList<>();
        SQLiteDatabase query = getConexao().getReadableDatabase();
        try {
            Cursor cursor = query.rawQuery(
                    "SELECT valcodigo," +
                            " tbveiculoalugado.veicodigo," +
                            " tbveiculoalugado.clicodigo," +
                            " valdataretirada," +
                            " valdatadevolucao," +
                            " valvalorpagar," +
                            " valvaloradicional," +
                            " valobservacao," +
                            " valstatus," +
                            " veimarca," +
                            " veimodelo," +
                            " veiespecificacao," +
                            " veichassi," +
                            " veiplaca," +
                            " veivalordiaria," +
                            " veiimagem," +
                            " clinome," +
                            " clicpf," +
                            " clitelefone," +
                            " clicnh," +
                            " cliendereco," +
                            " cliimagem" +
                            " FROM tbveiculoalugado" +
                            " INNER JOIN tbveiculo ON tbveiculoalugado.veicodigo = tbveiculo.veicodigo" +
                            " INNER JOIN tbcliente ON tbveiculoalugado.clicodigo = tbcliente.clicodigo" +
                            " WHERE valstatus = 1;"
                    , null);

            if (cursor.moveToFirst()) {
                do {
                    VeiculoAlugado veiculoAlugado = new VeiculoAlugado();
                    veiculoAlugado.setCodigo(cursor.getLong(0));
                    veiculoAlugado.getVeiculo().setCodigo(cursor.getLong(1));
                    veiculoAlugado.getCliente().setCodigo(cursor.getLong(2));
                    veiculoAlugado.setDataRetirada(DateUtil.converteStringToDate(cursor.getString(3), DateUtil.FORMATO_DIA_MES_ANO));
                    veiculoAlugado.setDataDevolucao(DateUtil.converteStringToDate(cursor.getString(4), DateUtil.FORMATO_DIA_MES_ANO));
                    veiculoAlugado.setValorPagar(cursor.getFloat(5));
                    veiculoAlugado.setValorAdicional(cursor.getFloat(6));
                    veiculoAlugado.setObservacao(cursor.getString(7));
                    veiculoAlugado.setStatus(cursor.getInt(8));
                    veiculoAlugado.getVeiculo().setMarca(cursor.getString(9));
                    veiculoAlugado.getVeiculo().setModelo(cursor.getString(10));
                    veiculoAlugado.getVeiculo().setEspecificacoes(cursor.getString(11));
                    veiculoAlugado.getVeiculo().setChassi(cursor.getString(12));
                    veiculoAlugado.getVeiculo().setPlaca(cursor.getString(13));
                    veiculoAlugado.getVeiculo().setValorDiaria(cursor.getFloat(14));
                    veiculoAlugado.getVeiculo().setImagem(cursor.getString(15));
                    veiculoAlugado.getCliente().setNome(cursor.getString(16));
                    veiculoAlugado.getCliente().setCpf(cursor.getString(17));
                    veiculoAlugado.getCliente().setTelefone(cursor.getString(18));
                    veiculoAlugado.getCliente().setCnh(cursor.getString(19));
                    veiculoAlugado.getCliente().setEndereco(cursor.getString(20));
                    veiculoAlugado.getCliente().setImagem(cursor.getString(21));

                    itens.add(veiculoAlugado);
                } while (cursor.moveToNext());
            }

            return itens;
        }catch (Exception e){
            e.printStackTrace();
        }
        return itens;
    }

    public VeiculoAlugado getVeiculoAlugadoByCodigo(long codigo){
        SQLiteDatabase query = getConexao().getReadableDatabase();
        VeiculoAlugado veiculoAlugado = new VeiculoAlugado();
        try {
            Cursor cursor = query.rawQuery(
                    "SELECT valcodigo," +
                            " tbveiculoalugado.veicodigo," +
                            " tbveiculoalugado.clicodigo," +
                            " valdataretirada," +
                            " valdatadevolucao," +
                            " valvalorpagar," +
                            " valvaloradicional," +
                            " valobservacao," +
                            " valstatus," +
                            " veimarca," +
                            " veimodelo," +
                            " veiespecificacao," +
                            " veichassi," +
                            " veiplaca," +
                            " veivalordiaria," +
                            " veiimagem," +
                            " clinome," +
                            " clicpf," +
                            " clitelefone," +
                            " clicnh," +
                            " cliendereco," +
                            " cliimagem" +
                            " FROM tbveiculoalugado" +
                            " INNER JOIN tbveiculo ON tbveiculoalugado.veicodigo = tbveiculo.veicodigo" +
                            " INNER JOIN tbcliente ON tbveiculoalugado.clicodigo = tbcliente.clicodigo" +
                            " WHERE valcodigo = " + codigo + ";"
                    , null);
            if(cursor.moveToFirst()){
                veiculoAlugado = new VeiculoAlugado();
                veiculoAlugado.setCodigo(cursor.getLong(0));
                veiculoAlugado.getVeiculo().setCodigo(cursor.getLong(1));
                veiculoAlugado.getCliente().setCodigo(cursor.getLong(2));
                veiculoAlugado.setDataRetirada(DateUtil.converteStringToDate(cursor.getString(3), DateUtil.FORMATO_DIA_MES_ANO));
                veiculoAlugado.setDataDevolucao(DateUtil.converteStringToDate(cursor.getString(4), DateUtil.FORMATO_DIA_MES_ANO));
                veiculoAlugado.setValorPagar(cursor.getFloat(5));
                veiculoAlugado.setValorAdicional(cursor.getFloat(6));
                veiculoAlugado.setObservacao(cursor.getString(7));
                veiculoAlugado.setStatus(cursor.getInt(8));
                veiculoAlugado.getVeiculo().setMarca(cursor.getString(9));
                veiculoAlugado.getVeiculo().setModelo(cursor.getString(10));
                veiculoAlugado.getVeiculo().setEspecificacoes(cursor.getString(11));
                veiculoAlugado.getVeiculo().setChassi(cursor.getString(12));
                veiculoAlugado.getVeiculo().setPlaca(cursor.getString(13));
                veiculoAlugado.getVeiculo().setValorDiaria(cursor.getFloat(14));
                veiculoAlugado.getVeiculo().setImagem(cursor.getString(15));
                veiculoAlugado.getCliente().setNome(cursor.getString(16));
                veiculoAlugado.getCliente().setCpf(cursor.getString(17));
                veiculoAlugado.getCliente().setTelefone(cursor.getString(18));
                veiculoAlugado.getCliente().setCnh(cursor.getString(19));
                veiculoAlugado.getCliente().setEndereco(cursor.getString(20));
                veiculoAlugado.getCliente().setImagem(cursor.getString(21));
            }
            return veiculoAlugado;
        }catch (Exception e){
            e.printStackTrace();
            return veiculoAlugado;
        }
    }
}
