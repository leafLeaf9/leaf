package com.gousade.controller;

import com.gousade.service.impl.InfluxDBQueryService;
import com.gousade.service.impl.InfluxDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/influx")
public class InfluxDBController {

    @Autowired
    private InfluxDBService influxDBService;

    @Autowired
    private InfluxDBQueryService influxDBQueryService;

    @PostMapping("/write")
    public String writeData(@RequestParam String measurement, @RequestParam double value) {
        influxDBService.writeData(measurement, value);
        return "Data written to InfluxDB successfully!";
    }


    @GetMapping("/query")
    public List<String> queryData(@RequestParam String measurement) {
        return influxDBQueryService.queryData(measurement);
    }
}
