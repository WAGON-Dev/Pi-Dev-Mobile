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

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.util.Vector;
import service.authuser;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {
    public static TextField username,email,password,confirmPassword,numtel,adresee;
    public static ComboBox box;
    authuser auth = new authuser();
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        username = new TextField("", "Username", 20, TextField.ANY);
        email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        password = new TextField("", "Password", 20, TextField.PASSWORD);
        confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        numtel = new TextField("", "Numéro de telephone", 20, TextField.ANY);
        adresee = new TextField("", "Adresse", 20, TextField.ANY);
        box = new ComboBox();
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        numtel.setSingleLineTextArea(false);
        adresee.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        ///////////test charger combobox///////
        Vector <String> vec =new Vector<String>();
        String client="client",guide="guide",hotel="hotel",agenceV="agence voyage",agenceLV="agence location voiture";
        vec.addElement(client);
        vec.addElement(guide);
        vec.addElement(hotel);
        //vec.addElement(agenceV);
        //vec.addElement(agenceLV);
        box.setModel(new DefaultListModel(vec));
        
        ////////////////////////////////////////
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                box,
                createLineSeparator(),
                new FloatingHint(numtel),
                createLineSeparator(),
                new FloatingHint(adresee),
                createLineSeparator(),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        //e -> new ActivateForm(res).show()
        next.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                auth.RegisterUser(res);
            }
        });
    }
    
}
