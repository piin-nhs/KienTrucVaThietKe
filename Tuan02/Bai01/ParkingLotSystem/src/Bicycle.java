public class Bicycle extends Vehicle {

    public Bicycle(String plate) {
        super(plate, "BICYCLE");
    }

    @Override
    public String toJson() {
        return "{"
                + "\"type\":\"" + type + "\","
                + "\"plate\":\"" + plate + "\""
                + "}";
    }
}
