import java.util.ArrayList;

public class MerchantNPC {
  private int purse;
  ArrayList<Item> items = new ArrayList<>();


  MerchantNPC() {
    purse = 1000;
    Item magical_sword = new Item("Magical Sword", "Magical Sword - LOOONG", 200);
    items.add(magical_sword);

  }
}


