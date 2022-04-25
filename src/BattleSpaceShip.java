import javax.swing.*;
import java.awt.*;

public class BattleSpaceShip extends SpaceShip{
    public static Image player = new ImageIcon("src/images/space-ship.png").getImage();
    // 전투형 우주선 공격력'
    int power;
    // 전투형 우주선 공격 기능
    int attack(){
        System.out.println("공격하기");
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