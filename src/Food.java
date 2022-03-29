public class Food extends Item{
  private final int healthPoints;

  Food(String shortName, String longName, int value, int healthPoints) {
    super(shortName, longName, value);
    this.healthPoints = healthPoints;
  }

  public int getHealthPoints() {
    return healthPoints;
  }
}
