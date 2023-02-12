package com.example.myapplication_arthur.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DAOAbstract {

    private SQLiteDAO conexao;
    private SQLiteDatabase query;

    public DAOAbstract(Context context) {
        this.conexao = SQLiteDAO.getInstance(context);
        this.query = conexao.getWritableDatabase();
    }

    public SQLiteDatabase getQuery(){
        return query;
    }

    protected SQLiteDAO getConexao(){
        return conexao;
    }
}
