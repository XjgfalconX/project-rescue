import java.util.ArrayList;
import java.util.Scanner;
public class Npc extends Character {
    private Scanner input;
    private int xp;
    private String nextMoveIntent = "";
    public Npc(int hp, int ap, String name, int speed, int dp, int acc, int xp) {
        super(hp, ap, name, speed, dp, acc, xp);
        input = new Scanner(System.in);
    }

    public void dmgPlayer(ArrayList<Character> chars, int extra) {
        for(int i = 0; i < chars.size(); i++) {
            if(chars.get(i) instanceof Player) {
                chars.get(i).dmgHp(super.ap + extra);
                System.out.println("The attack landed!");
            }
        }
    }

    public void addStatusToPlayer(ArrayList<Character> chars, String status) {
        for(int i = 0; i< chars.size(); i++) {
            if(chars.get(i) instanceof Player) {
                chars.get(i).addStatus(status);
                return;
            }
        }
    }

    public void performAction(ArrayList<Character> chars, String[] attacks) {

    }


    public boolean attackPlayer(ArrayList<Character> chars, int num) {
        if(((int) (Math.random() * 100 + 1)) <= this.acc) {
            dmgPlayer(chars, num);
            input.nextLine();
            return true;
        }
        System.out.println("The attack missed!");
        input.nextLine();
        return false;
    }


    public void calculateIntent() {
        if(this.name.equals("Slime")) {
            int dec = (int) (Math.random() *4) + 1;
            if(dec > 1) nextMoveIntent = "Leap Attack";
            else nextMoveIntent = "Acid Spit";
        } else if (this.name.equals("Goblin")) {
            int dec = (int) (Math.random() * 4) + 1;
            if(dec == 1) nextMoveIntent= "Spear Stab";
            else if (dec == 2) nextMoveIntent = "Dagger Slash";
            else if ( dec ==3 ) nextMoveIntent = "Mace Crush";
            else nextMoveIntent = "Laugh Mockingly";
        }
    }

    public String getIntent() { return nextMoveIntent; }
    public void performAction(ArrayList<Character> chars) {
        //Super keyword is basically 'this', but its for super class variables 

        switch(nextMoveIntent) {

            case "Leap Attack":
                System.out.println("The slime leaps at you!");
                attackPlayer(chars, 0);
                break;
            case "Acid Spit":
                System.out.println("The slime spits acid at you!");
                addStatusToPlayer(chars, "Weak Acid");
                attackPlayer(chars, 2);
                break;
            case "Spear Stab":
                System.out.println("The goblin takes out a spear and stabs at you!");
                attackPlayer(chars, 4);
                break;
            case "Dagger Slash":
                System.out.println("The goblin takes out its daggers and slashes at your ankles!");
                addStatusToPlayer(chars, "Weak Slow");
                attackPlayer(chars, 0);
                break;
            case "Mace Crush":
                System.out.println("The goblin takes out a mace and tries to crush you!");
                addStatusToPlayer(chars, "Blunt Trauma");
                attackPlayer(chars, 2);
                break;
            case "Laugh Mockingly":
                System.out.println("The goblin points its finger at you and laughs!");
                System.out.println("It wastes its turn");
                input.nextLine();
                break;
            default:
                System.out.println(super.name + " Is attacking!");
                attackPlayer(chars, 0);
                break;
        }
        this.calculateIntent();
    }
}