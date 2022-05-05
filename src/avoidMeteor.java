import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// 게임이 실행되는 배경과 화면 출력을 담당.
public class avoidMeteor extends JFrame {
    private Image backgroundImage = new ImageIcon("src/images/background3.jpg").getImage();

    // 더블 버퍼링을 위해 전체화면에 대한 이미지를 담는 인스턴스
    private Image bufferImage;
    private Graphics screenGraphic;

    // 게임플레이 클래스에 대한 객체 생성 (스레드 생성을 위해)
    public static GamePlay gamePlay = new GamePlay();

    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    public avoidMeteor(){
        setTitle("AVOID METEOR");
        setVisible(true);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 키보드 리스너 추가
        addKeyListener(new KeyAdapter() {
            // 키를 눌렀을 떄, 실행할 메소드
            // 키를 불리언으로 하지 않으면 2개의 입력값을 못받아들인다.
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
            // 키를 땠을 때 실행할 메소드
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
        gamePlay.start();
    }

    // Paints this component. ( 컴포넌트를 그린다. )
    // This method is called when the contents of the component should be painted; such as when the component is first being shown or is damaged and in need of repair. ( 프레임이 처음 켜졌을때와 데미지를 입었거나, 수리가 필요할때 paint()가 호출된다. )
    public void paint(Graphics g){
        bufferImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage,0,0,null);
    }

    public void screenDraw(Graphics g){
        g.drawImage(backgroundImage,0,0,null);
        gamePlay.gameDraw(g);

        // Repaints this component. ( 컴포넌트를 다시 그린다. )
        // If this component is a lightweight component, this method causes a call to this component's paint method as soon as possible. Otherwise, this method causes a call to this component's update method as soon as possible. ( 경량 컴포넌트라면 바로 paint()를 호출한다. 아니라면, update()를 호출한다. )
        this.repaint();
    }

    public static void main(String[] args) {
        new avoidMeteor();
    }
}




