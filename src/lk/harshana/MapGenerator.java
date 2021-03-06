package lk.harshana;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	public MapGenerator(int raw, int col) {
		map = new int[raw][col];
		
		for(int i = 0;i < map.length;i++) {
			for(int j = 0 ; j<map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		
		brickWidth = 540/ col;
		brickHeight = 150/ raw;
		
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0;i < map.length;i++) {
			for(int j = 0 ; j<map[0].length; j++) {
				if(map[i][j] > 0) {
					if(j % 2 == 0 ) {
						g.setColor(Color.WHITE);
					} else {
						g.setColor(Color.cyan);
					}
					
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int raw, int col, int value) {
		map[raw][col] = value;
	}
}
