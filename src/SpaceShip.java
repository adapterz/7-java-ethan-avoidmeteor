import java.awt.*;

public class SpaceShip extends Objects {

    // �⺻ ���ּ� �̹���
    public static Image player;
    // ���ּ� ����
    int lev;
    public static int playerX = (avoidMeteor.frameWidth - 64)/2;
    public static int playerY = (avoidMeteor.frameHeight - 64)/2;
    public static boolean up;
    public static boolean down;
    public static boolean  left;
    public static boolean  right;

    // �÷��̾ �̵���Ű�� �޼ҵ�
    // GUI Ŭ����(, �� �ȿ� Spaceship ��ü, alien ��ü,
    public static void keyProcess(){
        if (up && playerY -3 > 30) playerY -= 5;
        if (down && playerY+ 3 < avoidMeteor.frameHeight-64) playerY+=5;
        if (left && playerX -3 > 0) playerX -=5;
        if (right && playerX < avoidMeteor.frameWidth-64) playerX +=5;
    }
}

