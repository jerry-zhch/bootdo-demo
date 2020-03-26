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
 * 文章内容
 * </p>
 *
 * @author zhch
 * @since 2020-03-25
 */
@TableName("blog_content")
@ApiModel(value="BlogContent对象", description="文章内容")
public class BlogContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    @ApiModelProperty(value = "标题")
    private String title;

    private String slug;

    @ApiModelProperty(value = "创建人id")
    private Long created;

    @ApiModelProperty(value = "最近修改人id")
    private Long modified;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "分类")
    private String categories;

    private Integer hits;

    @ApiModelProperty(value = "评论数量")
    private Integer commentsNum;

    @ApiModelProperty(value = "开启评论")
    private Integer allowComment;

    @ApiModelProperty(value = "允许ping")
    private Integer allowPing;

    @ApiModelProperty(value = "允许反馈")
    private Integer allowFeed;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "创建时间")
    private Date gtmCreate;

    @ApiModelProperty(value = "修改时间")
    private Date gtmModified;


    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(Integer allowPing) {
        this.allowPing = allowPing;
    }

    public Integer getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(Integer allowFeed) {
        this.allowFeed = allowFeed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getGtmCreate() {
        return gtmCreate;
    }

    public void setGtmCreate(Date gtmCreate) {
        this.gtmCreate = gtmCreate;
    }

    public Date getGtmModified() {
        return gtmModified;
    }

    public void setGtmModified(Date gtmModified) {
        this.gtmModified = gtmModified;
    }

    @Override
    public String toString() {
        return "BlogContent{" +
        "cid=" + cid +
        ", title=" + title +
        ", slug=" + slug +
        ", created=" + created +
        ", modified=" + modified +
        ", content=" + content +
        ", type=" + type +
        ", tags=" + tags +
        ", categories=" + categories +
        ", hits=" + hits +
        ", commentsNum=" + commentsNum +
        ", allowComment=" + allowComment +
        ", allowPing=" + allowPing +
        ", allowFeed=" + allowFeed +
        ", status=" + status +
        ", author=" + author +
        ", gtmCreate=" + gtmCreate +
        ", gtmModified=" + gtmModified +
        "}";
    }
}
