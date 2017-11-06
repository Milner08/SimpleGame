package com.milner.platformEngine.sprites;

import java.awt.Color;
import java.util.Random;

import com.milner.platformEngine.graphix.Sprite;
import com.milner.platformEngine.graphix.SpriteSheet;

public class Water extends Sprite{
	private static int[][] tmptiles = {{40}};

	public Water(SpriteSheet sheet, int x, int y){
		super(sheet, tmptiles, x,y,1,1);
		setColours(new Color(0x00000FF).getRGB(), new Color(0x111144).getRGB());
		setPassable(false);
	}
}
