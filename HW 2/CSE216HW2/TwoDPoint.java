// Nitin Dev
// NDEV
// 112298641

import java.util.ArrayList;
import java.util.List;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The
 * coordinates of such a point is given by exactly two doubles specifying its
 * <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point {

    private double x;
    private double y;

    public TwoDPoint(double x, double y) {
        // TODO
        this.x = x;
        this.y = y;
    }

    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        // TODO
        return new double[] { x, y };
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of
     * doubles. A valid argument must always be an even number of doubles so that
     * every pair can be used to form a single <code>TwoDPoint</code> to be added to
     * the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of
     *                                  doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        // TODO
        if (coordinates.length % 2 == 1) {
            throw new IllegalArgumentException();
        }
        ArrayList<TwoDPoint> twoDPoints = new ArrayList<>();
        for (int x = 0; x < coordinates.length - 1; x += 2) {
            TwoDPoint temp = new TwoDPoint(coordinates[x], coordinates[x + 1]);
            twoDPoints.add(temp);
        }
        return twoDPoints;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
}