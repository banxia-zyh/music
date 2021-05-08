package com.banxia.music.mapper;

import com.banxia.music.pojo.Rank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评价 Mapper 接口
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Mapper
public interface RankMapper extends BaseMapper<Rank> {

    /**
     * 查总分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    public int selectRankNum(Integer songListId);

}
