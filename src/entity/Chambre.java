/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;


/**
 *
 * @author Ahmed
 */
public class Chambre {
    int id;
    String Type;
    double prix;
    Date datedebut ;
    Date datefin ;
    int Client_fk;
    int Hotel_fk;

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Chambre(){
    }
    
    public Chambre(int id, String Type, double prix, int Client_fk, int Hotel_fk) {
        this.id = id;
        this.Type = Type;
        this.prix = prix;
        this.Client_fk = Client_fk;
        this.Hotel_fk = Hotel_fk;
    }
    
    public Chambre(String Type, double prix, int Client_fk, int Hotel_fk) {
        this.Type = Type;
        this.prix = prix;
        this.Client_fk = Client_fk;
        this.Hotel_fk = Hotel_fk;
    }

    public int getIdChambre() {
        return id;
    }

    public String getType() {
        return Type;
    }

    public double getPrix() {
        return prix;
    }

    public int getClient_fk() {
        return Client_fk;
    }

    public int getHotel_fk() {
        return Hotel_fk;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setClient_fk(int Client_fk) {
        this.Client_fk = Client_fk;
    }

    public void setHotel_fk(int Hotel_fk) {
        this.Hotel_fk = Hotel_fk;
    }

    @Override
    public String toString() {
        return "Chambre{" + "id=" + id + ", Type=" + Type + ", prix=" + prix + ", Client_fk=" + Client_fk + ", Hotel_fk=" + Hotel_fk + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

 
    
}
