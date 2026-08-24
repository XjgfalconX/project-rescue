import java.util.ArrayList;

public class Defend implements ActiveSkill {

    public int getManaCost() { return 0; }
    public String getName() { return "Defend"; }
    public void execute(ArrayList<Character> chars) {
        for(int i = 0; i < chars.size(); i++) {
            if(chars.get(i) instanceof Player) chars.get(i).setDefending(true);

        }
    }
}