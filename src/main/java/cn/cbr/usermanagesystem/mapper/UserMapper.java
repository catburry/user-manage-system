package cn.cbr.usermanagesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.cbr.usermanagesystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
