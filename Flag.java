package gj.stratego.player.capecchi;

public class Flag {

	String fl;
	int rowB;
	int columnB;
	int rowR;
	int columnR;
	String[][] positionB = new String[10][10];
	String[][] positionR = new String[10][10];
	boolean isEliminated_Red = false;
	boolean isEliminated_Blue = false;
	public Flag() {
		fl = "FL";
		rowB = 9;
		columnB = 4;
		rowR = 0;
		columnR = 4;
		positionB[rowB][columnB] = fl;
		positionR[rowR][columnR] = fl;
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

	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
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
	
}
