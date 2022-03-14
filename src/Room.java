public class Room {

  Room room;

  private String name;
  private String roomDescription;
  private Room north;
  private Room east;
  private Room south;
  private Room west;

  Room(String name, String roomDescription) {
    setName(name);
    setRoomDescription(roomDescription);
  }

  // Setters
  void setRooms(Room north,Room east, Room south, Room west) {
    this.north = north;
    this.east = east;
    this.west = west;
    this.south = south;
  }
  void setName(String name) {
    this.name = name;
  }
  void setRoomDescription(String roomDescription){
    this.roomDescription = roomDescription;
  }

  // Getters
  String getName() {
    return name;
  }
  String getRoomDescription() {
    return roomDescription;
  }

  void room() {

  }
}
