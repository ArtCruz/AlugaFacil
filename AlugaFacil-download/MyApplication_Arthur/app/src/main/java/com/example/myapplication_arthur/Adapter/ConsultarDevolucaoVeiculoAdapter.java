package com.example.myapplication_arthur.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.MainActivity;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.Model.VeiculoAlugado;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.ui.consultarDevolucaoVeiculo.ConsultarDevolucaoVeiculoViewModel;
import com.example.myapplication_arthur.util.ImageUtil;
import com.example.myapplication_arthur.util.Principal;

import java.util.ArrayList;

public class ConsultarDevolucaoVeiculoAdapter extends RecyclerView.Adapter<ConsultarDevolucaoVeiculoViewHolder> {

    private Context context;
    private ArrayList<VeiculoAlugado> veiculoAlugados;

    public ConsultarDevolucaoVeiculoAdapter(Context context, ArrayList<VeiculoAlugado> veiculoAlugados) {
        this.context = context;
        this.veiculoAlugados = veiculoAlugados;
    }

    @NonNull
    @Override
    public ConsultarDevolucaoVeiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_recycle_consulta_devolucao_veiculo, parent, false);
        ConsultarDevolucaoVeiculoViewHolder viewHolder = new ConsultarDevolucaoVeiculoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultarDevolucaoVeiculoViewHolder holder, int position) {
        VeiculoAlugado veiculoAlugado = veiculoAlugados.get(position);
        Veiculo veiculo = veiculoAlugado.getVeiculo();

        holder.nome.setText(veiculo.toString());
        holder.placa.setText("Placa: "+veiculo.getPlaca());
        holder.valor.setText("R$ "+veiculoAlugado.getValorPagar());

        if(!veiculo.getImagem().isEmpty()) {
            Bitmap btm = ImageUtil.convertStringToBitMap(veiculo.getImagem());
            holder.imgCarro.setImageBitmap(btm);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long codigo = veiculoAlugado.getCodigo();
                Principal.getInstance(true).getChaves().put("veiculo_alugado_codigo", codigo);

                NavController navController = Navigation.findNavController((MainActivity) view.getContext(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_manutencao_devolucao_veiculo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return veiculoAlugados.size();
    }
}
