package com.lsm.cache;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;


public class CacheInitiator
{
    private static JedisShardInfo jedisShardInfo;
    static ShardedJedisPool shardedJedisPool;
    private static GenericObjectPoolConfig poolConfig;
    private static List<JedisShardInfo> shardList;
//    private static final LoggerHelper LOGGER = new LoggerHelper(Bootstrap.class);

    static
    {
        try
        {
            jedisShardInfo=new JedisShardInfo(Constants.CACHE_HOST,Constants.CACHE_PORT);
            jedisShardInfo.setPassword(Constants.CACHE_PWD);
            poolConfig=new JedisPoolConfig();
            poolConfig.setMaxIdle(Constants.CACHE_MAX_IDLE);
            poolConfig.setMaxWaitMillis(Constants.CACHE_MAX_WAIT_MILLIS);
            poolConfig.setTestOnBorrow(Constants.CACHE_TEST_ON_BORROW);
            poolConfig.setTestOnReturn(Constants.CACHE_TEST_ON_RETURN);
            poolConfig.setMinIdle(Constants.CACHE_MIN_IDLE);
            shardList=new ArrayList<JedisShardInfo>();
            shardList.add(jedisShardInfo);
            shardedJedisPool=new ShardedJedisPool(poolConfig,shardList);
        }
        catch(Exception e)
        {
//            LOGGER.error("CacheInitiator:"+e.getMessage(),e);
        }
    }
}
