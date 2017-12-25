/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;

import com.codename1.ui.Tabs;

import com.codename1.ui.Image;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;

import com.codename1.ui.plaf.Style;

import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entity.Chambre;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 *
 * @author Ahmed
 */
public class UpdateChambre_1 extends BaseForm {

    Container UpdateChambre;

    UpdateChambre_1(Resources res, Chambre c) {
        super("Newsfeed", BoxLayout.y());

        ////////////////////////////////////////////////////
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("updating your room");
        getContentPane().setScrollVisible(false);
/*Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");*/
       super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("roomgrey.jpg"), spacer1, "", "", "");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        
        UpdateChambre = new Container(BoxLayout.y());

        Container modifier = new Container(BoxLayout.y());
        Label et = new Label("Type ");
        ComboBox type = new ComboBox(c.getType());
        
        type.addItem("indiv");
        type.addItem("double");
        type.addItem("triple");

        Label nbr = new Label("Prix");
        TextField prix = new TextField("" + c.getPrix());

        prix.getStyle().setFgColor(0x000000);

        Button btnOk = new Button("modifier");
        modifier.add(et).add(type).add(nbr).add(prix);
        UpdateChambre.add(modifier).add(btnOk);
 add(UpdateChambre);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();

                req.setUrl("http://localhost:8081/Mobile1/web/app_dev.php/api/edit/" + c.getIdChambre() + "?type=" + type.getSelectedItem() + "&prix=" + prix.getText() + "");

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (!s.equals("Error")) {

                            Dialog.show("Confirmation", "update ok", "Ok", null);
                            ListeChambre lc = new ListeChambre(res);
                            lc.show();

                        } else {
                            Dialog.show("Confirmation", "update failed", "Ok", null);
                        }
                    }

                });

                NetworkManager.getInstance().addToQueue(req);
                //new ListeChambre(res).show();
            }
        });
               
    }

    public ArrayList<Chambre> getListChambre(String json) throws ParseException {
        ArrayList<Chambre> listChambres = new ArrayList<>();
        System.out.println("JSON*************\n" + json);
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> chambres = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) chambres.get("root");

            for (Map<String, Object> obj : list) {
                Chambre e = new Chambre(); //id, json, status);

                e.setId((int) Float.parseFloat(obj.get("idChambre").toString()));
                e.setType(obj.get("type").toString());
                e.setPrix((int) Float.parseFloat(obj.get("prix").toString()));

                listChambres.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listChambres);
        return listChambres;

    }

    public Container getF() {
        return UpdateChambre;
    }

    public void setF(Form f) {
        this.UpdateChambre = f;
    }

   

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);

        //FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

}
