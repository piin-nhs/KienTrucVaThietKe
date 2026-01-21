public class Truck extends Vehicle {

    public Truck(String plate) {
        super(plate, "TRUCK");
    }

    @Override
    public String toJson() {
        return "{"
                + "\"type\":\"" + type + "\","
                + "\"plate\":\"" + plate + "\""
                + "}";
    }
}
