package cn.com.eju.deal.contract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.ImageCompressUtil;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.OaService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.service.StoreDepositService;
import cn.com.eju.deal.store.service.StoreService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 合同终止Controller层
 * 
 * @author sunmm
 * @date 2016年8月1日 下午9:55:22
 */
@Controller
@RequestMapping(value = "stopcontract")
public class ContractChangeController extends BaseController {

	/**
	 * 日志
	 */
	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	@Resource(name = "stopContractService")
	private ContractChangeService stopContractService;
	
	@Resource(name = "contractService")
	private ContractService contractService;
	
	@Resource(name = "oaOperatorService")
	private OaOperatorService oaOperatorService;
	
	@Resource(name = "oaService")
	private OaService oaService;

	@Resource(name = "storeService")
	private StoreService storeService;

	@Resource(name = "linkageCityService")
	private LinkageCityService linkageCityService;
	
	@Resource(name = "cityService")
	private CityService cityService;
	
	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "storeDepositService")
	private StoreDepositService storeDepositService;

	/**
	 * 初始化--新建合同终止页面
	 * 
	 * @param contractId
	 *            合同Id
	 * @param mop
	 * @return
	 */
	@RequestMapping(value = "toAdd/{contractId}/{contractStatus}", method = RequestMethod.GET)
	public String toAdd(@PathVariable("contractId") Integer contractId,@PathVariable("contractStatus") Integer contractStatus,
			ModelMap mop) {
		// 获取页面上门店列表的数据
		List<?> storeList = new ArrayList<>();
		try {
			// 根据合同ID查询门店信息
			ResultData<?> reback = stopContractService
					.queryStoreOfAdd(contractId);

			// 页面数据
			storeList = (List<?>) reback.getReturnData();
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toAdd", "",
					UserInfoHolder.getUserId(), "", "门店信息查询失败！", e);
		}

		// 存放到mop中
		mop.addAttribute("storeList", storeList);
		mop.addAttribute("contractId", contractId);
		mop.addAttribute("contractStatus", contractStatus);
		return "contract/contractChangeAdd";
	}
	
//Add By GuoPengfei 合规性 start	
	/**
	 * 初始化--新建乙转甲终止页面
	 * @author wjj
	 * @param contractId
	 *            合同Id
	 * @param mop
	 * 
	 * @return
	 */
	@RequestMapping(value = "toAddB2A/{storeid}/{contractId}/{contractStatus}", method = RequestMethod.GET)
	public String toAddB2A(@PathVariable("storeid") Integer storeid, @PathVariable("contractId") Integer contractId,@PathVariable("contractStatus") Integer contractStatus, ModelMap mop)  {
		// 获取页面上门店列表的数据
		List<?> storeList = new ArrayList<>();
		 try {
	        	//查询isService=1的城市
	        	ResultData<?> reback = cityService.queryCityListByIsService();
	        	List<?> citylist = (List<?>) reback.getReturnData();
	        	mop.addAttribute("citylist", citylist);
			} catch (Exception e) {
				logger.error("storeDetail", "StoreControler", "toupdate", "", null, "", "查询城市", e);
			}
			     
		try {
			// B或者A2B的通过审核的最新合同
			// 根据合同ID查询门店信息
			ResultData<?> reback = storeService.getStoreById(storeid);

			// 页面数据
			storeList = (List<?>) reback.getReturnData();
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toAddB2A", "",
					UserInfoHolder.getUserId(), "", "门店信息查询失败！", e);
		}

		// 存放到mop中
		mop.addAttribute("storeList", storeList);
		mop.addAttribute("contractId", contractId);
		mop.addAttribute("contractStatus", contractStatus);
		mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.AGREEMENT_TYPE + ""));
		
		try {
            ResultData<?> data = contractService.getById(contractId);
            if("200".equals(data.getReturnCode())) {
                mop.put("contractInfo", data.getReturnData());
                if(!"".equals((((Map)((Map)(Map)data.getReturnData()).get("contract"))).get("cityNo"))) {
                    String cityNo = (((Map)((Map)(Map)data.getReturnData()).get("contract"))).get("cityNo").toString();
                    linkageCityService.getDistrictList(cityNo);
                    mop.addAttribute("districtList", linkageCityService.getDistrictList(cityNo).getReturnData());
                }
            }
        } catch (Exception e) {
            logger.error("contract", "ContractChangeController", "toAddB2A", "",
                    UserInfoHolder.getUserId(), "", "合同信息查询失败！", e);
        }
		
		return "contract/contractB2AChangeAdd";
	}
	
	/**
	 * 保存乙转甲变更申请信息
	 * @author wjj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "Save", method = RequestMethod.POST)
	public ReturnView<?, ?> Save(HttpServletRequest request, ModelMap modelMap) {

		// 返回map
		Map<String, Object> rspMap = new HashMap<String, Object>();
		// 获取map
		Map<String, Object> reqMap = bindParamToMap(request);
		ContractInfoDto dto = new ContractInfoDto();
		try {
			// 获取页面字段值，转为DTO
			this.setB2ADto(reqMap, dto, "create", "");

		} catch (Exception e1) {
			logger.error("contract", "ContractChangeController", "Save", "",
					UserInfoHolder.getUserId(), "", "", e1);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "合同变更参数设置失败！");
			return getOperateJSONView(rspMap);
		}

		//验证地址是否重复
		if ("1".equals(reqMap.get("storechange"))) {
			try {
				Map<String, Object> map = new HashMap<>();
				map.put("CityNo", reqMap.get("storeCity"));
				map.put("DistrictNo", reqMap.get("storeDistrict"));
				map.put("Address", reqMap.get("storeAddress"));

				ResultData<?> resultDate = storeService.checkAddress(map);
				if (resultDate.getReturnCode().equals(ReturnCode.FAILURE)) {
					rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
					rspMap.put(Constant.RETURN_MSG_KEY, "系统中已存在相同的门店地址，请重新填写!");
					return getOperateJSONView(rspMap);
				}
			} catch (Exception e) {
				logger.error("contract", "ContractChangeController", "doSave", "", UserInfoHolder.getUserId(), "", "", e);
				rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
				rspMap.put(Constant.RETURN_MSG_KEY, "验证置失败！");
				return getOperateJSONView(rspMap);
			}
		}

		//校验门店是否有审核中的保证金收款或退款记录 add by haidan 2018/03/23
		if(reqMap.get("mainchange") != null && reqMap.get("mainchange").toString().equals("1")) {
		    String storeNo = (String) reqMap.get("storeNoArray");
		    try{
		        ResultData<Integer> resultRecord = this.storeDepositService.checkStoreDepositLock(storeNo);
		        if(resultRecord.getReturnCode().equals(ReturnCode.SUCCESS)){
		            Integer rel = resultRecord.getReturnData();
		            if (rel > 0) {
		                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
		                rspMap.put(Constant.RETURN_MSG_KEY, "该乙转甲的门店存在审核中的保证金收款/退款记录，请处理完成后再做乙转甲操作。");
		                return getOperateJSONView(rspMap);
		            }
		        }
		    }catch (Exception e){
		        logger.error("contract", "ContractChangeController", "checkStoreDepositLock", "", UserInfoHolder.getUserId(), "", "", e);
		        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
		        rspMap.put(Constant.RETURN_MSG_KEY, "校验门店是否有审核中的保证金收款或退款记录失败！");
		        return getOperateJSONView(rspMap);
		    }
		}

		try {
			// 保存操作
			ResultData<?> resultData = stopContractService.create(dto);

			rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
			rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "Save", "",
					UserInfoHolder.getUserId(), "", "", e);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "新增合同变更失败！");
			return getOperateJSONView(rspMap);
		}
		return getSearchJSONView(rspMap);
	}
//Add By GuoPengfei 合规性 end	
	
	/**
	 * 保存变更申请信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "doSave", method = RequestMethod.POST)
	public ReturnView<?, ?> doSave(HttpServletRequest request, ModelMap modelMap) {

		// 返回map
		Map<String, Object> rspMap = new HashMap<String, Object>();

		// 获取map
		Map<String, Object> reqMap = bindParamToMap(request);

		ContractInfoDto dto = new ContractInfoDto();
		Integer contractId = Integer.valueOf(reqMap.get("contractId").toString());
		int count =0;
		try {
			count = stopContractService.queryStoreOfCountByConractId(contractId);
		} catch (Exception e2) {
		}
		try {

			// 获取页面字段值，转为DTO
			this.setDto(reqMap, dto, "create", "",count);

		} catch (Exception e1) {
			logger.error("contract", "ContractChangeController", "doSave", "",
					UserInfoHolder.getUserId(), "", "", e1);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "合同变更参数设置失败！");
			return getOperateJSONView(rspMap);
		}

		try {

			// 保存操作
			ResultData<?> resultData = stopContractService.create(dto);

			rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
			rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "doSave", "",
					UserInfoHolder.getUserId(), "", "", e);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "新增合同变更失败！");
			return getOperateJSONView(rspMap);
		}
		return getSearchJSONView(rspMap);
	}

	/**
	 * 初始化--编辑变更申请信息
	 * 
	 * @param id
	 *            合同变更ID
	 * @param contractId
	 *            合同ID
	 * @param mop
	 * @return
	 */
	@RequestMapping(value = "toEdit/{id}/{contractId}/{contractStatus}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("id") int id,
			@PathVariable("contractId") Integer contractId, 
			@PathVariable("contractStatus") Integer contractStatus,ModelMap mop) {
		// 返回map
		ResultData<?> resultData = new ResultData<>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			// 根据合同变更ID查询变更申请信息
			resultData = stopContractService.queryById(id);
            map = (Map<String, Object>) resultData.getReturnData();
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toEdit", "",
					UserInfoHolder.getUserId(), "", "合同变更信息查询失败！", e);
		}
		// 存放到mop中
		mop.addAttribute("contractChange", map);
		
		
		 try {
	        	//查询isService=1的城市
	        	ResultData<?> reback = cityService.queryCityListByIsService();
	        	List<?> citylist = (List<?>) reback.getReturnData();
	        	mop.addAttribute("citylist", citylist);
	        	
	        	
	        	//得到公司区域列表
	        	ResultData<?> resultDistrictList = new ResultData<>();
	        	String companyCity= (String) map.get("companyCity");
	        	if(!"".equals(companyCity))
	        	{
	        		 resultDistrictList = linkageCityService.getDistrictList(companyCity);            
	 	            mop.addAttribute("companyDistrictList", resultDistrictList.getReturnData());
	        	}
	            //得到门店区域列表
	        	ResultData<?> resultStoreDistrictList = new ResultData<>();
	        	String storeCity= (String) map.get("storeCity");
	        	if(!"".equals(storeCity))
	        	{
	        		resultStoreDistrictList = linkageCityService.getDistrictList(storeCity);            
		            mop.addAttribute("storeDistrictList", resultStoreDistrictList.getReturnData());
	        	}
	            //得到营业执照区域列表
	            ResultData<?> resultMinDistrictList = new ResultData<>();
	        	String newCompanyAddressCityNo= (String) map.get("newCompanyAddressCityNo");
	        	if(!"".equals(newCompanyAddressCityNo))
	        	{
	        		resultMinDistrictList = linkageCityService.getDistrictList(newCompanyAddressCityNo);
		            mop.addAttribute("mainDistrictList", resultMinDistrictList.getReturnData());
	        	}
	        	//收件地址
	        	if(map.get("cityNo") != null && !"".equals(map.get("cityNo").toString())) {
	        	    String cityNo = map.get("cityNo").toString();
	        	    linkageCityService.getDistrictList(cityNo);
	        	    mop.addAttribute("districtList", linkageCityService.getDistrictList(cityNo).getReturnData());
	        	}
	            
	        	if(map.get("oldCompanyId") != null) {
	        	   Integer oldCompanyId = Integer.valueOf(map.get("oldCompanyId").toString());
	        	   ResultData<?> companyData = companyService.getById(oldCompanyId);
	        	   mop.addAttribute("oldCompanyName", ((Map)companyData.getReturnData()).get("companyName"));
	        	}
			} catch (Exception e) {
				logger.error("storeDetail", "StoreControler", "toupdate", "", null, "", "查询城市", e);
			}
		
		//Add By QJP 2017/05/25 合规性 start		
		//取出变更类型
		int changeType = 0;
		Map<?,?> datamap = (Map<?,?>)resultData.getReturnData();
		changeType = Integer.parseInt( datamap.get("changeType").toString());
		//Add By QJP 2017/05/25 合规性 end
		
		// 获取页面上门店列表的数据
		List<?> storeList = new ArrayList<>();
		try {
			// 根据合同ID查询门店信息
			ResultData<?> reback = stopContractService.queryStoreOfEdit(id,
					contractId);

			// 页面数据
			storeList = (List<?>) reback.getReturnData();
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toEdit", "",
					UserInfoHolder.getUserId(), "", "门店信息查询失败！", e);
		}
		// 存放到mop中
		mop.addAttribute("storeList", storeList);
		mop.addAttribute("contractId", contractId);
		mop.addAttribute("contractStatus", contractStatus);
		mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.AGREEMENT_TYPE + ""));

		try {
			ResultData<?> data = contractService.getById(contractId);
			if("200".equals(data.getReturnCode())) {
				mop.put("contractInfo", data.getReturnData());
			}
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toEditB2A", "",
					UserInfoHolder.getUserId(), "", "合同信息查询失败！", e);
		}
		
//Mod By QJP 2017/05/25 合规性 start		
		//return "contract/contractChangeEdit";
		//根据变更类型返回页面
		String   url;
		if(changeType == DictionaryConstants.Contract_ChangeType_B2A)
		{
					   //返回视图
		     url = "contract/contractB2AChangeEdit";	
		}else{
			url = "contract/contractChangeEdit";		
		}
		return url;
//Mod By QJP 2017/05/25 合规性 End	
	}
	
	/**
	 * 更新变更申请信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "doUpdate", method = RequestMethod.PUT)
    public ReturnView<?, ?> doUpdate(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform) {

        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);

        ContractInfoDto dto = new ContractInfoDto();

        try {

            // 合同变更ID
            String id = (String) reqMap.get("contractChangeId");

//Mod By QJP 2017/05/25 合规性 start
//			// 获取页面字段值，转为DTO
//			this.setDto(reqMap, dto, "update", id);
            Integer contractId = Integer.valueOf(reqMap.get("contractId").toString());
            int count = 0;
            try {
                count = stopContractService.queryStoreOfCountByConractId(contractId);
            } catch (Exception e2) {
            }

            int changeType = Integer.parseInt(reqMap.get("changeType").toString());
            if (changeType == DictionaryConstants.Contract_ChangeType_B2A) {
                // 获取页面字段值，转为DTO
                this.setB2ADto(reqMap, dto, "update", id);
            } else {
                this.setDto(reqMap, dto, "update", id, count);
            }
//Mod By QJP 2017/05/25 合规性 end		

        } catch (Exception e1) {
            logger.error("contract", "ContractChangeController", "doUpdate", "",
                    UserInfoHolder.getUserId(), "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "合同变更参数设置失败！");
            return getOperateJSONView(rspMap);
        }

        //验证地址是否重复
        if ("1".equals(reqMap.get("storechange"))) {
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("CityNo", reqMap.get("storeCity"));
                map.put("DistrictNo", reqMap.get("storeDistrict"));
                map.put("Address", reqMap.get("storeAddress"));
                map.put("contractChangeId", reqMap.get("contractChangeId"));

                ResultData<?> resultDate = storeService.checkAddress(map);
                if (resultDate.getReturnCode().equals(ReturnCode.FAILURE)) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "系统中已存在相同的门店地址，请重新填写!");
                    return getOperateJSONView(rspMap);
                }
            } catch (Exception e) {
                logger.error("contract", "ContractChangeController", "doSave", "",
                        UserInfoHolder.getUserId(), "", "", e);
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "验证置失败！");
                return getOperateJSONView(rspMap);
            }
        }

		//校验门店是否有审核中的保证金收款或退款记录 add by haidan 2018/03/23
        if(reqMap.get("mainchange") != null && reqMap.get("mainchange").toString().equals("1")) {
    		String storeNo = (String) reqMap.get("storeNoArray");
    		try{
    			ResultData<Integer> resultRecord = this.storeDepositService.checkStoreDepositLock(storeNo);
    			if(resultRecord.getReturnCode().equals(ReturnCode.SUCCESS)){
    				Integer rel = resultRecord.getReturnData();
    				if (rel > 0) {
    					rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    					rspMap.put(Constant.RETURN_MSG_KEY, "该乙转甲的门店存在审核中的保证金收款/退款记录，请处理完成后再做乙转甲操作。");
    					return getOperateJSONView(rspMap);
    				}
    			}
    		}catch (Exception e){
    			logger.error("contract", "ContractChangeController", "checkStoreDepositLock", "", UserInfoHolder.getUserId(), "", "", e);
    			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    			rspMap.put(Constant.RETURN_MSG_KEY, "校验门店是否有审核中的保证金收款或退款记录失败！");
    			return getOperateJSONView(rspMap);
    		}
        }

        try {

            // 保存操作
            stopContractService.update(dto);

        } catch (Exception e) {
            logger.error("contract", "ContractChangeController", "doUpdate", "",
                    UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新合同变更失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getSearchJSONView(rspMap);
    }

	/**
	 * 查看合同变更申请信息
	 * 
	 * @param id
	 *            合同变更ID
	 * @param contractId
	 *            合同ID
	 * @param mop
	 * @return
	 */
	@RequestMapping(value = "toView/{id}/{contractId}/{contractStatus}", method = RequestMethod.GET)
	public String toView(@PathVariable("id") Integer id,
			@PathVariable("contractId") int contractId,@PathVariable("contractStatus") Integer contractStatus, ModelMap mop) {
		// 返回map
		ResultData<?> resultData = new ResultData<>();
		try {
			// 根据合同变更ID查询变更申请信息
			resultData = stopContractService.queryById(id);

		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toView", "",
					UserInfoHolder.getUserId(), "", "合同变更信息查询失败！", e);
		}
		// 存放到mop中
		mop.addAttribute("contractChange", resultData.getReturnData());
		// 获取页面上门店列表的数据
		List<?> storeList = new ArrayList<>();
		try {
			// 根据合同ID查询门店信息
			ResultData<?> reback = stopContractService.queryStoreByContractChangeId(id , contractId);

			// 页面数据
			storeList = (List<?>) reback.getReturnData();
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toView", "",
					UserInfoHolder.getUserId(), "", "门店信息查询失败！", e);
		}
		// 存放到mop中
		mop.addAttribute("storeList", storeList);
		mop.addAttribute("contractStatus", contractStatus);

		//取出变更类型
		int changeType = 0;
		Map<?,?> datamap = (Map<?,?>)resultData.getReturnData();
		changeType = Integer.parseInt( datamap.get("changeType").toString());
	    
		//根据变更类型返回页面
		String   url;
		if(changeType == DictionaryConstants.Contract_ChangeType_B2A)
		{
			//返回视图
		    try {
		        ResultData<?> reback = cityService.queryCityListByIsService();
                List<?> citylist = (List<?>) reback.getReturnData();
                mop.addAttribute("citylist", citylist);
                
                if(datamap.get("cityNo") != null && !"".equals(datamap.get("cityNo") )) {
                    String cityNo = datamap.get("cityNo").toString();
                    linkageCityService.getDistrictList(cityNo);
                    mop.addAttribute("districtList", linkageCityService.getDistrictList(cityNo).getReturnData());
                }
                mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.AGREEMENT_TYPE + ""));
            } catch (Exception e) {
                logger.error("contract", "ContractChangeController", "toView", "",
                        UserInfoHolder.getUserId(), "", "门店信息查询失败！", e);
            }
			url = "contract/contractA2BChangeView";	
		}else{
			url = "contract/contractChangeView";		
		}
		return url;

	}

    /**
     * 删除变更记录
     * @param request
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.PUT)
    public ReturnView<?, ?> delete(HttpServletRequest request) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);

        // 合同变更ID
        Integer id = Integer.valueOf((String) reqMap.get("id"));
        try {
			ResultData<?> resultData = stopContractService.delete(id);
			rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
			rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
			return getSearchJSONView(rspMap);
        } catch (Exception e) {
            logger.error("contract", "ContractChangeController", "delete", "", UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "合同变更作废失败！");
            return getOperateJSONView(rspMap);
        }
    }

	
    /*------------------------------------------专用方法开始------------------------------------------   */ 	
    /**
     * 图片上传到OA
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "oa/upload", method = RequestMethod.POST)
    public ReturnView<?, ?> uploadToOA(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        //合同附件上传标示
        //上传到OA系统
        try
        {
            //返回附件Id
            Map<?,?> backMop = oaFileAssist(request);
            String attachmentId = (String)backMop.get("attachmentId");
            String fileName = (String)backMop.get("fileName");
            //check返回值
            boolean b = isNumeric(attachmentId);
            if (!b)
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                return getOperateJSONView(rspMap);
            }
            
            //保存至本地图片关系表
            ContractInfoDto contractInfoDto = new ContractInfoDto();
            ContractChangeDto changeDto = new ContractChangeDto();
            changeDto.setFileCode(attachmentId);
            changeDto.setFileName(fileName);
            changeDto.setFileTypeCode((String)reqMap.get("fileTypeId"));
            changeDto.setUserCreate(UserInfoHolder.getUserId());
            changeDto.setDelFlag(true);
            String contractChangePicId = (String)reqMap.get("contractChangePicId");
            if(StringUtil.isNotEmpty(contractChangePicId) && contractChangePicId != "undefined"){
                changeDto.setContractChangePicId(Integer.valueOf(contractChangePicId));
            }
            
            contractInfoDto.setContractChangeDto(changeDto);
            
            ResultData<?> result = stopContractService.createOAPicIdRecord(contractInfoDto);
            Integer picId = (Integer)result.getReturnData();
            if(picId == null || picId < 0){
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                return getOperateJSONView(rspMap);
            }else if(picId > 0){
                rspMap.put("picId", picId);
            }
            rspMap.put(Constant.RETURN_DATA_KEY, attachmentId);
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractChangeController", "uploadToOA", "", UserInfoHolder.getUserId(), "", "合同变更图片上传至OA调用出错", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * check返回值（正常数据-1650100010010654120，异常-1Login name does not exist）
    * @param str
    * @return
    */
    public static boolean isNumeric(String str)
    {
        if (StringUtil.isNotEmpty(str))
        {
            for (int i = 2; i < str.length() - 1; i++)
            {
                //从第三个字符开始check,如果非数字，就不过
                if (!Character.isDigit(str.charAt(i)))
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
        
        return true;
    }

    /** 
    * 上传操作-调OA文件上传系统
    * @param request
    * @return
    * @throws Exception
    */
    private Map oaFileAssist(HttpServletRequest request)
        throws Exception
    {
        String attachmentId = "";
        
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver =
            new CommonsMultipartResolver(request.getSession().getServletContext());
        
        Map mop = new HashMap();
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request))
        {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            String fileTypeId = multiRequest.getParameter("fileTypeId");
            String fileTypeName = this.spellFileTypeName(fileTypeId);
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            
            while (iter.hasNext())
            {
                MultipartFile importFile = multiRequest.getFile(iter.next());
                
                //文件名
                String fileName = importFile.getOriginalFilename();
                fileName = new StringBuffer(fileTypeName).append(fileName).toString();
                //实例化类
                FileAssist fileAssist = new FileAssist();
                
                File tempFile = null;
                //获取绝对路径
                String tempFilePath = SystemParam.getWebConfigValue("oaTempFilePath");
                String absolutePath = fileAssist.getTmpRealPath(request, fileName, tempFilePath);
                
                //如果上传图片大于3M，进行图片压缩，否则直接上传
                if(importFile.getSize() > 3500000){
                    //MultipartFile转File
                    CommonsMultipartFile cf= (CommonsMultipartFile)importFile; 
                    DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
                    File temp = fi.getStoreLocation();
                    //图片压缩到文件绝对路径,生成临时文件
                    Boolean boo = ImageCompressUtil.getInstance().imageCompressRatio(temp, absolutePath, 0.5d);
                    if(!boo){
                        logger.error("WeiphotoHelper", "upload", "imageCompressRadio", "", UserInfoHolder.getUserId(), "", "压缩图片出错", null);
                        throw new Exception("压缩图片出错");
                    }
                    //删除临时文件
                    temp.delete();tempFile = new File(absolutePath);
                }else{
                    //生成临时文件
                    tempFile = new File(absolutePath);
                    importFile.transferTo(tempFile);
                }  
                
                //上传ＯＡ系统此处有验证，需是主办人才行
                String senderLoginName = UserInfoHolder.get().getUserCode();
                //查询是否是经办人
                ResultData<?> backResult = oaOperatorService.getByUserCode(senderLoginName);
                
                Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
                
                OaOperatorDto oaOperatorDto = new OaOperatorDto();
                
                if (null != mapTemp)
                {
                    oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
                }
                
                String operCode = oaOperatorDto.getOperCode();
                // 如果当前用户不是经办人 则使用数据库中配置的默认值
                if (StringUtil.isEmpty(operCode))
                {
                    senderLoginName = SystemParam.getWebConfigValue("operCode");
                }
                //上传操作-调OA文件上传系统
                attachmentId = oaService.oaAbsolutePathUpload(absolutePath, fileName, senderLoginName);
               
                if(tempFile != null){
                    //删除临时文件
                   // tempFile.delete();
                    
                    //删除临时文件
                	String delfilePath=tempFile.getParent();
                    tempFile.delete();
                    if(delfilePath!=null){
                    	(new File(delfilePath)).delete();
                    }
                }
                mop.put("attachmentId", attachmentId);
                mop.put("fileName", fileName);
                
            }
        }
       
        return mop;
    }
    
    
    
    private String spellFileTypeName(String fileTypeCode) {
   	 StringBuffer sbf = new StringBuffer();
        switch (fileTypeCode) {
            case "1":
                sbf.append("营业执照_");
                break;
            case "2":
                sbf.append("法人身份证_");
                break;
            case "3":
                sbf.append("合同照片_");
                break;
            case "4":
                sbf.append("门店照片_");
                break;
            case "5":
                sbf.append("房友确认单_");
                break;
            case "6":
                sbf.append("其他_");
                break;
            case "1001":
           	 sbf.append("终止合作协议书_");
           	 break;
            case "1002":
           	 sbf.append("三方解约协议_");
           	 break;
            case "1003":
           	 sbf.append("保证金收据_");
           	 break;
            case "1004":
           	 sbf.append("已付装修款退还证明_");
           	 break;
            case "1005":
           	 sbf.append("照片_");
           	 break;
            case "1006":
           	 sbf.append("照片_");
           	 break;
            case "1007":
           	 sbf.append("新A合作协议书_");
           	 break;
            case "1008":
           	 sbf.append("一事一议终止方案_");
           	 break;
            case "1009":
           	 sbf.append("其他_");
           	 break;
            case "1010":
           	 sbf.append("变更补充条款_");
           	 break;
            case "1011":
           	 sbf.append("新营业执照_");
           	 break;
            case "1012":
           	 sbf.append("国家企业信用信息公示系统网站截图_");
           	 break;
            case "1013":
           	 sbf.append("房友确认单_");
           	 break;
            case "1014":
           	 sbf.append("门店照片_");
           	 break;
            case "1015":
           	 sbf.append("其他_");
           	 break;
            case "1016":
           	 sbf.append("权利义务转让三方协议书_");
           	 break;
            case "1017":
           	 sbf.append("新签合作协议书_");
           	 break;
            case "1018":
           	 sbf.append("国家企业信用信息公示系统网站截图_");
           	 break;
            case "1019":
           	 sbf.append("其他_");
           	 break;
            case "1020":
            	sbf.append("重要提示函_");
            	break;
        }
        return sbf.toString();
	}
    
    /*------------------------------------------专用方法结束------------------------------------------   */ 
    
    /**
     * 获取页面字段值，转为DTO
     * @param reqMap 页面传入参数
     * @param dto  合同变更信息 Dto
     * @param type 操作类型(保存、更新)
     * @param id  主键ID
     * @throws Exception
     */
    public  void setB2ADto(Map<String, Object> reqMap, ContractInfoDto dto,
			String type, String id) throws Exception {
    	// 合同变更门店关联信息 Dto
    	ContractChangeDto contractChangeDto = new ContractChangeDto();
    	// 合同ID
    	String contractId = (String) reqMap.get("contractId");
    	// 变更类型
    	String changeType = (String) reqMap.get("changeType");
    	// 备注
    	String remarks = (String) reqMap.get("remarks");
    	//公司经营范围变更
    	String changeCompanyName = (String) reqMap.get("companyrange");
    	//公司注册地址变更
    	String changeCompanyAdress = (String) reqMap.get("companychange");
    	//门店地址变更
    	String changeStoreAdress = (String) reqMap.get("storechange");
    	//签约主体变更
    	String changeCompany = (String) reqMap.get("mainchange");
    	//变更后公司注册地址城市
    	String companyCity = (String) reqMap.get("companyCity");
    	//变更后公司注册地址区域
    	String companyDistrict = (String) reqMap.get("companyDistrict");
    	//变更后公司注册地址
    	String companyAdresss = (String) reqMap.get("companyAddress");
    	//变更后门店地址城市
    	String storeCity = (String) reqMap.get("storeCity");
    	//变更后门店地址区域
    	String storeDistrict = (String) reqMap.get("storeDistrict");
    	//变更后门店注册地址
    	String storeAdresss = (String) reqMap.get("storeAddress");
    	storeAdresss = storeAdresss.trim();
    	//refIds
    	String fileRecordMainIds = (String) reqMap.get("fileRecordMainIds");
    	//picIds
    	//String contractChangePicIds = (String) reqMap.get("contractChangePicIds");
    	//新公司名称
    	String newCompanyName = (String) reqMap.get("newCompanyName");
    	//新公司法定代表人
    	String newLegalPerson = (String) reqMap.get("newLegalPerson");
    	//新公司注册地址城市
    	String newCompanyAddressCityNo = (String) reqMap.get("newCompanyAddressCityNo");
    	//新公司注册地址区域
    	String newCompanyAddressDistrictNo = (String) reqMap.get("newCompanyAddressDistrictNo");
    	//新公司注册地址具体地址
    	String newCompanyAddress = (String) reqMap.get("newCompanyAddress");
    	//新合作协议书编号
    	String newAgreementNo = ((String) reqMap.get("newAgreementNo")).trim();
    	//新合同合作生效日期
    	String newDateLifeStart = (String) reqMap.get("newDateLifeStart");
    	//新合同合作终止日期
    	String newDateLifeEnd = (String) reqMap.get("newDateLifeEnd");
    	
    	//dto.setContractChangePicIds(contractChangePicIds);
        dto.setFileRecordMainIds(fileRecordMainIds); 
        if ("create".equals(type)) {
			// 合同终止No
			String contractStopNo = SeqNoHelper.getInstance()
					.getSeqNoByTypeCode("TYPE_CONTRACTCHANGE");
			// 设置合同终止No
			contractChangeDto.setContractStopNo(contractStopNo);
			// 创建人
			contractChangeDto.setUserIdCreate(UserInfoHolder.getUserId());
			// 创建日期
			contractChangeDto.setDateCreate(new Date());
		} else if ("update".equals(type)) {
			// 更新人
			contractChangeDto.setUpdateCreate(UserInfoHolder.getUserId());
			// 更新日期
			contractChangeDto.setUpdateDate(new Date());
			// 合同变更ID（主键ID）
			contractChangeDto.setId(Integer.valueOf(id));
		}
		// 合同ID
		contractChangeDto.setContractId(Integer.valueOf(contractId));
		// 变更类型
		contractChangeDto.setChangeType(Integer.valueOf(changeType));
		 //公司经营范围变更
		if(changeCompanyName == null){
			contractChangeDto.setChangeCompanyName(0);
		}else{
			contractChangeDto.setChangeCompanyName(Integer.valueOf(changeCompanyName));
		}
		
		//公司注册地址变更
		if(changeCompanyAdress == null){
			contractChangeDto.setChangeCompanyAdress(0);
		}else{
			contractChangeDto.setChangeCompanyAdress(Integer.valueOf(changeCompanyAdress));
		}
		
		//门店注册地址变更
		if(changeStoreAdress == null){
			contractChangeDto.setChangeStoreAdress(0);
		}else{
			contractChangeDto.setChangeStoreAdress(Integer.valueOf(changeStoreAdress));
			contractChangeDto.setOldStoreAddressDetail(reqMap.get("oldStoreAddressDetail").toString());
		}

		//变更后公司注册地址城市
			contractChangeDto.setCompanyCity(companyCity.trim());
		//变更后公司注册地址区域
			contractChangeDto.setCompanyDistrict(companyDistrict.trim());
		//变更后公司注册地址
			contractChangeDto.setCompanyAdresss(companyAdresss.trim());
		//变更后门店地址城市
			contractChangeDto.setStoreCity(storeCity.trim());
		//变更后门店地址区域
			contractChangeDto.setStoreDistrict(storeDistrict.trim());
		//变更后门店注册地址
			contractChangeDto.setStoreAdresss(storeAdresss.trim());
		
		//签约主体变更						
		if(changeCompany == null){
			contractChangeDto.setChangeCompany(0);
			contractChangeDto.setNewCompanyName("");
			contractChangeDto.setNewLegalPerson("");
			contractChangeDto.setNewCompanyAddressCityNo("");
			contractChangeDto.setNewCompanyAddressDistrictNo("");
			contractChangeDto.setNewCompanyAddress("");
			contractChangeDto.setNewAgreementNo("");		
			contractChangeDto.setNewDateLifeStart(null);		
			contractChangeDto.setNewDateLifeEnd(null);		
	        contractChangeDto.setRemarks(null);
	        
	        //合同相关字段
            contractChangeDto.setAgreementType(null);
            contractChangeDto.setAuthRepresentative("");
            contractChangeDto.setAgentContact("");
            contractChangeDto.setTotleDepositFee(null);
            contractChangeDto.setPenaltyFee(null);
            contractChangeDto.setCompanyBankNo("");
            contractChangeDto.setBankAccount("");
            contractChangeDto.setAccountName("");
            contractChangeDto.setRecipients("");
            contractChangeDto.setCityNo("");
            contractChangeDto.setDistrictNo("");
            contractChangeDto.setRecipientsAddress("");
		}else
		{
			contractChangeDto.setChangeCompany(Integer.valueOf(changeCompany));
			contractChangeDto.setNewCompanyName(newCompanyName);
			contractChangeDto.setNewLegalPerson(newLegalPerson);
			contractChangeDto.setNewCompanyAddressCityNo(newCompanyAddressCityNo);
			contractChangeDto.setNewCompanyAddressDistrictNo(newCompanyAddressDistrictNo);
			contractChangeDto.setNewCompanyAddress(newCompanyAddress);
			contractChangeDto.setNewAgreementNo(newAgreementNo.toUpperCase());
			if(newDateLifeStart != null || newDateLifeStart != "")
			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
				Date date =  formatter.parse(newDateLifeStart);
				contractChangeDto.setNewDateLifeStart(date);
			}
			if(newDateLifeEnd != null || newDateLifeEnd != "")
			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
				Date date =  formatter.parse(newDateLifeEnd);
				contractChangeDto.setNewDateLifeEnd(date);
			}
			
			//合同相关字段
			contractChangeDto.setAgreementType(Integer.valueOf(reqMap.get("agreementType").toString()));
			contractChangeDto.setAuthRepresentative(reqMap.get("authRepresentative").toString());
			contractChangeDto.setAgentContact(reqMap.get("agentContact").toString());
			contractChangeDto.setTotleDepositFee(new BigDecimal(reqMap.get("totleDepositFee").toString()));
			contractChangeDto.setPenaltyFee(new BigDecimal(reqMap.get("penaltyFee").toString()));
			contractChangeDto.setCompanyBankNo(reqMap.get("companyBankNo").toString());
			contractChangeDto.setBankAccount(reqMap.get("bankAccount").toString());
			contractChangeDto.setAccountName(reqMap.get("accountName").toString());
			contractChangeDto.setRecipients(reqMap.get("recipients").toString());
			contractChangeDto.setCityNo(reqMap.get("cityNo").toString());
			contractChangeDto.setDistrictNo(reqMap.get("districtNo").toString());
			contractChangeDto.setRecipientsAddress(reqMap.get("recipientsAddress").toString());
		}
		
		// 备注
		contractChangeDto.setRemarks(remarks);
		// 删除标识
		contractChangeDto.setDelFlag(false);
		
		contractChangeDto.setOldCompanyId(Integer.valueOf(reqMap.get("oldCompanyId").toString()));
		if(reqMap.get("newCompanyId") != null && !"".equals(reqMap.get("newCompanyId"))) {
		    contractChangeDto.setNewCompanyId(Integer.valueOf(reqMap.get("newCompanyId").toString()));
		}
		String updateCompanyName = reqMap.get("updateCompanyName").toString();
        if(updateCompanyName != null && "1".equals(updateCompanyName)) {
            contractChangeDto.setUpdateCompanyName(1);
            contractChangeDto.setOldUpdateCompanyName(reqMap.get("oldUpdateCompanyName").toString());
            contractChangeDto.setNewUpdateCompanyName(reqMap.get("newUpdateCompanyName").toString());
        }
			
		dto.setContractChangeDto(contractChangeDto);
		
		// 关联门店列表
		List<StoreDto> storeDtoList = new ArrayList<StoreDto>();
		// 门店ID集合
		String storeIds = (String) reqMap.get("storeIdArray");
		if (storeIds != null && StringUtil.isNotEmpty(storeIds)) {
			String[] arrays = storeIds.split(",");
			for (int i = 0; i < arrays.length; i++) {
				StoreDto storeDto = new StoreDto();
				// 门店ID
				int storeId = Integer.valueOf(arrays[i]);
				storeDto.setStoreId(storeId);
				storeDtoList.add(storeDto);
			}
		}
		dto.setStoreDetails(storeDtoList);
		
    }
    
	/**
	 * 获取页面字段值，转为DTO
	 * 
	 * @param reqMap
	 *            页面传入参数
	 * @param dto
	 *            合同变更信息 Dto
	 * @param type
	 *            操作类型(保存、更新)
	 * @param id
	 *            主键ID
	 * @throws Exception
	 */
	public static void setDto(Map<String, Object> reqMap, ContractInfoDto dto,
			String type, String id,Integer count) throws Exception {

		// 合同变更门店关联信息 Dto
		ContractChangeDto contractChangeDto = new ContractChangeDto();
		// 合同ID
		String contractId = (String) reqMap.get("contractId");
		// 变更类型
		String changeType = (String) reqMap.get("changeType");
		// 是否一并解除
		//String isReleaseFlag = (String) reqMap.get("isReleaseFlag");
		// 终止类型
		String stopType = (String) reqMap.get("stopType");
		// 已收保证金额数 
		//String receivedAmount = (String) reqMap.get("receivedAmount");
		
		//保证金处理方式
		//String depositBalance = (String) reqMap.get("depositBalance");
		//保证金退还金额
		//String depositBackMoney = (String) reqMap.get("depositBackMoney");
		
		
		// 三方装修合同情况
		String decorateCNTSituate = (String) reqMap.get("decorateCNTSituate");
		// 装修情况
		//String decorateSituate = (String) reqMap.get("decorateSituate");
		// 翻牌模式
		String flopMode = (String) reqMap.get("flopMode");
		// 装修费用总金额
		//String decorateAmount = (String) reqMap.get("decorateAmount");
		// 装修公司
		// 已支付金额
		String payAmount = (String) reqMap.get("payAmount");
		// 合作终止时间
		String stopDate = (String) reqMap.get("stopDate");
		// 是否A转B
		String aConvertBFlag = (String) reqMap.get("aConvertBFlag");
		// 终止具体原因
		String stopReason = (String) reqMap.get("stopReason");
		// 终止方案阐述
		String stopDescribe = (String) reqMap.get("stopDescribe");
		// 备注
		String remarks = (String) reqMap.get("remarks");
		//refIds
		String fileRecordMainIds = (String) reqMap.get("fileRecordMainIds");
		//picIds
		String contractChangePicIds = (String) reqMap.get("contractChangePicIds");
		
		dto.setContractChangePicIds(contractChangePicIds);
		dto.setFileRecordMainIds(fileRecordMainIds);
		
		if ("create".equals(type)) {
			// 合同终止No
			String contractStopNo = SeqNoHelper.getInstance()
					.getSeqNoByTypeCode("TYPE_CONTRACTSTOP");
			// 设置合同终止No
			contractChangeDto.setContractStopNo(contractStopNo);
			// 创建人
			contractChangeDto.setUserIdCreate(UserInfoHolder.getUserId());
			// 创建日期
			contractChangeDto.setDateCreate(new Date());
		} else if ("update".equals(type)) {
			// 更新人
			contractChangeDto.setUpdateCreate(UserInfoHolder.getUserId());
			// 更新日期
			contractChangeDto.setUpdateDate(new Date());
			// 合同变更ID（主键ID）
			contractChangeDto.setId(Integer.valueOf(id));
		}
		// 合同ID
		contractChangeDto.setContractId(Integer.valueOf(contractId));
		// 变更类型
		contractChangeDto.setChangeType(Integer.valueOf(changeType));
		// 终止类型
		contractChangeDto.setStopType(Integer.valueOf(stopType));
		// 是否一并解除
		
		// 已收保证金额数
		//contractChangeDto.setReceivedAmount(new BigDecimal(receivedAmount));
		// 三方装修合同情况
		/*contractChangeDto.setDecorateCNTSituate(Integer.valueOf(decorateCNTSituate));
		// 装修情况
		contractChangeDto.setDecorateSituate(Integer.valueOf(decorateSituate));
		// 装修公司
		contractChangeDto.setDecorateCompany(decorateCompany);
		// 翻牌模式
		contractChangeDto.setFlopMode(Integer.valueOf(flopMode));
		// 装修费用总金额
		contractChangeDto.setDecorateAmount(new BigDecimal(decorateAmount));
		// 已支付金额
		contractChangeDto.setPayAmount(new BigDecimal(payAmount));*/
		
		//保证金处理
		/*contractChangeDto.setDepositBalance(Integer.valueOf(depositBalance));
		if(Integer.valueOf(depositBalance) == 21303){
			
			contractChangeDto.setDepositBackMoney(new BigDecimal(depositBackMoney));
		}else{
			contractChangeDto.setDepositBackMoney(new BigDecimal(0));
			
		}*/

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.isNotEmpty(stopDate)) {
			// 合作终止时间
			contractChangeDto.setStopDate(format.parse(stopDate));
		}
		// 是否A转B
		//contractChangeDto.setIsaTob(Integer.valueOf(aConvertBFlag));
		// 终止具体原因
		contractChangeDto.setStopReason(stopReason);
		// 终止方案阐述
		contractChangeDto.setStopDescribe(stopDescribe);
		//TODO需要加信息
		// 备注
		contractChangeDto.setRemarks(remarks);
		// 删除标识
		contractChangeDto.setDelFlag(false);


		// 关联门店列表
		List<StoreDto> storeDtoList = new ArrayList<StoreDto>();
		// 门店ID集合
		String storeIds = (String) reqMap.get("storeIdArray");
		if (storeIds != null && StringUtil.isNotEmpty(storeIds)) {
			String[] arrays = storeIds.split(",");
			int storeIdLength = arrays.length;
			if(storeIdLength == count){
				contractChangeDto.setIsReleaseFlag(16801);
			}else {
				contractChangeDto.setIsReleaseFlag(16802);
			}
			
			for (int i = 0; i < arrays.length; i++) {
				StoreDto storeDto = new StoreDto();
				// 门店ID
				int storeId = Integer.valueOf(arrays[i]);
				
				String receivedAmount = (String) reqMap.get("receivedAmount" +storeId);
				
				//保证金处理方式
				String depositBalance = (String) reqMap.get("depositBalance" +storeId);
				//保证金退还金额
				String depositBackMoney = (String) reqMap.get("depositBackMoney" +storeId);
				//装修类型
		    	String decorationType = (String) reqMap.get("decorationType"+storeId);
				//装修类型
		    	
				if(decorationType != null && !"".equals(decorationType)){
						storeDto.setDecorationType(Integer.parseInt(decorationType));
				}
				//装修公司
				String decorateCompany = (String) reqMap.get("decorateCompany"+storeId);
				if(decorateCompany !="" && decorateCompany !=null){
					storeDto.setDecorateCompany(decorateCompany);
				}
				//装修状态
				String decorateSituate = (String) reqMap.get("decorateSituate"+storeId);
				if(decorateSituate != null && !"".equals(decorateSituate)){
					storeDto.setDecorateSituate(Integer.parseInt(decorateSituate));
				}
				//装修金额
				String decorateAmount = (String) reqMap.get("decorateAmount"+storeId);
				if(!decorateAmount.isEmpty()){
					storeDto.setDecorateAmount(new BigDecimal(Integer.parseInt(decorateAmount)));
				}else{
					storeDto.setDecorateAmount(new BigDecimal(0));
				}
				storeDto.setStoreId(storeId);
				storeDto.setReceivedAmount(new BigDecimal(receivedAmount));
				storeDto.setDepositBalance(Integer.parseInt(depositBalance));
				if(Integer.parseInt(depositBalance) == 21303){
					storeDto.setDepositBackMoney(new BigDecimal(depositBackMoney));
				}else if (Integer.parseInt(depositBalance) == 21301) {
					storeDto.setDepositBackMoney(new BigDecimal(receivedAmount));
				}else{
					storeDto.setDepositBackMoney(new BigDecimal(0.00));
				}
				storeDtoList.add(storeDto);
			}
		}
		
		dto.setContractChangeDto(contractChangeDto);
		dto.setStoreDetails(storeDtoList);
	}
	
	/**
	 * 判断合同中有无甲类门店
	 * @param contractId 合同ID
	 * @return
	 * @throws Exception
	 */
    public boolean hasATypeStore(Integer contractId) throws Exception
    {
    	boolean rtn = false;
		// 根据合同ID查询门店信息
    	ResultData<?> resultData = stopContractService.queryStoreOfAdd(contractId);
    	
    	 List<Map<?,?>> datamap = (List<Map<?,?>>)resultData.getReturnData();
    	 int abtypeType = 0;
		 for(Map<?,?> mop : datamap)
		 {
			 
			 String type = mop.get("abtypeStore")+"";
			 if(type != null && !"".equals(type) && !"null".equals(type))
			 {
				 abtypeType = Integer.parseInt(type);
			 }
			 
			 if(abtypeType == DictionaryConstants.Store_QualityGrade_A)
			 {
				 rtn = true;
				 break;
			 }
		 }
		 
		 return rtn;
    }	

    /**
     * 【描述】: 验证地址重复
     *
     * @author yinkun
     * @date: 2017年10月18日 上午10:31:43
     * @param request
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkStoreAddress", method = RequestMethod.GET)
    public ReturnView<?, ?> checkStoreAddress(HttpServletRequest request, ModelMap modelMap) {
        
        Map<String,Object> rspMap = new HashMap<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("CityNo", reqMap.get("storeCity"));
            map.put("DistrictNo", reqMap.get("storeDistrict"));
            map.put("Address", reqMap.get("storeAddress"));
            
               ResultData<?> resultData = storeService.checkAddress(map);
               if(resultData.getReturnCode().equals(ReturnCode.FAILURE)){
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "系统中已存在相同的门店地址，请重新填写!");
               }
            } catch (Exception e) {
                logger.error("contract", "ContractChangeController", "checkStoreAddress", "",
                           UserInfoHolder.getUserId(), "", "", e);
               rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
               rspMap.put(Constant.RETURN_MSG_KEY, "验证置失败！");
            }
        return getSearchJSONView(rspMap);
    }
    @RequestMapping(value = "getDepositBalance", method = RequestMethod.GET)
    @ResponseBody
	public Object getDepositBalance(ModelMap mop,HttpServletRequest request) {
		String flag = "0";
		Map<String, Object> reqMap = bindParamToMap(request);
		 Integer id = Integer.valueOf(reqMap.get("id").toString());
		 Integer contractId = Integer.valueOf(reqMap.get("contractId").toString());
		Object returnData2 = null;
		try {
			ResultData<?> returnData  = stopContractService.queryStoreByContractChangeId(id,contractId);
			if (returnData != null &&  returnData.getReturnCode().equals(ReturnCode.SUCCESS)) {
                returnData2 = returnData.getReturnData();
			}
		} catch (Exception e) {
			logger.error("crm", "ContractChangeController", "getDepositBalance", "",
                    UserInfoHolder.getUserId(), "", "", e);
		}
		return returnData2;
    }

	@ResponseBody
	@RequestMapping(value = "/operateAuditCt", method = RequestMethod.POST)
	public ReturnView<?, ?> operateAuditCt(HttpServletRequest request) {
		ResultData<?> rstData = new ResultData<>();
		//返回map
		Map<String, Object> rspMap = new HashMap<String, Object>();
		//获取map
		Map<String, Object> reqMap = bindParamToMap(request);
		String id = (String)reqMap.get("id");
		try {
			//更新
			rstData = stopContractService.operateAuditCt(Integer.valueOf(id));
		} catch (Exception e) {
			logger.error("stopcontract", "ContractChangeController", "operateAuditCt", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
			return getOperateJSONView(rspMap);
		}
		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
		rspMap.put(Constant.RETURN_MSG_KEY, rstData.getReturnMsg());
		return getOperateJSONView(rspMap);
	}
}
