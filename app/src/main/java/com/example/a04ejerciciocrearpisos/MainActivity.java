package com.example.a04ejerciciocrearpisos;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04ejerciciocrearpisos.modelos.Piso;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.a04ejerciciocrearpisos.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //1º-Definir el binding
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherCrearPiso;

    // Elemeentos necesarios para mostrar datos en un contenedor:
    // Contenedor de los datos (binding.content.contenidoMain)
    // Los datos
    private ArrayList<Piso> listaPisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2º-Instanciar el binding al layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaPisos = new ArrayList<>();
        inicializaLaunchers();
        pintarPisos();
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void pintarPisos() {
        binding.content.contenedorMain.removeAllViews();
    }

    private void inicializaLaunchers() {
        launcherCrearPiso = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null){
                            Piso piso = (Piso) result.getData().getExtras().getSerializable("PISO");
                            listaPisos.add(piso);
                            pintarPisos();
                        }
                    }
                }
        );
    }
}