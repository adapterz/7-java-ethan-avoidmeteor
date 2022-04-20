public class avoidMeteor {
    public static void main(String[] args) {
        BattleSpaceShip battleSpaceShip = new BattleSpaceShip();
        battleSpaceShip.attack();

        ScoutSpaceShip scoutSpaceShip = new ScoutSpaceShip();
        scoutSpaceShip.booster();

        DefensiveSpaceShip defensiveSpaceShip = new DefensiveSpaceShip();
        defensiveSpaceShip.defenseUp();

        Meteor meteor = new Meteor();
        meteor.scatter();

        Alien alien = new Alien();
        alien.fallFaster();

    }
}




