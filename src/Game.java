import java.util.Scanner;

public class Game {
    Scanner in = new Scanner(System.in);
    UserInterface ui = new UserInterface();
    Map map = new Map();
    StringBuilder roomCheck = new StringBuilder();
    LockedDoors ld = new LockedDoors();
    Darkness dark = new Darkness();

    void tellsIfVisited(Room room) {
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
        // attributter
        boolean game = true;
        String userInput;


        map.createRooms();

        // Sets starting room
        Room currentRoom = map.getStartRoom();
        currentRoom.setVisitedTrue();

        // xyzzy stuff
        Room xyzzy = map.getRoom1();
        Room teleportRoom;
        Room lastRoom = null;

        // Program Start
        ui.programStartupWelcome();

        // Program loop
        do {
            if (currentRoom.checkIfDarkRoom() && !currentRoom.checkIfLightsOn())
            currentRoom = dark.torchOn(currentRoom,lastRoom);
            else if (currentRoom.checkIfDarkRoom() && currentRoom.checkIfLightsOn())
            currentRoom = dark.torchOff(currentRoom, lastRoom);

            ui.askForPrompt();

            userInput = in.nextLine();

            switch (userInput) {
                case "go north", "n" -> {
                    lastRoom = currentRoom;
                    if (currentRoom.checkIfDoorIsLockedNorth())
                        ld.doorLocked(currentRoom, ld.directionsParse(userInput));
                    else if (currentRoom.getRoomNorth() != null) {
                        currentRoom = currentRoom.getRoomNorth();
                        if (currentRoom.checkIfVisited() == false) {
                            currentRoom.setVisitedTrue();
                            ui.goNorthMessage(currentRoom);
                        } else
                            ui.goNorthVisitedMessage(currentRoom);
                    } else
                        ui.invalidDirection();
                }
                case "go east", "e" -> {
                    lastRoom = currentRoom;
                    if (currentRoom.checkIfDoorIsLockedEast())
                        ld.doorLocked(currentRoom, ld.directionsParse(userInput));
                    else if (currentRoom.getRoomEast() != null) {
                        currentRoom = currentRoom.getRoomEast();
                        if (currentRoom.checkIfVisited() == false) {
                            currentRoom.setVisitedTrue();
                            ui.goEastMessage(currentRoom);
                        } else
                            ui.goEastVisitedMessage(currentRoom);
                    } else
                        ui.invalidDirection();
                }
                case "go south", "s" -> {
                    lastRoom = currentRoom;
                    if (currentRoom.checkIfDoorIsLockedSouth())
                        ld.doorLocked(currentRoom, ld.directionsParse(userInput));
                    else if (currentRoom.getRoomSouth() != null) {
                        currentRoom = currentRoom.getRoomSouth();
                        if (currentRoom.checkIfVisited() == false) {
                            currentRoom.setVisitedTrue();
                            ui.goSouthMessage(currentRoom);
                        } else
                            ui.goSouthVisitedMessage(currentRoom);
                    } else
                        ui.invalidDirection();
                }
                case "go west", "w" -> {
                    lastRoom = currentRoom;
                    if (currentRoom.checkIfDoorIsLockedWest())
                        ld.doorLocked(currentRoom, ld.directionsParse(userInput));
                    else if (currentRoom.getRoomWest() != null) {
                        currentRoom = currentRoom.getRoomWest();
                        if (currentRoom.checkIfVisited() == false) {
                            currentRoom.setVisitedTrue();
                            ui.goWestMessage(currentRoom);
                        } else
                            ui.goWestVisitedMessage(currentRoom);
                    } else
                        ui.invalidDirection();
                }
                case "help" -> ui.listOfCommands();
                case "exit" -> {
                    System.out.println("Thanks for playing. Goodbye!");
                    game = false;
                }
                case "look" -> {
                    ui.lookAround(currentRoom);
                    tellsIfVisited(currentRoom);
                }
                case "xyzzy" -> {
                    teleportRoom = currentRoom;
                    currentRoom = xyzzy;
                    System.out.println("You teleported to " + currentRoom.getName());
                    xyzzy = teleportRoom;
                }
                default -> ui.invalidCommand();
            }
            System.out.println();

        } while (game == true);

    }

}
