import java.util.ArrayList;

public interface ActiveSkill {
    String getName();
    int getManaCost();
    void execute(ArrayList<Character> chars);
}