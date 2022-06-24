package Model.DataPool;

public class SoldVehiclePool extends DataRecordPool {
    private static final SoldVehiclePool instance = new SoldVehiclePool();

    public SoldVehiclePool() {
        super(null);
    }

    public static SoldVehiclePool get() {
        return instance;
    }
}
