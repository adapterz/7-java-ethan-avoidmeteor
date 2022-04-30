import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// 게임플레이에 대한 내용 (플레이어 정보)
public class GamePlay extends Thread{


    //클립 변수 선언
    private Clip clip;
    // 게임의 딜레이를 표시
    private int delay = 10;
    // 딜레이마다 증가할 cnt (게임 진행 시간)
    private int count;
    // 현재 시간
    private long pretime;

    // 플레이어의 이미지
    private Image player = new ImageIcon(BattleSpaceShip.player).getImage();

    private ArrayList<Meteor> meteorList = new ArrayList<Meteor>();
    private ArrayList<Alien> alienList = new ArrayList<Alien>();
    private Meteor meteor;
    private Alien alien;

    boolean gamePlaying;


    @Override
    public void run() {
        // 게임 진행 시간, 플레이어 위치 초기화
        count = 0;
        gamePlaying = true;
        BattleSpaceShip.playerX = (avoidMeteor.frameWidth - 64)/2;
        BattleSpaceShip.playerY = (avoidMeteor.frameHeight - 64)/2;
        playSound("src/audios/background.wav",true);
        // 스레드가 실행되면 키 프로세스가 반복되어서 실행되고, 밀리초마다
        while(gamePlaying){
            // 현재시각을 밀리세컨드 단위로 반환한다.
            pretime = System.currentTimeMillis();
            // ( 현재시간 - cnt가 증가하기 전 시간 ) < delay일 경우,
            if (System.currentTimeMillis() - pretime < delay) {
                // 그 차이만 큼 Thread에 슬립을 준다.
                try {
                    Thread.sleep(delay - System.currentTimeMillis() + pretime);
                    // 키가 동작하도록 keyprocess 추가
                    BattleSpaceShip.keyProcess();
                    meteorAppearProcess();
                    meteorMoveProcess();
                    alienAppearProcess();
                    alienMoveProcess();
                    crashCheck();
                    count++;
                    // thread 슬립에 대한 예외 처리
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void meteorAppearProcess(){
        if (count % 30 == 0){
            //랜덤한 위치에 적들이 나오게 만든다.
            meteor = new Meteor(0, (int)(Math.random()*580));
            meteorList.add(meteor);
        }
    }

    private void meteorMoveProcess(){
        for(int i = 0; i<meteorList.size(); i++){
            meteor = meteorList.get(i);
            // 메테오 클래스 내 move 메서드 사용
            meteor.move();
        }
    }

    private void alienAppearProcess(){
        if (count % 30 == 0){
            //랜덤한 위치에 적들이 나오게 만든다.
            alien = new Alien((int)(Math.random()*780), 0 );
            alienList.add(alien);
        }
    }

    private void alienMoveProcess(){
        for(int i = 0; i<alienList.size(); i++){
            alien = alienList.get(i);
            // 외계인 클래스 내 move 메서드 사용
            alien.move();
        }
    }

    // 게임플레이의 요소를 그린다.
    public void gameDraw(Graphics g) {
        // 플레이어의 요소를 그려준다.
        playerDraw(g);
        meteorDraw(g);
        alienDraw(g);
    }

    // 플레이어의 관한 요소를 그릴 메서드
    public void playerDraw(Graphics g) {
        // 플레이어를 그려준다.
        g.drawImage(player, BattleSpaceShip.playerX, BattleSpaceShip.playerY, null);
    }

    // 적을 그릴 메서드
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

    //플레이어와 장애물이 닿았을 때
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
