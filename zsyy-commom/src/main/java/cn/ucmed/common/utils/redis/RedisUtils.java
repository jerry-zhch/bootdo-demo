package cn.ucmed.common.utils.redis;

import cn.ucmed.common.db.basic.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 */
@Component
@Slf4j
public class RedisUtils {
    private static final String POINT = ":";

    private static final String MSG_ERROR_DELETE_REDIS =
            "Delete redisClient error";

    private static final String MSG_ERROR_GET_REDIS =
            "Get redisClient error";

    private static final String MSG_ERROR_PUT_REDIS =
            "Set redisClient error";

    /**
     * 30 mins * 60 seconds
     */
    public static final Integer SESSION_EXP_DEFAULT = 30 * 60;

    @Autowired
    private RedisTemplate  redisTemplate;

    @Autowired
    private RedisTemplate redisTemplateIncr;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.redis.projectName}")
    private String projectName;

    /**
     * 放入缓存30分钟
     */
    public <T extends Cache> boolean set(T value) {
        try {
            redisTemplate.opsForValue().set(getProjectNameKey(value.getKey()), value, SESSION_EXP_DEFAULT, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error(MSG_ERROR_PUT_REDIS, e);
        }
        return false;
    }

    /**
     * 放入缓存，自定义事件，单位分钟
     */
    public <T extends Cache> boolean set(T value, long timeSeconds) {
        try {
            redisTemplate.opsForValue().set(getProjectNameKey(value.getKey()), value, timeSeconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error(MSG_ERROR_PUT_REDIS, e);
        }
        return false;
    }

    /**
     * 放入缓存，自定义时间
     */
    public <T extends Cache> boolean set(T value, long timeout, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(getProjectNameKey(value.getKey()), value, timeout, timeUnit);
            return true;
        } catch (Exception e) {
            log.error(MSG_ERROR_PUT_REDIS, e);
        }
        return false;
    }

    /**
     * 删除缓存
     */
    public Boolean remove(Object key) {
        try {
            redisTemplate.delete(getProjectNameKey(key));
            return true;
        } catch (Exception e) {
            log.error(MSG_ERROR_DELETE_REDIS, e);
        }
        return false;
    }

    /**
     * 获取缓存信息
     */
    public <T extends Cache> T get(Object key) {
        try {
            if (key != null) {
                return (T) redisTemplate.opsForValue().get(getProjectNameKey(key));
            }
        } catch (Exception e) {
            log.error(MSG_ERROR_GET_REDIS, e);
        }
        return null;
    }


    /**
     * 获取缓存信息
     */
    public String getString(Object key) {
        try {
            if (key != null) {
                return (String) redisTemplate.opsForValue().get(getProjectNameKey(key));
            }
        } catch (Exception e) {
            log.error(MSG_ERROR_GET_REDIS, e);
        }
        return null;
    }

    /**
     * 获取换成信息（通过token获取小程序用户信息专用）
     */
    public String getStringNoProjectNameKey(Object key) {
        try {
            if (key != null) {
                return (String) redisTemplate.opsForValue().get(key);
            }
        } catch (Exception e) {
            log.error(MSG_ERROR_GET_REDIS, e);
        }
        return null;
    }


    /**
     * 放入缓存，自定义时间
     */
    public String getSetString(String key, String value, long timeout, TimeUnit timeUnit) {
        try {
            if (key != null) {
                String temp = (String) redisTemplate.opsForValue().get(getProjectNameKey(key));
                redisTemplate.opsForValue().set(getProjectNameKey(key), value, timeout, timeUnit);
                return temp;
            }
        } catch (Exception e) {
            log.error(MSG_ERROR_PUT_REDIS, e);
        }
        return null;
    }

    public Integer getSetInteger(String key, Integer value, long timeout, TimeUnit timeUnit) {
        return getSetIntegerOrigin(getProjectNameKey(key), value, timeout, timeUnit);
    }


    /**
     * 未封装key
     */
    public Integer getSetIntegerOrigin(String key, Integer value, long timeout, TimeUnit timeUnit) {
        try {
            if (key != null) {
                Integer temp = (Integer) redisTemplate.opsForValue().get(key);
                redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
                return temp;
            }
        } catch (Exception e) {
            log.error(MSG_ERROR_PUT_REDIS, e);
        }
        return null;
    }


    /**
     * 给缓存增加一个目录
     */
    public String getProjectNameKey(Object key) {
        return projectName + POINT + key;
    }


    /**
     * 自增计数
     */
    public Long incr(String key,long liveTime,TimeUnit timeUnit) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(getProjectNameKey(key), redisTemplateIncr.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, timeUnit);
        }
        return increment;
    }


    public Long addAndGet(String key, Long number, long liveTime, TimeUnit timeUnit) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(getProjectNameKey(key), redisTemplateIncr.getConnectionFactory());
        try {
            Long increment = entityIdCounter.longValue();
            return entityIdCounter.addAndGet(number);
        } catch (Exception e) {
            entityIdCounter.expire(liveTime, timeUnit);
            entityIdCounter.set(number);
            return number;
        }

    }

    /**
     * 获取一定
     */
    public Set<String> keys(String keys) {
        return stringRedisTemplate.keys(getProjectNameKey(keys + "*"));
    }

    /**
     * Atomically increments by one the current value.以原子方式将当前值加 1，并返回加1后的值
     */
    public long generate(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }
}
