package tech.chengw.www.mapstruct;

import javax.annotation.Generated;
import tech.chengw.www.entity.po.Task;
import tech.chengw.www.entity.vo.SyncTaskVO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-11T11:55:15+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class TaskMapStructMapperImpl implements TaskMapStructMapper {

    @Override
    public Task taskVO2PO(SyncTaskVO taskVO) {
        if ( taskVO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskVO.getId() );
        task.setTaskName( taskVO.getTaskName() );
        task.setTaskPriority( taskVO.getTaskPriority() );
        task.setTaskState( taskVO.getTaskState() );
        task.setTaskDescription( taskVO.getTaskDescription() );
        task.setTaskDate( taskVO.getTaskDate() );

        return task;
    }

    @Override
    public SyncTaskVO taskPO2VO(Task taskPO) {
        if ( taskPO == null ) {
            return null;
        }

        SyncTaskVO syncTaskVO = new SyncTaskVO();

        syncTaskVO.setId( taskPO.getId() );
        syncTaskVO.setTaskName( taskPO.getTaskName() );
        syncTaskVO.setTaskPriority( taskPO.getTaskPriority() );
        syncTaskVO.setTaskState( taskPO.getTaskState() );
        syncTaskVO.setTaskDescription( taskPO.getTaskDescription() );
        syncTaskVO.setTaskDate( taskPO.getTaskDate() );

        return syncTaskVO;
    }
}
