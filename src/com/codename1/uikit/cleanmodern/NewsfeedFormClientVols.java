/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
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

/**
 *
 * @author Ghassen
 */
public class NewsfeedFormClientVols extends BaseFormClient {

    public NewsfeedFormClientVols(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste des Vols");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        addTab(swipe, res.getImage("vol.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
                
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Liste des Vols", barGroup);
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
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/apijsonpi/web/app_dev.php/api/vols");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            List<Vol> vols = new ArrayList<>();

            @Override
            public void actionPerformed(NetworkEvent evt) {
                vols = getList(new String(con.getResponseData()));
                for (Iterator it = vols.iterator(); it.hasNext();) {
                    Vol r = (Vol) it.next();
                    if(r.getClient_vol_fk()>0 ) {
                    } else {
                        addButton(res.getImage(r.getNom_Compagnie()+".jpg"),r,res);
                    }
                }

            } 
        });
        NetworkManager.getInstance().addToQueue(con);
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
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
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
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
    
   private void addButton(Image img, Vol v,Resources res) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       TextArea ta = new TextArea(v.getNom_Vol());
       TextArea tnc = new TextArea("Compagnie: "+v.getNom_Compagnie());
       TextArea tp = new TextArea(v.getPrix_vol().toString());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       tnc.setUIID("NewsTopLine");
       tnc.setEditable(false);
       tp.setUIID("NewsTopLine");
       tp.setEditable(false);
       
       Button sb = new Button("Réserver");
       sb.setTextPosition(RIGHT);
       Label likes = new Label("de "+v.getDepart(), "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       Label comments = new Label("à "+v.getArrivee(), "NewsBottomLine");
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments),
                       sb
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage("de "+v.getDepart()+" à "+v.getArrivee()+"\nPrix:"+v.getPrix_vol()+"DT", FontImage.MATERIAL_INFO));
       sb.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/apijsonpi/web/app_dev.php/api/reserver/"+v.getNumTicket()+"/"+authuser.user.getId_user());
        NetworkManager.getInstance().addToQueue(con);
        new NewsfeedFormClientVols(res).show();
           }
       });
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
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
