package cn.cbr.usermanagesystem.service;
import cn.cbr.usermanagesystem.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface UserService {
    User getById(Long id);
    Page<User> page(int current, int size);
    void add(User user);
    void updateById(User user);
    void deleteById(Long id);
}
