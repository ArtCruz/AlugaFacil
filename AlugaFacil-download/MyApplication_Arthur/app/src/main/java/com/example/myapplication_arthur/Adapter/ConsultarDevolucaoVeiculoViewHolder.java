package com.example.myapplication_arthur.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication_arthur.R;

public class ConsultarDevolucaoVeiculoViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView imgCarro;
    TextView nome;
    TextView placa;
    TextView valor;

    public ConsultarDevolucaoVeiculoViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.consultaDevolucaoItem);
        imgCarro = itemView.findViewById(R.id.consultaDevolucaoVeiculoImgVeiculo);
        nome = itemView.findViewById(R.id.consultaDevolucaoNome);
        placa = itemView.findViewById(R.id.consultaDevolucaoPlaca);
        valor = itemView.findViewById(R.id.consultaDevolucaoValor);
    }
}
