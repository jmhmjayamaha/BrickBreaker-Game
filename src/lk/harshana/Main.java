package lk.harshana;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame obj = new JFrame();
		PlayGame playGame = new PlayGame();
		obj.setBounds(10,10,700,600);
		obj.setTitle("Harshana's BrickBreak Game");
		obj.setVisible(true);
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(playGame);
	}

}
