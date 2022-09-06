package org.example.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.example.utils.id.SnowFlakeFactory;
import org.example.utils.psswd.PasswordUtils;

import java.io.Serializable;

/**
 * @description 用户实体类
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -7032479567987350240L;

    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String username;

    /**
     * 密码
     */
    @TableField("t_password")
    private String password;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;

    public User(){//无参新建用户时随机生成id、密码
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
        //默认加密后密码
        this.password = PasswordUtils.encryptPassword("123456");
    }
}

