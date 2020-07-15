package cn.com.eju.deal.Report.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cn.com.eju.deal.core.support.SystemCfg;

public class GroupConfig {

	private static int[] levelTypeIds = new int[] { 7,8,4 };
	
	private static String[] oldLevelTypeNames = new String[] { "事业部", "部门", "小组" };
//Mod By GuoPengFei 2017组织架构 Start
	//private static String[] levelTypeNames = new String[] { "事业部", "部门", "小组" };
	private static String[] levelTypeNames = new String[] { "区", "中心", "小组" };
//Mod By GuoPengFei 2017组织架构 Start
	
	private static Set<Integer> oldGroupIds = new HashSet<>(Arrays.asList(3,21,36,73,83,92,196,213,216,302,307,310,312,314,316,318,320,322,324,326,328,330,357,371,432,664));
	
	/**
	 * 取得业绩归属层级id列表
	 * @return 业绩归属层级id列表
	 */
	public static int[] getLevelTypeIds() {
		if (levelTypeIds == null) {
			String[] configValue = StringUtils.split(SystemCfg.getString("levelTypeIds"), ",");
			levelTypeIds = new int[configValue.length];
			for (int i = 0; i < configValue.length; i++) {
				levelTypeIds[i] = Integer.parseInt(configValue[i]);
			}
		}
		return levelTypeIds;
	}
	
	public static String[] getOldLevelTypeNames() {
		if (oldLevelTypeNames == null) {
			oldLevelTypeNames = StringUtils.split(SystemCfg.getString("oldLevelTypeNames"), ",");
		}
		return oldLevelTypeNames;
	}
	
	public static String[] getLevelTypeNames() {
		if (levelTypeNames == null) {
			levelTypeNames = StringUtils.split(SystemCfg.getString("levelTypeNames"), ",");
		}
		return levelTypeNames;
	}
	
	/**
	 * 取得3-1前的事业部id列表
	 * @return 3-1前的事业部id列表
	 */
	public static Set<Integer> getOldGroupIds() {
		if (oldGroupIds == null) {
			String[] configValue = StringUtils.split(SystemCfg.getString("oldGroupIds"), ",");
			oldGroupIds = new HashSet<>();
			for (int i = 0; i < configValue.length; i++) {
				oldGroupIds.add(Integer.valueOf(configValue[i]));
			}
		}
		
		return oldGroupIds;
	}
}
