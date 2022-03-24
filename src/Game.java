public class Game {
    UserInterface ui = new UserInterface();
    Map map = new Map();
    StringBuilder roomCheck = new StringBuilder();
    String playerInput;

    void tellIfVisited(Room room) {
        // Checks if you've gone that way before and tells you
        for (int i = 0; i < Compass.values().length; i++) {
            if (room.getRoom(Compass.values()[i]) != null && room.getRoom(Compass.values()[i]).checkIfVisited()) {
                if (roomCheck.isEmpty())
                    roomCheck.append("There are ways: ");
                roomCheck.append(Compass.values()[i]);
            }
            if (!(roomCheck.isEmpty()))
                System.out.println(roomCheck);
            roomCheck.delete(0,roomCheck.length());
        }
    }

    void runProgram() {
        // attributes
        boolean game = true;

        // Creators
        map.createRooms();
        Player player = new Player(map.getStartRoom());

        // Program Start
        ui.programStartupWelcome();

        // Program loop
        do {
            player.checkForDarkness();
            ui.askForPrompt();
            playerInput = player.playerInput();

            // Switch for all commands
            switch (playerInput) {
                case "go north", "n" -> player.playerMove(Compass.NORTH);
                case "go east", "e" -> player.playerMove(Compass.EAST);
                case "go south", "s" -> player.playerMove(Compass.SOUTH);
                case "go west", "w" -> player.playerMove(Compass.WEST);
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

                case "xyzzy" -> player.xyzzy();
                default -> ui.invalidCommand();
            }
            System.out.println();
        } while (game);
    }
}
