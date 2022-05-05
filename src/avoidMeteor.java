import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// ������ ����Ǵ� ���� ȭ�� ����� ���.
public class avoidMeteor extends JFrame {
    private Image backgroundImage = new ImageIcon("src/images/background3.jpg").getImage();

    // ���� ���۸��� ���� ��üȭ�鿡 ���� �̹����� ��� �ν��Ͻ�
    private Image bufferImage;
    private Graphics screenGraphic;

    // �����÷��� Ŭ������ ���� ��ü ���� (������ ������ ����)
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
            // Ű�� ���� �� ������ �޼ҵ�
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

    // Paints this component. ( ������Ʈ�� �׸���. )
    // This method is called when the contents of the component should be painted; such as when the component is first being shown or is damaged and in need of repair. ( �������� ó�� ���������� �������� �Ծ��ų�, ������ �ʿ��Ҷ� paint()�� ȣ��ȴ�. )
    public void paint(Graphics g){
        bufferImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage,0,0,null);
    }

    public void screenDraw(Graphics g){
        g.drawImage(backgroundImage,0,0,null);
        gamePlay.gameDraw(g);

        // Repaints this component. ( ������Ʈ�� �ٽ� �׸���. )
        // If this component is a lightweight component, this method causes a call to this component's paint method as soon as possible. Otherwise, this method causes a call to this component's update method as soon as possible. ( �淮 ������Ʈ��� �ٷ� paint()�� ȣ���Ѵ�. �ƴ϶��, update()�� ȣ���Ѵ�. )
        this.repaint();
    }

    public static void main(String[] args) {
        new avoidMeteor();
    }
}




