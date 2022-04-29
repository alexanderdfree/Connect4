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
   
      //find first empty space thinking from the bottom
      
      //if not, do nothing
   }
   
   
   public String toString(){
      //toString, 0=empty=space, 1=red=R, 2=yellow=Y
      
      /*for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            this.boardArr[x][y] = 0;
         }
      }*/
      for (int i = 0; i < this.boardArr.length; i++){
         for(int j = 0; j < this.boardArr[i].length; j++){
            StdOut.print(" [" + this.boardArr[i][j] + "]");
         }
         StdOut.println();
      }
   }
   
   public boolean won(){
      //for loop if 4 are in a row
      
      //for loop for if the board is full
   }
   public boolean columnIsFull(int column){
      //similar code to drop
   }
   
   public void main(String[] args){
      //"ui", std in, std out
   }
   
}