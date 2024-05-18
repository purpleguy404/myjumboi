package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originTilesize = 20;
    final int scale = 2;
    public int tilesize = originTilesize * scale;
    public int maxscreencol = 48;
    public int maxscreenrow = 27;
    public int screenwid = tilesize * maxscreencol;
    public int screenhei = tilesize * maxscreenrow;
    Keyhandler keyH = new Keyhandler();
    
    Thread gamethread;
    public CollisionChecker cChecker=new CollisionChecker(this);
    Player player = new Player(this, keyH);
    int playerX = 100;
    int playerY = 100;
    int fps = 60;
    int speed = 5;
    TileManager tileM=new TileManager(this);
    
    // Đường dẫn đến tệp tin hình ảnh nền
    private Image backgroundImage;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenwid, screenhei));

        // Tải hình ảnh nền từ package có sẵn
        try {
            // Lấy đường dẫn của tệp hình ảnh nền trong package
            
            backgroundImage =ImageIO.read(getClass().getResourceAsStream("/map/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startgamethread() {
        gamethread = new Thread(this);
        gamethread.start();
    }

    public void run() {
        double drawinterval = 1000000000 / fps;
        double delta = 0;
        long lasttime = System.nanoTime();
        long currenttime;
        long timer = 0;
        int drawcount = 0;
        while (gamethread != null) {
            currenttime = System.nanoTime();
            delta += (currenttime - lasttime) / drawinterval;
            timer += (currenttime - lasttime);
            lasttime = currenttime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawcount++;
            }
            if (timer >= 1000000000) {
                System.out.println("fps:" + drawcount);
                drawcount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Vẽ hình ảnh nền
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 200, 200, this);
        }
        tileM.draw(g2);
        // Vẽ người chơi
        player.draw(g2);
        g2.dispose();
    }
}



