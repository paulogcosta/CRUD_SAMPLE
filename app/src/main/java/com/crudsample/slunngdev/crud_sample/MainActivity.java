package com.crudsample.slunngdev.crud_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText numero;
    private Switch status;
    private userRegisterDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome  = findViewById(R.id.editNome);
        numero = findViewById(R.id.editNumero);
        status = (Switch) findViewById(R.id.statusSwitch);
        dao = new userRegisterDAO(this);

        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    status.setText("Ativo");
                }
                else {
                    status.setText("Inativo");
                }
            }
        });

    }

    public void salvar(View view){
        userRegister registro = new userRegister();
        registro.setNome(nome.getText().toString());
        registro.setNumero(numero.getText().toString());
        registro.setStatus(status.isChecked());
        long id  = dao.insert(registro);
        Toast.makeText(this, "Registro inserido ID: "+id, Toast.LENGTH_SHORT).show();
    }
}
