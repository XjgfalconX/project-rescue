import java.util.ArrayList;
import java.util.Scanner;

public class Character { // parent of npc and player
    private Scanner input;
    String name;
    int hp, ap, speed, dp, acc, xp;
    boolean isAlive;
    ArrayList<String> statuses;
    boolean isDefending = false;

    // will be adding more variables, maybe ill
    // only keep these for the character parent class
    // and add more variables for characters and npcs

    //constructor
    public Character(int hp, int ap, String name, int speed, int dp, int acc, int xp) {
        this.input = new Scanner(System.in);
        this.statuses = new ArrayList<>();
        this.xp = xp;
        this.hp = hp;
        this.ap = ap;
        this.name = name;
        this.isAlive = hp > 0;
        this.speed = speed;
        this.dp = dp;
        this.acc = acc;
    }

    //getters | setters
    public String getName() {
        return this.name;
        // you dont need "this" but its good practice
        //because i am specifically referring to 
        // objects variable, not a parameter
    }

    //This format looks neater to me for one line methods

    public ArrayList<String> getStatuses() { return statuses; }

    public int getAcc() { return this.acc; }

    public int getXp() { return this.xp; }

    public int getHp() { return this.hp; }

    public int getDp() { return this.dp; }

    public boolean isAlive() { return isAlive; }

    public int getAp() { return this.ap; }

    public boolean isDefending() { return isDefending; }

    public int getSpeed() { return this.speed; }

    public void setXp(int xp) { this.xp = xp; }

    public void setDefending(boolean state) { isDefending = state; }

    public void setName(String name) { this.name = name; }

    public void dmgHp(int dmg) {
        int temp = 0;
        if(isDefending) { temp = dp*2; }
        else { temp = dp; }
        dmg -= temp;
        if(dmg < 1) {
            dmg = 1;
        }
        hp = hp-dmg;
    }

    public void setAp(int ap) { this.ap = ap; }

    public void setSpeed(int speed) { this.speed = speed; }

    public void setDp(int dp) { this.dp = dp; }

    public void addStatus(String status) {
        for(int i = 0; i < statuses.size(); i++) {

            if(this.isDefending)  {
                System.out.println(this.name + " Is defending, the status has no effect!");
                return;
            }
            if(statuses.get(i).equals(status)) {
                System.out.println(this.name + " already has this status!");
                return;
            }

        }
        this.statuses.add(status);
    }

    //important methods
    public final boolean loadTurn() {
        isAlive = hp > 0;
        if(!this.isAlive) {
            System.out.println(this.name + " is dead, skipping turn");
            input.nextLine();
        }
        return isAlive;
    }

    //This method will get overwritten by the sublasses
    public void performAction(ArrayList<Character> chars) {
        return;
    }

    public void loadStatuses() {
        for(int i = 0; i < statuses.size(); i++) {
            if(statuses.get(i).equals("Weak Acid")) {
                this.dp -= 3; // slimes acid spit attack
            }
            if(statuses.get(i).equals("Weak Stone Skin")) {
                this.dp += 3; // defense power
            }
            if(statuses.get(i).equals("Weak Blind")) {
                this.acc /= 1.5;
            }
            if(statuses.get(i).equals("Weak Slow")) {
                this.acc -= 10;
                this.speed /= 2;
            }
            if(statuses.get(i).equals("Blunt Trauma")) {
                this.speed -= 2;
                System.out.println("Your head hurts!");
            }

        }
    }


}