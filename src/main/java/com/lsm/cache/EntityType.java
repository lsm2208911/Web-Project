package com.lsm.cache;

/**
 * @author zx
 *         on 16-4-26.
 */
public enum EntityType implements EnumInteger
{
		UserLocalCats(Constants.CACHE_USER_LOCAL_CATS),//REDIS USER CATS
	    UserLocalAgent(Constants.CACHE_USERS_LOCAL_AGENT),//REDIS USER AGENT对象
		UserLocalOnline(Constants.CACHE_USER_LOCAL_ONLINE),//REDIS USER ONLINE 用户在线信息
		UserLocalVersion(Constants.CACHE_USER_LOCAL_VERSION),//REDIS USER VERSION 版本号
		UserLocalGroupAndMemberInfoMap(Constants.CACHE_USERS_LOCAL_GROUP_AND_MEMBERS_INFO_MAP),//缓存群和群成员信息
		UserLocalGroupInfoCacheMap(Constants.CACHE_USERS_LOCAL_GROUP_INFO_CACHE_MAP),//缓存群和群属性信息
		UserLocal002(Constants.CACHE_USERS_LOCAL_002),//REDIS用户在线状态时间戳
		UserLocal003(Constants.CACHE_USERS_LOCAL_003),//REDIS用户lastKeepAliveTime时间戳
		UserLocalAddBuddyInvite(Constants.CACHE_USERS_LOCAL_ADD_BUDDY_INVITE),//REDIS 好友邀请缓存列表
		UserLocalGetOnline(Constants.CACHE_USERS_LOCAL_GET_ONLINE);//REDIS USER getOnline 获取用户在线状态给客户端
		private int value;

		@Override
		public int intValue()
		{
				return value;
		}

		EntityType(int value)
		{
				this.value = value;
		}
}
