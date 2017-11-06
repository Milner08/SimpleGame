package com.milner.platformEngine.level;

import java.util.ArrayList;
import java.util.Random;

import com.milner.platformEngine.Game;
import com.milner.platformEngine.InputHandler;
import com.milner.platformEngine.graphix.Sprite;
import com.milner.platformEngine.graphix.SpriteSheet;
import com.milner.platformEngine.sprites.Grass;
import com.milner.platformEngine.sprites.Rock;
import com.milner.platformEngine.sprites.Water;
import com.milner.platformEngine.sprites.WaterCorner;
import com.milner.platformEngine.sprites.WaterEdge;
import com.milner.platformEngine.sprites.mob.Player;

public class Level {
	private SpriteSheet sheet;
	public final int w, h;
	public int[][] tiles;
	private Random random = new Random();
	public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public Player player;
	private int playerX, playerY;
	boolean run = false;
	
	public Level(int w, int h, SpriteSheet spriteSheet,InputHandler input) {
		this.sheet = spriteSheet;
		this.w = w;
		this.h = h;
		tiles = new int[w][h];
		player = new Player(input, sheet, w, h);
		Sprite tmp;
		for(int y = 0; y<(h/8); y++){
			for(int x = 0; x < (w/8); x++){
				if(y < Game.HEIGHT/16){
					tmp = new Water(sheet, x,y);
				}else if(y >(h/8-Game.HEIGHT/16)){
					tmp = new Water(sheet, x,y);
				}else if(x <(Game.WIDTH/16)){
					tmp = new Water(sheet, x,y);
				}else if(x >(w/8-Game.WIDTH/16)){
					tmp = new Water(sheet, x,y);
				}else
					
				if(y == (Game.HEIGHT/16)){
					if(x == Game.WIDTH/16){
						tmp = new WaterCorner(sheet,x,y);
					}else if(x == w/8 - Game.WIDTH/16){
						tmp = new WaterCorner(sheet,x,y);
						tmp.rotateCW(90);
					}else{
						tmp = new WaterEdge(sheet, x,y);
						tmp.rotateCW(90);
					}
				}else if(y == (h/8-Game.HEIGHT/16)){
					if(x == Game.WIDTH/16){
						tmp = new WaterCorner(sheet,x,y);
						tmp.rotateCW(270);
					}else if(x == w/8 - Game.WIDTH/16){
						tmp = new WaterCorner(sheet,x,y);
						tmp.rotateCW(180);
					}else{
						tmp = new WaterEdge(sheet, x,y);
						tmp.rotateCW(270);
					}
				}else if(x == (Game.WIDTH/16)){
					tmp = new WaterEdge(sheet, x,y);
				}else if(x == (w/8-Game.WIDTH/16)){
					tmp = new WaterEdge(sheet, x,y);
					tmp.rotateCW(180);
				}
				
				else{
					tmp = new Grass(sheet, x, y);
					if(random.nextInt(50)==2){
						tmp = new Rock(sheet, x, y);
					}
				}
				
				sprites.add(tmp);
			}
		}
		player.place(sprites);
	}

	public int[][] render() {
		for(Sprite sprite : sprites){
			int[][] tmp = sprite.render();
			int x = sprite.x;
			int y = sprite.y;
			for (int i = 0; i < tmp.length; i++) {
				int[] tmp2 = tmp[i];
				for (int j = 0; j < tmp2.length; j++) {
					tiles[i + x][j + y] = tmp[i][j];
				}
			}
		}
		int[][] playertiles = new int[player.w][player.h];
		playertiles = player.render();
		playerX = player.xOffset + player.x - (player.w/2);
		playerY = player.yOffset + player.y - (player.h/2);
		for (int x = 0; x < player.w; x++){
			for(int y = 0; y < player.h; y++){
				if(playertiles[x][y] != 0){
					tiles[playerX + x][playerY + y] = playertiles[x][y];
				}
				
			}
		}
		return tiles;
	}
	
	public void tick(){
		player.move(sprites);
	}

}