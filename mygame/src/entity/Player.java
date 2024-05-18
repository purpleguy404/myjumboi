package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Keyhandler;

public class Player extends Entity{
    GamePanel gp;
    Keyhandler keyH;
    
    //public Player(GamePanel gp,Keyhandler keyH) {
    	//this.gp=gp;
    	//this.keyH=keyH;
    	
    	//solidArea=new Rectangle(8,0,gp.tilesize-16,gp.tilesize);
    	
    	
    	//setDefaultValues();
    	//getplayerimage();
    //}
    public double velx; // Vận tốc theo trục x của người chơi
    public double vely; // Vận tốc theo trục y của người chơi
    public double JumpVelocity = 8.0; // Độ lớn vận tốc khi nhảy
    public boolean Falling = false; // Biến boolean để xác định xem người chơi có đang rơi không
    
    
    public Player(GamePanel gp,Keyhandler keyH) {
    	this.gp=gp;
    	this.keyH=keyH;
    	setDefaultValues();
    	getplayerimage();
    }
    public void setDefaultValues() {
    	x=100;
    	y=100;
    	speed=5;
    	direction="down";
    }
    public void getplayerimage() {
    	try {
    		left1=ImageIO.read(getClass().getResourceAsStream("/playerima/leftwalk1.png"));
    		left2=ImageIO.read(getClass().getResourceAsStream("/playerima/leftwalk2.png"));
    		right1=ImageIO.read(getClass().getResourceAsStream("/playerima/rightwalk1.png"));
    		right2=ImageIO.read(getClass().getResourceAsStream("/playerima/rightwalk2.png"));
    		upright1=ImageIO.read(getClass().getResourceAsStream("/playerima/uppingright1.png"));
    		upright2=ImageIO.read(getClass().getResourceAsStream("/playerima/uppingright2.png"));
    		downleft=ImageIO.read(getClass().getResourceAsStream("/playerima/downleft1.png"));
    		downright=ImageIO.read(getClass().getResourceAsStream("/playerima/downright1.png"));
    		standright=ImageIO.read(getClass().getResourceAsStream("/playerima/standright.png"));
    		standleft=ImageIO.read(getClass().getResourceAsStream("/playerima/standleft.png"));
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }	
    public void update() {
    	if(keyH.uppress&&y>=0) {
    		direction="up";
			y-=speed;
		}
		else if(keyH.downpress&&y<=gp.screenwid-gp.tilesize) {
			direction="down";
			y+=speed;
		}
		else if(keyH.leftpress&&x>=0) {
			direction="left";
			x-=speed;
		}
		else if(keyH.rightpress&&x<=gp.screenhei-gp.tilesize) {
			direction="right";
			x+=speed;
		}
		else direction="stand";
    	collisionOn=false;
    	gp.cChecker.checkTile(this);
		spriteCounter++;
		if(spriteCounter>12) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
    }
    public void draw(Graphics2D g2) {
    	BufferedImage image=null;
    	switch(direction) {
    	case "up":
    		/*if(spriteNum==1) {
    			image=up1;
    		}
    		if(spriteNum==2) {
    			image=up2;
    		}*/
    		image=upright1;
    		break;
    		
    	case "left":
    		if(spriteNum==1) {
    			image=left1;
    		}
    		if(spriteNum==2) {
    			image=left2;
    		}
    		break;
    	case "right":
    		if(spriteNum==1) {
    			image=right1;
    		}
    		if(spriteNum==2) {
    			image=right2;
    		}
    		break;
    	case "down":
    		image=downleft;
    		break;
    	case "stand":
    		image=standright;
    		break;
    	}
    	g2.drawImage(image, x, y, gp.tilesize*2, gp.tilesize*2,null);
    }
}
