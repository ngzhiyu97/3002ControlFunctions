package com.example.itrail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

//    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    DrawingView dv ;
    TextView xCoord, yCoord;
    private ConstraintLayout touchview;
    Pixel[][] pixels;
    Coordinates coord;
    int numCols = 20;
    int numRows = 20;
    private int mTouchTolerance;

    private List<Point> mPoints = new ArrayList<Point>();

    int levelSize=25;

    //add every new dot created into this array
    Coordinates[] dotCoordinatesArray=null;

    //added new variables
    //hashtable key -> Integer array of (x,y) coordinates, value -> dot index
    public Hashtable<Integer,Integer> dotHashTable = new Hashtable<Integer,Integer>();
    private int tolerance =30;
    TextView warningText;
    TextView errorCountText;
    //The imaginary boundary box,maximum and minimum x/y position of the dots
    private int boundaryBoxY1 = 400;
    private int boundaryBoxY2 = 1400;
    private int boundaryBoxX1 = 100;
    private int boundaryBoxX2 = 1000;
    private double errorCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        warningText = findViewById(R.id.warningText);
        warningText.setVisibility(View.GONE);
        errorCountText = findViewById(R.id.errorCountText);

        int startGenerationX = boundaryBoxX1+40;
        int startGenerationY = boundaryBoxY1;
        Coordinates c = new Coordinates(startGenerationY,startGenerationX);
        dotCoordinatesArray = new Coordinates[levelSize];
        Integer[] xYValues = new Integer[2];
        String xYValuesString;
        Integer xYValueInt;
        for(int i=0;i<levelSize;i++){
            if(startGenerationX<boundaryBoxX2){

                c=new Coordinates(startGenerationY,startGenerationX);

                dotCoordinatesArray[i]= c;
                //2 loops to create the neighbouring coordinates for one point
                for(int j =0;j<=tolerance/2;j++) {
                    for(int k=0;k<tolerance/2;k++){
                        //combine the x and y value to be used as a key in the hashtable, eg. x=300,y=400, key=300400
                        xYValues[0] = c.getColumn()+j;
                        xYValuesString = xYValues[0].toString();
                        xYValues[1] = c.getRow()+k;
                        xYValuesString = xYValuesString+xYValues[1].toString();
                        xYValueInt = Integer.parseInt(xYValuesString);
                        dotHashTable.put(xYValueInt, i);
                        Log.d("hashtable",xYValueInt+"");
                    }
                }

                for(int j =1;j<=tolerance;j++) {
                    for(int k=0;k<tolerance/2;k++){
                        xYValues[0] = c.getColumn()-j;
                        xYValuesString = xYValues[0].toString();
                        xYValues[1] = c.getRow()-k;
                        xYValuesString = xYValuesString+xYValues[1].toString();
                        xYValueInt = Integer.parseInt(xYValuesString);
                        dotHashTable.put(xYValueInt, i);
                        Log.d("hashtable",xYValueInt+"");
                    }
                }
                startGenerationX=startGenerationX+200;
            }
            else{
                startGenerationY= startGenerationY+200;
                startGenerationX = boundaryBoxX1+200;
                i--;
            }
        }

        
        // Initialise Coordinates and Random number generator
//        pixels = new Pixel[numCols][numRows];
//        Random rand = new Random();
//
//        // Obtain a number between [0 - 49].
//        for (int i = 0; i < numCols; i++) {
//            for (int j = 0; j < numRows; j++) {
//                coord = new Coordinates(j, i);
//                pixels[i][j] = new Pixel(false, false, coord, 'a');
//            }
//        }
//
//        for (int i = 0; i < 100; i++) {
//            int n = rand.nextInt(numCols);
//            int m = rand.nextInt(numRows);
//            pixels[n][m].setId('Z');
//            pixels[n][m].setDot(true);
//        }



        //--------------------
/*  old dot generation
        Coordinates c = new Coordinates(400,399);
        dotCoordinatesArray = new Coordinates[3];
        dotCoordinatesArray[0]= c;
        for(int i =0;i<=tolerance/2;i++) {
            dotHashTable.put(c.getColumn() + i, c.getRow() + i);
        }
        for(int i =1;i<=tolerance/2;i++) {
            dotHashTable.put(c.getColumn() - i, c.getRow() - i);
        }

        c = new Coordinates(600,599);
        dotCoordinatesArray[1]= c;
        for(int i =0;i<=tolerance/2;i++) {
            dotHashTable.put(c.getColumn() + i, c.getRow() + i);
        }
        for(int i =1;i<=tolerance/2;i++) {
            dotHashTable.put(c.getColumn() - i, c.getRow() - i);
        }

        c = new Coordinates(700,699);
        dotCoordinatesArray[2]= c;
        for(int i =0;i<=tolerance/2;i++) {
            dotHashTable.put(c.getColumn() + i, c.getRow() + i);
        }
        for(int i =1;i<=tolerance/2;i++) {
            dotHashTable.put(c.getColumn() - i, c.getRow() - i);
        }
*/

//        for(int i = 0; i < numCols; i ++) {
//            for (int j = 0; j < numRows; j++) {
////                System.out.print("Coord: ");
////                System.out.println(pixels[i][j].getCoordiantes());
////                System.out.print("X: ");
////                System.out.println(pixels[i][j].coord.getColumn());
////                System.out.print("Y: ");
////                System.out.println(pixels[i][j].coord.getRow());
////                System.out.print("id: ");
////                System.out.println(pixels[i][j].getId());
//            }
//        }

        touchview = (ConstraintLayout) findViewById(R.id.constraintlayout);
        dv = new DrawingView(this);
        touchview.addView(dv);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(40);

        xCoord = (TextView) findViewById(R.id.xcordview);
        yCoord = (TextView) findViewById(R.id.ycordview);

    }


    public class DrawingView extends View {

        private Bitmap  mBitmap;
        private Canvas  mCanvas;
        private Path    mPath;
        private Paint   mBitmapPaint;
        Context context;
        private List<Point> mPoints = new ArrayList<Point>();
        private static final int TOUCH_TOLERANCE_DP = 8;
        private int     mLastPointIndex = 0;
        private boolean isPathStarted = false;

        private Paint   textPaint;
        private List<Point> tPoints = new ArrayList<Point>();

        public DrawingView(MainActivity c) {

            super(c, null);
            context = c;
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);

            textPaint = new Paint();
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(50);

            mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);

            generatePoints();


        }

        private void generatePoints()
        {
            Point p0 = new Point();
            for (int i = 0; i < dotCoordinatesArray.length; i++)
            {
                Log.d("array: ", dotCoordinatesArray[i].getColumn() + "");
                Log.d("array: ", dotCoordinatesArray[i].getRow() + "");
                mPoints.add(new Point(dotCoordinatesArray[i].getColumn(), dotCoordinatesArray[i].getRow()));

                Log.d("check",dotCoordinatesArray[i].getColumn()+ "c "+dotCoordinatesArray[i].getRow());
                tPoints.add(new Point(dotCoordinatesArray[i].getColumn()+15, dotCoordinatesArray[i].getRow()-15));
            }
        }


        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);


            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath( mPath,  mPaint);

            // TODO remove if you dont want points to be drawn
//            for (int i = 0; i < numCols; i++) {
//                for (int j = 0; j < numRows; j++) {
//                    canvas.drawPoint(pixels[i][j].coord.getColumn(), pixels[i][j].coord.getRow(), mPaint);
//                }
//            }
            // TODO remove if you dont want points to be drawn
            for (Point point : mPoints) {
                canvas.drawPoint(point.x, point.y, mPaint);
            }

            int i = 1;
            for (Point tPoint: tPoints) {
                canvas.drawText(String.valueOf(i),tPoint.x, tPoint.y, textPaint);
                i++;
            }


        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 8;

        private void touch_start(float x, float y) {
            mPath.reset();
            Point p = mPoints.get(mLastPointIndex);
//            mPath.moveTo(p.x, p.y);
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
            xCoord.setText(String.valueOf(x));
            yCoord.setText(String.valueOf(y));
            if(isPreviousDot(x,y)){
                warningText.setVisibility(GONE);
            }
            if (cPoint(x, y, mLastPointIndex)) {
//                mPath.reset();
                // user starts from given point so path can beis started
                isPathStarted = true;
            } else {
                // user starts move from point which doen's belongs to mPinst list
                isPathStarted = false;
                // Just prompt error here, no count to error rate

            }
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            double errorRate;
            String currentCoordinatesString;
            Integer currentCoordinatesInt;

            if (isPathStarted) {
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                    mX = x;
                    mY = y;
                    xCoord.setText(String.valueOf(x));
                    yCoord.setText(String.valueOf(y));
                    Log.d("checking", "true");
                    if (cPoint(x, y, mLastPointIndex + 1)) {
                        mPath.lineTo(mX, mY);
                        mCanvas.drawPath(mPath,  mPaint);
                        ++mLastPointIndex;
                    }


                    }
                }
            if(mLastPointIndex<levelSize-1){

                currentCoordinatesInt = (int)x;
                currentCoordinatesString = currentCoordinatesInt.toString();
                currentCoordinatesInt = (int)y;
                currentCoordinatesString=currentCoordinatesString+currentCoordinatesInt.toString();
                currentCoordinatesInt = Integer.parseInt(currentCoordinatesString);

                Log.d("index, x, y",dotHashTable.get(currentCoordinatesInt)+""+currentCoordinatesInt+" " +currentCoordinatesString);

                if(dotHashTable.containsKey(currentCoordinatesInt)) {
                    Log.d("key", "true");
                    if (dotHashTable.get(currentCoordinatesInt)!=mLastPointIndex
                            && dotHashTable.get(currentCoordinatesInt)!=mLastPointIndex+1) {
                        Log.d("tag", "wrong dot, start again");
                        if(warningText.getVisibility()==GONE) {
                            warningText.setVisibility(VISIBLE);
                            errorCount++;
                            errorRate = Math.round(errorCount / 25 * 100);
                            errorCountText.setText("Error rate:\n" + errorCount + " - " + errorRate + "%");
                        }
                        mPath.reset();
                        isPathStarted=false;
                    }
                }
            }

            // draw line with finger move
//            if (isPathStarted) {
//                mPath.reset();
//                Point p = mPoints.get(mLastPointIndex);
//                mPath.moveTo(p.x, p.y);
//                mPath.lineTo(x, y);
//            }


        }

//        private void touch_up() {
//            mPath.lineTo(mX, mY);
//            circlePath.reset();
//            // This code below is to reset before saving the line, can remove if want keep the line, but must be before mCanvas.drawPath(mPath,  mPaint); <- the saving part
//            mPath.reset();
//            // commit the path to our offscreen
//            mCanvas.drawPath(mPath,  mPaint);
//            // kill this so we don't double draw
//            mPath.reset();
//        }

        private void touch_up(float x, float y) {
            mPath.reset();
            if (cPoint(x, y, mLastPointIndex + 1) && isPathStarted) {
                // move finished at valid point so draw whole line

                // start point
//                Point p = mPoints.get(mLastPointIndex);
//                mPath.moveTo(p.x, p.y);
//                // end point
//                p = mPoints.get(mLastPointIndex + 1);
//                mPath.lineTo(p.x, p.y);
//                mCanvas.drawPath(mPath, mPaint);
//                mPath.reset();
//                // increment point index
//                ++mLastPointIndex;
                isPathStarted = false;
            }
            else
            {

            }

        }


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();

                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();

                    break;

                //when they release their finger, display notification and end test
                case MotionEvent.ACTION_UP:
                    touch_up(x, y);
                    invalidate();
                    break;
            }
            return true;
        }

        /**
         * Checks if user touch point with some tolerance
         */
        private boolean cPoint(float x, float y, int pointIndex) {
            if (pointIndex == mPoints.size()) {
                // out of bounds
                return false;
            }
            Point point = mPoints.get(pointIndex);
            if (x > (point.x - mTouchTolerance) && x < (point.x + mTouchTolerance)) {
                if (y > (point.y - mTouchTolerance) && y < (point.y + mTouchTolerance)) {
                    return true;
                }
            }
            return false;
        }

        private int dp2px(int dp) {
            Resources r = getContext().getResources();
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
            return (int) px;
        }

        //checks if currentLocation is the previous dot location
        private boolean isPreviousDot(float x, float y){
            int nextPointX = mPoints.get(mLastPointIndex).x;
            int nextPointY = mPoints.get(mLastPointIndex).y;

            if((nextPointX <= x+tolerance && nextPointX >= x-tolerance
                    && nextPointY <= y+tolerance && nextPointY >= y-tolerance)){
                return true;
            }
            return false;
        }



        public List<Point> getPoints() {
            return mPoints;
        }

        public void setPoints(List<Point> points) {
            this.mPoints = points;
        }
    }

}