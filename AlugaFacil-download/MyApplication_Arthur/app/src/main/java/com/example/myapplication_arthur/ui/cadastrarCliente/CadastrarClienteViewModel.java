package com.example.myapplication_arthur.ui.cadastrarCliente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CadastrarClienteViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CadastrarClienteViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}