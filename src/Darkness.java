import java.util.Scanner;

public class Darkness {
  Scanner in = new Scanner(System.in);
  UserInterface ui = new UserInterface();

  // Torch on
  public Room torchOn(Room currentRoom, Room lastRoom) {
    boolean darkness;
    Room tempRoom = currentRoom;
    ui.darknessMessage();
    do {
      darkness = true;
      ui.darknessPrompt();
      ui.askForPrompt();
      switch (in.nextLine()) {
        case "t" -> {
          darkness = false;
          currentRoom.setLightsOn();
          ui.torchLit();
        }
        case "r" -> {
          darkness = false;
          currentRoom = lastRoom;
          lastRoom = tempRoom;
          ui.walkAwayFromDarkness(currentRoom);
        }
        default -> ui.defaultMessageDarkness();
      }
    } while (darkness);
    return currentRoom;
  }

  // Torch off
  public Room torchOff(Room currentRoom, Room lastRoom) {
    boolean darkness;
    Room tempRoom = currentRoom;
    ui.darknessGoneMessage();
    do {
      darkness = false;
      ui.darknessGonePrompt();
      ui.askForPrompt();
      switch (in.nextLine()) {
        case "t" -> {
          darkness = true;
          currentRoom.setLightsOff();
          currentRoom = lastRoom;
          lastRoom = tempRoom;
          ui.torchPutOut();
          ui.fleeDarkness(currentRoom);
        }
        case "c" -> {
          darkness = true;
          ui.walkOnFromDarknessGone();
        }
        default -> ui.defaultMessageDarknessGone();
      }
    } while (!darkness);
    return currentRoom;
  }
}

