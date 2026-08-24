import java.util.Scanner;
import java.util.ArrayList;
public class Player extends Character {
    // you will learn what extension is later in CSA 
    // basically character is a parent class and i can
    // use its methods in this class by using the "super"
    // keyword, rather than needing to retype all of
    // the getters and setters
    private Scanner input;
    private int level;
    private int xp;
    private ArrayList<Equipment> inventory;
    private ArrayList<SkillPoint> skillPoints;
    private ArrayList<SkillPoint> passiveSkills;
    private ArrayList<ActiveSkill> activeSkills;
    private String usedSkill;
    private int stamina;


    public Player(int hp, int ap, String name, int speed, int dp, int acc, int xp, int stamina) {
        super(hp, ap, name, speed, dp, acc, xp);
        this.input = new Scanner(System.in);
        this.inventory = new ArrayList<>();
        this.passiveSkills = new ArrayList<>();
        this.activeSkills = new ArrayList<>();
        this.skillPoints = new ArrayList<>();
        this.activeSkills.add(new Defend());
        this.skillPoints.add(new SkillPoint("Heavy"));
        this.stamina = stamina;
    }

    public int getStam() { return stamina; }
    public void setStam(int stamina) { this.stamina = stamina; }

    public void addPassiveSkill(SkillPoint skill) { this.passiveSkills.add(skill); }
    public void addActiveSkill(ActiveSkill skill) { this.activeSkills.add(skill); }

    public int getLevel() { return this.level; }

    public void setLevel(int level) { this.level = level; }

    public void levelUp() {
        level++;
        //needs to do more (stats, and skills, possibly a level up sequence);
    }
    public void learnSkill(ActiveSkill newSkill) {
        boolean hasSkill = false;
        for (ActiveSkill s: activeSkills) {
            if(s.getName().equals(newSkill.getName())) return;
        }
        activeSkills.add(newSkill);
    }

    public void loadSkills() {
        for(int i = 0; i < skillPoints.size(); i++) {
            if(skillPoints.get(i).getName().equals("Heavy")) {
                learnSkill(new HeavyStrike());
            }
        }
    }

    public void addStatus(String status) {
        if(this.isDefending) {
            System.out.println(this.name + " is defending, status is not applied");
            input.nextLine();
            return;
        }
        for(int i = 0; i < super.statuses.size(); i++) {
            if(super.statuses.get(i).equals(status)) {
                System.out.println(this.name + " Already has this satatus!");
                input.nextLine();
                return;
            }
        }
        super.statuses.add(status);
    }
    public void performAction(ArrayList<Character> chars) {
        this.loadSkills();
        ArrayList<Integer> enemyIndex = new ArrayList<>();
        int idx = 1;
        enemyIndex.add(0);

        //IF THE STATUSES GO THROUGH DEFEND, PUT THEM AFTER THIS DECLARATION
        //OTHERWIDE PUT THEM BEFORE
        isDefending = false;

        System.out.println("It is your turn! What would you like to do?\n1. Skills\n2. Spells\n3. Attack\n4. Run");
        int choice = input.nextInt();
        if(choice == 1) {
            System.out.println("Select a skill, press 0 to go back");
            for(int i = 0; i < activeSkills.size(); i++) {
                System.out.println((i + 1) + ", " + activeSkills.get(i).getName() + " (Cost: " + activeSkills.get(i).getManaCost() + ")");
            }
            choice = input.nextInt() - 1;
            if(choice == -1) {
                performAction(chars);
                return;
            }
            if(choice >= 0 && choice < activeSkills.size()) {
                ActiveSkill selectedSkill = activeSkills.get(choice);
                selectedSkill.execute(chars);
            }
        }

        if(choice == 3) {
            System.out.println("Which enemy would you like to attack?\n Press 0 to go back");
            for(int i = 0; i < chars.size(); i++) {
                if(!(chars.get(i) instanceof Player)) {
                    if((chars.get(i).isAlive())) {
                        enemyIndex.add(i);
                        System.out.println(idx + ". " + chars.get(i).getName());
                        idx++;
                    }
                }
            }
            if(((int) Math.random() * 10 + 1) < acc) {
                choice = input.nextInt();
                if(choice == 0) {
                    performAction(chars);
                    return;
                }
                chars.get(enemyIndex.get(choice)).dmgHp(super.ap);
            } else {
                System.out.println("You missed!");
            }
        }
    }

}