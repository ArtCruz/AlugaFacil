package com.example.myapplication_arthur.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.MainActivity;
import com.example.myapplication_arthur.Model.Carro;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentAlugarCarroInicialBinding;
import com.example.myapplication_arthur.ui.alugarCarro.AlugarCarro;
import com.example.myapplication_arthur.ui.alugarCarro.AlugarCarroFragment;
import com.example.myapplication_arthur.util.ImageUtil;
import com.example.myapplication_arthur.util.Principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlugarAdapter extends RecyclerView.Adapter<AlugarViewHolder> {


    private Context context;
    private List<Veiculo> carros; //mudar pro bb depois


    public AlugarAdapter(Context context, List<Veiculo> carros) {
        this.context = context;
        this.carros = carros;
    }


    @NonNull
    @Override
    public AlugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.linha_recycle_alugar, parent, false);
       AlugarViewHolder viewHolder = new AlugarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlugarViewHolder holder, @SuppressLint("RecyclerView") int position) {
     Veiculo carro = carros.get(position);

     holder.nome.setText(carro.toString());
     if(!carro.getImagem().isEmpty()) {
         Bitmap btm = ImageUtil.convertStringToBitMap(carro.getImagem());
         holder.imgCarro.setImageBitmap(btm);
     }
     holder.valor.setText("R$ "+carro.getValorDiaria());
     holder.placa.setText("Placa: "+carro.getPlaca());
     final int pos = position;

     holder.card.setOnClickListener(new View.OnClickListener(){

         @Override
         public void onClick(View view) {
             Principal.getInstance(true).getChaves().put("veiculo_codigo", carro.getCodigo());

             NavController navController = Navigation.findNavController((MainActivity) view.getContext(), R.id.nav_host_fragment_content_main);
             navController.navigate(R.id.nav_manutencao_alugar_veiculo);
         }
     });

    }



    @Override
    public int getItemCount() {
        return carros.size();
    }
}
