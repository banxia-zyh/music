package com.banxia.music.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 歌曲
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Song对象", description="歌曲")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "歌手id")
    private Integer singerId;

    @ApiModelProperty(value = "歌名")
    private String name;

    @ApiModelProperty(value = "歌曲简介")
    private String introduction;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "歌曲图片")
    private String pic;

    @ApiModelProperty(value = "歌词")
    private String lyric;

    @ApiModelProperty(value = "歌曲地址")
    private String url;


}
