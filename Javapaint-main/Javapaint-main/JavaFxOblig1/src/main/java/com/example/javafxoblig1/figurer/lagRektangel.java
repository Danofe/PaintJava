package com.example.javafxoblig1.figurer;

import com.example.javafxoblig1.Main;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class lagRektangel extends Rectangle implements tfigur {
    double startKoordinatX;
    double startKoordinatY;

    //Henter informasjon om stroke, fill og strokewidth, og gir MouseEvent X og Y koordinatene til
    //variablene startKoordinatX og startKoordinatY
    public lagRektangel (MouseEvent e) {
        super (e.getX(),e.getY(),0,0);
        setStroke(Main.fargevelger.getValue());
        setFill(Main.fargevelger_fill.getValue());
        setStrokeWidth(Main.sSize.getValue());
        startKoordinatX = e.getX();
        startKoordinatY = e.getY();
    }
    //Henter ny X og Y koordinat fra MouseEvent, og gjør det mulig å dra rektangelt til forskjellige størrelser.
    //If setningene gjør det mulig å dra figuren alle veier for størrelsen.
    @Override
    public void dra(MouseEvent e){
        double avstandX = e.getX() - startKoordinatX;
        double avstandY = e.getY() - startKoordinatY;

        if (avstandX > 0) {
            setWidth(avstandX);
            setX(startKoordinatX);
        }
        else {
            setX(e.getX());
            setWidth(-avstandX);
        }
        if (avstandY > 0) {
            setHeight(avstandY);
            setY(startKoordinatY);
        }
        else {
            setY(e.getY());
            setHeight(-avstandY);
        }
    }
    //Gjør det mulig å flytte rektangelet, setter en ny X og Y, og sette ny fill og stroke.
    @Override
    public void flytt(MouseEvent e) {
        setFill(Main.fargevelger_fill.getValue());
        setStroke(Main.fargevelger.getValue());
        setX(e.getX()- getWidth() / 2);
        setY(e.getY() - getHeight() / 2);
    }
    //Viser info om rektangelet
    @Override
    public void info(){
        String type = "Type: " + Main.fig.getClass().getSimpleName() + "\n";
        type+="Areal: " + Math.abs(getHeight() * getWidth()) + "\n";
        type+="Høyde: " + getHeight() + "\n";
        type+="Bredde: " + getWidth() + "\n";
        type+="Farge: " + getFill() + "\n";
        type+="Ytre Farge" + getStroke() + "\n";
        Main.informasjon.setText(type);
    }
}

