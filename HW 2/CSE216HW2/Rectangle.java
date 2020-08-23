// Nitin Dev
// NDEV
// 112298641

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Quadrilateral implements SymmetricTwoDShape {

    public Rectangle(List<TwoDPoint> vertices) {
        super(vertices);
    }

    /**
     * The center of a rectangle is calculated to be the point of intersection of
     * its diagonals.
     *
     * @return the center of this rectangle.
     */
    @Override
    public Point center() {
        // TODO
        TwoDPoint[] temp = this.getVertices();
        TwoDPoint retangle = new TwoDPoint(((temp[0].getX() + temp[2].getX()) / 2), ((temp[0].getY() + temp[2].getY()) / 2));
        return retangle;

    }

    @Override
    public boolean isMember(List<? extends Point> vertices) {
        // TODO
        if (vertices.size() != 4) {
            return false;
        }
        List<TwoDPoint> v2 = new ArrayList<>();
        for (int x = 0; x < vertices.size(); x++) {
            if (vertices.get(x) instanceof TwoDPoint) {
                v2.add((TwoDPoint) vertices.get(x));
            } else {
                return false;
            }
        }
        try {
            Quadrilateral temp = new Quadrilateral(v2);
            TwoDPoint[] vertices_ = temp.getVertices();
            return temp.isRectangle(vertices_[0].getX(), vertices_[0].getY(), vertices_[1].getX(), vertices_[1].getY(),
                    vertices_[2].getX(), vertices_[2].getY(), vertices_[3].getX(), vertices_[3].getY())
                    && temp.areaOfRectangle() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public double area() {
        // TODO
        double[] lengthMeasurement = this.getSideLengths();
        return lengthMeasurement[0] * lengthMeasurement[1];
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

}