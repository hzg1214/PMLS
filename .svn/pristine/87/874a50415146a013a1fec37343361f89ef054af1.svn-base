package cn.com.eju.deal.base.util;

import java.util.Map;

public class BuildingNoUtil {
    public static Map<String,Object> parse(Map<String,Object> reqMap){
        if(reqMap.get("buildingNo") != null){
            String buildingNo = reqMap.get("buildingNo").toString();
            if(buildingNo.indexOf("/") != -1){
                buildingNo = buildingNo.replaceAll("/","%2F");
                reqMap.put("buildingNo",buildingNo);
            }
        }

        if(reqMap.get("oldBuildingNo") != null){
            String oldBuildingNo = reqMap.get("oldBuildingNo").toString();
            if(oldBuildingNo.indexOf("/") != -1){
                oldBuildingNo = oldBuildingNo.replaceAll("/","%2F");
                reqMap.put("oldBuildingNo",oldBuildingNo);
            }
        }

        return reqMap;
    }
}
