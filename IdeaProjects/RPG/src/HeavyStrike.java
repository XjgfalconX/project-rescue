import java.util.ArrayList;
import java.util.Scanner;
public class HeavyStrike implements ActiveSkill {
    private Scanner input;
    private Player cha;
    public String getName() { return "Heavy Overhead Strike"; }
    public int getManaCost() { return 5; }


    public void execute(ArrayList<Character> chars) {
        ArrayList<Integer> enemyIndex = new ArrayList<>();
        int idx = 1;
        enemyIndex.add(0);
        for(Character ch : chars) {
            if(ch instanceof Player) {
                cha = (Player) ch;
            }
        }
        int choice;
        input = new Scanner(System.in);
        System.out.println("Which enemy would you like to attack?\n Press 0 to go back");
        for(int i = 0; i < chars.size(); i++) {
            if(!(chars.get(i) instanceof Player)) {
                if((chars.get(i).isAlive())) {
                    enemyIndex.add(i);
                    System.out.println(idx + ", " + chars.get(i).getName());
                    idx++;
                }
            }
        }
        if (cha.getStam() < 5) {
            System.out.println("You dont have enough mana!");
            cha.performAction(chars);
            return;
        }
        if(( (int)(Math.random() * 10) + 1) < cha.getAcc()) {
            choice = input.nextInt();
            if(choice == 0) {
                cha.performAction(chars);
                return;
            }
            cha.setStam(cha.getStam() - 5);
            chars.get(enemyIndex.get(choice)).dmgHp(cha.getAp()*2);
        } else {
            System.out.println("You missed");
        }
    }
}