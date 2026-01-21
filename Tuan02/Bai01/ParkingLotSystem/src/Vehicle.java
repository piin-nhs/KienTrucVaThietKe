public abstract class Vehicle {
    protected String plate;
    protected String type;

    public Vehicle(String plate, String type) {
        this.plate = plate;
        this.type = type;
    }

    public String getPlate() { return plate; }
    public String getType() { return type; }

    public abstract String toJson();
}