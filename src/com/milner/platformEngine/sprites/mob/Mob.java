package com.milner.platformEngine.sprites.mob;

import java.util.ArrayList;
import java.util.Random;

import com.milner.platformEngine.Game;
import com.milner.platformEngine.InputHandler;
import com.milner.platformEngine.graphix.Sprite;
import com.milner.platformEngine.graphix.SpriteSheet;

public class Mob extends Sprite {
	public int yOffset = 0;
	public int xOffset = 0;
	public int newXOffset = 0;
	public int newYOffset = 0;
	public int mw, mh,sw,sh;
	public int paddingX1 = 3,
			paddingX2 = 3,
			paddingY1 = 6,
			paddingY2 = 0;
	public double moveDist = 1.5;
	
	
	protected InputHandler input;

	Random r = new Random();

	public Mob(InputHandler input, SpriteSheet sheet, int[][] tiles, int x, int y, int w, int h, int sw, int  sh, int mw, int mh){
		super(sheet, tiles, x, y, w, h);
		this.input = input;
		this.mw = mw;
		this.mh = mh;
		this.sw = sw;
		this.sh = sh;
	}
	public Mob(){
	}
	public void place(ArrayList<Sprite> sprites){
		newXOffset = Math.abs(r.nextInt(mw-Game.WIDTH)-(Game.WIDTH*2));
		newYOffset = Math.abs(r.nextInt(mh-Game.HEIGHT)-(Game.HEIGHT*2));
		System.out.println("x "+newXOffset+ " , "+newYOffset);
		if(collisionDetection(sprites)){
			place(sprites);
		}else{
			acceptMove();
		}
	}

	public void move(ArrayList<Sprite> sprites){
		tryMove();
		if(collisionDetection(sprites)){
			rejectMove();
		}else{
			acceptMove();
		}
	}
	public void tryMove(){
		newYOffset = yOffset;
		newXOffset = xOffset;
		if(input.up.down){
			newYOffset-=moveDist;
		}
		if(input.down.down){
			newYOffset+=moveDist;
		}
		if(input.left.down){
			newXOffset-=moveDist;
		}
		if(input.right.down){
			newXOffset+=moveDist;
		}
		if(newXOffset < 0) newXOffset = 0;
		else if (newXOffset > mw-sw) newXOffset = (mw-sw);
		if(newYOffset < 0) newYOffset = 0;
		else if (newYOffset > mh-sh) newYOffset = (mh-sh);
	}
	
	public void acceptMove(){
		xOffset = newXOffset;
		yOffset = newYOffset;
	}
	
	public void rejectMove(){
		newXOffset = xOffset;
		newYOffset = yOffset;
	}
	
	public boolean collisionDetection(ArrayList<Sprite> sprites){		
		int playerX = newXOffset + x - w/2 ;
		int playerY = newYOffset + y - h/2;
		int playerMaxX = playerX+w;
		int playerMaxY = playerY+h;
		String directx = "", directy = "";
		if(newXOffset - xOffset > 0){
			directx = "down";
		}else if(newXOffset - xOffset < 0){
			directx = "up";
		}else{
			directx = "";
		}
		if(newYOffset - yOffset > 0){
			directy = "right";
		}else if(newYOffset - yOffset < 0){
			directy = "left";
		}else{
			directy = "";
		}
		boolean collision = false;
		if(directx == "down"){
			for(int y = playerY + paddingY1; y < playerMaxY - paddingY2; y++){
				for(int x = playerMaxX - (int)Math.round(moveDist) - paddingX2; x < playerMaxX - paddingX2; x++){
					int tileX = (int)Math.floor((x)/8);
					int tileY = (int)Math.floor(y/8);
					int spriteIndex = (tileX + tileY * (mw/8));
					if(sprites.get(spriteIndex).isPassable()){
					
					}else{
						collision = true;
					}
				}
			}
		}else if(directx == "up"){
			for(int y = playerY + paddingY1; y < playerMaxY - paddingY2; y++){
				for(int x = playerX + paddingX1; x < playerX + (int)Math.round(moveDist) + paddingX1; x++){
					int tileX = (int)Math.floor((x)/8);
					int tileY = (int)Math.floor(y/8);
					int spriteIndex = (tileX + tileY * (mw/8));
					if(sprites.get(spriteIndex).isPassable()){
					
					}else{
						collision = true;
					}
				}
			}
		}
		if(directy == "right"){
			for(int y = playerY + paddingY2; x < playerX + (int)Math.round(moveDist) - paddingX2; x++){
				for(int x = playerX + paddingX1; x < playerMaxX - paddingX2; x++){
					int tileX = (int)Math.floor((x)/8);
					int tileY = (int)Math.floor(y/8);
					int spriteIndex = (tileX + tileY * (mw/8));
					if(sprites.get(spriteIndex).isPassable()){
					
					}else{
						collision = true;
					}
				}
			}
		}else if(directy == "left"){
			for(int y = playerY + paddingY1; y < playerMaxY - paddingY2; y++){
				for(int x = playerX + paddingX1; x < playerMaxX - paddingX2; x++){
					int tileX = (int)Math.floor((x)/8);
					int tileY = (int)Math.floor(y/8);
					int spriteIndex = (tileX + tileY * (mw/8));
					if(sprites.get(spriteIndex).isPassable()){
					
					}else{
						collision = true;
					}
				}
			}
		}
		return collision;
		
	}
}
