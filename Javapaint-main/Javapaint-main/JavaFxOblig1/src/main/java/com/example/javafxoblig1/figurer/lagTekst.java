package com.example.javafxoblig1.figurer;

import com.example.javafxoblig1.Main;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class lagTekst extends Text implements tfigur{

    //Henter tekst fra tekstfield, og sender en advarsel hvis den er tom, og tegner teksten (hvis det er tekst)
    public lagTekst(MouseEvent e){
        super(e.getX(),e.getY(), Main.teksten.getText());
        if (Main.teksten.getText().trim().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Feilmelding");
            alert.setHeaderText("Du har gjort en feil");
            alert.setContentText("Tekstfeltet er tomt");
            alert.showAndWait();
        } else {
            setStroke(Main.fargevelger.getValue());
            setFill(Main.fargevelger_fill.getValue());
            setStrokeWidth(Main.sSize.getValue());

        }
    }

    //Setter størrelsen på teksten ved å dra (MouseEvent)
    @Override
    public void dra(MouseEvent e){
        setFont(Font.font((e.getX() - getX())/2));
    }

    //Finner midten av tekst, og gjør det mulig å flytte utifra det, og velge ny fill og stroke.
    @Override
    public void flytt(MouseEvent e) {
        setFill(Main.fargevelger_fill.getValue());
        setStroke(Main.fargevelger.getValue());
        setX(e.getX() - (getBoundsInLocal().getWidth() / 2));
        setY(e.getY() + (getBoundsInLocal().getHeight() / 4));
    }

    //Viser informasjonen om teksten.
    @Override
    public void info(){
        String type = "Type: " + Main.fig.getClass().getSimpleName() + "\n";
        type+="Tekst: " + Main.teksten.getText() + "\n";
        type+="Bredde: " + (getBoundsInLocal().getWidth()) + "\n";
        type+="Farge: " + getFill() + "\n";
        type+="Ytre Farge" + getStroke() + "\n";
        Main.informasjon.setText(type);
    }


}
