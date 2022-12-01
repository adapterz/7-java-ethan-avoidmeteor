package main;

import spaceship.SpaceShip;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


// 게임이 실행되는 배경과 화면 출력을 담당.
public class AvoidMeteor extends JFrame {

    private Image initialBackgroundImage = new ImageIcon("src/images/background_initial_2.png").getImage();
    private Image inGameBackgroundImage = new ImageIcon("src/images/background3.jpg").getImage();
    private Image gameOverBackgroundImage = new ImageIcon("src/images/background_gameover.png").getImage();

    static boolean isInitialBackground;
    static boolean isInGameBackground;
    static boolean isGameOverBackground;

    private Image bufferImage;
    private Graphics screenGraphic;

    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    GamePlay gamePlay = new GamePlay();

    public AvoidMeteor(){
        setTitle("AVOID METEOR");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        init();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:
                        SpaceShip.up = true;
                        break;
                    case KeyEvent.VK_S:
                        SpaceShip.down = true;
                        break;
                    case KeyEvent.VK_A:
                        SpaceShip.left = true;
                        break;
                    case KeyEvent.VK_D:
                        SpaceShip.right = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        if (isInitialBackground){
                            gameStart();
                            gamePlay.start();
                            break;
                        }
                        if (isGameOverBackground){
                            gameStart();
                            new GamePlay().start();
                            break;
                        }
                    // ESC 를 누르면 게임 종료
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;

                }
            }
            public void keyReleased(KeyEvent e){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:
                        SpaceShip.up = false;
                        break;
                    case KeyEvent.VK_S:
                        SpaceShip.down = false;
                        break;
                    case KeyEvent.VK_A:
                        SpaceShip.left = false;
                        break;
                    case KeyEvent.VK_D:
                        SpaceShip.right = false;
                        break;
                }
            }
        });
    }

    private void init(){
        isInitialBackground = true;
        isInGameBackground = false;
        isGameOverBackground = false;
        playSound("src/audios/background.wav",true);
    }

    private void gameStart(){
        isInitialBackground = false;
        isInGameBackground = true;
        isGameOverBackground = false;
    };

    public static void gameOver(){
        isInitialBackground = false;
        isInGameBackground = false;
        isGameOverBackground = true;
    }

    public void paint(Graphics g){
        bufferImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage,0,0,null);
    }

    public void screenDraw(Graphics g){
        if (isInitialBackground){
            g.drawImage(initialBackgroundImage,0,0,null);
        }
        if (isInGameBackground){
            g.drawImage(inGameBackgroundImage,0,0,null);
            gamePlay.gameDraw(g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD,40));
            g.drawString("SCORE :" + GamePlay.score, 30, 80);
        }
        if (isGameOverBackground) {
            g.drawImage(gameOverBackgroundImage, 0, 0, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD,40));
            g.drawString("SCORE :" + GamePlay.score, 300, 400);
        }
        this.repaint();
    }

    public void playSound(String pathName, boolean isLoop){
        try{
            Clip clip = AudioSystem.getClip();
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
    public static void main(String[] args) {
        new AvoidMeteor();
    }
}




