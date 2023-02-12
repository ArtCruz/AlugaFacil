package com.example.myapplication_arthur.util;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

//Criar espaçamento entre clientes no Ver Clientes Cadastrados e no Devolucao Veiculo
public class SpacingItem extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public SpacingItem(int verticalSpaceHeight){
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        outRect.bottom = verticalSpaceHeight;

    }
}
