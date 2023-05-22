import java.util.List;

public class RopeSystem9 {
    private final Marker head;
    List<Marker> knots;

    public RopeSystem9(Marker head, Marker knot1, Marker knot2, Marker knot3, Marker knot4, Marker knot5, Marker knot6, Marker knot7, Marker knot8, Marker knot9) {
        this.head = head;
        knots = List.of(knot1, knot2, knot3, knot4, knot5, knot6, knot7, knot8, knot9);
    }

    void move(MoveCommand moveCommand) {
        System.out.println("!!! Move: '" + moveCommand.direction + " " + moveCommand.iterations + "`!!!");
        System.out.print("Initial Head[" + head.getX() + "," + head.getY() + "] ");
        int counter = 1;
        System.out.println();
        for (int i = 0; i < moveCommand.iterations; i++) {
            Marker prevKnot = head;
            int parentKnotPrevXPosition = prevKnot.getX();
            int parentKnotPrevYPosition = prevKnot.getY();
            System.out.println("~'" + moveCommand.direction + "' " + moveCommand.iterations + "times___" + "iteration #" + (i + 1));
            head.move(moveCommand.direction);
            System.out.println("head's new position[" + head.getX() + "," + head.getY() + "]");
            counter = 1;
            for (Marker knot : knots) {
                System.out.println("Parent knot prev position[" + parentKnotPrevXPosition + ", " + parentKnotPrevYPosition + "]");
                System.out.println("knot" + counter + ": [" + knot.getX() + ", " + knot.getY() + "]");
                int dx = prevKnot.getX() - knot.getX();
                int dy = prevKnot.getY() - knot.getY();
                if (dx == 1 && dy ==1){
                    System.out.print("knot" + counter + " shouldn`t move. ");
                    System.out.println(" Stop iteration#" + (i + 1));
                    System.out.print("Head[" + head.getX() + "," + head.getY() + "] ");
                    int counterS = 1;
                    for (Marker knotS : knots) {
                        System.out.print("knot" + counterS + "[" + knotS.getX() + "," + knotS.getY() + "]");
                        counterS++;
                    }
                    System.out.println();
                    break;
                } else if (dx >= 1 && dy >= 1) {
                    System.out.println("parent knot is too far move +1,+1");
                    knot.move(knot.getX() + 1, knot.getY() + 1);
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else if (dx <= -1 && dy >= 1) {
                    System.out.println("parent knot is too far move -1,+1");
                    knot.move(knot.getX() - 1, knot.getY() + 1);
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else if (dx <= -1 && dy <= -1) {
                    System.out.println("parent knot is too far move -1,-1");
                    knot.move(knot.getX() - 1, knot.getY() - 1);
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else if (dx >= 1 && dy <= -1) {
                    System.out.println("parent knot is too far move +1,-1");
                    knot.move(knot.getX() + 1, knot.getY() - 1);
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else if (dx == 2) {
                    System.out.println("parent knot is too far move +1,0");
                    knot.move(knot.getX() + 1, knot.getY());
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else if (dx == -2) {
                    System.out.println("parent knot is too far move -1,0");
                    knot.move(knot.getX() - 1, knot.getY());
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else if (dy == -2) {
                    System.out.println("parent knot is too far move 0,-1");
                    knot.move(knot.getX(), knot.getY() - 1);
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else if (dy == 2) {
                    System.out.println("parent knot is too far move 0,+1");
                    knot.move(knot.getX(), knot.getY() + 1);
                    System.out.println("knot" + counter + " moved to [" + knot.getX() + ", " + knot.getY() + "]");
                    counter++;
                } else {
                    System.out.print("knot" + counter + " shouldn`t move. ");
                    System.out.println(" Stop iteration#" + (i + 1));
                    System.out.print("Head[" + head.getX() + "," + head.getY() + "] ");
                    int counterS = 1;
                    for (Marker knotS : knots) {
                        System.out.print("knot" + counterS + "[" + knotS.getX() + "," + knotS.getY() + "]");
                        counterS++;
                    }
                    System.out.println();
                    break;
                }
                prevKnot = knot;
                System.out.println("now Parent knot should be knot" + (counter - 1));
            }
        }
    }

    public Marker getTail() {
        return knots.get(8);
    }
}
