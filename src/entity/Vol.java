/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Ghassen
 */
public class Vol {
    
    private int numTicket;
    private Date date_depart;
    private Date date_arrivee;
    private Double prix_vol;
    private String nom_Vol;
    private int client_vol_fk;
    private String depart;
    private String arrivee;
    private String nom_Compagnie;

    public Vol(int numTicket, Date date_depart, Date date_arrivee, Double prix_vol, String nom_Vol, int client_vol_fk, String depart, String arrivee, String nom_Compagnie) {
        this.numTicket = numTicket;
        this.date_depart = date_depart;
        this.date_arrivee = date_arrivee;
        this.prix_vol = prix_vol;
        this.nom_Vol = nom_Vol;
        this.client_vol_fk = client_vol_fk;
        this.depart = depart;
        this.arrivee = arrivee;
        this.nom_Compagnie = nom_Compagnie;
    }

    public Vol() {}

    public int getNumTicket() {
        return numTicket;
    }

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(Date date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public Double getPrix_vol() {
        return prix_vol;
    }

    public void setPrix_vol(Double prix_vol) {
        this.prix_vol = prix_vol;
    }

    public String getNom_Vol() {
        return nom_Vol;
    }

    public void setNom_Vol(String nom_Vol) {
        this.nom_Vol = nom_Vol;
    }

    public int getClient_vol_fk() {
        return client_vol_fk;
    }

    public void setClient_vol_fk(int client_vol_fk) {
        this.client_vol_fk = client_vol_fk;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public String getNom_Compagnie() {
        return nom_Compagnie;
    }

    public void setNom_Compagnie(String nom_Compagnie) {
        this.nom_Compagnie = nom_Compagnie;
    }
    
}
