package com.example.myapplication_arthur.ui.devolucaoVeiculo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.myapplication_arthur.DAO.VeiculoAlugadoDAO;
import com.example.myapplication_arthur.MainActivity;
import com.example.myapplication_arthur.Model.VeiculoAlugado;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentDevolucaoVeiculoBinding;
import com.example.myapplication_arthur.service.VeiculoAlugadoService;
import com.example.myapplication_arthur.util.DateUtil;
import com.example.myapplication_arthur.util.ImageUtil;
import com.example.myapplication_arthur.util.MaskEditUtil;
import com.example.myapplication_arthur.util.Principal;

import java.util.Date;

public class DevolucaoVeiculoFragment extends Fragment {

    private DevolucaoVeiculoViewModel mViewModel;
    private FragmentDevolucaoVeiculoBinding binding;
    private EditText etMarca;
    private EditText etPlaca;
    private EditText etValorAdicional;
    private EditText etDataDevolucao;
    private EditText etObservacao;
    private EditText etValorTotal;
    private ImageView imgCampo;
    private Button btnDevolcer;

    private VeiculoAlugado veiculoAlugado;

    public static DevolucaoVeiculoFragment newInstance() {
        return new DevolucaoVeiculoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDevolucaoVeiculoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        VeiculoAlugadoDAO dao = new VeiculoAlugadoDAO(getContext());
        long valcodigo = Principal.getChaves().get("veiculo_alugado_codigo");
        veiculoAlugado = dao.getVeiculoAlugadoByCodigo(valcodigo);

        etMarca = root.findViewById(R.id.ptMarcaCadVeic2);
        etMarca.setText(veiculoAlugado.getVeiculo().getMarca());
        etMarca.setEnabled(false);

        etPlaca = root.findViewById(R.id.etPlacaDevolVeicPersonName);
        etPlaca.setText(veiculoAlugado.getVeiculo().getPlaca());
        etPlaca.setEnabled(false);

        etValorAdicional = root.findViewById(R.id.etValorAdicionalDevolVeic);
        etValorAdicional.setEnabled(false);

        etDataDevolucao = root.findViewById(R.id.editTextDate);
        etDataDevolucao.setText(DateUtil.converteDateToString(veiculoAlugado.getDataDevolucao(), DateUtil.FORMATO_DIA_MES_ANO));

        etValorTotal = root.findViewById(R.id.ptValorDiariaCadVeic2);
        etValorTotal.setText(String.valueOf(veiculoAlugado.getValorPagar()));
        etValorTotal.setEnabled(false);

        etObservacao = root.findViewById(R.id.etObservacaoDevolucaoVeiculo);

        imgCampo = root.findViewById(R.id.imageViewcadastrarveic);
        if(!veiculoAlugado.getVeiculo().getImagem().isEmpty()){
            Bitmap bitMap = ImageUtil.convertStringToBitMap(veiculoAlugado.getVeiculo().getImagem());
            imgCampo.setImageBitmap(bitMap);
        }

        btnDevolcer = root.findViewById(R.id.btnDevolVeic);
        btnDevolcer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    VeiculoAlugadoService veiculoAlugadoService = new VeiculoAlugadoService(veiculoAlugado);
                    veiculoAlugado.setObservacao(etObservacao.getText().toString());
                    String strDataDevolucaoNova = etDataDevolucao.getText().toString();
                    if(veiculoAlugadoService.isDataDevolucaoInformada(strDataDevolucaoNova)){
                        Date dataDevolucaoNova = veiculoAlugadoService.getDataDevolucaoValidada(strDataDevolucaoNova);
                        float valorAdicional = veiculoAlugadoService.calculaValorAdicional(dataDevolucaoNova);
                        float valorPagarAtualizado = veiculoAlugadoService.calculaNovoValorPagar(valorAdicional);
                        veiculoAlugado.setValorPagar(valorPagarAtualizado);
                        veiculoAlugado.setStatus(VeiculoAlugadoDAO.STATUS_VEICULO_DEVOLVIDO);
                        veiculoAlugado.setDataDevolucao(dataDevolucaoNova);
                        if(dao.atualizarVeiculoAlugado(veiculoAlugado)){
                            Toast.makeText(root.getContext(), "Devolução realizada com sucesso", Toast.LENGTH_LONG);
                            NavController navController = Navigation.findNavController((MainActivity)root.getContext(), R.id.nav_host_fragment_content_main);
                            navController.navigate(R.id.nav_devolucao_veiculo);
                        }else{
                            Toast.makeText(root.getContext(), "Dados incorretos", Toast.LENGTH_LONG);
                        }

                    }
                    else{
                        Toast.makeText(root.getContext(), "Data de devolução invalida", Toast.LENGTH_LONG);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DevolucaoVeiculoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etValorAdicional = view.findViewById(R.id.etValorAdicionalDevolVeic);
        etValorAdicional.addTextChangedListener(MaskEditUtil.moneyMask(etValorAdicional));

        etValorTotal = view.findViewById(R.id.ptValorDiariaCadVeic2);
        etValorTotal.addTextChangedListener(MaskEditUtil.moneyMask(etValorTotal));

        etDataDevolucao = view.findViewById(R.id.editTextDate);
        etDataDevolucao.addTextChangedListener(MaskEditUtil.mask(etDataDevolucao, MaskEditUtil.FORMAT_DATE));
        etDataDevolucao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                VeiculoAlugadoService veiculoAlugadoService = new VeiculoAlugadoService(veiculoAlugado);
                String strDataDevolucaoNova = etDataDevolucao.getText().toString();
                if(veiculoAlugadoService.isDataDevolucaoInformada(strDataDevolucaoNova)){
                    Date dataDevolucaoNova = veiculoAlugadoService.getDataDevolucaoValidada(strDataDevolucaoNova);
                    float valorAdicional = veiculoAlugadoService.calculaValorAdicional(dataDevolucaoNova);
                    float valorPagarAtualizado = veiculoAlugadoService.calculaNovoValorPagar(valorAdicional);
                    etValorAdicional.setText(String.valueOf(valorAdicional));
                    etValorTotal.setText(String.valueOf(valorPagarAtualizado));
                } else{
                    Toast.makeText(view.getContext(), "Data invalida ou não informada", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}