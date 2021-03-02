/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rajiv
 */
public class Pixel {
/* create a pixel object to fill up the grid
  which has a bool dot anot
    index can be used as coordinate
    need another bool called line or edgel which creates an edgel
        
*/  
    
    // define two fields
    boolean dot, edgel;
    Coordinates coordinates;
    char id;
    // define no arg constructor
    Pixel()
    {  coordinates = new Coordinates(0,0);
        //x = 0;
        //y = 0;
        id = '1';
        dot = false;
        edgel = false;
    }
    // define parameterized constructor
    Pixel(boolean dot, boolean edgel, Coordinates coordinates, char id)
    {
        this.coordinates = coordinates;
        this.dot = dot;
        this.edgel = edgel;
        this.id = id;
    }
        public Coordinates getCoordiantes() {
            return coordinates;
          }
        
          // Setter
          public void setX(int x) {
            this.coordinates.setColumn(x);
          }
          
          public void setY(int y) {
            this.coordinates.setColumn(y);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
