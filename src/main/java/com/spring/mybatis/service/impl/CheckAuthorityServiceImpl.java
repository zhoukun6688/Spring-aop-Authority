package com.spring.mybatis.service.impl;

import com.spring.mybatis.componet.JedisClient;
import com.spring.mybatis.dao.PermissionMapper;
import com.spring.mybatis.service.CheckAuthorityService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckAuthorityServiceImpl implements CheckAuthorityService {

	@Resource
	private PermissionMapper permissionMapper;

	@Resource
	private JedisClient jedisClient;

	private static final Map<String, String> authorityMap = new HashMap<>();

	private static  final Logger logger = LoggerFactory.getLogger(CheckAuthorityServiceImpl.class);
	
	@Override
	public boolean checkAccess(String flag, HttpSession session) {

		String desUrl="";
		boolean desFlag=false;

		try {
			//在session中获取登录名
			String loginName = (String) session.getAttribute("userLoginName");
			//在session中获取用户角色ID
			Integer roleID= (Integer) session.getAttribute("roleID");
			String roleIdMap="ROLEID_PERMISSION:"+roleID.toString()+"_"+"MAP";
			//在redis中查找存放资源的map，若map存在，则通过目标资源获取
			authorityMap=jedisClient.hgetAll(roleIdMap);
			if (authorityMap!=null && authorityMap.size()>0){
				//匹配资源 这是的时间复杂度大约为O（1）
				desUrl=authorityMap.get(flag);
			} else {
				//若存放资源的map不存在，在数据库中将资源搜出来，放入map中，这时候map的key就是资源（Url）value 是1 目的是省内存
				List<String> list = permissionMapper.getPermissions(loginName);
				if (!list.isEmpty()) {
					for (String str : list) {
						authorityMap.put(str,"1");
					}
					//匹配资源 这是的时间复杂度大约为O（1）
					desUrl=authorityMap.get(flag);
					//将map放入redis中
					jedisClient.hmset(roleIdMap,authorityMap);
				}
			}
			//判断资源是否匹配成功
			if (StringUtils.isNoneBlank(desUrl)){
				desFlag=true;
			}

		}catch (Exception e){
			logger.info("获取资源失败",e);
		}


		return desFlag;
	}

}
