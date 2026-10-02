/**
 * Overrides {@link Shape#getCorner()} and also shows how {@code super} reaches
 * the overridden parent implementation.
 */
public class Rectangle extends Shape {

    @Override
    public int getCorner() {
        return 4;
    }

    public int getParentCorner() {
        return super.getCorner();
    }
}
