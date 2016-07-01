package com.lsm.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class Constants {
    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File("Constants.properties")));
//            properties = ConfigurationManager.loadProperties("Constants.properties", null, null);
        } catch (Exception e) {
            LOGGER.error("初始化常量配置【Constants】：" + e.getMessage(), e);
        }
    }

    //缓存数据类型
    public static final int CACHE_DATA_TYPE_USER = 1;
    public static final int CACHE_DATA_TYPE_GROUP = 2;
//    public static final int CACHE_DATA_TYPE_MESSAGE = 3;
    public static final int CACHE_USER_LOCAL_CATS= 4;
    public static final int CACHE_USER_LOCAL_ONLINE= 5;
    public static final int CACHE_USER_LOCAL_RPC_TCP_URL= 6;
    public static final int CACHE_USERS_LOCAL_AGENT= 7;
    public static final int CACHE_USER_LOCAL_VERSION= 8;
    public static final int CACHE_USERS_LOCAL_GET_ONLINE= 9;
    public static final int CACHE_USERS_LOCAL_GROUP_AND_MEMBERS_INFO_MAP= 10;
    public static final int CACHE_USERS_LOCAL_GROUP_INFO_CACHE_MAP=11;
    public static final int CACHE_USERS_LOCAL_002=13;
    public static final int CACHE_USERS_LOCAL_ADD_BUDDY_INVITE=14;
    public static final int CACHE_USERS_LOCAL_003=15;
    // TODO: 16-4-27 cache 增加类型常量
    //缓存中key的前缀
    public static final String CACHE_PREFIX_USER = "U";
    public static final String CACHE_PREFIX_GROUP = "G";
    public static final String CACHE_PREFIX_USER_LOCAL_GET_STATES_ONLY_ONE = "ULGSOOWebAsSets20160604";//用户userIds
    public static final String CACHE_PREFIX_USER_LOCAL_PRS_WORKER_DOUBLE_QUEUE= "WebAsPRSWorkDoubleQueue20160605";//双端队列：保存userId
    public static final String CACHE_PREFIX_USER_LOCAL_PRS_WORKER_QUEUE = "WebAsPRSWorkQueue20160613";//普通队列userId更新在线状态
    public static final String CACHE_PREFIX_USER_LOCAL_CATS= "CATS20160601";
    public static final String CACHE_PREFIX_USER_LOCAL_ONLINE = "ONLINE20160602";
    public static final String CACHE_PREFIX_USER_LOCAL_RPC_TCP_URL= "URL20160603";
    public static final String CACHE_PREFIX_USERS_LOCAL_AGENT= "ULUsersCache20160606";
    public static final String CACHE_PREFIX_USERS_LOCAL_ONLY_ONE = "ULUsersOnlyMap20160607";
    public static final String CACHE_PREFIX_USERS_LOCAL_GET_ONLINE= "GetUserOnline20160608";
    public static final String CACHE_PREFIX_USER_ONLINE_USER_AGENT_LOCAL_ONLY_ONE = "UserOnlineUserAgentMap20160609";
    public static final String CACHE_PREFIX_USER_LOCAL_VERSION= "UserOnlineVersion20160610";
    public static final String CACHE_PREFIX_USERS_LOCAL_GROUP_AND_MEMBERS_INFO_MAP="GroupAndMemberInfo20160611";
    public static final String CACHE_PREFIX_USERS_LOCAL_GROUP_INFO_CACHE_MAP="GroupInfoCache20160612";
    public static final String CACHE_PREFIX_USERS_LOCAL_002="CACHE_USERS_LOCAL_00220160614";
    public static final String CACHE_PREFIX_USERS_LOCAL_ADD_BUDDY_INVITE="UserLocalAddBuddyInvite20160615";
    public static final String CACHE_PREFIX_USERS_LOCAL_003="CACHE_USERS_LOCAL_00320160615";

    public static final String DOMAIN = getStringProperties("domain");//domain
    public static final int POLL_NUMBER= getIntProperties("poll.Number");//poll 数量
    public static final int RPC_PORT= getIntProperties("rpcPort");//rpc port
    public static final int TIMEOUT= getIntProperties("timeOut");//timeOut 默认超时时间
    public static final int KEEP_ALIVE_TIME= getIntProperties("keepAlive.time");//每5分钟跟PRS保持一次心跳
    public static final int EXPIRE_TIME= getIntProperties("expire.time");//webAs和PRS之间的session延续时间

    // TODO: 16-4-27 cache 增加类型前缀
    //===============================以下为配置参数，以上为业务常量============================
    public static final String CACHE_HOST = getStringProperties("cache.host");
    public static final int CACHE_PORT = getIntProperties("cache.port");
    public static final String CACHE_PWD = getStringProperties("cache.pwd");
    public static final int CACHE_MAX_IDLE = getIntProperties("cache.maxIdle");
    public static final int CACHE_MAX_WAIT_MILLIS = getIntProperties("cache.maxWaitMillis");
    public static final boolean CACHE_TEST_ON_BORROW = getBoolProperties("cache.testOnBorrow");
    public static final boolean CACHE_TEST_ON_RETURN = getBoolProperties("cache.testOnReturn");
    public static final int CACHE_MIN_IDLE = getIntProperties("cache.minIdle");

    /**获取用户头像*/
    public static final String  HDS_URL = getStringProperties("hds.url");

    /**start 轮询获取联系人和群成员状态及互踢参数配置*/
    public static final int LOOP_ONLINE_USER_THREAD_POOL = getIntProperties("loop.online.threadPool");
    public static final int LOOP_ONLINE_USER_THREAD_PAGE_SIZE = getIntProperties("loop.online.threadPageSize");
    public static final int LOOP_ONLINE_USER_THREAD_INITIAL_DELAY = getIntProperties("loop.online.initialDelay");
    public static final int LOOP_ONLINE_USER_THREAD_PERIOD = getIntProperties("loop.online.period");
    /**end 轮询获取联系人和群成员状态及互踢参数配置 */

    /** start 轮询获取个人，联系人，群组版本号变更*/
    public static final int LOOP_VERSION_USER_THREAD_POOL = getIntProperties("loop.version.threadPool");
    public static final int LOOP_VERSION_USER_THREAD_PAGE_SIZE = getIntProperties("loop.version.threadPageSize");
    public static final int LOOP_VERSION_USER_THREAD_INITIAL_DELAY = getIntProperties("loop.version.initialDelay");
    public static final int LOOP_VERSION_USER_THREAD_PERIOD = getIntProperties("loop.version.period");
    /** end 轮询获取个人，联系人，群组版本号变更*/

    /**##start 本地维护用户在线状态*/
    public static final int PRS_USER_ONLINE_THREAD_POOL = getIntProperties("prs.userOnline.threadPool");
    public static final int PRS_USER_ONLINE_INITIAL_DELAY = getIntProperties("prs.userOnline.initialDelay");
    public static final int PRS_USER_ONLINE_PERIOD = getIntProperties("prs.userOnline.period");
    public static final int RDS_USER_ONLINE_TIME = getIntProperties("rds.userOnline.time");
    /**##start 本地维护用户在线状态*/

    /** start 轮询从redis 队列获取用户*/
    public static final int LOOP_REDIS_USER_ID_THREAD_POOL = getIntProperties("loop.redis.userId.threadPool");
    public static final int LOOP_REDIS_USER_ID_INITIAL_DELAY= getIntProperties("loop.redis.userId.initialDelay");
    public static final int LOOP_REDIS_USER_ID_PERIOD = getIntProperties("loop.redis.userId.period");
    public static final int LOOP_REDIS_USER_ID_BATCH_COUNT = getIntProperties("loop.redis.userId.batch.count");
    /** end 轮询从redis 队列获取用户*/


    /** start 轮询获取好友邀请*/
    public static final int LOOP_INVITE_USER_THREAD_POOL = getIntProperties("loop.invite.threadPool");
    public static final int LOOP_INVITE_USER_THREAD_PAGE_SIZE = getIntProperties("loop.invite.threadPageSize");
    public static final int LOOP_INVITE_USER_THREAD_INITIAL_DELAY = getIntProperties("loop.invite.initialDelay");
    public static final int LOOP_INVITE_USER_THREAD_PERIOD = getIntProperties("loop.invite.period");
    /** end 轮询获取好友邀请*/

    /** start 轮询HasMessage*/
    public static final int LOOP_HAS_MESSAGE_THREAD_POOL= getIntProperties("loop.hasMessage.threadPool");
    public static final int LOOP_HAS_MESSAGE_THREAD_PAGE_SIZE= getIntProperties("loop.hasMessage.threadPageSize");
    public static final int LOOP_HAS_MESSAGE_THREAD_INITIAL_DELAY= getIntProperties("loop.hasMessage.initialDelay");
    public static final int LOOP_HAS_MESSAGE_THREAD_PERIOD= getIntProperties("loop.hasMessage.period");
    /** end 轮询HasMessage*/

    /** start 轮询GetPGroupNotify*/
    public static final int LOOP_GROUP_NOTIFY_THREAD_POOL= getIntProperties("loop.pGroupNotify.threadPool");
    public static final int LOOP_GROUP_NOTIFY_THREAD_PAGE_SIZE= getIntProperties("loop.pGroupNotify.threadPageSize");
    public static final int LOOP_GROUP_NOTIFY_THREAD_INITIAL_DELAY= getIntProperties("loop.pGroupNotify.initialDelay");
    public static final int LOOP_GROUP_NOTIFY_THREAD_PERIOD= getIntProperties("loop.pGroupNotify.period");
    /** end 轮询GetPGroupNotify*/
    public static final int SAP_LISTEN_PORT= getIntProperties("sap.listen.port");


    public static final String SIPC_DOMAIN= getStringProperties("sipcDomain");

    private static boolean getBoolProperties(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    private static int getIntProperties(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    private static String getStringProperties(String key) {
        return properties.getProperty(key);
    }

}
