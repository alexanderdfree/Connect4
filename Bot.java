public class Bot{
   
   //instance variables
   private int player;
   
   //constructor
   public Bot(int p){
      this.player = p;
   }
   
   public int negamax(Connect4 c, int alpha, int beta, int color, int depth){
   /* returns an int that is the score of this branch/move
   input: Connect4 c, int alpha(lower bound of pruning), int beta(upper bound of pruning), int color(which piece the bot is), int depth(how many moves into the future to look)
   output: int score of this move
   ex: */

      //score is 0 if game is a draw
      if (c.gameStatus() == 0) return 0;

      //store best move order
      int[] order = Bot.moveOrder(c);
      //check if player can win in next move
      for(int i = 0; i < c.getWidth(); i++){ //for each column
         if(c.moveArray()[order[i]]){ //if the move is valid
            //clone board to prevent side effects
            Connect4 e = c.clone();
            
            e.drop(order[i], color); //drop the token

            //if the next move is a win, return the amount of moves it has taken (to get here)
            //subtracted from total moves
            if (e.gameStatus() == color) return (e.getWidth() * e.getHeight() - e.moveTotal() + 1)/2;
            
         }
      }
      
      //given that the next move is not a win
      //upper bound is the longest possible path to victory
      int upper = (c.getWidth() * c.getHeight() - c.moveTotal() - 1)/2;

      //alpha/beta pruning
      if (beta > upper){
         beta = upper;
         if (alpha >= beta) return beta;
      }

      //recursive algorithm that calculates score for each move
      for(int i = 0; i < c.getWidth(); i++){ //for each column
         
         //start in the middle column and work outwards for efficiency's sake
         if(c.moveArray()[order[i]]){ //if the move is valid
            
            //clone board to prevent side effects
            Connect4 d = c.clone();
            
            d.drop(order[i], color); //drop the token in simulated board
            
            int result = -this.negamax(d, -alpha, -beta, color%2+1, depth+1);

            //alpha-beta pruning
            if (result >= beta) return result;
            if (result > alpha) alpha = result;
         }
      }
      return alpha;
   }
   
   public int findMove(Connect4 c){
   /* finds and returns the best move using Bot.negamax()
   input: Connect4 c
   output: (hopefully)the best possible move*/
      //this method returns best move as column integer
      
      int bestMove = -1; //placeholder for best move
      int[] order = Bot.moveOrder(c); //storing optimal move order
      
      //the number to beat (lowest possible)
      int bestTotal = (int)Double.NEGATIVE_INFINITY;
      
      //for every column
      for(int i = 0; i < c.getWidth(); i++){
         
         if(c.moveArray()[order[i]] && !moveLoses(c, this.player, order[i])){ //if the move is valid
         
            Connect4 d = c.clone(); //clone connect4 to prevent side effects
            
            d.drop(order[i], this.player); //drop the token in simulated board
            
            //my score is the inverse of my opponent's (definition of negamax)
            int score = -this.negamax(d, (int)Double.NEGATIVE_INFINITY, (int)Double.POSITIVE_INFINITY, this.player%2+1, d.moveTotal());

            if(score > bestTotal){ //if the score is better than the current best
               //change the current best move to be current column
               bestMove = order[i];
               
               //change the current best score to be current score
               bestTotal = score;
            }
         }
      }
      
      //prevents error when forced into losing
      //(basically just admits defeat)
      if (bestMove == -1){
         for(int i = 0; i < c.getWidth(); i++){
            //for each column, make any valid move
            if (c.moveArray()[i]) return i;
         }
      }
      
      //return the best possible move
      return bestMove;
   }
   public static boolean[] movesThatDontLose(Connect4 c, int color){//unused
      boolean[] l = new boolean[c.getWidth()];
      
      for(int i = 0; i < c.getWidth(); i++){ //for each column
         
         //start in the middle column and work outwards for efficiency's sake
         if(c.moveArray()[i]){ //if the move is valid
            
            //clone board to prevent side effects
            Connect4 d = c.clone();
            
            d.drop(i, color); //drop the other color token in simulated board
            
            for(int j = 0; j < d.getWidth(); j++){ //for each column
         
               //start in the middle column and work outwards for efficiency's sake
               if(d.moveArray()[j]){ //if the move is valid
                  
                  //clone board to prevent side effects
                  Connect4 e = d.clone();
                  e.drop(j, color%2+1); //drop the other color token in simulated board

                  if (e.gameStatus() == color%2+1) l[i] = false;
                  else l[i] = true;
                  
               }
            }
            
            
         }
      }

      return l;
   
   }
   public static boolean moveLoses(Connect4 c, int color, int col){
   /* return a boolean telling whether this move loses the bot the game or not
   input: Connect4 c, int color(what color this bot is), int col(column to check)
   output: boolean representing whether doing this move will lose this bot the game
   ex:
   Connect4 c = new Connect4(1, 7, 6)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   Bot.moveLoses(c, 2, 4)-->true
   Bot.moveLoses(c, 2, 3)-->false*/
      Connect4 d = c.clone();
      d.drop(col, color);

      for(int i = 0; i < d.getWidth(); i++){ //for each column
         
         //start in the middle column and work outwards for efficiency's sake
         if(d.moveArray()[i]){ //if the move is valid
            
            //clone board to prevent side effects
            Connect4 e = d.clone();
            
            e.drop(i, color%2+1); //drop the other color token in simulated board
            
            if (e.gameStatus() == color%2+1) return true;//if the bot loses
         }
      }
      return false;
   }
   public static int[] moveOrder(Connect4 c){
   /* returns an int array representing which columns to work through in what order*/
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
      while (difference <= w/2){
         
         //start at width/2 and move outwards incrementally in steps of 1
         order[index] = w/2 - difference;
         //increment index
         index++;
         order[index] = w/2 + difference;
         //increment index
         index++;
         //increment distance from middle
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