package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.pojo.Rank;
import com.banxia.music.service.RankService;
import com.banxia.music.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评价 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 新增评价
     * @param rank
     * @return
     */
    @RequestMapping(value = "/rank/add",method = RequestMethod.POST)
    public Object add(Rank rank){
        JSONObject jsonObject = new JSONObject();

        boolean flag = rankService.save(rank);

        if (flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"评价成功");
        }else {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"评价失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public Object rankOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return rankService.rankOfSongListId(Integer.parseInt(songListId));
    }

}

