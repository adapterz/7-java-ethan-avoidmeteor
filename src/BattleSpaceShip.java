import javax.swing.*;
import java.awt.*;

public class BattleSpaceShip extends SpaceShip{
    public static Image player = new ImageIcon("src/images/space-ship.png").getImage();
    // ������ ���ּ� ���ݷ�'
    int power;
    // ������ ���ּ� ���� ���
    int attack(){
        System.out.println("�����ϱ�");
        return power;
    }
    BattleSpaceShip(){
        hp = 100;
        width = 64;
        height = 64;
        speed = 5;
        power = 5;
    }
}