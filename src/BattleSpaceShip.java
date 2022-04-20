public class BattleSpaceShip extends SpaceShip{
    // 전투형 우주선 공격력
    int power;
    // 전투형 우주선 공격 기능
    int attack(){
        System.out.println("공격하기");
        return power;
    }
}