package tech.chengw.www.service;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.chengw.www.config.InfluxDBConfiguration;
import tech.chengw.www.entity.DataChangeRecord;

import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/10
 **/
@Service
public class RecordService {
    @Autowired
    private InfluxDBConfiguration influxConfig;
    private InfluxDB influxDB;
    /**
     * 记录数据库变更到influxDB
     * @param record
     */
    public void record(DataChangeRecord record) {
        Point point = Point.measurementByPOJO(DataChangeRecord.class)
                .addFieldsFromPOJO(record)
                .build();
        getInfluxDB().write(point);
    }


    /**
     * 连接数据库 ，若不存在则创建
     *
     * @return influxDb实例
     */
    private InfluxDB getInfluxDB() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(influxConfig.getUrl(), influxConfig.getUser(), influxConfig.getPassword());
            influxDB.setDatabase(influxConfig.getDatabase());
        }
        return influxDB;
    }
}
