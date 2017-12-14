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
public class Voyagepersonalise {
    private int id_vp;
    private String nom;
    private String ville_depart;
    private String Ville_arrive;
    private Date date_depart;
    private Date date_arrive;
    private int nbr_participant;
    private int client_vp_fk;
    private int guide_fk;

    public Voyagepersonalise() {
    }

    public Voyagepersonalise(int id_vp, String nom, String ville_depart, String Ville_arrive, Date date_depart, Date date_arrive, int nbr_participant, int client_vp_fk) {
        this.id_vp = id_vp;
        this.nom = nom;
        this.ville_depart = ville_depart;
        this.Ville_arrive = Ville_arrive;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
        this.nbr_participant = nbr_participant;
        this.client_vp_fk = client_vp_fk;
    }

    public int getId_vp() {
        return id_vp;
    }

    public void setId_vp(int id_vp) {
        this.id_vp = id_vp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille_depart() {
        return ville_depart;
    }

    public void setVille_depart(String ville_depart) {
        this.ville_depart = ville_depart;
    }

    public String getVille_arrive() {
        return Ville_arrive;
    }

    public void setVille_arrive(String Ville_arrive) {
        this.Ville_arrive = Ville_arrive;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_arrive() {
        return date_arrive;
    }

    public void setDate_arrive(Date date_arrive) {
        this.date_arrive = date_arrive;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    public int getClient_vp_fk() {
        return client_vp_fk;
    }

    public void setClient_vp_fk(int client_vp_fk) {
        this.client_vp_fk = client_vp_fk;
    }

    public int getGuide_fk() {
        return guide_fk;
    }

    public void setGuide_fk(int guide_fk) {
        this.guide_fk = guide_fk;
    }
    
    
}
