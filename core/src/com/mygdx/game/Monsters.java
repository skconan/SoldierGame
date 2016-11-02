package com.mygdx.game;

import java.util.Random;

public class Monsters {
	
	private int size[] = {80,50};
//	private int blood[] = {3,2,1,1};
	private int height;
    private int width;
    private Random rand;
    private String MAP[] = {
			".m......m.....m.",
			"................",
			"........m.......",
			"................",
			".m........m.....",
			"................",
			"................",
	};
	
	private boolean[][] hasMonster;
	private boolean[][] createdMonster;
	
	public Monsters(){
		rand = new Random();
		height = MAP.length;
        width = MAP[0].length();
        initMonsters();
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
    
//    public boolean hitMonster(){
//    	
//    }
}
