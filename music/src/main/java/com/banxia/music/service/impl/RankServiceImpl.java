package com.banxia.music.service.impl;

import com.banxia.music.pojo.Rank;
import com.banxia.music.mapper.RankMapper;
import com.banxia.music.service.RankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评价 服务实现类
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Service
public class RankServiceImpl extends ServiceImpl<RankMapper, Rank> implements RankService {

    @Autowired
    private RankMapper rankMapper;



    /**
     * 查总评分人数
     *
     * @param songListId
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum = rankMapper.selectRankNum(songListId);
        if(rankNum==0){
            return 5;
        }
        return rankMapper.selectScoreSum(songListId)/rankNum;
    }
}
