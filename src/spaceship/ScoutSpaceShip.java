package spaceship;

import spaceship.SpaceShip;

import static spaceship.BattleSpaceShip.speed;

public class ScoutSpaceShip extends SpaceShip {
    // ������ ���ּ� �ν��� ������
    int boostGauge;
    // ������ ���ּ� �ν��� ���
    int booster(){
        System.out.println("�ν��� ����ϱ�");
        speed = speed + boostGauge;
        return speed;
    }
}
