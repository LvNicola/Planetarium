package net.planetarium;

import java.util.TreeSet;

public class Planet extends CelestialBody {
    private final TreeSet<Satellite> satellites = new TreeSet<>();

    public Planet(String name, float mass, int radius, int id, Point position) {
        super(name, mass, radius, id, position);
    }

    public TreeSet<Satellite> getSatellites() {
        return satellites;
    }

    public void addSatellite(Satellite satellite) {
        satellites.add(satellite);
    }
}
