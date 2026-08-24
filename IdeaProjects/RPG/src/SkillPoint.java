import java.util.ArrayList;

public class SkillPoint {
    private String name;
    private ArrayList<String> reqs;



    public SkillPoint(String name) {
        this.name = name;
    }

    public void updateReqs() {
        switch(name) {
            case "Arsenal":
                reqs.add("Heavy");
                reqs.add("Medium");
                reqs.add("Light");
        }
    }

    public ArrayList<String> getReqs() {
        return this.reqs;
    }
    public String getName() { return name; }

}