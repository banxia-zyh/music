package com.banxia.music.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 歌手
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Singer对象", description="歌手")
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "歌手名字")
    private String name;

    @ApiModelProperty(value = "歌手性别（1男0女）")
    private Integer sex;

    @ApiModelProperty(value = "头象")
    private String pic;

    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @ApiModelProperty(value = "所属地区")
    private String location;

    @ApiModelProperty(value = "简介")
    private String introduction;


}
