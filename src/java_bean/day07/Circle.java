package java_bean.day07;

/**
 * @ClassName: Circle.java
 * @Author: anpeng
 * @Date: 2023/12/25 15:11
 */
public class Circle {
    private double radius;

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
