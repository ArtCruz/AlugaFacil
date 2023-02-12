package com.example.myapplication_arthur.ui.cadastrarVeiculo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication_arthur.DAO.VeiculoDAO;
import com.example.myapplication_arthur.Exceptions.CampoVazioException;
import com.example.myapplication_arthur.Exceptions.NumeroInvalidoException;
import com.example.myapplication_arthur.Model.Veiculo;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentCadastrarVeiculoBinding;
import com.example.myapplication_arthur.util.ImageUtil;
import com.example.myapplication_arthur.util.MaskEditUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CadastrarVeiculoFragment extends Fragment {

    private static final String SHARED_PREFS = "sharedPrefs";
    private FragmentCadastrarVeiculoBinding binding;
    private static final String[] itensMarca= new String[]{"Abarth","Aiways","Alfa Romeo","Alpine","Aston Martin","Audi","Bentley",
            "BMW","Citroën","Cupra","Dacia","DS","Ferrari","Fiat","Ford","Honda","Hyundai","Jaguar",
            "JEEP","KIA","Lamborghini","Land Rover", "Lexus", "Lotus", "Maserati", "Mazda", "Mercedes-Benz",
            "MG", "MINI","Mitsubishi", "Nissan","Opel", "Peugeot", "Polestar","Porsche","Renault",
            "SEAT","Skoda","Smart","Suzuki","TESLA","Toyota","Volkswagen","Volvo"};

    private static final String[] itensModelo= new String[]{"Sedan", "Hatch","Perua/Station Wagon",
            "Notchback","Cupê","Fastback","Conversível","Targa","Roadster","Minivan","Van","SUV",
            "Crossover","Picape","Furgão"};
    private VeiculoDAO veiculoDAO;

    private Veiculo veiculo;
    private AutoCompleteTextView autoCompleteMarca;
    private ArrayAdapter<String> arrayAdapterMarca;
    private AutoCompleteTextView autoCompleteModelo;
    private EditText especificacoes;
    private EditText chassi;
    private EditText placa;
    private EditText valorDiaria;
    private Button tirarFotoVeic;
    private Button salvarVeic;
    private ImageView imagem;

    private String imgString = "";

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastrar_veiculo);
    }

    public void onCreateTeste(Bundle savedInstanceState, View root){
        AutoCompleteTextView autoCompleteMarca = (AutoCompleteTextView) root.findViewById(R.id.acMarca);
        ArrayAdapter<String> adapterMarca = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itensMarca);
        autoCompleteMarca.setAdapter(adapterMarca);

        AutoCompleteTextView autoCompleteModelo = (AutoCompleteTextView) root.findViewById(R.id.acModelo);
        ArrayAdapter<String> adapterModelo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itensModelo);
        autoCompleteModelo.setAdapter(adapterModelo);

        especificacoes = (EditText) root.findViewById(R.id.ptEspecCadVeic);
        chassi = (EditText) root.findViewById(R.id.ptChassiCadVeic);
        placa = (EditText) root.findViewById(R.id.ptPlacaCadVeic2);
        valorDiaria = (EditText) root.findViewById(R.id.total);
        imagem = root.findViewById(R.id.imageViewcadastrarveic);
        tirarFotoVeic = root.findViewById(R.id.btnTirarFotoCadVeic);
        salvarVeic = root.findViewById(R.id.btnSalvarAlugar);


        //camera
        tirarFotoVeic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
        //fim camera

        salvarVeic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> camposValues = new HashMap<>();

                camposValues.put("marca", autoCompleteMarca.getText().toString());
                camposValues.put("modelo", autoCompleteModelo.getText().toString());
                camposValues.put("especificacao", especificacoes.getText().toString());
                camposValues.put("chassi", chassi.getText().toString());
                camposValues.put("placa", placa.getText().toString());
                camposValues.put("valorDiaria", valorDiaria.getText().toString());

                if(validaCampos(camposValues)) {

                    try {
                        veiculo = new Veiculo();

                        veiculo.setMarca(camposValues.get("marca"));
                        veiculo.setModelo(camposValues.get("modelo"));
                        veiculo.setEspecificacoes(camposValues.get("especificacao"));
                        veiculo.setChassi(camposValues.get("chassi"));
                        veiculo.setPlaca(camposValues.get("placa"));
                        veiculo.setValorDiaria(Float.parseFloat(camposValues.get("valorDiaria").replace(",", "")));
                        veiculo.setImagem(imgString);

                        long id = veiculoDAO.adicionarVeiculo(veiculo);
                        veiculo.setCodigo(id);
                        Toast.makeText(binding.getRoot().getContext(), "Veículo inserido com sucesso!", Toast.LENGTH_SHORT).show();

                        autoCompleteMarca.setText("");
                        autoCompleteModelo.setText("");
                        especificacoes.setText("");
                        chassi.setText("");
                        placa.setText("");
                        valorDiaria.setText("");
                        imagem.setImageBitmap(null);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(binding.getRoot().getContext(), "Veículo inserido com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CadastrarVeiculoViewModel cadastrarVeiculoViewModel =

                new ViewModelProvider(this).get(CadastrarVeiculoViewModel.class);

        binding = FragmentCadastrarVeiculoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        veiculoDAO =  new VeiculoDAO(root.getContext());

        final TextView textView = binding.tvMarca;
        cadastrarVeiculoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        onCreateTeste(savedInstanceState, root);

        return root;
    }


    private void setContentView(int fragment_cadastrar_veiculo) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagemv = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imagemv);

            imgString = ImageUtil.convertBitMapToString(imagemv);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chassi = (EditText) view.findViewById(R.id.ptChassiCadVeic);
        chassi.addTextChangedListener(MaskEditUtil.mask(chassi, MaskEditUtil.FORMAT_CHASSI));

        placa = (EditText) view.findViewById(R.id.ptPlacaCadVeic2);
        placa.addTextChangedListener(MaskEditUtil.mask(placa, MaskEditUtil.FORMAT_PLACA));

        valorDiaria = (EditText) view.findViewById(R.id.total);
        valorDiaria.addTextChangedListener(MaskEditUtil.moneyMask(valorDiaria));
    }

    private boolean validaCampos(HashMap<String,String> camposValues){
        boolean sucesso = true;

        Iterator iterator = camposValues.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry element = (Map.Entry) iterator.next();
            String elementValue = (String) element.getValue();
            if(sucesso == true && elementValue.isEmpty()){
                sucesso = false;
            }
        }

        return sucesso;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}