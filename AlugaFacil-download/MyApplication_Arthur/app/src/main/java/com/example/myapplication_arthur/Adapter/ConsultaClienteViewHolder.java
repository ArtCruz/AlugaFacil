package com.example.myapplication_arthur.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication_arthur.R;

public class ConsultaClienteViewHolder extends RecyclerView.ViewHolder {

    TextView nome;
    TextView telefone;
    ImageView imagem;

    public ConsultaClienteViewHolder(@NonNull View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.linhaNomeConsultaClienteValor);
        telefone = itemView.findViewById(R.id.linhaTelefoneConsultaClienteValor);
        imagem = itemView.findViewById(R.id.linhaImagemConsultaCliente);
    }
}
