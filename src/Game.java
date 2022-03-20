public class Game {
    UserInterface ui = new UserInterface();
    Map map = new Map();
    StringBuilder roomCheck = new StringBuilder();
    Darkness dark = new Darkness();
    String playerInput;

    void tellIfVisited(Room room) {
        // Checks if you've gone that way before and tells you
        if (room.getRoomEast() != null) {
            if (room.getRoomEast().checkIfVisited()) {
                if (roomCheck.isEmpty())
                    roomCheck.append("There are ways: ");
                roomCheck.append(" -East- ");
            }
        }
        else if (room.getRoomNorth() != null) {
            if (room.getRoomNorth().checkIfVisited()) {
                if (roomCheck.isEmpty())
                    roomCheck.append("You can go: ");
                roomCheck.append(" -North- ");
            }
        }
        else if (room.getRoomWest() != null) {
            if (room.getRoomWest().checkIfVisited()) {
                if (roomCheck.isEmpty())
                    roomCheck.append("You can go: ");
                roomCheck.append(" -West- ");
            }
        }
        if (room.getRoomSouth() != null) {
            if (room.getRoomSouth().checkIfVisited()) {
                if (roomCheck.isEmpty()) {
                    roomCheck.append("You can go: ");
                }
                roomCheck.append(" -South- ");
            }
        }
        if (!(roomCheck.isEmpty()))
            System.out.println(roomCheck);
        roomCheck.delete(0,roomCheck.length());
    }

    void runProgram() {
        // attributes
        boolean game = true;
        String userInput;

        // Creators
        map.createRooms();
        Player player = new Player(map.getStartRoom());

        // Program Start
        ui.programStartupWelcome();

        // Program loop
        do {
            if (player.getCurrentRoom().checkIfDarkRoom() && !player.getCurrentRoom().checkIfLightsOn())
                player.setCurrentRoom(dark.torchOn(player.getCurrentRoom(),player.getLastRoom()));
            else if (player.getCurrentRoom().checkIfDarkRoom() && player.getCurrentRoom().checkIfLightsOn())
                player.setCurrentRoom(dark.torchOff(player.getCurrentRoom(), player.getLastRoom()));

            ui.askForPrompt();
            playerInput = player.playerInput();

            // Switch for all commands
            switch (playerInput) {
                case "go north", "n" -> player.playerMove("n");
                case "go east", "e" -> player.playerMove("e");
                case "go south", "s" -> player.playerMove("s");
                case "go west", "w" -> player.playerMove("w");
                case "help" -> ui.listOfCommands();
                case "exit" -> {
                    ui.goodbyeMessage();
                    game = false;
                }
                case "look" -> {
                    ui.lookAround(player.getCurrentRoom());
                    tellIfVisited(player.getCurrentRoom());
                    player.findItems();
                }
                case "inventory", "invent", "inv" -> {
                    player.displayPlayerInventory();
                    player.dropItems();
                }

                case "xyzzy" -> {
                    player.xyzzy();
                }
                default -> ui.invalidCommand();
            }
            System.out.println();
        } while (game);
    }
}
