package net.planetarium;

public class Satellite extends CelestialBody{
    private final Planet parent;

    public Satellite(String name, float mass, int radius, int id, Point position, Planet parent) {
        super(name, mass, radius, id, position);
        this.parent = parent;
    }

    public Planet getParent() { return parent; }
}
