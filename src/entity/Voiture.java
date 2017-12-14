/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author nouha
 */
public class Voiture {
    private String model;
    private int duration;
    private int rate;
    private int type;
    private String status;
    private int client_vo_fk;
    private int alv_vo_fk;
    private int id_voiture;
    private String regNo;
    private String img_voiture;
    private Date depart;
    private Date arrivee;

    public Voiture() {
    }

    public Voiture(String model, int duration, int rate, String status, int client_vo_fk, int alv_vo_fk, int id_voiture, String regNo, String img_voiture, Date depart, Date arrivee) {
        this.model = model;
        this.duration = duration;
        this.rate = rate;
        this.status = status;
        this.client_vo_fk = client_vo_fk;
        this.alv_vo_fk = alv_vo_fk;
        this.id_voiture = id_voiture;
        this.regNo = regNo;
        this.img_voiture = img_voiture;
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClient_vo_fk() {
        return client_vo_fk;
    }

    public void setClient_vo_fk(int client_vo_fk) {
        this.client_vo_fk = client_vo_fk;
    }

    public int getAlv_vo_fk() {
        return alv_vo_fk;
    }

    public void setAlv_vo_fk(int alv_vo_fk) {
        this.alv_vo_fk = alv_vo_fk;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getImg_voiture() {
        return img_voiture;
    }

    public void setImg_voiture(String img_voiture) {
        this.img_voiture = img_voiture;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public Date getArrivee() {
        return arrivee;
    }

    public void setArrivee(Date arrivee) {
        this.arrivee = arrivee;
    }

    @Override
    public String toString() {
        return "Voiture{" + "model=" + model + ", duration=" + duration + ", rate=" + rate + ", status=" + status + ", client_vo_fk=" + client_vo_fk + ", alv_vo_fk=" + alv_vo_fk + ", id_voiture=" + id_voiture + ", regNo=" + regNo + ", img_voiture=" + img_voiture + ", depart=" + depart + ", arrivee=" + arrivee + '}';
    }
    
    
}
