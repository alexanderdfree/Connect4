public class Bot{
   
   //instance variables
   private int player;
   private double min;
   private double max;
   
   //constructor
   public Bot(int p){
      this.player = p;
   }
   
   public double negamax(Connect4 c, double alpha, double beta, int color, int depth){
      
      //score is 0 if game is a draw
      if (c.gameStatus() == 0) return 0;

      //check if player can win in next move
      for(int i = 0; i < c.getWidth(); i++){ //for each column
         
         if(c.moveArray()[i]){ //if the move is valid
            
            //clone board to prevent side effects
            Connect4 e = c.clone();
            
            e.drop(i, color); //drop the token
            
            //if the next move is a win, return the amount of moves it has taken (to get here)
            if (e.gameStatus() == color) return (e.getWidth()*e.getHeight() + 1 - e.moveTotal())/2;
            
         }
      }
      
      //if (depth > 10) return 0;
      
      //given that the next move is not a win
      //this is the longest possible path to victory 
      //(in the amount of moves on the current player's side):
      double upper = (c.getWidth()*c.getHeight() - 1 - c.moveTotal())/2;
      
      //alpha/beta pruning (elaborate)
      if (beta > upper){
         beta = upper;
         if (alpha >= beta) return beta;
      }
      
      //store best move order
      int[] order = Bot.moveOrder(c);
      
      //recursive algorithm that calculates score for each move
      for(int i = 0; i < c.getWidth(); i++){ //for each column
         
         //start in the middle column and work outwards for efficiency's sake
         if(c.moveArray()[order[i]]){ //if the move is valid
            
            //clone board to prevent side effects
            Connect4 d = c.clone();
            
            d.drop(order[i], color); //drop the token in simulated board
            
            upper = -this.negamax(d, -alpha, -beta, color%2+1, depth+1);

            //alpha-beta pruning
            if (upper >= beta) return upper;
            if (upper > alpha) alpha = upper;
         }
      }
      return alpha;
   }
   
   public int findMove(Connect4 c){
      //this method returns best move as column integer
      
      int bestMove = -1; //placeholder for best move
      int[] order = Bot.moveOrder(c); //storing optimal move order
      
      //the number to beat (lowest possible)
      double bestTotal = -Double.POSITIVE_INFINITY;
      
      //for every column
      for(int i = 0; i < c.getWidth(); i++){
         
         if(c.moveArray()[order[i]]){ //if the move is valid
         
            Connect4 d = c.clone(); //clone connect4 to prevent side effects
            
            d.drop(order[i], this.player); //drop the token in simulated board
            
            //the score is the negative... (definition of negamax)
            double score = -this.negamax(d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.player%2+1, 0);
            //double score = this.negamax(d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.player, 0);
            
            
            if(score > bestTotal){ //if the score is better than the current best
               //change the current best move to be current column
               bestMove = i;
               
               //change the current best score to be current score
               bestTotal = score;
            }
         }
      }
      
      //prevents error when forced into losing
      //(basically just admits defeat)
      if (bestMove == -1){
         for(int i = 0; i < c.getWidth()-1; i++){
            //for each column, make any valid move
            if (c.moveArray()[i]) return i;
         }
      }
      
      //return the best possible move
      return bestMove;
   }
   
   public static int[] moveOrder(Connect4 c){
      //start in the middle column and work outwards for efficiency's sake
      
      //access width
      int w = c.getWidth();
      //create integer array
      int[] order = new int[w];
      
      //create index variable and variable to store
      //difference from center column
      int index = 1;
      int difference = 1;
      
      //set middle column to be always first
      order[0] = w/2;
      
      //while the column will still be in bounds
      while (w/2 - difference >= 0 || w/2 + difference < w){
         //start at width/2 and move outwards incrementally in steps of 1
         if(w/2 - difference >= 0){
            order[index] = w/2 - difference;
            index++;
         }
         if(w/2 + difference < w){
            order[index] = w/2 + difference;
            index++;
            
         }
         difference++;
      }
      //return
      return order;
   }
   
   public static void main(String[] args){
      //main method to test bots against each other
      //if all goes to plan, the bot should be an even match for itself
      //and the board should fill up
      
      //create bot objects
      Bot b1 = new Bot(1);
      Bot b2 = new Bot(2);
      
      //create Connect 4 game
      Connect4 game = new Connect4(1, 7, 6);
      
      while (game.gameStatus() == -1){ //check if the game is over
            
               //print the board
               game.print();
               
               //red bot
               Connect4.botRedTurn(game, b1);
               
               //check if the game is over
               if(game.gameStatus()!=-1) break;
               
               //print the board
               game.print();
               
               //yellow bot
               Connect4.botYellowTurn(game, b2);
            
         }
   }
}