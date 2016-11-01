package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Monsters {
	private String fileMonsters[] = {"monster00.fw.png","monster01.fw.png",
									"monster02.fw.png","monster03.fw.png",
									"monster04.fw.png"};
	private int size[] = {80,40,30};
//	private int blood[] = {3,2,1,1};
	private int height;
    private int width;
    private Random rand;
    private String MAP[] = {
			".m...m..m.m...m.",
			"....m......m.m..",
			"..m...m.m..m..m.",
			"....m.....m...m.",
			".m...m..m.m...m.",
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
        System.out.println("dddddddddddddd");
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
	
	public Vector2 genMonster(){
		
		int sizeAndBlood = rand.nextInt(3);
		int fileMonster = rand.nextInt(4);
		Vector2 monsterData = new Vector2(fileMonster, sizeAndBlood);
		return monsterData; 
	}
	
	public boolean hasMonsterAt(int r, int c) {
        return hasMonster[r][c];
    }
	
	public boolean createdMonsterAt(int r, int c) {
		boolean old = createdMonster[r][c];
		createdMonster[r][c] = false;
        return old;
    }
	
	public String getName(){
		int index = rand.nextInt(3);
		return fileMonsters[index];
	}
	
	public int getSize(){
		int index = rand.nextInt(3);
		return size[index];
	}
	
	public int getHeight() {
        return height;
    }
 
    public int getWidth() {
        return width;
    }
    
}
