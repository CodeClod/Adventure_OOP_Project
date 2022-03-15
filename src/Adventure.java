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
    boolean darkness;
    String userInput;
    //

    //Room 1-9 Instancing
    Room room1 = new Room();
    room1.setName("Forest clearing");
    room1.setRoomDescription("a quiet, serene clearing. The mossy undergrowth looks like soft green wool.");
    room1.setRoomDescriptionShort("a quiet, serene clearing.");

    Room room2 = new Room();
    room2.setName("Short roadway");
    room2.setRoomDescription("a short roadway between two forests. The cobbles lie uneven and are overgrown with grass.");
    room2.setRoomDescriptionShort("a short roadway between two forests.");

    Room room3 = new Room();
    room3.setName("Little Abandoned Town");
    room3.setRoomDescription("a derelict town of little use to anyone. All you can see is rubble and what someone once called home.");
    room3.setRoomDescriptionShort("a derelict town of little use to anyone.");

    Room room4 = new Room();
    room4.setName("Forest river");
    room4.setRoomDescription("a surprisingly bright river. You can see fish swimming in the clear water.");
    room4.setRoomDescriptionShort("a surprisingly bright river.");

    Room room5 = new Room();
    room5.setName("Hermit's cave");
    room5.setRoomDescription("a small cave inhabited by a hermit. You glare inside and see various trinkets made of animal bones");
    room5.setRoomDescriptionShort("a small cave inhabited by a hermit.");

    Room room6 = new Room();
    room6.setName("Haunted forest");
    room6.setRoomDescription("a dark forest, filled with all sorts of thorny plant. You hear strange noises all around you.");
    room6.setRoomDescriptionShort("a dark forest, filled with all sorts of thorny plant.");

    Room room7 = new Room();
    room7.setName("Ruined Hilltop Castle");
    room7.setRoomDescription("a ruined castle overlooking the river below. It doesn't seem to have been inhabited for a long time");
    room7.setRoomDescriptionShort("a ruined castle overlooking the river below.");

    Room room8 = new Room();
    room8.setName("Damp swamp");
    room8.setRoomDescription("a mysterious swamp. There's a slim, old walkway through the bog.");
    room8.setRoomDescriptionShort("a mysterious swamp.");

    Room room9 = new Room();
    room9.setName("Cabin in the Woods");
    room9.setRoomDescription("a strange little cabin. The cabin is inhabited by three sisters, possibly witches");
    room9.setRoomDescriptionShort("strange little cabin.");

    // Sets starting room
    Room currentRoom = room1;

    room1.setVisitedTrue();

    // Setters for room refferences / connectivity
    room1.setRooms(null, room2, room4, null);
    room2.setRooms(null, room3, null, room1);
    room3.setRooms(null, null, room6, room2);
    room4.setRooms(room1, null, room7, null);
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

    Room xyzzy = room1;
    Room teleportroom;
    Room lastRoom = null;
    // Program loop
    do {


      // Darkness! - room 6 - haunted forest
      if (currentRoom == room6 && !room6.checkIfLightsOn()) {
        System.out.println("Suddenly you feel like the darkness is closing in on you!");
        do {
          darkness = true;
          System.out.println("Light a torch (t) or return whence you came (r)");
          System.out.println("What will you do?: ");
          switch (in.nextLine()) {
            case "t" -> {
              darkness = false;
              room6.setLightsOn();
              System.out.println("You light your torch and the darkness dissipates.");
            }
            case "r" -> {
              darkness = false;
              currentRoom = lastRoom;
              lastRoom = room6;
              System.out.printf("You return whence you came, reaching a %s. You see %s\n", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
            }
            default -> {
              System.out.println("The darkness engulfs you! You have to make a choice!");
            }
          }
        } while (darkness);
      }




      System.out.println("What will you do?: ");
      userInput = in.nextLine();

      switch (userInput) {
        case "go north", "n" -> {
          lastRoom = currentRoom;
          if (currentRoom.getRoomNorth() != null) {
            currentRoom = currentRoom.getRoomNorth();
            if (currentRoom.checkIfVisited() == false) {
              currentRoom.setVisitedTrue();
              System.out.printf("You went north, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescription());
            } else
              System.out.printf("You went north, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
          } else
            System.out.println("You can't go that way.");
        }
        case "go east", "e" -> {
          lastRoom = currentRoom;
          if (currentRoom.getRoomEast() != null) {
            currentRoom = currentRoom.getRoomEast();
            if (currentRoom.checkIfVisited() == false) {
              currentRoom.setVisitedTrue();
              System.out.printf("You went east, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescription());
            } else
              System.out.printf("You went east, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
          } else
            System.out.println("You can't go that way.");
        }
        case "go south", "s" -> {
          lastRoom = currentRoom;
          if (currentRoom.getRoomSouth() != null) {
            currentRoom = currentRoom.getRoomSouth();
            if (currentRoom.checkIfVisited() == false) {
              currentRoom.setVisitedTrue();
              System.out.printf("You went south, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescription());
            } else
              System.out.printf("You went south, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
          } else
            System.out.println("You can't go that way.");
        }
        case "go west", "w" -> {
          lastRoom = currentRoom;
          if (currentRoom.getRoomWest() != null) {
            currentRoom = currentRoom.getRoomWest();
            if (currentRoom.checkIfVisited() == false) {
              currentRoom.setVisitedTrue();
              System.out.printf("You went west, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescription());
            } else
              System.out.printf("You went west, reaching a %s. You see %s ", currentRoom.getName(), currentRoom.getRoomDescriptionShort());
          } else
            System.out.println("You can't go that way.");
        }
        case "help" -> listOfCommands();
        case "exit" -> {
          System.out.println("Thanks for playing. Goodbye!");
          game = false;
        }
        case "look" -> System.out.println("You're in a " + currentRoom.getName() + ". Looking around, you see " + currentRoom.getRoomDescription());
        case "xyzzy" -> {
          teleportroom = currentRoom;
          currentRoom = xyzzy;
          System.out.println("You teleported to " + currentRoom.getName());
          xyzzy = teleportroom;
        }
        default -> System.out.println("Invalid command. Type \"help\" for a list of commands.");
      }
      System.out.println();

    } while (game == true);

  }

  public static void main(String[] args) {
    new Adventure().runProgram();

  }

}
