// Nitin Dev
// NDEV
// 112298641

import java.util.Arrays;
import java.util.List;

public class Quadrilateral implements Positionable, TwoDShape {

    private final TwoDPoint[] vertices = new TwoDPoint[4];

    public Quadrilateral(double... vertices) {
        this(TwoDPoint.ofDoubles(vertices));
    }

    public Quadrilateral(List<TwoDPoint> vertices) {
        int n = 0;
        for (TwoDPoint p : vertices)
            this.vertices[n++] = p;
        if (!isMember(vertices))
            throw new IllegalArgumentException(
                    String.format("Invalid set of vertices specified for %s", this.getClass().getCanonicalName()));
    }

    /**
     * Given a list of four points, adds them as the four vertices of this
     * quadrilateral in the order provided in the list. This is expected to be a
     * counterclockwise order of the four corners.
     *
     * @param points the specified list of points.
     * @throws IllegalStateException if the number of vertices provided as input is
     *                               not equal to four.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        if (points.size() != 4) {
            throw new IllegalStateException();
        }
        for (int x = 0; x < points.size(); x++) {
            Point temp = points.get(x);

            if (temp instanceof TwoDPoint) {
                this.vertices[x] = new TwoDPoint(((TwoDPoint) temp).getX(), ((TwoDPoint) temp).getY());
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public List<TwoDPoint> getPosition() {
        return Arrays.asList(vertices);
    }

    /**
     * @return the lengths of the four sides of the quadrilateral. Since the setter
     *         {@link Quadrilateral#setPosition(List)} expected the corners to be
     *         provided in a counterclockwise order, the side lengths are expected
     *         to be in that same order.
     */
    protected double[] getSideLengths() {
        // TODO
        double[] sides = new double[4];
        for (int y = 0; y < 4; y++) {
            TwoDPoint t1 = vertices[y % 4];
            TwoDPoint t2 = vertices[(y + 1) % 4];
            double length = Math.sqrt(Math.pow((t2.getY() - t1.getY()), 2) + Math.pow((t2.getX() - t1.getX()), 2));
            sides[y] = length;
        }
        return sides;
    }

    @Override
    public int numSides() {
        return 4;
    }

    @Override
    public boolean isMember(List<? extends Point> vertices) {
        return vertices.size() == 4;
    }


    public TwoDPoint[] getVertices() {
        return this.vertices;
    }

    public boolean isRectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double d1 = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double d2 = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double d3 = Math.sqrt(Math.pow(x3 - x4, 2) + Math.pow(y3 - y4, 2));
        double d4 = Math.sqrt(Math.pow(x4 - x1, 2) + Math.pow(y4 - y1, 2));
        double diagonal1 = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        double diagonal2 = Math.sqrt(Math.pow(x2 - x4, 2) + Math.pow(y2 - y4, 2));

        return (d1 == d3) && (d2 == d4) && (diagonal1 == diagonal2);
    }

    public double areaOfRectangle() {
        double[] lengths = this.getSideLengths();
        return lengths[0] * lengths[1];
    }
}