package com.crudsample.slunngdev.crud_sample;

import java.io.Serializable;

public class userRegister implements Serializable{
    private Integer id;
    private String nome;
    private String numero;
    private Boolean status;
    private String statusShow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
        if(status){
            statusShow = "Ativo";
        }
        else{
            statusShow = "Inativo";
        }
    }

    @Override
    public String toString(){
        return nome;
    }

}
