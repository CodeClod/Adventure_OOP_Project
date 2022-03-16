public class UserInterface {

    void listOfCommands() {
        System.out.println("List of commands: ");
        System.out.println("\t\"help\" Prints list of commands.");
        System.out.println("\t\"look\" Take a look at current location.");
        System.out.println("\t\"exit\" Exits the game.");
        System.out.println("\t\"go north/south/east/west\" OR n/s/e/w, to move in a direction.");
    }

    void programStartupWelcome() {
        System.out.println("""
        Welcome to this little text-based adventure
        "Enter \"help\" to get a list of commands"
        
        You find yourself seemingly lost walking through a forest.
        You're not sure how it happened, but you got no choice but to keep moving.
        (Move around with \"go \" plus \"north/south/east/west\" OR simply n/s/e/w)
        
        Now...""");
    }

    void goNorthMessage(Room room) {
        System.out.printf("You went north, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
    }
    void goNorthVisitedMessage(Room room) {
        System.out.printf("You went north, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
    }

    void goEastMessage(Room room) {
        System.out.printf("You went east, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
    }
    void goEastVisitedMessage(Room room) {
        System.out.printf("You went east, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
    }

    void goSouthMessage(Room room) {
        System.out.printf("You went south, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
    }
    void goSouthVisitedMessage(Room room) {
        System.out.printf("You went south, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
    }

    void goWestMessage(Room room) {
        System.out.printf("You went west, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
    }
    void goWestVisitedMessage(Room room) {
        System.out.printf("You went west, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
    }

    void lookAround(Room room) {
        System.out.println("You're in a " + room.getName() + ". Looking around, you see " + room.getRoomDescription());
    }

    void askForPrompt() {
        System.out.println("What will you do?: ");
    }

    void invalidDirection() {
        System.out.println("Can't go that way.");
    }

    void invalidCommand() {
        System.out.println("Invalid command. Type \"help\" for a list of commands.");
    }
}
