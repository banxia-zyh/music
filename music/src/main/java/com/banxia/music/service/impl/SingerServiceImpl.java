package com.banxia.music.service.impl;

import com.banxia.music.pojo.Singer;
import com.banxia.music.mapper.SingerMapper;
import com.banxia.music.service.SingerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 歌手 服务实现类
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {


}
