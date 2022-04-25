import javax.swing.*;
import java.awt.*;

public class Meteor extends Obstacle{
    // 흩어질 개수
    int numberOfMeteor;
    public static Image meteorImage;
    public static int meteorX = (int)(Math.random()*(avoidMeteor.frameWidth-64));
    public static int meteorY = (int)(Math.random()*(avoidMeteor.frameHeight-64-30)+30);
    Meteor(){
        meteorImage = new ImageIcon("src/images/falling-asteroid.png").getImage();
    }

    // 흩어지는 기능
    void scatter (){
        System.out.println("흩어지기");
    }
}
