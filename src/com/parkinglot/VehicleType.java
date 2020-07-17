package com.parkinglot;

public enum VehicleType {
    LIGHT_MOTOR_VEHICLE("light"),
    HEAVY_MOTOR_VEHCILE("heavy"),
    ANY("any");

    public final String commandStr;

    public static VehicleType valueOfCommandStr(String commandStr) {
        for (VehicleType vehicleType : values()) {
            if (vehicleType.commandStr.equals(commandStr)) {
                return vehicleType;
            }
        }
        return null;
    }

    private VehicleType(String commandStr) {
        this.commandStr = commandStr;
    }

}
