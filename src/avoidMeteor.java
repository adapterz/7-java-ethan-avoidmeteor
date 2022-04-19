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
    // 우주선 기본 능력치
    int hp = 10;
    int width = 10;
    int height = 10;
    int speed = 10;
    void move() {
        System.out.println("좌우 움직이기");
    }
}

class BattleSpaceShip extends SpaceShip{
    // 전투형 우주선 공격력 = powerspaceship
    int power;
    // 전투형 우주선 공격 기능
    int attack(){
        System.out.println("공격하기");
        return power;
    }
}

class ScoutSpaceShip extends SpaceShip{
    // 정찰형 우주선 부스터 게이지 = boostGauge
    int boostGauge;
    // 정찰형 우주선 부스터 기능
    int booster(){
        System.out.println("부스터 사용하기");
        speed = speed + boostGauge;
        return speed;
    }
}

class DefensiveSpaceShip extends SpaceShip{
    // 방어형 우주선 방어력
    int def;
    // 방어형 우주선 방어력 추가 기능
    int defenseUp(){
        System.out.println("방어력 강화");
        hp = hp + def;
        return hp;
    }
}