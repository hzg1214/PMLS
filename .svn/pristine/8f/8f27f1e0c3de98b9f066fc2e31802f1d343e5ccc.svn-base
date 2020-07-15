package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.pmls.commission.dto.CommissionImportDto;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("pmlsKfCommissionService")
public class PmlsKfCommissionService extends BaseService<CommissionImportDto> {


    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commission" + "/queryList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData<?> add(CommissionImportDto dto) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "commission" + "/add";

        ResultData reback = post(url, dto);

        return reback;
    }

    public ResultData<?> queryCityList(Map<String, Object> reqMap) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "commission" + "/queryCityList/{param}";

        ResultData reback = get(url, reqMap);

        return reback;
    }

    public ResultData<?> queryBusinessTypeList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "commission" + "/queryBusinessTypeList/{param}";
        ResultData reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> checkAccount(CommissionImportDto dto) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "commission" + "/checkAccount";

        ResultData reback = post(url, dto);

        return reback;
    }

    public ResultData<?> importExcel(CommonsMultipartFile file, CommissionImportDto checkDto, HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();

        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            List<CommissionImportDto> list = new ArrayList<>();

            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow row = xssfSheet.getRow(rowNum);
                if (rowNum == 0) {
                    if (!checkExcelHeadName(resultData, checkDto.getImportType(), row)) {
                        return resultData;
                    }
                } else {
                    CommissionImportDto dto = new CommissionImportDto();
                    dto.setCreateUser(((UserInfo) WebUtil.getValueFromSession(request, "userInfo")).getUserCode());
                    //dto.setCreateUser(UserInfoHolder.get().getUserCode());//
                    dto.setCityNo(checkDto.getCityNo());
                    dto.setDateMonth(checkDto.getDateMonth());

                    int colIndex = 0;
                    String accountProjectName = getValue(row.getCell(colIndex++));
                    if (isEmpty(resultData, rowNum, accountProjectName, "核算主体")) {
                        return resultData;
                    }
                    dto.setAccountProjectName(accountProjectName);

                    String accountProjectCode = getValue(row.getCell(colIndex++));
                    if (isEmpty(resultData, rowNum, accountProjectCode, "核算主体编码")) {
                        return resultData;
                    }
                    dto.setAccountProjectCode(accountProjectCode);

                    String checkBodyName = getValue(row.getCell(colIndex++));
                    if (isEmpty(resultData, rowNum, checkBodyName, "考核主体")) {
                        return resultData;
                    }
                    dto.setCheckBodyName(checkBodyName);

                    String checkBodyCode = getValue(row.getCell(colIndex++));
                    if (isEmpty(resultData, rowNum, checkBodyCode, "考核主体编码")) {
                        return resultData;
                    }
                    dto.setCheckBodyCode(checkBodyCode);

                    String costName = getValue(row.getCell(colIndex++));
                    dto.setCostName(costName);

                    String costCode = getValue(row.getCell(colIndex++));
                    dto.setCostCode(costCode);

                    String projectName = getValue(row.getCell(colIndex++));
                    dto.setProjectName(projectName);

                    String projectNo = getValue(row.getCell(colIndex++));
                    dto.setProjectNo(projectNo);

                    if ("kf".equals(checkDto.getImportType())) {
                        String userName = getValue(row.getCell(colIndex++));
                        if (isEmpty(resultData, rowNum, userName, "姓名")) {
                            return resultData;
                        }
                        dto.setUserName(userName);

                        String userCode = getValue(row.getCell(colIndex++));
                        if (isEmpty(resultData, rowNum, userCode, "工号")) {
                            return resultData;
                        }
                        dto.setUserCode(userCode);
                    }

                    String serviceType = getValue(row.getCell(colIndex++));
                    if (isEmpty(resultData, rowNum, serviceType, "业务类型")) {
                        return resultData;
                    } else {
                        if (checkBusinessType(resultData, rowNum, serviceType, "业务类型")) {
                            return resultData;
                        }
                        //联动数据  19201
                        if ("19201".equals(checkDto.getBusinessTypeCode()) && !"联动".equals(serviceType)) {
                            resultData.setFail("第" + (rowNum + 1) + "行业务类型不匹配，当前选择业务类型为联动！");
                            return resultData;
                        }
                        //非联动 19202
                        if ("19202".equals(checkDto.getBusinessTypeCode()) && "联动".equals(serviceType)) {
                            resultData.setFail("第" + (rowNum + 1) + "行业务类型不匹配，当前选择业务类型为非联动！");
                            return resultData;
                        }
                    }
                    dto.setServiceType(serviceType);

                    String jobBonus = getValue(row.getCell(colIndex++));
                    if (isEmpty(resultData, rowNum, jobBonus, "岗位佣金")) {
                        return resultData;
                    }
                    dto.setJobBonus(new BigDecimal(jobBonus));

                    String teamBonus = getValue(row.getCell(colIndex++));
                    if (isEmpty(resultData, rowNum, teamBonus, "团奖")) {
                        return resultData;
                    }
                    dto.setTeamBonus(new BigDecimal(teamBonus));
                    dto.setDate(getValue(row.getCell(colIndex)));

                    //项目和成本只能填一个
                    boolean isCostSet = !StringUtils.isEmpty(dto.getCostCode()) || !StringUtils.isEmpty(dto.getCostName());
                    boolean isProjectSet = !StringUtils.isEmpty(dto.getProjectNo()) || !StringUtils.isEmpty(dto.getProjectName());
                    if (isCostSet && isProjectSet) {
                        resultData.setFail("项目和成本只能填一个！");
                        return resultData;
                    }

                    if (isCostSet) {
                        if (isEmpty(resultData, rowNum, dto.getCostName(), "成本中心")) {
                            return resultData;
                        }
                        if (isEmpty(resultData, rowNum, dto.getCostCode(), "成本中心编码")) {
                            return resultData;
                        }
                    }

                    if (isProjectSet) {
                        if (isEmpty(resultData, rowNum, dto.getProjectName(), "项目")) {
                            return resultData;
                        }
                        if (isEmpty(resultData, rowNum, dto.getProjectNo(), "项目编码")) {
                            return resultData;
                        }
                    }

                    if (isEmpty(resultData, rowNum, dto.getDate(), "日期")) {
                        return resultData;
                    } else {
                        if (!checkDto.getDateMonth().equals(dto.getDate().substring(0, 7))) {
                            resultData.setFail("第" + (rowNum + 1) + "行日期不属于所选时间月份，请注意所有日期都要属于所选月份！");
                            return resultData;
                        }
                    }

                    list.add(dto);
                }
            }

            CommissionImportDto dto = new CommissionImportDto();
            dto.setCityNo(checkDto.getCityNo());
            dto.setDateMonth(checkDto.getDateMonth());
            dto.setImportDtos(list);
            dto.setImportType(checkDto.getImportType());
            dto.setBusinessTypeCode(checkDto.getBusinessTypeCode());
            if (!list.isEmpty()) {
                resultData = this.add(dto);
            } else {
                resultData.setFail("导入失败：表格无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setFail("导入异常");
        }

        return resultData;
    }

    private boolean checkBusinessType(ResultData resultData, int rowNum, String obj, String msgName) {
        String businessTypeArray[] = {"拓展A", "拓展B", "交易", "联动", "软件端口", "MLS成交","授牌","维护","金融","MLS会员","其他","门店续签","代理收入"};
        boolean flag = true;
        for (String businessType : businessTypeArray) {
            if (businessType.equals(obj)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            resultData.setFail("第" + (rowNum + 1) + "行业务类型错误，请注意所有业务类型都必须是拓展A,拓展B,交易,联动,软件端口,MLS成交,授牌,维护,金融,MLS会员,其他,门店续签,代理收入其中一个！");
            return true;
        }
        return false;
    }

    private boolean isEmpty(ResultData resultData, int rowNum, String obj, String msgName) {
        if (StringUtils.isEmpty(obj)) {
            resultData.setFail("第" + (rowNum + 1) + "行" + msgName + "不能空值，请注意所有" + msgName + "都不能空值或含有公式！");
            return true;
        }
        return false;
    }

    private boolean checkExcelHeadName(ResultData<?> resultData, String importType, XSSFRow row) {
        int index = 0;
        if (!row.getCell(index++).toString().equals("核算主体")) {
            resultData.setFail("格式错误：第" + index + "列列名不是核算主体");
            return false;
        } else if (!row.getCell(index++).toString().equals("核算主体编码")) {
            resultData.setFail("格式错误：第" + index + "列列名不是核算主体编码");
            return false;
        } else if (!row.getCell(index++).toString().equals("考核主体")) {
            resultData.setFail("格式错误：第" + index + "列列名不是考核主体");
            return false;
        } else if (!row.getCell(index++).toString().equals("考核主体编码")) {
            resultData.setFail("格式错误：第" + index + "列列名不是考核主体编码");
            return false;
        } else if (!row.getCell(index++).toString().equals("成本中心")) {
            resultData.setFail("格式错误：第" + index + "列列名不是成本中心");
            return false;
        } else if (!row.getCell(index++).toString().equals("成本中心编码")) {
            resultData.setFail("格式错误：第" + index + "列列名不是成本中心编码");
            return false;
        } else if (!row.getCell(index++).toString().equals("项目")) {
            resultData.setFail("格式错误：第" + index + "列列名不是项目");
            return false;
        } else if (!row.getCell(index++).toString().equals("项目编码")) {
            resultData.setFail("格式错误：第" + index + "列列名不是项目编码");
            return false;
        }

        String dateName = "计提时间";
        if ("kf".equals(importType)) {
            if (!row.getCell(index++).toString().equals("姓名")) {
                resultData.setFail("格式错误：第" + index + "列列名不是姓名");
                return false;
            } else if (!row.getCell(index++).toString().equals("工号")) {
                resultData.setFail("格式错误：第" + index + "列列名不是工号");
                return false;
            }
            dateName = "可发日期";
        }

        if (!row.getCell(index++).toString().equals("业务类型")) {
            resultData.setFail("格式错误：第" + index + "列列名不是业务类型");
            return false;
        } else if (!row.getCell(index++).toString().equals("岗位佣金")) {
            resultData.setFail("格式错误：第" + index + "列列名不是岗位佣金");
            return false;
        } else if (!row.getCell(index++).toString().equals("团奖")) {
            resultData.setFail("格式错误：第" + index + "列列名不是团奖");
            return false;
        } else if (!row.getCell(index++).toString().equals(dateName)) {
            resultData.setFail("格式错误：第" + index + "列列名不是" + dateName);
            return false;
        }

        return true;
    }

    private String getValue(XSSFCell cell) {
        String result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_NUMERIC: // 数字
                    if (DateUtil.isCellDateFormatted(cell)) {
                        result = DateUtils.formatDate(cell.getDateCellValue());
                    } else {
                        result = String.valueOf((cell.getNumericCellValue()));
                    }
                    break;
                case XSSFCell.CELL_TYPE_STRING: // 字符串
                    result = cell.getStringCellValue();
                    //去首尾空格
                    if (result != null) {
                        result = result.trim();
                    }
                    break;
                default:
                    result = "";
                    break;
            }
        }

        return result;
    }
}
