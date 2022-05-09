public class Bot{
   //bot stuff
   private int player;
   //private Connect4 connect;
   private double min;
   private double max;
   
   public Bot(int p){
      this.player = p;
   }
   
   public int minimax(Connect4 c/*, int depth, int alpha, int beta*/){
      //recursive algorithm that calculates score for each move
      if (c.gameStatus() == this.player){
         return 1;
      }
      if (c.gameStatus() == 0){
         return 0;
      }
      if (c.gameStatus() != this.player && c.gameStatus() >= 1){
         return -1;
      }
      int total = 0;
      for(int i = 0; i < c.openMoves().length; i++){
         c.drop(c.openMoves()[i], this.player);
         //MAY NEED METHOD TO "UNDROP"
         total += this.minimax(c);
         c.undrop(c.openMoves()[i], this.player);
      }
      return total;
   }
   public int findMove(Connect4 c){
      //returns best move as column integer
      //return 0;
      int bestMove = 0;
      double bestTotal = -Double.POSITIVE_INFINITY;
      for(int i = 0; i < c.openMoves().length; i++){
         c.drop(c.openMoves()[i], this.player);
         if(this.minimax(c) > bestTotal){
            bestMove = i;
            bestTotal = this.minimax(c);
         }
         c.undrop(c.openMoves()[i], this.player);
      }
      return bestMove;
   }
   public static void main(String[] args){
      //main
   }
}