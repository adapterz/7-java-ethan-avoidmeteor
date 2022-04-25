import javax.swing.*;
import java.awt.*;

public class Alien extends Obstacle{
    // 부스터 게이지
    int boostGauge;
    public static Image alienImage;
    public static int alienX = (int)(Math.random()*(avoidMeteor.frameWidth-64));
    public static int alienY = (int)(Math.random()*(avoidMeteor.frameHeight-64-30)+30);


    // 1초 후에 빠르게 떨어지는 기능
    void fallFaster (){
        System.out.println("1초 후에 빠르게 떨어진다.");
    }

    Alien(){
        alienImage = new ImageIcon("src/images/alien-3.png").getImage();
    }

}

