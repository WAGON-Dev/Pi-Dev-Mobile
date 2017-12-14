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

import entity.Voiture;
import com.codename1.components.FloatingHint;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class InsertCar extends BaseForm {

    public InsertCar(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        //Voiture voiture=new Voiture(model, TOP, CENTER, status, CENTER, LEFT, BRB_OTHER, regNo, img_voiture, depart, arrivee);
        TextField regNo = new TextField("", "Registration Number", 20, TextField.ANY);
        TextField model = new TextField("", "Model", 20, TextField.ANY);
        TextField duration = new TextField("", "Duration", 20, TextField.NUMERIC);
        TextField type = new TextField("", "Type", 20, TextField.NUMERIC);
        TextField rate = new TextField("", "Rate", 20, TextField.NUMERIC);
        RadioButton status=new RadioButton("Available");
        TextField alv_vo_fk = new TextField("", "Votre numero id", 20, TextField.NUMERIC);
        TextField img_voiture = new TextField("", "Car Picture", 20, TextField.ANY);
        regNo.setSingleLineTextArea(true);
        model.setSingleLineTextArea(true);
        duration.setSingleLineTextArea(true);
        rate.setSingleLineTextArea(true);
        alv_vo_fk.setSingleLineTextArea(true);
        img_voiture.setSingleLineTextArea(true);
        type.setSingleLineTextArea(true);
        Button next = new Button("Add Car");
        Button profil = new Button("profil");
        profil.addActionListener(e -> previous.showBack());
        profil.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(regNo),
                createLineSeparator(),
                new FloatingHint(model),
                createLineSeparator(),
                new FloatingHint(duration),
                createLineSeparator(),
                new FloatingHint(rate),
                createLineSeparator(),
                new FloatingHint(type),
                createLineSeparator(),
                status,
                createLineSeparator(),
                new FloatingHint(alv_vo_fk),
                createLineSeparator(),
                new FloatingHint(img_voiture),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, profil)
        ));
        next.requestFocus();
        //next.addActionListener(e -> new ActivateForm(res).show());
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(status.isSelected()){
                    status.setText("1");
                }
                else status.setText("0");
                ConnectionRequest req=new ConnectionRequest();
                req.setUrl("http://localhost:8081/Api_Mobile/web/app_dev.php/api/voitures/new?model="+model.getText()+"&duration="+duration.getText()+"&rate="+rate.getText()+"&type="+type.getText()+"&status="+status.getText()+"");
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                       Dialog.show("Confirmation", "ajout ok", "Ok", null);
                 
                      
                    }
                });
             NetworkManager.getInstance().addToQueue(req);

            }
        });
    }
    
}
