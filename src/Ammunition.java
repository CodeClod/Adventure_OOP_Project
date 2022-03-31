public class Ammunition extends Item{
  private int amount;

  Ammunition(String shortName, String longName, int value, int amount) {
    super(shortName, longName, value);
    this.amount = amount;
  }
  // Add subclasses for each kind of ammunition (arrows, bolts, bullets) and add another parameter to increase or
  // decrease dmg of base weapon depending on type. (Bodkin, barbed, flaming).

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
