import java.util.ArrayList;

public class Room {

  private String name;
  private String roomDescription;
  private String roomDescriptionShort;
  private Room north;
  private Room east;
  private Room south;
  private Room west;
  private boolean visited = false;
  private boolean light =  false;
  private boolean darkRoom = false;
  private boolean lockNorth = false;
  private boolean lockEast = false;
  private boolean lockSouth = false;
  private boolean lockWest = false;

  ArrayList<Item> roomItems = new ArrayList<>();

  void addItem(Item item) {
    roomItems.add(item);
  }

  Room() {
  }

  boolean checkIfVisited() {
    return visited;
  }
  boolean checkIfLightsOn() {
    return light;
  }
  boolean checkIfDarkRoom() {
    return darkRoom;
  }
  boolean checkIfDoorIsLockedNorth() {
    return lockNorth;
  }
  boolean checkIfDoorIsLockedEast() {
    return lockEast;
  }
  boolean checkIfDoorIsLockedSouth() {
    return lockSouth;
  }
  boolean checkIfDoorIsLockedWest() {
    return lockWest;
  }
  // Setters
  void setRooms(Room north,Room east, Room south, Room west) {
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
    darkRoom = true;
  }

  void setLockNorth() {
    lockNorth = true;
  }
  void setLockEast() {
    lockEast = true;
  }
  void setLockSouth() {
    lockSouth = true;
  }
  void setLockWest() {
    lockWest = true;
  }

  void setUnlockNorth() {
    lockNorth = false;
  }
  void setUnlockEast() {
    lockEast = false;
  }
  void setUnlockSouth() {
    lockSouth = false;
  }
  void setUnlockWest() {
    lockWest = false;
  }

  void setName(String name) {
    this.name = name;
  }
  void setRoomDescription(String roomDescription){
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
  Room getRoomNorth() {
    return north;
  }
  Room getRoomEast() {
    return east;
  }
  Room getRoomSouth() {
    return south;
  }
  Room getRoomWest() {
    return west;
  }

}
