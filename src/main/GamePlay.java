package main;

import enemy.Alien;
import enemy.Meteor;
import main.AvoidMeteor;
import spaceship.BattleSpaceShip;


import javax.sound.sampled.*;
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
    public static int count;
    // ���� �ð�
    private long pretime;
    public static int score;

    private ArrayList<Meteor> meteorList = new ArrayList<Meteor>();
    private ArrayList<Alien> alienList = new ArrayList<Alien>();
    private Meteor meteor;
    private Alien alien;

    boolean gamePlaying;

    @Override
    public void run() {
        // ���� ���� �ð�, �÷��̾� ��ġ �ʱ�ȭ
        score = 0;
        count = 0;
        gamePlaying = true;
        BattleSpaceShip player = new BattleSpaceShip((AvoidMeteor.FRAME_WIDTH - 64)/2, (AvoidMeteor.FRAME_HEIGHT - 64)/2);

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
                    if(!gamePlaying){
                        Thread.sleep(100);
                        Thread.interrupted();
//                        clip.stop();
                    }
                    count++;
                    score++;
                    // thread ������ ���� ���� ó��

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void meteorAppearProcess(){
        if (count % 30 == 0){
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
//            meteor.crash(player);
            }
        }
//
//    private void meteorClearProcess(){
//        for (int i = 0; i < meteorList.size(); i++){
//            meteor = meteorList.get(i);
//            meteorList.remove(meteor);
//        }
//        }

    private void alienAppearProcess(){
        if (count % 30 == 0){
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
//            crashCheckAlien(spaceship.BattleSpaceShip.player, alien);
            if (alien.y > 580){
                alienList.remove(i);
            }
            if (!gamePlaying){
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

    public void crashCheckMeteor(BattleSpaceShip player,Meteor meteor) throws InterruptedException {
        for (int i = 0; i<meteorList.size(); i++) {
            meteor = meteorList.get(i);
            if (Math.abs((BattleSpaceShip.x + BattleSpaceShip.width / 2) - (meteor.x + meteor.width / 2)) < ((BattleSpaceShip.width + meteor.width) / 2)
                    && Math.abs((BattleSpaceShip.y + BattleSpaceShip.height / 2) - (meteor.y + meteor.height / 2)) < ((BattleSpaceShip.height + meteor.height) / 2)) {
                meteor.x = 1000;
                meteor.y = 1000;
                playSound("src/audios/boom1.wav", false);
//                clip.stop();
                gamePlaying = false;
                main.AvoidMeteor.gameOver();
            }
        }
    }

    public void crashCheckAlien(BattleSpaceShip player,Alien alien) throws InterruptedException {
        for (int i = 0; i<alienList.size(); i++) {
            alien = alienList.get(i);
        if (Math.abs( ( BattleSpaceShip.x + BattleSpaceShip.width/2 ) - ( alien.x + alien.width/2 ) ) < ((BattleSpaceShip.width + alien.width)/2)
                && Math.abs( ( BattleSpaceShip.y + BattleSpaceShip.height/2 ) - ( alien.y + alien.height/2 ) ) < ((BattleSpaceShip.height + alien.height)/2)) {
            alien.x = 1000;
            alien.y = 1000;
            playSound("src/audios/boom1.wav", false);
            gamePlaying = false;
            AvoidMeteor.gameOver();
        }
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
