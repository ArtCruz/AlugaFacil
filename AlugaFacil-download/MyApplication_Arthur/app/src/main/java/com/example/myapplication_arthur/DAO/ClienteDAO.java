package com.example.myapplication_arthur.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication_arthur.Model.Cliente;

import java.util.ArrayList;

public class ClienteDAO extends DAOAbstract{

    public ClienteDAO(Context context) {
        super(context);
    }

    public long adicionaCliente(Cliente c){
        ContentValues values = new ContentValues();

        values.put("clinome", c.getNome());
        values.put("clicpf", c.getCpf());
        values.put("clitelefone", c.getTelefone());
        values.put("clicnh", c.getCnh());
        values.put("cliendereco", c.getEndereco());
        values.put("cliimagem", c.getImagem());

        return getQuery().insert("tbcliente", null, values);
    }

    public ArrayList<Cliente> consultaCliente(){
        SQLiteDatabase query = getConexao().getReadableDatabase();
        Cursor cursor = query.rawQuery("SELECT clicodigo, clinome, clicpf, clicnh, clitelefone, cliendereco, cliimagem FROM tbcliente", null);
        ArrayList<Cliente> lista = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                lista.add(new Cliente(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)));
            }while (cursor.moveToNext());
        }

        return lista;
    }
}
