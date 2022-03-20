import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  Room currentRoom;
  UserInterface ui = new UserInterface();
  LockedDoors ld = new LockedDoors();
  Scanner in = new Scanner(System.in);
  String input;
  ArrayList<Item> playerItems = new ArrayList<>();
  ArrayList<Item> toRemove = new ArrayList<>();

  void addItemPlayer(Item item) {
    playerItems.add(item);
    //currentRoom.roomItems.remove(item);
  }

  void removeItemPlayer(Item item) {
    //playerItems.remove(item);
    currentRoom.roomItems.add(item);
  }

  void findItems() { // TODO: move all displayed text to UserInterface
    toRemove.clear();
    System.out.println("Do you want to search for item? Yes (y) or No (n)");
    switch (in.nextLine()) {
      case "y" -> {
        System.out.println("You search the area for items.");
        if (currentRoom.roomItems.isEmpty())
          System.out.println("There are no items to be found here.");
        else {
          takeItem();
        }
      }
      case "n" -> System.out.println("You continue onwards");
      default -> findItems();
    }
  }

  void takeItem() { // TODO: move all displayed text to UserInterface
    currentRoom.displayRoomInventory();
    System.out.println("Do you want to pick up an item? Yes(y) or No (n) ");
    switch (in.nextLine()) {
      case "y" -> {
        System.out.println("Which item do you want to pick up? (item name)"); // responds to item name instead of "take item".
        String userInput = in.nextLine();
        for (Item item : currentRoom.roomItems
        ) {
          if (userInput.equalsIgnoreCase(item.shortName)) {
            toRemove.add(item);
            System.out.println("You pick up the " + item.shortName);
            addItemPlayer(item);
          } else {
            System.out.println("You found no such item!, try again");
          }
        }
        currentRoom.roomItems.removeAll(toRemove);
        if (!currentRoom.roomItems.isEmpty())
          takeItem();
        else System.out.println("There are no more items to be found. You continue onwards");
      }
      case "n" -> System.out.println("You continue onwards");
      default -> takeItem();
    }
  }

  void dropItems() { // TODO: move all displayed text to UserInterface.
    toRemove.clear();
    boolean noItem = false;
    System.out.println("Do you want to drop an item? Yes (y) or No (n)");
    switch (in.nextLine()) {
      case "y" -> {
        if (playerItems.size() == 0)
          System.out.println("Your inventory is empty. You continue onwards");
        else {
          System.out.println("Which item do you want to drop? (item name)"); // responds to item name instead of "drop item".
          String userInput = in.nextLine();
          for (Item item : playerItems
          ) {
            if (userInput.equalsIgnoreCase(item.shortName)) {
              toRemove.add(item);
              System.out.println("You drop the " + item.shortName);
              removeItemPlayer(item);
            } else {
              noItem = true;
            }
          }
          playerItems.removeAll(toRemove);
          if (!playerItems.isEmpty()) {
            if (noItem) {
              System.out.println("You have no such item!, try again");
            }
            displayPlayerInventory();
            dropItems();
          }
        }
      }
      case "n" -> System.out.println("You pack your stuff.");
      default -> dropItems();
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
          ld.doorLocked(currentRoom, ld.convertDirections(string));
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
          ld.doorLocked(currentRoom, ld.convertDirections(string));
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
          ld.doorLocked(currentRoom, ld.convertDirections(string));
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
          ld.doorLocked(currentRoom, ld.convertDirections(string));
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
