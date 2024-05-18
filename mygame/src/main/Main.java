package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window= new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(100, 100);
        window.setResizable(false);
        window.setTitle("Welcome to the jungle");
        GamePanel gamepan=new GamePanel();
        window.add(gamepan);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepan.startgamethread();
	}
}