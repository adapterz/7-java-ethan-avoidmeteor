import javax.swing.*;
import java.awt.*;

public class Meteor extends Obstacle {
    Image image = new ImageIcon("src/images/falling-asteroid.png").getImage();
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int x, y;
    int speed = 3;

    public Meteor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        this.x += speed;
    }
}
