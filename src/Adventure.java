import java.util.Scanner;

public class Adventure {
  Scanner in = new Scanner(System.in);

  void listOfCommands() {
    System.out.println("List of commands: ");
    System.out.println("\t\"help\" Prints list of commands.");
    System.out.println("\t\"look\" Take a look at current location.");
    System.out.println("\t\"exit\" Exits the game.");
    System.out.println("\t\"");
  }


  // TODO Make methods for room instancing/creation and connectivity


  void runProgram() {

    //Room 1
    Room room1 = new Room("Forest clearing","A quiet, serene clearing. The mossy undergrowth looks like green wool");
    // room1 description

    //Room 2
    Room room2 = new Room("Short roadway", "A short roadway between two forests");
    // room1 description

    //Room 3
    Room room3 = new Room();
    // room1 description

    //Room 4
    Room room4 = new Room();
    // room1 description

    //Room 5
    Room room5 = new Room();
    // room1 description

    //Room 6
    Room room6 = new Room();
    // room1 description

    //Room 7
    Room room7 = new Room();
    // room1 description

    //Room 8
    Room room8 = new Room();
    // room1 description

    //Room 9
    Room room9 = new Room();
    // room1 description

    // Starting room
    Room currentRoom = room1;

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
    System.out.println("Welcome to this little text-based adventure. ");
    System.out.println("Enter \"help\" to get a list of commands.");


    System.out.println("You find yourself seemingly lost walking through a forest.\nYou're not sure how it happened, but you got no choice but to keep moving.");
    System.out.println("(Move around with \"go \" plus \"north\", \"south\", \"east\" or \"west\")");



  }

  public static void main(String[] args) {


  }

  int playerPosition;

  void uiAdventure() {
    menuAdventure();
  }



  void menuAdventure() {
    //Switch
  }




}
