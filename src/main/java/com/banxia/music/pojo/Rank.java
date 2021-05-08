package com.banxia.music.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评价
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Rank对象", description="评价")
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "歌单id")
    private Integer songListId;

    @ApiModelProperty(value = "用户id")
    private Integer consumerId;

    @ApiModelProperty(value = "评分")
    private Integer score;


}
