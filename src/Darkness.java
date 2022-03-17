import java.util.Scanner;

public class Darkness {
  Scanner in = new Scanner(System.in);
  UserInterface ui = new UserInterface();

  public Room darkness(Room currentRoom, Room lastRoom) {
    // Darkness! - room 6 - haunted forest
    boolean darkness;
    Room tempRoom = currentRoom;
    // Torch off
    if (currentRoom.checkIfDarkRoom() && currentRoom.checkIfLightsOn()) {
      System.out.println("You feel the light shining through the branches, do you really need a torch in hand?");
      do {
        darkness = false;
        System.out.println("Put your torch away (t) or continue with it lit (c)"); // "Put your torch away" used instead of "turn light off".
        ui.askForPrompt();
        switch (in.nextLine()) {
          case "t" -> {
            darkness = true;
            currentRoom.setLightsOff();
            currentRoom = lastRoom;
            lastRoom = tempRoom;
            System.out.println("As soon as you put your torch out the darkness returns!");
            System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
          }
          case "c" -> {
            darkness = true;
            System.out.println("You continue onward with a torch in your hand.");
          }
          default -> System.out.println("You pause for a second as a ray of sunlight gently hits your face. What is your choice.");
        }
      } while (!darkness);
    }

    // Torch on
    if (currentRoom.checkIfDarkRoom() && !currentRoom.checkIfLightsOn()) {
      System.out.println("Suddenly you feel like the darkness is closing in on you!");
      do {
        darkness = true;
        System.out.println("Light a torch (t) or return whence you came (r)"); // "Light a torch" used instead of "turn light on".
        ui.askForPrompt();
        switch (in.nextLine()) {
          case "t" -> {
            darkness = false;
            currentRoom.setLightsOn();
            System.out.println("You light your torch and the darkness dissipates.");
          }
          case "r" -> {
            darkness = false;
            currentRoom = lastRoom;
            lastRoom = tempRoom;
            System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
          }
          default -> System.out.println("The darkness engulfs you! You have to make a choice!");
        }
      } while (darkness);
    }
    return currentRoom;
  }
}
