package com.example.myapplication_arthur;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplication_arthur.Adapter.AlugarAdapter;
import com.example.myapplication_arthur.Model.Carro;
import com.example.myapplication_arthur.ui.cadastrarVeiculo.CadastrarVeiculoFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_arthur.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private RecyclerView recycler;
    private AlugarAdapter adapter;
    private ArrayList<Carro> itens; //passar pro bb

    EditText editText;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            //camera
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 0);
            }


            setSupportActionBar(binding.appBarMain.toolbar);//appBarMain.toolbar

            drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, binding.appBarMain.toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();


            DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_cadastrar_veiculo,
                    R.id.nav_cadastrar_cliente,
                    R.id.nav_devolucao_veiculo,
                    R.id.nav_alugar_veiculo_inicial,
                    R.id.nav_consulta_cliente,
                    R.id.nav_consulta_veiculo)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch(item.getItemId()){
                        case R.id.alugar_veiculo:
                            navController.navigate(R.id.nav_alugar_veiculo_inicial);
                            break;
                        case R.id.cadastrar_veiculo:
                            navController.navigate(R.id.nav_cadastrar_veiculo);
                            break;
                        case R.id.cadastrar_cliente:
                            navController.navigate(R.id.nav_cadastrar_cliente);
                            break;
                        case R.id.devolver_veiculo:
                            navController.navigate(R.id.nav_devolucao_veiculo);
                            break;
                        case R.id.ver_clientes:
                            navegar(navController, R.id.nav_consulta_cliente);
                            break;
                            /*
                        case R.id.nav_alugar_veiculo_final:
                            navegar(navController, R.id.nav_alugar_veiculo_final);
                            break;
                             */
                        case R.id.ver_veiculo:
                            navegar(navController, R.id.nav_consulta_veiculo);
                            break;

                        default:
                            return true;
                    }
                    return true;
                }
            });
        }

        private void navegar(NavController navController, int id){
            navController.popBackStack();
            navController.navigate(id);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if(requestCode == 1 && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imagemv = (Bitmap) extras.get("data");
//             ImageView imagem = root.findViewById(R.id.imagCarro);
//            imagem.setImageBitmap(imagemv);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private void replaceFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

    }
}
