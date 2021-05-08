package com.banxia.music.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 收藏
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Collect对象", description="收藏")
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "收藏类型（0歌曲1歌单）")
    private Byte type;

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌单id")
    private Integer songListId;

    @ApiModelProperty(value = "收藏时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

}
