package com.milner.platformEngine.sprites;

import java.awt.Color;

import com.milner.platformEngine.graphix.Sprite;
import com.milner.platformEngine.graphix.SpriteSheet;

public class Rock extends Sprite{
	static int[][] tmptiles2 = {{35}};

	public Rock(SpriteSheet sheet, int x, int y){
		super(sheet, tmptiles2, x,y,1,1);
		setPassable(false);
		setColours(new Color(83,184,73).getRGB(), Color.GRAY.getRGB(), Color.DARK_GRAY.getRGB());
	}
}
