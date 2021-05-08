package com.banxia.music.mapper;

import com.banxia.music.pojo.ListSong;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 歌单包含歌曲列表 Mapper 接口
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Mapper
public interface ListSongMapper extends BaseMapper<ListSong> {

}
