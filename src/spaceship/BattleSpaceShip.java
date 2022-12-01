package spaceship;
import main.AvoidMeteor;

import javax.swing.*;
import java.awt.*;

public class BattleSpaceShip extends SpaceShip {

    // 전투형 우주선 공격력'
    public static Image player = new ImageIcon("src/images/space-ship.png").getImage();
    public static int width = 5;
    public static int height = 5;
    static int speed = 5;
    static int power = 5;
    static int hp = 100;
    public static int x;
    public static int y;

    public BattleSpaceShip(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static void keyProcess(){
        if (SpaceShip.up && x - speed > 30) y -= speed;
        if (SpaceShip.down && y + speed < AvoidMeteor.FRAME_HEIGHT - height) y += speed;
        if (SpaceShip.left && x - speed > 0) x -= speed;
        if (SpaceShip.right && x < AvoidMeteor.FRAME_WIDTH - width) x += speed;
    }

    int attack(){
        System.out.println("공격하기");
        return power;
    }

}