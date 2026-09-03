import java.util.Scanner;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("This is unfinished, and will always be unfinished");
        System.out.println("Enter a name for your character");
        String name = input.nextLine();
        Character player = new Player(30, 5, name, 10, 1, 100, 0, 20);
        Character npc1 = new Npc(15, 3, "Slime", 55, 0, 100, 10);
        Character npc2 = new Npc(15, 3, "Goblin", 5, 2, 100, 10);
        ArrayList<Character> chars = new ArrayList<Character>();
        chars.add(npc1);
        chars.add(npc2);
        chars.add(player);
        CombatEngine ce = new CombatEngine(chars);
        if(ce.combatStart()) {
            //Skill tree not implemented yet
            System.out.println("Congrats on winning!\nHere is a showcase of the skill tree");
        }
    }
}