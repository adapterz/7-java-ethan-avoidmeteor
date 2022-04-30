import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// �����÷��̿� ���� ���� (�÷��̾� ����)
public class GamePlay extends Thread{


    //Ŭ�� ���� ����
    private Clip clip;
    // ������ �����̸� ǥ��
    private int delay = 10;
    // �����̸��� ������ cnt (���� ���� �ð�)
    private int count;
    // ���� �ð�
    private long pretime;

    // �÷��̾��� �̹���
    private Image player = new ImageIcon(BattleSpaceShip.player).getImage();

    private ArrayList<Meteor> meteorList = new ArrayList<Meteor>();
    private ArrayList<Alien> alienList = new ArrayList<Alien>();
    private Meteor meteor;
    private Alien alien;

    boolean gamePlaying;


    @Override
    public void run() {
        // ���� ���� �ð�, �÷��̾� ��ġ �ʱ�ȭ
        count = 0;
        gamePlaying = true;
        BattleSpaceShip.playerX = (avoidMeteor.frameWidth - 64)/2;
        BattleSpaceShip.playerY = (avoidMeteor.frameHeight - 64)/2;
        playSound("src/audios/background.wav",true);
        // �����尡 ����Ǹ� Ű ���μ����� �ݺ��Ǿ ����ǰ�, �и��ʸ���
        while(gamePlaying){
            // ����ð��� �и������� ������ ��ȯ�Ѵ�.
            pretime = System.currentTimeMillis();
            // ( ����ð� - cnt�� �����ϱ� �� �ð� ) < delay�� ���,
            if (System.currentTimeMillis() - pretime < delay) {
                // �� ���̸� ŭ Thread�� ������ �ش�.
                try {
                    Thread.sleep(delay - System.currentTimeMillis() + pretime);
                    // Ű�� �����ϵ��� keyprocess �߰�
                    BattleSpaceShip.keyProcess();
                    meteorAppearProcess();
                    meteorMoveProcess();
                    alienAppearProcess();
                    alienMoveProcess();
                    crashCheck();
                    count++;
                    // thread ������ ���� ���� ó��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void meteorAppearProcess(){
        if (count % 30 == 0){
            //������ ��ġ�� ������ ������ �����.
            meteor = new Meteor(0, (int)(Math.random()*580));
            meteorList.add(meteor);
        }
    }

    private void meteorMoveProcess(){
        for(int i = 0; i<meteorList.size(); i++){
            meteor = meteorList.get(i);
            // ���׿� Ŭ���� �� move �޼��� ���
            meteor.move();
        }
    }

    private void alienAppearProcess(){
        if (count % 30 == 0){
            //������ ��ġ�� ������ ������ �����.
            alien = new Alien((int)(Math.random()*780), 0 );
            alienList.add(alien);
        }
    }

    private void alienMoveProcess(){
        for(int i = 0; i<alienList.size(); i++){
            alien = alienList.get(i);
            // �ܰ��� Ŭ���� �� move �޼��� ���
            alien.move();
        }
    }

    // �����÷����� ��Ҹ� �׸���.
    public void gameDraw(Graphics g) {
        // �÷��̾��� ��Ҹ� �׷��ش�.
        playerDraw(g);
        meteorDraw(g);
        alienDraw(g);
    }

    // �÷��̾��� ���� ��Ҹ� �׸� �޼���
    public void playerDraw(Graphics g) {
        // �÷��̾ �׷��ش�.
        g.drawImage(player, BattleSpaceShip.playerX, BattleSpaceShip.playerY, null);
    }

    // ���� �׸� �޼���
    public void meteorDraw(Graphics g) {
        for (int i = 0; i<meteorList.size(); i++){
            meteor = meteorList.get(i);
            g.drawImage(meteor.image, meteor.x, meteor.y, null);
        }
    }

    public void alienDraw(Graphics g) {
        for (int i = 0; i<alienList.size(); i++){
            alien = alienList.get(i);
            g.drawImage(alien.image, alien.x, alien.y, null);
        }
    }

    //�÷��̾�� ��ֹ��� ����� ��
    public void crashCheck(){
        if (BattleSpaceShip.playerX+64>meteor.x&&meteor.x+meteor.width>BattleSpaceShip.playerX&&BattleSpaceShip.playerY+64>meteor.y&&meteor.y+meteor.height>BattleSpaceShip.playerY){
            meteor.x = 1000;
            meteor.y = 1000;
            playSound("src/audios/boom1.wav",false);
            gamePlaying = false;
        }
        if (BattleSpaceShip.playerX+64>alien.x &&alien.x +alien.width>BattleSpaceShip.playerX&&BattleSpaceShip.playerY+64>alien.y &&alien.y+alien.height>BattleSpaceShip.playerY){
            alien.x = 1000;
            alien.y = 1000;
            playSound("src/audios/boom1.wav",false);
            gamePlaying = false;
        }
    }

    public void playSound(String pathName, boolean isLoop){
        try{
            clip = AudioSystem.getClip();
            File audioFile = new File(pathName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioStream);
            clip.start();
            if (isLoop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
