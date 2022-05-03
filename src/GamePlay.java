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
//    private Image player = new ImageIcon(BattleSpaceShip.player).getImage();

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
        BattleSpaceShip player = new BattleSpaceShip((avoidMeteor.FRAME_WIDTH - 64)/2, (avoidMeteor.FRAME_HEIGHT - 64)/2);
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
                    meteorMoveProcess(player);
                    alienAppearProcess();
                    alienMoveProcess();
                    crashCheckMeteor(player, meteor);
                    crashCheckAlien(player, alien);

                    count++;
                    // thread ������ ���� ���� ó��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void meteorAppearProcess(){
        if (count % 50 == 0){
            //������ ��ġ�� ������ ������ �����.
            meteor = new Meteor(0, (int)(Math.random()*580));
            meteorList.add(meteor);
            System.out.println(meteorList);
        }
    }

    private void meteorMoveProcess(BattleSpaceShip player){
        for(int i = 0; i<meteorList.size(); i++){
            meteor = meteorList.get(i);
            // ���׿� Ŭ���� �� move �޼��� ���
            meteor.move();
            if (meteor.x > 800){
                meteorList.remove(i);
            }
            meteor.crash(player);
            }
        }

    private void alienAppearProcess(){
        if (count % 50 == 0){
            //������ ��ġ�� ������ ������ �����.
            alien = new Alien((int)(Math.random()*780), 0 );
            alienList.add(alien);
            System.out.println(alienList);
        }
    }

    private void alienMoveProcess(){
        for(int i = 0; i<alienList.size(); i++){
            alien = alienList.get(i);
            // �ܰ��� Ŭ���� �� move �޼��� ���
            alien.move();
//            crashCheckAlien(BattleSpaceShip.player, alien);
            if (alien.y > 580){
                alienList.remove(i);
            }
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
        g.drawImage(BattleSpaceShip.player, BattleSpaceShip.x, BattleSpaceShip.y, null);
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
//    public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2){
//    //���� �浹 ���� �ҽ��� �����մϴ�.
//    //���� �̹��� ������ �ٷ� �޾� �ش� �̹����� ����, ���̰���
//    //�ٷ� ����մϴ�.
//        boolean check = false;
//        if ( Math.abs( ( x1 + img1.getWidth(null) / 2 )
//                - ( x2 + img2.getWidth(null) / 2 ))
//                < ( img2.getWidth(null) / 2 + img1.getWidth(null) / 2 )
//                && Math.abs( ( y1 + img1.getHeight(null) / 2 )
//                - ( y2 + img2.getHeight(null) / 2 ))
//                < ( img2.getHeight(null)/2 + img1.getHeight(null)/2 ) ){
//    //�̹��� ����, ���̰��� �ٷ� �޾� ����մϴ�.
//            check = true;//�� ���� true�� check�� true�� �����մϴ�.
//        }else{ check = false;}
//
//        return check; //check�� ���� �޼ҵ忡 ���� ��ŵ�ϴ�.
//
//    }
    public void crashCheckMeteor(BattleSpaceShip player,Meteor meteor){
        if (Math.abs( ( BattleSpaceShip.x + BattleSpaceShip.width/2 ) - ( meteor.x + meteor.width/2 ) ) < ((BattleSpaceShip.width + meteor.width)/2)
        && Math.abs( ( BattleSpaceShip.y + BattleSpaceShip.height/2 ) - ( meteor.y + meteor.height/2 ) ) < ((BattleSpaceShip.height + meteor.height)/2)) {
            meteor.x = 1000;
            meteor.y = 1000;
            playSound("src/audios/boom1.wav", false);
            gamePlaying = false;
        }
//        System.out.println("���ּ� ��ǥ : "+BattleSpaceShip.x+" "+BattleSpaceShip.y);
//        System.out.println("� ��ǥ : "+meteor.x+" "+meteor.y);
//        System.out.println("���ּ� ��ǥ : "+player.x+" "+player.y);
    }

    public void crashCheckAlien(BattleSpaceShip player,Alien alien){
        if (Math.abs( ( BattleSpaceShip.x + BattleSpaceShip.width/2 ) - ( alien.x + alien.width/2 ) ) < ((BattleSpaceShip.width + alien.width)/2)
                && Math.abs( ( BattleSpaceShip.y + BattleSpaceShip.height/2 ) - ( alien.y + alien.height/2 ) ) < ((BattleSpaceShip.height + alien.height)/2)){
            alien.x = 1000;
            alien.y = 1000;
            playSound("src/audios/boom1.wav",false);
            gamePlaying = false;
        }
    }
//    public void crashCheck(BattleSpaceShip player, Meteor meteor, Alien alien){
//        if ((Math.abs((player.x + player.width / 2) - ( meteor.x + meteor.width / 2)) < ( meteor.width / 2 + player.width / 2) &&
//                Math.abs( (player.y + player.height / 2) - (meteor.y + meteor.height / 2)) < ( meteor.height /2 + player.height / 2))){
//            meteor.x = 1000;
//            meteor.y = 1000;
//            playSound("src/audios/boom1.wav",false);
//            gamePlaying = false;
//        }
//
//        if ((Math.abs((player.x + player.width / 2) - ( alien.x + player.width / 2)) < ( alien.width / 2 + player.width / 2) &&
//                Math.abs( (player.y + player.height / 2) - (alien.y + alien.height / 2)) < ( alien.height /2 + player.height / 2))){
//            alien.x = 1000;
//            alien.y = 1000;
//            playSound("src/audios/boom1.wav",false);
//            gamePlaying = false;
//        }

//
//        if (BattleSpaceShip.playerX>meteor.x && BattleSpaceShip.playerX<meteor.x+meteor.width && BattleSpaceShip.playerY>meteor.y&&BattleSpaceShip.playerY<meteor.y+meteor.height){
//            meteor.x = 1000;
//            meteor.y = 1000;
//            playSound("src/audios/boom1.wav",false);
//            gamePlaying = false;
//        }
//        if (BattleSpaceShip.playerX>alien.x && BattleSpaceShip.playerX<alien.x+alien.width && BattleSpaceShip.playerY>alien.y && BattleSpaceShip.playerY<alien.y+alien.height){
//            alien.x = 1000;
//            alien.y = 1000;
//            playSound("src/audios/boom1.wav",false);
//            gamePlaying = false;
//        }


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
