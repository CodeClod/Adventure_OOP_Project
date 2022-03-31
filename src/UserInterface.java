public class UserInterface {

  void listOfCommands() {
    System.out.println("List of commands: ");
    System.out.println("\t\"help\" Prints list of commands.");
    System.out.println("\t\"look\" Take a look at current location.");
    System.out.println("\t\"exit\" Exits the game.");
    System.out.println("\t\"go north/south/east/west\" OR n/s/e/w, to move in a direction.");
  }

  void programStartupWelcome() {
    System.out.println("""
        Welcome to this little text-based adventure
        "Enter \"help\" to get a list of commands"
                
        You find yourself seemingly lost walking through a forest.
        You're not sure how it happened, but you got no choice but to keep moving.
        (Move around with \"go \" plus \"north/south/east/west\" OR simply n/s/e/w)
                
        Now...""");
  }

  void goMessage(Room room, Compass direction) {
    System.out.printf("You went %s, reaching a %s. You see %s \n",direction, room.getName(), room.getRoomDescription());
  }

  void goVisitedMessage(Room room, Compass direction) {
    System.out.printf("You went %s, reaching a %s. You see %s ",direction, room.getName(), room.getRoomDescriptionShort());
  }

  void lookAround(Room room) {
    System.out.println("You're in a " + room.getName() + ". Looking around, you see " + room.getRoomDescription());
  }

  void askForPrompt() {
    System.out.println("What will you do?: ");
  }

  void invalidDirection() {
    System.out.println("Can't go that way.");
  }

  void goodbyeMessage() {
    System.out.println("Thanks for playing. Goodbye!");
  }

  void invalidCommand() {
    System.out.println("Invalid command. Type \"help\" for a list of commands.");
  }

  //Locked Door
  void messageDoorLocked1(Compass direction) {
    System.out.println("A log is blocking the way " + direction.toString()); //a locked door is blocking the way
  }

  void messageDoorLocked2() {
    System.out.println("Do you want to chop your way through (c) or look around (l)"); //Do you want to unlock the door (u) or look around (l)
  }

  void messageDoorLocked3() {
    System.out.println("You chop your way through the log!"); // You unlock the door
  }

  void messageDoorLocked4(Room currentRoom) {
    System.out.printf("You are still at a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
  }

  void messageDoorLockedDefault() {
    System.out.println("The log is still blocking the way. You'll need to equip an axe to chop your way through!"); //The door is still locked.
  }

  //Darkness
  //Lights On
  void messageDarkness1() {
    System.out.println("Suddenly you feel like the darkness is closing in on you!");
  }

  void messageDarkness2() {
    System.out.println("Light a torch (t) or return whence you came (r)"); // "Light a torch" used instead of "turn light on".
  }

  void messageDarkness3() {
    System.out.println("You light your torch and the darkness dissipates.");
  }

  void messageDarkness4(Room currentRoom) {
    System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
  }

  void messageDarknessDefault() {
    System.out.println("The darkness engulfs you! You have to make a choice!");
  }

  //Lights Off
  void messageDarknessGone1() {
    System.out.println("You feel the light shining through the branches, do you really need a torch in hand?");
  }

  void messageDarknessGone2() {
    System.out.println("Put your torch away (t) or continue with it lit (c)"); // "Put your torch away" used instead of "turn light off".
  }

  void messageDarknessGone3() {
    System.out.println("As soon as you put your torch out the darkness returns!");
  }

  void messageDarknessGone4(Room currentRoom) {
    System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
  }

  void messageDarknessGone5() {
    System.out.println("You continue onward with a torch in your hand.");
  }

  void messageDarknessGoneDefault() {
    System.out.println("You pause for a second as a ray of sunlight gently hits your face. What is your choice.");
  }
}
