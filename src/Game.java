import java.util.Scanner;

public class Game {
    Scanner in = new Scanner(System.in);
    UserInterface ui = new UserInterface();
    Map map = new Map();

    void tellsIfVisited(Room room) {

    }

    void runProgram() {
        // attributter
        boolean game = true;
        boolean darkness;
        boolean torch = false;
        String userInput;
        StringBuilder roomCheck = new StringBuilder();

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
            // Darkness! - room 6 - haunted forest
            if (currentRoom == map.getRoom6() && map.getRoom6().checkIfLightsOn() && torch) {
                System.out.println("You feel the light shining through the branches, do you really need a torch in hand?");
                do {
                    darkness =false;
                    System.out.println("Put your torch away (t) or continue with it lit (c)"); // "Put your torch away" used instead of "turn light off".
                    System.out.println("What will you do?: ");
                    switch (in.nextLine()) {
                        case "t" -> {
                            darkness = true;
                            torch = false;
                            map.getRoom6().setLightsOff();
                            currentRoom = lastRoom;
                            lastRoom = map.getRoom6();
                            System.out.println("As soon as you put your torch out the darkness returns!");
                            System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
                        }
                        case "c" -> {
                            darkness = true;
                            System.out.println("You continue onward with a torch in your hand.");
                        }
                        default -> System.out.println("You pause for a second as a ray of sunlight gently hits your face. What is your choice.");
                    }

                } while (!darkness);
            }
            if (currentRoom == map.getRoom6() && !map.getRoom6().checkIfLightsOn() && !torch) {
                System.out.println("Suddenly you feel like the darkness is closing in on you!");
                do {
                    darkness = true;
                    System.out.println("Light a torch (t) or return whence you came (r)"); // "Light a torch" used instead of "turn light on".
                    System.out.println("What will you do?: ");
                    switch (in.nextLine()) {
                        case "t" -> {
                            darkness = false;
                            torch = true;
                            map.getRoom6().setLightsOn();
                            System.out.println("You light your torch and the darkness dissipates.");
                        }
                        case "r" -> {
                            darkness = false;
                            currentRoom = lastRoom;
                            lastRoom = map.getRoom6();
                            System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
                        }
                        default -> System.out.println("The darkness engulfs you! You have to make a choice!");
                    }
                } while (darkness);
            }
            if (map.getRoom6().checkIfLightsOn()) {
                map.getRoom6().setRoomDescription("a light forest, filled with all sorts of thorny plant.");
                map.getRoom6().setRoomDescriptionShort("a light forest, filled with all sorts of thorny plant.");
            }

            ui.askForPrompt();

            userInput = in.nextLine();

            switch (userInput) {
                case "go north", "n" -> {
                    lastRoom = currentRoom;
                    if (currentRoom.getRoomNorth() != null) {
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
                    if (currentRoom.getRoomEast() != null) {
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
                    if (currentRoom.getRoomSouth() != null) {
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
                    if (currentRoom.getRoomWest() != null) {
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
                    // Checks if you've gone that way before and tells you
                    if (currentRoom.getRoomEast() != null) {
                        if (currentRoom.getRoomEast().checkIfVisited()) {
                            if (roomCheck.isEmpty())
                                roomCheck.append("There are ways:");
                            roomCheck.append(" -East- ");
                        }
                    }
                    else if (currentRoom.getRoomNorth() != null) {
                        if (currentRoom.getRoomNorth().checkIfVisited()) {
                            if (roomCheck.isEmpty())
                                roomCheck.append("You can go:");
                            roomCheck.append(" -North- ");
                        }
                    }
                    else if (currentRoom.getRoomWest() != null) {
                        if (currentRoom.getRoomWest().checkIfVisited()) {
                            if (roomCheck.isEmpty())
                                roomCheck.append("You can go:");
                            roomCheck.append(" -West- ");
                        }
                    }
                    if (currentRoom.getRoomSouth() != null) {
                        if (currentRoom.getRoomSouth().checkIfVisited()) {
                            if (roomCheck.isEmpty()) {
                                roomCheck.append("You can go:");
                            }
                            roomCheck.append(" -South- ");
                        }
                    }
                    if (!(roomCheck.isEmpty()))
                        System.out.println(roomCheck);
                    roomCheck.delete(0,roomCheck.length());
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
