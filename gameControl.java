/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rajiv
 */

import java.util.Random;




public class gameControl {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        Pixel[][]  A;
        int numCols = 20;
        int numRows = 20;
        A = new Pixel[numCols][numRows];
        Random rand = new Random();

// Obtain a number between [0 - 49].
        
        //System.out.println(A[1][1].getX());
        for(int i = 0; i < numCols; i ++){
            for(int j = 0; j < numRows; j ++){
                Coordinates coord = new Coordinates(j,i);
                A[i][j] = new Pixel(false, false, coord, 'a');
                


//coord[i].setCoordinates(ThreadLocalRandom.current().nextInt(0, 99) + 1, ThreadLocalRandom.current().nextInt(0, 99) + 1);
        }
            //coord[i].setCoordinates(ThreadLocalRandom.current().nextInt(0, 99) + 1, ThreadLocalRandom.current().nextInt(0, 99) + 1);
        }
        
        for (int i = 0; i < 100; i++){
        int n = rand.nextInt(numCols);
        int m = rand.nextInt(numRows);
        A[n][m].setId('Z');
        A[n][m].setDot(true);}
        
        for(int i = 0; i < numCols; i ++){
            for(int j = 0; j < numRows; j ++){
        System.out.print("X: ");
        System.out.println(A[i][j].coordinates.getColumn());
        System.out.print("Y: ");
        System.out.println(A[i][j].coordinates.getRow());
        System.out.print("id: ");
        System.out.println(A[i][j].getId());}
        
        
        
            }    System.out.println("Noice");
    
}}
