package com.banxia.music.service.impl;

import com.banxia.music.pojo.Comment;
import com.banxia.music.mapper.CommentMapper;
import com.banxia.music.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
