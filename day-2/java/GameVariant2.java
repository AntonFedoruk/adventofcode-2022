public enum GameVariant2 implements GameStrategy {

//A - Rock
//B - Paper
//C - Scissors
//X means you need to lose
//Y means you need to end the round in a draw
//Z means you need to win

    AX(0 + 3),
    AY(3 + 1),
    AZ(6 + 2),

    BX(0 + 1),
    BY(3 + 2),
    BZ(6 + 3),

    CX(0 + 2),
    CY(3 + 3),
    CZ(6 + 1);

    GameVariant2(int finalScore) {
        this.finalScore = finalScore;
    }

    private final int finalScore;

    public int getFinalScore() {
        return finalScore;
    }
}
