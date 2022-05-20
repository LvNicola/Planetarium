package net.planetarium;

import java.util.TreeMap;
import java.util.TreeSet;

public class SolarSystemManager {

    public static void main(String[] args) {
        SolarSystemManager solarSystemManager = new SolarSystemManager();
        solarSystemManager.run();
    }

    private Star star;

    //utilizzato per ottenere in modo efficente il corpo celeste partendo dall'ID
    private final TreeMap<Integer, CelestialBody> sorted_by_id = new TreeMap<>();
    //utilizzato per ottenere in modo efficente il corpo celeste partendo dal nome
    private final TreeMap<String, CelestialBody> sorted_by_name = new TreeMap<>();
    /*utilizzato per controllare in modo efficente se è già presente
    un'altro corpo celeste nel sistema solare nella stessa posizione*/
    private final TreeSet<CelestialBody> sorted_by_position = new TreeSet<>();

    public void run() {
        System.out.println("Welcome to the Empire Solar System Manager!");
        System.out.println("Before proceeding with the initialization of the system you must configure it...");
        System.out.println("\nDigit K for configuration system, X for exit...");

        if (Console.stringInput(new String[]{"K", "X"}).equals("K")) {
            Console.clearScreen();
            initialConfiguration();
            while (true) menuOptions();
        } else {
            Console.closeProgram();
        }
    }

    private void initialConfiguration() {
        System.out.print("\n\n");
        System.out.println(Console.ANSI_GREEN_BACKGROUND + Console.ANSI_BLACK + "--- CONFIGURATION ---" + Console.ANSI_RESET);

        System.out.println("Enter the name of your star: ");
        String starName = Console.stringInput();

        System.out.println("Enter the mass of your star: ");
        int starMass = Console.intInput();

        System.out.println("Enter the radius of your star: ");
        int starRadius = Console.intInput();

        this.star = new Star(starName, starMass, starRadius, IdGenerator.getId(), new Point(0, 0));
        addCelestialBody(star);

        Console.printSuccess("Star successfully added!");
        Console.printSuccess("Setup completed.\n");
        Console.printSuccess("Initialization in progress...");

        try {
            Thread.sleep(1400);
            Console.clearScreen();
        } catch (InterruptedException ignored) {
        }
    }

    private void menuOptions() {
        System.out.print("\n\n");
        System.out.println(Console.ANSI_GREEN_BACKGROUND + Console.ANSI_BLACK + "--- IMPERIAL MENU ---" + Console.ANSI_RESET);

        System.out.println("1) Add planet");
        System.out.println("2) Add satellite");
        System.out.println("3) Remove celestial body (sun excluded)");
        System.out.println("4) Search celestial body in the system");
        System.out.println("5) Celestial body display, enter 0 to view all celestial bodies");
        System.out.println("6) Mass center calculation");
        System.out.println("0) Exit");

        System.out.println("\nChoose the action you want to perform on the selected menu:");
        int menuChoice = Console.intInput();

        System.out.println();
        switch (menuChoice) {
            case 1: {
                System.out.println("Enter planet name: ");
                String planetName = Console.stringInput();

                System.out.println("Enter planet mass: ");
                int planetMass = Console.intInput();

                System.out.println("Enter planet radius: ");
                int planetRadius = Console.intInput();

                System.out.println("Enter center X coordinate: ");
                double planetXPos = Console.doubleInput();

                System.out.println("Enter center Y coordinate: ");
                double planetYPos = Console.doubleInput();

                if (addPlanet(new Planet(planetName, planetMass, planetRadius, IdGenerator.getId(), new Point(planetXPos, planetYPos))))
                    Console.printSuccess("\nPlanet successfully added");
                else
                    Console.printError("\n Planet not added");
            }
            try {
                Thread.sleep(1400);
                Console.clearScreen();
            } catch (InterruptedException ignored) {
            }
            break;
            case 2: {
                System.out.println("Enter satellite planet parent: ");
                CelestialBody satelliteParent = findByUser();
                if (!(satelliteParent instanceof Planet))
                    break;

                System.out.println("Enter satellite name: ");
                String satelliteName = Console.stringInput();

                System.out.println("Enter satellite mass: ");
                int satelliteMass = Console.intInput();

                System.out.println("Enter satellite radius: ");
                int satelliteRadius = Console.intInput();

                System.out.println("Enter center X coordinate: ");
                double satelliteXPos = Console.doubleInput();

                System.out.println("Enter center Y coordinate: ");
                double satelliteYPos = Console.doubleInput();

                if (addSatellite(new Satellite(satelliteName, satelliteMass, satelliteRadius, IdGenerator.getId(), new Point(satelliteXPos, satelliteYPos), (Planet) (satelliteParent))))
                    Console.printSuccess("\nSatellite successfully added");
                else
                    Console.printError("\n Satellite not added");
            }
            try {
                Thread.sleep(1400);
                Console.clearScreen();
            } catch (InterruptedException ignored) {
            }
            break;
            case 3:
                CelestialBody to_remove = findByUser();
                if (to_remove != null)
                    remove(to_remove);
                break;
            case 4: {
                if (findByUser() != null)
                    Console.printSuccess("The required celestial body is present in the system");
                else Console.printError("The required celestial body is not present in the system...");
            }
            break;
            case 5: {
                CelestialBody cb;
                cb = findByUser();
                if (cb == null)
                    Console.printError("The required celestial body is not present in the system...");
                else
                    visualize(cb);
            }
            break;
            case 6:
                System.out.println("the mass center is " + centerOfMass());
                break;
            case 0:
            default:
                Console.closeProgram();
                break;
        }
    }

    private CelestialBody findByUser() {
        System.out.println("Enter 0 to search for name, 1 to search for ID: ");

        if (Console.stringInput(new String[]{"0", "1"}).equals("0")) {
            System.out.println("Enter the celestial body name to search: ");
            return find(Console.stringInput());
        }

        System.out.println("Enter the celestial body ID to search: ");
        return find(Console.intInput());
    }


    private boolean addCelestialBody(CelestialBody c) {
        if (!sorted_by_id.containsKey(c.getId()) && !sorted_by_name.containsKey(c.getName()) && sorted_by_position.add(c)) {
            sorted_by_id.put(c.getId(), c);
            sorted_by_name.put(c.getName(), c);
            return true;
        }
        return false;
    }

    private boolean addPlanet(Planet planet) {
        if (addCelestialBody(planet)) {
            star.addPlanet(planet);
            return true;
        }
        return false;
    }

    private boolean addSatellite(Satellite satellite) {
        if (addCelestialBody(satellite)) {
            star.addSatellite(satellite);
            return true;
        }
        return false;
    }

    private void remove(CelestialBody c) {
        sorted_by_id.remove(c.getId());
        sorted_by_position.remove(c);
        sorted_by_name.remove(c.getName());
    }

    private CelestialBody find(int ID) {
        return sorted_by_id.get(ID);
    }

    private CelestialBody find(String name) {
        return sorted_by_name.get(name);
    }

    private void visualize(CelestialBody cb) {
        if (cb instanceof Star) visualize((Star) cb);
        else if (cb instanceof Planet) visualize((Planet) cb);
        else visualize((Satellite) cb);
    }

    private void visualize(Planet planet) {
        System.out.println("\tPlanet" + planet);

        for (Satellite satellite : planet.getSatellites())
            System.out.println("\t\t" + satellite);
    }

    private void visualize(Satellite satellite) {
        System.out.println("Satellite" + satellite);

        String starPath = "{ ID: " + star.getId() + ", " + star.getName() + "}";
        String planetPath = "{ ID: " + satellite.getParent().getId() + ", " + satellite.getParent().getName() + "}";
        String satellitePath = "{ ID: " + satellite.getId() + ", " + satellite.getName() + "}";

        System.out.println("Path (Star > Planet > Satellite): \n" + starPath + " > " + planetPath + " > " + satellitePath);
    }

    private void visualize(Star star) {
        System.out.println("Star: " + star);

        for (Planet planet : star.getPlanets())
            visualize(planet);
    }

    private Point centerOfMass() {
        double x = 0, y = 0;
        long sum_mass = 0;
        for (CelestialBody cb : sorted_by_id.values()) {
            x += cb.getPosition().getX() * cb.getMass();
            y += cb.getPosition().getY() * cb.getMass();
            sum_mass += cb.getMass();
        }
        return new Point(x / sum_mass, y / sum_mass);
    }
}
