public class Food extends Item{
  private final int healing;

  Food(String shortName, String longName, int value, int healing) {
    super(shortName, longName, value);
    this.healing = healing;
  }


  public int getHealing() {
    return healing;
  }
}
