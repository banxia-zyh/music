package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.mapper.AdminMapper;
import com.banxia.music.pojo.Admin;
import com.banxia.music.service.AdminService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController

public class AdminController {


    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){

        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        wrapper.eq("password",password);
        List<Admin> list = adminService.list(wrapper);

        //boolean flag = adminService.verifyPassword(name,password);
        if(list != null){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"登录成功");
            session.setAttribute(Constant.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"用户名或密码错误");
        return jsonObject;
    }


}

