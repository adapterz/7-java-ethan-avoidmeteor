public class avoidMeteor {
    public static void main(String[] args) {
        BattleSpaceShip battleSpaceShip = new BattleSpaceShip();
        battleSpaceShip.attack();

        ScoutSpaceShip scoutSpaceShip = new ScoutSpaceShip();
        scoutSpaceShip.move();

        DefensiveSpaceShip defensiveSpaceShip = new DefensiveSpaceShip();
        defensiveSpaceShip.defenseUp();

    }
}




