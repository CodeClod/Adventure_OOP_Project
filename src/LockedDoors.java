import java.util.Scanner;

public class LockedDoors {
  Scanner in = new Scanner(System.in);
  UserInterface ui = new UserInterface();

  public void doorLocked(Room currentRoom, Compass direction) {
    boolean door;
    ui.messageDoorLocked1(direction);
    do {
      door = true;
      ui.messageDoorLocked2();
      ui.askForPrompt();
      String userInput = in.nextLine();
      switch (userInput) {
        case "p" -> {
          door = false;
          switch (direction) {
            case NORTH -> {
              currentRoom.setUnlock(direction);
              currentRoom.getRoom(direction).setUnlock(Compass.SOUTH);
            }
            case EAST -> {
              currentRoom.setUnlock(direction);
              currentRoom.getRoom(direction).setUnlock(Compass.WEST);
            }
            case SOUTH -> {
              currentRoom.setUnlock(direction);
              currentRoom.getRoom(direction).setUnlock(Compass.NORTH);
            }
            case WEST -> {
              currentRoom.setUnlock(direction);
              currentRoom.getRoom(direction).setUnlock(Compass.EAST);
            }
          }
          ui.messageDoorLocked3();
        }
        case "l" -> {
          door = false;
          ui.messageDoorLocked4(currentRoom);
        }
        default -> ui.messageDoorLockedDefault();
      }
    } while (door);
  }

}