public class Bot{
   //bot stuff
   private int player;
   //private Connect4 connect;
   private double min;
   private double max;
   
   public Bot(int p){
      this.player = p;
   }
   
   public int minimax(Connect4 c, int depth, int alpha, int beta, boolean isMax){
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
      int numToBeat = 0;
      if(isMax){
         numToBeat = -Integer.MAX_VALUE;
         for(int i = 0; i < c.openMoves().length; i++){
            c.drop(c.openMoves()[i], this.player);
            int current = this.minimax(c, depth+1, alpha, beta, false);
            if (current > numToBeat) numToBeat = current;
            if (numToBeat > alpha) alpha = numToBeat;
            //total += this.minimax(c);
            c.undrop(c.openMoves()[i]);
            if (beta<=alpha) break;
         }
      }
      else{
         numToBeat = Integer.MIN_VALUE;;
         for(int i = 0; i < c.openMoves().length; i++){
            c.drop(c.openMoves()[i], this.player);
            //MAY NEED METHOD TO "UNDROP"
            int current = this.minimax(c, depth+1, alpha, beta, true);
            if (current < numToBeat) numToBeat = current;
            if (numToBeat < beta) beta = numToBeat;
            c.undrop(c.openMoves()[i]);
            if (beta<=alpha) break;
         }
      }
      return numToBeat;
   }
   public int findMove(Connect4 c){
      //returns best move as column integer
      //return 0;
      int bestMove = 0;
      double bestTotal = -Double.POSITIVE_INFINITY;
      for(int i = 0; i < c.openMoves().length; i++){
         c.drop(c.openMoves()[i], this.player);
         int score = this.minimax(c, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
         if(score > bestTotal){
            bestMove = i;
            bestTotal = score;
         }
         c.undrop(c.openMoves()[i]);
      }
      return bestMove;
   }
   public static void main(String[] args){
      //main
      Bot b = new Bot(1);
      Connect4 c = new Connect4(7, 6, 1);
      StdOut.println(b.findMove(c));
   }
}