import java.util.ArrayList;

public class NPC {
  private final String name;
  private final String descriptionShort;
  private final String descriptionLong;
  private final String classDescription;
  private final int health;
  private int purseGold;
  private final Weapon equipped;
  ArrayList<Item> items = new ArrayList<>();

  NPC( String name, String descriptionShort, String descriptionLong, String classDescription, int health, int purseGold, Weapon equipped) {
    this.name = name;
    this.descriptionShort = descriptionShort;
    this.descriptionLong = descriptionLong;
    this.classDescription = classDescription;
    this.health = health;
    this.purseGold = purseGold;
    this.equipped = equipped;
  }

  //Items - add/remove
  public void addItem(Item item) {
    items.add(item);
  }

  void removeItems(ArrayList<Item> list) {
    items.removeAll(list);
  }

  //gold - add/remove
  void addToPurse(int gold) {
    purseGold += gold;
  }

  void removeFromPurse(int gold) {
    purseGold -= gold;
  }

  //Getters
  public String getName() {
    return name;
  }

  public String getDescriptionShort() {
    return descriptionShort;
  }

  public String getDescriptionLong() {
    return descriptionLong;
  }

  public int getHealth() {
    return health;
  }

  public int getPurseGold() {
    return purseGold;
  }

  public Weapon getEquipped() {
    return equipped;
  }

  public ArrayList<Item> getItems() {
    return items;
  }

  public String getClassDescription() {
    return classDescription;
  }

  // Inventory
  void displayInventory() {
    System.out.println("_______________MERCHANT INVENTORY_________________");
    System.out.println("ITEM:                                      VALUE: ");
    for (Item item : items) {
      System.out.printf("%-44s %s\n", item.getShortName(), item.getValue());
    }
    System.out.println("__________________________________________________");
    System.out.printf("%-44s %d \n", "PURSE (GOLD):", purseGold);
    if (equipped != null)
      System.out.println("EQUIPPED: " + equipped.getShortName());
    else
      System.out.println("No weapon equipped");
    System.out.println();
  }
}


