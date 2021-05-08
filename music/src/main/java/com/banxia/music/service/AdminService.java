package com.banxia.music.service;

import com.banxia.music.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
public interface AdminService extends IService<Admin> {
    public boolean verifyPassword(String name, String password);
}
