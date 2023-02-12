package com.example.myapplication_arthur.ui.consultarDevolucaoVeiculo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarDevolucaoVeiculoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public ConsultarDevolucaoVeiculoViewModel(MutableLiveData<String> mText) {
        this.mText = mText;
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
