package cn.ep.spring.boot.ch02.s09;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisRepository implements InitializingBean {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private ObjectMapper objectMapper;

    public <T> boolean set(String key, T value) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            StringRedisSerializer keySerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
            GenericJackson2JsonRedisSerializer valueSerializer = (GenericJackson2JsonRedisSerializer) redisTemplate.getValueSerializer();
            connection.set(keySerializer.serialize(key), valueSerializer.serialize(value));
            return true;
        });
    }

    public <T> T get(String key, Class<T> clz) {
        return redisTemplate.execute((RedisCallback<T>) connection -> {
            StringRedisSerializer keySerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
            GenericJackson2JsonRedisSerializer valueSerializer = (GenericJackson2JsonRedisSerializer) redisTemplate.getValueSerializer();
            byte[] value = connection.get(keySerializer.serialize(key));
            return (T) valueSerializer.deserialize(value);
        });
    }

    private boolean setStr(String key, String value) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            return true;
        });
    }

    private String getStr(String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] value = connection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
    }

    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public <T> boolean setList(String key, List<T> list) throws IOException {
        String value = objectMapper.writeValueAsString(list);
        return setStr(key, value);
    }

    public <T> List<T> getList(String key, Class<T> clz) throws IOException {
        String json = getStr(key);
        return objectMapper.readValue(json, getCollectionType(ArrayList.class, clz));
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     */
    private JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public long lpush(String key, Object obj) throws IOException {
        final String value = objectMapper.writeValueAsString(obj);
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return connection.lPush(serializer.serialize(key), serializer.serialize(value));
        });
    }

    public long rpush(String key, Object obj) throws IOException {
        final String value = objectMapper.writeValueAsString(obj);
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return connection.rPush(serializer.serialize(key), serializer.serialize(value));
        });
    }

    public String lpop(String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] res = connection.lPop(serializer.serialize(key));
            return serializer.deserialize(res);
        });
    }

    @Override
    public void afterPropertiesSet() {
        objectMapper = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }
}
