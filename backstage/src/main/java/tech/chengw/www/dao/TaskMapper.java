package tech.chengw.www.dao;

import org.apache.ibatis.annotations.Mapper;
import tech.chengw.www.entity.po.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2020-05-11
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    List<Task> selectAll();
}
