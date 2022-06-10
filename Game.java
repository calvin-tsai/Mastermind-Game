import java.util.HashMap;

/*
My game will be mastermind. The player either chooses a solution of a 4 digit number or gets a randomized solution. This game is similar to Wordle. The player makes a guess, and the code returns whether there is a number in the right place or the right number in the wrong place. the difference from Wordle is that the clues are not in order nor do they correspond to each digit. If the player guesses the 4 numbers within 10 guesses, they win. The classes I will use will be a Play method which starts the game and creates a solution, a guess method that calculates the result from the guess, and a getSolution method that returns the result. The main class will use the play, guess, and getSolution methods to play the game.
*/
public class Game{
  private int[] line = {0,0,0,0};  //solution array
  private int numGuesses;
  private boolean gameover = false;
  private String Name;
  private static HashMap<String,Integer> scores;

  public Game(){
    LeaderBoardState state = LeaderBoardState.restore();
    if (state != null)
      scores = state.scores;
    else
      scores = new HashMap<String, Integer>();
    
  }

 public void play(){
   String name = Utils.inputStr("What is your name?\n\t");
   Name = name;
  int ask = Utils.inputNum("1 player or 2 player game? 1 = 1 player, 2 = 2 player\n  ");
  boolean cont = false;
  while(!cont){
  switch(ask){
    case 1:
      for(int i = 0; i < line.length; i++){
        line[i] = Utils.randInt(1,6);
        }
      cont = true;
      break;

    case 2:
      for(int i = 0; i < line.length; i++){
    line[i] = Utils.inputNum("What is the next number? Number must be between 1 and 6\n ");
    if(line[i] > 6) line[i] = 6;
    if(line[i] < 1) line[i] = 1;
    }
      cont = true;
      break;

    default:
      //FEEDBACK: include print
      System.out.println("Please enter either a \"1\" for 1 player or a \"2\" for 2 player");
      cont = false;
      
    }
  }
   System.out.print("Input your guess. A guess must be a 4 digit number with no spaces and digits must be between 1 and 6 inclusive.\n");
   

   
  }

  public String getSolution(){
    String result = "";
    for(int num : line){
      result += num + " ";
    }
    return result;
  }
  
  public void guess(){
    numGuesses++;
    //converts guess into HashMap
    HashMap<Integer, Integer> list = new HashMap<Integer, Integer>();
    int guess = Utils.inputNum("");
    if(guess < 1111){
      guess = 1111;
      System.out.println("not a valid guess, defaulted to 1111");
    }
    if(guess > 6666){
      guess = 6666;
      System.out.println("not a valid guess, defaulted to 6666");
    }
    String guessString = "" + guess + " ";
    if(guess >= 1111){
      for(int i = 0; i < 4; i++){
        list.put(i, Integer.parseInt(guessString.substring(i, i+1)));
        }
      }
    //System.out.print(list.get(0));

    String result = "";
    for(int i = 0; i < 4; i++){
      String symbol = "";
      for(int k = 0; k < 4; k++){
        if(list.get(i) == line[k] && i == k){
          symbol = "x";
          break;
        }
        else if(list.get(i) == line[k]){
          symbol = "o";
        }
        else if(!symbol.equals("o")){
          symbol = "-";
        }
      }
      result += symbol;
    }
    //System.out.println(result);
    result += " ";
    String finalResult = "";
    
    for(int i = 0; i < 4; i++){
      if(result.substring(i, i+1).equals("x"))
         finalResult += "x";
    }
    for(int i = 0; i < 4; i++){
      if(result.substring(i, i+1).equals("o"))
         finalResult += "o";
    }
    for(int i = 0; i < 4; i++){
      if(result.substring(i, i+1).equals("-"))
         finalResult += "-";
    }
    System.out.println(finalResult.substring(0,2) + "\n" + finalResult.substring(2)); 
    if(result.equals("xxxx ")){
      //int abc = 0;
      //while(abc > -1){
      System.out.print("---- ☻YOU WIN☺ ----   \n");
      //abc++;
      //}
      gameover = true;
      }
    
  }
  public void putScore(){
    scores.put(Name, numGuesses);
  }
  public boolean getGameover(){
    return gameover;
  }
  public int getNumGuesses(){
    return numGuesses;
  }
  public HashMap<String,Integer> getScoreHash(){
    return scores;
  }
}