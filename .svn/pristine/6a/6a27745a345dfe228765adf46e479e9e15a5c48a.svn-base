package cn.com.eju.pmls.contacts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.pmls.contacts.service.PmlsContactsService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.student.StudentDto;

/**   
* 联系人Controller  来源CRM
*/
@Controller
@RequestMapping(value = "pmlsContacts")
public class PmlsContactsController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "pmlsContactsService")
    private PmlsContactsService pmlsContactsService;
    
    /** 
    * 初始化
    * @param
    * @param
    * @return
    */
    @RequestMapping(value = "/store/{storeId}/{userCreate}", method = RequestMethod.GET)
    public String list(@PathVariable("storeId") int storeId,@PathVariable("userCreate") int userCreate, ModelMap mop)
    {
        //存放到mop中
        mop.put("storeId", storeId);
        mop.put("userCreate", userCreate);
        
        return "contacts/contactsList";
    }
    
    /** 
    * 查询--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "q", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> index(HttpServletRequest request, ModelMap mop)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        //原样组装CRM参数
        //reqMap.put("userCreate",UserInfoHolder.get().getUserId());
        reqMap.put("orderType","DESC");
        reqMap.put("orderName","IsDefault");

        
        //页面数据
//        List<?> contentlist = new ArrayList<>();
        reqMap.remove("userCreate");
        //获取当前用户及其下属用户Id集合, 用于页面权限过滤
        List<Integer> idsList = new ArrayList<>();
        
        //获取页面显示数据
        ResultData<?> reback = null;
        try
        {
            //页面权限数据，自己及其下属
            idsList = pmlsContactsService.getUserIdList();
            reqMap.put("userIdList", idsList);
            
            PageInfo pageInfo = getPageInfo(request);
            
            reback = pmlsContactsService.index(reqMap, pageInfo);
            
            //页面数据
//            contentlist = (List<?>)reback.getReturnData();
            
            
        }
        catch (Exception e)
        {
            logger.error("pmlsContacts", "PmlsContactsController", "index", JsonUtil.parseToJson(reqMap), 0, "", "", e);
        }

        //存放到mop中
//        mop.addAttribute("contentlist", contentlist);
        
        return reback;
    }


    /**
     * 修改--初始化
     * @param id
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") Integer id, ModelMap mop)
    {

        //返回map
        ResultData<?> resultData = new ResultData<>();

        try
        {
            resultData = pmlsContactsService.getById(id);
        }
        catch (Exception e)
        {

            logger.error("pmlsContacts", "PmlsContactsController", "toEdit", id + "", 0, "", "", e);
        }

        //存放到mop中
        mop.addAttribute("info", JSONObject.toJSON(resultData.getReturnData()));
        List<CommonCodeDto> sexList = new ArrayList<CommonCodeDto>();
        List<CommonCodeDto> commonCodeDtos = SystemParam.getCodeListByKey(DictionaryConstants.SEX_TYPE + "");
        for (int i = 0; i < commonCodeDtos.size(); i++) {
            CommonCodeDto dto = new CommonCodeDto();
            BeanUtils.copyProperties(commonCodeDtos.get(i), dto);
            sexList.add(dto);
        }
        mop.put("sexList", JSONObject.toJSON(sexList));
        mop.put("id", id);

        return "contacts/storeCntctsEditPopup";
    }

    /**
     *  修改
     *  @param request
     * @throws Exception
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> update(HttpServletRequest request,@RequestParam(value = "isform", required = false) String isform
            , @PathVariable("id") String id)
    {

        //返回map
        ResultData<?> resultData = new ResultData<>();

        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);

        reqMap.remove("_method");

        ContactsDto contactsDto = new ContactsDto();

        contactsDto.setId(Integer.valueOf(id));

        //获取页面字段值，转为DTO
        try
        {
            setDto(reqMap, contactsDto);
        }
        catch (Exception e1)
        {

            logger.error("Contacts", "ContactsController", "update", "", 0, "", "", e1);

            //rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            resultData.setFail("修改失败");

            return resultData;
        }

        try
        {
            //更新
            pmlsContactsService.update(contactsDto);
        }
        catch (Exception e)
        {

            logger.error("Contacts", "ContactsController", "update", "", 0, "", "", e);

            //rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            resultData.setFail("修改失败");

            return resultData;
        }

        //rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        resultData.setSuccess("操作成功");

        return resultData;
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "destroy/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> destroy(@PathVariable Integer id, HttpServletResponse response)
    {
        //返回map
        //返回map
        ResultData<?> resultData = new ResultData<>();

        try
        {
            //删除
            pmlsContactsService.delete(id);
        }
        catch (Exception e)
        {

            logger.error("pmlsContacts", "PmlsContactsController", "destroy", id + "", 0, "", "", e);

            //rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            resultData.setFail("修改失败");

            return resultData;
        }

        //rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        resultData.setSuccess("操作成功");
        return resultData;

    }

    /**
     * 获取页面字段值，转为DTO
     * @param reqMap
     * @param contactsDto
     */
    public static void setDto(Map<String, Object> reqMap, ContactsDto contactsDto)
    //        throws Exception
    {
        //获取页面字段值，转为DTO
        String name = (String)reqMap.get("name");
        String mobilePhone = (String)reqMap.get("mobilePhone");

        String email = (String)reqMap.get("email");
        String wechat = (String)reqMap.get("wechat");
        String qQ = (String)reqMap.get("qQ");

        // 联系地址
        String address = (String)reqMap.get("contactAddress");

        String remark = (String)reqMap.get("remark");

        String sexStr = (String)reqMap.get("sex");
        if (StringUtil.isNotEmpty(sexStr))
        {
            contactsDto.setSex(Integer.valueOf(sexStr));
        }

        String storeIdStr = (String)reqMap.get("storeId");

        if (StringUtil.isNotEmpty(storeIdStr))
        {
            contactsDto.setStoreId(Integer.valueOf(storeIdStr));
        }

        String userCreateStr = (String)reqMap.get("userCreate");

        if (StringUtil.isNotEmpty(storeIdStr))
        {
            contactsDto.setUserCreate(Integer.valueOf(userCreateStr));
        }

        //修改时设Id
        String contactIdStr = (String)reqMap.get("contactId");
        if (StringUtil.isNotEmpty(contactIdStr))
        {
            contactsDto.setId(Integer.valueOf(contactIdStr));
        }

        contactsDto.setName(name);
        contactsDto.setMobilePhone(mobilePhone);
        contactsDto.setEmail(email);
        contactsDto.setWechat(wechat);
        contactsDto.setqQ(qQ);
        contactsDto.setAddress(address);
        contactsDto.setRemark(remark);

        contactsDto.setUserCreate(UserInfoHolder.getUserId());

        String contactsNo = (String)reqMap.get("contactsNo");
        if (StringUtil.isEmpty(contactsNo))
        {
            contactsNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_CONTACT");
        }
//
//        //首要联系人
//        String isDefaultStr = (String)reqMap.get("isDefault");
//        if (StringUtil.isNotEmpty(isDefaultStr))
//        {
//            contactsDto.setIsDefault(Boolean.valueOf(isDefaultStr));
//        }

        contactsDto.setContactsNo(contactsNo);
    }












    /**
     * 创建--初始化
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value = "c/{storeId}/{userId}", method = RequestMethod.GET)
    public String toAdd(@PathVariable("storeId") int storeId,@PathVariable("userId") int userId, ModelMap mop)
    {

        mop.addAttribute("storeId", storeId);
        mop.addAttribute("userId", userId);
        //存放到mop中
        List<CommonCodeDto> sexList = new ArrayList<CommonCodeDto>();
        List<CommonCodeDto> commonCodeDtos = SystemParam.getCodeListByKey(DictionaryConstants.SEX_TYPE + "");
        for (int i = 0; i < commonCodeDtos.size(); i++) {
            CommonCodeDto dto = new CommonCodeDto();
            BeanUtils.copyProperties(commonCodeDtos.get(i), dto);
            sexList.add(dto);
        }
        mop.put("sexList", sexList);
        return "company/companyCntctsAddPopup";
    }*/

    /**
     *  创建
     *  @param request
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try
        {
            ContactsDto contactsDto = new ContactsDto();
            //是否是默认联系人，默认false
            contactsDto.setIsDefault(false);

            //获取页面字段值，转为DTO
            setDto(reqMap, contactsDto);

            ResultData<?> resultData = pmlsContactsService.create(contactsDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {

            logger.error("Contacts", "ContactsController", "create", JsonUtil.parseToJson(reqMap), 0, "", "", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }

        return getOperateJSONView(rspMap);
    }

    /**
     * 查看
     * @param id
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();

        try
        {
            resultData = pmlsContactsService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("pmlsContacts", "pmlsContactsController", "show", id + "", 0, "", "", e);
        }

        //存放到mop中
        mop.addAttribute("info", resultData.getReturnData());

        return "contacts/companyCntctsDetailPopup";
    }*/
}
