
// Nitin Dev
// NDEV
// 112298641

import java.util.ArrayList;
import java.util.List;

// TODO : a missing interface method must be implemented in this class to make it compile. This must be in terms of volume().
public class Cuboid implements ThreeDShape, SurfaceAreaClass {

    private final ThreeDPoint[] vertices = new ThreeDPoint[8];

    /**
     * Creates a cuboid out of the list of vertices. It is expected that the
     * vertices are provided in the order as shown in the figure given in the
     * homework document (from v0 to v7).
     *
     * @param vertices the specified list of vertices in three-dimensional space.
     */
    public Cuboid(List<ThreeDPoint> vertices) {
        if (vertices.size() != 8)
            throw new IllegalArgumentException(
                    String.format("Invalid set of vertices specified for %s", this.getClass().getName()));
        int n = 0;
        for (ThreeDPoint p : vertices)
            this.vertices[n++] = p;
    }

    @Override
    public double volume() {
        // TODO
        return sideLength(vertices[0], vertices[1]) * sideLength(vertices[0], vertices[3])
                * sideLength(vertices[0], vertices[5]);
    }

    @Override
    public ThreeDPoint center() {
        // TODO
        return new ThreeDPoint(((vertices[0].getX() + vertices[7].getX()) / 2),
                ((vertices[0].getY() + vertices[7].getY()) / 2), ((vertices[0].getZ() + vertices[7].getZ()) / 2));
    }

    @Override
    public int compareTo(ThreeDShape cuboid) {
        if (this.volume() > cuboid.volume()) {
            return 1;
        } else if (this.volume() < cuboid.volume()) {
            return -1;
        }
        return 0;
    }

    public double sideLength(ThreeDPoint p1, ThreeDPoint p2) {
        double length = Math.sqrt(
                (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()) + (p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
                        + (p1.getZ() - p2.getZ()) * (p1.getZ() - p2.getZ()));
        return length;
    }

    public Cuboid random() {
        double length = Math.random() * 100;
        double width = Math.random() * 100;
        double height = Math.random() * 100;

        ThreeDPoint pnt = new ThreeDPoint(Math.random() * 100, Math.random() * 100, Math.random() * 100);
        List<ThreeDPoint> lst = new ArrayList<>();
        lst.add(pnt);

        lst.add(new ThreeDPoint(lst.get(0).getX(), lst.get(0).getY() - length, lst.get(0).getZ()));
        lst.add(new ThreeDPoint(lst.get(1).getX(), lst.get(1).getY(), lst.get(1).getZ() - height));
        lst.add(new ThreeDPoint(lst.get(2).getX(), lst.get(2).getY() + length, lst.get(2).getZ()));
        lst.add(new ThreeDPoint(lst.get(3).getX() - width, lst.get(3).getY(), lst.get(3).getZ()));
        lst.add(new ThreeDPoint(lst.get(4).getX(), lst.get(4).getY(), lst.get(4).getZ() + height));
        lst.add(new ThreeDPoint(lst.get(5).getX(), lst.get(5).getY() - length, lst.get(5).getZ()));
        lst.add(new ThreeDPoint(lst.get(6).getX(), lst.get(6).getY(), lst.get(6).getZ() - height));

        return new Cuboid(lst);
    }

    @Override
    public double surfaceArea() {
        return (2 * sideLength(vertices[0], vertices[1]) * sideLength(vertices[0], vertices[3]))
                + (2 * sideLength(vertices[0], vertices[1]) * sideLength(vertices[0], vertices[5]))
                + (2 * sideLength(vertices[0], vertices[3]) * sideLength(vertices[0], vertices[5]));
    }
}