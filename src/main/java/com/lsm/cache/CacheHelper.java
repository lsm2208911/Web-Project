package com.lsm.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CacheHelper
{
    private static final Logger LOGGER= LoggerFactory.getLogger(CacheHelper.class);
    private static final int defaultSeconds=30*60;//默认保存时间30分钟

    public static void set(EntityType type, String key, Object value){
        set(type.intValue(),key,value);
    }

    public static Object get(EntityType type,String key){
        return get(type.intValue(),key);
    }

    public static void set(EntityType type,String key,int seconds,Object value){
        setAndSeconds(type.intValue(),key,seconds,value);
    }

    public static Object get(String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return jedIs.get(key);
        }
        catch(Exception ex)
        {
            LOGGER.error("get[String key]:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        return null;
    }

    public static void setx(String key,String value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.setex(key,defaultSeconds,value);
        }
        catch(Exception ex)
        {
            LOGGER.error("setx[String key,String value]:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    private static void setAndSeconds(int type,String key,int seconds,Object value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.setex(getCacheKey(type,key),seconds,serialize(value));
        }
        catch(Exception ex)
        {
            LOGGER.error("setAndSeconds:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }

    }

    private static void set(int type,String key,Object value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.setex(getCacheKey(type,key),defaultSeconds,serialize(value));
        }
        catch(Exception ex)
        {
            LOGGER.error("set:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }

    }

    public static void hmset(String key,Map<String,String> value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.hmset(key,value);
        }
        catch(Exception ex)
        {
            LOGGER.error("hmset:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }

    }

    public static void expire(EntityType type,String key,int seconds){
        expire(type.intValue(),key,seconds);
    }

    /**
     * 延续key时间
     * @param key key
     * @param seconds seconds
     */
    private static void expire(int type,String key,int seconds){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.expire(getCacheKey(type,key),seconds);
        }
        catch(Exception ex)
        {
            LOGGER.error("expire:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }

    }

    public static Map<String,String> hgetAll(String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return jedIs.hgetAll(key);
        }
        catch(Exception ex)
        {
            LOGGER.error("hgetAll:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return new HashMap<>();
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }

    }

    public static List<String> hmget(String key,String... value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return jedIs.hmget(key,value);
        }
        catch(Exception ex)
        {
            LOGGER.error("hmget:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return new ArrayList<>();
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    public static boolean hexists(String key,String value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return jedIs.hexists(key,value);
        }
        catch(Exception ex)
        {
            LOGGER.error("hexists:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return false;
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    public static void hdel(String key,String... value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.hdel(key,value);
        }
        catch(Exception ex)
        {
            LOGGER.error("hdel:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    public static void del(EntityType type,String key){
        del(type.intValue(),key);
    }

    private static void del(int type,String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {

            boolean exists=jedIs.exists(getCacheKey(type,key));
            if(exists)
            {
                jedIs.del(getCacheKey(type,key));
            }
        }
        catch(Exception ex)
        {
            LOGGER.error("del:"+ex.getMessage(),ex);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        if(jedIs!=null)
        {
            jedIs.close();
        }
    }

    /**
     * <p>设置key value并制定这个键值的有效期</p>
     * @param key 键
     * @param value 值
     * @param seconds 过期时间
     */
    public static void setex(String key,String value,int seconds){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.setex(key,seconds,value);
        }
        catch(Exception e)
        {
            LOGGER.error("setex:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        if(jedIs!=null)
        {
            jedIs.close();
        }
    }

    /**
     * <p>通过key向list头部添加字符串</p>
     * @param key key
     * @param value calue
     */
    public static void lpush(String key,String... value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.lpush(key,value);
        }
        catch(Exception e)
        {
            LOGGER.error("lpush:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    public static void lpush(byte[] key,byte[]... value){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.lpush(key,value);
        }
        catch(Exception e)
        {
            LOGGER.error("lpush[]:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    /**
     * BRPOP 是列表的阻塞式(blocking)弹出原语。
     * 它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的尾部元素。
     * 关于阻塞操作的更多信息，请查看 BLPOP 命令， BRPOP 除了弹出元素的位置和 BLPOP 不同之外，其他表现一致。
     */
    @SuppressWarnings("rawtypes")
    public static List<String> brpop(String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            List<String> data=jedIs.brpop(key);
            return data;
        }
        catch(Exception e)
        {
            LOGGER.error("brpop:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return new ArrayList<>();
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    /**
     * BRPOP 是列表的阻塞式(blocking)弹出原语。
     * 它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的尾部元素。
     * 关于阻塞操作的更多信息，请查看 BLPOP 命令， BRPOP 除了弹出元素的位置和 BLPOP 不同之外，其他表现一致。
     */
    @SuppressWarnings("rawtypes")
    public static List<String> brpop(int timeout,String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            List<String> data=jedIs.brpop(timeout,key);
            return data;
        }
        catch(Exception e)
        {
            LOGGER.error("brpop timeout:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return new ArrayList<>();
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    /**
     * 移除并返回列表 key 的尾元素。
     */
    @SuppressWarnings("unchecked")
    public static <T> T rpop(String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return (T)jedIs.rpop(key);
        }
        catch(Exception e)
        {
            LOGGER.error("rpop"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return null;
        }
        finally
        {
            jedIs.close();
        }
    }

    public static byte[] rpop(byte[] key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return jedIs.rpop(key);
        }
        catch(Exception e)
        {
            LOGGER.error("rpop"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return null;
        }
        finally
        {
            jedIs.close();
        }
    }

    /**
     * BLPOP 是列表的阻塞式(blocking)弹出原语。
     * 它是 LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素。
     */
    @SuppressWarnings("rawtypes")
    public static List<String> blpop(int timeout,String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            List<String> data=jedIs.blpop(timeout,key);
            return data;
        }
        catch(Exception e)
        {
            LOGGER.error("brpop timeout:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return new ArrayList<>();
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    /**
     * <p>通过key向指定的set中添加value</p>
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public static void sadd(String key,String... members){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            jedIs.sadd(key,members);
        }
        catch(Exception e)
        {
            LOGGER.error("sadd:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    /**
     * <p>删除名称为key的set中的元素member</p>
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public static void srem(String key,String members){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            if(sismember(key,members)) jedIs.srem(key,members);
        }
        catch(Exception e)
        {
            LOGGER.error("srem:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    /**
     * 返回列表 key 的长度。
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     * 如果 key 不是列表类型，返回一个错误。
     */
    public static Long llen(String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return jedIs.llen(key);
        }
        catch(Exception e)
        {
            LOGGER.error("llen:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return 0L;
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    public static Long llen(byte[] key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {
            return jedIs.llen(key);
        }
        catch(Exception e)
        {
            LOGGER.error("llen[]:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return 0L;
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    /**
     * <p>通过key判断value是否是set中的元素</p>
     * @param key key
     * @param member member
     * @return true/false
     */
    public static boolean sismember(String key,String member){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        boolean res=false;
        try
        {
            res=jedIs.sismember(key,member);
        }
        catch(Exception e)
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
            LOGGER.error("sismember:"+e.getMessage(),e);
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        return res;
    }

    private static byte[] getCacheKey(int type,String key){
        return (getPrefix(type)+":"+key).getBytes();
    }

    private static Object get(int type,String key){
        ShardedJedis jedis=CacheInitiator.shardedJedisPool.getResource();
        byte[] bytes=jedis.get(getCacheKey(type,key));
        jedis.close();
        return deserialize(bytes);
    }

    public static boolean exists(int type,String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {

            return jedIs.exists(getCacheKey(type,key));
        }
        catch(Exception e)
        {
            LOGGER.error("exists"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
        return false;
    }

    public static boolean exists(String key){
        ShardedJedis jedIs=CacheInitiator.shardedJedisPool.getResource();
        try
        {

            return jedIs.exists(key);
        }
        catch(Exception e)
        {
            LOGGER.error("exists:"+e.getMessage(),e);
            if(jedIs!=null)
            {
                jedIs.close();
            }
            return false;
        }
        finally
        {
            if(jedIs!=null)
            {
                jedIs.close();
            }
        }
    }

    private static String getPrefix(int type){
        switch(type)
        {
            case Constants.CACHE_DATA_TYPE_USER:
                return Constants.CACHE_PREFIX_USER;
            case Constants.CACHE_DATA_TYPE_GROUP:
                return Constants.CACHE_PREFIX_GROUP;
            // TODO: 16-4-27 cache 增加类型前缀判断
            case Constants.CACHE_USER_LOCAL_CATS:
                return Constants.CACHE_PREFIX_USER_LOCAL_CATS;
            case Constants.CACHE_USER_LOCAL_ONLINE:
                return Constants.CACHE_PREFIX_USER_LOCAL_ONLINE;
            case Constants.CACHE_USER_LOCAL_RPC_TCP_URL:
                return Constants.CACHE_PREFIX_USER_LOCAL_RPC_TCP_URL;
            case Constants.CACHE_USERS_LOCAL_AGENT:
                return Constants.CACHE_PREFIX_USERS_LOCAL_AGENT;
            case Constants.CACHE_USER_LOCAL_VERSION:
                return Constants.CACHE_PREFIX_USER_LOCAL_VERSION;
            case Constants.CACHE_USERS_LOCAL_GET_ONLINE:
                return Constants.CACHE_PREFIX_USERS_LOCAL_GET_ONLINE;
            case Constants.CACHE_USERS_LOCAL_GROUP_AND_MEMBERS_INFO_MAP:
                return Constants.CACHE_PREFIX_USERS_LOCAL_GROUP_AND_MEMBERS_INFO_MAP;
            case Constants.CACHE_USERS_LOCAL_GROUP_INFO_CACHE_MAP:
                return Constants.CACHE_PREFIX_USERS_LOCAL_GROUP_INFO_CACHE_MAP;
            case Constants.CACHE_USERS_LOCAL_002:
                return Constants.CACHE_PREFIX_USERS_LOCAL_002;
            case Constants.CACHE_USERS_LOCAL_ADD_BUDDY_INVITE:
                return Constants.CACHE_PREFIX_USERS_LOCAL_ADD_BUDDY_INVITE;
            case Constants.CACHE_USERS_LOCAL_003:
                return Constants.CACHE_PREFIX_USERS_LOCAL_003;
        }
        return "";
    }

    private static byte[] serialize(Object obj){
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        try
        {
            byteArrayOutputStream=new ByteArrayOutputStream();
            objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }
        catch(IOException e)
        {
            LOGGER.error("Error when serializing\n",e);
        }
        return null;
    }

    private static Object deserialize(byte[] bytes){
        ObjectInputStream objectInputStream;
        ByteArrayInputStream byteArrayInputStream;
        try
        {
            byteArrayInputStream=new ByteArrayInputStream(bytes);
            objectInputStream=new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        }
        catch(IOException|ClassNotFoundException e)
        {
            LOGGER.error("Error when de-serializing\n",e);
        }
        return null;
    }

    public static void main(String[] args){

    }
}
