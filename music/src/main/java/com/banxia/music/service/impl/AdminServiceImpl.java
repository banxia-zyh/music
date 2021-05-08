package com.banxia.music.service.impl;

import com.banxia.music.pojo.Admin;
import com.banxia.music.mapper.AdminMapper;
import com.banxia.music.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public boolean verifyPassword(String name, String password){

        QueryWrapper<Admin> wrapper = new QueryWrapper<>();

        wrapper.eq("name",name);
        wrapper.eq("password",password);

        return adminMapper.selectCount(wrapper) > 0;

    }

}
