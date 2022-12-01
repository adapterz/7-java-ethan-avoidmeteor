package spaceship;

import spaceship.SpaceShip;

import static spaceship.BattleSpaceShip.speed;

public class ScoutSpaceShip extends SpaceShip {
    // 정찰형 우주선 부스터 게이지
    int boostGauge;
    // 정찰형 우주선 부스터 기능
    int booster(){
        System.out.println("부스터 사용하기");
        speed = speed + boostGauge;
        return speed;
    }
}
