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

  Room() {
  }

  boolean checkIfVisited() {
    return visited;
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
