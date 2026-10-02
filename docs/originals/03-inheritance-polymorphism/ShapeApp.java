public class ShapeApp {
    public static void main(String[] args) {

        var Shape = new Shape();
        System.out.println(Shape.getCorner());

        var Rectangle = new Rectangle();
        System.out.println(Rectangle.getCorner());
        System.out.println(Rectangle.getParentCorner());

    }
}
