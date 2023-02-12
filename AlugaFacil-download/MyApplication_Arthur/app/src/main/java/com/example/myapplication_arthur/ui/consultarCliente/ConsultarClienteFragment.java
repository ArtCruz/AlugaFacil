package com.example.myapplication_arthur.ui.consultarCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.Adapter.ConsultaClienteAdapter;
import com.example.myapplication_arthur.DAO.ClienteDAO;
import com.example.myapplication_arthur.Model.Cliente;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentConsultarClienteBinding;
import com.example.myapplication_arthur.util.SpacingItem;

import java.util.ArrayList;

public class ConsultarClienteFragment extends Fragment {

    private ConsultarClienteViewModel viewModel;
    private FragmentConsultarClienteBinding binding;

    private RecyclerView recyclerView;
    private ConsultaClienteAdapter adapter;
    private ArrayList<Cliente> itens;
    private ClienteDAO dao;
    private SearchView searchView;

    public static ConsultarClienteFragment newInstance(){
        return new ConsultarClienteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConsultarClienteBinding.inflate(inflater,container, false);
        View root = binding.getRoot();

        return inflater.inflate(R.layout.fragment_consultar_cliente, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ConsultarClienteViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.searchViewConsultaCliente);
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

        recyclerView = view.findViewById(R.id.recyclerConsultaCliente);
        dao = new ClienteDAO(view.getContext());
        itens = dao.consultaCliente();
        adapter = new ConsultaClienteAdapter(getContext(), itens);
        SpacingItem itemClientes = new SpacingItem(10);
        recyclerView.addItemDecoration(itemClientes);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }

    private void filterList(String text) {
        ArrayList<Cliente> filteredList = new ArrayList<>();
        for(Cliente item: itens){
            if(item.getNome().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getContext(),R.string.cliente_nao_encontrado,Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.setFilteredList(filteredList);
        }
    }

    private String[] getOptionsAutoComplete(ArrayList<Cliente> itens){
        String[] result = new String[itens.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = itens.get(i).getNome();
        }

        return result;
    }
}