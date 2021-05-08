package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.pojo.Collect;
import com.banxia.music.service.CollectService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService CollectService;

    QueryWrapper<Collect> wrapper = new QueryWrapper<>();

    /**
     * 添加收藏
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           //用户id
        String type = request.getParameter("type");               //收藏类型（0歌曲1歌单）
        String songId = request.getParameter("songId");           //歌曲id
        if(songId==null||songId.equals("")){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"收藏歌曲为空");
            return jsonObject;
        }

        wrapper.eq("user_Id",userId);
        wrapper.eq("song_id",songId);
        if(!CollectService.list(wrapper).isEmpty()){
            jsonObject.put(Constant.CODE,2);
            jsonObject.put(Constant.MSG,"已收藏");
            return jsonObject;
        }

        //保存到收藏的对象中
        Collect Collect = new Collect();
        Collect.setUserId(Integer.parseInt(userId));
        Collect.setType(new Byte(type));
        Collect.setSongId(Integer.parseInt(songId));

        boolean flag = CollectService.save(Collect);
        if(flag){   //保存成功
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id

        wrapper.eq("user_id",userId);
        wrapper.eq("song_id",songId);

        return CollectService.remove(wrapper);
    }

    /**
     * 查询所有收藏
     */
    @RequestMapping(value = "/allCollect",method = RequestMethod.GET)
    public Object allCollect(){

        System.out.println("====================="+CollectService.list()+"===========");
        return CollectService.list();
    }

    /**
     * 查询某个用户的收藏列表
     */
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");          //用户id
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return CollectService.list(wrapper);
    }




}

