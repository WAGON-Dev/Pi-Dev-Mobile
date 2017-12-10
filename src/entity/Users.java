/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Users {
int id_user;
String username;
String username_canonical;
String email;
String email_canonical;
int enabled;
String salt;
String password;
Date last_login;
String confirmation_tokenIndex;
Date password_requested_at;
String roles;
String nom;
int numTel;
String adresse;
String image;
int etoile;
int nb_chambre;
String prenom;
String cin;
Date dateNaissence;
int note;
int nbr_note;
int nbr_voiture;
int nbr_voyage_organise;
int nb_chambre_reserve;

    public Users() {
    }


    public Users(int id_user, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_tokenIndex, Date password_requested_at, String roles, String nom, int numTel, String adresse, String image, int etoile, int nb_chambre, String prenom, String cin, Date dateNaissence, int note, int nbr_note, int nbr_voiture, int nbr_voyage_organise, int nb_chambre_reserve) {
        this.id_user = id_user;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_tokenIndex = confirmation_tokenIndex;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.numTel = numTel;
        this.adresse = adresse;
        this.image = image;
        this.etoile = etoile;
        this.nb_chambre = nb_chambre;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaissence = dateNaissence;
        this.note = note;
        this.nbr_note = nbr_note;
        this.nbr_voiture = nbr_voiture;
        this.nbr_voyage_organise = nbr_voyage_organise;
        this.nb_chambre_reserve = nb_chambre_reserve;
    }

    public Users(String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_tokenIndex, Date password_requested_at, String roles, String nom, int numTel, String adresse, String image, int etoile, int nb_chambre, String prenom, String cin, Date dateNaissence, int note, int nbr_note, int nbr_voiture, int nbr_voyage_organise, int nb_chambre_reserve) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_tokenIndex = confirmation_tokenIndex;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.numTel = numTel;
        this.adresse = adresse;
        this.image = image;
        this.etoile = etoile;
        this.nb_chambre = nb_chambre;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaissence = dateNaissence;
        this.note = note;
        this.nbr_note = nbr_note;
        this.nbr_voiture = nbr_voiture;
        this.nbr_voyage_organise = nbr_voyage_organise;
        this.nb_chambre_reserve = nb_chambre_reserve;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_tokenIndex() {
        return confirmation_tokenIndex;
    }

    public void setConfirmation_tokenIndex(String confirmation_tokenIndex) {
        this.confirmation_tokenIndex = confirmation_tokenIndex;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public int getNb_chambre() {
        return nb_chambre;
    }

    public void setNb_chambre(int nb_chambre) {
        this.nb_chambre = nb_chambre;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateNaissence() {
        return dateNaissence;
    }

    public void setDateNaissence(Date dateNaissence) {
        this.dateNaissence = dateNaissence;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getNbr_note() {
        return nbr_note;
    }

    public void setNbr_note(int nbr_note) {
        this.nbr_note = nbr_note;
    }

    public int getNbr_voiture() {
        return nbr_voiture;
    }

    public void setNbr_voiture(int nbr_voiture) {
        this.nbr_voiture = nbr_voiture;
    }

    public int getNbr_voyage_organise() {
        return nbr_voyage_organise;
    }

    public void setNbr_voyage_organise(int nbr_voyage_organise) {
        this.nbr_voyage_organise = nbr_voyage_organise;
    }

    public int getNb_chambre_reserve() {
        return nb_chambre_reserve;
    }

    public void setNb_chambre_reserve(int nb_chambre_reserve) {
        this.nb_chambre_reserve = nb_chambre_reserve;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id_user;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{" + "id_user=" + id_user + ", username=" + username + ", username_canonical=" 
                + username_canonical + ", email=" + email + ", email_canonical=" 
                + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" 
                + last_login + ", confirmation_tokenIndex=" + confirmation_tokenIndex + ", password_requested_at=" 
                + password_requested_at + ", roles=" + roles + ", nom=" + nom + ", numTel=" + numTel + ", adresse=" 
                + adresse + ", image=" + image + ", etoile=" + etoile + ", nb_chambre=" + nb_chambre + ", prenom=" 
                + prenom + ", cin=" + cin + ", dateNaissence=" + dateNaissence + ", note=" + note + ", nbr_note=" 
                + nbr_note + ", nbr_voiture=" + nbr_voiture + ", nbr_voyage_organise=" 
                + nbr_voyage_organise + ", nb_chambre_reserve=" + nb_chambre_reserve + '}';
    }
}
