public class SuperCar extends Vehicle {

    public SuperCar(String plate) {
        super(plate, "SUPER_CAR");
    }

    @Override
    public String toJson() {
        return "{"
                + "\"type\":\"" + type + "\","
                + "\"plate\":\"" + plate + "\""
                + "}";
    }
}
