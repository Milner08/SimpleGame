package com.milner.platformEngine.sprites.mob;

import java.awt.Color;

import com.milner.platformEngine.Game;
import com.milner.platformEngine.InputHandler;
import com.milner.platformEngine.graphix.SpriteSheet;

public class Player extends Mob {
	private static int[][] tmptiles = {{36,37},{68,69}};

	public Player(InputHandler input, SpriteSheet sheet, int sw, int sh) {
		super(input, sheet, tmptiles, (Game.WIDTH/16), (Game.HEIGHT/16), 2,2, Game.WIDTH, Game.HEIGHT, sw, sh);
		setColours(0,new Color(0x777777).getRGB(),new Color(0xBBBBBB).getRGB(),new Color(0x555555).getRGB());
	}
	

}
