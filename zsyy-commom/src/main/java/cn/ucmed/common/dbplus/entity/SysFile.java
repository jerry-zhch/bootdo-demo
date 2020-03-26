package cn.ucmed.common.dbplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author zhch
 * @since 2020-03-26
 */
@TableName("sys_file")
@ApiModel(value="SysFile对象", description="文件上传")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件类型")
    private Integer type;

    @ApiModelProperty(value = "URL地址")
    private String url;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SysFile{" +
        "id=" + id +
        ", type=" + type +
        ", url=" + url +
        ", createDate=" + createDate +
        "}";
    }
}
