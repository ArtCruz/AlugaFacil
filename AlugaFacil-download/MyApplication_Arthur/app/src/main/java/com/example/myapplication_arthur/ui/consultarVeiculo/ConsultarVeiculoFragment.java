package com.example.myapplication_arthur.ui.consultarVeiculo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.Adapter.ConsultaVeiculoAdapter;
import com.example.myapplication_arthur.DAO.VeiculoDAO;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentConsultarVeiculoBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConsultarVeiculoFragment extends Fragment {

    private ConsultarVeiculoViewModel viewModel;
    private FragmentConsultarVeiculoBinding binding;

    private RecyclerView recyclerView;
    private ConsultaVeiculoAdapter adapter;
    private ArrayList<Veiculo> itens;
    private VeiculoDAO dao;
    private SearchView searchView;

    public static ConsultarVeiculoFragment newInstance(){
        return new ConsultarVeiculoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConsultarVeiculoBinding.inflate(inflater,container, false);
        View root = binding.getRoot();

        return inflater.inflate(R.layout.fragment_consultar_veiculo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ConsultarVeiculoViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.searchViewConsultaVeiculo);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = view.findViewById(R.id.recyclerConsultaVeiculo);
        dao = new VeiculoDAO(view.getContext());
        itens = dao.consultaVeiculo();
        adapter = new ConsultaVeiculoAdapter(getContext(), itens);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }

    private void filterList(String text){
        List<Veiculo> filteredList = new ArrayList<>();
        for(Veiculo item : itens){
            if(item.getPlaca().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getContext(),R.string.veiculo_nao_encontrado, Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilteredList(filteredList);
        }
    }

    private String[] getOptionsAutoComplete(ArrayList<Veiculo> itens){
        String[] result = new String[itens.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = itens.get(i).getPlaca();
        }

        return result;
    }

}
