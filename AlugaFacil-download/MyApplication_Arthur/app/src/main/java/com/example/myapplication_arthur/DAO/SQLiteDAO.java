package com.example.myapplication_arthur.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDAO extends SQLiteOpenHelper {

    private static final String database = "aluga_facil";
    private static final int versao = 1;
    private static SQLiteDAO instance;
    private static Context context;

    private SQLiteDAO(Context context) {
        super(context, database, null, versao);
    }

    public static SQLiteDAO getInstance(Context c){
        if(instance == null){
            context = c;
            instance = new SQLiteDAO(context);
        }
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase query) {
        query.execSQL(
                "CREATE TABLE IF NOT EXISTS tbcliente(" +
                        "clicodigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "clinome VARCHAR(200) NOT NULL," +
                        "clicpf VARCHAR(14) NOT NULL," +
                        "clitelefone VARCHAR(15) NOT NULL," +
                        "clicnh INTEGER NOT NULL," +
                        "cliendereco TEXT NOT NULL," +
                        "cliimagem TEXT" +
                        ")"
        );
        query.execSQL(
                "CREATE TABLE IF NOT EXISTS tbveiculo(" +
                        "veicodigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "veimarca VARCHAR(60) NOT NULL," +
                        "veimodelo VARCHAR(60) NOT NULL," +
                        "veiespecificacao TEXT," +
                        "veichassi TEXT NOT NULL," +
                        "veiplaca VARCHAR(10) NOT NULL," +
                        "veivalordiaria REAL NOT NULL," +
                        "veiimagem TEXT);"
        );
        query.execSQL(
                "CREATE TABLE IF NOT EXISTS tbveiculoalugado(" +
                        "valcodigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "veicodigo INTEGER NOT NULL," +
                        "clicodigo INTEGER NOT NULL," +
                        "valdataretirada DATE NOT NULL," +
                        "valdatadevolucao DATE NOT NULL," +
                        "valvalorpagar REAL NOT NULL," +
                        "valvaloradicional REAL," +
                        "valobservacao TEXT," +
                        "valstatus INTEGER NOT NULL," +
                        "CONSTRAINT \"fk_veiculoalugado_veiculo\" FOREIGN KEY(veicodigo) REFERENCES tbveiculo(veicodigo)," +
                        "CONSTRAINT \"fk_veiculoalugado_cliente\" FOREIGN KEY(clicodigo) REFERENCES tbcliente(clicodigo)" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
