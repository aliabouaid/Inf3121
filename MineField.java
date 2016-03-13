import java.util.Random;

class MineField {

	private final int ROW_MAX = 5;
	private final int COL_MAX = 10;
	private final int NR_OF_MINES_MAX = 15;
	private boolean[][] mines;
	private boolean[][] visible;
	private boolean explosiveMine;


	MineField(){
		mines = new boolean[ROW_MAX][COL_MAX];
		visible = new boolean[ROW_MAX][COL_MAX];
		explosiveMine = false;
		clearBoard();
		randomlyAllocateMines();
	}

	/*
	 * A method to initiate the board
	 */ 
	private void clearBoard(){
		for(int row = 0; row < ROW_MAX; row++){
			for(int col = 0; col < COL_MAX; col++){
				mines[row][col] = false;
				visible[row][col] = false;
			}
		}
	}

	/*
	 * A method to set 15 bombs randomly
	 */
	private void randomlyAllocateMines(){
		int counter = 0;
		int randomRow,randomCol;
		Random random = new Random();

		while(counter < NR_OF_MINES_MAX){
			randomRow = Math.abs(random.nextInt() % ROW_MAX);
			randomCol = Math.abs(random.nextInt() % COL_MAX);

			if(successSettingBomb(randomRow,randomCol)){
				counter++;
			}
		}
	}

	/*
	 * A method to check if the random chosen field already contains a bomb
	 */
	private boolean successSettingBomb(int randomRow, int randomCol) {
		if(!(mines[randomRow][randomCol])){
			mines[randomRow][randomCol] = true;
			return true;
		}
		return false;
	}

	/*
	* Sets up the Minefield with MAX_ROW * MAX_COL size
	* and print the char acordingly to position in field
	*/
	public void drawMineField() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		for(int row = 0; row < ROW_MAX; row++){
			System.out.print(row + " |");
			for(int col = 0; col < COL_MAX; col++){
				System.out.print(" " + drawChar(row, col));
			}
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}

	/*
	 * A method to print chars all over the board to show
	 * the state of each field
	 */
	private char drawChar(int row, int col) {
		int count = 0;
		// a field to show the content
		if(visible[row][col]){
			// a bomb
			if(mines[row][col]){
				return '*';
			}
			// normal field --> show number of bombs in the neighbors
			count = checkFieldHasExplosiveNeighbors(row, col);
		}else{ // the field's content is not to be shown
			// show the already chosen fields
			if(explosiveMine){
				return '-';
		
			}else{
				// the field's not chosen
				return '?';
			}
		}
		return (char)(count + '0');
	}

	// A method that counts the number of bombs in the neighbor fields
	private int checkFieldHasExplosiveNeighbors(int row, int col){
		int count = 0;
		for(int irow = (row - 1); irow <= (row + 1); irow++){
			for(int icol = (col - 1); icol <= (col + 1); icol++){
				if(legalSoCountNeighbor(irow, icol)){
					count++;
				}
			}
		}
		return count;
	}

	/* 
	 * A method to check if the coordinators are in the legal range
	 * of the board
	 */
	private boolean legalSoCountNeighbor(int irow, int icol){
		if(icol >= 0 && icol < COL_MAX && irow >= 0 && irow < ROW_MAX){
			if(mines[irow][icol]){
				return true;
			}
		}
		return false;
	}

	/*
	 * A method to split the coordinates and check if they are legal
	 */
	public boolean legalMoveString(String input){
		String[] separated = input.split(" ");
		int row = 0;
		int col = 0;
		if(tryToSeperateStringToInt(separated)){
			row = Integer.parseInt(separated[0]);
			col = Integer.parseInt(separated[1]);
		}else{
			return false;
		}

		if(legalMoveValue(row, col)){
			return true;
		}
		return false;
	}

	/*
	 * A help method to check if the coordinates are legal
	 */
	private boolean tryToSeperateStringToInt(String[] separated){
		try{
			int row = Integer.parseInt(separated[0]);
			int col = Integer.parseInt(separated[1]);
			if(row < 0 || col < 0 || row >= ROW_MAX || col >= COL_MAX){
				throw new java.io.IOException();
			}
		}catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}
		return true;
	}

	/*
	 * Method to execute moves
	 */
	private boolean legalMoveValue(int row, int col) {
		// The field is already chosen
		if(visible[row][col]){
			System.out.println("You stepped in allready revealed area!");
			return false;

		}
		// set the field to visible
		else{
			visible[row][col] = true;
		}
		// it is a bomb
		if(mines[row][col]){
			setVisibleMineLikeTrueDrawField();
			return false;
		}
		return true;
	}

	// A method to show all the bombs when the player chooses a bomb
	private void setVisibleMineLikeTrueDrawField(){
		for(int row = 0; row < ROW_MAX; row++){
			for(int col = 0; col < COL_MAX; col++){
				if(mines[row][col]){
					visible[row][col] = true;
				}
			}
		}
		explosiveMine = true;
		drawMineField();
	}

	public boolean getExplosiveMine(){
		return explosiveMine;
	}
}
