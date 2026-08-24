import java.util.Scanner;
import java.util.ArrayList;



public class CombatEngine {
    private Scanner input;
    private int turnIndex;
    private ArrayList<Character> chars;

    private boolean pisDead = false;
    private boolean nisDead = false;

    public CombatEngine(ArrayList<Character> chars) {
        this.chars = chars;
        this.turnIndex = 0;
        this.input = new Scanner(System.in);
    }

    public boolean combatStart() {
        loadCharacters();


        while(true) {
            loadScreen();
            if(turnIndex >= chars.size()) { turnIndex = 0; }
            if(chars.get(turnIndex).loadTurn()) {
                chars.get(turnIndex).loadStatuses();
                chars.get(turnIndex).performAction(chars);
            }
            turnIndex++;
            endConditionLoader();
            if(pisDead) {
                System.out.println("You died!!!");
                return false;
            }
            else if(nisDead) {
                System.out.println("You won!!!");
                return true;
            }
        }
    }

    public void endConditionLoader() {
        int amtNpc = 0;
        int deadNpc = 0;
        for(int i = 0; i < chars.size(); i++) {
            if(chars.get(i) instanceof Npc) {
                amtNpc++;
                if(!(chars.get(i).isAlive())) {
                    deadNpc++;
                }
            }
            if(chars.get(i) instanceof Player) {
                if(!(chars.get(i).isAlive)) {
                    pisDead = true;
                }
            }
        }
        if(amtNpc == deadNpc) {
            nisDead = true;
        }
    }
    public void loadScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        //Clears the console

        for(int i = 0; i < chars.size(); i++) {
            if(chars.get(i) instanceof Player) {
                System.out.println("| Health: " + chars.get(i).getHp() + " | " + "Stamina(Mana): " + ((Player)chars.get(i)).getStam() +  " | ");
                System.out.print("Statuses: | ");
                for(int j = 0; j < chars.get(i).getStatuses().size(); j++) {
                    System.out.print(chars.get(i).getStatuses().get(j) + " | ");
                }
                System.out.println();
            }
        }
        for(int i = 0; i < chars.size(); i++) {
            if(!(chars.get(i) instanceof Player)) {
                System.out.println(chars.get(i).getName() + "'s Health: " + chars.get(i).getHp() + " | Intent: " + ((Npc)chars.get(i)).getIntent() + " | ");
            }
        }
    }// Orders the arraylist into the proper initiative order by speed
    public void loadCharacters() {
        //you should probably try to
        //understand this because it will
        //be useful to you for later
        //units

        //BUBBLE SORT ALGORITHM
        int n = this.chars.size();
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j <n - i - 1; j++) {
                //compare adjacent elements, and swap
                if(chars.get(j).getSpeed() < chars.get(j + 1).getSpeed()) {
                    Character temp = chars.get(j);
                    chars.set(j, chars.get(j+1));
                    chars.set(j+1, temp);
                }
            }
        }
        for(Character c : chars) {
            if(c instanceof Npc) {
                ((Npc)c).calculateIntent();
            }
        }
    }


}