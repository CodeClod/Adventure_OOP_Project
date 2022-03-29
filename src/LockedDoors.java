import java.util.Objects;
import java.util.Scanner;

public class LockedDoors {
  Scanner in;
  UserInterface ui;

  LockedDoors(UserInterface ui, Scanner in) {
    this.ui = ui;
    this.in = in;
  }

  public void doorLocked(Room currentRoom, Compass direction, Weapon equipped) {
    boolean door;
    ui.messageDoorLocked1(direction);
    do {
      door = true;
      ui.messageDoorLocked2(); // TODO: change to the message
      ui.askForPrompt();
      String userInput = in.nextLine();
      switch (userInput) {
        case "p" -> {
          if (equipped != null && Objects.equals(equipped.getShortname(), "Axe")) {
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
            // ui.messageDoorLocked3(); TODO: change to the message in the next line.
            System.out.println("You chop your way through the log!");
          }
          else {
            ui.messageDoorLockedDefault();
            System.out.println("You'll need an axe to chop your way through!");
          }
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