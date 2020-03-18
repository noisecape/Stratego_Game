package gj.stratego.player.capecchi;

import java.util.LinkedList;

import gj.stratego.player.Player;
import gj.stratego.player.capecchi.First_Bomb;
import gj.stratego.player.capecchi.First_Explorator;
import gj.stratego.player.capecchi.First_Miner;
import gj.stratego.player.capecchi.Flag;
import gj.stratego.player.capecchi.General;
import gj.stratego.player.capecchi.Marshal;
import gj.stratego.player.capecchi.Second_Bomb;
import gj.stratego.player.capecchi.Second_Explorator;
import gj.stratego.player.capecchi.Second_Miner;
import gj.stratego.player.capecchi.Spy;

public class RandomPlayer implements Player {

	boolean isRed;
	String board[][];
	int[][] enemieBoard;
	int moveArray[];
	String lastPieceMoved = "";

	LinkedList<LinkedList<Integer>> allMoves = new LinkedList<LinkedList<Integer>>();
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

	public RandomPlayer() {
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
		board = new String[10][10];
		enemieBoard = new int[4][10];
		moveArray = new int[4];
		piecesMovable = new String[7];
	}

	@Override
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
						System.out.println("RIGA 99");
						System.out.println("My piece: "+myPiece+" myValue: "+myVal);
						System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
						board[r][c] = myPiece;
					}else if(myVal < enemieVal){
						System.out.println("RIGA 104");
						System.out.println("My piece: "+myPiece+" myValue: "+myVal);
						System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
						board[r][c] = "1";
						nullifyBlue(myPiece);
					}else if(myVal == enemieVal){
						System.out.println("RIGA 110");
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
				System.out.println("RIGA 127");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 132");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyBlue(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 138");
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
					System.out.println("RIGA 176");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = myPiece;
				}else if(myVal < enemieVal){
					System.out.println("RIGA 181");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "1";
					nullifyRed(myPiece);
				}else if(myVal == enemieVal){
					System.out.println("RIGA 187");
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
				System.out.println("RIGA 203");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 208");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyRed(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 213");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "0";
				nullifyRed(myPiece);
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
					System.out.println("RIGA 254");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = myPiece;
				}else if(myVal < enemieVal){
					System.out.println("RIGA 259");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "1";
					nullifyRed(myPiece);
				}else if(myVal == enemieVal){
					System.out.println("RIGA 265");
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
				System.out.println("RIGA 277");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 282");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyRed(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 288");
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
					System.out.println("RIGA 328");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = myPiece;
				}else if(myVal < enemieVal){
					System.out.println("RIGA 333");
					System.out.println("My piece: "+myPiece+" myValue: "+myVal);
					System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
					board[r][c] = "1";
					nullifyBlue(myPiece);
				}else if(myVal == enemieVal){
					System.out.println("RIGA 339");
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
				System.out.println("RIGA 351");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = myPiece;
			}else if(myVal < enemieVal){
				System.out.println("RIGA 356");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "1";
				nullifyBlue(myPiece);
			}else if(myVal == enemieVal){
				System.out.println("RIGA 362");
				System.out.println("My piece: "+myPiece+" myValue: "+myVal);
				System.out.println("Enemie: "+enemie+" enemieValue: "+enemieVal);
				board[r][c] = "0";
				nullifyBlue(myPiece);
			}
			break;
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

	int getRowBlue(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Blue == false) {
				rst = ma.getRowB();
				break;
			}
		case "GE":
			if (ge.isEliminated_Blue == false) {
				rst = ge.getRowB();
				break;
			}
		case "FM":
			if (fm.isEliminated_Blue == false) {
				rst = fm.getRowB();
				break;
			}
		case "SM":
			if (sm.isEliminated_Blue == false) {
				rst = sm.getRowB();
				break;
			}
		case "FS":
			if (fs.isEliminated_Blue == false) {
				rst = fs.getRowB();
				break;
			}
		case "SS":
			if (ss.isEliminated_Blue == false) {
				rst = ss.getRowB();
				break;
			}
		case "SP":
			if (sp.isEliminated_Blue == false) {
				rst = sp.getRowB();
				break;
			}
		case "FB":
			if (fb.isEliminated_Blue == false) {
				rst = fb.getRowB();
				break;
			}
		case "SB":
			if (sb.isEliminated_Blue == false) {
				rst = sb.getRowB();
				break;
			}
		case "FL":
			if (fl.isEliminated_Blue == false) {
				rst = fl.getRowB();
				break;
			}
		}
		return rst;
	}

	int getColumnBlue(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Blue == false) {
				rst = ma.getColumnB();
				break;
			}
		case "GE":
			if (ge.isEliminated_Blue == false) {
				rst = ge.getColumnB();
				break;
			}
		case "FM":
			if (fm.isEliminated_Blue == false) {
				rst = fm.getColumnB();
				break;
			}
		case "SM":
			if (sm.isEliminated_Blue == false) {
				rst = sm.getColumnB();
				break;
			}
		case "FS":
			if (fs.isEliminated_Blue == false) {
				rst = fs.getColumnB();
				break;
			}
		case "SS":
			if (ss.isEliminated_Blue == false) {
				rst = ss.getColumnB();
				break;
			}
		case "SP":
			if (sp.isEliminated_Blue == false) {
				rst = sp.getColumnB();
				break;
			}
		case "FB":
			if (fb.isEliminated_Blue == false) {
				rst = fb.getColumnB();
				break;
			}
		case "SB":
			if (sb.isEliminated_Blue == false) {
				rst = sb.getColumnB();
				break;
			}
		case "FL":
			if (fl.isEliminated_Blue == false) {
				rst = fl.getColumnB();
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
				break;
			}
		case "GE":
			if (ge.isEliminated_Red == false) {
				rst = ge.getRowR();
				break;
			}
		case "FM":
			if (fm.isEliminated_Red == false) {
				rst = fm.getRowR();
				break;
			}
		case "SM":
			if (sm.isEliminated_Red == false) {
				rst = sm.getRowR();
				break;
			}
		case "FS":
			if (fs.isEliminated_Red == false) {
				rst = fs.getRowR();
				break;
			}
		case "SS":
			if (ss.isEliminated_Red == false) {
				rst = ss.getRowR();
				break;
			}
		case "SP":
			if (sp.isEliminated_Red == false) {
				rst = sp.getRowR();
				break;
			}
		case "FB":
			if (fb.isEliminated_Red == false) {
				rst = fb.getRowR();
				break;
			}
		case "SB":
			if (sb.isEliminated_Red == false) {
				rst = sb.getRowR();
				break;
			}
		case "FL":
			if (fl.isEliminated_Red == false) {
				rst = fl.getRowR();
				break;
			}
		}
		return rst;
	}

	int getColumnRed(String piece) {
		int rst = 0;
		switch (piece) {
		case "MA":
			if (ma.isEliminated_Red == false) {
				rst = ma.getColumnR();
				break;
			}
		case "GE":
			if (ge.isEliminated_Red == false) {
				rst = ge.getColumnR();
				break;
			}
		case "FM":
			if (fm.isEliminated_Red == false) {
				rst = fm.getColumnR();
				break;
			}
		case "SM":
			if (sm.isEliminated_Red == false) {
				rst = sm.getColumnR();
				break;
			}
		case "FS":
			if (fs.isEliminated_Red == false) {
				rst = fs.getColumnR();
				break;
			}
		case "SS":
			if (ss.isEliminated_Red == false) {
				rst = ss.getColumnR();
				break;
			}
		case "SP":
			if (sp.isEliminated_Red == false) {
				rst = sp.getColumnR();
				break;
			}
		case "FB":
			if (fb.isEliminated_Red == false) {
				rst = fb.getColumnR();
				break;
			}
		case "SB":
			if (sb.isEliminated_Red == false) {
				rst = sb.getColumnR();
				break;
			}
		case "FL":
			if (fl.isEliminated_Red == false) {
				rst = fl.getColumnR();
				break;
			}
		}
		return rst;
	}

	int getValuesRed(String piece) {
		int rst = 0;
		if(ma.isEliminated_Red == false && piece.equals("MA")){
			rst = 10;
			System.out.println(piece+"-"+rst);
		}else if(fm.isEliminated_Red == false && piece.equals("FM")){
			rst = 3;
			System.out.println(piece+"-"+rst);
		}else if(sm.isEliminated_Red == false && piece.equals("SM")){
			rst = 3;
			System.out.println(piece+"-"+rst);
		}else if(fs.isEliminated_Red == false && piece.equals("FS")){
			rst = 2;
			System.out.println(piece+"-"+rst);
		}else if(ss.isEliminated_Red == false && piece.equals("SS")){
			rst = 2;
			System.out.println(piece+"-"+rst);
		}else if(ge.isEliminated_Red == false && piece.equals("GE")){
			rst = 9;
			System.out.println(piece+"-"+rst);
		}else if(sp.isEliminated_Red == false && piece.equals("SP")){
			rst = 1;
			System.out.println(piece+"-"+rst);
		}else{
			System.out.println(ma.isEliminated_Red+" MA");
			System.out.println(ge.isEliminated_Red+" GE");
			System.out.println(fs.isEliminated_Red+" FS");
			System.out.println(ss.isEliminated_Red+" SS");
			System.out.println(fl.isEliminated_Red+" FL");
			System.out.println(fb.isEliminated_Red+" FB");
			System.out.println(sb.isEliminated_Red+" SB");
			System.out.println(fm.isEliminated_Red+" FM");
			System.out.println(sm.isEliminated_Red+" SM");
			System.out.println(sp.isEliminated_Red+" SP");
		}
		return rst;
	}
	
	int getValuesBlue(String piece) {
		int rst = 0;
		if(ma.isEliminated_Blue == false && piece.equals("MA")){
			rst = 10;
			System.out.println(piece+"-"+rst);
		}else if(fm.isEliminated_Blue == false && piece.equals("FM")){
			rst = 3;
			System.out.println(piece+"-"+rst);
		}else if(sm.isEliminated_Blue == false && piece.equals("SM")){
			rst = 3;
			System.out.println(piece+"-"+rst);
		}else if(fs.isEliminated_Blue == false && piece.equals("FS")){
			rst = 2;
			System.out.println(piece+"-"+rst);
		}else if(ss.isEliminated_Blue == false && piece.equals("SS")){
			rst = 2;
			System.out.println(piece+"-"+rst);
		}else if(ge.isEliminated_Blue == false && piece.equals("GE")){
			rst = 9;
			System.out.println(piece+"-"+rst);
		}else if(sp.isEliminated_Blue == false && piece.equals("SP")){
			rst = 1;
			System.out.println(piece+"-"+rst);
		}else{
			System.out.println(ma.isEliminated_Blue+" MA");
			System.out.println(ge.isEliminated_Blue+" GE");
			System.out.println(fs.isEliminated_Blue+" FS");
			System.out.println(ss.isEliminated_Blue+" SS");
			System.out.println(fl.isEliminated_Blue+" FL");
			System.out.println(fb.isEliminated_Blue+" FB");
			System.out.println(sb.isEliminated_Blue+" SB");
			System.out.println(fm.isEliminated_Blue+" FM");
			System.out.println(sm.isEliminated_Blue+" SM");
			System.out.println(sp.isEliminated_Blue+" SP");
		}
		return rst;
	}

	String getMyPieceBlue() {
		String rst;
		if (ma.isEliminated_Blue == false && board[ma.getRowB()][ma.getColumnB()].equals("1")) {
			rst = "MA";
		} else if (fm.isEliminated_Blue == false && board[fm.getRowB()][fm.getColumnB()].equals("1")) {
			rst = "FM";
		} else if (sm.isEliminated_Blue == false && board[sm.getRowB()][sm.getColumnB()].equals("1")) {
			rst = "SM";
		} else if (fs.isEliminated_Blue == false && board[fs.getRowB()][fs.getColumnB()].equals("1")) {
			rst = "FS";
		} else if (ss.isEliminated_Blue == false && board[ss.getRowB()][ss.getColumnB()].equals("1")) {
			rst = "SS";
		} else if (ge.isEliminated_Blue == false && board[ge.getRowB()][ge.getColumnB()].equals("1")) {
			rst = "GE";
		} else if (sp.isEliminated_Blue == false && board[sp.getRowB()][sp.getColumnB()].equals("1")) {
			rst = "SP";
		} else if (fb.isEliminated_Blue == false && board[fb.getRowB()][fb.getColumnB()].equals("1")) {
			rst = "FB";
		} else if (sb.isEliminated_Blue == false && board[sb.getRowB()][sb.getColumnB()].equals("1")) {
			rst = "SB";
		} else if (fl.isEliminated_Blue == false && board[fl.getRowB()][fl.getColumnB()].equals("1")) {
			rst = "FL";
		} else {
			rst = null;
		}
		return rst;
	}

	String getMyPieceRed() {
		String rst;
		if (ma.isEliminated_Red == false && board[ma.getRowR()][ma.getColumnR()] == "1") {
			rst = "MA";
		} else if (fm.isEliminated_Red == false && board[fm.getRowR()][fm.getColumnR()] == "1") {
			rst = "FM";
		} else if (sm.isEliminated_Red == false && board[sm.getRowR()][sm.getColumnR()] == "1") {
			rst = "SM";
		} else if (fs.isEliminated_Red == false && board[fs.getRowR()][fs.getColumnR()] == "1") {
			rst = "FS";
		} else if (ss.isEliminated_Red == false && board[ss.getRowR()][ss.getColumnR()] == "1") {
			rst = "SS";
		} else if (ge.isEliminated_Red == false && board[ge.getRowR()][ge.getColumnR()] == "1") {
			rst = "GE";
		} else if (sp.isEliminated_Red == false && board[sp.getRowR()][sp.getColumnR()] == "1") {
			rst = "SP";
		} else if (fb.isEliminated_Red == false && board[fb.getRowR()][fb.getColumnR()] == "1") {
			rst = "FB";
		} else if (sb.isEliminated_Red == false && board[sb.getRowR()][sb.getColumnR()] == "1") {
			rst = "SB";
		} else if (fl.isEliminated_Red == false && board[fl.getRowR()][fl.getColumnR()] == "1") {
			rst = "FL";
		} else {
			rst = null;
		}
		return rst;
	}

	public LinkedList<LinkedList<Integer>> checkMoves(LinkedList<LinkedList<Integer>> moves) {
		for (int i = 0; i < piecesMovable.length; i++) {
			LinkedList<Integer> tempForward = new LinkedList<Integer>();
			LinkedList<Integer> tempBackward = new LinkedList<Integer>();
			LinkedList<Integer> tempLeft = new LinkedList<Integer>();
			LinkedList<Integer> tempRight = new LinkedList<Integer>();
			if (isRed) {
				if (piecesMovable[i] != null) {
					int r = getRowRed(piecesMovable[i]);
					int c = getColumnRed(piecesMovable[i]);
					String piece = getPiece(r, c);
					int pieceExc = getPieceExc(piece);
					if (piecesMovable[i] != null && pieceExc != 1 && pieceExc != -1 && checkNoObstaclesForwardRed(r, c, 1)) {
						tempForward.add(r);
						tempForward.add(c);
						tempForward.add(1);
						tempForward.add(1);
						allMoves.add(tempForward);
					}
					if (piecesMovable[i] != null && pieceExc != 2 && pieceExc != -1 && checkNoObstaclesBackwardRed(r, c, 2)) {
						tempBackward.add(r);
						tempBackward.add(c);
						tempBackward.add(2);
						tempBackward.add(1);
						allMoves.add(tempBackward);
					}
					if (piecesMovable[i] != null && pieceExc != 3 && pieceExc != -1 && checkNoObstaclesLeftRed(r, c, 3)) {
						tempLeft.add(r);
						tempLeft.add(c);
						tempLeft.add(3);
						tempLeft.add(1);
						allMoves.add(tempLeft);
					}
					if (piecesMovable[i] != null && pieceExc != 4 && pieceExc != -1 && checkNoObstaclesRightRed(r, c, 4)) {
						tempRight.add(r);
						tempRight.add(c);
						tempRight.add(4);
						tempRight.add(1);
						allMoves.add(tempRight);
					}
				}
			} else {
				if (piecesMovable[i] != null) {
					int r = getRowBlue(piecesMovable[i]);
					int c = getColumnBlue(piecesMovable[i]);
					String piece = getPiece(r, c);
					int pieceExc = getPieceExc(piece);
					if (piecesMovable[i] != null && pieceExc != 1 && pieceExc != -1 && checkNoObstaclesForwardBlue(r, c, 1)) {
						tempForward.add(r);
						tempForward.add(c);
						tempForward.add(1);
						tempForward.add(1);
						allMoves.add(tempForward);
					}
					if (piecesMovable[i] != null && pieceExc != 2 && pieceExc != -1 && checkNoObstaclesBackwardBlue(r, c, 2)) {
						tempBackward.add(r);
						tempBackward.add(c);
						tempBackward.add(2);
						tempBackward.add(1);
						allMoves.add(tempBackward);
					}
					if (piecesMovable[i] != null && pieceExc != 3 && pieceExc != -1 && checkNoObstaclesLeftBlue(r, c, 3)) {
						tempLeft.add(r);
						tempLeft.add(c);
						tempLeft.add(3);
						tempLeft.add(1);
						allMoves.add(tempLeft);
					}
					if (piecesMovable[i] != null && pieceExc != 4 && pieceExc != -1 && checkNoObstaclesRightBlue(r, c, 4)) {
						tempRight.add(r);
						tempRight.add(c);
						tempRight.add(4);
						tempRight.add(1);
						allMoves.add(tempRight);
					}
				}
			}
		}
		return moves;
	}

	@Override
	public int[] move() {
		allMoves = new LinkedList<LinkedList<Integer>>();
		allMoves = checkMoves(allMoves);
		int randomMove = (int) (Math.random() * allMoves.size());
		if (allMoves.size() == 0) {
			return null;
		} else {
			LinkedList<Integer> temp = allMoves.get(randomMove);
			int r = temp.get(0);
			int c = temp.get(1);
			int direction = temp.get(2);
			int steps = temp.get(3);
			lastPieceMoved = getPiece(r, c);
			moveArray[0] = r;
			moveArray[1] = c;
			moveArray[2] = direction;
			moveArray[3] = steps;
			if (isRed) {
				if (direction == 1) {
					setPrevDirectionF(lastPieceMoved);
					reloadCoordinatesR(lastPieceMoved, 1);
				} else if (direction == 2) {
					setPrevDirectionB(lastPieceMoved);
					reloadCoordinatesR(lastPieceMoved, 2);
				} else if (direction == 3) {
					setPrevDirectionL(lastPieceMoved);
					reloadCoordinatesR(lastPieceMoved, 3);
				} else if (direction == 4) {
					setPrevDirectionR(lastPieceMoved);
					reloadCoordinatesR(lastPieceMoved, 4);
				}
			} else {
				if (direction == 1) {
					setPrevDirectionF(lastPieceMoved);
					reloadCoordinatesB(lastPieceMoved, 1);
				} else if (direction == 2) {
					setPrevDirectionB(lastPieceMoved);
					reloadCoordinatesB(lastPieceMoved, 2);
				} else if (direction == 3) {
					setPrevDirectionL(lastPieceMoved);
					reloadCoordinatesB(lastPieceMoved, 3);
				} else if (direction == 4) {
					setPrevDirectionR(lastPieceMoved);
					reloadCoordinatesB(lastPieceMoved, 4);
				}
			}
			System.out.println("****MOVE****");
			printMatrix(board);
			return moveArray;
		}
	}

	String getPiece(int r, int c) {
		String rst = "";
		if (isRed) {
			if (ma.isEliminated_Red == false && ma.getRowR() == r && ma.getColumnR() == c) {
				rst = ma.getMa();
			} else if (fm.isEliminated_Red == false && fm.getRowR() == r && fm.getColumnR() == c) {
				rst = fm.getFm();
			} else if (sm.isEliminated_Red == false && sm.getRowR() == r && sm.getColumnR() == c) {
				rst = sm.getSm();
			} else if (fs.isEliminated_Red == false && fs.getRowR() == r && fs.getColumnR() == c) {
				rst = fs.getFs();
			} else if (ss.isEliminated_Red == false && ss.getRowR() == r && ss.getColumnR() == c) {
				rst = ss.getSs();
			} else if (ge.isEliminated_Red == false && ge.getRowR() == r && ge.getColumnR() == c) {
				rst = ge.getGe();
			} else if (sp.isEliminated_Red == false && sp.getRowR() == r && sp.getColumnR() == c) {
				rst = sp.getSp();
			}
		} else {
			if (ma.isEliminated_Blue == false && ma.getRowB() == r && ma.getColumnB() == c) {
				rst = ma.getMa();
			} else if (fm.isEliminated_Blue == false && fm.getRowB() == r && fm.getColumnB() == c) {
				rst = fm.getFm();
			} else if (sm.isEliminated_Blue == false && sm.getRowB() == r && sm.getColumnB() == c) {
				rst = sm.getSm();
			} else if (fs.isEliminated_Blue == false && fs.getRowB() == r && fs.getColumnB() == c) {
				rst = fs.getFs();
			} else if (ss.isEliminated_Blue == false && ss.getRowB() == r && ss.getColumnB() == c) {
				rst = ss.getSs();
			} else if (ge.isEliminated_Blue == false && ge.getRowB() == r && ge.getColumnB() == c) {
				rst = ge.getGe();
			} else if (sp.isEliminated_Blue == false && sp.getRowB() == r && sp.getColumnB() == c) {
				rst = sp.getSp();
			}
		}
		return rst;
	}

	int getPieceExc(String piece) {
		int rst = -1;
		switch (piece) {
		case "MA":
				rst = ma.getPrevDirection();
			break;
		case "FM":
				rst = fm.getPrevDirection();
			break;
		case "SM":
				rst = sm.getPrevDirection();
			break;
		case "FS":
				rst = fs.getPrevDirection();
			break;
		case "SS":
				rst = ss.getPrevDirection();
			break;
		case "GE":
				rst = ge.getPrevDirection();
			break;
		case "SP":
				rst = sp.getPrevDirection();
		case "":
			rst = -1;
			break;
		}
		return rst;
	}

	void reloadCoordinatesR(String i, int direction) {
		switch (i) {
		case "MA":
				reload_Ma_R(direction);
				break;
		case "FM":
				reload_Fm_R(direction);
				break;
		case "SM":
				reload_Sm_R(direction);
				break;
		case "FS":
				reload_Fs_R(direction);
				break;
		case "SS":
				reload_Ss_R(direction);
				break;
		case "GE":
				reload_Ge_R(direction);
				break;
		case "SP":
				reload_Sp_R(direction);
				break;
		}
	}

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

	void reloadCoordinatesB(String piece, int direction) {
		switch (piece) {
		case "MA":
				reload_Ma_B(direction);
				break;
		case "FM":
				reload_Fm_B(direction);
				break;
		case "SM":
				reload_Sm_B(direction);
				break;
		case "FS":
				reload_Fs_B(direction);
				break;
		case "SS":
				reload_Ss_B(direction);
				break;
		case "GE":
				reload_Ge_B(direction);
				break;
		case "SP":
				reload_Sp_B(direction);
				break;
		}
	}

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

	void printMatrix(String board[][]) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("|" + board[i][j]);
			}
			System.out.println();
		}
	}

	public void setPrevDirectionF(String i) {
		switch (i) {
		case "MA":
				ma.setPrevDirection(2);
				break;
		case "FM":
				fm.setPrevDirection(2);
				break;
		case "SM":
				sm.setPrevDirection(2);
				break;
		case "FS":
				fs.setPrevDirection(2);
				break;
		case "SS":
				ss.setPrevDirection(2);
				break;
		case "GE":
				ge.setPrevDirection(2);
				break;
		case "SP":
				sp.setPrevDirection(2);
				break;
		}
	}

	public void setPrevDirectionB(String i) {
		switch (i) {
		case "MA":
				ma.setPrevDirection(1);
				break;
		case "FM":
				fm.setPrevDirection(1);
				break;
		case "SM":
				sm.setPrevDirection(1);
				break;
		case "FS":
				fs.setPrevDirection(1);
				break;
		case "SS":
				ss.setPrevDirection(1);
				break;
		case "GE":
				ge.setPrevDirection(1);
				break;
		case "SP":
				sp.setPrevDirection(1);
				break;
		}
	}

	public void setPrevDirectionL(String i) {
		switch (i) {
		case "MA":
				ma.setPrevDirection(4);
				break;
		case "FM":
				fm.setPrevDirection(4);
				break;
		case "SM":
				sm.setPrevDirection(4);
				break;
		case "FS":
				fs.setPrevDirection(4);
				break;
		case "SS":
				ss.setPrevDirection(4);
				break;
		case "GE":
				ge.setPrevDirection(4);
				break;
		case "SP":
				sp.setPrevDirection(4);
				break;
		}
	}

	public void setPrevDirectionR(String i) {
		switch (i) {
		case "MA":
				ma.setPrevDirection(3);
				break;
		case "FM":
				fm.setPrevDirection(3);
				break;
		case "SM":
				sm.setPrevDirection(3);
				break;
		case "FS":
				fs.setPrevDirection(3);
				break;
		case "SS":
				ss.setPrevDirection(3);
				break;
		case "GE":
				ge.setPrevDirection(3);
				break;
		case "SP":
				sp.setPrevDirection(3);
				break;
		}
	}

	boolean checkNoObstaclesForwardBlue(int r, int c, int direction) {
		boolean rst = false;
		r = r - 1;
		if (r >= 0 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

	boolean checkNoObstaclesBackwardBlue(int r, int c, int direction) {
		boolean rst = false;
		r = r + 1;
		if (r <= 9 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

	boolean checkNoObstaclesLeftBlue(int r, int c, int direction) {
		boolean rst = false;
		c = c - 1;
		if (c >= 0 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

	boolean checkNoObstaclesRightBlue(int r, int c, int direction) {
		boolean rst = false;
		c = c + 1;
		if (c <= 9 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

	boolean checkNoObstaclesForwardRed(int r, int c, int direction) {
		boolean rst = false;
		r = r + 1;
		if (r <= 9 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

	boolean checkNoObstaclesBackwardRed(int r, int c, int direction) {
		boolean rst = false;
		r = r - 1;
		if (r >= 0 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

	boolean checkNoObstaclesLeftRed(int r, int c, int direction) {
		boolean rst = false;
		c = c + 1;
		if (c <= 9 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

	boolean checkNoObstaclesRightRed(int r, int c, int direction) {
		boolean rst = false;
		c = c - 1;
		if (c >= 0 && (board[r][c] == "1" || board[r][c] == "0")) {
			rst = true;
		}
		return rst;
	}

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

	@Override
	public int[] position(String piece) {
		int[] positions = new int[2];
		if (isRed) {
			if (piece == fb.getFb()) {
				positions[0] = fb.getRowR();
				positions[1] = fb.getColumnR();
			} else if (piece == fs.getFs()) {
				positions[0] = fs.getRowR();
				positions[1] = fs.getColumnR();
			} else if (piece == fm.getFm()) {
				positions[0] = fm.getRowR();
				positions[1] = fm.getColumnR();
			} else if (piece == fl.getFl()) {
				positions[0] = fl.getRowR();
				positions[1] = fl.getColumnR();
			} else if (piece == ge.getGe()) {
				positions[0] = ge.getRowR();
				positions[1] = ge.getColumnR();
			} else if (piece == ma.getMa()) {
				positions[0] = ma.getRowR();
				positions[1] = ma.getColumnR();
			} else if (piece == sb.getSb()) {
				positions[0] = sb.getRowR();
				positions[1] = sb.getColumnR();
			} else if (piece == ss.getSs()) {
				positions[0] = ss.getRowR();
				positions[1] = ss.getColumnR();
			} else if (piece == sm.getSm()) {
				positions[0] = sm.getRowR();
				positions[1] = sm.getColumnR();
			} else {
				positions[0] = sp.getRowR();
				positions[1] = sp.getColumnR();
			}
		} else {
			if (piece == fb.getFb()) {
				positions[0] = fb.getRowB();
				positions[1] = fb.getColumnB();
			} else if (piece == fs.getFs()) {
				positions[0] = fs.getRowB();
				positions[1] = fs.getColumnB();
			} else if (piece == fm.getFm()) {
				positions[0] = fm.getRowB();
				positions[1] = fm.getColumnB();
			} else if (piece == fl.getFl()) {
				positions[0] = fl.getRowB();
				positions[1] = fl.getColumnB();
			} else if (piece == ge.getGe()) {
				positions[0] = ge.getRowB();
				positions[1] = ge.getColumnB();
			} else if (piece == ma.getMa()) {
				positions[0] = ma.getRowB();
				positions[1] = ma.getColumnB();
			} else if (piece == sb.getSb()) {
				positions[0] = sb.getRowB();
				positions[1] = sb.getColumnB();
			} else if (piece == ss.getSs()) {
				positions[0] = ss.getRowB();
				positions[1] = ss.getColumnB();
			} else if (piece == sm.getSm()) {
				positions[0] = sm.getRowB();
				positions[1] = sm.getColumnB();
			} else {
				positions[0] = sp.getRowB();
				positions[1] = sp.getColumnB();
			}
		}
		return positions;
	}

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

	public void initPiecesMovable() {
		piecesMovable[0] = ma.getMa();
		piecesMovable[1] = fm.getFm();
		piecesMovable[2] = sm.getSm();
		piecesMovable[3] = fs.getFs();
		piecesMovable[4] = ss.getSs();
		piecesMovable[5] = ge.getGe();
		piecesMovable[6] = sp.getSp();
	}

	@Override
	public void start(boolean isFirst) {
		if (isFirst) {
			reloadPositionsR();
			isRed = true;
			initBoard(board);
			redPosition();
			initPiecesMovable();
		} else {
			reloadPositionsB();
			isRed = false;
			initBoard(board);
			bluePosition();
			initPiecesMovable();
		}
	}

	@Override
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

	@Override
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

}
