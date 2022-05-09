public class Bot{
   //bot stuff
   private int player;
   public Bot(int p){
      this.player = p;
   }
   
   public int score(Connect4 c){
      //recursive algorithm that calculates score for each move
      //
      
      if (c.gameStatus() == this.player){
         return 1;
      }
      if (c.gameStatus() == this.player){
         return -1;
      }
      if (c.gameStatus() != this.player){
         return 0;
      }
      int total = 0;
      for(int i = 0; i < c.openMoves().length; i++){
         c.drop(c.openMoves()[i], this.player);
         //MAY NEED METHOD TO "UNDROP"
         total += this.score(c);
         c.undrop(c.openMoves()[i], this.player);
      }
      return total;
   }
   public int minimax(Connect4 c){
      //returns best move as column integer
      //return 0;
      int bestMove = 0;
      int bestTotal = 0;
      for(int i = 0; i < c.openMoves().length; i++){
         c.drop(c.openMoves()[i], this.player);
         if(this.score(c) > bestTotal){
            bestMove = i;
            bestTotal = this.score(c);
         }
         c.undrop(c.openMoves()[i], this.player);
      }
      return bestMove;
   }
   public static void main(String[] args){
      //main
   }
}