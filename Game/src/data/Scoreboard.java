package data;

import static helpers.Artist.drawNumbers;
import static helpers.Artist.loadTexture;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;


public class Scoreboard {
	private int leftPaddlePoints,rightPaddlePoints,leftPaddleRounds,rightPaddleRounds;
	private static boolean gameOver = false;
	private Texture numbers;
	private int pointsPerGame,totalRounds;
	

	public Scoreboard(int pointsPerGame, int totalRounds) {
		this.pointsPerGame = pointsPerGame;
		this.totalRounds = totalRounds;
		numbers = loadTexture("res/numbers.png","PNG");
		
	}

	public void update(){
		drawNumbers(numbers,leftPaddlePoints,Display.getWidth()/2 - 420,50);
		drawNumbers(numbers,leftPaddleRounds,Display.getWidth()/2 - 300,50);
		
		drawNumbers(numbers,rightPaddlePoints,Display.getWidth()/2 + 180,50);
		drawNumbers(numbers,rightPaddleRounds,Display.getWidth()/2 + 300,50);
	}
	
	public void newRound(){
		if(leftPaddleRounds >= totalRounds && leftPaddleRounds > rightPaddleRounds || rightPaddleRounds >= totalRounds && rightPaddleRounds > leftPaddleRounds){
			if(leftPaddleRounds > rightPaddleRounds){
				Boot.getSfx().get("clapping").play();
			}else{
				Boot.getSfx().get("booing").play();
			}
			gameOver = true;
			Boot.setGameState(2);
		}
	}

	public void addPoint(String paddle) {
		if(paddle.equals("PaddleLeft")){
			leftPaddlePoints++;
			if(leftPaddlePoints >= pointsPerGame){
				Boot.getSfx().get("round").play();
				leftPaddlePoints = 0;
				leftPaddleRounds++;
				newRound();
			}else{
				Boot.getSfx().get("score").play();
			}
		}else{
			rightPaddlePoints++;
			if(rightPaddlePoints >= pointsPerGame){
				Boot.getSfx().get("round").play();
				rightPaddlePoints = 0;
				rightPaddleRounds++;
				newRound();
			}else{
				Boot.getSfx().get("score").play();
			}
		}
		System.out.println("Human: Points = " + leftPaddlePoints + ",Rounds = " + leftPaddleRounds);
		System.out.println("AI: Points = " + rightPaddlePoints + ",Rounds = " + rightPaddleRounds);
		System.out.println();
	}
	
	public static boolean isGameOver() {
		return gameOver;
	}
	
	public void reset(){
		leftPaddlePoints = 0;
		rightPaddlePoints = 0;
		leftPaddleRounds = 0;
		rightPaddleRounds = 0;
	}
}
