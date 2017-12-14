/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import entity.Users;
import entity.Voiture;
import com.codename1.components.FloatingHint;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.rest.api.v2010.account.MessageCreator; 
import com.twilio.type.PhoneNumber; 
/**
 * Signup UI
 *
 * @author Shai Almog
 */
//https://www.twilio.com/try-twilioemail:nouha.asfour@esprit.tn pwd:64739502nouhaasfour
public class RentCarClient extends BaseForm {
 private final static String ACCOUNT_SID = "AC89947f605551f38a90aa1bac3afe91a5"; 
    private final static String AUTH_TOKEN = "c035f8337d46fa2cfa2b950896872964"; 
 
    public RentCarClient(Resources res,Voiture voiture,Users user) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        Picker depart=new Picker();
        depart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
       Picker arrive=new Picker();
        arrive.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Button next = new Button("Confirm Rent");
        Button signIn = new Button("Back to List");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Another Rent ?");
        
        Container content = BoxLayout.encloseY(
                new Label("Rent A Car", "LogoLabel"),
                new Label("Rent Date","Label"),
                depart,
                createLineSeparator(),
                 new Label("Restoring Date","Label"),
               arrive,
                createLineSeparator()
                
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 
                ConnectionRequest req = new ConnectionRequest();
                  //Date d2 = null;
                  //Date d3 = null;
       
          //  d2 = new Date(depart.getDate().getTime());
           // d3 = new Date(arrive.getDate().getTime());
        
                req.setUrl("http://localhost:8081/Api_Mobile/web/app_dev.php/api/voitures/"+voiture.getId_voiture()+"?depart="+depart.getDate()
                        +"&arrivee="+arrive.getDate()+ "");
                      
                req.addResponseListener(new ActionListener<NetworkEvent>() {
                            
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                       // if (s.equals("success")) {
                           Dialog.show("Confirmation", "Car is Rented", "Ok", null);
                           Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
           Message message = Message.creator(new PhoneNumber("+21650696097"),
        new PhoneNumber("+14245706846"),"please be send to nouha asfour").create();

    System.out.println(message.getSid());
                            AfficheForClient cl=new AfficheForClient(res, user);
                            cl.show();
                        //}
                    }
                });
                NetworkManager.getInstance().addToQueue(req);

            }
        });
    }
    
}
