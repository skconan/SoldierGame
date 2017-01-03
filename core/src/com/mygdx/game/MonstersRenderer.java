package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MonstersRenderer {
	private SpriteBatch batch;
	private Texture[] monsterImg = new Texture[10];
	private Texture monsterImgRender;
	private String fileMonsters[] = {"monster00.fw.png", "monster01.fw.png", "monster02.fw.png", "bossAtk.fw.png"};
	private World world;
	private Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/sound.mp3"));
	private int[] monstersProperty = new int[5];
	private BitmapFont font;
	private int size, x, y, blood, indexImg;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public MonstersRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.batch = batch;
		for(int i=0; i < fileMonsters.length; i++) {
			monsterImg[i] = new Texture(fileMonsters[i]);
		}
		font = new BitmapFont();
		sound.loop();
	}
	
	public void render() {
		for (int r = 0; r < world.monsters.mapHeight; r++) {
			 for (int c = 0; c < world.monsters.mapWidth; c++) {				
				if (world.monsters.hasMonsterAt(r, c)) {
					if (world.monsters.createdMonsterAt(r, c)) {
						world.monsters.genMonsters(r, c);
					}
					monstersProperty = world.monsters.getMonsters(r, c);
					size = monstersProperty[0];
					x = monstersProperty[1];
					y = monstersProperty[2];
					blood = monstersProperty[3];
					indexImg = monstersProperty[4];
					world.soldier.hitMonsters(x, y);
					monsterImgRender = monsterImg[indexImg];
					batch.begin();
					font.draw(batch, "HP : " + blood, x-size/2, y+size/2 + 10);
					batch.draw(monsterImgRender, x-size/2, y-size/2, size, size);
					batch.end();
					if(world.score >= 60) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(1, 1, 0, (float) 30);
						shapeRenderer.circle(x+world.monsters.speedCircle, y-world.monsters.speedCircle, 20);
						shapeRenderer.end();
						world.soldier.hitCircle(x+world.monsters.speedCircle, y-world.monsters.speedCircle);
					}
				}
			 }
		}
    }
}