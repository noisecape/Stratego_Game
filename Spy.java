package gj.stratego.player.capecchi;

public class Spy {
	
	String sp;
	String[][] positionB = new String[10][10];
	String[][] positionR = new String[10][10];
	int rowB;
	int columnB;
	int rowR;
	int columnR;
	static int value = 1;
	int enemieF = 0;
	int enemieB = 0;
	int enemieL = 0;
	int enemieR = 0;
	int prevDirection = 0;
	boolean isEliminated_Red = false;
	boolean isEliminated_Blue = false;
	public Spy(){
		sp = "SP";
		rowB = 8;
		columnB = 4;
		rowR = 1;
		columnR = 4;
		positionB[rowB][columnB] = sp;
		positionR[rowR][columnR] = sp;
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


	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
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
		Spy.value = value;
	}
	
}
