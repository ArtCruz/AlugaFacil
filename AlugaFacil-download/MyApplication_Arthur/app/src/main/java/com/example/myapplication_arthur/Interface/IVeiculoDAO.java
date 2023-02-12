package com.example.myapplication_arthur.Interface;

import com.example.myapplication_arthur.Model.*;

import java.util.List;

public interface IVeiculoDAO {

    void adicionarVeiculo(Veiculo v);
    List<Veiculo> retornarTodosVeiculos();
    //void alugarVeiculo(Veiculo v, Cliente c);
}
