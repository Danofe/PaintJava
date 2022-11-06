package com.example.javafxoblig1;

import javafx.scene.Node;

public class Fufo {
    public Node node;

    public Fufo(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return node.toString();
    }
}
