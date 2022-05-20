package net.planetarium;

public class IdGenerator {
    private static int id;

    public static int getId() {
        return id++;
    }
}
