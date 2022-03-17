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

  void goNorthMessage(Room room) {
    System.out.printf("You went north, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
  }

  void goNorthVisitedMessage(Room room) {
    System.out.printf("You went north, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
  }

  void goEastMessage(Room room) {
    System.out.printf("You went east, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
  }

  void goEastVisitedMessage(Room room) {
    System.out.printf("You went east, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
  }

  void goSouthMessage(Room room) {
    System.out.printf("You went south, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
  }

  void goSouthVisitedMessage(Room room) {
    System.out.printf("You went south, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
  }

  void goWestMessage(Room room) {
    System.out.printf("You went west, reaching a %s. You see %s ", room.getName(), room.getRoomDescription());
  }

  void goWestVisitedMessage(Room room) {
    System.out.printf("You went west, reaching a %s. You see %s ", room.getName(), room.getRoomDescriptionShort());
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

  void invalidCommand() {
    System.out.println("Invalid command. Type \"help\" for a list of commands.");
  }

  //Locked Door
  void doorLockedMessage(String direction) {
    System.out.println("A log is blocking the way " + direction); //a locked door is blocking the way
  }

  void askForPromptDoorLocked() {
    System.out.println("Do you want to push the it aside (p) or look around (l)"); //Do you want to unlock the door (u) or look around (l)
  }

  void doorUnlockedMessage() {
    System.out.println("You push the log aside"); // You unlock the door
  }

  void walkAwayFromDoorLocked(Room currentRoom) {
    System.out.printf("You are still at a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
  }

  void defaultMessageDoorLocked() {
    System.out.println("The log is still blocking the way."); //The door is still locked.
  }

  //Darkness
  //TorchOn
  void darknessMessage() {
    System.out.println("Suddenly you feel like the darkness is closing in on you!");
  }

  void darknessPrompt() {
    System.out.println("Light a torch (t) or return whence you came (r)"); // "Light a torch" used instead of "turn light on".
  }

  void torchLit() {
    System.out.println("You light your torch and the darkness dissipates.");
  }

  void walkAwayFromDarkness(Room currentRoom) {
    System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
  }

  void defaultMessageDarkness() {
    System.out.println("The darkness engulfs you! You have to make a choice!");
  }

  //TorchOff
  void darknessGoneMessage() {
    System.out.println("You feel the light shining through the branches, do you really need a torch in hand?");
  }

  void darknessGonePrompt() {
    System.out.println("Put your torch away (t) or continue with it lit (c)"); // "Put your torch away" used instead of "turn light off".
  }

  void torchPutOut() {
    System.out.println("As soon as you put your torch out the darkness returns!");
  }

  void fleeDarkness(Room currentRoom) {
    System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
  }

  void walkOnFromDarknessGone() {
    System.out.println("You continue onward with a torch in your hand.");
  }

  void defaultMessageDarknessGone() {
    System.out.println("You pause for a second as a ray of sunlight gently hits your face. What is your choice.");
  }
}
