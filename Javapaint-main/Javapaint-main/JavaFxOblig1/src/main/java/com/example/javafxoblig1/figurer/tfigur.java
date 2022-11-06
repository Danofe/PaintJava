package com.example.javafxoblig1.figurer;

import javafx.scene.input.MouseEvent;

//En enkel use interface
public interface tfigur {
    void dra(MouseEvent e);

    void flytt(MouseEvent e);

    void info();

}
