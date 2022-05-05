import javax.swing.*;
import java.awt.*;

public class BattleSpaceShip extends SpaceShip{

    // 전투형 우주선 공격력'
    public static Image player = new ImageIcon("src/images/space-ship.png").getImage();
    public static int width = player.getWidth(null);
    public static int height = player.getHeight(null);
    static int speed = 5;
    static int power = 5;
    static int hp = 100;
    static int x;
    static int y;

    BattleSpaceShip(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static void keyProcess(){
        if (up && x - speed > 30) y -= speed;
        if (down && y + speed < avoidMeteor.FRAME_HEIGHT - height) y += speed;
        if (left && x - speed > 0) x -= speed;
        if (right && x < avoidMeteor.FRAME_WIDTH - width) x += speed;
    }

    int attack(){
        System.out.println("공격하기");
        return power;
    }

}