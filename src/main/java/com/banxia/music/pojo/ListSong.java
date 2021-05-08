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
 * 歌单包含歌曲列表
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ListSong对象", description="歌单包含歌曲列表")
public class ListSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌单id")
    private Integer songListId;


}
