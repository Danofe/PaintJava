package com.example.javafxoblig1.figurer;

import com.example.javafxoblig1.Main;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import static com.example.javafxoblig1.Main.fig;


//Klasse for å lage sirkel.
//Henter stroke og fill fra main sin colorpicker.
public class lagSirkel extends Circle implements tfigur {
    private double mX, mY;
    public lagSirkel(MouseEvent e) {
        super(e.getX(), e.getY(),10);
        setStroke(Main.fargevelger.getValue());
        setFill(Main.fargevelger_fill.getValue());
        setStrokeWidth(Main.sSize.getValue());
        mX = e.getX();
        mY = e.getY();
        setCenterX(mX);
        setCenterY(mY);

    }
    //Finner midten av sirkel med hjelp av MouseEvent og utvider sirkelen.
    @Override
    public void dra(MouseEvent e) {
        setRadius((Math.abs(e.getX() - getCenterX()) + Math.abs(e.getY() - getCenterY())) / 2);
    }
    //Klasse for å plassere sirkelen når man flytter, og for å endre stroke og fill.
    @Override
    public void flytt(MouseEvent e) {
        setFill(Main.fargevelger_fill.getValue());
        setStroke(Main.fargevelger.getValue());
            setCenterX(e.getX());
            setCenterY(e.getY());
    }
    //Klasse for å vise info om sirkel. Regner ut areal, henter type figur,fill og stroke fra figur.
    @Override
    public void info(){
        String type = "Type: " + Main.fig.getClass().getSimpleName() + "\n";
        type+="Bredde: " + getRadius() + getRadius() + "\n";
        type+="Areal: " + (getRadius() * getRadius() * Math.PI) + "\n";
        type+="Farge: " + getFill() + "\n";
        type+="Ytre Farge" + getStroke() + "\n";
        Main.informasjon.setText(type);
    }
}
