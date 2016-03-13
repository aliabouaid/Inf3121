import java.util.Scanner;

public class Ranking{

	private final int MAX_PEOPLE_LIMIT = 5;
	private String[] nameOfPlayer;
	private int[] record;
	private int lastPlayed;

	Ranking(){
		nameOfPlayer = new String[MAX_PEOPLE_LIMIT];
		record = new int[MAX_PEOPLE_LIMIT];
		lastPlayed = 0;
	}

	/*
	 * A method to record the score and register
	 * the user's name if he was one of the 5 with
	 * top score
	 */
	public void recordScoreRegisterName(int result){
		String playerName = recordNameOfPlayer();
		// result not higher than top 5
		if((lastPlayed == MAX_PEOPLE_LIMIT) && record[MAX_PEOPLE_LIMIT-1] > result){
			System.out.println("\n Sorry you cannot enter top " + (MAX_PEOPLE_LIMIT) + " players");
			return;
		}
		
		// player number <= 5, record the name and score
		if(lastPlayed <= MAX_PEOPLE_LIMIT){
			nameOfPlayer[lastPlayed] = playerName;
			record[lastPlayed] = result;
			lastPlayed++;

		}
		
		sortInDecsendingOrder();
		showResult();
	}

	/*
	 * A method to record the players name
	 */
	private String recordNameOfPlayer() {
		System.out.print("\nPlease enter your name - ");
		Scanner in = new Scanner(System.in);
		String newNameOfPlayer = in.nextLine();
		return newNameOfPlayer;
	}

	/*
	 * A method to print the results
	 */
	public void showResult() {
		// no score registered yet
		if(lastPlayed == 0){
			System.out.println("Still no results");
			return;
		}

		System.out.println("N\tname\tresult");
		for(int i = 0; i < lastPlayed; i++){
			System.out.println((i + 1) + "\t" + nameOfPlayer[i] + "\t" + record[i]);
		}
	}

	/*
	 * A method to sort the scores in descending order
	 */
	private void sortInDecsendingOrder(){
		boolean unsorted;
		// no need for sorting
		if(lastPlayed < 2){
			return;
		}
		unsorted = true;
		while(unsorted){
			unsorted = false;
			if(theNextisBiggerthanThis()){
				unsorted = true;
			}
		}
	}

	// A method to check the order of the scores
	private boolean theNextisBiggerthanThis(){
		for(int i = 0; i < (lastPlayed - 1); i++){
			if(record[i + 1] > record[i]){
				swapInDecsendingOrder(i);
				return true;
			}
		}
		return false;
	}

	/*
	 * A method to swap record and name
	 */
	private void swapInDecsendingOrder(int i){
		int swapR = record[i];
		record[i] = record[i + 1];
		record[i + 1] = swapR;
		String swapN = nameOfPlayer[i];
		nameOfPlayer[i] = nameOfPlayer[i + 1];
		nameOfPlayer[i + 1] = swapN;
	}
}
