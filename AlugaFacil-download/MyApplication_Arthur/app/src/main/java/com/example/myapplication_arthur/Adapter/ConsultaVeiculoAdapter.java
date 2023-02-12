package com.example.myapplication_arthur.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.MainActivity;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.ui.alugarCarro.AlugarCarroFragment;
import com.example.myapplication_arthur.util.ImageUtil;
import com.example.myapplication_arthur.util.Principal;

import java.util.ArrayList;
import java.util.List;

public class ConsultaVeiculoAdapter extends RecyclerView.Adapter<ConsultaVeiculoViewHolder>{
    private Context context;
    private List<Veiculo> carros; //mudar pro bb depois


    public ConsultaVeiculoAdapter(Context context, ArrayList<Veiculo> carros) {
        this.context = context;
        this.carros = carros;
    }


    @NonNull
    @Override
    public ConsultaVeiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_recycle_consulta_veiculo, parent, false);
        ConsultaVeiculoViewHolder viewHolder = new ConsultaVeiculoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaVeiculoViewHolder holder, int position) {
        Veiculo carro = carros.get(position);

        holder.nome.setText(carro.toString());
        holder.placa.setText("Placa: "+carro.getPlaca());
        holder.valor.setText("R$ "+carro.getValorDiaria());

        if(!carro.getImagem().isEmpty()) {
            Bitmap btm = ImageUtil.convertStringToBitMap(carro.getImagem());
            holder.imgCarro.setImageBitmap(btm);
        }
    }

    public void setFilteredList(List<Veiculo> filteredList){
        this.carros = filteredList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return carros.size();
    }
}
