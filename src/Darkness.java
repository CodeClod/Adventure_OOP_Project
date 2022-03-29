import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Darkness {
  Scanner in;
  UserInterface ui;

  Darkness(UserInterface ui, Scanner in) {
    this.ui = ui;
    this.in = in;
  }

  public Room lightsOn(Room currentRoom, Room lastRoom, Weapon equipped) {
    boolean loop;
    Room tempRoom = currentRoom;
    ui.messageDarkness1();
    do {
      loop = true;
      ui.messageDarkness2();
      ui.askForPrompt();
      switch (in.nextLine()) {
        case "t" -> {
          if (equipped != null && Objects.equals(equipped.getShortname(), "Torch")) {
            currentRoom.setLightsOn();
            ui.messageDarkness3();
            loop = false;
          } else ui.messageDarknessDefault();
        }
        case "r" -> {
          loop = false;
          currentRoom = lastRoom;
          lastRoom = tempRoom;
          ui.messageDarkness4(currentRoom);
        }
        default -> ui.messageDarknessDefault();
      }
    } while (loop);
    return currentRoom;
  }

  // Torch off
  public Room lightsOff(Room currentRoom, Room lastRoom) {
    boolean loop;
    Room tempRoom = currentRoom;
    ui.messageDarknessGone1();
    do {
      loop = false;
      ui.messageDarknessGone2();
      ui.askForPrompt();
      switch (in.nextLine()) {
        case "t" -> {
          loop = true;
          currentRoom.setLightsOff();
          currentRoom = lastRoom;
          lastRoom = tempRoom;
          ui.messageDarknessGone3();
          ui.messageDarknessGone4(currentRoom);
        }
        case "c" -> {
          loop = true;
          ui.messageDarknessGone5();
        }
        default -> ui.messageDarknessGoneDefault();
      }
    } while (!loop);
    return currentRoom;
  }
}

