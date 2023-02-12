package com.example.myapplication_arthur.ui.consultarVeiculo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarVeiculoViewModel extends ViewModel {

    private final MutableLiveData<String> text;

    public ConsultarVeiculoViewModel() {
        this.text = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return text;
    }
}
