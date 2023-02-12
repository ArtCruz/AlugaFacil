package com.example.myapplication_arthur.ui.alugarCarroInicial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlugarCarroInicialViewModel extends ViewModel {

private final MutableLiveData<String> mText;

    public AlugarCarroInicialViewModel() {mText = new MutableLiveData<>();}

    public LiveData<String> getText() {
        return mText;
    }
}