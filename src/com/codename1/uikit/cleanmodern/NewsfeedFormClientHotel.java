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
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.cleanmodern.ListeChambre.getImageFromTheme;
import entity.Chambre;
import entity.Users;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import service.authuser;

/**
 *
 * @author Ahmed
 */
public class NewsfeedFormClientHotel extends BaseFormClient {

    Container AfficheHotel;

    public NewsfeedFormClientHotel(Resources res) {
        super("Newsfeed", BoxLayout.y());

        AfficheHotel = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ////////////////////////////////////////////////////
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("HOTELS");
        getContentPane().setScrollVisible(false);
 
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("hotel2.jpg"), spacer1, "15 Likes  ", "85 Comments", "vos chambres ");
        //addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
        //Image img = getImageFromTheme("index.jpg").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / ));
        //Button image = new Button(img);
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
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl("http://localhost:8081/Mobile1/web/app_dev.php/api/chambre/all");
        con.setUrl("http://localhost:8081/Mobile1/web/app_dev.php/api/hotel/all"); //Pour la liste des étudiants 
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Image resizedImage = getImageFromTheme("hotel1.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 3));
                    
                    //Image img1 = res.getImage("index.jpg");

                    //System.out.println(getListEtudiant(new String(con.getResponseData())));
                    for (Users t : getListHotel(new String(con.getResponseData()))) {
                        /*Container c2 = new Container(BoxLayout.y());
                          c2.setScrollableY(true);*/
                        //MultiButton mb = new MultiButton();
                        // MultiButton mb1 = new MultiButton();
                        System.out.println(t.getRoles());
                      
                        if (t.getRoles().equals("[ROLE_HOTEL]")) {
                             Button btn = new Button("Acceder aux chambres");
                            Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            Container cnt2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            cnt.add(resizedImage);
                            cnt.add(t.getNom());
                            cnt.add(t.getAdresse());
                            cnt2.add(btn);
                            btn.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent evt) {
                                     new ListeChambreClient(res,t.getId_user()).show();
                                 }
                             });
                            //cnt.add(String.valueOf(t.getEtoile()));
                            //cnt.add(Integer.toString(t.getEtoile()));
                            AfficheHotel.add(cnt);
                            AfficheHotel.add(cnt2);
                        } else {
                            System.out.println("aa");
                        }

                        //  cnt1.add(btn);
                    }
                } catch (ParseException ex) {

                }

            }
        });
        NetworkManager.getInstance().addToQueue(con);
        //addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);

        add(AfficheHotel);

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

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

    public ArrayList<Users> getListHotel(String json) throws ParseException {
        ArrayList<Users> ListHotel = new ArrayList<>();
        System.out.println("JSON*************\n" + json);
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> hotels = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) hotels.get("root");

            for (Map<String, Object> obj : list) {
                Users e = new Users(); //id, json, status);
               // if (obj.get("etoile")  != "") {
                    //System.out.println(obj.get("etoile"));
                    //e.setEtoile((int) Integer.parseInt(obj.get("etoile").toString()));
                    e.setId_user((int) Float.parseFloat(obj.get("idUser").toString()));
                    e.setRoles(obj.get("roles").toString());
                    //System.out.println(e.getRoles());
                    e.setAdresse(((String) obj.get("adresse")));
                    e.setNom(((String) obj.get("nom")));
                //}

                ListHotel.add(e);

            }

        } catch (IOException ex) {
        }
        //System.out.println(ListHotel);
        return ListHotel;

    }

    public Container getF() {
        return AfficheHotel;
    }

    public void setF(Form f) {
        this.AfficheHotel = f;
    }

    public static Image getImageFromTheme(String name) {
        try {
            Resources resFile = Resources.openLayered("/theme");
            Image image = resFile.getImage(name);
            return image;
        } catch (IOException ioe) {
            //Log.p("Image " + name + " not found: " + ioe);
        }
        return null;
    }
}
