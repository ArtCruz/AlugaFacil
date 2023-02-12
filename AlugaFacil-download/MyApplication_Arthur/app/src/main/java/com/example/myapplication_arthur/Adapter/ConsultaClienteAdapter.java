package com.example.myapplication_arthur.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.R;

import com.example.myapplication_arthur.Model.Cliente;
import com.example.myapplication_arthur.util.ImageUtil;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ConsultaClienteAdapter extends RecyclerView.Adapter<ConsultaClienteViewHolder> {

    private Context context;
    private ArrayList<Cliente> clientes;

    public ConsultaClienteAdapter(Context context, ArrayList<Cliente> clientes) {
        this.context = context;
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public ConsultaClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_recycle_consulta_cliente, parent, false);
        ConsultaClienteViewHolder viewHolder = new ConsultaClienteViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);

        holder.nome.setText(cliente.getNome());
        holder.telefone.setText(cliente.getTelefone());

        if(!cliente.getImagem().isEmpty()) {
            Bitmap imgBitMap = ImageUtil.convertStringToBitMap(cliente.getImagem());
            holder.imagem.setImageBitmap(imgBitMap);
        }
    }

    public void setFilteredList(ArrayList<Cliente> filteredList){
        this.clientes = filteredList;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}
