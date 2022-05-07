package enemy;


import javax.swing.*;
import java.awt.*;

public class Alien {
    public Image image = new ImageIcon("src/images/alien-3.png").getImage();
    public int width = image.getWidth(null);
    public int height = image.getHeight(null);
    public int x;
    public int y;

    public Alien(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move() {
        this.y += 3;
    }
}

