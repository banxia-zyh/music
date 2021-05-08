package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.pojo.Consumer;
import com.banxia.music.pojo.Song;
import com.banxia.music.service.ConsumerService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端用户表 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object add(Consumer consumer){
        JSONObject jsonObject = new JSONObject();

        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("username",consumer.getUsername());
        List<Consumer> list = consumerService.list(wrapper);

        if (!list.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"用户名已存在");
        }else {

            boolean flag = consumerService.save(consumer);
            if (flag) {
                jsonObject.put(Constant.CODE, 1);
                jsonObject.put(Constant.MSG, "注册成功");

            } else {
                jsonObject.put(Constant.CODE, 0);
                jsonObject.put(Constant.MSG, "添加失败");
            }
        }
        return jsonObject;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object update(Consumer consumer){
        JSONObject jsonObject = new JSONObject();
        boolean flag = consumerService.saveOrUpdate(consumer);
        if (flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"修改失败");
        return jsonObject;
    }

    @RequestMapping(value = "/updateConsumerPic", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        if (avatarFile.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages";
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatarPath = "/avatorImages/"+fileName;

        try {
            avatarFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatarPath);

            boolean flag = consumerService.updateById(consumer);
            if (flag){
                jsonObject.put(Constant.CODE,1);
                jsonObject.put(Constant.MSG,"上传成功");
                jsonObject.put("avator",storeAvatarPath);
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteConsumer(String id){
        return consumerService.removeById(Integer.parseInt(id));
    }

    //@RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id ){
        System.out.println("================="+id+"===================");
        return consumerService.getById(id);
    }

    @RequestMapping(value = "/allConsumer",method = RequestMethod.GET)
    public Object allConsumer(){
        System.out.println(consumerService.list());
        return consumerService.list();
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        if(username == null||username.equals("")){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"用户名不能为空");
            return String.valueOf(jsonObject);
        }
        if(password == null||password.equals("")){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"密码不能为空");
            return String.valueOf(jsonObject);
        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("password",password);
        List<Consumer> list = consumerService.list(wrapper);
        if(!list.isEmpty()){   //验证成功
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"登录成功");
            jsonObject.put("userMsg",list.get(0));
            System.out.println("============"+list+"===========");
            System.out.println("============"+jsonObject+"==============");
            return String.valueOf(jsonObject);
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"用户名或密码错误");
        return String.valueOf(jsonObject);
    }

}




