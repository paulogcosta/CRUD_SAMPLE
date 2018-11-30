package com.crudsample.slunngdev.crud_sample;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

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
        registerForContextMenu(listView);
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

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRegister(newText);
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.context_menu,menu);
    }

    public void searchRegister(String nome){
        registrosFiltrados.clear();
        for(userRegister reg : registros){
            if(reg.getNome().toLowerCase().contains(nome.toLowerCase())){
                registrosFiltrados.add(reg);
            }
        }
        listView.invalidateViews();
    }

    public void updateRegister(MenuItem item){}

    public void deleteRegister(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final userRegister regToDelete = registrosFiltrados.get(menuInfo.position);
        AlertDialog confirm = new AlertDialog.Builder(this).setTitle("Excluir").setMessage("Deseja excluir o registro?")
                .setNegativeButton("Não",null).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        registrosFiltrados.remove(regToDelete);
                        registros.remove(regToDelete);
                        dao.delete(regToDelete);
                        listView.invalidateViews();
                    }
                }).create();
        confirm.show();
    }


    public void newRegister(MenuItem item){
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
    }

    public void syncServiceStart(MenuItem item){
        Intent intent = new Intent(this, SyncService.class);
        startService(intent);
    }
}
