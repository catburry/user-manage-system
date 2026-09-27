package cn.cbr.usermanagesystem.service.impl;

import cn.cbr.usermanagesystem.entity.User;
import cn.cbr.usermanagesystem.mapper.UserMapper;
import cn.cbr.usermanagesystem.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        //参数校验
        if (id==null||id<=0){
            throw new RuntimeException("id不能为空");
        }
        //调用mapper
        User user=userMapper.selectById(id);
        //重复检验
        if(user==null){
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    @Override
    public Page<User> page(int current, int size) {
        //参数校验
        if (current<=0)current=1;
        if (size<=0)size=10;
        if(size>100)size=100;
        //创建分页对象
        Page<User> page=new Page<>(current,size);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //调用mapper
        userMapper.selectPage(page,wrapper);
        return page;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void add(User user) {
        //参数校验
        if (user.getUserName()==null||user.getUserName().isEmpty()){
            throw new RuntimeException("用户名不能为空");
        }
        if (user.getPassword()==null||user.getPassword().isEmpty()){
            throw new RuntimeException("密码不能为空");
        }
        //业务判断
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        User existUser=userMapper.selectOne(wrapper);
        if (existUser!=null){
            throw new RuntimeException("用户已存在");
        }
        //数据预处理
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        //调用mapper
        userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void updateById(User user) {
        //参数判断
        if (user.getId()==null||user.getId()<=0){
            throw new RuntimeException("id不能为空");
        }
        if (user.getUserName()==null||user.getUserName().isEmpty()){
            throw new RuntimeException("用户名不能为空");
        }
        //业务判断
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName()).ne("id",user.getId());
        User existUser=userMapper.selectOne(wrapper);
        if(existUser!=null){
            throw new RuntimeException("用户已存在");
        }
        user.setUpdateTime(LocalDateTime.now());
        //调用mapper
        int rows=userMapper.updateById(user);
        if (rows==0){
            throw new RuntimeException("用户不存在，修改失败");
        }


    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteById(Long id) {
        //参数判断
        if (id==null||id<=0){
            throw new RuntimeException("id不能为空");
        }
        //调用mapper
        int rows=userMapper.deleteById(id);
        if (rows==0){
            throw new RuntimeException("用户不存在，删除失败");
        }

    }
}
