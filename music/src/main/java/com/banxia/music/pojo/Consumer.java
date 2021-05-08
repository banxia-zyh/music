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
 * 前端用户表
 * </p>
 *
 * @author zhāngyùnhǎo
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Consumer对象", description="前端用户表")
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别（1男0女）")
    private Integer sex;

    @ApiModelProperty(value = "手机")
    private String phoneNum;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @ApiModelProperty(value = "签名")
    private String introduction;

    @ApiModelProperty(value = "地区")
    private String location;

    @ApiModelProperty(value = "头像")
    private String avator;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
