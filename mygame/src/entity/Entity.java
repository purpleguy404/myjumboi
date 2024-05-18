package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed;
    public BufferedImage upright1,upright2,upleft1,upleft2,downleft,downright,left1,left2,right1,right2,standright,standleft;
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea;
    public boolean collisionOn=false;
}
