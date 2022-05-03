import javax.swing.*;
import java.awt.*;

public class Alien extends Obstacle{
    // 부스터 게이지
    int boostGauge;
    Image image = new ImageIcon("src/images/alien-3.png").getImage();
    int x, y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);

    public Alien(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move() {
//        this.x -= 2;
        this.y += 3;
    }
    // 1초 후에 빠르게 떨어지는 기능
    void fallFaster (){
        System.out.println("1초 후에 빠르게 떨어진다.");
    }
}

