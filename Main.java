
class Main {
  public static void main(String[] args) {
    System.out.println("\n--MASTERMIND--");
    System.out.println("Game rules: The objective of this game is to correctly guess a hidden number code using clues from each guess. If you can find the code within 10 guesses, you win! The solution is a 4-digit number with digits between 1 and 6, inclusive. After you make a guess, the game will return a combination of the following three symbols:\n\"x\": One of the four numbers you guessed is in the solution in the correct place.\n\"o\": One of the four numbers you guessed is in the solution, but not in the right place.\n\"-\": One of the four numbers you guessed is not in the solution at all.\nExample: \nSolution: 2236\nGuess: 6234\nxx\no-\nNote: the position of the symbols do not correspond to the position of the guess like in Wordle.");
    Game g = new Game();
    String command = ""; 
    while(!command.equals("q")){
     command = Utils.inputStr("Would you like to: p - play a new game, h - view high scores, q - quit\n\t");
    switch(command){
      case "p":
        g = new Game();
        g.play();
        //1System.out.print(g.getSolution());
        int x = 0;
        System.out.println("input your first guess");
        while(x < 10 && g.getGameover() != true){
          if(x != 0) System.out.println("input your next guess");
          g.guess();
          if(g.getGameover() == true)
            break;
          x++;
        }
        System.out.print("Solution: " + g.getSolution());
        System.out.println("Game over");
        g.putScore();
        break;

      case "h":
        if(g.getScoreHash().size() == 0){
          System.out.println("No scores yet");
          break;
        }
        if(g.getScoreHash() == null){
          System.out.println("No scores yet");
          break;
        }
        for (String i : g.getScoreHash().keySet()) {
          System.out.println("name: " + i + " score: " + (10 - g.getScoreHash().get(i)));
          }
        break;

      default:
        if(!command.equals("q"))
        System.out.println("Please type either p, q, or h");
      }
    }    
  System.out.println ("Goodbye");
    LeaderBoardState state = new LeaderBoardState();
    state.scores = g.getScoreHash();
    state.save();
  }
}