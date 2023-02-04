package snakesAndLadders;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
	int diceCount;
	
	public Dice(int diceCount) {
		this.diceCount = diceCount;
	}
	
	public int diceRoll() {
		int totalSum = 0;
		int diceUsed = 0;
		while(diceUsed < diceCount) {
			totalSum += ThreadLocalRandom.current().nextInt(1,6);
			diceUsed++;
		}
		return totalSum;
	}
}