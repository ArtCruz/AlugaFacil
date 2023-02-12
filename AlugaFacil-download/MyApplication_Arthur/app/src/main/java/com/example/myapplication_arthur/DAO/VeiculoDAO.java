package com.example.myapplication_arthur.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_arthur.Model.Veiculo;

import java.util.ArrayList;

public class VeiculoDAO extends DAOAbstract{

    public VeiculoDAO(Context context) {
        super(context);
    }

    /*
        public static List<Veiculo> veiculos = new ArrayList<>();
    */
    public long adicionarVeiculo(Veiculo v) {
        ContentValues values = new ContentValues();

        values.put("veimarca", v.getMarca());
        values.put("veimodelo", v.getModelo());
        values.put("veiespecificacao", v.getEspecificacoes());
        values.put("veichassi", v.getChassi());
        values.put("veiplaca", v.getPlaca());
        values.put("veivalordiaria", v.getValorDiaria());
        values.put("veiimagem", v.getImagem());

        return getQuery().insert("tbveiculo", null, values);
    }

    public ArrayList<Veiculo> consultaVeiculo(){
        SQLiteDatabase query = getConexao().getReadableDatabase();
        Cursor cursor = query.rawQuery(
                "SELECT veicodigo," +
                        "veimarca," +
                        "veimodelo," +
                        "veiespecificacao," +
                        "veichassi," +
                        "veiplaca," +
                        "veivalordiaria," +
                        "veiimagem" +
                        " FROM tbveiculo;"
                , null);
        ArrayList<Veiculo> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                lista.add(new Veiculo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getFloat(6),
                        cursor.getString(7)));
            }while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Veiculo> consultaVeiculoNaoAlugado() {
        SQLiteDatabase query = getConexao().getReadableDatabase();
        Cursor cursor = query.rawQuery(
                "SELECT veicodigo," +
                            "veimarca," +
                            "veimodelo," +
                            "veiespecificacao," +
                            "veichassi," +
                            "veiplaca," +
                            "veivalordiaria," +
                            "veiimagem" +
                      " FROM tbveiculo" +
                      " WHERE tbveiculo.veicodigo NOT IN (" +
                        " SELECT veicodigo" +
                        " FROM tbveiculoalugado" +
                        " WHERE valstatus = 1" +
                        " );"
        , null);
        ArrayList<Veiculo> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                lista.add(new Veiculo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getFloat(6),
                        cursor.getString(7)));
            }while (cursor.moveToNext());
        }

        return lista;
    }

    public Veiculo consultaVeiculoByCodigo(long codigo) {
        SQLiteDatabase query = getConexao().getReadableDatabase();
        Cursor cursor = query.rawQuery(
                "SELECT veicodigo," +
                        "veimarca," +
                        "veimodelo," +
                        "veiespecificacao," +
                        "veichassi," +
                        "veiplaca," +
                        "veivalordiaria," +
                        "veiimagem" +
                        " FROM tbveiculo" +
                        " WHERE tbveiculo.veicodigo = " + codigo + ";"
                , null);
        Veiculo veiculo = new Veiculo();
        if(cursor.moveToFirst()){
            veiculo.setCodigo(cursor.getLong(0));
            veiculo.setMarca(cursor.getString(1));
            veiculo.setModelo(cursor.getString(2));
            veiculo.setEspecificacoes(cursor.getString(3));
            veiculo.setChassi(cursor.getString(4));
            veiculo.setPlaca(cursor.getString(5));
            veiculo.setValorDiaria(cursor.getFloat(6));
            veiculo.setImagem(cursor.getString(7));
        }

        return veiculo;
    }
}
