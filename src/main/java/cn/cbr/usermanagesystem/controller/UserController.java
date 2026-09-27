package cn.cbr.usermanagesystem.controller;

import cn.cbr.usermanagesystem.common.Result;
import cn.cbr.usermanagesystem.service.UserService;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.cbr.usermanagesystem.entity.User;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 测试登录，浏览器访问： http://localhost:8080/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Result doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return Result.success(tokenInfo);
        }
        return Result.error("登录失败");
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/user/isLogin
    @RequestMapping("isLogin")
    public Result isLogin() {
        return Result.success("当前会话是否登录：" + StpUtil.isLogin());
    }


    /**
     * 查询单个用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id){
        User user=userService.getById(id);
        return Result.success(user);
    }

    /**
     * 分页查询
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/page")
    public Result<Page<User>> page(@RequestParam(defaultValue="1") int current ,@RequestParam(defaultValue = "10") int size){
        Page<User> page=userService.page(current,size);
        return Result.success(page);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public Result<?> add(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping
    public Result<Void> updateById(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return Result.success();
    }
}
