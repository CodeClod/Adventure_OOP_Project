import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Room currentRoom;
    UserInterface ui = new UserInterface();
    LockedDoors ld = new LockedDoors();
    Scanner in = new Scanner(System.in);
    String input;
    ArrayList<Item> playerItems = new ArrayList<>();

    void addItemPlayer(Item item) {
        playerItems.add(item);
        currentRoom.roomItems.remove(item);
    }

    void removeItemPlayer(Item item) {
        playerItems.remove(item);
        currentRoom.roomItems.add(item);
    }

    void findItems () {
        System.out.println("Do you want to search for item? Yes (y) or No (n)");
        switch (in.nextLine()) {
            case "y" -> {
                System.out.println("You search for items.");
                if (currentRoom.roomItems.size() == 0)
                    System.out.println("You don't find any items");
                else {
                    for (int i = 0; i < currentRoom.roomItems.size(); i++) {
                        System.out.println("You find a " + currentRoom.roomItems.get(i).shortName);
                        takeItem(currentRoom.roomItems.get(i));
                    }
                }
            }
            case "n" -> System.out.println("You don't search for items.");
        }
    }

    void takeItem(Item item) {
        System.out.printf("\nDo you want to take the %s? Yes (y) or No(n)\n",item.shortName);
        switch (in.nextLine()) {
            case "y" -> {
                addItemPlayer(item);
                System.out.println("You take the "+item.shortName);
                displayPlayerInventory();
                findItems();
            }
            case "n" -> System.out.printf("\nYou leave the %s be\n",item.shortName);
        }
    }

    void loseItems () {
        System.out.println("Do you want to drop an item? Yes (y) or No (n)");
        switch (in.nextLine()) {
            case "y" -> {
                System.out.println("You go through your inventory.");
                if (playerItems.size() == 0)
                    System.out.println("Your inventory is empty");
                else {
                    for (int i = 0; i < playerItems.size(); i++) {
                        displayPlayerInventory();
                        System.out.println("Do you want to drop the " + playerItems.get(i).shortName);
                        dropItem(playerItems.get(i));
                    }
                }
            }
            case "n" -> System.out.println("You keep your items.");
        }
    }
    void dropItem(Item item) {
        System.out.printf("\nDo you want to drop the %s? Yes (y) or No(n)\n",item.shortName);
        switch (in.nextLine()) {
            case "y" -> {
                removeItemPlayer(item);
                System.out.println("You drop the "+item.shortName);
                displayPlayerInventory();
            }
            case "n" -> System.out.println("You keep the "+item.shortName);
        }
    }

    void displayPlayerInventory() {
        System.out.println();
        System.out.println("____________________INVENTORY_____________________");
        for (Item playerItem : playerItems) {
            System.out.println(playerItem.shortName);
        }
        System.out.println("__________________________________________________");
    }


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
