package gracee.parser;

/**
 * Parse user input commands
 */

public class graceeParser {

    /**
     * Command categories for main menu
     */
    public enum MainCmd {
        SHOP,
        PRICE,
        HISTORY,
        TASKS,
        EXIT,
        INVALID
    }

    /**
     * Parse user input and map to {@link MainCmd}
     * @param input as user input string
     * @return Corresponded value for {@link MainCmd}
     */

    public static MainCmd parseMain(String input){
        switch (input) {
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

    /**
     * Command categories for sub menu - task
     */

    public enum subTaskCmd {
        ADD,
        UPDATE,
        LIST,
        REMOVE,
        SEARCH,
        KEYWORD,
        BACK,
        INVALID
    }

    /**
     * Parse user input string and map to {@link subTaskCmd}
     * @param input as user input string
     * @return Corresponded value for {@link subTaskCmd}
     */

    public static subTaskCmd parseSubTask(String input){
        switch (input) {
            case "1" :
                return subTaskCmd.ADD;
            case "2" :
                return subTaskCmd.UPDATE;
            case "3" :
                return subTaskCmd.LIST;
            case "4" :
                return subTaskCmd.REMOVE;
            case "5" :
                return subTaskCmd.SEARCH;
            case "6" :
                return subTaskCmd.KEYWORD;
            case "7" :
                return subTaskCmd.BACK;
            default:
                return subTaskCmd.INVALID;
        }
    }
}
