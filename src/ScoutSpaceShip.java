public class ScoutSpaceShip extends SpaceShip{
    // ������ ���ּ� �ν��� ������ = boostGauge
    int boostGauge;
    // ������ ���ּ� �ν��� ���
    int booster(){
        System.out.println("�ν��� ����ϱ�");
        speed = speed + boostGauge;
        return speed;
    }
}
