public class DefensiveSpaceShip extends SpaceShip{
    // ����� ���ּ� ����
    int def;
    // ����� ���ּ� ���� �߰� ���
    int defenseUp(){
        System.out.println("���� ��ȭ");
        hp = hp + def;
        return hp;
    }
}