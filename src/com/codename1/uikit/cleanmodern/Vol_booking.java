/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.components.WebBrowser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entity.Vol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import service.authuser;
import java.io.*;
import java.lang.*;

/**
 *
 * @author Ghassen
 */
public class Vol_booking extends BaseFormClient {

    public Vol_booking(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        addTab(swipe, res.getImage("booking.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");

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

        WebBrowser browser = new WebBrowser();
        browser.setHeight(450);
        browser.setWidth(150);
        Container cont = new Container(flow);
        cont.setLayout(new BorderLayout());
        cont.addComponent(BorderLayout.CENTER, browser);
        cont.setHeight(450);
        cont.setWidth(getWidth());
        Button btn = new Button("Book on our Site");
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        add(LayeredLayout.encloseIn(btn));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browser.setURL("http://localhost/govoyagepi/web/app_dev.php/vol/");
            }
        });

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Liste des Compagnie", barGroup);
        all.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        addCompany(res, browser);
        add(cont);
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

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
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
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
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addCompany(Resources res, WebBrowser browser) {
        int height = Display.getInstance().convertToPixels(4.5f);
        int width = Display.getInstance().convertToPixels(7.5f);
        Button imaget = new Button(res.getImage("TunisAir.jpg").fill(width, height));
        Button imagen = new Button(res.getImage("NouvelAir.jpg").fill(width, height));
        Button imagea = new Button(res.getImage("AirFrance.jpg").fill(width, height));
        Button imagel = new Button(res.getImage("lufthansa.jpg").fill(width, height));
        Button imagee = new Button(res.getImage("emirates.jpg").fill(width, height));
        imaget.setUIID("Label");
        imagee.setUIID("Label");
        imagea.setUIID("Label");
        imagen.setUIID("Label");
        imagel.setUIID("Label");
        Container cnt = new Container();
        cnt.add(BoxLayout.encloseX(
                imagea,
                imagee,
                imagel,
                imagen,
                imaget));
        add(cnt);
        imaget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browser.setURL("http://www.tunisair.com/site/publish/module/horairevol.asp");
            }
        });
        imagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browser.setURL("https://www.nouvelair.com/fr/reserver/billet.html");
            }
        });
        imagee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browser.setURL("https://fly4.emirates.com/CAB/IBE/SearchAvailability.aspx");
            }
        });
        imagea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browser.setURL("https://www.airfrance.tn/TN/fr/local/process/standardbooking/SearchAction.do?=&WT.srch=1&WT.mc_id=C_TN_paidsearch_Google_Brandname_null_null_null&esv_medium=SEA_Brand_e&esv_source=google&esv_campaign=Structurel_&ESV_market=TN&ESV_device=c&gclid=EAIaIQobChMI1oS17YSL2AIVYSrTCh1wYwA2EAAYASABEgKIlPD_BwE&gclsrc=aw.ds");
            }
        });
        imagel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browser.setURL("https://www.lufthansa.com/us/en/Arrival-and-Departure");
            }
        });

    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

    public ArrayList<Vol> getList(String json) {
        ArrayList<Vol> listEtudiants = new ArrayList<>();
        try {
            int i = 0;
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            Form f = new Form();
            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                i++;
                Vol e = new Vol();//id, json, status);
                e.setClient_vol_fk((int) Float.parseFloat(obj.get("clientVolFk").toString()));
                e.setNumTicket((int) Float.parseFloat(obj.get("numticket").toString()));
                e.setPrix_vol((double) Float.parseFloat(obj.get("prixVol").toString()));
                e.setNom_Vol(obj.get("nomVol").toString());
                e.setNom_Compagnie(obj.get("nomCompagnie").toString());
                e.setArrivee(obj.get("arrivee").toString());
                e.setDepart(obj.get("depart").toString());
                System.out.println(e.toString());
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }
}
