public class Board{
   //instance variables
   private int width;
   private int height;
   private int boardArr[][];
   
   //constructor
   public Board(int w, int h){
      this.width = 7; //ignore user instructions for now
      this.height = 6;
      this.boardArr = new int[w][h];
      for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            this.boardArr[x][y] = 0; //0 represents empty, 1 represents red, 2 represents yellow
         }
      }
   }
   
   //
   public void drop(int column){
   
   }
   
   //toString, 0=empty=space, 1=red=
   public String toString(){
      
   }
   
}