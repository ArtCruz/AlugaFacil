package com.example.myapplication_arthur.ui.cadastrarCliente;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_arthur.DAO.ClienteDAO;
import com.example.myapplication_arthur.Model.Cliente;
import com.example.myapplication_arthur.R;
import com.example.myapplication_arthur.databinding.FragmentCadastrarClienteBinding;
import com.example.myapplication_arthur.util.ImageUtil;
import com.example.myapplication_arthur.util.MaskEditUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CadastrarClienteFragment extends Fragment {

    private FragmentCadastrarClienteBinding binding;

    private CadastrarClienteViewModel mViewModel;
    private ImageView imagemCadCli;
    private Button tirarFoto;
    private EditText nome;
    private EditText cpf;
    private EditText cnh;
    private EditText telefone;
    private EditText endereco;
    private Button cadastrar;

    private String imgString = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastrar_cliente);
    }


    public void onCreateCadastrar(@Nullable Bundle savedInstanceState, @Nullable View root) {
       imagemCadCli = root.findViewById(R.id.imageViewcadcliente);
       tirarFoto = root.findViewById(R.id.tirarftcliente);
       nome = root.findViewById(R.id.txtNome);
       cpf = root.findViewById(R.id.txtCPF);
       cnh = root.findViewById(R.id.txtCNH);
       telefone = root.findViewById(R.id.txtTelefone);
       endereco = root.findViewById(R.id.txtEndereço);
       cadastrar = root.findViewById(R.id.btnCadastrarCliente);

        //camera
        tirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
        //fim camera

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            try {
                Bundle extras = data.getExtras();
                Bitmap imagemv = (Bitmap) extras.get("data");
                imagemCadCli.setImageBitmap(imagemv);

                imgString = ImageUtil.convertBitMapToString(imagemv);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CadastrarClienteViewModel cadastrarClienteViewModel =
                new ViewModelProvider(this).get(CadastrarClienteViewModel.class);

        binding = FragmentCadastrarClienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.txtNome;
        cadastrarClienteViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        onCreateCadastrar(savedInstanceState, root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nome = view.findViewById(R.id.txtNome);

        cpf = view.findViewById(R.id.txtCPF);
        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));

        cnh = view.findViewById(R.id.txtCNH);
        cnh.addTextChangedListener(MaskEditUtil.mask(cnh, MaskEditUtil.FORMAT_CNH));

        telefone = view.findViewById(R.id.txtTelefone);
        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));

        endereco = view.findViewById(R.id.txtEndereço);

        cadastrar = view.findViewById(R.id.btnCadastrarCliente);
        cadastrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                HashMap<String, String> camposValues = new HashMap<>();

                camposValues.put("nome", nome.getText().toString());
                camposValues.put("cpf", cpf.getText().toString());
                camposValues.put("cnh", cnh.getText().toString());
                camposValues.put("telefone", telefone.getText().toString());
                camposValues.put("endereco", endereco.getText().toString());

                if(validaCampos(camposValues)){
                    try {
                        Cliente cliente = new Cliente();

                        cliente.setNome(camposValues.get("nome"));
                        cliente.setCpf(camposValues.get("cpf"));
                        cliente.setCnh(camposValues.get("cnh"));
                        cliente.setTelefone(camposValues.get("telefone"));
                        cliente.setEndereco(camposValues.get("endereco"));
                        cliente.setImagem(imgString);

                        ClienteDAO clienteDAO = new ClienteDAO(binding.getRoot().getContext());
                        long codigo = clienteDAO.adicionaCliente(cliente);
                        cliente.setCodigo(codigo);

                        Toast.makeText(binding.getRoot().getContext(), "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                        nome.setText("");
                        cpf.setText("");
                        cnh.setText("");
                        telefone.setText("");
                        endereco.setText("");
                        imagemCadCli.setImageBitmap(null);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(binding.getRoot().getContext(), "Campo invalido", Toast.LENGTH_SHORT).show();
                }


            }
        });
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CadastrarClienteViewModel.class);
        // TODO: Use the ViewModel


    }

    private void setContentView(int fragment_cadastrar_cliente) {
    }

}