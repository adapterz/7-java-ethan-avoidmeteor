import javax.swing.*;
import java.awt.*;

public class Alien extends Obstacle{
    // �ν��� ������
    int boostGauge;
    public static Image alienImage;
    public static int alienX = (int)(Math.random()*(avoidMeteor.frameWidth-64));
    public static int alienY = (int)(Math.random()*(avoidMeteor.frameHeight-64-30)+30);


    // 1�� �Ŀ� ������ �������� ���
    void fallFaster (){
        System.out.println("1�� �Ŀ� ������ ��������.");
    }

    Alien(){
        alienImage = new ImageIcon("src/images/alien-3.png").getImage();
    }

}

