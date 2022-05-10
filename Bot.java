public class Bot{
   //bot stuff
   private int player;
   //private Connect4 connect;
   private double min;
   private double max;
   
   public Bot(int p){
      this.player = p;
   }
   
   public int negamax(Connect4 c, int alpha, int beta, int color){
      //recursive algorithm that calculates score for each move
      if (c.gameStatus() == color){
         return (color*2-3);
      }
      if (c.gameStatus() == 0){
         return 0;
      }
      if (c.gameStatus() != color && c.gameStatus() >= 1){
         return -(color*2-3);
      }
      int numToBeat = -Integer.MAX_VALUE;
      for(int i = 0; i < c.openMoves().length; i++){
         Connect4 d = c.clone();
         d.drop(d.openMoves()[i], color%2+1); //change the board for this iteration
         //int current = -this.negamax(c, depth-1, -alpha, -beta); //current value of iteration
         numToBeat = Math.max(numToBeat, -this.negamax(d, -alpha, -beta, color%2+1));
         alpha = Math.max(alpha, numToBeat);
         //if (current > numToBeat) numToBeat = current;
         //if (numToBeat > alpha) alpha = numToBeat;
         //total += this.minimax(c);
         //d.undrop(c.openMoves()[i]);
         if (alpha >= beta) return numToBeat;
      }
      return numToBeat;
   }
   public int findMove(Connect4 c){
      //returns best move as column integer
      //return 0;
      int bestMove = 0;
      double bestTotal = -Double.POSITIVE_INFINITY;
      for(int i = 0; i < c.openMoves().length; i++){
         //c.drop(c.openMoves()[i], this.player);
         //int score = this.minimax(c, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
         Connect4 d = c.clone();
         d.drop(d.openMoves()[i], this.player);
         //int id = 1;
         int score = this.negamax(d, Integer.MIN_VALUE, Integer.MAX_VALUE, this.player);
         if(score > bestTotal){
            bestMove = i;
            bestTotal = score;
         }
         //d.undrop(c.openMoves()[i]);
      }
      return bestMove;
   }
   public static void main(String[] args){
      //main
      Bot b = new Bot(1);
      
      Connect4 c = new Connect4(1, 7, 6);
      Connect4 a = c.clone();
      StdOut.println(b.findMove(a));
      /*c.drop(0, 1);
      c.drop(0, 1);
      c.drop(0, 1);
      c.print();
      StdOut.println("");
      a.print();*/
   }
}