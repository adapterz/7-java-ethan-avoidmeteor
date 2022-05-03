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
//    private Image player = new ImageIcon(BattleSpaceShip.player).getImage();

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
        BattleSpaceShip player = new BattleSpaceShip((avoidMeteor.FRAME_WIDTH - 64)/2, (avoidMeteor.FRAME_HEIGHT - 64)/2);
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
                    meteorMoveProcess(player);
                    alienAppearProcess();
                    alienMoveProcess();
                    crashCheckMeteor(player, meteor);
                    crashCheckAlien(player, alien);

                    count++;
                    // thread 슬립에 대한 예외 처리
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void meteorAppearProcess(){
        if (count % 50 == 0){
            //랜덤한 위치에 적들이 나오게 만든다.
            meteor = new Meteor(0, (int)(Math.random()*580));
            meteorList.add(meteor);
            System.out.println(meteorList);
        }
    }

    private void meteorMoveProcess(BattleSpaceShip player){
        for(int i = 0; i<meteorList.size(); i++){
            meteor = meteorList.get(i);
            // 메테오 클래스 내 move 메서드 사용
            meteor.move();
            if (meteor.x > 800){
                meteorList.remove(i);
            }
            meteor.crash(player);
            }
        }

    private void alienAppearProcess(){
        if (count % 50 == 0){
            //랜덤한 위치에 적들이 나오게 만든다.
            alien = new Alien((int)(Math.random()*780), 0 );
            alienList.add(alien);
            System.out.println(alienList);
        }
    }

    private void alienMoveProcess(){
        for(int i = 0; i<alienList.size(); i++){
            alien = alienList.get(i);
            // 외계인 클래스 내 move 메서드 사용
            alien.move();
//            crashCheckAlien(BattleSpaceShip.player, alien);
            if (alien.y > 580){
                alienList.remove(i);
            }
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
        g.drawImage(BattleSpaceShip.player, BattleSpaceShip.x, BattleSpaceShip.y, null);
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
//    public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2){
//    //기존 충돌 판정 소스를 변경합니다.
//    //이제 이미지 변수를 바로 받아 해당 이미지의 넓이, 높이값을
//    //바로 계산합니다.
//        boolean check = false;
//        if ( Math.abs( ( x1 + img1.getWidth(null) / 2 )
//                - ( x2 + img2.getWidth(null) / 2 ))
//                < ( img2.getWidth(null) / 2 + img1.getWidth(null) / 2 )
//                && Math.abs( ( y1 + img1.getHeight(null) / 2 )
//                - ( y2 + img2.getHeight(null) / 2 ))
//                < ( img2.getHeight(null)/2 + img1.getHeight(null)/2 ) ){
//    //이미지 넓이, 높이값을 바로 받아 계산합니다.
//            check = true;//위 값이 true면 check에 true를 전달합니다.
//        }else{ check = false;}
//
//        return check; //check의 값을 메소드에 리턴 시킵니다.
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
//        System.out.println("우주선 좌표 : "+BattleSpaceShip.x+" "+BattleSpaceShip.y);
//        System.out.println("운석 좌표 : "+meteor.x+" "+meteor.y);
//        System.out.println("우주선 좌표 : "+player.x+" "+player.y);
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
