package com.example.myapplication_arthur.ui.alugarCarro;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication_arthur.DAO.ClienteDAO;
import com.example.myapplication_arthur.DAO.VeiculoAlugadoDAO;
import com.example.myapplication_arthur.DAO.VeiculoDAO;
import com.example.myapplication_arthur.Exceptions.CampoVazioException;
import com.example.myapplication_arthur.Exceptions.NumeroInvalidoException;
import com.example.myapplication_arthur.MainActivity;
import com.example.myapplication_arthur.Model.Cliente;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.Model.VeiculoAlugado;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentAlugarCarroBinding;
import com.example.myapplication_arthur.databinding.FragmentCadastrarVeiculoBinding;
import com.example.myapplication_arthur.ui.cadastrarVeiculo.CadastrarVeiculoViewModel;
import com.example.myapplication_arthur.util.DateUtil;
import com.example.myapplication_arthur.util.MaskEditUtil;
import com.example.myapplication_arthur.util.Principal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AlugarCarroFragment extends Fragment {
    private static final String SHARED_PREFS = "sharedPrefs";
    private FragmentAlugarCarroBinding binding;

    private AutoCompleteTextView cliente;
    private AutoCompleteTextView veiculo;
    private EditText valorDiaria;
    private EditText dataRetirada;
    private EditText dataDevolucao;
    private EditText total;
    private Button salvar;

    private VeiculoAlugado veiculoAlugado;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alugar_carro);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlugarCarroViewModel alugarCarroViewModel =
                new ViewModelProvider(this).get(AlugarCarroViewModel.class);

        binding = FragmentAlugarCarroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        VeiculoDAO veiculoDAO = new VeiculoDAO(getContext());
        veiculoAlugado = new VeiculoAlugado();
        long veicodigo = Principal.getChaves().get("veiculo_codigo");
        veiculoAlugado.setVeiculo(veiculoDAO.consultaVeiculoByCodigo(veicodigo));

        veiculo = root.findViewById(R.id.acveiculo);
        veiculo.setText(veiculoAlugado.getVeiculo().toString());
        veiculo.setEnabled(false);

        cliente = root.findViewById(R.id.acCliente);

        valorDiaria = root.findViewById(R.id.total);
        valorDiaria.setText(String.valueOf(veiculoAlugado.getVeiculo().getValorDiaria()));
        valorDiaria.setEnabled(false);

        dataRetirada = root.findViewById(R.id.dataRetirada);
        dataDevolucao = root.findViewById(R.id.dataDevolucao);
        total = root.findViewById(R.id.total);

        //alugarCarroViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        salvar = root.findViewById(R.id.btnSalvarAlugar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    veiculoAlugado.setDataRetirada((Date) dataRetirada.getText());
                    veiculoAlugado.setDataDevolucao((Date) dataDevolucao.getText());
                    veiculoAlugado.setStatus(VeiculoAlugadoDAO.STATUS_VEICULO_ALUGADO);
                    veiculoAlugado.setValorPagar(Float.parseFloat(valorDiaria.getText().toString()));

                    VeiculoAlugadoDAO alugadoDAO = new VeiculoAlugadoDAO(root.getContext());
                    alugadoDAO.adicionaVeiculoAlugado(veiculoAlugado);
                    Toast.makeText(root.getContext(), "Veiculo Alugado", Toast.LENGTH_LONG);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataRetirada = view.findViewById(R.id.dataRetirada);
        dataRetirada.addTextChangedListener(MaskEditUtil.mask(dataRetirada, MaskEditUtil.FORMAT_DATE));
        dataRetirada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                MainActivity c = (MainActivity) view.getContext();
                float valorDiaria = veiculoAlugado.getVeiculo().getValorDiaria();
                EditText etTotal = c.findViewById(R.id.total);
                if(!focus && !dataRetirada.getText().toString().isEmpty()){
                    String strDataRetirada = dataRetirada.getText().toString();
                    Date now = new Date();
                    if(!DateUtil.compararDataAtual(strDataRetirada)){
                        dataRetirada.setText("");
                        Toast.makeText(view.getContext(), "A data inserida deve ser maior ou igual a data atual (" + now.toString() + ")", Toast.LENGTH_LONG);
                    }else {
                        EditText etDataDevolucao = c.findViewById(R.id.dataDevolucao);
                        String strDataDevolucao = etDataDevolucao.getText().toString();
                        if(!strDataDevolucao.isEmpty()) {
                            long dif = DateUtil.diferencaDias(strDataRetirada, strDataDevolucao);
                            valorDiaria = valorDiaria * dif;
                        }
                    }
                }
                etTotal.setText(String.valueOf(valorDiaria));
            }
        });

        dataDevolucao = view.findViewById(R.id.dataDevolucao);
        dataDevolucao.addTextChangedListener(MaskEditUtil.mask(dataDevolucao, MaskEditUtil.FORMAT_DATE));
        dataDevolucao.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean focus) {
                MainActivity c = (MainActivity) view.getContext();
                float valorDiaria = veiculoAlugado.getVeiculo().getValorDiaria();
                EditText etTotal = c.findViewById(R.id.total);
                EditText etDataRetirada = c.findViewById(R.id.dataRetirada);
                String strDataRetirada = etDataRetirada.getText().toString();
                if(!focus && !dataDevolucao.getText().toString().isEmpty()){
                    String strDataDevolucao = dataDevolucao.getText().toString();
                    Date now = new Date();
                    if(!DateUtil.compararDataAtual(strDataDevolucao) || !DateUtil.compararDatas(strDataDevolucao, strDataRetirada)){
                        dataDevolucao.setText("");
                        Toast.makeText(view.getContext(), "A data inserida invalida", Toast.LENGTH_LONG);
                    }else {
                        if(!strDataRetirada.isEmpty()) {
                            long dif = DateUtil.diferencaDias(strDataRetirada, strDataDevolucao);
                            valorDiaria = valorDiaria * dif;
                        }
                    }
                }
                etTotal.setText(String.valueOf(valorDiaria));
            }
        });

        ClienteDAO dao = new ClienteDAO(view.getContext());
        ArrayList<Cliente> listaCliente = dao.consultaCliente();
        String[] autoCompleteOptions = getOptionsAutoComplete(listaCliente);
        cliente = view.findViewById(R.id.acCliente);
        cliente.setAdapter(new ArrayAdapter<String>(view.getContext(),R.layout.linha_recycle_consulta_cliente, R.id.linhaNomeConsultaCliente, autoCompleteOptions));
        cliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                veiculoAlugado.setCliente(listaCliente.get(position));
                Object o = adapterView.getItemAtPosition(position);
                cliente.setText(veiculoAlugado.getCliente().getNome());
            }
        });

        salvar = view.findViewById(R.id.btnSalvarAlugar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(veiculoAlugado != null) {
                        veiculoAlugado.setDataRetirada(DateUtil.converteStringToDate(dataRetirada.getText().toString(), DateUtil.FORMATO_DIA_MES_ANO));
                        veiculoAlugado.setDataDevolucao(DateUtil.converteStringToDate(dataDevolucao.getText().toString(), DateUtil.FORMATO_DIA_MES_ANO));
                        veiculoAlugado.setStatus(VeiculoAlugadoDAO.STATUS_VEICULO_ALUGADO);
                        veiculoAlugado.setValorPagar(Float.parseFloat(valorDiaria.getText().toString()));

                        VeiculoAlugadoDAO alugadoDAO = new VeiculoAlugadoDAO(binding.getRoot().getContext());
                        alugadoDAO.adicionaVeiculoAlugado(veiculoAlugado);

                        NavController navController = Navigation.findNavController((MainActivity) binding.getRoot().getContext(), R.id.nav_host_fragment_content_main);
                        navController.navigate(R.id.nav_alugar_veiculo_inicial);

                        Toast.makeText(view.getContext(), "Veiculo Alugado", Toast.LENGTH_LONG);
                    }
                }catch(Exception e){
                    Toast.makeText(view.getContext(), "Não foi possível realizar a inclusão do veiculo", Toast.LENGTH_LONG);
                    e.printStackTrace();
                }
            }
        });
    }

    private void setContentView(int fragment_cadastrar_veiculo) {
    }

    private String[] getOptionsAutoComplete(ArrayList<Cliente> itens){
        String[] result = new String[itens.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = itens.get(i).getNome();
        }

        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
