/**
 * Demonstrates method overriding and {@code super} calls.
 *
 * The original used local variables named {@code Shape} and {@code Rectangle},
 * which shadow the type names; they were renamed to {@code shape} and
 * {@code rectangle} (see docs/evaluation.md).
 */
public class ShapeApp {
    public static void main(String[] args) {

        var shape = new Shape();
        System.out.println(shape.getCorner());

        var rectangle = new Rectangle();
        System.out.println(rectangle.getCorner());
        System.out.println(rectangle.getParentCorner());
    }
}
