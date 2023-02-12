package com.example.myapplication_arthur.Interface;

import com.example.myapplication_arthur.Model.Cliente;
import java.util.List;

public interface IClienteDAO {

    void adicionarCliente(Cliente c);
    List<Cliente> retornarTodosClientes();
}
