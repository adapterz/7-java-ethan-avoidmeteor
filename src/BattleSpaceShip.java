import javax.swing.*;
import java.awt.*;

public class BattleSpaceShip extends SpaceShip{

    // 전투형 우주선 공격력'
    static int x;
    static int y;
    int power;
    // 전투형 우주선 공격 기능
//    public static int playerX =
//    public static int playerY = ;

    BattleSpaceShip(int x, int y){
        player = new ImageIcon("src/images/space-ship.png").getImage();
        this.x = (avoidMeteor.FRAME_WIDTH - 64)/2;;
        this.y = (avoidMeteor.FRAME_HEIGHT - 64)/2;
        this.width = 64;
        this.height = 64;
        this.hp = 100;
        this.speed = 5;
        this.power = 5;
    }
    public static void keyProcess(){
        if (up && x -3 > 30) y -= 5;
        if (down && y+ 3 < avoidMeteor.FRAME_HEIGHT-64) y+=5;
        if (left && x -3 > 0) x -=5;
        if (right && x < avoidMeteor.FRAME_WIDTH-64) x +=5;
    }
    int attack(){
        System.out.println("공격하기");
        return power;
    }

}