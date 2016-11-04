package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Monsters {
	
	private int size[] = {120,60};
	private int height;
    private int width;
    private Random rand;
    private Point point = new Point();
    private Vector2 pt;
    
    private String MAP[] = {
			".m......m.....m.",
			"................",
			"........m.......",
			"................",
			".m........m.....",
			"................",
			"................",
	};
//    private String MAP[] = {
//			".m............m.",
//			"................",
//			"...............",
//			"................",
//			".m........m.....",
//			"................",
//			"................",
//	};
	private boolean[][] hasMonster;
	private boolean[][] createdMonster;
	private float[][][] monsterPosition;

	public Monsters(){
		rand = new Random();
		height = MAP.length;
        width = MAP[0].length();
        initMonsters();
        monsterPosition = new float[height][width][3];
    }
	
	public void update(){
		pt = point.getPositionMouse();

		if (Gdx.input.isButtonPressed(Buttons.LEFT)){
			monsterKilled((int)pt.x, SoldierGame.HEIGHT-(int)pt.y);

			ShapeRenderer shapeRenderer = new ShapeRenderer();
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.circle(pt.x, SoldierGame.HEIGHT-pt.y, 20);
			shapeRenderer.end();
		}
		
	}
	
	private void initMonsters() {
		hasMonster = new boolean[height][width];
		createdMonster = new boolean[height][width];
        for(int r = 0; r < height; r++) {
            for(int c = 0; c < width; c++) {
            	hasMonster[r][c] = MAP[r].charAt(c) == 'm';
            	createdMonster[r][c] = hasMonster[r][c];
            }
        }
    }
	
	public boolean hasMonsterAt(int r, int c) {
        return hasMonster[r][c];
    }
	
	public boolean createdMonsterAt(int r, int c) {
		boolean old = createdMonster[r][c];
		createdMonster[r][c] = false;
        return old;
    }
	
	public int getSize(){
		int index = rand.nextInt(1);
		return size[index];
	}
	
	public int getHeight() {
        return height;
    }
 
    public int getWidth() {
        return width;
    }
    
    
    public void getPosition(int r, int c, float x, float y, int monsterSize){
    	monsterPosition[r][c][0] = x;
    	monsterPosition[r][c][1] = y;
    	monsterPosition[r][c][2] = monsterSize;
    }
    
    public void monsterKilled(int mouseX, int mouseY){
    	for(int r = 0; r < getHeight(); r++) {
			 for(int c = 0; c < getWidth(); c++) {		
				 if(hasMonsterAt(r, c)){
					 int x = (int)monsterPosition[r][c][0];
					 int y = (int)monsterPosition[r][c][1];
					 int monsterSize = 80;
					 
					 if(mouseX >= x-monsterSize && mouseX <= x+monsterSize && mouseY >= y-monsterSize && mouseY <= y+monsterSize){
						 System.out.println("x " + x + " " +mouseX);
						 System.out.println("y " + y + " " + mouseY);
						
						 System.out.println("===================");
						 hasMonster[r][c] = false;
					 }
				 }
			 }
    	}
    }
}
