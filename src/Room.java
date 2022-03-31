import java.util.ArrayList;
import java.util.List;

public class Room {
  private String name;
  private String roomDescription;
  private String roomDescriptionShort;
  private Room north;
  private Room east;
  private Room south;
  private Room west;
  private NPC npc;
  private boolean visited = false;
  private boolean light = false;
  private boolean darkness = false;
  private boolean searched = false;
  private boolean containsNPC = false;
  ArrayList<Item> items = new ArrayList<>();
  List<Compass> lockedDoors = new ArrayList<>();
  List<Compass> unlockedDoors = new ArrayList<>();

  public Room(NPC npc) {
    this.npc = npc;
  }

  public Room() {
  }

  //Checks
  boolean checkIfVisited() {
    return visited;
  }

  boolean checkIfLightsOn() {
    return light;
  }

  boolean checkIfDarkness() {
    return darkness;
  }

  boolean checkIfDoorIsLocked(Compass direction) {
    return lockedDoors.contains(direction);
  }

  boolean checkIfContainsNPC () {
    return containsNPC;
  }

  // Setters
  void setRooms(Room north, Room east, Room south, Room west) {
    this.north = north;
    this.east = east;
    this.west = west;
    this.south = south;
  }

  void setVisitedTrue() {
    visited = true;
  }

  void setLightsOn() {
    light = true;
  }

  void setLightsOff() {
    light = false;
  }

  void setDarkRoom() {
    darkness = true;
  }

  void setSearched() {
    searched = true;
  }

  void setContainsNPC() {
    containsNPC = true;
  }

  void setLock(Compass direction) {
    lockedDoors.add(direction);
  }

  void setUnlock(Compass direction) {
    unlockedDoors.add(direction);
    lockedDoors.remove(direction);
  }

  void setName(String name) {
    this.name = name;
  }

  void setRoomDescription(String roomDescription) {
    this.roomDescription = roomDescription;
  }

  void setRoomDescriptionShort(String roomDescriptionShort) {
    this.roomDescriptionShort = roomDescriptionShort;
  }

  // Getters
  String getName() {
    return name;
  }

  String getRoomDescription() {
    return roomDescription;
  }

  String getRoomDescriptionShort() {
    return roomDescriptionShort;
  }

  Room getRoom( Compass direction) {
    switch (direction) {
      case NORTH -> {
        return north;
      }
      case EAST -> {
        return east;
      }
      case SOUTH -> {
        return south;
      }
      case WEST -> {
        return west;
      }
      default -> {
        return null;
      }
    }
  }

  NPC getNpc() {
    return npc;
  }

  //Items
  void addItem(Item item) {
    items.add(item);
  }

  void removeItems(ArrayList<Item> list) {
    items.removeAll(list);
  }

  void displayRoomInventory() {
    System.out.println();
    System.out.println("____________________ITEMS FOUND___________________");
    if(searched) {
      System.out.println("ITEM:                                      VALUE: ");
      for (Item item : items) {
        System.out.printf("%-44s %s\n", item.getShortName(), item.getValue());
      }
    }
    else
      System.out.println("Area not searched");
    //System.out.println("__________________________________________________");
  }

}
