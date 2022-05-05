import javax.swing.*;
import java.awt.*;

public class Alien extends Obstacle{
    Image image = new ImageIcon("src/images/alien-3.png").getImage();
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int x, y;

    public Alien(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move() {
        this.y += 3;
    }
}

