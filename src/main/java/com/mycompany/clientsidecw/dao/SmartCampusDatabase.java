package com.mycompany.clientsidecw.dao;

import com.mycompany.clientsidecw.model.Room;
import com.mycompany.clientsidecw.model.Sensor;
import com.mycompany.clientsidecw.model.SensorReading;
import java.util.ArrayList;
import java.util.List;

public class SmartCampusDatabase {

    public static final List<Room> ROOMS = new ArrayList<>();
    public static final List<Sensor> SENSORS = new ArrayList<>();
    public static final List<SensorReading> READINGS = new ArrayList<>();

    static {
        Room room1 = new Room("LIB-301", "Library Quiet Study", 50);
        Room room2 = new Room("ENG-101", "Engineering Lab", 30);
        Room room3 = new Room("SCI-202", "Science Lecture Hall", 100);

        room1.getSensorIds().add("TEMP-001");
        room1.getSensorIds().add("CO2-001");
        room2.getSensorIds().add("OCC-001");

        ROOMS.add(room1);
        ROOMS.add(room2);
        ROOMS.add(room3);

        SENSORS.add(new Sensor("TEMP-001", "Temperature", "ACTIVE", 21.5, "LIB-301"));
        SENSORS.add(new Sensor("CO2-001", "CO2", "ACTIVE", 410.0, "LIB-301"));
        SENSORS.add(new Sensor("OCC-001", "Occupancy", "MAINTENANCE", 12.0, "ENG-101"));

        READINGS.add(new SensorReading("R001", System.currentTimeMillis(), 21.5));
        READINGS.add(new SensorReading("R002", System.currentTimeMillis(), 410.0));
        READINGS.add(new SensorReading("R003", System.currentTimeMillis(), 12.0));
    }
}