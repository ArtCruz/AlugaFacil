package com.example.myapplication_arthur.ui.cadastrarVeiculo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CadastrarVeiculoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CadastrarVeiculoViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}