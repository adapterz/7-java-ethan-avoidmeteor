import javax.swing.*;
import java.awt.*;

public class Meteor extends Obstacle {

    Image image = new ImageIcon("src/images/falling-asteroid.png").getImage();
    int x, y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    // 흩어질 개수
    int numberOfMeteor;

    public Meteor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        this.x += 3;
        this.y -= 2;
    }

    // 흩어지는 기능
    void scatter() {
        System.out.println("흩어지기");
    }
}
