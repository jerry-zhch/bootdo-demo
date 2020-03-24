package cn.ucmed.common.db.news.service.impl;

import cn.ucmed.common.db.news.entity.Notice;
import cn.ucmed.common.db.news.mapper.NoticeMapper;
import cn.ucmed.common.db.news.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther 郄彦腾
 * @create 2019-02-26 13:38:49
 * @describe 服务实现类
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
