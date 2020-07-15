package cn.com.eju.deal.contract.enums;

/**
 * 合同终止上传附件类型枚举
 * 
 * @author sunmm
 * @date 2016年8月1日 下午9:42:59
 */
public enum FileTypeEnum {

	ZZXYS("1001", "终止合作协议书"), 
	SFJYXY("1002", "三方解约协议"), 
	BZJSJ("1003", "保证金收据"), 
	YFTHZM("1004", "已付装修款退还证明"),
	ZXZM("1005", "注销证明"), 
	ZP("1006", "照片"), 
	XAHZXYS("1007", "新A合作协议书"), 
	ZZFA("1008", "一事一议终止方案"), 
	QT("1009", "其他"),
	BGBCTKZTW("1010", "变更补充条款"),
	XYYZZZTW("1011", "新营业执照"),
	GJXXGSZTW("1012", "国家信息公示"),
	FYQRDZTW("1013", "房友确认单"),
	MDZPZTW("1014", "门店照片"),
	QTZTW("1015", "其他"),
	ZRSFQLZT("1016", "转让三方权利"),
	XQXYSCLZT("1017", "新签协议书及材料"),
	GJXXGSZT("1018", "国家信息公示"),
	QTZTWZT("1019", "其他"),
	FRSFZ("2","法人身份证"),
	BCXY("1026","合同补充协议"),
	ZYTSH("1020","重要提示函");
	private FileTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 枚举Code
	 */
	private String code;

	/**
	 * 枚举内容
	 */
	private String name;

	/**
	 * 获取枚举Code
	 * 
	 * @return code 枚举Code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置枚举Code
	 * 
	 * @param code
	 *            枚举Code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取枚举内容
	 * 
	 * @return name 枚举内容
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置枚举内容
	 * 
	 * @param name
	 *            枚举内容
	 */
	public void setName(String name) {
		this.name = name;
	}

	public static String getNameByCode(String code) {
		for (FileTypeEnum enumObj : FileTypeEnum.values()) {
			if (code == enumObj.getCode()) {
				return enumObj.getName();
			}
		}
		return code;
	}
}
