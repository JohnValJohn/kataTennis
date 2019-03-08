public enum GameScore {
    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FOURTY("40"), DEUCE("Deuce"), ADVANTAGE("Advantage");

    public final String value;

    GameScore(String value) {
        this.value = value;
    }
}
