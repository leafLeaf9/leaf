package com.gousade.service.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfluxDBQueryService {

    @Autowired
    private InfluxDBClient influxDBClient;

    public List<String> queryData(String measurement) {
        String query = "from(bucket: \"my-bucket\") |> range(start: -1h) |> filter(fn: (r) => r._measurement == \"" + measurement + "\")";
        QueryApi queryApi = influxDBClient.getQueryApi();
        List<String> results = new ArrayList<>();

        queryApi.query(query, (cancellable, record) -> {
            results.add(record.getValueByKey("_value").toString());
        });

        return results;
    }
}