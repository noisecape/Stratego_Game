package gj.stratego.player.capecchi;
/**
 * First Bomb class.
 * @author tommasocapecchi
 *
 */
public class First_Bomb {

	String fb;
	int rowB = 0;
	int columnB = 0;
	int rowR = 0;
	int columnR = 0;
	String[][] positionB = new String[10][10];
	String[][] positionR = new String[10][10];
	boolean isEliminated_Red = false;
	boolean isEliminated_Blue = false;
	/**
	 * Default constructor which sets the different properties
	 * of the class to their defaults values.
	 */
	public First_Bomb(){
		fb = "FB";
		rowB = 9;
		columnB = 3;
		rowR = 0;
		columnR = 5;
		positionB[rowB][columnB] = fb;
		positionR[rowR][columnR] = fb;
	}
	
	public boolean isEliminated_Red() {
		return isEliminated_Red;
	}

	public void setEliminated_Red(boolean isEliminated_Red) {
		this.isEliminated_Red = isEliminated_Red;
	}

	public boolean isEliminated_Blue() {
		return isEliminated_Blue;
	}

	public void setEliminated_Blue(boolean isEliminated_Blue) {
		this.isEliminated_Blue = isEliminated_Blue;
	}

	/**
	 * 
	 * @return an integer which represents the x coordinate of the blue piece into the game board.
	 */
	public int getRowB() {
		return rowB;
	}
	/**
	 * Sets the initial row position of the blue piece.
	 * @param rowB is the integer that represents the initial row position of the blue piece.
	 */
	public void setRowB(int rowB) {
		this.rowB = rowB;
	}
	/**
	 * 
	 * @return an integer which represents the y coordinate of the blue piece into the game board.
	 */
	public int getColumnB() {
		return columnB;
	}
	/**
	 * Sets the initial column position of the blue piece.
	 * @param columnB is the integer that represents the initial column position of the blue piece.
	 */
	public void setColumnB(int columnB) {
		this.columnB = columnB;
	}
	/**
	 * 
	 * @return an integer which represents the x coordinate of the red piece into the game board.
	 */
	public int getRowR() {
		return rowR;
	}
	/**
	 * Sets the initial row position of the red piece.
	 * @param rowR is the integer that represents the initial row position of the red piece.
	 */
	public void setRowR(int rowR) {
		this.rowR = rowR;
	}
	/**
	 * 
	 * @return an integer which represents the y coordinate of the red piece into the game board.
	 */
	public int getColumnR() {
		return columnR;
	}
	/**
	 * Sets the intial column position of the red piece.
	 * @param columnR is the integer that represents the initial row position of the red piece.
	 */
	public void setColumnR(int columnR) {
		this.columnR = columnR;
	}
	/**
	 * 
	 * @return the string that represents the name of the piece into the game board.
	 */
	public String getFb() {
		return fb;
	}
	/**
	 * Sets the name of the piece.
	 * @param fb = "FB"
	 */
	public void setFb(String fb) {
		this.fb = fb;
	}
	/**
	 * 
	 * @return an array of two positions: the first one represents the x coordinate, the second one represents the y coordinate.
	 */
	public String[][] getPositionB() {
		return positionB;
	}
	/**
	 * Sets the coordinates of the blue piece using an array of two integers: {x,y}.
	 * @param positionB is the array containing the coordinates of the blue piece.
	 */
	public void setPositionB(String[][] positionB) {
		this.positionB = positionB;
	}
	/**
	 * 
	 * @return an array of two positions: the first one represents the x coordinate, the second one represents the y coordinate.
	 */
	public String[][] getPositionR() {
		return positionR;
	}
	/**
	 * Sets the coordinates of the red piece using an array of two integers: {x,y}.
	 * @param positionR is the array containing the coordinates of hte red piece.
	 */
	public void setPositionR(String[][] positionR) {
		this.positionR = positionR;
	}
	
}
