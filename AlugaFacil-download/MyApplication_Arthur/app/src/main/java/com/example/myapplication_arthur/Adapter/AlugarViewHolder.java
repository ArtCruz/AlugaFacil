package com.example.myapplication_arthur.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication_arthur.Model.Carro;
import com.example.myapplication_arthur.R;

import java.util.List;

public class AlugarViewHolder extends RecyclerView.ViewHolder {

    private List<Carro> carros; //passar p banco d dados dps
    CardView card;
    ImageView imgCarro;
    TextView nome;
    TextView placa;
    TextView valor;


    public AlugarViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.itenLista);
        imgCarro = itemView.findViewById(R.id.imagCarro);
        nome = itemView.findViewById(R.id.nomeCarro);
        placa = itemView.findViewById(R.id.placaCarro);
        valor = itemView.findViewById(R.id.valorCarro);
    }

    


}