package com.example.dotgeneration;

public class Coordinates {
    float row;
    float column;


    Coordinates(float row, float column){
        this.column = column;
        this.row = row;
    }

    public void setColumn(float column){
        this.column = column;
    }

    public void setRow(float row){
        this.row = row;
    }

    public float getRow(){
        return row;
    }

    public float getColumn(){
        return column;
    }

}
