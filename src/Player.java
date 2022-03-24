import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  private Room currentRoom;
  private Room lastRoom;
  private Room xyzzy;
  UserInterface ui = new UserInterface();
  LockedDoors ld = new LockedDoors();
  Darkness dark = new Darkness();
  Scanner in = new Scanner(System.in);
  ArrayList<Item> items = new ArrayList<>();
  ArrayList<Item> toRemove = new ArrayList<>();

  void addItem(Item item) {
    items.add(item);
    //currentRoom.roomItems.remove(item);
  }

  void removeItem(Item item) {
    //playerItems.remove(item);
    currentRoom.items.add(item);
  }

  void findItems() { // TODO: move all displayed text to UserInterface
    toRemove.clear();
    System.out.println("Do you want to search for item? Yes (y) or No (n)");
    switch (in.nextLine()) {
      case "y" -> {
        System.out.println("You search the area for items.");
        if (currentRoom.items.isEmpty())
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
        for (Item item : currentRoom.items
        ) {
          if (userInput.equalsIgnoreCase(item.shortName)) {
            toRemove.add(item);
            System.out.println("You pick up the " + item.shortName);
            addItem(item);
          } else {
            System.out.println("You found no such item!, try again");
          }
        }
        currentRoom.items.removeAll(toRemove);
        if (!currentRoom.items.isEmpty())
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
        if (items.size() == 0)
          System.out.println("Your inventory is empty. You continue onwards");
        else {
          System.out.println("Which item do you want to drop? (item name)"); // responds to item name instead of "drop item".
          String userInput = in.nextLine();
          for (Item item : items
          ) {
            if (userInput.equalsIgnoreCase(item.shortName)) {
              toRemove.add(item);
              System.out.println("You drop the " + item.shortName);
              removeItem(item);
            } else {
              noItem = true;
            }
          }
          items.removeAll(toRemove);
          if (!items.isEmpty()) {
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
    for (Item playerItem : items) {
      System.out.println(playerItem.shortName);
    }
    System.out.println("__________________________________________________");
  }

  Player(Room StartingRoom) {
    currentRoom = StartingRoom;
    xyzzy = StartingRoom;

  }

  String playerInput() {
    return in.nextLine();
  }

  void setCurrentRoom(Room room) {
    currentRoom = room;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  void setLastRoom (Room room) {
    lastRoom = room;
  }

  void playerMove(Compass direction) {
    setLastRoom(currentRoom);
        if (currentRoom.checkIfDoorIsLocked(direction))
          ld.doorLocked(currentRoom, direction);
        else if (currentRoom.getRoom(direction) != null) {
          currentRoom = currentRoom.getRoom(direction);
          if (!(currentRoom.checkIfVisited())) {
            currentRoom.setVisitedTrue();
            ui.goMessage(currentRoom, direction);
          } else
            ui.goVisitedMessage(currentRoom, direction);
        } else
          ui.invalidDirection();
      }

  void xyzzy() {
    Room teleportRoom = currentRoom;
    currentRoom = xyzzy;
    System.out.println("You teleported to " + currentRoom.getName());
    xyzzy = teleportRoom;
  }

  void checkForDarkness() {
    if (currentRoom.checkIfDarkness() && !currentRoom.checkIfLightsOn())
      setCurrentRoom(dark.LightsOn(currentRoom,lastRoom));
    else if (currentRoom.checkIfDarkness() && currentRoom.checkIfLightsOn())
      setCurrentRoom(dark.lightsOff(currentRoom, lastRoom));
  }
}
