package spaceship;

import spaceship.SpaceShip;

import static spaceship.BattleSpaceShip.hp;

public class DefensiveSpaceShip extends SpaceShip {
    // ����� ���ּ� ����
    int def = 10;
    // ����� ���ּ� ���� �߰� ���
    int defenseUp(){
        System.out.println("���� ��ȭ");
        hp = hp + def;
        return hp;
    }
}