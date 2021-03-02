package com.example.dotgeneration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class TestPage extends AppCompatActivity {

    ConnectDots connectDots = new ConnectDots();
    Coordinates currentFingerCoordinates = new Coordinates(0,0);
    int levelSize=0;

    //add every new dot created into this array
    Coordinates[] dotCoordinatesArray=null;


    //assuming that onCreate is called whenever the user enters a new level
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*code to generate the dots here
            some code
        */

        //update this according to the dots generated in ascending order
        //Eg. index 0 -> dot 1, index 2 -> dot a, etc)
        //dotCoordinatesArray = null;

        //update level size here, change 20 to a variable that will adjust the value according to the level
        //levelSize=20;

        //connectDots.updateLevelDetails(dotCoordinatesArray,levelSize);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        currentFingerCoordinates.setColumn(x);
        currentFingerCoordinates.setRow(y);
        int correctDot = 0; // 1=wrong dot, 2=correct last dot, 0=correct dot

        //when the user press or move their finger on the screen
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                correctDot=connectDots.checkConnectedDot(currentFingerCoordinates);
                //dot connected is in the right order
                if(correctDot==2){
                    //end test, since last dot reached
                }
                //dot connected is in the wrong order
                if(correctDot==1){
                    //display notification for wrong dot connected and remove current line

                    //additional function to check if user clicked on previous dot
                    while(correctDot!=2){
                        correctDot=connectDots.checkPreviousDot(currentFingerCoordinates);
                    }
                }
            case MotionEvent.ACTION_MOVE:
                correctDot=connectDots.checkConnectedDot(currentFingerCoordinates);
                //dot connected is in the right order
                if(correctDot==2){
                    //end test, since last dot reached
                }
                //dot connected is in the wrong order
                if(correctDot==1){
                    //display notification for wrong dot connected and remove current line
                    //check if user clicked on the previous dot as instructed
                    while(correctDot!=2){
                        correctDot=connectDots.checkPreviousDot(currentFingerCoordinates);
                    }
                }
            //when they release their finger, display notification and end test
            case MotionEvent.ACTION_BUTTON_RELEASE:
                //some notification fucntion and user needs to click on the most recent dot
        }
        return super.onTouchEvent(event);
    }
}