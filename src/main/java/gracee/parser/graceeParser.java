package gracee.parser;

public class graceeParser {

    public enum MainCmd {
        SHOP,
        PRICE,
        HISTORY,
        TASKS,
        EXIT,
        INVALID
    }

    public static MainCmd parseMain(String s){
        switch (s) {
            case "1":
                return MainCmd.SHOP;
            case "2":
                return MainCmd.PRICE;
            case "3":
                return MainCmd.HISTORY;
            case "4":
                return MainCmd.TASKS;
            case "5":
                return MainCmd.EXIT;
            default:
                return MainCmd.INVALID;
        }
    }

    public enum subTaskCmd {
        ADD,
        UPDATE,
        LIST,
        REMOVE,
        BACK,
        INVALID
    }

    public static subTaskCmd parseSubTask(String s){
        switch (s) {
            case "1" :
                return subTaskCmd.ADD;
            case "2" :
                return subTaskCmd.UPDATE;
            case "3" :
                return subTaskCmd.LIST;
            case "4" :
                return subTaskCmd.REMOVE;
            case "5" :
                return subTaskCmd.BACK;
            default:
                return subTaskCmd.INVALID;
        }
    }
}
