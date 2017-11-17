package com.example.mario.projetopratico.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Techined on 16/11/2017.
 */

public class FocoModel {

    // Atributos ponto.
    private int primary_key;
    private LatLng latlng;
    private int user_id;
    private double Latitude;
    private double Longitude;


    private String description;
    private int tipo_de_foco;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
    private boolean active;


    // Construtor pra quando tiver cadastro.
    public FocoModel(int primary_key, LatLng latlng, int user_id, int tipo_de_foco, Date created_at, boolean active, String description) {
        this.primary_key = primary_key;
        this.latlng = latlng;
        this.user_id = user_id;
        this.tipo_de_foco = tipo_de_foco;
        this.created_at = created_at;
        this.active = active;
        this.description = description;
    }
    // testes
    public FocoModel(LatLng latlng, String description, int tipo_de_foco, boolean active) {
        this.latlng = latlng;
        this.description = description;
        this.tipo_de_foco = tipo_de_foco;
        this.active = active;
    }

    // Utilizado para testes
    public FocoModel(LatLng latlng) {
        this.latlng = latlng;
    }



    // Métodos
    public int getLiberado_por() {
        return liberado_por;
    }

    public void setLiberado_por(int liberado_por) {
        this.liberado_por = liberado_por;
    }

    private int liberado_por;


    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTipo_de_foco() {
        return tipo_de_foco;
    }

    public void setTipo_de_foco(int tipo_de_foco) {
        this.tipo_de_foco = tipo_de_foco;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public int getPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(int primary_key) {
        this.primary_key = primary_key;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
