package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    
    public TileManager(GamePanel gp) {
    	this.gp=gp;
    	tile=new Tile[10];
    	getTileImage();
    	mapTileNum=new int[gp.maxscreencol][gp.maxscreenrow];
    	loadMap();
    }
    public void getTileImage() {
    	try {
    		tile[0]=new Tile();
    		tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tileset/square1.png"));
    		tile[1]=new Tile();
    		tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tileset/square3.png"));
    		tile[2]=new Tile();
    		tile[2].image=ImageIO.read(getClass().getResourceAsStream("/tileset/square5.png"));
    		tile[2].collision=true;
    		}catch(IOException e) {
    		e.printStackTrace();
    	}		
    }
    public void loadMap() {
    	try {
    		InputStream is=getClass().getResourceAsStream("/map/map.txt");
    		BufferedReader br=new BufferedReader(new InputStreamReader(is));
    		int col=0;
    		int row=0;
    		while(col<gp.maxscreencol&&row<gp.maxscreenrow) {
    			String line=br.readLine();
    			while(col<gp.maxscreencol) {
    				String numbers[]=line.split(" ");
    				int num= Integer.parseInt(numbers[col]);
    				mapTileNum[col][row]=num;
    				col++;
    			}
    			if(col==gp.maxscreencol) {
    				col=0;
    				row++;
    			}
    		}
    		br.close();
    	}catch(Exception e) {
    		
    	}
    }
    public void draw(Graphics2D g2) {
    	int col=0;
    	int row=0;
    	int x=0;
    	int y=0;
    	while(col<gp.maxscreencol&&row<gp.maxscreenrow) {
    		int tileNum=mapTileNum[col][row];
    		if(tileNum<3) g2.drawImage(tile[tileNum].image, x, y,gp.tilesize,gp.tilesize, gp);
    		col++;
    		x+=gp.tilesize;
    		if(col==gp.maxscreencol) {
    			col=0;
    			x=0;
    			row++;
    			y+=gp.tilesize;
    		}
    	}
    }
}
