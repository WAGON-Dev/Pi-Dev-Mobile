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
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import java.util.List;
import com.codename1.uikit.cleanmodern.SignInForm;
import entity.Users;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.AfficheForALV;
import com.codename1.uikit.cleanmodern.AfficheForClient;
import com.codename1.uikit.cleanmodern.Guide_UI;
import com.codename1.uikit.cleanmodern.ListeChambre;
import com.codename1.uikit.cleanmodern.NewsfeedFormClient;
import com.codename1.uikit.cleanmodern.SignUpForm;
import org.mindrot.BCrypt;
import service.MD5;

/**
 *
 * @author Asus
 */
public class authuser {

    public static Users user = new Users();
    int temp;
    
    public void login(Resources res) {
        // TextField userlogin = (TextField) SignInForm.builder.findByName("Username", SignInForm.ctn);
        //TextField passlogin = (TextField) SignInForm.builder.findByName("Password", SignInForm.ctn);
        String userlog = SignInForm.username.getText();
        String passlog = SignInForm.password.getText();

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
                        user.setId_user((int) Float.parseFloat(data.get("idUser").toString()));
                        user.setEmail(((String) data.get("email")));
                        user.setNom(((String) data.get("nom")));
                        user.setPassword(((String) data.get("password")));
                        user.setPrenom(((String) data.get("prenom")));
                        user.setNumTel((int) Float.parseFloat(data.get("numtel").toString()));
                        user.setUsername(((String) data.get("username")));
                        user.setAdresse(((String) data.get("adresse")));
                        user.setCin(((String) data.get("cin")));
                        /*Map<String, Object> data2 = (Map<String, Object>) (data.get("datenaissence"));
                        temp = (int) Float.parseFloat(data2.get("timestamp").toString());
                        Date myDate = new Date(temp * 1000L);
                        //user.setDateNaissence(myDate);*/
                        List<String> content = new ArrayList<>();
                        content.addAll((Collection<? extends String>) (data.get("roles")));
                        user.setRoles(content.get(0));
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                System.out.println(user);
                if (passlog.equals("")) {
                    Dialog.show("error", "Please put your password ! ", "cancel", "ok");
                } 
                if (!(BCrypt.checkpw(passlog, user.getPassword())))
                         {
                    System.out.println(user.getPassword());
                    System.out.println(passlog);
                    Dialog.show("error", "Wrong password please retry! ", "cancel", "ok");
                } 
                else {
                    if (user.getRoles().equals("ROLE_CLIENT")) {
                        new NewsfeedFormClient(res).show();
                    } else if (user.getRoles().equals("ROLE_GUIDE")){
                        new Guide_UI(res).show();
                    }else if (user.getRoles().equals("ROLE_AGENCE_VOITURE")) {
                        new AfficheForALV(res,user).show();
                        System.out.println("corect");
                    }else if (user.getRoles().equals("ROLE_HOTEL")) {
                        new ListeChambre(res).show();
                       //new AfficheForClient(res,user).show();
                        System.out.println("correct");
                    }else{
                        Dialog.show("error", "Votre Espace n'est pas encore pret ", "cancel", "ok");
                    }
                }
            }
        };
        System.out.println(userlog);
//        connectionRequest.setUrl("http://localhost:8081/apijsonpi/web/app_dev.php/api/finduser/" + userlog);
        connectionRequest.setUrl("http://localhost/apijsonpi/web/app_dev.php/api/finduser/" + userlog);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void RegisterUser(Resources res) {
        String rol = "";
        String userlog = SignUpForm.username.getText();
        String pass = SignUpForm.password.getText();
        
        String passhs = BCrypt.hashpw(SignUpForm.password.getText(), BCrypt.gensalt()) ;
        String conpasshs = BCrypt.hashpw(SignUpForm.confirmPassword.getText(), BCrypt.gensalt()) ;
        
        String email = SignUpForm.email.getText();
        String conpass = SignUpForm.confirmPassword.getText();
        int numtel = Integer.parseInt(SignUpForm.numtel.getText());
        String adresse = SignUpForm.adresee.getText();
        int role = SignUpForm.box.getSelectedIndex();
        /*affichage données test
        System.out.println(userlog);
        System.out.println(pass);
        System.out.println(conpass);
        System.out.println(numtel);
        System.out.println(adresse);
        System.out.println(role);
        System.out.println(email);
        System.out.println(role);
        System.out.println(role);*/
        if (role == 0) {
            rol = "ROLE_CLIENT";
        } else if (role == 1) {
            rol = "ROLE_GUIDE";
        } else if (role == 2) {
            rol = "ROLE_HOTEL";
        } else if (role == 3) {
            rol = "ROLE_PASSAGER";
        } else if (role==4){
            rol = "ROLE_AGENCE_VOITURE";
        }
        if (!pass.equals(conpass)) {
            Dialog.show("error", "please confirm your password ", "cancel", "ok");
        } else {
            user.setUsername(userlog);
            user.setPassword(pass);
            user.setEmail(email);
            user.setNumTel(numtel);
            user.setAdresse(adresse);
            user.setRoles(rol);
        }
        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                System.out.println(input);

            }

            @Override
            protected void postResponse() {
                if (user.getRoles().equals("ROLE_CLIENT")) {
                        new NewsfeedFormClient(res).show();
                    /*Message m = new Message("Welcome in GoVoyage Application");
                        m.getAttachments().put("test", "text/plain");
                        //m.getAttachments().put(imageAttachmentUri, "image/png");
                        Display.getInstance().sendMessage(new String[]{user.getEmail()}, "Subject of message", m);*/
                    System.out.println("corect");
                } 
                else {
                    Dialog.show("error", "Votre Espace n'est pas encore pret ", "cancel", "ok");
                }
            }
        };

        connectionRequest.setUrl("http://localhost/apijsonpi/web/app_dev.php/api/newuser?username=" + userlog + "&email=" + email + "&password=" + passhs + "&role=" + rol + "&numtel=" + numtel + "&adresse=" + adresse);

//        connectionRequest.setUrl("http://localhost:8081/apijsonpi/web/app_dev.php/api/newuser?username=" + userlog + "&email=" + email + "&password=" + MD5.hash(pass) + "&role=" + rol + "&numtel=" + numtel + "&adresse=" + adresse);
        //connectionRequest.setUrl("http://localhost/apijsonpi/web/app_dev.php/api/newuser?username=" + userlog + "&email=" + email + "&password=" + MD5.hash(pass) + "&role=" + rol + "&numtel=" + numtel + "&adresse=" + adresse);

        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
}
