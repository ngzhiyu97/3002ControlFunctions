package com.example.itrail;

public class Pixel {
    // define two fields
    boolean dot, edgel;
    Coordinates coord;
    char id;
    // define no arg constructor
    Pixel()
    {  coord = new Coordinates(0,0);
        //x = 0;
        //y = 0;
        id = '1';
        dot = false;
        edgel = false;
    }
    // define parameterized constructor
    Pixel(boolean dot, boolean edgel, Coordinates coordinates, char id)
    {
        this.coord = coordinates;
        this.dot = dot;
        this.edgel = edgel;
        this.id = id;
    }


    public Coordinates getCoordiantes() {
        return coord;
    }

    // Setter
    public void setX(int x) {
        this.coord.setColumn(x);
    }

    public void setY(int y) {
        this.coord.setColumn(y);
    }

    public boolean getDot() {
        return dot;
    }

    public boolean getEdgel() {
        return edgel;
    }
    // Setter
    public void setDot(boolean dot) {
        this.dot = dot;
    }

    public void setEdgel(boolean edgel) {
        this.edgel = edgel;
    }

    public char getId() {
        return id;
    }
    // Setter
    public void setId(char id) {
        this.id = id;
    }
}
