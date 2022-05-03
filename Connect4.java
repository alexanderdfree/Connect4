public class Connect4 extends Bot{ // things to fix: spacing, method contracts, making sure we standardize thinking of top vs. bottom
   //DECIDE WHETHER OR NOT WE DO 2 CLASSES, WHETHER TO EXTEND BOT?
   
   //instance variables
   private int player;
   private int width;
   private int height;
   private int board[][];
   
   //constructor
   public Connect4(int w, int h, String p){
      /*Constructor
         Input: int w (width), int h (height), String p (player)
         Output: none
         Side Effects: create new Connect4, assign instance variables
      */
      
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
      
      this.board = new int[w][h];
      for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            this.board[x][y] = 0; //0 represents empty, 1 represents red, 2 represents yellow
         }
      }
   }
   
   
   public boolean drop(int column, int user){
      /* drops a token with the given player's color
            in the specified column, simulating gravity 
         Input: int column, int user (# id of player)
         Output: boolean (whether it worked or not)
         Side Effects: update board[][]
      */
      int rowToPlace = 0;
      //find first empty space thinking from the bottom
      for(int i = 0; i < height; i++){
         if(this.board[column][i] == 0){
            this.board[column][i] = user;
            return true;
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
      
      for (int i = 0; i < this.board.length; i++){
         for(int j = 0; j < this.board[i].length; j++){
            char temp = ' ';
            
            if (this.board[i][j] == 1) temp = 'R';
            if (this.board[i][j] == 2) temp = 'Y';
            
            StdOut.print(" [" + temp + "]");
            
         }
         StdOut.println();
      }
   }
   
   public int gameStatus(){
      //returns int of player that won, 0 for draw
      
      //for loop if 4 are in a row
         //for loop for vertical and horizontal terminates -> i < height - 4, width - 4
         //diagonal will be annoying, look at board and think of possible options
         
         
      //for loop for if the board is full
      
      /*for( EACH ROW ){
         if ALL COLUMNS ARE FULL return 0;
      }*/
      return 0; //placeholder
   }
   public boolean columnIsFull(int column){
      
      //if the first row of the column is full, return true
      if(board[column][this.height] != 0) return true;
      else return false;
      
   }
   
   public void main(String[] args){
      //"ui", std in, std out, print after every turn, check win vs. draw, etc
   }
   
}