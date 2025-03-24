package com.gousade.service.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class InfluxDBService {

    @Autowired
    private InfluxDBClient influxDBClient;

    public void writeData(String measurement, double value) {
        try (WriteApi writeApi = influxDBClient.getWriteApi()) {
            Point point = Point.measurement(measurement)
                    .addField("value", value)
                    .time(Instant.now(), WritePrecision.NS);
            writeApi.writePoint(point);
        }
    }
}
