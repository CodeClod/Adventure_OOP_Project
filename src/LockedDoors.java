import java.util.Scanner;

public class LockedDoors {
  Scanner in = new Scanner(System.in);

  public void doorLocked(Room currentRoom, String userInput) {
    boolean door;
    String direction = null;
    // Door Locked
    switch (userInput) {
      case "go north", "n" -> direction = "north";
      case "go east", "e" -> direction = "east";
      case "go south", "s" -> direction = "south";
      case "go west", "w" -> direction = "west";
    }
    System.out.println("A log is blocking the way "+direction); //a locked door is blocking the way
    do {
      door = true;
      System.out.println("Do you want to push the it aside (p) or look around (l)"); //Do you want to unlock the door (u) or look around (l)
      new UserInterface().askForPrompt();
      switch (in.nextLine()) {
        case "p" -> { //u
          door = false;
          switch (userInput) {
            case "go north", "n" -> {
              currentRoom.setUnlockNorth();
              currentRoom.getRoomNorth().setUnlockSouth();
            }
            case "go east", "e" -> {
              currentRoom.setUnlockEast();
              currentRoom.getRoomEast().setUnlockWest();
            }
            case "go south", "s" -> {
              currentRoom.setUnlockSouth();
              currentRoom.getRoomSouth().setUnlockNorth();
            }
            case "go west", "w" -> {
              currentRoom.setUnlockWest();
              currentRoom.getRoomWest().setUnlockEast();
            }
          }
          System.out.println("You push the log aside"); // You unlock the door
        }
        case "l" -> {
          door = false;
          System.out.printf("You are still at a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
        }
        default -> System.out.println("The log is still blocking the way."); //The door is still locked.
      }
    } while (door);
  }

}