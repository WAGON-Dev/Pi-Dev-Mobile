/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.uikit.cleanmodern.NewsfeedFormClient;
import com.codename1.uikit.cleanmodern.ProfilFormClient;
import entity.Users;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static service.authuser.user;

/**
 *
 * @author Asus
 */
public class ProfilClient {
    int temp;
    public void Modifier(){
        
        String userlog = ProfilFormClient.username.getText();
        String pass = ProfilFormClient.password.getText();
        String nom = ProfilFormClient.nom.getText();
        String prenom = ProfilFormClient.prenom.getText();
        String mail = ProfilFormClient.email.getText();
        
        System.out.println(userlog);
        System.out.println(pass);
        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(mail);
        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    if (data.isEmpty()) {
                        Dialog.show("error", "Email not found ! please retry ", "Cancel", "ok");
                    } else {
                        authuser.user.setId_user((int) Float.parseFloat(data.get("idUser").toString()));
                        authuser.user.setEmail(((String) data.get("email")));
                        authuser.user.setNom(((String) data.get("nom")));
                        authuser.user.setPassword(((String) data.get("password")));
                        authuser.user.setPrenom(((String) data.get("prenom")));
                        authuser.user.setNumTel((int) Float.parseFloat(data.get("numtel").toString()));
                        authuser.user.setUsername(((String) data.get("username")));                       
                        authuser.user.setAdresse(((String) data.get("adresse")));
                        authuser.user.setCin(((String) data.get("cin")));
                        Map<String, Object> data2 = (Map<String, Object>) (data.get("datenaissence"));
                        temp = (int) Float.parseFloat(data2.get("timestamp").toString());
                        Date myDate = new Date(temp * 1000L);
                        authuser.user.setDateNaissence(myDate);
                        List<String> content = new ArrayList<>();
                        content.addAll((Collection<? extends String>) (data.get("roles")));
                        authuser.user.setRoles(content.get(0));
                    }
                } catch (IOException err) {
                    Log.e(err);                       
                }
            }
            @Override
            protected void postResponse() {
                Dialog.show("Succés", "Vos données ont éte modifier avec succés ", "cancel", "ok");
            }
        };
        //System.out.println(userlog);
        if(authuser.user.getPassword()!=pass){
            pass=MD5.hash(authuser.user.getPassword());
        }
        connectionRequest.setUrl("http://localhost/apijsonpi/web/app_dev.php/api/edituser/"+authuser.user.getId_user()+"?username="+userlog+"&email="+mail+"&password="+pass+"&nom="+nom+"&prenom="+prenom);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
}
