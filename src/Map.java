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
  }

  void createRoom2() {
    //NPC
    Weapon gilded_dagger = new MeleeWeapon("Gilded Dagger", "a nice looking gilded dagger. It seems very nimble.", 100, 5);
    NPC merchant = new NPC("Martin the Hobbit", "a rather big hobbit, standing 3 feet 8 inches with a big fat belly and hairy feet.","You see a distressed looking hobbit standing next to a broken down wagon","merchant", 100, 250,gilded_dagger);
    Weapon magical_sword = new MeleeWeapon("Magical Sword","a magical sword gleaming with power",100, 12);
    merchant.addItem(magical_sword);
    Weapon axe = new MeleeWeapon("Axe","a nice looking axe",20, 5);
    merchant.addItem(axe);
    Food hobbit_ration = new Food("Hobbit ration", "a neat little ration of bacon, beans, sausages & POTATOES!", 20, 50);
    merchant.addItem(hobbit_ration);

    //Room
    room2 = new Room(merchant);
    room2.setName("Short roadway");
    room2.setRoomDescription("a short roadway between two forests. The cobbles lie uneven and are overgrown with grass.");
    room2.setRoomDescriptionShort("a short roadway between two forests.");
    room2.setLock(Compass.EAST);
    room1.setContainsNPC();
  }

  void createRoom3() {
    room3 = new Room();
    room3.setName("Little Abandoned Town");
    room3.setRoomDescription("a derelict town of little use to anyone. All you can see is rubble and what someone once called home.");
    room3.setRoomDescriptionShort("a derelict town of little use to anyone.");
    room3.setLock(Compass.WEST);
    Weapon torch = new MeleeWeapon("Torch", "a handy wooden torch",5,2);
    Item gold = new Gold ("Gold Coins", "a small stash of shiny golden coins",10);
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
    MeleeWeapon sword = new MeleeWeapon("Sword", "a surprisingly sharp sword",100, 8);
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
    Item magicalAmulet = new Item("Magical Amulet", "A mysterious magical amulet",200);
    Item gold = new Gold ("Gold Coins", "a small stash of shiny golden coins",5);
    room9.addItem(magicalAmulet);
    room9.addItem(gold);
  }

  Room getStartRoom() {
    return startRoom;
  }
}
