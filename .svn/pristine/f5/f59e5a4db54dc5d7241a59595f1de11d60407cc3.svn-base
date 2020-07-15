package cn.com.eju.deal.company.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.company.service.CompanyStoreService;
import cn.com.eju.deal.company.service.GpCompanyStoreService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.company.GpCompanyStoreDto;


/**   
* 公司门店关系
* @author 张文辉
* @date 2016年7月4日 下午5:19:37
*/
@Controller
@RequestMapping(value = "companyStore")
public class CompanyStoreController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "companyStoreService")
    private CompanyStoreService companyStoreService;
    @Resource(name = "gpcompanyStoreService")
    private GpCompanyStoreService gpcompanyStoreService;
    
    /**
     *  创建comanyStore关系
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> createCompanyStore(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            String storeIdArr = request.getParameter("storeIdArr");
            String companyId = request.getParameter("companyId");
            String[] storeIds = storeIdArr.split(",");
            for (int i = 0; i<storeIds.length; i++) {
                CompanyStoreDto companyStoreDto = new CompanyStoreDto();
                companyStoreDto.setStoreId(Integer.valueOf(storeIds[i]));
                companyStoreDto.setCompanyId(Integer.parseInt(companyId));
                companyStoreService.createCompanyStore(companyStoreDto);
            }
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("CompanyStore", "CompanyStoreController", "createCompanyStore", "", UserInfoHolder.getUserId(), "", "", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     *  删除comanyStore关系
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "deleteRelate", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> deleteRelate(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String storeId = request.getParameter("storeId");
        String companyId = request.getParameter("companyId");
        CompanyStoreDto companyStoreDto = new CompanyStoreDto();
        companyStoreDto.setStoreId(Integer.valueOf(storeId));
        companyStoreDto.setCompanyId(Integer.valueOf(companyId));
        try
        {
            companyStoreService.deleteRelate(companyStoreDto);
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("CompanyStore", "CompanyStoreController", "deleteCompanyStore", "", UserInfoHolder.getUserId(), "", "", e);
        }
        return getOperateJSONView(rspMap);
    }
    /*---公盘门店关系  -*/
    

    /**
     *  创建comanyStore关系
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "gpCsAdd", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> createGpCompanyStore(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            String storeIdArr = request.getParameter("storeIdArr");
            String companyId = request.getParameter("companyId");
            String[] storeIds = storeIdArr.split(",");
            for (int i = 0; i<storeIds.length; i++) {
                GpCompanyStoreDto companyStoreDto = new GpCompanyStoreDto();
                companyStoreDto.setStoreId(Integer.valueOf(storeIds[i]));
                companyStoreDto.setCompanyId(Integer.parseInt(companyId));
                gpcompanyStoreService.createGpCompanyStore(companyStoreDto);
            }
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("CompanyStore", "CompanyStoreController", "createCompanyStore", "", UserInfoHolder.getUserId(), "", "", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     *  删除gpcomanyStore关系
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "deletegpRelate", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> deletegpRelate(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String storeId = request.getParameter("storeId");
        String companyId = request.getParameter("companyId");
        GpCompanyStoreDto companyStoreDto = new GpCompanyStoreDto();
        companyStoreDto.setStoreId(Integer.valueOf(storeId));
        companyStoreDto.setCompanyId(Integer.valueOf(companyId));
        try
        {
        	gpcompanyStoreService.deletegpRelate(companyStoreDto);
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("CompanyStore", "CompanyStoreController", "deleteCompanyStore", "", UserInfoHolder.getUserId(), "", "", e);
        }
        return getOperateJSONView(rspMap);
    }
}
