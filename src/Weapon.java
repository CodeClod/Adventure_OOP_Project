public abstract class Weapon extends Item{
  private final int damage;

  Weapon(String shortName, String longName, int value, int damage) {
    super(shortName, longName, value);
    this.damage = damage;
  }

  public int getDamage() {
    return damage;
  }
}
