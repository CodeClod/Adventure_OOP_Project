import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Room currentRoom;
    UserInterface ui = new UserInterface();
    LockedDoors ld = new LockedDoors();
    Scanner in = new Scanner(System.in);
    String input;
    // xyzzy stuff
    Room xyzzy;
    Room teleportRoom;
    Room lastRoom = null;



    Player(Room StartingRoom) {
        currentRoom = StartingRoom;
        xyzzy = StartingRoom;

    }

    String playerInput() {
        input = in.nextLine();
        return input;
    }

    Room getCurrentRoom() {
        return currentRoom;
    }

    void setCurrentRoom(Room room) {
        currentRoom = room;
    }
    Room getLastRoom() {
        return lastRoom;
    }
    // Tried refactoring the move command... gave up after about 1 hour. It works
    void playerMove(String string) {
        switch (string) {
            case "go north", "n" -> {
                lastRoom = currentRoom;
                if (currentRoom.checkIfDoorIsLockedNorth())
                    ld.doorLocked(currentRoom, ld.directionsParse(string));
                else if (currentRoom.getRoomNorth() != null) {
                    currentRoom = currentRoom.getRoomNorth();
                    if ((!currentRoom.checkIfVisited())) {
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
                    ld.doorLocked(currentRoom, ld.directionsParse(string));
                else if (currentRoom.getRoomEast() != null) {
                    currentRoom = currentRoom.getRoomEast();
                    if (!(currentRoom.checkIfVisited())) {
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
                    ld.doorLocked(currentRoom, ld.directionsParse(string));
                else if (currentRoom.getRoomSouth() != null) {
                    currentRoom = currentRoom.getRoomSouth();
                    if (!(currentRoom.checkIfVisited())) {
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
                    ld.doorLocked(currentRoom, ld.directionsParse(string));
                else if (currentRoom.getRoomWest() != null) {
                    currentRoom = currentRoom.getRoomWest();
                    if (!(currentRoom.checkIfVisited())) {
                        currentRoom.setVisitedTrue();
                        ui.goWestMessage(currentRoom);
                    } else
                        ui.goWestVisitedMessage(currentRoom);
                } else
                    ui.invalidDirection();
            }
        }
    }
    void xyzzy() {
        teleportRoom = currentRoom;
        currentRoom = xyzzy;
        System.out.println("You teleported to " + currentRoom.getName());
        xyzzy = teleportRoom;
    }

}
