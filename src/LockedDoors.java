import java.util.Scanner;

public class LockedDoors {
  Scanner in = new Scanner(System.in);
  UserInterface ui = new UserInterface();

  public String directionsParse(String userInput) {
    String direction = null;
    // Door Locked
    switch (userInput) {
      case "go north", "n" -> direction = "n";
      case "go east", "e" -> direction = "e";
      case "go south", "s" -> direction = "s";
      case "go west", "w" -> direction = "w";
    }
    return direction;
  }

  public void doorLocked(Room currentRoom, String direction) {
    boolean door;
    ui.doorLockedMessage(direction);
    do {
      door = true;
      ui.askForPromptDoorLocked();
      ui.askForPrompt();
      switch (in.nextLine()) {
        case "p" -> { //u
          door = false;
          switch (direction) {
            case "n" -> {
              currentRoom.setUnlockNorth();
              currentRoom.getRoomNorth().setUnlockSouth();
            }
            case "e" -> {
              currentRoom.setUnlockEast();
              currentRoom.getRoomEast().setUnlockWest();
            }
            case "s" -> {
              currentRoom.setUnlockSouth();
              currentRoom.getRoomSouth().setUnlockNorth();
            }
            case "w" -> {
              currentRoom.setUnlockWest();
              currentRoom.getRoomWest().setUnlockEast();
            }
          }
          ui.doorUnlockedMessage();
        }
        case "l" -> {
          door = false;
          ui.walkAwayFromDoorLocked(currentRoom);
        }
        default -> ui.defaultMessageDoorLocked();
      }
    } while (door);
  }

}