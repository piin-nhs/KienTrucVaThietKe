public class ParkingLot {
    private static ParkingLot instance;
    private Vehicle[] slots;

    private ParkingLot() {
        slots = new Vehicle[12];
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) instance = new ParkingLot();
        return instance;
    }

    public int park(Vehicle v) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                slots[i] = v;
                return i;
            }
        }
        return -1;
    }

    public Vehicle[] getSlots() {
        return slots;
    }

    public int getCapacity() {
        return slots.length;
    }


    public void leave(int index) {
        if (index >= 0 && index < slots.length) slots[index] = null;
    }
}