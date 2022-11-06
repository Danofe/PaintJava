package com.example.javafxoblig1.figurer;


import com.example.javafxoblig1.Main;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class laglinje extends Line implements tfigur {
    double sluttKoordinatX;
    double sluttKoordinatY;

    //Henter fill,stroke og strokewidth
    public laglinje(MouseEvent e) {
        super(e.getX(), e.getY(), e.getX(), e.getY());
        setStroke(Main.fargevelger.getValue());
        setFill(Main.fargevelger_fill.getValue());
        setStrokeWidth(Main.sSize.getValue());

    }

    //Setter slutt X Y koordinatene fra MouseEvent
    @Override
    public void dra(MouseEvent e) {
        setEndX(e.getX());
        setEndY(e.getY());
        sluttKoordinatX = e.getX();
        sluttKoordinatY = e.getY();
    }

    //Lager en formel for å flytte linje og sette ny fill og stroke
    @Override
    public void flytt(MouseEvent e) {
        setFill(Main.fargevelger_fill.getValue());
        setStroke(Main.fargevelger.getValue());
        double høyde = getStartY() - getEndY();
        double bredde = getStartX() - getEndX();

        setStartX(e.getX() - bredde / 2);
        setStartY(e.getY() - høyde / 2);
        setEndX(getStartX() + bredde);
        setEndY(getStartY() + høyde);

    }
    ///Viser all informasjonen om linjen.
    @Override
    public void info() {
        double bredde = getStartX() - getEndX();
        String type = "Type: " + Main.fig.getClass().getSimpleName() + "\n";
        type+="Bredde: " + Math.round(bredde) + "\n";
        type+="Farge: " + getFill() + "\n";
        type+="Ytre Farge" + getStroke() + "\n";
        Main.informasjon.setText(type);

    }
}
