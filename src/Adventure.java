import java.util.Locale;
import java.util.Scanner;

public class Adventure {
  Scanner in = new Scanner(System.in);
  //Room room1,room2,room3,room4,room5,room6,room7,room8,room9;
  void listOfCommands() {
    System.out.println("List of commands: ");
    System.out.println("\t\"help\" Prints list of commands.");
    System.out.println("\t\"look\" Take a look at current location.");
    System.out.println("\t\"exit\" Exits the game.");
    System.out.println("\t\"go north/south/east/west\" OR n/s/e/w, to move in a direction.");
  }

  /*
  void roomsNameSet() {
      room1.setName("Forest Clearing");
    }
*/

  void runProgram() {
    // attributter
    boolean game = true;
    String userInput;
    //

    // TODO Make short descriptions
    //Room 1-9 Instancing
    Room room1 = new Room();
    room1.setName("Forest clearing");
    room1.setRoomDescription("a quiet, serene clearing. The mossy undergrowth looks like soft green wool");
    room1.setRoomDescriptionShort();
    Room room2 = new Room();
    room2.setName("Short roadway");
    room2.setRoomDescription("a short roadway between two forests");
    Room room3 = new Room();
    room3.setName("Little Abandoned Town");
    room3.setRoomDescription("a derelict town of little use to anyone. All you can see is rubble and what someone once called home");
    Room room4 = new Room();
    room4.setName("Forest river");
    room4.setRoomDescription("a surprisingly bright river. You can see fish swimming in the clear water");
    Room room5 = new Room();
    room5.setName("Hermit's cave");
    room5.setRoomDescription("a small cave filled mushrooms, and filled with various trinkets made of animal bones. A hermit greets you");
    Room room6 = new Room();
    room6.setName("Haunted forest");
    room6.setRoomDescription("this part of the forests is dark, moist and filled with all sorts of thorny plant. You hear strange noises all around you.");
    Room room7 = new Room();
    room7.setName("Ruined Hilltop Castle");
    room7.setRoomDescription("a ruined castle overlooking the river below. It doesn't seem to have been inhabited for a long time");
    Room room8 = new Room();
    room8.setName("Damp swamp");
    room8.setRoomDescription("a mysterious swamp. There's a slim, old walkway through the bog.");
    Room room9 = new Room();
    room9.setName("Cabin in the Woods");
    room9.setRoomDescription("a strange little cabin inhabited by three wyrd sisters");

    // Sets starting room
    Room currentRoom = room1;
    room1.setVisitedTrue();

    // Setters for room refferences / connectivity
    room1.setRooms(null, room2, room4, null);
    room2.setRooms(null, room3, null, room1);
    room3.setRooms(null, null, room6, room2);
    room4.setRooms(room1, null, room7, null );
    room5.setRooms(null, null, room8, null);
    room6.setRooms(room3, null, room9, null);
    room7.setRooms(room4, room8, null, null);
    room8.setRooms(room5, room9, null, room7);
    room9.setRooms(room6, null, null, room8);

    // Program Start
    System.out.println("Welcome to this little text-based adventure");
    System.out.println("Enter \"help\" to get a list of commands");
    System.out.println();
    System.out.println("You find yourself seemingly lost walking through a forest.\nYou're not sure how it happened, but you got no choice but to keep moving.");
    System.out.println("(Move around with \"go \" plus \"north/south/east/west\" OR simply n/s/e/w)");
    System.out.println("Now...");
    // Program loop
    do {
      System.out.println("What will you do?: ");
      userInput = in.nextLine();

      switch (userInput) {
        case "go north", "n" -> {
          if (currentRoom.getRoomNorth() != null) {
            currentRoom = currentRoom.getRoomNorth();
            if (currentRoom.checkIfVisited() == false) {
              currentRoom.setVisitedTrue();
              System.out.printf("You went north, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescription());
            }
            else
              System.out.printf("You went north, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
          }
          else
            System.out.println("You can't go that way.");
        }
        case "go east", "e" -> {
          if (currentRoom.getRoomEast() != null) {
            currentRoom = currentRoom.getRoomEast();
            System.out.printf("You went east, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescription());
          }
          else
            System.out.println("You can't go that way.");
        }
        case "go south", "s" -> {
          if (currentRoom.getRoomSouth() != null) {
            currentRoom = currentRoom.getRoomSouth();
            System.out.printf("You went south, reaching a %s. You see %s ", currentRoom.getName().toLowerCase(Locale.ROOT), currentRoom.getRoomDescription());
          }
          else
            System.out.println("You can't go that way.");
        }
        case "go west", "w" -> {
          if (currentRoom.getRoomWest() != null) {
            currentRoom = currentRoom.getRoomWest();
            System.out.printf("You went west, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescription());
          }
          else
            System.out.println("You can't go that way.");
        }
        case "help" -> listOfCommands();
        case "exit" -> {
          System.out.println("Thanks for playing. Goodbye!");
          game = false;
        }
        case "look"-> System.out.println("You're in a " + currentRoom.getName() + ". Looking around, you see " + currentRoom.getRoomDescription());
        default -> System.out.println("Invalid command. Type \"help\" for a list of commands.");
        }
      System.out.println();

    } while (game == true);

  }

  public static void main(String[] args) {
    new Adventure().runProgram();

  }

}
