public class Connect4 extends Bot{ // things to fix: spacing, method contracts, making sure we standardize thinking of top vs. bottom
   //DECIDE WHETHER OR NOT WE DO 2 CLASSES, WHETHER TO EXTEND BOT?
   
   //instance variables
   private int player; //1 for red, 2 for yellow
   private int width; //board width
   private int height; //board height
   private int board[][]; //board 2D array -> board[width][height]
   
   public Connect4(int w, int h, String p){
      /*Constructor
         Input: int w (width), int h (height), String p (player)
         Output: none
         Side Effects: create new Connect4, assign instance variables
      */
      
      if(p.equals("red")) this.player = 1;
      else if(p.equals("yellow")) this.player = 2;
      else{
         StdOut.println("Invalid color, player set to red");
         this.player = 1;
      }
      
      this.width = 7; //ignore user instructions for now
      this.height = 6;
      
      this.board = new int[w][h];
      for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            this.board[x][y] = 0; //0 represents empty, 1 represents red, 2 represents yellow
         }
      }
   }
   
   public char getSpot(int x, int y){
      if(board[x][y] == 0) return 'E';
      else if(board[x][y] == 1) return 'R';
      return 'Y';
   }
   
   public boolean drop(int x, int user){
      /* drops a token with the given player's color
            in the specified column, simulating gravity 
         Input: int x, int user (# id of player)
         Output: boolean (whether it worked or not)
         Side Effects: update board[][]
      */
      
      //find first empty space thinking from the bottom
      
      //for every y-slot on the board
      for(int y = 0; y < this.height; y++){
         //if the slot is empty
         if(this.board[x][y] == 0){
            this.board[x][y] = user; //place the token at (column (x), row (y))
            return true; //return true to show method dropped sucessfully

         }
      }
      return false; //if not, do nothing - return false so we know it didn't work
   }
   
   public void print(){
      /* essentially a toString() method for the board 
            (0=empty=space, 1=red=R, 2=yellow=Y)
         Input: none, uses this.board[][]
         Output: StdOut prints the board
         Side Effects: none
      */ 
      for(int y = this.height - 1; y >= 0; y--){
         for (int x = 0; x < this.width; x++){
            char temp = ' ';
            
            if (this.board[x][y] == 1) temp = 'R';
            if (this.board[x][y] == 2) temp = 'Y';
            
            StdOut.print(" [" + temp + "]");
            
         }
         StdOut.println();
      }
   }
   
   public int gameStatus(){
      //returns int of player that won, 0 for draw, -1 for still going
      
      //for loop if 4 are in a row
         //for loop for vertical and horizontal terminates -> i < height - 4, width - 4
         //diagonal will be annoying, look at board and think of possible options
         
      for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            int spot = this.board[x][y];
            int spotsToCheck = 1;
            int connected = 1;
            while (spotsToCheck > 0){ //find a method that will run through for each direction, maybe while loop for each one
               //left
               if (x > 3){
                  //if (spot !=)
               }
               //right
               if (x < width-4){
               
               }
               //above
               if (y < height-4){
               
               }
               //below
               if (y > 3){
               
               }
               //upRight
               if (y < height-4 && x < width-4){
               
               }
               //downRight
               if (y > 3 && x < width-4){
               
               }
               //upLeft
               if (y < height-4 && x > 3){
               
               }
               //downLeft
               if (y > 3 && x > 3){
               
               }
            }   
         }
      }
         
      //for loop for if the board is full
      int count = 0;
      for(int x = 0; x < this.width; x++){
         if(this.board[x][this.height] != 0) count++;
      }
      if (count == this.width) return 0;
      return -1; //if 1. nobody has 4 in a row, 2. the board still has room
   }
   
   public boolean columnIsFull(int column){
      
      //if the first row of the column is full, return true
      if(board[column][this.height-1] != 0) return true;
      else return false;
      
   }
   
   public void main(String[] args){
      //"ui", std in, std out, print after every turn, check win vs. draw, etc
   }

}