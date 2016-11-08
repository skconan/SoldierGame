package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.f
public class WorldRenderer {
	private SoldierGame soldierGame;
	private World world;
	private Texture bgImg, bgImgView, bgImgTop, moon;
//	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
//	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	
	WorldRenderer(SoldierGame soldierGame, World world) {
		this.soldierGame = soldierGame;
		this.world = world;
		bgImg = new Texture("bg.png");
		bgImgTop = new Texture("bg_top.gif");
		bgImgView = new Texture("bg_view.png");
		moon = new Texture("moon.png");
//		font = new BitmapFont();
	}
	
	public void render(float delta) {
		
		Vector2 pt = world.point.getPositionMouse();
        SpriteBatch batch = soldierGame.batch;
        
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        batch.begin();
        batch.draw(bgImgTop, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
        batch.draw(bgImgView,(int) (0.07*pt.x-SoldierGame.WIDTH/2), 0, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(moon,(int) (0.01*pt.x-SoldierGame.WIDTH/2-200), 50, SoldierGame.WIDTH*2, SoldierGame.HEIGHT);
        batch.draw(bgImg, 0, 0, SoldierGame.WIDTH, SoldierGame.HEIGHT);
//        font.draw(batch, "SCORE : " + world.getScore(), SoldierGame.WIDTH-100, SoldierGame.HEIGHT-10);
        batch.end();
        
        world.soldierRenderer.render();
        world.pointRenderer.render();
        world.monstersRenderer.render(delta);
    }
}
