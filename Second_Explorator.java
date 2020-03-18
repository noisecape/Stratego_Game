package gj.stratego.player.capecchi;

public class Second_Explorator {
	String ss;
	String[][] positionB = new String[10][10];
	String[][] positionR = new String[10][10];
	int rowB;
	int columnB;
	int rowR;
	int columnR;
	static int value = 2;
	int enemieF = 0;
	int enemieB = 0;
	int enemieL = 0;
	int enemieR = 0;
	int prevDirection = 0;
	boolean isEliminated_Red = false;
	boolean isEliminated_Blue = false;
	public Second_Explorator(){
		ss = "SS";
		rowB = 9;
		columnB = 6;
		rowR = 0;
		columnR = 2;
		positionB[rowB][columnB] = ss;
		positionR[rowR][columnR] = ss;
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

	public int getPrevDirection() {
		return prevDirection;
	}

	public void setPrevDirection(int prevDirection) {
		this.prevDirection = prevDirection;
	}

	public int getEnemieF() {
		return enemieF;
	}

	public void setEnemieF(int enemieF) {
		this.enemieF = enemieF;
	}

	public int getEnemieB() {
		return enemieB;
	}

	public void setEnemieB(int enemieB) {
		this.enemieB = enemieB;
	}

	public int getEnemieL() {
		return enemieL;
	}

	public void setEnemieL(int enemieL) {
		this.enemieL = enemieL;
	}

	public int getEnemieR() {
		return enemieR;
	}

	public void setEnemieR(int enemieR) {
		this.enemieR = enemieR;
	}

	public int getRowB() {
		return rowB;
	}

	public void setRowB(int rowB) {
		this.rowB = rowB;
	}

	public int getColumnB() {
		return columnB;
	}

	public void setColumnB(int columnB) {
		this.columnB = columnB;
	}

	public int getRowR() {
		return rowR;
	}
	
	public void setRowR(int rowR) {
		this.rowR = rowR;
	}

	public int getColumnR() {
		return columnR;
	}

	public void setColumnR(int columnR) {
		this.columnR = columnR;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String[][] getPositionB() {
		return positionB;
	}

	public void setPositionB(String[][] positionB) {
		this.positionB = positionB;
	}

	public String[][] getPositionR() {
		return positionR;
	}

	public void setPositionR(String[][] positionR) {
		this.positionR = positionR;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		Second_Explorator.value = value;
		System.out.println(Second_Explorator.value);
	}
}
