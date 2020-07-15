package cn.com.eju.pmls.commission.enums;

/**
 * 合同终止上传附件类型枚举
 * 
 * @author sunmm
 * @date 2016年8月1日 下午9:42:59
 */
public enum EstateTypeEnum {

	YSSR(24501, "应收收入模板"),
	YJSR(24502, "应计收入模板"),
	YJFY(24503, "应计返佣模板"),
	YJSS(24504, "应计实收模板"),
	SJFY(24505, "实际返佣模板");
	private EstateTypeEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 枚举Code
	 */
	private Integer code;

	/**
	 * 枚举内容
	 */
	private String name;

	/**
	 * 获取枚举Code
	 * 
	 * @return code 枚举Code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 设置枚举Code
	 * 
	 * @param code
	 *            枚举Code
	 */
	public void setCode(Integer code) {
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

	public static String getNameByCode(Integer code) {
		for (EstateTypeEnum enumObj : EstateTypeEnum.values()) {
			if (code == enumObj.getCode()) {
				return enumObj.getName();
			}
		}
		return code+"";
	}
}
