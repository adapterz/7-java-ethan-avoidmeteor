import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Meteor extends Obstacle {
    private ArrayList<Meteor> meteorList = new ArrayList<Meteor>();
    private ArrayList<Alien> alienList = new ArrayList<Alien>();
    private Meteor meteor;
    private Alien alien;
    int count;
    Image image = new ImageIcon("src/images/falling-asteroid.png").getImage();
    int x, y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    // ����� ����
    int numberOfMeteor;

    public Meteor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        this.x += 3;
//        this.y += 1;
    }

    public void crash(BattleSpaceShip player){
        if (Math.abs( ( BattleSpaceShip.x + BattleSpaceShip.width/2 ) - ( meteor.x + meteor.width/2 ) ) < ((BattleSpaceShip.width + meteor.width)/2)
                && Math.abs( ( BattleSpaceShip.y + BattleSpaceShip.height/2 ) - ( meteor.y + meteor.height/2 ) ) < ((BattleSpaceShip.height + meteor.height)/2)) {
            meteor.x = 1000;
            meteor.y = 1000;
//            playSound("src/audios/boom1.wav", false);
//            gamePlaying = false;
        }
    }

    public void appear(){
        if (count % 50 == 0){
            //������ ��ġ�� ������ ������ �����.
            meteor = new Meteor(0, (int)(Math.random()*580));
            meteorList.add(meteor);
        }
    }
    // ������� ���
    void scatter() {
        System.out.println("�������");
    }
}
