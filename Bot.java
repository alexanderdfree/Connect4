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
         c.drop(i, player);
         total += this.score(c);
      }
      return total;
   }
   public static int minimax(){
   //returns best move as column integer
   }
   
   public static int winner(Connect4 c){
      //check who wins given a hypothetical board
      for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            int spot = this.board[x][y];
            //int spotsToCheck = 1;
            int connected = 1;
            //find a method that will run through for each direction, maybe while loop for each one
            //left
            if(spot != 0){
               if (x > 3){
                  if (spot == this.board[x-1][y]){
                     if (spot == this.board[x-2][y]){
                        if (spot == this.board[x-3][y]){
                           return spot;
                        }     
                     }
                  }
               }
               //right
               if (x < width-4){
                  if (spot == this.board[x+1][y]){
                     if (spot == this.board[x+2][y]){
                        if (spot == this.board[x+3][y]){
                           return spot;
                        }     
                     }
                  }
               }
               //above
               if (y < height-4){
                  if (spot == this.board[x][y+1]){
                     if (spot == this.board[x][y+2]){
                        if (spot == this.board[x][y+3]){
                           return spot;
                        }     
                     }
                  }
               }
               //below
               if (y > 3){
                  if (spot == this.board[x][y-1]){
                     if (spot == this.board[x][y-2]){
                        if (spot == this.board[x][y-3]){
                           return spot;
                        }     
                     }
                  }
               }
               //upRight
               if (y < height-4 && x < width-4){
                  if (spot == this.board[x+1][y+1]){
                     if (spot == this.board[x+2][y+2]){
                        if (spot == this.board[x+3][y+3]){
                           return spot;
                        }     
                     }
                  }
               }
               //downRight
               if (y > 3 && x < width-4){
                  if (spot == this.board[x+1][y-1]){
                     if (spot == this.board[x+2][y-2]){
                        if (spot == this.board[x+3][y-3]){
                           return spot;
                        }     
                     }
                  }
               }
               //upLeft
               if (y < height-4 && x > 3){
                  if (spot == this.board[x-1][y+1]){
                     if (spot == this.board[x-2][y+2]){
                        if (spot == this.board[x-3][y+3]){
                           return spot;
                        }     
                     }
                  }
               }
               //downLeft
               if (y > 3 && x > 3){
                  if (spot == this.board[x-1][y-1]){
                     if (spot == this.board[x-2][y-2]){
                        if (spot == this.board[x-3][y-3]){
                           return spot;
                        }     
                     }
                  }
               
               }
            }
         }
   }
   }
   public static void staticDrop(Connect4 c){
      //drop function but static version
   }
   public static void main(String[] args){
      //main
   }
}