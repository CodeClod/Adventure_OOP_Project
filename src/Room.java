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
  private boolean visited = false;
  private boolean light = false;
  private boolean darkness = false;
  ArrayList<Item> items = new ArrayList<>();
  List<Compass> lockedDoors = new ArrayList<>();
  List<Compass> unlockedDoors = new ArrayList<>();

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
    for (Item roomItem : items) {
      System.out.println(roomItem.getShortname());
    }
    System.out.println("__________________________________________________");
  }

}
