public class avoidMeteor {
    public static void main(String[] args) {
        BattleSpaceShip a = new BattleSpaceShip();
        a.attack();

        ScoutSpaceShip b = new ScoutSpaceShip();
        b.move();

        DefensiveSpaceShip c = new DefensiveSpaceShip();
        c.defenseUp();

    }
}

class SpaceShip{
    // ���ּ� �⺻ �ɷ�ġ
    int hp = 10;
    int width = 10;
    int height = 10;
    int speed = 10;
    void move() {
        System.out.println("�¿� �����̱�");
    }
}

class BattleSpaceShip extends SpaceShip{
    // ������ ���ּ� ���ݷ� = powerspaceship
    int power;
    // ������ ���ּ� ���� ���
    int attack(){
        System.out.println("�����ϱ�");
        return power;
    }
}

class ScoutSpaceShip extends SpaceShip{
    // ������ ���ּ� �ν��� ������ = boostGauge
    int boostGauge;
    // ������ ���ּ� �ν��� ���
    int booster(){
        System.out.println("�ν��� ����ϱ�");
        speed = speed + boostGauge;
        return speed;
    }
}

class DefensiveSpaceShip extends SpaceShip{
    // ����� ���ּ� ����
    int def;
    // ����� ���ּ� ���� �߰� ���
    int defenseUp(){
        System.out.println("���� ��ȭ");
        hp = hp + def;
        return hp;
    }
}