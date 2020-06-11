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
     * 获取自上次同步以来的变更
     * @param userId
     * @param lastTime
     * @return
     */
    public List<DataChangeRecord> getRecord(Integer userId, Date lastTime) {
        // 此处查询所有内容,如果
        String queryCmd = "SELECT * FROM \""
                + influxConfig.getDatabase()
                + "\" WHERE user_id = '" + userId + "' AND time > "+lastTime.getTime()+"ms";
        QueryResult result = getInfluxDB().query(new Query(queryCmd));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<DataChangeRecord> records = resultMapper.toPOJO(result, DataChangeRecord.class);
        return records;
    }

    /**
     * 连接数据库 ，若不存在则创建
     *
     * @return influxDb实例
     */
    private InfluxDB getInfluxDB() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(influxConfig.getUrl(), influxConfig.getUser(), influxConfig.getPassword())
                    .setDatabase(influxConfig.getDatabase());
        }
        return influxDB;
    }
}
