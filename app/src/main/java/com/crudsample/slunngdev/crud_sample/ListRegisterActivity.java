package com.crudsample.slunngdev.crud_sample;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListRegisterActivity extends AppCompatActivity {

    private ListView listView;
    private userRegisterDAO dao;
    private List<userRegister> registros;
    private List<userRegister> registrosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_register);

        listView = findViewById(R.id.listRegister);
        dao = new userRegisterDAO(this);
        registros = dao.getAllReg();
        registrosFiltrados.addAll(registros);
        ArrayAdapter<userRegister> adapter = new ArrayAdapter<userRegister>(this,android.R.layout.simple_list_item_1,registrosFiltrados);
        listView.setAdapter(adapter);
        //listView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onResume() {
        super.onResume();
        registros = dao.getAllReg();
        registrosFiltrados.clear();
        registrosFiltrados.addAll(registros);
        listView.invalidateViews();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.main_menu,menu);
        return true;
    }

    public void updateRegister(MenuItem item){}

    public void deleteRegister(MenuItem item){}


    public void newRegister(MenuItem item){
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
    }
}
