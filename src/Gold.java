public class Gold extends Item{
  private int amount;

  Gold(int amount, String shortName, String longName) {
    super(shortName,longName);
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }
}
