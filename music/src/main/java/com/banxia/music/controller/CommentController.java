package com.banxia.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.banxia.music.pojo.Comment;
import com.banxia.music.service.CommentService;
import com.banxia.music.utils.Constant;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           //用户id
        String type = request.getParameter("type");               //评论类型（0歌曲1歌单）
        String songId = request.getParameter("songId");           //歌曲id
        String songListId = request.getParameter("songListId");   //歌单id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(new Byte(type) ==0){
            comment.setSongId(Integer.parseInt(songId));
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.save(comment);
        if(flag){   //保存成功
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"评论成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();                   //主键
        String userId = request.getParameter("userId").trim();           //用户id
        String type = request.getParameter("type").trim();               //评论类型（0歌曲1歌单）
        String songId = request.getParameter("songId").trim();           //歌曲id
        String songListId = request.getParameter("songListId").trim();   //歌单id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(songId!=null&&songId.equals("")){
            songId = null;
        }else {
            comment.setSongId(Integer.parseInt(songId));
        }
        if(songListId!=null&&songListId.equals("")){
            songListId = null;
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);

        boolean flag = commentService.saveOrUpdate(comment);
        if(flag){   //保存成功
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 删除评论
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        boolean flag = commentService.removeById(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();          //主键
        return commentService.getById(Integer.parseInt(id));
    }

    /**
     * 查询所有评论
     */
    @RequestMapping(value = "/allComment",method = RequestMethod.GET)
    public Object allComment(HttpServletRequest request){
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.select("content");
        return commentService.list();
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @RequestMapping(value = "/commentOfSongId",method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId");          //歌曲id

        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id",songId);


        return commentService.list(wrapper);
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @RequestMapping(value = "/commentOfSongListId",method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");          //歌曲id
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id",songListId);

        return commentService.list(wrapper);
    }

    /**
     * 给某个评论点赞
     */
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Object like(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();           //主键
        String up = request.getParameter("up").trim();           //用户id

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean flag = commentService.saveOrUpdate(comment);
        if(flag){   //保存成功
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"点赞成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"点赞失败");
        return jsonObject;
    }
}

