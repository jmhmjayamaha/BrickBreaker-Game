package lk.harshana;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayGame extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballX = 120;
	private int ballY = 310;
	private int ballXDir = -1;
	private int ballYDir = -2;
	
	private MapGenerator mapGenerator;
	
	public PlayGame() {
		mapGenerator = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 692, 592);
		
		// drawing map
		mapGenerator.draw((Graphics2D)g);
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 591);
		
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
		
		//score
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("score : " + score, 540, 30);
		
		if(totalBricks <= 0) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.drawString("You Won",190, 300);
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Press enter to return", 190, 350);
		}
		
		if(ballY > 570) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.drawString("Game over, Score : " + score	, 190, 300);
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Press enter to return", 190, 350);
		}
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballX, ballY, 20,20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYDir = -ballYDir;
			}
			
			A : for(int i = 0 ; i < mapGenerator.map.length;i++) {
				for(int j = 0; j < mapGenerator.map[0].length;j++) {
					if(mapGenerator.map[i][j] > 0) {
						int brickX = j * mapGenerator.brickWidth + 80;
						int brickY = i * mapGenerator.brickHeight + 50;
						int brickWidth = mapGenerator.brickWidth;
						int brickHeight = mapGenerator.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickHeight, brickWidth);
						Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							mapGenerator.setBrickValue(i, j, 0);
							totalBricks--;
							score += 5;
							
							if(ballX +19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width ) {
								ballXDir = -ballXDir;
							}else {
								ballYDir = -ballYDir;
							}
							
							break A;
						}
					}
				}
			}
			
			ballX += ballXDir;
			ballY += ballYDir;
			if(ballX < 0) {
				ballXDir = -ballXDir;
			}
			if(ballY < 0) {
				ballYDir = -ballYDir;
			}
			if(ballX > 670 ) {
				ballXDir = -ballXDir;
			}
		}
		
		repaint();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballX = 120;
				ballY = 310;
				ballXDir = -1;
				ballYDir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				mapGenerator = new MapGenerator(3, 7);
				
				repaint();
			}
		}
	}
	
	public void moveRight() {
		play = true;
		playerX += 20;
	}
	
	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

}
