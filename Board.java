public class Board{
   //instance variables
   private int player;
   private int width;
   private int height;
   private int boardArr[][];
   
   //constructor
   public Board(int w, int h, String p){
      if(p.equals("red"))
         this.player = 1;
      if(p.equals("yellow"))
         this.player = 2;
      else{
         StdOut.println("Invalid color, player set to red");
         this.player = 1;
      }
      
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
   public void drop(int column, int user){
      int rowToPlace = 0;
      //find first empty space thinking from the bottom
      for(int i = 0; i<height; i++){
         if(boardArr[column][i]==0){
            boardArr[column][i] = user;
            break;
         }
      }
      //if not, do nothing
   }
   
   
   public void print(){
      //toString, 0=empty=space, 1=red=R, 2=yellow=Y
      
      for (int i = 0; i < this.boardArr.length; i++){
         for(int j = 0; j < this.boardArr[i].length; j++){
            char temp = ' ';
            
            if (this.boardArr[i][j] == 1) temp = 'R';
            if (this.boardArr[i][j] == 2) temp = 'Y';
            
            StdOut.print(" [" + temp + "]");
            
         }
         StdOut.println();
      }
   }
   
   public int won(){
      //returns int of player that won
      
      //for loop if 4 are in a row
      
      //for loop for if the board is full
      
      return 0; //placeholder
   }
   public boolean columnIsFull(int column){
      //similar code to drop
      
      //if the column is full, return true
         //for loop
      
      //else return false
      
      return false; //placeholder
   }
   
   public void main(String[] args){
      //"ui", std in, std out, print after every turn
   }
   
}