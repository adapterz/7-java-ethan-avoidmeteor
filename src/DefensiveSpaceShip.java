public class DefensiveSpaceShip extends SpaceShip{
    // 방어형 우주선 방어력
    int def;
    // 방어형 우주선 방어력 추가 기능
    int defenseUp(){
        System.out.println("방어력 강화");
        hp = hp + def;
        return hp;
    }
}