package com.example.myapplication_arthur.ui.consultarCliente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarClienteViewModel extends ViewModel {

    private final MutableLiveData<String> text;

    public ConsultarClienteViewModel() {
        this.text = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return text;
    }
}
