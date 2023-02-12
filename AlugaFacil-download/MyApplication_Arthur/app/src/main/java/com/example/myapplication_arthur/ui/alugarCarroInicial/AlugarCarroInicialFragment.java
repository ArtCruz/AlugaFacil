package com.example.myapplication_arthur.ui.alugarCarroInicial;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication_arthur.Adapter.AlugarAdapter;
import com.example.myapplication_arthur.DAO.VeiculoDAO;
import com.example.myapplication_arthur.Model.Carro;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentAlugarCarroInicialBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 * create an instance of this fragment.
 */
public class AlugarCarroInicialFragment extends Fragment {

    private AlugarCarroInicialViewModel mViewModel;
    private FragmentAlugarCarroInicialBinding binding;
    private RecyclerView recycler;
    private AlugarAdapter adapter;
    private ArrayList<Carro> itens; //passar pro bb
    private VeiculoDAO dao;

    public static AlugarCarroInicialFragment newInstance(){return new AlugarCarroInicialFragment();}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlugarCarroInicialViewModel.class);
        // TODO: Use the ViewModel
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAlugarCarroInicialBinding.inflate(inflater,container, false);
        View root = binding.getRoot();


        // att o recycler view
        recycler = root.findViewById(R.id.recyclerAlugar);
        Context mainActivityContext = getContext();

        dao = new VeiculoDAO(binding.getRoot().getContext());
        ArrayList<Veiculo> veiculos = dao.consultaVeiculoNaoAlugado();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mainActivityContext);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(new AlugarAdapter(mainActivityContext, veiculos));
        recycler.setVisibility(View.VISIBLE);


        return root;

    }

}