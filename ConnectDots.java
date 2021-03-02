package com.example.dotgeneration;

import java.util.Hashtable;

public class ConnectDots{

    //coordinatesArray should contain the dots in ascending order
    //(Eg. index 0 -> dot 1, index 2 -> dot a, etc)
    Coordinates[] dotCoordinatesArray=null;
    Coordinates nextDotCoordinates=null;
    int dotIndex = 0;
    int levelSize =0;
    Hashtable<Coordinates,Boolean> dotCoordinatesHashtable=null;

    //timer starts when the user enters the test environment, so it will be done in main

    //constructor
    public ConnectDots(){}

    //use this method when a new level is started to set the level deatils
    public void updateLevelDetails(Coordinates[] dotCoordinatesArray,int levelSize){
        dotCoordinatesHashtable = null;
    dotIndex =0;
    this.dotCoordinatesArray=dotCoordinatesArray;
    //level size = number of dots in the level
    this.levelSize=levelSize;
    //construct the hashtable, used to check if user's finger location is on a dot
        for(int i=0;i<levelSize;i++){
            dotCoordinatesHashtable.put(dotCoordinatesArray[i],true);
        }
    }

    //call this whenever the user's finger is on the screen, pass in the new coordinates of the user's finger
    //returns 0 for correct dot, 1 for wrong dot and 2 for last dot
    public int checkConnectedDot(Coordinates currentCoordinates){
        //if user connected the correct dot
        if(currentCoordinates == dotCoordinatesArray[dotIndex]){
            //set next dot's coordinates and increment dotIndex
            dotIndex++;
            nextDotCoordinates = dotCoordinatesArray[dotIndex];

            //check if current coordinates is the last dot
            if(levelSize == dotIndex){
            //end test function
            return 2;
            }
        }

        //if user connected to a dot but it is not the correct one
        if(dotCoordinatesHashtable.contains(currentCoordinates)){
            //(false can be used in main to call for the line to be removed and to display a notification)
            return 1;
        }

        //if the user is at some random location without a dot or current correct dot is not the last dot
        return 0;
    }

    //if user connected the wrong dot, call this function to make user return to previous dot
    public int checkPreviousDot(Coordinates currentCoordinates) {
        //check if the dot connected is the first one,
        //if it is not minus 1 to find the previous dot the user needs to click on
        if (dotIndex != 0)
            dotIndex--;

        return checkConnectedDot(currentCoordinates);
    }
}
