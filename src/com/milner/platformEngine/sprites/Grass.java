package com.milner.platformEngine.sprites;

import java.awt.Color;
import java.util.Random;

import com.milner.platformEngine.graphix.Sprite;
import com.milner.platformEngine.graphix.SpriteSheet;

public class Grass extends Sprite{
	private static int[][] tmptiles = {{33}};

	public Grass(SpriteSheet sheet, int x, int y){
		super(sheet, tmptiles, x,y,1,1);
		setColours(new Color(83,184,73).getRGB(), Color.GREEN.getRGB());
	}
}
