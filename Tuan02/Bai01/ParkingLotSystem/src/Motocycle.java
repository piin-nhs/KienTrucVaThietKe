public class Motocycle extends Vehicle {

    public Motocycle(String plate) {
        super(plate, "MOTOCYCLE");
    }

    @Override
    public String toJson() {
        return "{"
                + "\"type\":\"" + type + "\","
                + "\"plate\":\"" + plate + "\""
                + "}";
    }
}
