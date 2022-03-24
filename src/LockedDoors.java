import java.util.Scanner;

public class LockedDoors {
  Scanner in;
  UserInterface ui;

  LockedDoors(UserInterface ui, Scanner in) {
    this.ui = ui;
    this.in = in;
  }

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