package com.example.myapplication_arthur.ui.alugarCarro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlugarCarroViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AlugarCarroViewModel() {
        mText = new MutableLiveData<>();
    }


    public LiveData<String> getText() {
        return mText;
    }
}
