import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class avoidMeteor extends JFrame {
    public Image backgroundImage = new ImageIcon("src/images/background3.jpg").getImage();

    private Image bufferImage;
    private Graphics screenGraphic;

    static int frameWidth = 800;
    static int frameHeight = 600;

    public avoidMeteor(){
        setTitle("AVOID METEOR");
        setVisible(true);
        setSize(frameWidth,frameHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ű���� ������ �߰�
        addKeyListener(new KeyAdapter() {
            // Ű�� ������ ��, ������ �޼ҵ�
            // Ű�� �Ҹ������� ���� ������ 2���� �Է°��� ���޾Ƶ��δ�.
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
                }
            }
            //            Ű�� ���� �� ������ �޼ҵ�
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
        new BattleSpaceShip();
        new Meteor();
        new Alien();
        while(true){
            try{
                // ���� �ݺ� ��Ű�� ������ �� �� �����Ƿ�, 20�и��ʷ� ���� �д�
                Thread.sleep(20);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            SpaceShip.keyProcess();
            crashCheck();
        }
    }

    // �÷��̾�� ��ֹ��� ����� ��, �÷��̾� ���� ȹ��
    public void crashCheck(){
        if (SpaceShip.playerX+64>Meteor.meteorX&&Meteor.meteorX+64>SpaceShip.playerX&&SpaceShip.playerY+64>Meteor.meteorY&&Meteor.meteorY+64>SpaceShip.playerY){
            Meteor.meteorX = (int)(Math.random()*(avoidMeteor.frameWidth-64));
            Meteor.meteorY = (int)(Math.random()*(avoidMeteor.frameHeight-64-30)+30);
        }
        if (SpaceShip.playerX+64>Alien.alienX&&Alien.alienX+64>SpaceShip.playerX&&SpaceShip.playerY+64>Alien.alienY&&Alien.alienY+64>SpaceShip.playerY){
            Alien.alienX = (int)(Math.random()*(avoidMeteor.frameWidth-64));
            Alien.alienY = (int)(Math.random()*(avoidMeteor.frameHeight-64-30)+30);
        }
    }



    public void paint(Graphics g){
        bufferImage = createImage(frameWidth, frameHeight);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage,0,0,null);
    }


    public void screenDraw(Graphics g){
        g.drawImage(backgroundImage,0,0,null);
        g.drawImage(BattleSpaceShip.player,BattleSpaceShip.playerX,BattleSpaceShip.playerY,null);
        g.drawImage(Meteor.meteorImage,Meteor.meteorX,Meteor.meteorY,null);
        g.drawImage(Alien.alienImage,Alien.alienX,Alien.alienY,null);
        this.repaint();

    }

    public static void main(String[] args) {
        new avoidMeteor();
    }
}




