package tech.chengw.www.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/20
 **/
@Data
@Accessors(chain = true)
@Measurement(name = "data-change-record")
public class DataChangeRecord {
    @Column(name = "id")
    private Integer id;
    @Column(name = "table_name",tag = true)
    private String tableName;
    @Column(name = "type")
    private Integer type;
    @Column(name = "user_id",tag = true)
    private String userId;
}
