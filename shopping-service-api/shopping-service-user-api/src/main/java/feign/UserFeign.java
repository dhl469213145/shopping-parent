package feign;

import api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.entity.UserEntity;

@FeignClient(name = "user")
@RequestMapping("/user")
public interface UserFeign {
    @GetMapping("/load/{id}")
    public R<UserEntity> findByUsername(@PathVariable(name = "id") String id);


    /**
     * 添加积分
     *
     * @param points
     * @param username
     * @return
     */
    @GetMapping(value = "/points/add")
    public R addPoints(@RequestParam(value = "points") Integer points, @RequestParam(value = "username") String username);


}
