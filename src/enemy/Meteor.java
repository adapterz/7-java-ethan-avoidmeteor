package enemy;

import javax.swing.*;
import java.awt.*;

public class Meteor {
    public Image image = new ImageIcon("src/images/falling-asteroid.png").getImage();
    public int width = image.getWidth(null);
    public int height = image.getHeight(null);
    public int x;
    public int y;
    int speed = 3;

    public Meteor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        this.x += speed;
    }
}
