package tech.chengw.www.scheduler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;


import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.google.protobuf.InvalidProtocolBufferException;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.chengw.www.config.CanalSeverConfiguration;
import tech.chengw.www.entity.DataChangeRecord;
import tech.chengw.www.service.RecordService;


/**
 * @author chengw
 */
@Component
public class MysqlLogSyncScheduler {

    @Autowired
    private CanalSeverConfiguration canalSeverConfiguration;
    @Autowired
    private RecordService recordService;

    Logger logger = LoggerFactory.getLogger(MysqlLogSyncScheduler.class);

    private CanalConnector connector;

    private final int batchSize = 1000;

    /**
     * 初始化canal连接
     */
    private void init() {
        if(connector == null) {
            connector = CanalConnectors.newSingleConnector(new InetSocketAddress(canalSeverConfiguration.getIp(),canalSeverConfiguration.getPort()), canalSeverConfiguration.getDestination(), canalSeverConfiguration.getUserName(), canalSeverConfiguration.getPassword());
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            return;
        }
        connector.connect();
        connector.rollback();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void sync() {
        // 初始化
        init();

        // 获取指定数量的数据
        Message message = connector.getWithoutAck(batchSize);
        long batchId = message.getId();
        if (batchId == -1 || message.getEntries().size() == 0) {
            return;
        }
        try {
            //记录
            record(message.getEntries());
        } catch (Exception e) {
            e.printStackTrace();
            // 处理失败, 回滚数据
            connector.rollback(batchId);
            return;
        }
        // 提交确认
        connector.ack(batchId);
    }

    /**
     * 记录数据库变动
     * @param entries
     */
    private void record(List<Entry> entries) {
        entries.forEach(entry -> {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                return;
            }

            RowChange rowChange = null;
            try {
                rowChange = RowChange.parseFrom(entry.getStoreValue());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
                return;
            }
            //变更类型
            EventType changeType = rowChange.getEventType();
            //变更数据的表名
            String tableName = entry.getHeader().getTableName();
            //只记录task表的变更
            if(!tableName.equals("task")) {
                return;
            }
            rowChange.getRowDatasList().forEach(rowData -> {
                DataChangeRecord record = new DataChangeRecord()
                        .setType(changeType.getNumber())
                        .setTableName(tableName);
                //delete型使用变更前的数据行，其他使用变更后的数据行
                List<Column> columns = Objects.equals(changeType, EventType.DELETE) ? rowData.getBeforeColumnsList() : rowData.getAfterColumnsList();
                //提取id和userId
                columns.forEach(column -> {
                    if(column.getName().equals("id")) {
                        record.setId(Integer.valueOf(column.getValue()));
                    }
                    if(column.getName().equals("user_id")) {
                        record.setUserId(column.getValue());
                    }
                });
                recordService.record(record);
            });
        });

    }



}