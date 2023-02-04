package snakesAndLadders;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
	
	Board board;
	Dice dice;
	Deque<Player> playerList = new LinkedList<>();
	Player winner;
	
	public Game() {
		initializeGame();
	}
	
	private void initializeGame() {
		board = new Board(10, 4, 4);
		dice = new Dice(1);
		winner= null;
		addPlayers();
	}
	
	private void addPlayers() {
		Player player1 = new Player("p1",0); 
		Player player2 = new Player("p2",0);
		this.playerList.add(player1);
		this.playerList.add(player2);
	}
	
	public void startGame() {
		while(winner==null) {
			//get player who's chance it is
			Player player = getPlayerTurn();
			//roll the dice
			int diceNumber = dice.diceRoll();
			//get the newPosition after dice roll
			System.out.println("Player "+ player.playerId +"currentPosition is"+player.currentPosition);
			int newPosition = player.currentPosition + diceNumber;
			newPosition = jumpCheck(newPosition);
			player.currentPosition = newPosition;
			System.out.println("Player "+ player.playerId +"newPosition is"+player.currentPosition);
			if(newPosition >= board.cells.length*board.cells.length-1) {
				winner = player;
			}
		}
		System.out.println("Winner is "+ winner.playerId);
	}
	
	public Player getPlayerTurn() {
		Player playerTurn = playerList.removeFirst();
		playerList.addLast(playerTurn);
		return playerTurn;		
	}
	
	public int jumpCheck(int position) {
		int row =position/board.boardSize;
		int column =position%board.boardSize;
		Cell cellObj = board.cells[row][column];
		if(cellObj.jump!=null && cellObj.jump.start == position) {
			return position + cellObj.jump.end;
		}
		return position;
	}

}
