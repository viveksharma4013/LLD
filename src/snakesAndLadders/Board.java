package snakesAndLadders;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
	Cell [][] cells;
	int boardSize;
	public Board(int boardSize,int numberOfSnakes, int numberOfLadders) {
		this.boardSize = boardSize;
		initializeCells();
		addSnakesAndLadders(cells, numberOfSnakes, numberOfLadders);
	}

	private void initializeCells() {
		cells = new Cell[this.boardSize][this.boardSize];
		for(int i=0;i<this.boardSize;i++) {
			for(int j=0;j<this.boardSize;j++) {
				Cell cellObj = new Cell();
				cells[i][j] = cellObj;
			}
		}
	}

	private void addSnakesAndLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders) {
		while(numberOfSnakes>0){
			int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length-1)); 
			int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length-1));
			if(snakeTail>=snakeHead) {
				continue;
			}
			Jump jumpObj = new Jump(snakeHead,snakeTail);
			Cell cell = getCell(snakeHead);
			cell.jump = jumpObj;
			numberOfSnakes--;
		}
		
		while(numberOfLadders>0) {
			int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length-1)); 
			int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length-1));
			if(ladderTail>=ladderHead) {
				continue;
			}
			Jump jumpObj = new Jump(ladderHead,ladderTail);
			Cell cell = getCell(ladderHead);
			cell.jump = jumpObj;
			numberOfLadders--;
		}
	}
	
	private Cell getCell(int position){
		int row = position/this.boardSize;
		int col = position %this.boardSize;
		return cells[row][col];
	}
	
}
