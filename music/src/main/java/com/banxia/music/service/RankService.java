package com.banxia.music.service;

import com.banxia.music.pojo.Rank;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评价 服务类
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
public interface RankService extends IService<Rank> {


    /**
     * 查总评分人数
     */
    public int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     */
    public int rankOfSongListId(Integer songListId);

}
