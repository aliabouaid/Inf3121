import java.util.Scanner;

public class Minesweeper {

	private static MineField field;
	private static Ranking rank;
	private static String playerInput;
	private static int result;

	public static void main(String[] args) {
		rank = new Ranking();
		mainMessage();

		// while player still active and dont type exit
		while(gameCountinue() == true);
		System.out.println("\nThank you for playing :) Have a nice day!");
	}

	private static void mainMessage(){
		System.out.println("Welcome to Minesweeper!");
		System.out.println("To play just input some coordinates and try not to step ont mine :)");
		System.out.println("Usefull commands:");
		System.out.println("restart- Starts a new game.");
		System.out.println("exit- Quits the game.");
		System.out.println("top- Reveals the top scoreboard.");
		System.out.println("Have Fun !");
	}

	/*
	 * The method that controls the game
	 */
	private static boolean gameCountinue(){
		field = new MineField();
		result = 0;

		while (true){
			// Player stepped on mine -> save -> new game
			if(playerSteppedOnMine()){
				return true;
			}

			field.drawMineField();
			playerInput = userEnterdMove();

			//Player command "top" -> print rankings
			if(playerInput.equals("top")){
				rank.showResult();
				continue;

			// Player command "restart" -> new game
			}else if(playerInput.equals("restart")){
				rank.recordScoreRegisterName(result);
				return true;

			//Player command exit -> save & exit
			}else if(playerInput.equals("exit")){
				rank.recordScoreRegisterName(result);
				return false;
			}
			// Player won the game / revealed mine -> save & continue
			else if(playerWonTheGame()){
				return true;
			}
		}
	}

	/*
	 * A method to check if the player chose a bomb
	 */
	private static boolean playerSteppedOnMine(){
		if(field.getExplosiveMine()) {
			System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
			rank.recordScoreRegisterName(result);
			return true;
		}
		return false;
	}

	/*
	 * A method to read the coordinates entered by player
	 */
	private static String userEnterdMove(){
		System.out.print("\nPlease enter your move(row col): ");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		return input;
	}

	/*
	 * A method to check if the player won the game
	 */
	private static boolean playerWonTheGame(){
		if(field.legalMoveString(playerInput)){
			result++;
			if (result == 35) {
				System.out.println("Congratulations you WON the game!");
				rank.recordScoreRegisterName(result);
				return true;
			}
		}
		return false;
	}
}
