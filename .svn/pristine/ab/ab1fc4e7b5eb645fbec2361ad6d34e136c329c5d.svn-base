package cn.com.eju.pmls.channelBusiness.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.channelBusiness.dto.BusinessManagerDto;
import cn.com.eju.pmls.channelBusiness.service.BusinessManagerService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "businessManagerController")
public class BusinessManagerController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "businessManagerService")
    private BusinessManagerService businessManagerService;

    //商户管理页面
    @RequestMapping(value = "businessManagerList", method = RequestMethod.GET)
    public ModelAndView businessManagerList(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> map = bindParamToMap(request);
        if (map.containsKey("backTab")) {
            mop.addAttribute("backTab", (String) map.get("backTab"));
        }
        mop.addAttribute("userId",UserInfoHolder.get().getUserId());
        ModelAndView mv = new ModelAndView("channelBusiness/businessManagerList");
        return mv;
    }
    //选择维护人页面
    @RequestMapping(value = "/selectMaintainPage", method = RequestMethod.GET)
    public String selectBusinessPage(HttpServletRequest request, ModelMap mop){
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAllAttributes(reqMap);
        return "channelBusiness/selectMaintainPage";
    }

    //新增商户页面
    @RequestMapping(value = "addBusinessPage", method = RequestMethod.GET)
    public String addBusinessPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        ResultData<BusinessManagerDto> resultData = new ResultData<BusinessManagerDto>();
        try {
            List<CommonCodeDto> channelTypeList = SystemParam.getCodeListByKey("236");//渠道分类
            List<CommonCodeDto> channelLevelList = SystemParam.getCodeListByKey("274");//渠道等级

            model.addAttribute("channelTypeList", JsonUtil.parseToJson(channelTypeList));
            model.addAttribute("channelLevelList", JsonUtil.parseToJson(channelLevelList));

            if(id!=null && !"".equals(id)){//查询商户信息，进入修改页面
                Map<String, Object> reqMap = bindParamToMap(request);
                BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
                resultData = businessManagerService.getBusinessInfo(dto);
                model.addAttribute("id",id);
                model.addAttribute("businessDto", JSONObject.toJSON(resultData.getReturnData()));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "channelBusiness/addBusinessPage";
    }
    //商户详情页面
    @RequestMapping(value = "businessDetailPage", method = RequestMethod.GET)
    public String businessDetailPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        ResultData<BusinessManagerDto> resultData = new ResultData<BusinessManagerDto>();
        try {
            if(id!=null && !"".equals(id)){//查询商户信息，进入修改页面
                Map<String, Object> reqMap = bindParamToMap(request);
                BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
                resultData = businessManagerService.getBusinessInfo(dto);
                model.addAttribute("id",id);
                model.addAttribute("businessDto", JSONObject.toJSON(resultData.getReturnData()));
                model.addAttribute("userCityNo",UserInfoHolder.get().getCityNo());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "channelBusiness/businessDetailPage";
    }

    //新增联系人页面
    @RequestMapping(value = "addContactsPage", method = RequestMethod.GET)
    public String addContactsPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        String companyNo=request.getParameter("companyNo");
        ResultData<BusinessManagerDto> resultData = new ResultData<BusinessManagerDto>();
        try {
            if(id!=null && !"".equals(id)){//查询联系人信息，进入修改页面
              Map<String, Object> reqMap = bindParamToMap(request);
              BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
              resultData = businessManagerService.getContactsInfo(dto);
              model.addAttribute("id",id);
              model.addAttribute("contactDto", JSONObject.toJSON(resultData.getReturnData()));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("companyNo",companyNo);
        return "channelBusiness/addContactsPage";
    }

    /**
     * 查询商户管理列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBusinessManagerList", method = RequestMethod.POST)
    public ResultData<?> getBusinessManagerList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("acCityNo",UserInfoHolder.get().getCityNo());

            //获取页面显示数据
            resultData = businessManagerService.getBusinessManagerList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BusinessManager",
                    "BusinessManagerController",
                    "getBusinessManagerList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询商户管理列表异常",
                    e);
        }

        return resultData;
    }

    //获取商户信息
    @ResponseBody
    @RequestMapping(value = "getBusinessInfo", method = RequestMethod.POST)
    public ResultData<?> getBusinessInfo(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");

            resultData = businessManagerService.getBusinessInfo(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "getBusinessInfo",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询商户信息异常",
                    e);
        }
        return resultData;
    }
    //根据公司名和统一社会信用代码获取商户信息
    @ResponseBody
    @RequestMapping(value = "getBusinessInfo2", method = RequestMethod.POST)
    public ResultData<?> getBusinessInfo2(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            resultData = businessManagerService.getBusinessInfo2(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "getBusinessInfo2",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询商户信息异常",
                    e);
        }
        return resultData;
    }
    //新增商户
    @ResponseBody
    @RequestMapping(value = "addBusiness", method = RequestMethod.POST)
    public ResultData<?> addBusiness(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            String companyNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_COMPANY");//商户编号
            String storeNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_STORE");//门店编号
            dto.setUserCreate(UserInfoHolder.getUserId().toString());
            dto.setAcCityNo(UserInfoHolder.get().getCityNo());
            dto.setCompanyNo(companyNo);
            dto.setStoreNo(storeNo);

            resultData = businessManagerService.addBusiness(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "addBusiness",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增商户信息异常",
                    e);
        }
        return resultData;
    }
    //修改商户
    @ResponseBody
    @RequestMapping(value = "updateBusiness", method = RequestMethod.POST)
    public ResultData<?> updateBusiness(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            dto.setUserCreate(UserInfoHolder.getUserId().toString());
            resultData = businessManagerService.updateBusiness(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "updateBusiness",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改商户信息异常",
                    e);
        }
        return resultData;
    }
    //删除商户
    @ResponseBody
    @RequestMapping(value = "deleteBusiness", method = RequestMethod.POST)
    public ResultData<?> deleteBusiness(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            resultData = businessManagerService.deleteBusiness(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "deleteBusiness",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "删除商户信息异常",
                    e);
        }
        return resultData;
    }
    //新增联系人
    @ResponseBody
    @RequestMapping(value = "addContacts", method = RequestMethod.POST)
    public ResultData<?> addContacts(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            dto.setUserCreate(UserInfoHolder.getUserId().toString());
            resultData = businessManagerService.addContacts(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "addContacts",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增联系人",
                    e);
        }
        return resultData;
    }
    //修改联系人
    @ResponseBody
    @RequestMapping(value = "updateContacts", method = RequestMethod.POST)
    public ResultData<?> updateContacts(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            dto.setUserCreate(UserInfoHolder.getUserId().toString());
            resultData = businessManagerService.updateContacts(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "updateContacts",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改联系人",
                    e);
        }
        return resultData;
    }

    //获取联系人列表
    @ResponseBody
    @RequestMapping(value = "getContactsList", method = RequestMethod.POST)
    public ResultData<?> getContactsList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = businessManagerService.getContactsList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BusinessManager",
                    "BusinessManagerController",
                    "getContactsList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询联系人列表异常",
                    e);
        }

        return resultData;
    }

    //获取联系人信息
    @ResponseBody
    @RequestMapping(value = "getContactsInfo", method = RequestMethod.POST)
    public ResultData<?> getContactsInfo(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            resultData = businessManagerService.getContactsInfo(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "getContactsInfo",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询联系人信息异常",
                    e);
        }
        return resultData;
    }

    //获取公司日志列表
    @ResponseBody
    @RequestMapping(value = "getOperationLogList", method = RequestMethod.POST)
    public ResultData<?> getOperationLogList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = businessManagerService.getOperationLogList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BusinessManager",
                    "BusinessManagerController",
                    "getOperationLogList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "获取公司日志列表异常",
                    e);
        }

        return resultData;
    }

    //新增商户城市关系表
    @ResponseBody
    @RequestMapping(value = "addCompanyReleaseCity", method = RequestMethod.POST)
    public ResultData<?> addCompanyReleaseCity(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            dto.setUserCreate(UserInfoHolder.getUserId().toString());
            dto.setAcCityNo(UserInfoHolder.get().getCityNo());
            resultData = businessManagerService.addCompanyReleaseCity(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "addCompanyReleaseCity",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增商户城市关系表",
                    e);
        }
        return resultData;
    }
    //显示OA附件
    @RequestMapping(value = "showOaFile", method = RequestMethod.GET)
    public String showOaFile(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            String requestUrl=SystemParam.getWebConfigValue("frameFileUrl")+"/"+reqMap.get("url").toString();
            System.out.println("====下载盖章合同附件URL:"+requestUrl);
            InputStream inputStream = null;
            OutputStream out = null;
            try {
                //根据文件在服务器的路径读取该文件转化为流
                URL url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(60 * 1000);
                inputStream = conn.getInputStream();
                //创建一个Buffer字符串
                byte[] buffer = new byte[1024];
                String fileName = reqMap.get("fileName").toString();      //abcd.png
                //设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                //设置文件头：最后一个参数是设置下载文件名（设置编码格式防止下载的文件名乱码）
                response.setHeader("Content-Disposition", "attachment;fileName="+new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
                out = response.getOutputStream();
                int b = 0;
                while (b != -1){
                    b = inputStream.read(buffer);
                    //写到输出流(out)中
                    out.write(buffer,0,b);
                }
                conn.disconnect();
            }catch (Exception e){
                System.out.println(e.getMessage());
//                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                    out.close();
                    out.flush();
                }catch (Exception e){
                    System.out.println(e.getMessage());
//                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "showOaFile",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "显示OA附件",
                    e);
        }
        return null;
    }

    /**
     * 查询维护人列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMaintainerList", method = RequestMethod.POST)
    public ResultData<?> getMaintainerList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("cityNo",UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = businessManagerService.getMaintainerList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BusinessManager",
                    "BusinessManagerController",
                    "getMaintainerList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询维护人列表",
                    e);
        }

        return resultData;
    }
    /**
     * 查询公司维护人记录列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCompanyMaintainerList", method = RequestMethod.POST)
    public ResultData<?> getCompanyMaintainerList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = businessManagerService.getCompanyMaintainerList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BusinessManager",
                    "BusinessManagerController",
                    "getCompanyMaintainerList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询公司维护人记录列表",
                    e);
        }

        return resultData;
    }

    //修改维护人
    @ResponseBody
    @RequestMapping(value = "updateMaintainer", method = RequestMethod.POST)
    public ResultData<?> updateMaintainer(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            dto.setUserCreate(UserInfoHolder.getUserId().toString());
            resultData = businessManagerService.updateMaintainer(dto);
        } catch (Exception e) {
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "updateMaintainer",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改维护人",
                    e);
        }
        return resultData;
    }

    @ResponseBody
    @RequestMapping(value = "adjustToProcuration", method = RequestMethod.POST)
    public ResultData<?> adjustToProcuration(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            dto.setUserCreate(UserInfoHolder.getUserId().toString());
            resultData = businessManagerService.adjustToProcuration(dto);
        } catch (Exception e) {
            logger.error("调整是否借佣渠道异常",e);
            logger.error("BusinessManagerDto",
                    "BusinessManagerController",
                    "adjustToProcuration",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "调整是否借佣渠道异常",
                    e);
        }
        return resultData;
    }


    @ResponseBody
    @RequestMapping(value = "getCompanyStoreList", method = RequestMethod.POST)
    public ResultData<?> getCompanyStoreList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = businessManagerService.getCompanyStoreList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("查询公司门店记录列表",e);
            logger.error("BusinessManager",
                    "BusinessManagerController",
                    "getCompanyStoreList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询公司门店记录列表",
                    e);
        }

        return resultData;
    }


}
