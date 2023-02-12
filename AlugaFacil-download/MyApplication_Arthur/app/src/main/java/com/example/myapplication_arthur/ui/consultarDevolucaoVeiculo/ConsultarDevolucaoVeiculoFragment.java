package com.example.myapplication_arthur.ui.consultarDevolucaoVeiculo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.Adapter.ConsultarDevolucaoVeiculoAdapter;
import com.example.myapplication_arthur.DAO.VeiculoAlugadoDAO;
import com.example.myapplication_arthur.Model.VeiculoAlugado;
import com.example.myapplication_arthur.databinding.FragmentConsultarDevolucaoVeiculoBinding;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.util.SpacingItem;

import java.util.ArrayList;

public class ConsultarDevolucaoVeiculoFragment extends Fragment {

    private ConsultarDevolucaoVeiculoViewModel viewModel;
    private FragmentConsultarDevolucaoVeiculoBinding binding;
    private ConsultarDevolucaoVeiculoAdapter adapter;
    private ArrayList<VeiculoAlugado> itens;
    private VeiculoAlugadoDAO dao;
    private RecyclerView recyclerView;

    public static ConsultarDevolucaoVeiculoFragment newInstance(){
        return new ConsultarDevolucaoVeiculoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConsultarDevolucaoVeiculoBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.fragment_consultar_devolucao_veiculo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //viewModel = new ViewModelProvider(this).get(ConsultarDevolucaoVeiculoViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewConsultarDevolucaoVeiculo);
        dao = new VeiculoAlugadoDAO(view.getContext());
        itens = dao.consultaVeiculoAlugado();
        adapter = new ConsultarDevolucaoVeiculoAdapter(getContext(), itens);
        SpacingItem itemDevolucaoVeiculo = new SpacingItem(10);
        recyclerView.addItemDecoration(itemDevolucaoVeiculo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);

    }
}
