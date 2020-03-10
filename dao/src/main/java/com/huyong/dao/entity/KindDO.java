package com.huyong.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述: 分类
 *
 * @author huyong
 * @date 2020-03-10 9:04 下午
 */
@Data
public class KindDO implements Serializable {
    private static final long serialVersionUID = 7573523316588926846L;
    private Long id;
    /**
     * 父级菜单
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String kindName;
    /**
     * 创建者
     */
    private Long userId;
    /**
     * 修改者
     */
    private Long updateUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除/存在
     */
    private Integer status;
}
