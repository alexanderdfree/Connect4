public class Bot{
   //bot stuff
   private int player;
   //private Connect4 connect;
   private double min;
   private double max;
   
   public Bot(int p){
      this.player = p;
   }
   
   /*public int negamax1(Connect4 c, int alpha, int beta, int color, int depth){ // BETA NEVER CHANGES
      int depthMax = 12;
      int scaleValue = 10000 * c.getWidth() * c.getHeight()/(depth+1);
      //int scaleValue = 100000;
      if (depth > depthMax) return heuristicValue(c, color, true, scaleValue);
      
      //StdOut.println(depth);
      //recursive algorithm that calculates score for each move
      if (c.gameStatus() == color){
         //return heuristicValue(c, color, false, scaleValue);
         return (color*2-3) * scaleValue;
      }
      if (c.gameStatus() == 0){
         return 0;
      }
      if (c.gameStatus() != color && c.gameStatus() >= 1){
         return -(color*2-3) * scaleValue;
      }
      
      int numToBeat = -Integer.MAX_VALUE;
      //for(int i = 0; i < c.openMoves().length; i++){
      for(int i = 0; i < c.getWidth(); i++){
         if(c.moveArray()[i]){
         Connect4 d = c.clone();
         //d.drop(d.openMoves()[i], color%2+1); //change the board for this iteration
         //d.drop(d.openMoves()[i], color);
         d.drop(i, color); //change the board for this iteration
         //int current = -this.negamax(c, depth-1, -alpha, -beta); //current value of iteration
         numToBeat = Math.max(numToBeat, -this.negamax(d, -alpha, -beta, color%2+1, depth+1));
         alpha = Math.max(alpha, numToBeat);
         //if (current > numToBeat) numToBeat = current;
         //if (numToBeat > alpha) alpha = numToBeat;
         //total += this.minimax(c);
         //d.undrop(c.openMoves()[i]);
         if (alpha >= beta) return numToBeat;
         }
      }
      return numToBeat;
   }*/
   public double negamax2(Connect4 c, double alpha, double beta, int color, int depth){ // BETA NEVER CHANGES
      if (c.gameStatus() == 0) return 0;
      //int depthMax = 12;
      //int scaleValue = 10000 * c.getWidth() * c.getHeight()/(depth+1);
      //int scaleValue = 100000;
      //if (depth > depthMax) return heuristicValue(c, color, true, scaleValue);
      
      //recursive algorithm that calculates score for each move
      for(int i = 0; i < c.getWidth(); i++){
         Connect4 e = c.clone();
         if(c.moveArray()[i]){
            e.drop(i, color);
            if (e.gameStatus() == color) return (e.getWidth()*e.getHeight() + 1 - e.moveTotal())/2;
            //StdOut.println(e.moveTotal());
         }
      }
      
      double upper = (c.getWidth()*c.getHeight() - 1 - c.moveTotal())/2;
      //int numToBeat = -Integer.MAX_VALUE;
      
      if (beta > upper) {
         beta = upper;
         if (alpha >= beta) return beta;
      }
            
      for(int i = 0; i < c.getWidth(); i++){
         if(c.moveArray()[i]){
            
            Connect4 d = c.clone();
            d.drop(i, color); //change the board for this iteration
            
            upper = -this.negamax(d, -alpha, -beta, color%2+1, depth+1);
            //alpha = Math.max(alpha, numToBeat);

            if (upper >= beta) return upper;
            if (upper > alpha) alpha = upper;
         }
      }
      return alpha;
   }
   public double negamax(Connect4 c, double alpha, double beta, int color, int depth){
      int depthMax = 5;
      
      
      //StdOut.println(depth);

      if (c.gameStatus() == color) return (color%2+1)*10000;//Double.POSITIVE_INFINITY;
      if (c.gameStatus() == 0) return 0;
      if (c.gameStatus() != color && c.gameStatus() >= 1) return -(color%2+1)*10000;//Double.NEGATIVE_INFINITY;
      if (depth > depthMax) return eval(c, color);
      
      double numToBeat = Double.NEGATIVE_INFINITY;
      //for(int i = 0; i < c.openMoves().length; i++){
      for(int i = 0; i < c.getWidth(); i++){
         if(c.moveArray()[i]){
            Connect4 d = c.clone();
            //d.drop(d.openMoves()[i], color%2+1); //change the board for this iteration
            //d.drop(d.openMoves()[i], color);
            d.drop(i, color); //change the board for this iteration
            //int current = -this.negamax(c, depth-1, -alpha, -beta); //current value of iteration
            numToBeat = Math.max(numToBeat, -this.negamax(d, -alpha, -beta, color%2+1, depth+1));
            
         }
      }
      return numToBeat;

   }
   public int eval(Connect4 c, int color){
      return (c.countBoard(color)[1]*4 + c.countBoard(color)[2]) - (c.countBoard(color%2+1)[1]*4 + c.countBoard(color%2+1)[2]);
   }
   public int heuristicValue(Connect4 c, int color, boolean depthReached, int s){
      /*if (c.gameStatus() == color){
         return (color*2-3) * s;
      }
      if (c.gameStatus() == 0){
         return 0;
      }*/
      if (c.gameStatus() != color && c.gameStatus() >= 1){
         return -(color*2-3) * s;
      }
      return c.countBoard(color)[0] * s + c.countBoard(color)[1]*100 + c.countBoard(color)[2];
      //return c.countBoard(color)[0] * s + c.countBoard(color)[1]*100 + c.countBoard(color)[2] - (c.countBoard(color%2+1)[0] * s + c.countBoard(color%2+1)[1]*100 + c.countBoard(color%2+1)[2]);
      //if (depthReached){
         //int botTotal = 7*4 + 7*4 + 4*4 + 4*4; //edit for different board size
         //int playerTotal = botTotal;
         //EVALUATE
         //return
      //}
      //return 0;
   }
   public int findMove(Connect4 c){
      //returns best move as column integer
      //return 0;
      int bestMove = -1;
      double bestTotal = -Double.POSITIVE_INFINITY;
      for(int i = 0; i < c.getWidth()-1; i++){
         //c.drop(c.openMoves()[i], this.player);
         //int score = this.minimax(c, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
         if(c.moveArray()[i]){
            Connect4 d = c.clone();
            //d.drop(d.openMoves()[i], this.player);
            d.drop(i, this.player);
            //d.drop(i, this.player%2+1);
            //d.drop(d.openMoves()[i], this.player%2+1);
            //int id = 1;
            //int score = this.negamax(d, Integer.MIN_VALUE, Integer.MAX_VALUE, this.player, 0);
            //int score = this.negamax(d, Integer.MIN_VALUE, Integer.MAX_VALUE, this.player%2+1, 0);
            //double score = -this.negamax2(d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.player%2+1, 0);
            double score = this.negamax2(d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.player, 0);
            if(score > bestTotal){
               bestMove = i;
               bestTotal = score;
            }
         }
         //d.undrop(c.openMoves()[i]);
      }
      if (bestMove == -1){
         for(int i = 0; i < c.getWidth()-1; i++){
            if (c.moveArray()[i]) return i;
         }
      }
      return bestMove;
   }
   public static void main(String[] args){
      //main
      Bot b1 = new Bot(1);
      Bot b2 = new Bot(2);
      Connect4 game = new Connect4(1, 7, 6);
      //Connect4 a = c.clone();
      
      /*while (c.gameStatus()<0){
         c.redBotMove();
         c.redYellowMove();
      }*/
      /*c.drop(0, 1);
      c.drop(0, 1);
      c.drop(0, 1);
      c.print();
      StdOut.println("");
      a.print();*/
      while(game.gameStatus()==-1){//check if the game is over
            
               //print the board
               game.print();
               
               //red player
               //StdOut.println(b1.findMove(c));
               Connect4.botRedTurn(game, b1);
               
               //check if the game is over
               if(game.gameStatus()!=-1) break;
               
               //print the board
               game.print();
               
               //yellow player
               //StdOut.println(b1.findMove(c));
               Connect4.botYellowTurn(game, b2);
            
         }
   }
}