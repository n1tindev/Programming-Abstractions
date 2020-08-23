// Nitin Dev
// NDEV
// 112298641

public class Sphere implements ThreeDShape, SurfaceAreaClass {

    private ThreeDPoint center;
    private double radius;

    public Sphere(ThreeDPoint center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Sphere(double a, double b, double c, double radius) {
        this.center = new ThreeDPoint(a, b, c);
        this.radius = radius;
    }

    @Override
    public double volume() {
        double vol = ((4.0 / 3.0) * Math.PI * Math.pow(radius, 3));
        return vol;
    }

    @Override
    public Point center() {
        return this.center;
    }

    @Override
    public int compareTo(ThreeDShape sphere) {
        if (this.volume() > sphere.volume()) {
            return 1;
        } else if (this.volume() < sphere.volume()) {
            return -1;
        }
        return 0;
    }

    public Sphere random() {
        double p1 = (Math.random() * 100);
        double p2 = (Math.random() * 100);
        double p3 = (Math.random() * 100);
        double rad = (Math.random() * 100);
        return new Sphere(p1, p2, p3, rad);
    }

    @Override
    public double surfaceArea() {
        double SA = (4 * Math.PI * Math.pow(this.radius, 2));
        return SA;
    }
}