//package cn.com.eju.deal.contract.service;
//
//import org.springframework.stereotype.Service;
//
//import cn.com.eju.deal.base.service.BaseService;
//import cn.com.eju.deal.base.support.SystemParam;
//import cn.com.eju.deal.core.support.ResultData;
//import cn.com.eju.deal.dto.contract.DepositDto;
//
///**
// * 调用OMS Service
// */
//@Service("depositService")
//public class DepositService extends BaseService<DepositDto> {
//	/**
//	 * 传递OMS 保证金信息
//	 */
//	public ResultData<?> transDepositInfo(DepositDto dto) throws Exception {
//		// 调用 接口  
//		String url = SystemParam.getWebConfigValue("OMSService")+ "oms/deposit"+"";
//		ResultData<?> backResult = post(url, dto);
//		return backResult;
//		
//	}
//}
