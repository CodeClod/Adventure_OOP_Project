import java.util.Scanner;

public class Darkness {
  Scanner in = new Scanner(System.in);
  UserInterface ui = new UserInterface();

  public Room LightsOn(Room currentRoom, Room lastRoom) {
    boolean darkness;
    Room tempRoom = currentRoom;
    ui.messageDarkness1();
    do {
      darkness = true;
      ui.messageDarkness2();
      ui.askForPrompt();
      switch (in.nextLine()) {
        case "t" -> {
          darkness = false;
          currentRoom.setLightsOn();
          ui.messageDarkness3();
        }
        case "r" -> {
          darkness = false;
          currentRoom = lastRoom;
          lastRoom = tempRoom;
          ui.messageDarkness4(currentRoom);
        }
        default -> ui.messageDarknessDefault();
      }
    } while (darkness);
    return currentRoom;
  }

  // Torch off
  public Room lightsOff(Room currentRoom, Room lastRoom) {
    boolean darkness;
    Room tempRoom = currentRoom;
    ui.messageDarknessGone1();
    do {
      darkness = false;
      ui.messageDarknessGone2();
      ui.askForPrompt();
      switch (in.nextLine()) {
        case "t" -> {
          darkness = true;
          currentRoom.setLightsOff();
          currentRoom = lastRoom;
          lastRoom = tempRoom;
          ui.messageDarknessGone3();
          ui.messageDarknessGone4(currentRoom);
        }
        case "c" -> {
          darkness = true;
          ui.messageDarknessGone5();
        }
        default -> ui.messageDarknessGoneDefault();
      }
    } while (!darkness);
    return currentRoom;
  }
}

