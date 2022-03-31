public class Item {
    private final String shortName;
    private final String longName;
    private final int value;

    Item(String shortName, String longName, int value) {
        this.shortName = shortName;
        this.longName = longName;
        this.value = value;
    }

    String getShortName() {
        return shortName;
    }

    String getLongName() {
        return longName;
    }

    int getValue() {
        return value;
    }
}
