public class Room {

  Room room;

  private String name;
  private String roomDescription;
  private String roomDescriptionShort;
  private Room north;
  private Room east;
  private Room south;
  private Room west;
  private boolean visited = false;
  private boolean light =  false;
  private boolean torch = false;

  Room() {
  }

  boolean checkIfVisited() {
    return visited;
  }
  boolean checkIfLightsOn() {
    return light;
  }
  boolean checkIfTorchLit() {
    return torch;
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

  void setLitTorch() {
    torch = true;
  }
  void setPutOutTorch() {
    torch = false;
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
