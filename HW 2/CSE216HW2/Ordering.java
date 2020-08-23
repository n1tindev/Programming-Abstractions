// Nitin Dev
// NDEV
// 112298641

import java.util.*;

public class Ordering {

    static class XLocationComparator implements Comparator<TwoDShape> {
        @Override
        public int compare(TwoDShape o1, TwoDShape o2) {
            // TODO
            double o1_X = 0;
            if (o1 instanceof Circle) {
                Circle temp = (Circle) o1;
                o1_X = temp.center().coordinates()[0] - temp.getRadius();
            } else if (o1 instanceof Quadrilateral) {
                Quadrilateral temp = (Quadrilateral) o1;
                double min = temp.getVertices()[0].getX();
                for (int x = 1; x < 4; x++) {
                    if (min > temp.getVertices()[x].getX()) {
                        min = temp.getVertices()[x].getX();
                    }
                }
                o1_X = min;
            }

            double o2_X = 0;
            if (o2 instanceof Circle) {
                Circle temp = (Circle) o2;
                o2_X = temp.center().coordinates()[0] - temp.getRadius();
            } else if (o2 instanceof Quadrilateral) {
                Quadrilateral temp = (Quadrilateral) o2;
                double min = temp.getVertices()[0].getX();
                for (int y = 1; y < 4; y++) {
                    if (min > temp.getVertices()[y].getX()) {
                        min = temp.getVertices()[y].getX();
                    }
                }
                o2_X = min;
            }
            if (o1_X > o2_X) {
                return 1;
            } else if (o1_X < o2_X) {
                return -1;
            }
            return 0;
        }
    }

    static class AreaComparator implements Comparator<SymmetricTwoDShape> {
        @Override
        public int compare(SymmetricTwoDShape o1, SymmetricTwoDShape o2) {
            // TODO
            if (o1.area() > o2.area()) {
                return 1;
            } else if (o1.area() < o2.area()) {
                return -1;
            }
            return 0;
        }
    }

    static class SurfaceAreaComparator implements Comparator<ThreeDShape> {
        @Override
        public int compare(ThreeDShape o1, ThreeDShape o2) {
            // TODO
            SurfaceAreaClass s1 = (SurfaceAreaClass) o1;
            SurfaceAreaClass s2 = (SurfaceAreaClass) o2;
            if (s1.surfaceArea() > s2.surfaceArea()) {
                return 1;
            } else if (s1.surfaceArea() < s2.surfaceArea()) {
                return -1;
            }
            return 0;
        }
    }

    // TODO: there's a lot wrong with this method. correct it so that it can work
    // properly with generics.
    static <T> void copy(Collection<? extends T> source, Collection<T> destination) {
        destination.addAll(source);
    }

    public static void main(String[] args) {
        List<TwoDShape> shapes = new ArrayList<>();
        List<SymmetricTwoDShape> symmetricshapes = new ArrayList<>();
        List<ThreeDShape> threedshapes = new ArrayList<>();

        /*
         * uncomment the following block and fill in the "..." constructors to create
         * actual instances. If your implementations are correct, then the code should
         * compile and yield the expected results of the various shapes being ordered by
         * their smallest x-coordinate, area, volume, surface area, etc.
         */

        List<TwoDPoint> rectangleCoordinates = new ArrayList<>();
        rectangleCoordinates.add(new TwoDPoint(4, 2));
        rectangleCoordinates.add(new TwoDPoint(-4, 2));
        rectangleCoordinates.add(new TwoDPoint(-4, -2));
        rectangleCoordinates.add(new TwoDPoint(4, -2));
        symmetricshapes.add(new Rectangle(rectangleCoordinates));


        List<TwoDPoint> squareCoordinates = new ArrayList<>();
        squareCoordinates.add(new TwoDPoint(3.5, 3.5));
        squareCoordinates.add(new TwoDPoint(-3.5, 3.5));
        squareCoordinates.add(new TwoDPoint(-3.5, -3.5));
        squareCoordinates.add(new TwoDPoint(3.5, -3.5));
        symmetricshapes.add(new Square(squareCoordinates));

        symmetricshapes.add(new Circle(2, 2, 4));

        copy(symmetricshapes, shapes); // note-1 //
        // shapes.add(new Quadrilateral(new ArrayList<>()));

        // sorting 2d shapes according to various criteria
        shapes.sort(new XLocationComparator());
        symmetricshapes.sort(new XLocationComparator());
        symmetricshapes.sort(new AreaComparator());

        // sorting 3d shapes according to various criteria
        Collections.sort(threedshapes);
        threedshapes.sort(new SurfaceAreaComparator());

        /*
         * if your changes to copy() are correct, uncommenting the following block will
         * also work as expected note that copy() should work for the line commented
         * with 'note-1' while at the same time also working with the lines commented
         * with 'note-2' and 'note-3'.
         */

        List<Number> numbers = new ArrayList<>();
        List<Double> doubles = new ArrayList<>();
        Set<Square> squares = new HashSet<>();
        Set<Quadrilateral> quads = new LinkedHashSet<>();

        copy(doubles, numbers); // note-2 //
        copy(squares, quads); // note-3 //
    }
}