package com.example.javafxoblig1;


import com.example.javafxoblig1.figurer.*;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.PublicKey;
import java.util.*;

//Deklarering
public class Main extends Application {
        public static ToggleButton rKnapp = new ToggleButton("Rektangel");
        public static ToggleButton sKnapp = new ToggleButton("Sirkel");
        public static ToggleButton lKnapp = new ToggleButton("Linje");
        public static ToggleButton tKnapp = new ToggleButton("Tekst");
        public static ToggleButton select = new ToggleButton("Select");
        public static ToggleButton[] kGruppe = {rKnapp, sKnapp, lKnapp, tKnapp, select};

        public static Button frem = new Button("Frem");

        public static Button tilbake = new Button("Bak");

        public static ToggleGroup Utvalg = new ToggleGroup();
        public static Slider sSize = new Slider();
        public static TextField teksten = new TextField();
        public static ColorPicker fargevelger = new ColorPicker(Color.BLACK);
        public static ColorPicker fargevelger_fill = new ColorPicker(Color.WHITE);
        public static Shape fig;
        public static TextArea informasjon = new TextArea();

        public static ListView<Fufo> layer = new ListView<>();

        public static Stack<Shape> figliste = new Stack();

        public static Node sFig;

    @Override
    public void start(Stage hovedS) throws IOException {

        //Knapper med for loop som styler og sett i gruppe
        for (ToggleButton utstyr : kGruppe) {
            utstyr.setToggleGroup(Utvalg);
            utstyr.setMinWidth(100);
            utstyr.setStyle("-fx-font: 12 arial;");
            utstyr.setPadding(new Insets(5));
        }

        //Colorpicker Style
        fargevelger_fill.getStyleClass().add("split-button");
        fargevelger.getStyleClass().add("split-button");

        //TextView Size
        layer.setEditable(false);
        layer.setPrefHeight(200);


        //Slider stroke size
        sSize.setValue(5);
        sSize.setMin(1);
        sSize.setMax(20);

        //Enkel VBox som har alt utstyr til paint applikasjonen
        VBox knp = new VBox(10);
        knp.setSpacing(20);
        knp.setStyle("-fx-background-color: grey");
        knp.setPrefWidth(200);
        knp.setAlignment(Pos.TOP_CENTER);
        knp.setPadding(new Insets(10));
        knp.getChildren().addAll(rKnapp, sKnapp, lKnapp, tKnapp, fargevelger, fargevelger_fill, teksten, select, informasjon, sSize, layer, frem, tilbake);
        //Lager canvas
        Pane canvas = new Pane();

        //Actionevent for museklikk, tilkaller lagRektangel og setter i fig, og legger deretter til i canvas
        canvas.setOnMousePressed(e->{
            if(rKnapp.isSelected()) {
                fig = new lagRektangel(e);
                canvas.getChildren().add(fig);
            } else if (sKnapp.isSelected()) {
                fig = new lagSirkel(e);
                canvas.getChildren().add(fig);
            } else if (lKnapp.isSelected()) {
                fig = new laglinje(e);
                canvas.getChildren().add(fig);
            } else if (tKnapp.isSelected()) {
                fig = new lagTekst(e);
                canvas.getChildren().add(fig);
            } else if (select.isSelected()) {
                sFig = (Node) e.getTarget();
                if (canvas.getChildren().contains(sFig)) {
                   ((tfigur)sFig).info();
                }
            }
        });
        //Actionevent for når man drar musen etter å ha valgt en figur
            canvas.setOnMouseDragged(e->{
            if (rKnapp.isSelected() || sKnapp.isSelected() || lKnapp.isSelected() || tKnapp.isSelected()) {
                ((tfigur)fig).dra(e);
                ((tfigur)fig).info();
            } else if (select.isSelected()) {
                ((tfigur)sFig).flytt(e);
                ((tfigur)sFig).info();
            }
        });
        //Actionevent for når man slipper musen, og legger til objektet i liste
            canvas.setOnMouseReleased(e->{
                if (rKnapp.isSelected()) {
                    figliste.push(fig);
                } else if (sKnapp.isSelected()) {
                    figliste.push(fig);
                } else if (lKnapp.isSelected()) {
                    figliste.push(fig);
                } else if (tKnapp.isSelected()) {
                    figliste.push(fig);
                } else if (select.isSelected()) {

                }
            });

            canvas.getChildren().addListener((ListChangeListener<Node>) change -> {
                layer.getItems().clear();
                for (Node n: change.getList()){
                    layer.getItems().add(new Fufo(n));
                }
            });
            layer.setOnMouseClicked(e->{
                sFig = layer.getSelectionModel().getSelectedItems().get(0).node;
            });
            frem.setOnAction(e->{
                if (select.isSelected()) {
                    int in = figliste.indexOf(fig);
                    Collections.swap(figliste, in, in + 1);
                    canvas.getChildren().clear();
                    canvas.getChildren().addAll(figliste);

                }
            });
        tilbake.setOnAction(e->{
            if (select.isSelected()) {
                int in = figliste.indexOf(fig);
                Collections.swap(figliste, in, in - 1);
                canvas.getChildren().clear();
                canvas.getChildren().addAll(figliste);

            }
        });
        //Lager selve vinduet med størrelse osv
        BorderPane  hovedVindu = new BorderPane();
        hovedVindu.setCenter(canvas);
        hovedVindu.setRight(knp);
        Scene hoved  = new Scene (hovedVindu, 1300, 900);
        hovedS.setScene(hoved);
        hovedS.setTitle("DanoOsci Paint!");
        hovedS.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}