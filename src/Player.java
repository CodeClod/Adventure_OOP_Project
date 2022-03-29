import java.util.*;

public class Player {
  Scanner in;
  UserInterface ui;
  private Room currentRoom;
  private Room lastRoom;
  private Room xyzzy;
  LockedDoors ld;
  ArrayList<Item> items = new ArrayList<>();
  int purseGold = 0;
  int purse = 0;
  private int health = 100;


  void showHealth() {
    if (health < 100){
      System.out.println("You're at " + health + " health. You're quite healthy.");
    }
    else if (health <= 80) {
      System.out.println("You're at " + health + " health. You got some scapes, but you're alright.");
    }
    else if (health <= 60){
      System.out.println("You're at " + health + " health. You're slightly hurt, maybe you should eat a fruit?");
    }
    else if (health <= 40){
      System.out.println("You're at " + health + " health. You're quite hurt, be careful...");
    }
    else if (health <= 20){
      System.out.println("You're at " + health + " health. You're seriously hurt, you need immediate healing!");
    }
  }

  void loseHealth(int health) {
    this.health -= health;
  }
  void getHealth(int health) {
    this.health += health;
  }

  // checks both rooms for name and eat's it if it's able
  void checkBothRooms(ArrayList arrayList, String itemName) {
    Iterator itr = arrayList.iterator();
    while (itr.hasNext()) {
      Item itemInList = (Item)itr.next();
      if (itemInList.getShortname().equalsIgnoreCase(itemName)){
        if (itemInList instanceof Food) {
          System.out.println("You ate the " + itemInList.getShortname().toLowerCase(Locale.ROOT));
          System.out.println("...");
          if (((Food) itemInList).getHealing() > 0) {
            System.out.println("You got " + ((Food) itemInList).getHealing() + " health");
            health += ((Food) itemInList).getHealing();
          }
          else if (((Food) itemInList).getHealing() < 0) {
            System.out.println("You lost " + ((Food) itemInList).getHealing() + " health");
            health -= ((Food) itemInList).getHealing();
          }
          itr.remove();
        }
        else
          System.out.println("You can't eat " + itemInList.getShortname());
      }
    }
  }



  // Hvis man finder et item i items så adder man 1 for at komme videre (i slutningen af arrayet)
  void eat(String item) {
    checkBothRooms(items, item);
    checkBothRooms(currentRoom.items, item);
  }


/*
  void eat(String item) {
    ArrayList<Item> tempList = items;
    for (int i = 0; i < tempList.size(); i++) {
      if (tempList.get(i).getShortname().equalsIgnoreCase(item)){
        if (tempList.get(i) instanceof Food) {
          System.out.println("You ate the " + tempList.get(i).getShortname().toLowerCase(Locale.ROOT));
          System.out.println("...");
          System.out.println("You got " + tempList + " ");
          if (((Food) tempList.get(i)).getHealing() > 0) {
            System.out.println("You got " + ((Food) tempList.get(i)).getHealing() + " health");
            health += ((Food) tempList.get(i)).getHealing();
          }
          else if (((Food) items.get(i)).getHealing() < 0) {
            System.out.println("You lost " + ((Food) tempList.get(i)).getHealing() + " health");
            health -= ((Food) tempList.get(i)).getHealing();
          }
          items.remove(i);
        }
      }
      else
        System.out.println("There's no such item");
    }
  }

 */

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
          takeAction();
        }
      }
      case "n" -> System.out.println("You continue onwards");
      default -> findItems();
    }
  }

  void takeAction() { // TODO: move all displayed text to UserInterface

    currentRoom.displayRoomInventory();
    System.out.println("Take an action (take+item,eat+item or leave) ");
    String userInput = in.nextLine().toLowerCase(Locale.ROOT);

    if (userInput.contains("take")) {
      userInput = userInput.substring(5);
      System.out.println(userInput);
      takeItem(userInput);
    }
    else if(userInput.contains("eat")) {
      userInput = userInput.substring(4);
      eat(userInput);
      //eatItem (userInput);
      System.out.println(); // TODO: placeholder.
    }
    else if(userInput.contains("leave")){
      System.out.println("You continue onwards");
    }
    else
      takeAction();
  }

  void takeItem(String string) {
    ArrayList<Item> toRemove = new ArrayList<>();
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
        System.out.println("You found no such item!, try again"); //todo: check hvorvidt der udskrives flere gange
      }
    }
    currentRoom.removeItems(toRemove);
    if (!currentRoom.items.isEmpty())
      takeAction();
    else System.out.println("There are no more items to be found. You continue onwards");
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
      System.out.printf("%-44s %s\n", item.getShortname(), item.getValue());
    }
    System.out.println("                                          AMOUNT: ");
    System.out.printf("%-44s %d \n", "Gold", purseGold);
    System.out.println("__________________________________________________");
  }

  Player(Room StartingRoom, UserInterface ui, Scanner in) {
    this.ui = ui;
    this.in = in;
    this.ld = new LockedDoors(ui, in);
    currentRoom = StartingRoom;
    xyzzy = StartingRoom;
    Gold gold = new Gold("Gold Coins", "A small stash of shiny golden coins", 20);
    addToPurse(gold);
    Food chicken = new Food("Grilled Chicken", "A delicious looking grilled chicken", 5, 20);
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
