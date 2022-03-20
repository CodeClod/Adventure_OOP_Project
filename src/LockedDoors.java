import java.util.Scanner;

public class LockedDoors {
  Scanner in = new Scanner(System.in);
  UserInterface ui = new UserInterface();

  public String convertDirections(String userInput) {
    String direction = null;
    switch (userInput) {
      case "go north", "n" -> direction = "north";
      case "go east", "e" -> direction = "east";
      case "go south", "s" -> direction = "south";
      case "go west", "w" -> direction = "west";
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
      String userInput = in.nextLine();
      switch (userInput) {
        case "p" -> { //u
          door = false;
          switch (direction) {
            case "north" -> {
              currentRoom.setUnlockNorth();
              currentRoom.getRoomNorth().setUnlockSouth();
            }
            case "east" -> {
              currentRoom.setUnlockEast();
              currentRoom.getRoomEast().setUnlockWest();
            }
            case "south" -> {
              currentRoom.setUnlockSouth();
              currentRoom.getRoomSouth().setUnlockNorth();
            }
            case "west" -> {
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