public class Connect4{
   
   //instance variables
   private int player; //1 for red, 2 for yellow
   private int width; //board width
   private int height; //board height
   private int board[][]; //board 2D array -> board[width][height]
   private int moves;
   
   public Connect4(int p, int w, int h){
      /*Constructor
         Input: int w (width), int h (height), String p (player)
         Output: none
         Side Effects: create new Connect4, assign instance variables
      */
      
      this.player = p;
      this.width = w;
      this.height = h;
      
      //create 2D int array
      this.board = new int[w][h];
      
      //for every column 
      for(int x = 0; x < this.width; x++){
         //and every row in that column
         for(int y = 0; y < this.height; y++){
         
            //set each spot to be empty
            this.board[x][y] = 0; //0 represents empty, 1 represents red, 2 represents yellow
            
         }
      }
      this.moves = 0;
   }
   public Connect4(int p, int w, int h, int m){
      /*Constructor
         Input: int w (width), int h (height), String p (player)
         Output: none
         Side Effects: create new Connect4, assign instance variables
      */
      
      this.player = p;
      this.width = w;
      this.height = h;
      this.moves = m;
      //create 2D int array
      this.board = new int[w][h];
      
      //for every column 
      for(int x = 0; x < this.width; x++){
         //and every row in that column
         for(int y = 0; y < this.height; y++){
         
            //set each spot to be empty
            this.board[x][y] = 0; //0 represents empty, 1 represents red, 2 represents yellow
            
         }
      }
        
   }
   
   //create connect4 with specific board
   public Connect4(int w, int h, int p, int m, int[][] b){
      /*Constructor
         Input: int w (width), int h (height), String p (player)
         Output: none
         Side Effects: create new Connect4, assign instance variables
      */
      
      this.player = p;
      this.width = w;
      this.height = h;
      this.board = b;
      this.moves = m;
   }
   
   public char getSpotChar(int x, int y){
   /* returns the piece at given spot
   input x, y location of spot
   Output: char representing 
   E = empty
   R = red
   Y = yellow
   ex:

   Connect4 c = new Connect4(1, 7, 6)
   c.drop(3, 1)
   c.drop(2, 2)
   c.getSpotChar(3, 0)-->'R'
   c.getSpotChar(2, 0)-->'Y'
   c.getSptChar(3, 1)-->'E'*/
      
      if(board[x][y] == 0) return 'E';
      else if(board[x][y] == 1) return 'R';
      return 'Y';
   }
   
   public int[] openMoves(){
   /* returns an array of valid moves
   input: NaN
   output: an array of all possible moves
   ex:
   Connect4 c = new Connect4(1, 7, 6)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.openMoves()-->[0, 1, 2, 3, 4, 5, 6]
   d.drop(3,1)
   c.openMoves()-->[0, 1, 2, 4, 5, 6]*/

      int count = 0;
      for(int x = 0; x < this.width; x++){
         if(this.board[x][this.height-1] == 0){
            count++;
         }
      }
      int ints[] = new int[count];
      count = 0;
      for(int x = 0; x < this.width; x++){
         if(this.board[x][this.height-1] == 0){
            ints[count] = x;
            count++;
         }
      }
      return ints;
      
   }
   public boolean[] moveArray(){
   /* return a boolean array telling whether or not a move is valid(ver similiar to Connect4.openMoves())
   input: NaN
   output: an array of all possible moves
   ex:
   Connect4 c = new Connect4(1, 7, 6)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.openMoves()-->[true, true, true, true, true, true, true]
   d.drop(3,1)
   c.openMoves()-->[true, true, true, false, true, true, true]*/

      //return an array int[width][true/false]
      //of column index and whether the move is valid
      
      //create array
      boolean[] bools = new boolean[width];
      
      //for each column
      for (int x = 0; x < this.width; x++){
         //if the top spot is open
         if(this.board[x][this.height-1] == 0){
            //set the arr to be true at that index
            bools[x] = true;
         }
         //otherwise make it false
         else bools[x] = false;
      }
      //return
      return bools;
      
   }
   
   public void drop(int x, int user){
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
            this.moves++;
            return; //return blank to end

         }
      }
      return; //if not, do nothing
      
   }
   public int lowestHeight(int x){
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
         if(this.board[x][y] != 0) return y;
      }
      return -1; //if not, do nothing
      
   }
   public Connect4 clone(){
   /* returns a clone by value
   inputs: NaN
   outputs: a Connect4 clone of this Connect4 object
   ex:
   Connect4 c = new Connect4(1, 7, 6)
   c.drop(3,1)
   c.drop(3,2)
   c.clone()-->c(by value not reference)*/
   
      Connect4 a = new Connect4(this.player, this.width, this.height, this.moves);
      
      for(int x = 0; x < this.width; x++){ //for every column
         for(int y = 0; y < this.height; y++){ //and row
            //copy the board contents
            a.board[x][y] = this.board[x][y]; //0 represents empty, 1 represents red, 2 represents yellow
         }
      }

      return a;
   } 
   public void undrop(int x){//unused method
      //undo the drop() method
      for(int y = this.height-1; y > 0; y--){
         //if the slot is filled
         if(this.board[x][y] != 0){
            this.board[x][y] = 0; //remove the token at (column (x), row (y))
            return; //return blank to end
         }
      }
      
      return; //if not, do nothing
   }

   public int[] twoInARow(int player){
      /* returns an integer array of the two coordinates of the two tokens in a row
      in the bottom row of the board
        inputs: int player (the player who's turn it is)
        outputs: int[] (the two coordinates of the two tokens in a row)
        ex:
        Connect4 c = new Connect4(1, 7, 6)

        c.drop(3, 1)
        c.drop(2, 1)

        c.twoInARow(1) --> [2]
       */


      int count = 0;
      //for every column
      for(int x = 1; x < this.width-2; x++) {
         //if the two spaces are filled with opponent color
         //and the adjacent ones are empty
         if(this.board[0][x] == 0 && this.board[0][x+1] == 0)  count++;
      }
      int[] twos = new int[count];
      count = 0;
      for(int x = 0; x < this.width-1; x++) {
          if(this.board[0][x] == 0 && this.board[0][x+1] == 0) {
             twos[count] = x;
             count++;
          }
      }
      return twos;
   }
   public int moveTotal(){
      //returns depth of entire board so far (amount of moves made in lifetime)
      return this.moves;
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
   public int[][] getBoard(){
      //accessor method for board
      
      return this.board;
   }
   public int getWidth(){
      //accessor method for width
      
      return this.width;
   }
   public int getHeight(){
      //accessor method for height
      return this.height;
   }
   public int getSpot(int x, int y){
      //accessor method for a specific spot/token
      return board[x][y];
   }
   
   public int gameStatus(){
   /* returns int of player that won, 0 for draw, -1 for still going
   input: NaN
   output: int representing if someone won or not
   ex:
   Connect4 c = new Connect4(1, 7, 6)
   c.gameStatus()--> -1
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.drop(3,1)
   c.gameStatus()--> 1*/
      
      
      //for loop if 4 are in a row
         //for loop for vertical and horizontal terminates -> i < height - 4, width - 4
         //diagonal will be annoying, look at board and think of possible options
         
      for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            int spot = this.board[x][y];
            //find a method that will run through for each direction, maybe while loop for each one
            //left
            if(spot != 0){
               if (x >= 3){
                  if (spot == this.board[x-1][y]){
                     if (spot == this.board[x-2][y]){
                        if (spot == this.board[x-3][y]){
                           return spot;
                        }     
                     }
                  }
               }
               //right
               if (x <= width-4){
                  if (spot == this.board[x+1][y]){
                     if (spot == this.board[x+2][y]){
                        if (spot == this.board[x+3][y]){
                           return spot;
                        }     
                     }
                  }
               }
               //above
               if (y <= height-4){
                  if (spot == this.board[x][y+1]){
                     if (spot == this.board[x][y+2]){
                        if (spot == this.board[x][y+3]){
                           return spot;
                        }     
                     }
                  }
               }
               //below
               if (y >= 3){
                  if (spot == this.board[x][y-1]){
                     if (spot == this.board[x][y-2]){
                        if (spot == this.board[x][y-3]){
                           return spot;
                        }     
                     }
                  }
               }
               //upRight
               if (y <= height-4 && x <= width-4){
                  if (spot == this.board[x+1][y+1]){
                     if (spot == this.board[x+2][y+2]){
                        if (spot == this.board[x+3][y+3]){
                           return spot;
                        }     
                     }
                  }
               }
               //downRight
               if (y >= 3 && x <= width-4){
                  if (spot == this.board[x+1][y-1]){
                     if (spot == this.board[x+2][y-2]){
                        if (spot == this.board[x+3][y-3]){
                           return spot;
                        }     
                     }
                  }
               }
               //upLeft
               if (y <= height-4 && x >= 3){
                  if (spot == this.board[x-1][y+1]){
                     if (spot == this.board[x-2][y+2]){
                        if (spot == this.board[x-3][y+3]){
                           return spot;
                        }     
                     }
                  }
               }
               //downLeft
               if (y >= 3 && x >= 3){
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
         
      //for loop for if the board is full
      int count = 0;
      for(int x = 0; x < this.width; x++){
         if(this.board[x][this.height-1] != 0) count++;
      }
      if (count == this.width) return 0;
      return -1; //if 1. nobody has 4 in a row, 2. the board still has room
   }
   public int[] countBoard(int player){//unused
   /* returns an int array representing */
      int[] streaks = new int[3];
      for(int x = 0; x < this.width; x++){
         for(int y = 0; y < this.height; y++){
            int spot = this.board[x][y];
            //find a method that will run through for each direction, maybe while loop for each one
            //left
            if(spot != 0){
            
               //left 4
               if (x >= 3){
                  if (spot == this.board[x-1][y]){
                     if (spot == this.board[x-2][y]){
                        if (spot == this.board[x-3][y]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //left 3
               else if (x >= 2){
                  if (spot == this.board[x-1][y]){
                     if (spot == this.board[x-2][y]){

                        streaks[1]++;
                          
                     }
                  }
               }
               //left 2
               else if (x >= 1){
                  if (spot == this.board[x-1][y]){

                        streaks[2]++;
                          
                     
                  }
               }
               
               //right 4
               if (x <= width-4){
                  if (spot == this.board[x+1][y]){
                     if (spot == this.board[x+2][y]){
                        if (spot == this.board[x+3][y]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //right 3
               else if (x <= width-3){
                  if (spot == this.board[x+1][y]){
                     if (spot == this.board[x+2][y]){
                        streaks[1]++;    
                     }
                  }
               }
               
               //right 2
               else if (x <= width-2){
                  if (spot == this.board[x+1][y]){
                        streaks[2]++;
                             
                     
                  }
               }
               
               //above 4
               if (y <= height-4){
                  if (spot == this.board[x][y+1]){
                     if (spot == this.board[x][y+2]){
                        if (spot == this.board[x][y+3]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //above 3
               else if (y <= height-3){
                  if (spot == this.board[x][y+1]){
                     if (spot == this.board[x][y+2]){
                           streaks[1]++;
                           
                     }
                  }
               }
               //above 2
               else if (y <= height-2){
                  if (spot == this.board[x][y+1]){
                           streaks[2]++;
                            
                     
                  }
               }
               
               //down 4
               if (y >= 3){
                  if (spot == this.board[x][y-1]){
                     if (spot == this.board[x][y-2]){
                        if (spot == this.board[x][y-3]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //down 3
               else if (y >= 2){
                  if (spot == this.board[x][y-1]){
                     if (spot == this.board[x][y-2]){
                           streaks[1]++;
                             
                     }
                  }
               }
               //down 2
               else if (y >= 1){
                  if (spot == this.board[x][y-1]){
                     streaks[2]++;
                             
                     
                  }
               }
               //upRight 4
               if (y <= height-4 && x <= width-4){
                  if (spot == this.board[x+1][y+1]){
                     if (spot == this.board[x+2][y+2]){
                        if (spot == this.board[x+3][y+3]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //upRight 3
               else if (y <= height-3 && x <= width-3){
                  if (spot == this.board[x+1][y+1]){
                     if (spot == this.board[x+2][y+2]){
                           streaks[1]++;
                            
                     }
                  }
               }
               //upRight 2
               else if (y <= height-2 && x <= width-2){
                  if (spot == this.board[x+1][y+1]){
                     streaks[2]++;
                            
                     
                  }
               }
               //downRight4
               if (y >= 3 && x <= width-4){
                  if (spot == this.board[x+1][y-1]){
                     if (spot == this.board[x+2][y-2]){
                        if (spot == this.board[x+3][y-3]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //downRight 3
               else if (y >= 2 && x <= width-3){
                  if (spot == this.board[x+1][y-1]){
                     if (spot == this.board[x+2][y-2]){
                        streaks[1]++;
                             
                     }
                  }
               }
               //downRight 2
               else if (y >= 1 && x <= width-2){
                  if (spot == this.board[x+1][y-1]){
                     streaks[2]++;
                             
                     
                  }
               }
               //upLeft 4
               if (y <= height-4 && x >= 3){
                  if (spot == this.board[x-1][y+1]){
                     if (spot == this.board[x-2][y+2]){
                        if (spot == this.board[x-3][y+3]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //upLeft 3
               else if (y <= height-3 && x >= 2){
                  if (spot == this.board[x-1][y+1]){
                     if (spot == this.board[x-2][y+2]){
                        streaks[1]++;
                            
                     }
                  }
               }
               //upLeft 2
               else if (y <= height-2 && x >= 1){
                  if (spot == this.board[x-1][y+1]){
                     streaks[2]++;
                            
                     
                  }
               }
               //downLeft 4
               if (y >= 3 && x >= 3){
                  if (spot == this.board[x-1][y-1]){
                     if (spot == this.board[x-2][y-2]){
                        if (spot == this.board[x-3][y-3]){
                           streaks[0]++;
                        }     
                     }
                  }
               }
               //downLeft 3
               else if (y >= 2 && x >= 2){
                  if (spot == this.board[x-1][y-1]){
                     if (spot == this.board[x-2][y-2]){
                        streaks[1]++;
                             
                     }
                  }
               }
               //downLeft 2
               else if (y >= 1 && x >= 1){
                  if (spot == this.board[x-1][y-1]){
                     streaks[2]++;
                             
                     
                  }
               
               } 
            }
         }
      }
      return streaks;
   }
   public boolean columnIsFull(int column){
   /* returns if the column is full
   input: int column
   output: boolean whether column is full or not*/
      
      //if the first row of the column is full, return true
      if(board[column][this.height-1] != 0) return true;
      else return false;
      
   }
   public static void humanRedTurn(Connect4 game){
      //runs human turn as red player
      StdOut.println("red to move");
      String move = StdIn.readLine();
      game.drop(Integer.parseInt(move), 1);
   }
   public static void humanYellowTurn(Connect4 game){
      //runs human turn as yellow player
      StdOut.println("yellow to move");
      String move = StdIn.readLine();
      game.drop(Integer.parseInt(move), 2);
   }
   public static void botRedTurn(Connect4 game, Bot b){
      //runs bot turn as red player
      int botMove = b.findMove(game.clone());
      StdOut.println("bot drops a red piece in column " + botMove);
      game.drop(botMove, 1);
   }
   public static void botYellowTurn(Connect4 game, Bot b){
      //runs bot turn as yellow player
      int botMove = b.findMove(game.clone());
      StdOut.println("bot drops a yellow piece in column " + botMove);
      game.drop(botMove, 2);
   }
   public static void printEnd(Connect4 game){
      //print who won
      if(game.gameStatus() == 1){
         StdOut.println("RED WON!");
      }
      else if(game.gameStatus() == 2){
         StdOut.println("YELLOW WON");
      }
      else{
         StdOut.println("DRAW!");
      }
   }
   public static void main(String[] args){
      //"ui", std in, std out, print after every turn, check win vs. draw, etc
      StdOut.println("Red or yellow?");
      String color = StdIn.readLine();
      
      int colorInt = 2;
      if(color.equals("red") || color.equals("Red") || color.equals("R") || color.equals("r")) colorInt = 1;
      
      Connect4 game = new Connect4(colorInt, 7, 6);
      
      StdOut.println("Would you like to play against a bot? Yes or no.");
      String botBool = StdIn.readLine();
      Bot b = new Bot(colorInt%2+1);
      
      if(botBool.equals("Yes") || botBool.equals("Y") || botBool.equals("yes") || botBool.equals("y")){
         if(colorInt == 1){
            while(game.gameStatus() == -1){//check if the game is over
            
               //print the board
               game.print();
               
               //red player
               humanRedTurn(game);
               
               //check if the game is over
               if(game.gameStatus()!=-1) break;
               
               //print the board
               game.print();
               
               //yellow player
               botYellowTurn(game, b);
            }
         }
         else{
            while(game.gameStatus() == -1){//check if the game is over
            
               //print the board
               game.print();
               
               //red player
               botRedTurn(game, b);
               
               //check if the game is over
               if(game.gameStatus() != -1) break;
               
               //print the board
               game.print();
               
               //yellow player
               humanYellowTurn(game);
            }
         }
      }
      else{
         while(game.gameStatus() == -1){//check if the game is over
         
            //print the board
            game.print();
            
            //red player
            humanRedTurn(game);
            
            //check if the game is over
            if(game.gameStatus() != -1) break;
            
            //print the board
            game.print();
            
            //yellow player
            humanYellowTurn(game);
         }
      }
      game.print();
      printEnd(game);
      
   }

}