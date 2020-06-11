package tech.chengw.www.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.chengw.www.entity.po.Task;
import tech.chengw.www.entity.vo.SyncTaskVO;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/11
 **/
@Mapper
public interface TaskMapStructMapper {

    TaskMapStructMapper INSTANCE = Mappers.getMapper(TaskMapStructMapper.class);

    Task taskVO2PO(SyncTaskVO taskVO);

}
