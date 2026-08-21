import java.util.*;
import java.time.*;

public class Main
{
    private static LocalDate date = LocalDate.now();
    public static Map<LocalDate, ArrayList<Integer>> dates = new HashMap<>();
    public static Map<String, DateManager> reocurrances = new HashMap<>();
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> commands = new ArrayList<>(List.of("help", "setDate", "nd", "logExpense", "logProfit", "setRecurringExpense", "setRecurringProfit", "date", "getPL", "getPLR"));

    public static void main(String[] args)
    {
        System.out.println("This is an expense tracker!\nI used java's LocalDate to handle the dates\nI am going to add the nio file import to handle I/O\nAnd I used a HashMap to handle the date for expenses and profits");
        while(true) {
            String usrIn = input.nextLine();
            doCmd(usrIn);
        }
    }

    public static void help() {
        System.out.println("This is a list of the commands: ");
        for(String cmd : commands) {
            System.out.println(cmd);
        }
    }

    public static void help(String cmd) {
        switch(cmd) {
            case "nd":
                System.out.println("Sets the date to the next day");
                break;
            case "setRecurringExpense":
                System.out.println("Creates a expense that happens every certain amount of days\nMake sure not to put a negative value, it automatically sets the number you put as an expense");
                break;
            case "setRecurringProfit":
                System.out.println("Creates a profit that happens every certain amount of days");
                break;
            case "help":
                System.out.println("System's help command, can be used to list out commands\nOr can be used to get help with specific commands");
                break;
            case "setDate":
                System.out.println("Sets current date, must be formatted:\n xxxx-xx-xx");
                break;
            case "date":
                System.out.println("Looks at the current date you have set");
                break;
            default:
                System.out.println("That is not a proper command");
                break;
        }
    }

    public static void checkValue(LocalDate dateOfValueChange) {
        ArrayList<Integer> values = dates.get(dateOfValueChange);
        if (values != null) {
            for (Integer val : values) {
                System.out.println(val);
            }
        } else {
            System.out.println("There were no value changes on this day");
        }
    }

    public static void addValue(Integer value) {
        dates.computeIfAbsent(date, k -> new ArrayList<>()).add(value);
    }

    public static void getProfitLoss(LocalDate date) {
        try {
            for(Integer i : dates.get(date)) {
                System.out.println(i);
            }
        }
        catch(Throwable t) {
            System.out.println("There are no changes marked for this date");
        }
    }

    public static void doCmd(String cmd) {
        try {
            if(cmd.substring(0, 5).equalsIgnoreCase("getPL")) {
                if(cmd.length() > 5) {
                    getProfitLoss(LocalDate.parse(cmd.substring(6))); // fix add parse
                    return;
                }
            }
            if(cmd.substring(0, 4).equalsIgnoreCase("help")) {
                if(cmd.length() > 4) {
                    help(cmd.substring(5));
                    return;
                }
                help();
                return;
            }
        } catch (Throwable i) {
        }

        if(commands.indexOf(cmd) < 0) {
            System.out.println("That is not a proper command");
            return;
        }
        int interval;
        int amount;

        switch(cmd) {
            case "setDate":
                System.out.println("New date: xxxx-xx-xx");
                String inp = input.nextLine();
                try {
                    date = LocalDate.parse(inp);
                } catch (Throwable t) {
                    System.out.println("You formatted it wrong, make sure\nTo type a 0 infront of 1 digit months!");
                    return;
                }
                System.out.println("New date: " + inp);
                return;

            case "date":
                System.out.println(date + "");
                return;

            case "nd":
                date = date.plusDays(1);
                // Triggers checks for recurring expenses on the new day
                for (DateManager dm : reocurrances.values()) {
                    dm.updateReoccurrence(date);
                }
                return;

            case "help":
                help();
                return;

            case "setRecurringExpense": {
                System.out.println("After how many days does this expense occur?");
                interval = input.nextInt();
                System.out.println("How much is the expense?");
                amount = input.nextInt();
                input.nextLine();
                System.out.println("What is the name for this expense?");
                String name = input.nextLine();
                addValue(amount*-1);
                DateManager x = new DateManager(date, interval, amount * -1);
                reocurrances.put(name, x);
                return;
            }
            case "setRecurringProfit": {
                System.out.println("After how many days does this profit occur?");
                interval = input.nextInt();
                System.out.println("How much is the profit?");
                amount = input.nextInt();
                input.nextLine();
                System.out.println("What is the name for this profit?");
                String name = input.nextLine();
                addValue(amount);
                DateManager x = new DateManager(date, interval, amount);
                reocurrances.put(name, x);
                return;
            }
            case "getPL":

        }
    }
}