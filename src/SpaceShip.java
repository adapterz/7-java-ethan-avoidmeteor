import java.awt.*;

public class SpaceShip extends Objects {

    // 기본 우주선 이미지
    public static Image player;
    // 우주선 레벨
    int lev;
    public static int playerX = (avoidMeteor.frameWidth - 64)/2;
    public static int playerY = (avoidMeteor.frameHeight - 64)/2;
    public static boolean up;
    public static boolean down;
    public static boolean  left;
    public static boolean  right;

    // 플레이어를 이동시키는 메소드
    // GUI 클래스(, 그 안에 Spaceship 객체, alien 객체,
    public static void keyProcess(){
        if (up && playerY -3 > 30) playerY -= 5;
        if (down && playerY+ 3 < avoidMeteor.frameHeight-64) playerY+=5;
        if (left && playerX -3 > 0) playerX -=5;
        if (right && playerX < avoidMeteor.frameWidth-64) playerX +=5;
    }
}

