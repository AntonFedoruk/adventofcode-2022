public class RopeSystem {
    public RopeSystem(Marker head, Marker tail) {
        this.tail = tail;
        this.head = head;
    }

    private Marker tail, head;
    void move(MoveCommand moveCommand) {
        System.out.println("!!! Move: '" + moveCommand.direction + "' " + moveCommand.iterations + " times --> ");
        System.out.println("Initial Head["+ head.getX() + "," + head.getY() +"] Tail["+ tail.getX() + "," + tail.getY() +"]");
        for (int i = 0; i < moveCommand.iterations; i++) {
            int prevHeadXPos = head.getX();
            int prevHeadYPos = head.getY();
            System.out.println("iteration #" + (i+1));
            head.move(moveCommand.direction);
            int dx = head.getX() - tail.getX();
            int dy = head.getY() - tail.getY();
            System.out.println("Math.abs(dx):" + Math.abs(dx));
            System.out.println("Math.abs(dy):" + Math.abs(dy));
            if (Math.abs(dx) > 1 | Math.abs(dy) > 1) {
                tail.move(prevHeadXPos, prevHeadYPos);
            }
            System.out.println("now Head["+ head.getX() + "," + head.getY() +"] Tail["+ tail.getX() + "," + tail.getY() +"]");
        }
    }
}
