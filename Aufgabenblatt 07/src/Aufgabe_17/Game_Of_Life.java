package Aufgabe_17;

//author: Jan Philipp Bamberger, Stephan Malzkorn, Leon Peulings

public class Game_Of_Life {
	
	private boolean[][] gameField;
	private int size;
	
	public Game_Of_Life(int size){
		this.size = size;
		this.init();
	}
	
	protected boolean getField(int i, int j){
		return gameField[i][j];
	}
	
	protected void step(){
		int count;
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j < gameField[i].length; j++) {
				count = this.count(i,j);
				switch (count) {
				case 0:
				case 1:
					gameField[i][j] = false;
					break;
				case 3:
					gameField[i][j] = true;
					break;
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					gameField[i][j] = false;
					break;
				default:
					break;
				}
			}
		}
	}

	private int count(int i, int j) {
		int temp = 0;
		if (gameField[i-1<0 ? size-1 : i-1][j-1<0 ? size-1 : j-1] == true) {
			temp ++;
		}
		if (gameField[i+1>size-1 ? 0 : i+1][j+1>size-1 ? 0 : j+1] == true) {
			temp ++;
		}
		if (gameField[i+1>size-1 ? 0 : i+1][j-1<0 ? size-1 : j-1] == true) {
			temp ++;
		}
		if (gameField[i-1<0 ? size-1 : i-1][j+1>size-1 ? 0 : j+1] == true) {
			temp ++;
		}
		if (gameField[i-1<0 ? size-1 : i-1][j] == true) {
			temp ++;
		}
		if (gameField[i+1>size-1 ? 0 : i+1][j] == true) {
			temp ++;
		}
		if (gameField[i][j-1<0 ? size-1 : j-1] == true) {
			temp ++;
		}
		if (gameField[i][j+1>size-1 ? 0 : j+1] == true) {
			temp ++;
		}
		return temp;
	}
	
	protected void setField(boolean value, int i, int j){
		gameField[i][j] = value;
	}
	
	protected void print(){
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j < gameField[i].length; j++) {
				System.out.print(gameField[i][j]);
			}
			System.out.println();
		}
	}

	protected void init() {
		int temp = 0;
		gameField = new boolean[size][size];
		do {
			for (int i = 0; i < gameField.length; i++) {
				for (int j = 0; j < gameField[i].length; j++) {
					if ((Math.random() * 2)<1) {
						gameField[i][j] = true;
						temp ++;
					} else {
						gameField[i][j] = false;
					}
				}
			}
		} while (temp < (0.25 * Math.pow(size, 2)));
	}
	
}
