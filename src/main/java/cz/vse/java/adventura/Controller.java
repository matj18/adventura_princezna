package cz.vse.java.adventura;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Controller {

    private static final int SIRKA_IKONY = 45;
    private static final int VYSKA_IKONY = 30;


    @FXML
    private VBox seznamVychodu;
    @FXML
    private VBox seznamPredmetuVMistnosti;
    @FXML
    private VBox seznamPredmetuVBatohu;
    @FXML
    private VBox postava;
    @FXML
    private Button tlacitkoPromluv;
    @FXML
    private AnchorPane vyberSTlacitkem;

    private IHra hra;

    public ImageView obrazekLokace;
    @FXML
    private Label popisLokace;
    @FXML
    private Label jmenoLokace;
    @FXML
    private ChoiceBox<String> vyber;
    @FXML
    private Button tlacitkoHadej;


    @FXML
    private Menu menuNapoveda;
    @FXML
    private Menu menuNovaHra;


    private boolean promluveno = false;

    public void setHra(IHra hra) {
        this.hra = hra;
        HerniPlan herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        vyberSTlacitkem.setVisible(false);
        pripravMenu();
        zmenProstor(aktualniProstor);
    }

    private void pripravMenu() {
        MenuItem menuSpustit = new MenuItem("Spustit");
        menuNovaHra.getItems().add(menuSpustit);
        menuSpustit.setOnAction(event -> {
            //System.out.println("Menu spustit Selected");
            this.hra = new Hra();
            setHra(hra);
        });
        MenuItem menuDoc = new MenuItem("dokumentace");
        menuNapoveda.getItems().add(menuDoc);
        menuDoc.setOnAction(event -> {
            System.out.println("Menu doc Selected");
            Alert a = new Alert(Alert.AlertType.NONE);
            a.getDialogPane().getButtonTypes().add(ButtonType.OK);
            a.setTitle("Nápověda");
            a.setContentText("hgbj");
            a.show();
        });
    }

    private void zmenProstor(Prostor prostor) {
        if (!hra.konecHry()) {
            hra.zpracujPrikaz("jdi " + prostor.getNazev());
            //System.out.println(hra.getHerniPlan().getAktualniProstor().getNazev());

            jmenoLokace.setText(prostor.getNazev());
            popisLokace.setText(prostor.getPopis());
            if (prostor.getNazev().equals("komnata")) {
                popisLokace.setText("komnata, kde se nachází čaroděj");
            }

            String nazevObrazku = "/" + prostor.getNazev() + ".jpg";
            //String nazevObrazku = "/" + "domecek" + ".jpg";
            Image image = new Image(getClass().getResourceAsStream(nazevObrazku));
            obrazekLokace.setImage(image);

            if (!(promluveno && prostor.getNazev().equals("komnata"))) {
                vyberSTlacitkem.setVisible(false);
            } else {
                vyberSTlacitkem.setVisible(true);
            }

            pridejVychody(prostor);
            pridejPredmety(prostor);
            pridejPostavu(prostor);
        }
    }
    private void pridejVychody(Prostor prostor) {
        //seznamVychodu.setSpacing(5);
        seznamVychodu.getChildren().clear();
        for (Prostor p : prostor.getVychody()) {
            HBox vychod = new HBox();
            vychod.setId("polozka");
            vychod.setPadding(new Insets(4, 4, 4, 5));
            vychod.setSpacing(10);
            Label nazevProstoru = new Label(p.getNazev());

            ImageView vychodImageView = new ImageView();
            Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + p.getNazev() + ".jpg"));
            //Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + "les" + ".jpg"));
            vychodImageView.setFitHeight(VYSKA_IKONY);
            vychodImageView.setFitWidth(SIRKA_IKONY);
            vychodImageView.setImage(vychodImage);

            vychod.getChildren().addAll(vychodImageView, nazevProstoru);

            if (p.jeZamceno()) {
                ImageView zamcenoImageView = new ImageView();
                Image zamcenoImage = new Image(getClass().getClassLoader().getResourceAsStream("\\zamceno.jpg"));
                zamcenoImageView.setFitHeight(25);
                zamcenoImageView.setFitWidth(25);
                zamcenoImageView.setImage(zamcenoImage);
                vychod.getChildren().add(zamcenoImageView);

            }

            seznamVychodu.getChildren().add(vychod);
            vychod.setOnMouseClicked(event -> {
                if (!p.jeZamceno()) {
                    zmenProstor(p);
                }
                if (p.getNazev().equals("dvířka") && hra.zpracujPrikaz("brašna").contains("klíč")) {
                    p.setZamceno(false);
                    zmenProstor(p);
                }
            });
        }
    }

    private void pridejPredmety(Prostor prostor) {
        seznamPredmetuVMistnosti.getChildren().clear();
        //seznamPredmetuVMistnosti.setSpacing(5);

        for (Vec vec : prostor.getSeznamVeci()) {
            if (vec.jeViditelnost()) {
                pridejPredmetDoMistnosti(vec);
            }
        }
    }

    private void pridejPredmetDoMistnosti(Vec vec) {
        HBox predmet = vytvorHBoxPredmet(vec);
        predmet.setId("polozka");
        predmet.setPadding(new Insets(4, 4, 4, 5));

        seznamPredmetuVMistnosti.getChildren().add(predmet);

        predmet.setOnMouseClicked(event -> {
            if (vec.jePrenositelnost() && seznamPredmetuVBatohu.getChildren().size() < 5) {
                hra.zpracujPrikaz("seber " + vec.getNazev());
                HBox vecVBatohu = vytvorHBoxPredmet(vec);
                vecVBatohu.setId("polozka");
                vecVBatohu.setPadding(new Insets(4, 4, 4, 5));
                //seznamPredmetuVBatohu.setSpacing(5);
                seznamPredmetuVBatohu.getChildren().add(vecVBatohu);
                //pridejPredmety(hra.getHerniPlan().getAktualniProstor());
                seznamPredmetuVMistnosti.getChildren().remove(predmet);

                vecVBatohu.setOnMouseClicked(event1 -> {
                    hra.zpracujPrikaz("vyhoď "+vec.getNazev());
                    seznamPredmetuVBatohu.getChildren().remove(vecVBatohu);
                    pridejPredmetDoMistnosti(vec);

                });
            }
        });
    }

    private HBox vytvorHBoxPredmet(Vec vec) {
        HBox predmet = new HBox();
        predmet.setSpacing(10);
        Label nazevVeci = new Label(vec.getNazev());

        ImageView predmetImageView = new ImageView();
        Image predmetImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + vec.getNazev() + ".jpg"));
        //Image predmetImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + "maliny" + ".jpg"));
        predmetImageView.setFitHeight(VYSKA_IKONY);
        predmetImageView.setFitWidth(SIRKA_IKONY);
        predmetImageView.setImage(predmetImage);


        predmet.getChildren().addAll(predmetImageView, nazevVeci);

        return predmet;
    }

    private void pridejPostavu(Prostor prostor) {
        Postava postavaVProstoru = prostor.getPostava();
        if (postavaVProstoru == null) {
            tlacitkoPromluv.setVisible(false);
            postava.getChildren().clear();
        }
        else {
            tlacitkoPromluv.setVisible(true);
            postava.setSpacing(10);
            Label jmenoPostavy = new Label(postavaVProstoru.getJmeno());
            jmenoPostavy.setId("jmenoPostavy");

            ImageView postavaImageView = new ImageView();
            Image postavaImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + postavaVProstoru.getJmeno() + ".jpg"));
            //Image predmetImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + "maliny" + ".jpg"));
            postavaImageView.setFitHeight(120);
            postavaImageView.setFitWidth(180);
            postavaImageView.setImage(postavaImage);

            postava.getChildren().addAll(postavaImageView, jmenoPostavy);

            tlacitkoPromluv.setOnMouseClicked(event -> {
                Alert promluva = new Alert(Alert.AlertType.NONE);
                promluva.getDialogPane().getButtonTypes().add(ButtonType.OK);
                promluva.setTitle(postavaVProstoru.getJmeno());
                promluva.setHeaderText(postavaVProstoru.getJmeno() + " říká:");
                promluva.setContentText(postavaVProstoru.mluv());
                promluva.show();

                for (Vec vec : postavaVProstoru.getSeznam()) {
                    vec.setViditelnost(true);
                }
                pridejPredmety(prostor);


                if (postavaVProstoru.getJmeno().equals("čaroděj") && !promluveno) {
                    promluveno = true;
                    vyberSTlacitkem.setVisible(true);
                    vyber.getItems().clear();
                    vyber.getItems().add("kočka");
                    vyber.getItems().add("žába");
                    vyber.getItems().add("ryba");
                    tlacitkoHadej.setOnMouseClicked(event1 -> {
                        boolean maPrisady = (hra.zpracujPrikaz("brašna").contains("bylinky") && hra.zpracujPrikaz("brašna").contains("amulet") && hra.zpracujPrikaz("brašna").contains("modrá_houba") && hra.zpracujPrikaz("brašna").contains("pampeliška"));
                        String zvire = "žába";
                        String tip = vyber.getValue();
                        if (tip == null) {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setTitle("Chyba");
                            a.setHeaderText("Je potřeba vybrat, do jakého zvířete byl princ zakletý.");
                            a.show();
                        }
                        else {
                            if (!maPrisady) {
                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setTitle("Chyba");
                                a.setHeaderText("Nemáš všechny přísady na lektvar!");
                                a.show();
                            }
                            else { //ma prisady a ma tip
                                if (tip.equals(zvire)) {
                                    Alert a = new Alert(Alert.AlertType.NONE);
                                    a.setTitle("Gratulujeme!");
                                    a.setHeaderText("Povedlo se ti zachránit prince!");
                                    a.getDialogPane().getButtonTypes().add(ButtonType.OK);
                                    a.show();
                                    hra.zpracujPrikaz("konec");
                                }
                                else { // ma prisady, ale spatny tip
                                    Alert a = new Alert(Alert.AlertType.NONE);
                                    a.setTitle("Ajaj!");
                                    a.setHeaderText("To nebyl princ! Můžeš zkusit hádat znovu.");
                                    a.getDialogPane().getButtonTypes().add(ButtonType.OK);
                                    a.show();
                                }
                            }
                        }
                    });
                }
            });
        }
    }

}
