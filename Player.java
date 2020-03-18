package gj.stratego.player.capecchi;

import gj.stratego.player.Player;

/**
 * Class of a Stratego player.
 * 
 * @author tommasocapecchi
 *
 */

public class CapecchiPlayer implements Player {

	boolean isRed;

	String board[][];

	int[][] enemieBoard;

	int moveArray[];

	int[] selectedPlayer;

	String lastPieceMoved = "";

	int myDirection = 0;

	String[] piecesMovable;

	First_Bomb fb;
	First_Explorator fs;
	First_Miner fm;
	Flag fl;
	General ge;
	Marshal ma;
	Second_Bomb sb;
	Second_Explorator ss;
	Second_Miner sm;
	Spy sp;

	/**
	 * Default constructor which create all the pieces and all the arrays in
	 * order to play the game.
	 */
	public CapecchiPlayer() {
		fb = new First_Bomb();
		fs = new First_Explorator();
		fm = new First_Miner();
		fl = new Flag();
		ge = new General();
		ma = new Marshal();
		sb = new Second_Bomb();
		ss = new Second_Explorator();
		sm = new Second_Miner();
		sp = new Spy();
		piecesMovable = new String[7];
		selectedPlayer = new int[2];
		board = new String[10][10];
		enemieBoard = new int[4][10];
		moveArray = new int[4];
		start(isRed);
		move();
	}

	/**
	 * Method used to reload all the initials positions of all the red pieces
	 * ones the game is over.
	 */
	public void reloadPositionsR() {
		sp = new Spy();
		sp.setRowR(1);
		sp.setColumnR(4);

		ge = new General();
		ge.setRowR(0);
		ge.setColumnR(1);

		fl = new Flag();
		fl.setRowR(0);
		fl.setColumnR(4);

		ma = new Marshal();
		ma.setRowR(2);
		ma.setColumnR(4);

		ss = new Second_Explorator();
		ss.setRowR(0);
		ss.setColumnR(2);

		sb = new Second_Bomb();
		sb.setRowR(0);
		sb.setColumnR(3);

		sm = new Second_Miner();
		sm.setRowR(1);
		sm.setColumnR(3);

		fs = new First_Explorator();
		fs.setRowR(0);
		fs.setColumnR(6);

		fb = new First_Bomb();
		fb.setRowR(0);
		fb.setColumnR(5);

		fm = new First_Miner();
		fm.setRowR(1);
		fm.setColumnR(5);
	}

	/**
	 * Method used to reload all the initials positions of all the blue pieces
	 * ones the game is over.
	 */
	public void reloadPositionsB() {
		sp = new Spy();
		sp.setRowB(8);
		sp.setColumnB(4);

		ge = new General();
		ge.setRowB(9);
		ge.setColumnB(7);

		fl = new Flag();
		fl.setRowB(9);
		fl.setColumnB(4);

		ma = new Marshal();
		ma.setRowB(7);
		ma.setColumnB(4);

		ss = new Second_Explorator();
		ss.setRowB(9);
		ss.setColumnB(6);

		sb = new Second_Bomb();
		sb.setRowB(9);
		sb.setColumnB(5);

		sm = new Second_Miner();
		sm.setRowB(8);
		sm.setColumnB(5);

		fs = new First_Explorator();
		fs.setRowB(9);
		fs.setColumnB(2);

		fb = new First_Bomb();
		fb.setRowB(9);
		fb.setColumnB(3);

		fm = new First_Miner();
		fm.setRowB(8);
		fm.setColumnB(3);
	}

	/**
	 * Method used to initialize the board which will be used to track all the
	 * pieces (both enemies and player's); it also marks the positions that
	 * can't be used by pieces to make their moves.
	 * 
	 * @param board
	 *            of the game.
	 */
	public void initBoard(String[][] board) {
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				board[r][c] = "0";
			}
		}
		board[4][2] = "L";
		board[4][3] = "L";
		board[5][2] = "L";
		board[5][3] = "L";
		board[4][6] = "L";
		board[4][7] = "L";
		board[5][6] = "L";
		board[5][7] = "L";
	}

	/**
	 * Method used to check if the current player is the first player to make a
	 * move.
	 */
	public void start(boolean isFirst) {
		if (isFirst) {
			reloadPositionsR();
			isRed = true;
			initBoard(board);
			redPosition();
			initPiecesMoveble();
		} else {
			reloadPositionsB();
			isRed = false;
			initBoard(board);
			bluePosition();
			initPiecesMoveble();
		}
	}

	/**
	 * Called if current player is red, it loads on the board the positions of
	 * all red pieces.
	 */
	private void redPosition() {
		board[fb.getRowR()][fb.getColumnR()] = fb.getFb();
		board[fs.getRowR()][fs.getColumnR()] = fs.getFs();
		board[fm.getRowR()][fm.getColumnR()] = fm.getFm();
		board[fl.getRowR()][fl.getColumnR()] = fl.getFl();
		board[ge.getRowR()][ge.getColumnR()] = ge.getGe();
		board[ma.getRowR()][ma.getColumnR()] = ma.getMa();
		board[sb.getRowR()][sb.getColumnR()] = sb.getSb();
		board[ss.getRowR()][ss.getColumnR()] = ss.getSs();
		board[sm.getRowR()][sm.getColumnR()] = sm.getSm();
		board[sp.getRowR()][sp.getColumnR()] = sp.getSp();
	}

	/**
	 * Called if current player is blue, it loads on the board the positions of
	 * all blue pieces.
	 */
	private void bluePosition() {
		board[fb.getRowB()][fb.getColumnB()] = fb.getFb();
		board[fs.getRowB()][fs.getColumnB()] = fs.getFs();
		board[fm.getRowB()][fm.getColumnB()] = fm.getFm();
		board[fl.getRowB()][fl.getColumnB()] = fl.getFl();
		board[ge.getRowB()][ge.getColumnB()] = ge.getGe();
		board[ma.getRowB()][ma.getColumnB()] = ma.getMa();
		board[sb.getRowB()][sb.getColumnB()] = sb.getSb();
		board[ss.getRowB()][ss.getColumnB()] = ss.getSs();
		board[sm.getRowB()][sm.getColumnB()] = sm.getSm();
		board[sp.getRowB()][sp.getColumnB()] = sp.getSp();
	}

	/**
	 * Possible values of piece:
	 * "FL","FB","SB","MA","GE","FM","SM","FS","SM","SP".
	 * 
	 * @return row and column in which the pieces will be positioned into the
	 *         board.
	 */
	public int[] position(String piece) {
		int[] positions = new int[2];
		if (isRed) {
			if (piece.equals(fb.getFb())) {
				positions[0] = fb.getRowR();
				positions[1] = fb.getColumnR();
			} else if (piece.equals(fs.getFs())) {
				positions[0] = fs.getRowR();
				positions[1] = fs.getColumnR();
			} else if(piece.equals(fm.getFm())) {
				positions[0] = fm.getRowR();
				positions[1] = fm.getColumnR();
			} else if (piece.equals(fl.getFl())) {
				positions[0] = fl.getRowR();
				positions[1] = fl.getColumnR();
			} else if (piece.equals(ge.getGe())) {
				positions[0] = ge.getRowR();
				positions[1] = ge.getColumnR();
			} else if (piece.equals(ma.getMa())) {
				positions[0] = ma.getRowR();
				positions[1] = ma.getColumnR();
			} else if (piece.equals(sb.getSb())) {
				positions[0] = sb.getRowR();
				positions[1] = sb.getColumnR();
			} else if (piece.equals(ss.getSs())) {
				positions[0] = ss.getRowR();
				positions[1] = ss.getColumnR();
			} else if (piece.equals(sm.getSm())) {
				positions[0] = sm.getRowR();
				positions[1] = sm.getColumnR();
			} else {
				positions[0] = sp.getRowR();
				positions[1] = sp.getColumnR();
			}
		} else {
			if (piece.equals(fb.getFb())) {
				positions[0] = fb.getRowB();
				positions[1] = fb.getColumnB();
			} else if (piece.equals(fs.getFs())) {
				positions[0] = fs.getRowB();
				positions[1] = fs.getColumnB();
			} else if (piece.equals(fm.getFm())) {
				positions[0] = fm.getRowB();
				positions[1] = fm.getColumnB();
			} else if (piece.equals(fl.getFl())) {
				positions[0] = fl.getRowB();
				positions[1] = fl.getColumnB();
			} else if (piece.equals(ge.getGe())) {
				positions[0] = ge.getRowB();
				positions[1] = ge.getColumnB();
			} else if (piece.equals(ma.getMa())) {
				positions[0] = ma.getRowB();
				positions[1] = ma.getColumnB();
			} else if (piece.equals(sb.getSb())) {
				positions[0] = sb.getRowB();
				positions[1] = sb.getColumnB();
			} else if (piece.equals(ss.getSs())) {
				positions[0] = ss.getRowB();
				positions[1] = ss.getColumnB();
			} else if (piece.equals(sm.getSm())) {
				positions[0] = sm.getRowB();
				positions[1] = sm.getColumnB();
			} else {
				positions[0] = sp.getRowB();
				positions[1] = sp.getColumnB();
			}
		}
		return positions;
	}

	/**
	 * Method that allows a player to see the pieces of the enemie.
	 */
	public void viewPositions(int[][] position) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 10; j++) {
				if (position[i][j] != 0) {
					enemieBoard[i][j] = 1;
				}
			}
		}
		copyEnemiePos(enemieBoard);
	}

	/**
	 * It copies the enemies positions on the main board of the game.
	 * 
	 * @param enemie
	 *            is the matrix of enemies positions
	 */
	public void copyEnemiePos(int[][] enemie) {
		if (isRed) {
			int r = 0;
			for (int i = 6; i < 10; i++) {
				int c = 0;
				for (int j = 0; j < 10; j++) {
					if (enemie[r][c] == 1) {
						board[i][j] = "1";
					}
					c++;
				}
				r++;
			}
		} else {
			int r = 0;
			for (int i = 0; i < 4; i++) {
				int c = 0;
				for (int j = 0; j < 10; j++) {
					if (enemie[r][c] == 1) {
						board[i][j] = "1";
					}
					c++;
				}
				r++;
			}
		}
	}

	/**
	 * Checks if there is a piece movable left.
	 * 
	 * @return true or false.
	 */
	public boolean allNull() {
		boolean rst = true;
		for (int i = 0; i < piecesMovable.length; i++) {
			if (piecesMovable[i] != null) {
				rst = false;
			}
		}
		return rst;
	}

	/**
	 * Method used to communicate the moves of the pieces.
	 */
	public int[] move() {
		if (allNull()) {
			return null;
		} else {
			moveArray = new int[4];
			int[] obstaclesF = checkObstaclesF();
			int[] obstaclesB = checkObstaclesB();
			int[] obstaclesL = checkObstaclesL();
			int[] obstaclesR = checkObstaclesR();
			setPossibleEnemieF(obstaclesF);
			setPossibleEnemieB(obstaclesB);
			setPossibleEnemieL(obstaclesL);
			setPossibleEnemieR(obstaclesR);
			if (isRed) {
				computeMoveRed(obstaclesF, obstaclesB, obstaclesL, obstaclesR);
			} else {
				computeMoveBlue(obstaclesF, obstaclesB, obstaclesL, obstaclesR);
			}
			return moveArray;
		}
	}

	void printMatrix(String board[][]) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(" | " + board[i][j] + " | ");
			}
			System.out.println();
		}
	}

	/**
	 * Method that compute the direction in which a blue piece has to move: as
	 * soon as the algorithm finds a piece in which his direction there's an
	 * enemie, it communicates to moveArray row, column, direction and number of
	 * steps.
	 * 
	 * @param f
	 *            contains all the possible forward moves of each pieces
	 * @param b
	 *            contains all the possible backward moves of each pieces
	 * @param l
	 *            contains all the possible left moves of each pieces
	 * @param r
	 *            contains all the possible right moves of each pieces
	 */
	public void computeMoveBlue(int[] f, int[] b, int[] l, int[] r) {
		boolean forward = false;
		boolean backward = false;
		boolean left = false;
		boolean right = false;
		for (int i = 0; i < piecesMovable.length; i++) {
			if (piecesMovable[i] != null) {
				if (checkPrevDirection(i) != 1 && checkEnemieF(i)) {
					lastPieceMoved = getPiece(i);
					forward = true;
					int row = getRowBlue(i);
					int column = getColumnBlue(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 1;
					moveArray[3] = 1;
					setPrevDirectionF(i);
					reloadCoordinatesB(i, 1);
					break;
				} else if (checkPrevDirection(i) != 2 && checkEnemieB(i)) {
					myDirection = 2;
					lastPieceMoved = getPiece(i);
					backward = true;
					int row = getRowBlue(i);
					int column = getColumnBlue(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 2;
					moveArray[3] = 1;
					setPrevDirectionB(i);
					reloadCoordinatesB(i, 2);
					break;
				} else if (checkPrevDirection(i) != 3 && checkEnemieL(i)) {
					myDirection = 3;
					lastPieceMoved = getPiece(i);
					left = true;
					int row = getRowBlue(i);
					int column = getColumnBlue(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 3;
					moveArray[3] = 1;
					setPrevDirectionL(i);
					reloadCoordinatesB(i, 3);
					break;
				} else if (checkPrevDirection(i) != 4 && checkEnemieR(i)) {
					myDirection = 4;
					lastPieceMoved = getPiece(i);
					right = true;
					int row = getRowBlue(i);
					int column = getColumnBlue(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 4;
					moveArray[3] = 1;
					setPrevDirectionR(i);
					reloadCoordinatesB(i, 4);
					break;
				}
			}
		}
		noEnemieFoundCheckBlue(forward, backward, left, right);
	}

	private int getColumnBlue(int piece) {
		int rst = 0;
		switch (piece) {
		case 0:
			if (ma.isEliminated_Blue == false) {
				rst = ma.getColumnB();
			}
			break;
		case 1:
			if (fm.isEliminated_Blue == false) {
				rst = fm.getColumnB();
			}
			break;
		case 2:
			if (sm.isEliminated_Blue == false) {
				rst = sm.getColumnB();
			}
			break;
		case 3:
			if (fs.isEliminated_Blue == false) {
				rst = fs.getColumnB();
			}
			break;
		case 4:
			if (ss.isEliminated_Blue == false) {
				rst = ss.getColumnB();
			}
			break;
		case 5:
			if (ge.isEliminated_Blue == false) {
				rst = ge.getColumnB();
			}
			break;
		case 6:
			if (sp.isEliminated_Blue == false) {
				rst = sp.getColumnB();
			}
			break;
		}
		return rst;
	}

	private int getRowBlue(int piece) {
		int rst = -1;
		switch (piece) {
		case 0:
			if (ma.isEliminated_Blue == false) {
				rst = ma.getRowB();
			}
			break;
		case 1:
			if (fm.isEliminated_Blue == false) {
				rst = fm.getRowB();
			}
			break;
		case 2:
			if (sm.isEliminated_Blue == false) {
				rst = sm.getRowB();
			}
			break;
		case 3:
			if (fs.isEliminated_Blue == false) {
				rst = fs.getRowB();
			}
			break;
		case 4:
			if (ss.isEliminated_Blue == false) {
				rst = ss.getRowB();
			}
			break;
		case 5:
			if (ge.isEliminated_Blue == false) {
				rst = ge.getRowB();
			}
			break;
		case 6:
			if (sp.isEliminated_Blue == false) {
				rst = sp.getRowB();
			}
			break;
		}
		return rst;
	}

	/**
	 * It sets the forward direction as the direction in which for 3 rounds a
	 * certain piece can't be moved.
	 * 
	 * @param i
	 *            is the direction in which the pieces has been moved.
	 */
	public void setPrevDirectionF(int i) {
		switch (i) {
		case 0:
			ma.setPrevDirection(2);
			break;
		case 1:
			fm.setPrevDirection(2);
			break;
		case 2:
			sm.setPrevDirection(2);
			break;
		case 3:
			fs.setPrevDirection(2);
			break;
		case 4:
			ss.setPrevDirection(2);
			break;
		case 5:
			ge.setPrevDirection(2);
			break;
		case 6:
			sp.setPrevDirection(2);
			break;
		}
	}

	/**
	 * It sets the backward direction as the direction in which for 3 rounds a
	 * certain piece can't be moved.
	 * 
	 * @param i
	 *            is the direction in which the pieces has been moved.
	 */
	public void setPrevDirectionB(int i) {
		switch (i) {
		case 0:
			ma.setPrevDirection(1);
			break;
		case 1:
			fm.setPrevDirection(1);
			break;
		case 2:
			sm.setPrevDirection(1);
			break;
		case 3:
			fs.setPrevDirection(1);
			break;
		case 4:
			ss.setPrevDirection(1);
			break;
		case 5:
			ge.setPrevDirection(1);
			break;
		case 6:
			sp.setPrevDirection(1);
			break;
		}
	}

	/**
	 * It sets the left direction as the direction in which for 3 rounds a
	 * certain piece can't be moved.
	 * 
	 * @param i
	 *            is the direction in which the pieces has been moved.
	 */
	public void setPrevDirectionL(int i) {
		switch (i) {
		case 0:
			ma.setPrevDirection(4);
			break;
		case 1:
			fm.setPrevDirection(4);
			break;
		case 2:
			sm.setPrevDirection(4);
			break;
		case 3:
			fs.setPrevDirection(4);
			break;
		case 4:
			ss.setPrevDirection(4);
			break;
		case 5:
			ge.setPrevDirection(4);
			break;
		case 6:
			sp.setPrevDirection(4);
			break;
		}
	}

	/**
	 * It sets the right direction as the direction in which for 3 rounds a
	 * certain piece can't be moved.
	 * 
	 * @param i
	 *            is the direction in which the pieces has been moved.
	 */
	public void setPrevDirectionR(int i) {
		switch (i) {
		case 0:
			ma.setPrevDirection(3);
			break;
		case 1:
			fm.setPrevDirection(3);
			break;
		case 2:
			sm.setPrevDirection(3);
			break;
		case 3:
			fs.setPrevDirection(3);
			break;
		case 4:
			ss.setPrevDirection(3);
			break;
		case 5:
			ge.setPrevDirection(3);
			break;
		case 6:
			sp.setPrevDirection(3);
			break;
		}
	}

	/**
	 * It checks the preview direction of a certain piece.
	 * 
	 * @param i
	 *            is the direction in which the pieces has been moved.
	 * @return rst is the preview direction.
	 */
	public int checkPrevDirection(int i) {
		int rst = 0;
		switch (i) {
		case 0:
				rst = ma.getPrevDirection();
				break;
		case 1:
				rst = fm.getPrevDirection();
				break;
		case 2:
				rst = sm.getPrevDirection();
				break;
		case 3:
				rst = fs.getPrevDirection();
				break;
		case 4:
				rst = ss.getPrevDirection();
				break;
		case 5:
				rst = ge.getPrevDirection();
				break;
		case 6:
				rst = sp.getPrevDirection();
				break;
		}
		return rst;
	}

	/**
	 * Compute the move for the selected blue piece that has no enemie in every
	 * direction. It check all of the movable pieces and it chooses the first
	 * that isn't null and it has free direction into the board.
	 * 
	 * @param f
	 *            is true if there is an enemie in forward direction of a piece
	 * @param b
	 *            is true if there is an enemie in backward direction of a piece
	 * @param l
	 *            is true if there is an enemie in left direction of a piece
	 * @param r
	 *            is true if there is an enemie in right direction of a piece
	 */

	public void noEnemieFoundCheckBlue(boolean f, boolean b, boolean l, boolean r) {
		if (!f && !b && !l && !r) {
			boolean found = false;
			for (int i = 0; i < piecesMovable.length; i++) {
				if (piecesMovable[i] != null) {
					int prevDirection = checkPrevDirection(i);
					if (!found && prevDirection != 1) {
						int row = getRowBlue(i);
						int column = getColumnBlue(i);
						row = row - 1;
						if (row >= 0 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowBlue(i);
							moveArray[1] = getColumnBlue(i);
							moveArray[2] = 1;
							moveArray[3] = 1;
							setPrevDirectionF(i);
							reloadCoordinatesB(i, 1);
							break;
						}
					}
					if (!found && prevDirection != 2) {
						int row = getRowBlue(i);
						int column = getColumnBlue(i);
						row = row + 1;
						if (row <= 9 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowBlue(i);
							moveArray[1] = getColumnBlue(i);
							moveArray[2] = 2;
							moveArray[3] = 1;
							setPrevDirectionB(i);
							reloadCoordinatesB(i, 2);
							break;
						}
					}
					if (!found && prevDirection != 3) {
						int row = getRowBlue(i);
						int column = getColumnBlue(i);
						column = column - 1;
						if (column >= 0 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowBlue(i);
							moveArray[1] = getColumnBlue(i);
							moveArray[2] = 3;
							moveArray[3] = 1;
							setPrevDirectionL(i);
							reloadCoordinatesB(i, 3);
							break;
						}
					}
					if (!found && prevDirection != 4) {
						int row = getRowBlue(i);
						int column = getColumnBlue(i);
						column = column + 1;
						if (column <= 9 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowBlue(i);
							moveArray[1] = getColumnBlue(i);
							moveArray[2] = 4;
							moveArray[3] = 1;
							setPrevDirectionR(i);
							reloadCoordinatesB(i, 4);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Compute the move for the selected red piece that has no enemie in every
	 * direction. It check all of the movable pieces and it chooses the first
	 * that isn't null and it has free direction into the board.
	 * 
	 * @param f
	 *            is true if there is an enemie in forward direction of a piece
	 * @param b
	 *            is true if there is an enemie in backward direction of a piece
	 * @param l
	 *            is true if there is an enemie in left direction of a piece
	 * @param r
	 *            is true if there is an enemie in right direction of a piece
	 */

	public void noEnemieFoundCheckRed(boolean f, boolean b, boolean l, boolean r) {
		if (!f && !b && !l && !r) {
			boolean found = false;
			for (int i = 0; i < piecesMovable.length; i++) {
				if (piecesMovable[i] != null) {
					int prevDirection = checkPrevDirection(i);
					if (!found && prevDirection != 1) {
						int row = getRowRed(i);
						int column = getColumnRed(i);
						row = row + 1;
						if (piecesMovable[i] != null && row <= 9 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowRed(i);
							moveArray[1] = getColumnRed(i);
							moveArray[2] = 1;
							moveArray[3] = 1;
							setPrevDirectionF(i);
							reloadCoordinatesR(i, 1);
							break;
						}
					}
					if (!found && prevDirection != 2) {
						int row = getRowRed(i);
						int column = getColumnRed(i);
						row = row - 1;
						if (piecesMovable[i] != null && row >= 0 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowRed(i);
							moveArray[1] = getColumnRed(i);
							moveArray[2] = 2;
							moveArray[3] = 1;
							setPrevDirectionB(i);
							reloadCoordinatesR(i, 2);
							break;
						}
					}
					if (!found && prevDirection != 3) {
						int row = getRowRed(i);
						int column = getColumnRed(i);
						column = column + 1;
						if (piecesMovable[i] != null && column <= 9 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowRed(i);
							moveArray[1] = getColumnRed(i);
							moveArray[2] = 3;
							moveArray[3] = 1;
							setPrevDirectionL(i);
							reloadCoordinatesR(i, 3);
							break;
						}
					}
					if (!found && prevDirection != 4) {
						int row = getRowRed(i);
						int column = getColumnRed(i);
						column = column - 1;
						if (piecesMovable[i] != null && column >= 0 && board[row][column] == "0") {
							found = true;
							moveArray[0] = getRowRed(i);
							moveArray[1] = getColumnRed(i);
							moveArray[2] = 4;
							moveArray[3] = 1;
							setPrevDirectionR(i);
							reloadCoordinatesR(i, 4);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Method that compute the direction in which a red piece has to move: as
	 * soon as the algorithm finds a piece in which his direction there's an
	 * enemie, it communicates to moveArray row, column, direction and number of
	 * steps.
	 * 
	 * @param f
	 *            contains all the possible forward moves of each pieces
	 * @param b
	 *            contains all the possible backward moves of each pieces
	 * @param l
	 *            contains all the possible left moves of each pieces
	 * @param r
	 *            contains all the possible right moves of each pieces
	 */

	public void computeMoveRed(int[] f, int[] b, int[] l, int[] r) {
		boolean forward = false;
		boolean backward = false;
		boolean left = false;
		boolean right = false;
		for (int i = 0; i < piecesMovable.length; i++) {
			if (piecesMovable[i] != null) {
				if (checkPrevDirection(i) != 1 && checkEnemieF(i)) {
					lastPieceMoved = getPiece(i);
					forward = true;
					int row = getRowRed(i);
					int column = getColumnRed(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 1;
					moveArray[3] = 1;
					setPrevDirectionF(i);
					reloadCoordinatesR(i, 1);
					break;
				} else if (checkPrevDirection(i) != 2 && checkEnemieB(i)) {
					lastPieceMoved = getPiece(i);
					backward = true;
					int row = getRowRed(i);
					int column = getColumnRed(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 2;
					moveArray[3] = 1;
					setPrevDirectionB(i);
					reloadCoordinatesR(i, 2);
					break;
				} else if (checkPrevDirection(i) != 3 && checkEnemieL(i)) {
					myDirection = 3;
					lastPieceMoved = getPiece(i);
					left = true;
					int row = getRowRed(i);
					int column = getColumnRed(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 3;
					moveArray[3] = 1;
					setPrevDirectionL(i);
					reloadCoordinatesR(i, 3);
					break;
				} else if (checkPrevDirection(i) != 4 && checkEnemieR(i)) {
					myDirection = 4;
					lastPieceMoved = getPiece(i);
					right = true;
					int row = getRowRed(i);
					int column = getColumnRed(i);
					moveArray[0] = row;
					moveArray[1] = column;
					moveArray[2] = 4;
					moveArray[3] = 1;
					setPrevDirectionR(i);
					reloadCoordinatesR(i, 4);
					break;
				}
			}
		}
		noEnemieFoundCheckRed(forward, backward, left, right);

	}

	int getColumnRed(int piece) {
		int rst = 0;
		switch (piece) {
		case 0:
			if (ma.isEliminated_Red == false) {
				rst = ma.getColumnR();
			}
			break;
		case 1:
			if (fm.isEliminated_Red == false) {
				rst = fm.getColumnR();
			}
			break;
		case 2:
			if (sm.isEliminated_Red == false) {
				rst = sm.getColumnR();
			}
			break;
		case 3:
			if (fs.isEliminated_Red == false) {
				rst = fs.getColumnR();
			}
			break;
		case 4:
			if (ss.isEliminated_Red == false) {
				rst = ss.getColumnR();
			}
			break;
		case 5:
			if (ge.isEliminated_Red == false) {
				rst = ge.getColumnR();
			}
			break;
		case 6:
			if (sp.isEliminated_Red == false) {
				rst = sp.getColumnR();
			}
			break;
		}
		return rst;
	}

	int getRowRed(int piece) {
		int rst = 0;
		switch (piece) {
		case 0:
			if (ma.isEliminated_Red == false) {
				rst = ma.getRowR();
			}
			break;
		case 1:
			if (fm.isEliminated_Red == false) {
				rst = fm.getRowR();
			}
			break;
		case 2:
			if (sm.isEliminated_Red == false) {
				rst = sm.getRowR();
			}
			break;
		case 3:
			if (fs.isEliminated_Red == false) {
				rst = fs.getRowR();
			}
			break;
		case 4:
			if (ss.isEliminated_Red == false) {
				rst = ss.getRowR();
			}
			break;
		case 5:
			if (ge.isEliminated_Red == false) {
				rst = ge.getRowR();
			}
			break;
		case 6:
			if (sp.isEliminated_Red == false) {
				rst = sp.getRowR();
			}
			break;
		}
		return rst;
	}

	String getPiece(int piece) {
		String rst = null;
		switch (piece) {
		case 0:
				rst = ma.getMa();
			break;
		case 1:
				rst = fm.getFm();
			break;
		case 2:
				rst = sm.getSm();
			break;
		case 3:
				rst = fs.getFs();
			break;
		case 4:
				rst = ss.getSs();
			break;
		case 5:
				rst = ge.getGe();
			break;
		case 6:
				rst = sp.getSp();
			break;
		}
		return rst;
	}

	/**
	 * Checks if the is an enemie to the right of the piece.
	 * 
	 * @param i
	 *            represents the index of the movable piece.
	 * @return true if there is an enemie else it returns false.
	 */
	boolean checkEnemieR(int i) {
		boolean rst = false;
		int possibleMoveR = getPossibleMoveR(i);
		int count = 1;
		if (isRed) {
			int r = getRowRed(i);
			int c = getColumnRed(i);
			c = c - 1;
			while (c >= 0 && count <= possibleMoveR) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					c--;
					count++;
				}
			}
		} else {
			int r = getRowBlue(i);
			int c = getColumnBlue(i);
			c = c + 1;
			while (c <= 9 && count <= possibleMoveR) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					c++;
					count++;
				}
			}
		}
		return rst;
	}

	/**
	 * Checks if the is an enemie to the left of the piece.
	 * 
	 * @param i
	 *            represents the index of the movable piece.
	 * @return true if there is an enemie else it returns false.
	 */
	boolean checkEnemieL(int i) {
		boolean rst = false;
		int possibleMoveL = getPossibleMoveL(i);
		int count = 1;
		if (isRed) {
			int r = getRowRed(i);
			int c = getColumnRed(i);
			c = c + 1;
			while (c <= 9 && count <= possibleMoveL) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					c++;
					count++;
				}
			}
		} else {
			int r = getRowBlue(i);
			int c = getColumnBlue(i);
			c = c - 1;
			while (c >= 0 && count <= possibleMoveL) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					c--;
					count++;
				}
			}
		}
		return rst;
	}

	/**
	 * Checks if the is an enemie backward to the piece.
	 * 
	 * @param i
	 *            represents the index of the movable piece.
	 * @return true if there is an enemie else it returns false.
	 */
	boolean checkEnemieB(int i) {
		boolean rst = false;
		int possibleMoveB = getPossibleMoveB(i);
		int count = 1;
		if (isRed) {
			int r = getRowRed(i);
			int c = getColumnRed(i);
			r = r - 1;
			while (r >= 0 && count <= possibleMoveB) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					r--;
					count++;
				}
			}
		} else {
			int r = getRowBlue(i);
			int c = getColumnBlue(i);
			r = r + 1;
			while (r <= 9 && count <= possibleMoveB) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					r++;
					count++;
				}
			}
		}
		return rst;
	}

	/**
	 * Checks if the is an enemie forward to the piece.
	 * 
	 * @param i
	 *            represents the index of the movable piece.
	 * @return true if there is an enemie else it returns false.
	 */
	boolean checkEnemieF(int i) {
		boolean rst = false;
		int possibleMoveF = getPossibleMoveF(i);
		int count = 1;
		if (isRed) {
			int r = getRowRed(i);
			int c = getColumnRed(i);
			r = r + 1;
			while (r <= 9 && count <= possibleMoveF) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					r++;
					count++;
				}
			}
		} else {
			int r = getRowBlue(i);
			int c = getColumnBlue(i);
			r = r - 1;
			while (r >= 0 && count <= possibleMoveF) {
				if (board[r][c] == "1") {
					rst = true;
					break;
				} else {
					r--;
					count++;
				}
			}
		}
		return rst;
	}

	/**
	 * Checks how many moves a piece can do.
	 * 
	 * @param i
	 *            represents the index of the movable piece
	 * @return numbers of moves that a piece can do to the right.
	 */

	int getPossibleMoveR(int i) {
		int rst = 0;
		switch (i) {
		case 0:
				rst = ma.getEnemieR();
				break;
		case 1:
				rst = fm.getEnemieR();
				break;
		case 2:
				rst = sm.getEnemieR();
				break;
		case 3:
				rst = fs.getEnemieR();
				break;
		case 4:
				rst = ss.getEnemieR();
				break;
		case 5:
				rst = ge.getEnemieR();
				break;
		case 6:
				rst = sp.getEnemieR();
				break;
		}
		return rst;
	}

	/**
	 * Checks how many moves a piece can do.
	 * 
	 * @param i
	 *            represents the index of the movable piece
	 * @return numbers of moves that a piece can do to the left.
	 */
	int getPossibleMoveL(int i) {
		int rst = 0;
		switch (i) {
		case 0:
				rst = ma.getEnemieL();
				break;
		case 1:
				rst = fm.getEnemieL();
				break;
		case 2:
				rst = sm.getEnemieL();
				break;
		case 3:
				rst = fs.getEnemieL();
				break;
		case 4:
				rst = ss.getEnemieL();
				break;
		case 5:
				rst = ge.getEnemieL();
				break;
		case 6:
				rst = sp.getEnemieL();
				break;
		}
		return rst;
	}

	/**
	 * Checks how many moves a piece can do.
	 * 
	 * @param i
	 *            represents the index of the movable piece
	 * @return numbers of moves that a piece can do backward.
	 */
	int getPossibleMoveB(int i) {
		int rst = 0;
		switch (i) {
		case 0:
				rst = ma.getEnemieB();
				break;
		case 1:
				rst = fm.getEnemieB();
				break;
		case 2:
				rst = sm.getEnemieB();
				break;
		case 3:
				rst = fs.getEnemieB();
				break;
		case 4:
				rst = ss.getEnemieB();
				break;
		case 5:
				rst = ge.getEnemieB();
				break;
		case 6:
				rst = sp.getEnemieB();
				break;
		}
		return rst;
	}

	/**
	 * Checks how many moves a piece can do.
	 * 
	 * @param i
	 *            represents the index of the movable piece
	 * @return numbers of moves that a piece can do forward.
	 */
	int getPossibleMoveF(int i) {
		int rst = 0;
		switch (i) {
		case 0:
				rst = ma.getEnemieF();
				break;
		case 1:
				rst = fm.getEnemieF();
				break;
		case 2:
				rst = sm.getEnemieF();
				break;
		case 3:
				rst = fs.getEnemieF();
				break;
		case 4:
				rst = ss.getEnemieF();
				break;
		case 5:
				rst = ge.getEnemieF();
				break;
		case 6:
				rst = sp.getEnemieF();
				break;
		}
		return rst;
	}

	/**
	 * Sets the possible forward moves for each piece, which could contains an
	 * enemie.
	 * 
	 * @param obstF
	 *            is the array of all the pieces' possibles moves.
	 */
	void setPossibleEnemieF(int[] obstF) {
			ma.setEnemieF(obstF[0]);
			fm.setEnemieF(obstF[1]);
			sm.setEnemieF(obstF[2]);
			fs.setEnemieF(obstF[3]);
			ss.setEnemieF(obstF[4]);
			ge.setEnemieF(obstF[5]);
			sp.setEnemieF(obstF[6]);
	}

	/**
	 * Sets the possible backward moves for each piece, which could contains an
	 * enemie.
	 * 
	 * @param obstF
	 *            is the array of all the pieces' possibles moves.
	 */
	void setPossibleEnemieB(int[] obstB) {
			ma.setEnemieB(obstB[0]);
			fm.setEnemieB(obstB[1]);
			sm.setEnemieB(obstB[2]);
			fs.setEnemieB(obstB[3]);
			ss.setEnemieB(obstB[4]);
			ge.setEnemieB(obstB[5]);
			sp.setEnemieB(obstB[6]);
	}

	/**
	 * Sets the possible moves to the left for each piece, which could contains
	 * an enemie.
	 * 
	 * @param obstF
	 *            is the array of all the pieces' possibles moves.
	 */
	void setPossibleEnemieL(int[] obstL) {
			ma.setEnemieL(obstL[0]);
			fm.setEnemieL(obstL[1]);
			sm.setEnemieL(obstL[2]);
			fs.setEnemieL(obstL[3]);
			ss.setEnemieL(obstL[4]);
			ge.setEnemieL(obstL[5]);
			sp.setEnemieL(obstL[6]);
	}

	/**
	 * Sets the possible moves to the right for each piece, which could contains
	 * an enemie.
	 * 
	 * @param obstF
	 *            is the array of all the pieces' possibles moves.
	 */
	void setPossibleEnemieR(int[] obstR) {
			ma.setEnemieR(obstR[0]);
			fm.setEnemieR(obstR[1]);
			sm.setEnemieR(obstR[2]);
			fs.setEnemieR(obstR[3]);
			ss.setEnemieR(obstR[4]);
			ge.setEnemieR(obstR[5]);
			sp.setEnemieR(obstR[6]);
	}

	/**
	 * Counts how many moves a piece can do forward.
	 * 
	 * @return The array that contains each pieces possible moves.
	 */
	int[] checkObstaclesF() {
		int[] rst = new int[7];
		int count = 0;
		for (int i = 0; i < rst.length; i++) {
			if (piecesMovable[i] != null) {
				count = obstaclesF(piecesMovable[i]);
				rst[i] = count;
				count = 0;
			}
		}
		return rst;
	}

	/**
	 * Counts how many moves a piece can do to the left.
	 * 
	 * @return The array that contains each pieces possible moves.
	 */
	int[] checkObstaclesL() {
		int[] rst = new int[7];
		int count = 0;
		for (int i = 0; i < rst.length; i++) {
			if (piecesMovable[i] != null) {
				count = obstaclesL(piecesMovable[i]);
				rst[i] = count;
				count = 0;
			}
		}
		return rst;
	}

	/**
	 * Counts how many moves a piece can do backward.
	 * 
	 * @return The array that contains each pieces possible moves.
	 */
	int[] checkObstaclesB() {
		int[] rst = new int[7];
		int count = 0;
		for (int i = 0; i < rst.length; i++) {
			if (piecesMovable[i] != null) {
				count = obstaclesB(piecesMovable[i]);
				rst[i] = count;
				count = 0;
			}
		}
		return rst;
	}

	/**
	 * Counts how many moves a piece can do to the right.
	 * 
	 * @return The array that contains each pieces possible moves.
	 */
	int[] checkObstaclesR() {
		int[] rst = new int[7];
		int count = 0;
		for (int i = 0; i < rst.length; i++) {
			if (piecesMovable[i] != null) {
				count = obstaclesR(piecesMovable[i]);
				rst[i] = count;
				count = 0;
			}
		}
		return rst;
	}

	/**
	 * Counts how many moves a piece can do forward before find an obstacle.
	 * 
	 * @param piece
	 *            of the player
	 * @return rst is the number of moves that a piece can do before find an
	 *         obstacle.
	 */
	int obstaclesF(String piece) {
		int rst = 0;
		if (isRed) {
			int r = getRowRed(piece);
			int c = getColumnRed(piece);
			r = r + 1;
			while (piece != null && r <= 9) {
				if (board[r][c] == "0") {
					r = r + 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		} else {
			int r = getRowBlue(piece);
			int c = getColumnBlue(piece);
			r = r - 1;
			while (piece != null && r >= 0) {
				if (board[r][c] == "0") {
					r = r - 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		}
		return rst;
	}

	/**
	 * Counts how many moves a piece can do backward before find an obstacle.
	 * 
	 * @param piece
	 *            of the player
	 * @return rst is the number of moves that a piece can do before find an
	 *         obstacle.
	 */
	int obstaclesB(String piece) {
		int rst = 0;
		if (isRed) {
			int r = getRowRed(piece);
			int c = getColumnRed(piece);
			r = r - 1;
			while (piece != null && r >= 0) {
				if (board[r][c] == "0") {
					r = r - 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		} else {
			int r = getRowBlue(piece);
			int c = getColumnBlue(piece);
			r = r + 1;
			while (piece != null && r <= 9) {
				if (board[r][c] == "0") {
					r = r + 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		}
		return rst;
	}

	/**
	 * Counts how many moves a piece can do to the left before find an obstacle.
	 * 
	 * @param piece
	 *            of the player
	 * @return rst is the number of moves that a piece can do before find an
	 *         obstacle.
	 */
	int obstaclesL(String piece) {
		int rst = 0;
		if (isRed) {
			int r = getRowRed(piece);
			int c = getColumnRed(piece);
			c = c + 1;
			while (piece != null && c <= 9) {
				if (board[r][c] == "0") {
					c = c + 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		} else {
			int r = getRowBlue(piece);
			int c = getColumnBlue(piece);
			c = c - 1;
			while (piece != null && c >= 0) {
				if (board[r][c] == "0") {
					c = c - 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		}
		return rst;
	}

	/**
	 * Counts how many moves a piece can do to the right before find an
	 * obstacle.
	 * 
	 * @param piece
	 *            of the player
	 * @return rst is the number of moves that a piece can do before find an
	 *         obstacle.
	 */
	int obstaclesR(String piece) {
		int rst = 0;
		if (isRed) {
			int r = getRowRed(piece);
			int c = getColumnRed(piece);
			c = c - 1;
			while (piece != null && c >= 0) {
				if (board[r][c] == "0") {
					c = c - 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		} else {
			int r = getRowBlue(piece);
			int c = getColumnBlue(piece);
			c = c + 1;
			while (piece != null && c <= 9) {
				if (board[r][c] == "0") {
					c = c + 1;
					rst = rst + 1;
				} else if (board[r][c] == "1") {
					rst = rst + 1;
					break;
				} else {
					break;
				}
			}
		}
		return rst;
	}

	/**
	 * Initialize the array of the movable pieces.
	 */
	public void initPiecesMoveble() {
		piecesMovable[0] = ma.getMa();
		piecesMovable[1] = fm.getFm();
		piecesMovable[2] = sm.getSm();
		piecesMovable[3] = fs.getFs();
		piecesMovable[4] = ss.getSs();
		piecesMovable[5] = ge.getGe();
		piecesMovable[6] = sp.getSp();
	}

	/**
	 * Called after the blue Spy has been moved. It reloads its coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Spy has been moved.
	 */
	private void reload_Sp_B(int direction) {
		board[sp.getRowB()][sp.getColumnB()] = "0";
		if (direction == 1) {
			sp.setRowB(sp.getRowB() - 1);
		} else if (direction == 2) {
			sp.setRowB(sp.getRowB() + 1);
		} else if (direction == 3) {
			sp.setColumnB(sp.getColumnB() - 1);
		} else if (direction == 4) {
			sp.setColumnB(sp.getColumnB() + 1);
		}
		board[sp.getRowB()][sp.getColumnB()] = sp.getSp();
	}

	/**
	 * Called after the blue General has been moved. It reloads its coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the General has been moved.
	 */
	private void reload_Ge_B(int direction) {
		board[ge.getRowB()][ge.getColumnB()] = "0";
		if (direction == 1) {
			ge.setRowB(ge.getRowB() - 1);
		} else if (direction == 2) {
			ge.setRowB(ge.getRowB() + 1);
		} else if (direction == 3) {
			ge.setColumnB(ge.getColumnB() - 1);
		} else if (direction == 4) {
			ge.setColumnB(ge.getColumnB() + 1);
		}
		board[ge.getRowB()][ge.getColumnB()] = ge.getGe();
	}

	/**
	 * Called after the blue Second Explorer has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Second Explorer has been moved.
	 */
	private void reload_Ss_B(int direction) {
		board[ss.getRowB()][ss.getColumnB()] = "0";
		if (direction == 1) {
			ss.setRowB(ss.getRowB() - 1);
		} else if (direction == 2) {
			ss.setRowB(ss.getRowB() + 1);
		} else if (direction == 3) {
			ss.setColumnB(ss.getColumnB() - 1);
		} else if (direction == 4) {
			ss.setColumnB(ss.getColumnB() + 1);
		}
		board[ss.getRowB()][ss.getColumnB()] = ss.getSs();
	}

	/**
	 * Called after the blue First Explorer has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the First Explorer has been moved.
	 */
	private void reload_Fs_B(int direction) {
		board[fs.getRowB()][fs.getColumnB()] = "0";
		if (direction == 1) {
			fs.setRowB(fs.getRowB() - 1);
		} else if (direction == 2) {
			fs.setRowB(fs.getRowB() + 1);
		} else if (direction == 3) {
			fs.setColumnB(fs.getColumnB() - 1);
		} else if (direction == 4) {
			fs.setColumnB(fs.getColumnB() + 1);
		}
		board[fs.getRowB()][fs.getColumnB()] = fs.getFs();
	}

	/**
	 * Called after the blue Second Miner has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Second Miner has been moved.
	 */
	private void reload_Sm_B(int direction) {
		board[sm.getRowB()][sm.getColumnB()] = "0";
		if (direction == 1) {
			sm.setRowB(sm.getRowB() - 1);
		} else if (direction == 2) {
			sm.setRowB(sm.getRowB() + 1);
		} else if (direction == 3) {
			sm.setColumnB(sm.getColumnB() - 1);
		} else if (direction == 4) {
			sm.setColumnB(sm.getColumnB() + 1);
		}
		board[sm.getRowB()][sm.getColumnB()] = sm.getSm();
	}

	/**
	 * Called after the blue First Miner has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the First Miner has been moved.
	 */
	private void reload_Fm_B(int direction) {
		board[fm.getRowB()][fm.getColumnB()] = "0";
		if (direction == 1) {
			fm.setRowB(fm.getRowB() - 1);
		} else if (direction == 2) {
			fm.setRowB(fm.getRowB() + 1);
		} else if (direction == 3) {
			fm.setColumnB(fm.getColumnB() - 1);
		} else if (direction == 4) {
			fm.setColumnB(fm.getColumnB() + 1);
		}
		board[fm.getRowB()][fm.getColumnB()] = fm.getFm();
	}

	/**
	 * Called after the blue Marshal has been moved. It reloads its coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Marshal has been moved.
	 */
	private void reload_Ma_B(int direction) {
		board[ma.getRowB()][ma.getColumnB()] = "0";
		if (direction == 1) {
			ma.setRowB(ma.getRowB() - 1);
		} else if (direction == 2) {
			ma.setRowB(ma.getRowB() + 1);
		} else if (direction == 3) {
			ma.setColumnB(ma.getColumnB() - 1);
		} else if (direction == 4) {
			ma.setColumnB(ma.getColumnB() + 1);
		}
		board[ma.getRowB()][ma.getColumnB()] = ma.getMa();
	}

	/**
	 * Called after the red Marshal has been moved. It reloads its coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Marshal has been moved.
	 */
	private void reload_Ma_R(int direction) {
		board[ma.getRowR()][ma.getColumnR()] = "0";
		if (direction == 1) {
			ma.setRowR(ma.getRowR() + 1);
		} else if (direction == 2) {
			ma.setRowR(ma.getRowR() - 1);
		} else if (direction == 3) {
			ma.setColumnR(ma.getColumnR() + 1);
		} else if (direction == 4) {
			ma.setColumnR(ma.getColumnR() - 1);
		}
		board[ma.getRowR()][ma.getColumnR()] = ma.getMa();
	}

	/**
	 * Called after the red First Miner has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the First Miner has been moved.
	 */
	private void reload_Fm_R(int direction) {
		board[fm.getRowR()][fm.getColumnR()] = "0";
		if (direction == 1) {
			fm.setRowR(fm.getRowR() + 1);
		} else if (direction == 2) {
			fm.setRowR(fm.getRowR() - 1);
		} else if (direction == 3) {
			fm.setColumnR(fm.getColumnR() + 1);
		} else if (direction == 4) {
			fm.setColumnR(fm.getColumnR() - 1);
		}
		board[fm.getRowR()][fm.getColumnR()] = fm.getFm();
	}

	/**
	 * Called after the red Second Miner has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Second Miner has been moved.
	 */
	private void reload_Sm_R(int direction) {
		board[sm.getRowR()][sm.getColumnR()] = "0";
		if (direction == 1) {
			sm.setRowR(sm.getRowR() + 1);
		} else if (direction == 2) {
			sm.setRowR(sm.getRowR() - 1);
		} else if (direction == 3) {
			sm.setColumnR(sm.getColumnR() + 1);
		} else if (direction == 4) {
			sm.setColumnR(sm.getColumnR() - 1);
		}
		board[sm.getRowR()][sm.getColumnR()] = sm.getSm();
	}

	/**
	 * Called after the red First Explorer has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the First Explorer has been moved.
	 */
	private void reload_Fs_R(int direction) {
		board[fs.getRowR()][fs.getColumnR()] = "0";
		if (direction == 1) {
			fs.setRowR(fs.getRowR() + 1);
		} else if (direction == 2) {
			fs.setRowR(fs.getRowR() - 1);
		} else if (direction == 3) {
			fs.setColumnR(fs.getColumnR() + 1);
		} else if (direction == 4) {
			fs.setColumnR(fs.getColumnR() - 1);
		}
		board[fs.getRowR()][fs.getColumnR()] = fs.getFs();
	}

	/**
	 * Called after the red Second Explorer has been moved. It reloads its
	 * coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Second Explorer has been moved.
	 */
	private void reload_Ss_R(int direction) {
		board[ss.getRowR()][ss.getColumnR()] = "0";
		if (direction == 1) {
			ss.setRowR(ss.getRowR() + 1);
		} else if (direction == 2) {
			ss.setRowR(ss.getRowR() - 1);
		} else if (direction == 3) {
			ss.setColumnR(ss.getColumnR() + 1);
		} else if (direction == 4) {
			ss.setColumnR(ss.getColumnR() - 1);
		}
		board[ss.getRowR()][ss.getColumnR()] = ss.getSs();
	}

	/**
	 * Called after the red General has been moved. It reloads its coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the General has been moved.
	 */
	private void reload_Ge_R(int direction) {
		board[ge.getRowR()][ge.getColumnR()] = "0";
		if (direction == 1) {
			ge.setRowR(ge.getRowR() + 1);
		} else if (direction == 2) {
			ge.setRowR(ge.getRowR() - 1);
		} else if (direction == 3) {
			ge.setColumnR(ge.getColumnR() + 1);
		} else if (direction == 4) {
			ge.setColumnR(ge.getColumnR() - 1);
		}
		board[ge.getRowR()][ge.getColumnR()] = ge.getGe();
	}

	/**
	 * Called after the red Spy has been moved. It reloads its coordinates.
	 * 
	 * @param direction
	 *            is the direction in which the Spy has been moved.
	 */
	private void reload_Sp_R(int direction) {
		board[sp.getRowR()][sp.getColumnR()] = "0";
		if (direction == 1) {
			sp.setRowR(sp.getRowR() + 1);
		} else if (direction == 2) {
			sp.setRowR(sp.getRowR() - 1);
		} else if (direction == 3) {
			sp.setColumnR(sp.getColumnR() + 1);
		} else if (direction == 4) {
			sp.setColumnR(sp.getColumnR() - 1);
		}
		board[sp.getRowR()][sp.getColumnR()] = sp.getSp();
	}

	/**
	 * Chooses the red piece that needs to be reloaded after it has been moved.
	 * 
	 * @param i
	 *            represents the index of a piece in the movable pieces array.
	 * @param direction
	 *            is the direction in which the piece has been moved.
	 */
	void reloadCoordinatesR(int i, int direction) {
		switch (i) {
		case 0:
			reload_Ma_R(direction);
			break;
		case 1:
			reload_Fm_R(direction);
			break;
		case 2:
			reload_Sm_R(direction);
			break;
		case 3:
			reload_Fs_R(direction);
			break;
		case 4:
			reload_Ss_R(direction);
			break;
		case 5:
			reload_Ge_R(direction);
			break;
		case 6:
			reload_Sp_R(direction);
			break;
		}
	}

	/**
	 * Chooses the blue piece that needs to be reloaded after it has been moved.
	 * 
	 * @param i
	 *            represents the index of a piece in the movable pieces array.
	 * @param direction
	 *            is the direction in which the piece has been moved.
	 */
	void reloadCoordinatesB(int i, int direction) {
		switch (i) {
		case 0:
			reload_Ma_B(direction);
			break;
		case 1:
			reload_Fm_B(direction);
			break;
		case 2:
			reload_Sm_B(direction);
			break;
		case 3:
			reload_Fs_B(direction);
			break;
		case 4:
			reload_Ss_B(direction);
			break;
		case 5:
			reload_Ge_B(direction);
			break;
		case 6:
			reload_Sp_B(direction);
			break;
		}

	}

	/**
	 * Communicate to the enemie the move of the current player piece.
	 */
	public void tellMove(int[] move) {
		int r = move[0];
		int c = move[1];
		int direction = move[2];
		int steps = move[3];
		if (isRed) {
			switch (direction) {
			case 1:
				board[r][c] = "0";
				board[r - steps][c] = "1";
				break;
			case 2:
				board[r][c] = "0";
				board[r + steps][c] = "1";
				break;
			case 3:
				board[r][c] = "0";
				board[r][c - steps] = "1";
				break;
			case 4:
				board[r][c] = "0";
				board[r][c + steps] = "1";
				break;
			}
		} else {
			switch (direction) {
			case 1:
				board[r][c] = "0";
				board[r + steps][c] = "1";
				break;
			case 2:
				board[r][c] = "0";
				board[r - steps][c] = "1";
				break;
			case 3:
				board[r][c] = "0";
				board[r][c + steps] = "1";
				break;
			case 4:
				board[r][c] = "0";
				board[r][c - steps] = "1";
				break;
			}
		}

	}

	String getMyPieceRed() {
		String rst = null;
		if (ma.isEliminated_Red == false && board[ma.getRowR()][ma.getColumnR()].equals("1")) {
			rst = "MA";
		} else if (fm.isEliminated_Red == false && board[fm.getRowR()][fm.getColumnR()].equals("1")) {
			rst = "FM";
		} else if (sm.isEliminated_Red == false && board[sm.getRowR()][sm.getColumnR()].equals("1")) {
			rst = "SM";
		} else if (fs.isEliminated_Red == false && board[fs.getRowR()][fs.getColumnR()].equals("1")) {
			rst = "FS";
		} else if (ss.isEliminated_Red == false && board[ss.getRowR()][ss.getColumnR()].equals("1")) {
			rst = "SS";
		} else if (ge.isEliminated_Red == false && board[ge.getRowR()][ge.getColumnR()].equals("1")) {
			rst = "GE";
		} else if (sp.isEliminated_Red == false && board[sp.getRowR()][sp.getColumnR()].equals("1")) {
			rst = "SP";
		} else if (fb.isEliminated_Red == false && board[fb.getRowR()][fb.getColumnR()].equals("1")) {
			rst = "FB";
		} else if (sb.isEliminated_Red == false && board[sb.getRowR()][sb.getColumnR()].equals("1")) {
			rst = "SB";
		} else if (fl.isEliminated_Red == false && board[fl.getRowR()][fl.getColumnR()].equals("1")) {
			rst = "FL";
		}
		return rst;
	}

	int getValuesBlue(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Blue == false) {
				rst = 10;
				break;
			}
		case "GE":
			if (ge.isEliminated_Blue == false) {
				rst = 9;
				break;
			}
		case "FM":
			if (fm.isEliminated_Blue == false) {
				rst = 3;
				break;
			}
		case "SM":
			if (sm.isEliminated_Blue == false) {
				rst = 3;
				break;
			}
		case "FS":
			if (fs.isEliminated_Blue == false) {
				rst = 2;
				break;
			}
		case "SS":
			if (ss.isEliminated_Blue == false) {
				rst = 2;
				break;
			}
		case "SP":
			if (sp.isEliminated_Blue == false) {
				rst = 1;
				break;
			}
		}
		return rst;
	}
	
	int getValuesRed(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Red == false) {
				rst = 10;
				break;
			}
		case "GE":
			if (ge.isEliminated_Red == false) {
				rst = 9;
				break;
			}
		case "FM":
			if (fm.isEliminated_Red == false) {
				rst = 3;
				break;
			}
		case "SM":
			if (sm.isEliminated_Red == false) {
				rst = 3;
				break;
			}
		case "FS":
			if (fs.isEliminated_Red == false) {
				rst = 2;
				break;
			}
		case "SS":
			if (ss.isEliminated_Red == false) {
				rst = 2;
				break;
			}
		case "SP":
			if (sp.isEliminated_Red == false) {
				rst = 1;
				break;
			}
		}
		return rst;
	}

	int getRowRed(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Red == false) {
				rst = ma.getRowR();
			}
			break;
		case "GE":
			if (ge.isEliminated_Red == false) {
				rst = ge.getRowR();
			}
			break;
		case "FM":
			if (fm.isEliminated_Red == false) {
				rst = fm.getRowR();
			}
			break;
		case "SM":
			if (sm.isEliminated_Red == false) {
				rst = sm.getRowR();
			}
			break;
		case "FS":
			if (fs.isEliminated_Red == false) {
				rst = fs.getRowR();
			}
			break;
		case "SS":
			if (ss.isEliminated_Red == false) {
				rst = ss.getRowR();
			}
			break;
		case "SP":
			if (sp.isEliminated_Red == false) {
				rst = sp.getRowR();
			}
			break;
		case "FB":
			if (fb.isEliminated_Red == false) {
				rst = fb.getRowR();
			}
			break;
		case "SB":
			if (sb.isEliminated_Red == false) {
				rst = sb.getRowR();
			}
			break;
		case "FL":
			if (fl.isEliminated_Red == false) {
				rst = fl.getRowR();
			}
			break;
		}
		return rst;
	}

	int getColumnRed(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Red == false) {
				rst = ma.getColumnR();
			}
			break;
		case "GE":
			if (ge.isEliminated_Red == false) {
				rst = ge.getColumnR();
			}
			break;
		case "FM":
			if (fm.isEliminated_Red == false) {
				rst = fm.getColumnR();
			}
			break;
		case "SM":
			if (sm.isEliminated_Red == false) {
				rst = sm.getColumnR();
			}
			break;
		case "FS":
			if (fs.isEliminated_Red == false) {
				rst = fs.getColumnR();
			}
			break;
		case "SS":
			if (ss.isEliminated_Red == false) {
				rst = ss.getColumnR();
			}
			break;
		case "SP":
			if (sp.isEliminated_Red == false) {
				rst = sp.getColumnR();
			}
			break;
		case "FB":
			if (fb.isEliminated_Red == false) {
				rst = fb.getColumnR();
			}
			break;
		case "SB":
			if (sb.isEliminated_Red == false) {
				rst = sb.getColumnR();
			}
			break;
		case "FL":
			if (fl.isEliminated_Red == false) {
				rst = fl.getColumnR();
			}
			break;
		}
		return rst;
	}

	/**
	 * It handles the fights between the pieces.
	 */
	public void fight(String enemie) {
		System.out.println("ENEMIE: "+enemie);
		if (isRed) {
			String myPiece = getMyPieceRed();
			if (myPiece == null) {
				myPiece = lastPieceMoved;
				System.out.println("MY PIECE: "+myPiece);
				myPieceAttacksRed(myPiece, enemie);
			} else {
				System.out.println("MY PIECE: "+myPiece);
				enemieAttacksRed(myPiece, enemie);
			}
		} else {
			String myPiece = getMyPieceBlue();
			if (myPiece == null) {
				myPiece = lastPieceMoved;
				System.out.println("MY PIECE: "+myPiece);
				myPieceAttacksBlue(myPiece, enemie);
			} else {
				System.out.println("MY PIECE: "+myPiece);
				enemieAttacksBlue(myPiece, enemie);
			}
		}
		printMatrix(board);
	}

	public void enemieAttacksBlue(String myPiece, String enemie) {
		int r = getRowBlue(myPiece);
		int c = getColumnBlue(myPiece);
		switch (myPiece) {
		case "FB":
			if (enemie.equals("FM") || enemie.equals("SM")) {
				board[r][c] = "1";
				nullifyBlue(myPiece);
			} else {
				board[r][c] = myPiece;
			}
			break;
		case "SB":
			if (enemie.equals("FM") || enemie.equals("SM")) {
				board[r][c] = "1";
				nullifyBlue(myPiece);
			} else {
				board[r][c] = myPiece;
			}
			break;
		case "MA":
			if (enemie.equals("SP")) {
				board[r][c] = "1";
				nullifyBlue(myPiece);
			} else {
				if (enemie.equals(myPiece)) {
					board[r][c] = "0";
					nullifyBlue(myPiece);
				} else {
					int myVal = getValuesBlue(myPiece);
					int enemieVal = getValuesRed(enemie);
					if(myVal > enemieVal){
						System.out.println("RIGA 2521");
						System.out.println("My piece: "+myPiece+" myValue: "+myVal);
						System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
						board[r][c] = myPiece;
					}else if(myVal < enemieVal){
						System.out.println("RIGA 2526");
						System.out.println("My piece: "+myPiece+" myValue: "+myVal);
						System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
						board[r][c] = "1";
						nullifyBlue(myPiece);
					}else if(myVal == enemieVal){
						System.out.println("RIGA 2532");
						System.out.println("My piece: "+myPiece+" myValue: "+myVal);
						System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
						board[r][c] = "0";
						nullifyBlue(myPiece);
					}
				}
			}
			break;
		case "FL":
			board[r][c] = "1";
			nullifyBlue(myPiece);
			break;
		default:
			int myVal = getValuesBlue(myPiece);
			int enemieVal = getValuesRed(enemie);
			if(myVal > enemieVal){
				System.out.println("RIGA 2549");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 2554");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyBlue(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 2560");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "0";
				nullifyBlue(myPiece);
			}
			break;
		}
	}

	public void enemieAttacksRed(String myPiece, String enemie) {
		int r = getRowRed(myPiece);
		int c = getColumnRed(myPiece);
		switch (myPiece) {
		case "FB":
			if (enemie.equals("FM") || enemie.equals("SM")) {
				board[r][c] = "1";
				nullifyRed(myPiece);
			} else {
				board[r][c] = myPiece;
			}
			break;
		case "SB":
			if (enemie.equals("FM") || enemie.equals("SM")) {
				board[r][c] = "1";
				nullifyRed(myPiece);
			} else {
				board[r][c] = myPiece;
			}
			break;
		case "MA":
			if (enemie.equals("SP")) {
				board[r][c] = "1";
				nullifyRed(myPiece);
			} else {
				int myVal = getValuesRed(myPiece);
				int enemieVal = getValuesBlue(enemie);
				if(myVal > enemieVal){
					System.out.println("RIGA 2598");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = myPiece;
				}else if(myVal < enemieVal){
					System.out.println("RIGA 2603");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "1";
					nullifyRed(myPiece);
				}else if(myVal == enemieVal){
					System.out.println("RIGA 2609");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "0";
					nullifyRed(myPiece);
				}
			}
			break;
		case "FL":
			board[r][c] = "1";
			nullifyRed(myPiece);
			break;
		default:
			int myVal = getValuesRed(myPiece);
			int enemieVal = getValuesBlue(enemie);
			if(myVal > enemieVal){
				System.out.println("RIGA 2625");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 2630");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyRed(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 2636");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "0";
				nullifyRed(myPiece);
			}
			break;
		}
	}

	public void myPieceAttacksBlue(String myPiece, String enemie) {
		int r = getRowBlue(myPiece);
		int c = getColumnBlue(myPiece);
		switch (enemie) {
		case "FB":
			if (myPiece.equals("FM") || myPiece.equals("SM")) {
				board[r][c] = myPiece;
			} else {
				board[r][c] = "1";
				nullifyBlue(myPiece);
			}
			break;
		case "SB":
			if (myPiece.equals("FM") || myPiece.equals("SM")) {
				board[r][c] = myPiece;
			} else {
				board[r][c] = "1";
				nullifyBlue(myPiece);
			}
			break;
		case "FL":
			board[r][c] = myPiece;
			break;
		case "MA":
			if (myPiece.equals("SP")) {
				board[r][c] = myPiece;
			} else {
				int myVal = getValuesBlue(myPiece);
				int enemieVal = getValuesRed(enemie);
				if(myVal > enemieVal){
					System.out.println("RIGA 2658");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = myPiece;
				}else if(myVal < enemieVal){
					System.out.println("RIGA 2663");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "1";
					nullifyBlue(myPiece);
				}else if(myVal == enemieVal){
					System.out.println("RIGA 2669");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "0";
					nullifyBlue(myPiece);
				}
			}
			break;
		default:
			int myVal = getValuesBlue(myPiece);
			int enemieVal = getValuesRed(enemie);
			if(myVal > enemieVal){
				System.out.println("RIGA 2681");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 2686");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyBlue(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 2692");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "0";
				nullifyBlue(myPiece);
			}
			break;
		}
	}

	public void myPieceAttacksRed(String myPiece, String enemie) {
		int r = getRowRed(myPiece);
		int c = getColumnRed(myPiece);
		switch (enemie) {
		case "FB":
			if (myPiece.equals("FM") || myPiece.equals("SM")) {
				board[r][c] = myPiece;
			} else {
				board[r][c] = "1";
				nullifyRed(myPiece);
			}
			break;
		case "SB":
			if (myPiece.equals("FM") || myPiece.equals("SM")) {
				board[r][c] = myPiece;
			} else {
				board[r][c] = "1";
				nullifyRed(myPiece);
			}
			break;
		case "FL":
			board[r][c] = myPiece;
			break;
		case "MA":
			if (myPiece.equals("SP")) {
				board[r][c] = myPiece;
			} else {
				int myVal = getValuesRed(myPiece);
				int enemieVal = getValuesBlue(enemie);
				if(myVal > enemieVal){
					System.out.println("RIGA 2750");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = myPiece;
				}else if(myVal < enemieVal){
					System.out.println("RIGA 2755");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "1";
					nullifyRed(myPiece);
				}else if(myVal == enemieVal){
					System.out.println("RIGA 2761");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "0";
					nullifyRed(myPiece);
				}
			}
			break;
		default:
			int myVal = getValuesRed(myPiece);
			int enemieVal = getValuesBlue(enemie);
			if(myVal > enemieVal){
				System.out.println("RIGA 2773");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 2778");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyRed(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 2784");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "0";
				nullifyRed(myPiece);
			}
			break;
		}
	}

	int getColumnBlue(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Blue == false) {
				rst = ma.getColumnB();
			}
			break;
		case "GE":
			if (ge.isEliminated_Blue == false) {
				rst = ge.getColumnB();
			}
			break;
		case "FM":
			if (fm.isEliminated_Blue == false) {
				rst = fm.getColumnB();
			}
			break;
		case "SM":
			if (sm.isEliminated_Blue == false) {
				rst = sm.getColumnB();
			}
			break;
		case "FS":
			if (fs.isEliminated_Blue == false) {
				rst = fs.getColumnB();
			}
			break;
		case "SS":
			if (ss.isEliminated_Blue == false) {
				rst = ss.getColumnB();
			}
			break;
		case "SP":
			if (sp.isEliminated_Blue == false) {
				rst = sp.getColumnB();
			}
			break;
		case "FB":
			if (fb.isEliminated_Blue == false) {
				rst = fb.getColumnB();
			}
			break;
		case "SB":
			if (sb.isEliminated_Blue == false) {
				rst = sb.getColumnB();
			}
			break;
		case "FL":
			if (fl.isEliminated_Blue == false) {
				rst = fl.getColumnB();
			}
			break;
		}
		return rst;
	}

	int getRowBlue(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Blue == false) {
				rst = ma.getRowB();
			}
			break;
		case "GE":
			if (ge.isEliminated_Blue == false) {
				rst = ge.getRowB();
			}
			break;
		case "FM":
			if (fm.isEliminated_Blue == false) {
				rst = fm.getRowB();
			}
			break;
		case "SM":
			if (sm.isEliminated_Blue == false) {
				rst = sm.getRowB();
			}
			break;
		case "FS":
			if (fs.isEliminated_Blue == false) {
				rst = fs.getRowB();
			}
			break;
		case "SS":
			if (ss.isEliminated_Blue == false) {
				rst = ss.getRowB();
			}
			break;
		case "SP":
			if (sp.isEliminated_Blue == false) {
				rst = sp.getRowB();
			}
			break;
		case "FB":
			if (fb.isEliminated_Blue == false) {
				rst = fb.getRowB();
			}
			break;
		case "SB":
			if (sb.isEliminated_Blue == false) {
				rst = sb.getRowB();
			}
			break;
		case "FL":
			if (fl.isEliminated_Blue == false) {
				rst = fl.getRowB();
			}
			break;
		}
		return rst;
	}

	String getMyPieceBlue() {
		String rst = null;
		if (ma.isEliminated_Blue == false && board[ma.getRowB()][ma.getColumnB()] == "1") {
			rst = "MA";
		} else if (fm.isEliminated_Blue == false && board[fm.getRowB()][fm.getColumnB()] == "1") {
			rst = "FM";
		} else if (sm.isEliminated_Blue == false && board[sm.getRowB()][sm.getColumnB()] == "1") {
			rst = "SM";
		} else if (fs.isEliminated_Blue == false && board[fs.getRowB()][fs.getColumnB()] == "1") {
			rst = "FS";
		} else if (ss.isEliminated_Blue == false && board[ss.getRowB()][ss.getColumnB()] == "1") {
			rst = "SS";
		} else if (ge.isEliminated_Blue == false && board[ge.getRowB()][ge.getColumnB()] == "1") {
			rst = "GE";
		} else if (sp.isEliminated_Blue == false && board[sp.getRowB()][sp.getColumnB()] == "1") {
			rst = "SP";
		} else if (fb.isEliminated_Blue == false && board[fb.getRowB()][fb.getColumnB()] == "1") {
			rst = "FB";
		} else if (sb.isEliminated_Blue == false && board[sb.getRowB()][sb.getColumnB()] == "1") {
			rst = "SB";
		} else if (fl.isEliminated_Blue == false && board[fl.getRowB()][fl.getColumnB()] == "1") {
			rst = "FL";
		}
		return rst;
	}

	/**
	 * Called when a piece of a current player has been eliminated. It reloads
	 * his value to "null" because it has been eliminated from the game.
	 * 
	 * @param i
	 *            represents the index of the eliminated piece.
	 */
	public void nullifyBlue(String i) {
		switch (i) {
		case "MA":
			piecesMovable[0] = null;
			ma.isEliminated_Blue = true;
			break;
		case "FM":
			piecesMovable[1] = null;
			fm.isEliminated_Blue = true;
			break;
		case "SM":
			piecesMovable[2] = null;
			sm.isEliminated_Blue = true;
			break;
		case "FS":
			piecesMovable[3] = null;
			fs.isEliminated_Blue = true;
			break;
		case "SS":
			piecesMovable[4] = null;
			ss.isEliminated_Blue = true;
			break;
		case "GE":
			piecesMovable[5] = null;
			ge.isEliminated_Blue = true;
			break;
		case "SP":
			piecesMovable[6] = null;
			sp.isEliminated_Blue = true;
			break;
		case "FB":
			fb.isEliminated_Blue = true;
			break;
		case "SB":
			sb.isEliminated_Blue = true;
			break;
		case "FL":
			fl.isEliminated_Blue = true;
		}
	}
	
	public void nullifyRed(String i) {
		switch (i) {
		case "MA":
			piecesMovable[0] = null;
			ma.isEliminated_Red = true;
			break;
		case "FM":
			piecesMovable[1] = null;
			fm.isEliminated_Red = true;
			break;
		case "SM":
			piecesMovable[2] = null;
			sm.isEliminated_Red = true;
			break;
		case "FS":
			piecesMovable[3] = null;
			fs.isEliminated_Red = true;
			break;
		case "SS":
			piecesMovable[4] = null;
			ss.isEliminated_Red = true;
			break;
		case "GE":
			piecesMovable[5] = null;
			ge.isEliminated_Red = true;
			break;
		case "SP":
			piecesMovable[6] = null;
			sp.isEliminated_Red = true;
			break;
		case "FB":
			fb.isEliminated_Red = true;
			break;
		case "SB":
			sb.isEliminated_Red = true;
			break;
		case "FL":
			fl.isEliminated_Red = true;
		}
	}
}
