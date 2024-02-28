package com.redis.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
public class RedisUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setStringData(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public void setStringData(String key, String value, Duration duration){
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public String getStringData(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    public <T> boolean setObjectData(String key, T data) {
        try {
            String value = objectMapper.writeValueAsString(data);

            setStringData(key, value);

            return true;
        } catch(Exception ex) {
            // TODO - 어떻게 처리할 것인지 throw 후 호출부에서 catch 잡아서 처리?
            log.error("Redis Exception = {}", ex);
            return false;
        }
    }

    public <T> Optional<T> getObjectData(String key, TypeReference<T> clazz) {
        try {
            String value = (String) redisTemplate.opsForValue().get(key);

            if(value == null) {
                return Optional.empty();
            }
            return Optional.of(objectMapper.readValue(value, clazz));
        } catch(Exception ex) {
            log.error("Redis Exception = {}", ex);
            return Optional.empty();
        }
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }
}