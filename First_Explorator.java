package gj.stratego.player.capecchi;

/**
 * First Explorator class.
 * @author tommasocapecchi
 *
 */

public class First_Explorator {
	
	String fs;
	int rowB;
	int columnB;
	int rowR;
	int columnR;
	String[][] positionB = new String[10][10];
	String[][] positionR = new String[10][10];
	int prevDirection = 0;
	public static int value = 2;
	int enemieF = 0;
	int enemieB = 0;
	int enemieL = 0;
	int enemieR = 0;
	boolean isEliminated_Red = false;
	boolean isEliminated_Blue = false;
	/**
	 * Default constructor which sets the different properties
	 * of the class to their defaults values.
	 */
	public First_Explorator(){
		fs = "FS";
		rowB = 9;
		columnB = 2;
		rowR = 0;
		columnR = 6;
		positionB[rowB][columnB] = fs;
		positionR[rowR][columnR] = fs;
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
	 * @return the preview direction of the piece.
	 */
	public int getPrevDirection() {
		return prevDirection;
	}
	/**
	 * Sets the preview direction of the piece.
	 * @param prevDirection: 1 = forward; 2 = backward; 3 = left; 4 = right;
	 */
	public void setPrevDirection(int prevDirection) {
		this.prevDirection = prevDirection;
	}
	/**
	 * 
	 * @return the number of the possible moves that the piece can do forward before collide with an enemie.
	 */
	public int getEnemieF() {
		return enemieF;
	}
	/**
	 * Sets the number of the possible moves that the piece can do forward before collide with an enemie.
	 * @param enemieF is the number of the possible moves forward.
	 */
	public void setEnemieF(int enemieF) {
		this.enemieF = enemieF;
	}
	/**
	 * 
	 * @return the number of the possible moves that the piece can do backward before collide with an enemie.
	 */
	public int getEnemieB() {
		return enemieB;
	}

	/**
	 * Sets the number of the possible moves that the piece can do backward before collide with an enemie.
	 * @param enemieB is the number the of possible moves backward.
	 */
	public void setEnemieB(int enemieB) {
		this.enemieB = enemieB;
	}
	/**
	 * 
	 * @return the number of the possible moves that the piece can do to the left before collide with an enemie.
	 */
	public int getEnemieL() {
		return enemieL;
	}
	/**
	 * Sets the number of the possible moves that the piece can do to the left before collide with an enemie.
	 * @param enemieL is the number the of possible moves to the left.
	 */
	public void setEnemieL(int enemieL) {
		this.enemieL = enemieL;
	}
	/**
	 * 
	 * @return the number of the possible moves that the piece can do to the right before collide with an enemie.
	 */
	public int getEnemieR() {
		return enemieR;
	}
	/**
	 * Sets the number of the possible moves that the piece can do to the right before collide with an enemie.
	 * @param enemieR is the number the of possible moves to the right.
	 */
	public void setEnemieR(int enemieR) {
		this.enemieR = enemieR;
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
	public String getFs() {
		return fs;
	}
	/**
	 * Sets the name of the piece.
	 * @param fs = "FS"
	 */
	public void setFs(String fs) {
		this.fs = fs;
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
	/**
	 * It represents the value of the piece.
	 * @return an integer that represents the value of the piece. 
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Sets the value of the piece.
	 * @param value = 2
	 */
	public void setValue(int value) {
		First_Explorator.value = value;
	}
	
}
