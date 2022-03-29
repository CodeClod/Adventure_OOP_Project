import java.util.ArrayList;
import java.util.Scanner;

public class NPC extends Player {
  NPC(Room StartingRoom, int health, int purseGold, Weapon equipped, UserInterface ui, Scanner in) {
    super(StartingRoom, health, purseGold, equipped, ui, in);
  }
}


