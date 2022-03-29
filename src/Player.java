import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Player {
  Scanner in;
  UserInterface ui;
  private Room currentRoom;
  private Room lastRoom;
  private Room xyzzy;
  private int health;
  LockedDoors ld;
  ArrayList<Item> items = new ArrayList<>();
  int purseGold = 0;

  Player(Room StartingRoom, int health, UserInterface ui, Scanner in) {
    this.ui = ui;
    this.in = in;
    this.ld = new LockedDoors(ui, in);
    this.health = health;
    currentRoom = StartingRoom;
    xyzzy = StartingRoom;
    Gold gold = new Gold("Gold Coins", "A small stash of shiny golden coins", 20);
    addToPurse(gold);
    Food steak = new Food("Steak", "A delicious looking steak", 10, 40);
    addItem(steak);
    Food chicken = new Food("Grilled Chicken", "A delicious looking grilled chicken", 5, 20);
    addItem(chicken);
  }

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

  void findItems() {
    System.out.println("Do you want to search for item? Yes (y) or No (n)");
    switch (in.nextLine()) {
      case "y" -> {
        System.out.println("You search the area for items.");
        currentRoom.setSearched();
        if (currentRoom.items.isEmpty())
          System.out.println("There are no items to be found here.");
        else {
          takeAction();
        }
      }
      case "n" -> System.out.println("You continue onwards");
      default -> findItems();
    }
  }

  void takeAction() {
    currentRoom.displayRoomInventory();
    displayPlayerInventory();
    System.out.println("Take an action (take+item,drop+item,eat+item or leave) ");
    String userInput = in.nextLine().toLowerCase(Locale.ROOT);

    if (userInput.contains("take")) {
      userInput = userInput.substring(5);
      takeItem(userInput);
    }
    if (userInput.contains("drop")) {
      userInput = userInput.substring(5);
      dropItem(userInput);
    } else if (userInput.contains("eat")) {
      userInput = userInput.substring(4);
      eat(userInput);
    } else if (userInput.contains("leave")) {
      System.out.println("You continue onwards");
    } else
      takeAction();
  }

  void takeItem(String string) {
    ArrayList<Item> toRemove = new ArrayList<>();
    boolean noItem = false;
    for (Item item : currentRoom.items
    ) {
      if (string.equalsIgnoreCase(item.getShortname())) {
        toRemove.add(item);
        System.out.println("You pick up the " + item.getShortname());
        if (item.getClass().equals(Gold.class)) {
          addToPurse((Gold) item);
        } else
          addItem(item);
      } else {
        noItem = true;
      }
    }
    currentRoom.removeItems(toRemove);
    if (!currentRoom.items.isEmpty()) {
      if (noItem) {
        System.out.println("You found no such item!, try again");
      }
      takeAction();
    } else System.out.println("There are no more items to be found. You continue onwards");
  }

  void dropItem(String string) {
    ArrayList<Item> toRemove = new ArrayList<>();
    boolean noItem = false;
    if (items.size() == 0) {
      System.out.println("Your inventory is empty. You continue onwards");
      takeAction();
    } else {
      for (Item item : items
      ) {
        if (string.equalsIgnoreCase(item.getShortname())) {
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
          System.out.println("You have no such item!, try again");
        }
        takeAction();
      } else System.out.println("You pack your stuff.");
    }
  }

  void showHealth() {
    if (health == 100) {
      System.out.println("You're at " + health + " health. You're quite healthy.");
    } else if (health <= 80) {
      System.out.println("You're at " + health + " health. You got some scrapes, but you're alright.");
    } else if (health <= 60) {
      System.out.println("You're at " + health + " health. You're slightly hurt, maybe you should eat a fruit?");
    } else if (health <= 40) {
      System.out.println("You're at " + health + " health. You're quite hurt, be careful...");
    } else if (health <= 20) {
      System.out.println("You're at " + health + " health. You're seriously hurt, you need immediate healing!");
    }
  }

  void loseHealth(int health) {
    this.health -= health;
  }

  void getHealth(int health) {
    this.health += health;
  }

  void eat(String string) {
    ArrayList<Item> tempList = new ArrayList<>();
    boolean noItem = false;
    boolean notEatable = false;
    for (Item item : items
    ) {
      if (string.equalsIgnoreCase(item.getShortname()) && item.getClass().equals(Food.class)) {
        tempList.add(item);
        System.out.println("You ate the " + item.getShortname().toLowerCase(Locale.ROOT));
        System.out.println("...");

        if (((Food) item).getHealthPoints() > 0) {
          System.out.println("You got " + ((Food) item).getHealthPoints() + " health");
          health += ((Food) item).getHealthPoints();

        } else if (((Food) item).getHealthPoints() < 0) {
          System.out.println("You lost " + ((Food) item).getHealthPoints() + " health");
          health -= ((Food) item).getHealthPoints();

        }
      } else if (string.equalsIgnoreCase(item.getShortname()) && !item.getClass().equals(Food.class)) {
        System.out.println("You can't eat that "+item.getShortname());
        notEatable = true;
      }
      else {
        noItem = true;
      }
    }
    removeItems(tempList);
    if (!items.isEmpty()) {
      if (noItem && !notEatable) {
        System.out.println("There's no such item");
      }
      takeAction();
    } else System.out.println("There are no more eatable items. You continue onwards");
  }

  void displayPlayerInventory() {
    System.out.println();
    System.out.println("____________________INVENTORY_____________________");
    System.out.println("ITEM:                                      VALUE: ");
    for (Item item : items) {
      System.out.printf("%-44s %s\n", item.getShortname(), item.getValue());
    }
    System.out.println("__________________________________________________");
    System.out.printf("%-44s %d \n", "Purse (gold):", purseGold);
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
