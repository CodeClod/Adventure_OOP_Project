public class Map {
  private Room startRoom;
  private Room room1, room2, room3, room4, room5, room6, room7, room8, room9;

  void createRooms() {
    createRoom1();
    createRoom2();
    createRoom3();
    createRoom4();
    createRoom5();
    createRoom6();
    createRoom7();
    createRoom8();
    createRoom9();

    room1.setRooms(null, room2, room4, null);
    room2.setRooms(null, room3, null, room1);
    room3.setRooms(null, null, room6, room2);
    room4.setRooms(room1, null, room7, null);
    room5.setRooms(null, null, room8, null);
    room6.setRooms(room3, null, room9, null);
    room7.setRooms(room4, room8, null, null);
    room8.setRooms(room5, room9, null, room7);
    room9.setRooms(room6, null, null, room8);

    startRoom = room1;
  }

  void createRoom1() {
    room1 = new Room();
    room1.setName("Forest clearing");
    room1.setRoomDescription("a quiet, serene clearing. The mossy undergrowth looks like soft green wool.");
    room1.setRoomDescriptionShort("a quiet, serene clearing.");
    Item axe = new Item("Axe","A nice looking axe");
    room1.addItem(axe);
  }

  void createRoom2() {
    room2 = new Room();
    room2.setName("Short roadway");
    room2.setRoomDescription("a short roadway between two forests. The cobbles lie uneven and are overgrown with grass.");
    room2.setRoomDescriptionShort("a short roadway between two forests.");
    room2.setLock(Compass.EAST);
  }

  void createRoom3() {
    room3 = new Room();
    room3.setName("Little Abandoned Town");
    room3.setRoomDescription("a derelict town of little use to anyone. All you can see is rubble and what someone once called home.");
    room3.setRoomDescriptionShort("a derelict town of little use to anyone.");
    room3.setLock(Compass.WEST);
    Item torch = new Item("Torch", "A handy wooden torch");
    Item gold = new Gold (10,"Gold Coin", "A shiny golden coin");
    room3.addItem(torch);
    room3.addItem(gold);
  }

  void createRoom4() {
    room4 = new Room();
    room4.setName("Forest river");
    room4.setRoomDescription("a surprisingly bright river. You can see fish swimming in the clear water.");
    room4.setRoomDescriptionShort("a surprisingly bright river.");
  }

  void createRoom5() {
    room5 = new Room();
    room5.setName("Hermit's cave");
    room5.setRoomDescription("a small cave inhabited by a hermit. You glare inside and see various trinkets made of animal bones");
    room5.setRoomDescriptionShort("a small cave inhabited by a hermit.");
  }

  void createRoom6() {
    room6 = new Room();
    room6.setName("Haunted forest");
    room6.setRoomDescription("a dark forest, filled with all sorts of thorny plant. You hear strange noises all around you.");
    room6.setRoomDescriptionShort("a dark forest, filled with all sorts of thorny plant.");
    room6.setDarkRoom();
    if (room6.checkIfLightsOn()) { //alternative description - lights on
      room6.setRoomDescription("a light forest, filled with all sorts of thorny plant.");
      room6.setRoomDescriptionShort("a light forest, filled with all sorts of thorny plant.");
    }
  }

  void createRoom7() {
    room7 = new Room();
    room7.setName("Ruined Hilltop Castle");
    room7.setRoomDescription("a ruined castle overlooking the river below. It doesn't seem to have been inhabited for a long time");
    room7.setRoomDescriptionShort("a ruined castle overlooking the river below.");
    Item sword = new Item("Sword", "Surprisingly sharp word");
    room7.setLock(Compass.EAST);
    room7.addItem(sword);
  }

  void createRoom8() {
    room8 = new Room();
    room8.setName("Damp swamp");
    room8.setRoomDescription("a mysterious swamp. There's a slim, old walkway through the bog.");
    room8.setRoomDescriptionShort("a mysterious swamp.");
    room8.setLock(Compass.NORTH);
    room8.setLock(Compass.WEST);
  }

  void createRoom9() {
    room9 = new Room();
    room9.setName("Cabin in the Woods");
    room9.setRoomDescription("a strange little cabin. The cabin is inhabited by three sisters, possibly witches");
    room9.setRoomDescriptionShort("strange little cabin.");
    Item magicalAmulet = new Item("Magical Amulet", "A mysterious magical amulet");
    Item gold = new Gold (5,"Gold Coin", "A shiny golden coin");
    room9.addItem(magicalAmulet);
    room9.addItem(gold);
  }

  Room getStartRoom() {
    return startRoom;
  }
/*
  Room getRoom1() {
    return room1;
  }

  Room getRoom2() {
    return room2;
  }

  Room getRoom3() {
    return room3;
  }

  Room getRoom4() {
    return room4;
  }

  Room getRoom5() {
    return room5;
  }

  Room getRoom6() {
    return room6;
  }

  Room getRoom7() {
    return room7;
  }

  Room getRoom8() {
    return room8;
  }

  Room getRoom9() {
    return room9;
  }

 */
}
