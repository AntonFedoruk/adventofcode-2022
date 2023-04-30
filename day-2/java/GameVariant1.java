public enum GameVariant1 implements GameStrategy {

    //A - Rock
//B - Paper
//C - Scissors
//X - Rock
//Y - Paper
//Z - Scissors
    AX(1 + 3),
    AY(2 + 6),
    AZ(3 + 0),

    BX(1 + 0),
    BY(2 + 3),
    BZ(3 + 6),

    CX(1 + 6),
    CY(2 + 0),
    CZ(3 + 3);

    GameVariant1(int finalScore) {
        this.finalScore = finalScore;
    }

    private final int finalScore;

    public int getFinalScore() {
        return finalScore;
    }
}
