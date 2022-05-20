package net.planetarium;

import java.util.TreeSet;

public class Star extends CelestialBody{
    private final TreeSet<Planet> planets = new TreeSet<>();

    public Star(String name, float mass, int radius, int id, Point position) {
        super(name, mass, radius, id, position);
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public TreeSet<Planet> getPlanets() { return planets; }

    public void addSatellite(Satellite satellite) {
        satellite.getParent().addSatellite(satellite);
    }
}

