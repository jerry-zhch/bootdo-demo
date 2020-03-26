package cn.ucmed.common.dbplus.service.impl;

import cn.ucmed.common.dbplus.entity.BlogContent;
import cn.ucmed.common.dbplus.mapper.BlogContentMapper;
import cn.ucmed.common.dbplus.service.IBlogContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章内容 服务实现类
 * </p>
 *
 * @author zhch
 * @since 2020-03-25
 */
@Service
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContent> implements IBlogContentService {

}
