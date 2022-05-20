package net.planetarium;

public class CelestialBody implements Comparable<CelestialBody> {
    private String name;
    private float mass;
    private int radius;
    private int id;
    private Point position;

    public CelestialBody(String name, float mass, int radius, int id, Point position) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.id = id;
        this.position = position;
    }

    public CelestialBody(CelestialBody to_copy) {
        name = to_copy.name;
        mass = to_copy.mass;
        radius = to_copy.radius;
        id = to_copy.id;
        position = to_copy.position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelestialBody that = (CelestialBody) o;
        return id == that.id;
    }

    @Override
    public int compareTo(CelestialBody c) {
        if (collision(c))
            return 0;
        return getPosition().compareTo(c.getPosition());
    }

    public boolean collision(CelestialBody c) {
        return position.distance(c.position) <= radius + c.radius;
    }

    public String toString() {
        return "ID: " + id + "; Nome: " + name + "; Mass: " + mass + "; Radius: " + radius + "; Position: " + position.toString();
    }
}
