import java.util.Random;

public class VehicleFactory {
    private static final Random rand = new Random();

    private static String generatePlate() {
        String[] cities = {"59", "29", "43", "30"};
        String[] chars = {"A", "B", "C", "X", "Y", "Z"};

        String city = cities[rand.nextInt(cities.length)];
        String char1 = chars[rand.nextInt(chars.length)];
        int num = rand.nextInt(9999);

        return String.format("%s-%s%d %04d", city, char1, rand.nextInt(9), num);
    }

    public static Vehicle createRandomVehicle() {
        String plate = generatePlate();

        int r = rand.nextInt(4);
        if (r == 0) return new SuperCar(plate);
        if (r == 1) return new Motocycle(plate);
        if (r == 2) return new Truck(plate);
        return new Bicycle(plate);
    }
}
