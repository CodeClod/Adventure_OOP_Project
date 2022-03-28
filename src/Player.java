import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  Scanner in;
  UserInterface ui;
  private Room currentRoom;
  private Room lastRoom;
  private Room xyzzy;
  LockedDoors ld;
  ArrayList<Item> items = new ArrayList<>();
  int purseGold = 0;

  //gold
  void addToPurse(Gold gold) {
    purseGold += gold.getValue();
  }

  boolean removeFromPurse(int gold) { //TODO: skal muligvis rettes til.
    if (gold > purseGold) {
      return false;
    } else
      purseGold -= gold;
    return true;
  }

  //items
  void addItem(Item item) {
    items.add(item);
  }

  void removeItems(ArrayList<Item> list) {
    items.removeAll(list);
  }

  void findItems() { // TODO: move all displayed text to UserInterface
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
    ArrayList<Item> toRemove = new ArrayList<>();
    currentRoom.displayRoomInventory();
    System.out.println("Do you want to pick up an item? Yes(y) or No (n) ");
    switch (in.nextLine()) {
      case "y" -> {
        System.out.println("Which item do you want to pick up? (item name)"); // responds to item name instead of "take item".
        String userInput = in.nextLine();
        for (Item item : currentRoom.items
        ) {
          if (userInput.equalsIgnoreCase(item.getShortname())) {
            toRemove.add(item);
            System.out.println("You pick up the " + item.getShortname());
            if (item.getClass().equals(Gold.class)) {
              addToPurse((Gold)item);
            }
            else
              addItem(item);
          } else {
            System.out.println("You found no such item!, try again"); //todo: check hvorvidt der udskrives flere gange
          }
        }
        currentRoom.removeItems(toRemove);
        if (!currentRoom.items.isEmpty())
          takeItem();
        else System.out.println("There are no more items to be found. You continue onwards");
      }
      case "n" -> System.out.println("You continue onwards");
      default -> takeItem();
    }
  }

  void dropItems() { // TODO: move all displayed text to UserInterface.
    ArrayList<Item> toRemove = new ArrayList<>();
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
            if (userInput.equalsIgnoreCase(item.getShortname())) {
              toRemove.add(item);
              System.out.println("You drop the " + item.getShortname());
              currentRoom.addItem(item);
            } else {
              noItem = true;
            }
          }
          removeItems(toRemove);
          if (!items.isEmpty()) {
            if (noItem) {
              System.out.println("You have no such item!, try again"); //todo: check hvorvidt der udskrives flere gange
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
    System.out.println("ITEM:                                      VALUE: ");
    for (Item item : items) {
      System.out.printf("%-44s %s\n",item.getShortname(),item.getValue());
    }
    System.out.println("                                          AMOUNT: ");
    System.out.printf("%-44s %d \n","Gold", purseGold);
    System.out.println("__________________________________________________");
  }

  Player(Room StartingRoom, UserInterface ui, Scanner in) {
    this.ui = ui;
    this.in = in;
    this.ld = new LockedDoors(ui, in);
    currentRoom = StartingRoom;
    xyzzy = StartingRoom;
    Gold gold = new Gold ("Gold Coins", "A small stash of shiny golden coins",20);
    addToPurse(gold);
    Food chicken = new Food("Grilled Chicken","A delicious looking grilled chicken", 5,20);
    addItem(chicken);
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

  void setLastRoom(Room room) {
    lastRoom = room;
  }

  public Room getLastRoom() {
    return lastRoom;
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
}
