package cn.com.eju.deal.Report.model;


public class ExpandDetail {
	
	
	//Add By tong 2017/04/07 Start
		private Integer Id;
		
		private String companyId;
		
		private String contractType;
		
		private String contractNo;

		public Integer getId() {
			return Id;
		}

		public void setId(Integer id) {
			Id = id;
		}

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}

		public String getContractType() {
			return contractType;
		}

		public void setContractType(String contractType) {
			this.contractType = contractType;
		}

		public String getContractNo() {
			return contractNo;
		}

		public void setContractNo(String contractNo) {
			this.contractNo = contractNo;
		}
		
		//Add By tong 2017/04/07 End
	
	/*//业绩归属城市
	private String performanceCity;
	//业绩归属事业部
	private String performanceCause;
	//业绩归属部门
	private String performanceDepartment;
    //业绩归属组
	private String performanceGroup;
	//业绩归属人
	private String performancePerson;
	//合同类型
	private String contractType;
	
	//-----门店信息-------
	//公司名称
	private String store1;
	//营业执照编码
	private String store2;
	//挂牌名称
	private String store3;
	//门店地址
	private String store4;
	//门店联系人
	private String store5;
	//联系人电话
	private String store6;
	//所属行政区
	private String store7;
	
	//-----合同信息-------------
	//协议书编号
	private String contract1;
	//合作开始日期
	private String contract2;
	//合作到期日期
	private String contract3;
	//门店数
	private String contract4;
	//草签日期
	private String contract5;
	//OA审核通过日期	
	private String contract6;
	//OA审核终止日期
	private String contract7;
	
	
	//-----保证金信息---
	//应收金额
	private String deposit1;
	
	//收款状态
	private String deposit2;
	
	//到账金额
	private String deposit3;
	
	//到账日期
	private String deposit4;
	


	
	//-----翻牌1.0---
	
	//装修公司
	private String drawOne1;
	//结算价格合计
	private String drawOne2;
	//翻牌完成时间
	private String drawOne3;
	
	
	
	
	
	
	//-----翻牌2.0---
	
	//装修公司
	private String drawTwo1;
	//翻牌申请通过时间
	private String drawTwo2;
	//OA翻牌申请单号
	private String drawTwo3;
	//报价小计
	private String drawTwo4;
	//标注项目报价
	private String drawTwo5;
	//增加项目报价
	private String drawTwo6;
	//翻牌完成时间
	private String drawTwo7;
	//翻牌验收通过时间
	private String drawTwo8;
	//OA翻牌验收单号
	private String drawTwo9;
	//结算价格合计
	private String drawTwo10;
	//标准项结算价
	private String drawTwo11;
	//新增项结算价
	private String drawTwo12;
	
	
	//------翻牌请款情况（1.0）
	//已付金额
	private String drawPayOne1;
	//第一次请款
	private String drawPayOne2;
	//第一次请款日期
	private String drawPayOne3;
	//第二次请款
	private String drawPayOne4;
	//第二次请款日期
	private String drawPayOne5;
	//第三次请款
	private String drawPayOne6;
	//第三次请款日期
	private String drawPayOne7;
	
	
	//------翻牌请款情况（2.0）
	//已付金额
	private String drawPayTwo1;
	//第一次请款
	private String drawPayTwo2;
	//第一次请款日期
	private String drawPayTwo3;
	//第二次请款
	private String drawPayTwo4;
	//第二次请款日期
	private String drawPayTwo5;
	//第三次请款
	private String drawPayTwo6;
	//第三次请款日期
	private String drawPayTwo7;

	
	
	
	//---交易服务信息
	//交易服务信息
	private String exchange1;
	
	
	//---门店录入信息
	
	//姓名
	private String storeEntering1;
	//工号
	private String storeEntering2;
	//录入日期
	private String storeEntering3;
	
	//-----------业绩归属人员信息---
	//拓展专员姓名
	private String pf1;
	//拓展专员工号
	private String pf2;
	//拓展经理姓名
	private String pf3;
	//拓展经理工号
	private String pf4;
	//拓展总监姓名
	private String pf5;
	//拓展总监工号
	private String pf6;
	
	//---当前维护人信息---
	//维护事业部
	private String maintain1;
	//维护组
	private String maintain2;
	//维护人姓名
	private String maintain3;
	//维护人工号
	private String maintain4;
	//维护开始日期
	private String maintain5;
	
	//-----历史维护人信息

	//维护人姓名1
	private String hMaintain1;
	//维护人工号1
	private String hMaintain2;
	//维护开始日期1
	private String  hMaintain3;

	//维护人姓名2
	private String hMaintain4;
	//护人工号2
	private String hMaintain5;
	//维护开始日期2
	private String hMaintain6;
	

		//维护人姓名3
		private String hMaintain7;
		//护人工号3
		private String hMaintain8;
		//维护开始日期3
		private String hMaintain9;
		

		//维护人姓名4
		private String hMaintain10;
		//护人工号4
		private String hMaintain11;
		//维护开始日期4
		private String hMaintain12;
		public String getPerformanceCity() {
			return performanceCity;
		}
		public void setPerformanceCity(String performanceCity) {
			this.performanceCity = performanceCity;
		}
		public String getPerformanceCause() {
			return performanceCause;
		}
		public void setPerformanceCause(String performanceCause) {
			this.performanceCause = performanceCause;
		}
		public String getPerformanceDepartment() {
			return performanceDepartment;
		}
		public void setPerformanceDepartment(String performanceDepartment) {
			this.performanceDepartment = performanceDepartment;
		}
		public String getPerformanceGroup() {
			return performanceGroup;
		}
		public void setPerformanceGroup(String performanceGroup) {
			this.performanceGroup = performanceGroup;
		}
		public String getPerformancePerson() {
			return performancePerson;
		}
		public void setPerformancePerson(String performancePerson) {
			this.performancePerson = performancePerson;
		}
		public String getContractType() {
			return contractType;
		}
		public void setContractType(String contractType) {
			this.contractType = contractType;
		}
		public String getStore1() {
			return store1;
		}
		public void setStore1(String store1) {
			this.store1 = store1;
		}
		public String getStore2() {
			return store2;
		}
		public void setStore2(String store2) {
			this.store2 = store2;
		}
		public String getStore3() {
			return store3;
		}
		public void setStore3(String store3) {
			this.store3 = store3;
		}
		public String getStore4() {
			return store4;
		}
		public void setStore4(String store4) {
			this.store4 = store4;
		}
		public String getStore5() {
			return store5;
		}
		public void setStore5(String store5) {
			this.store5 = store5;
		}
		public String getStore6() {
			return store6;
		}
		public void setStore6(String store6) {
			this.store6 = store6;
		}
		public String getStore7() {
			return store7;
		}
		public void setStore7(String store7) {
			this.store7 = store7;
		}
		public String getContract1() {
			return contract1;
		}
		public void setContract1(String contract1) {
			this.contract1 = contract1;
		}
		public String getContract2() {
			return contract2;
		}
		public void setContract2(String contract2) {
			this.contract2 = contract2;
		}
		public String getContract3() {
			return contract3;
		}
		public void setContract3(String contract3) {
			this.contract3 = contract3;
		}
		public String getContract4() {
			return contract4;
		}
		public void setContract4(String contract4) {
			this.contract4 = contract4;
		}
		public String getContract5() {
			return contract5;
		}
		public void setContract5(String contract5) {
			this.contract5 = contract5;
		}
		public String getContract6() {
			return contract6;
		}
		public void setContract6(String contract6) {
			this.contract6 = contract6;
		}
		public String getContract7() {
			return contract7;
		}
		public void setContract7(String contract7) {
			this.contract7 = contract7;
		}
		public String getDeposit1() {
			return deposit1;
		}
		public void setDeposit1(String deposit1) {
			this.deposit1 = deposit1;
		}
		public String getDeposit2() {
			return deposit2;
		}
		public void setDeposit2(String deposit2) {
			this.deposit2 = deposit2;
		}
		public String getDeposit3() {
			return deposit3;
		}
		public void setDeposit3(String deposit3) {
			this.deposit3 = deposit3;
		}
		public String getDeposit4() {
			return deposit4;
		}
		public void setDeposit4(String deposit4) {
			this.deposit4 = deposit4;
		}
		public String getDrawOne1() {
			return drawOne1;
		}
		public void setDrawOne1(String drawOne1) {
			this.drawOne1 = drawOne1;
		}
		public String getDrawOne2() {
			return drawOne2;
		}
		public void setDrawOne2(String drawOne2) {
			this.drawOne2 = drawOne2;
		}
		public String getDrawOne3() {
			return drawOne3;
		}
		public void setDrawOne3(String drawOne3) {
			this.drawOne3 = drawOne3;
		}
		public String getDrawTwo1() {
			return drawTwo1;
		}
		public void setDrawTwo1(String drawTwo1) {
			this.drawTwo1 = drawTwo1;
		}
		public String getDrawTwo2() {
			return drawTwo2;
		}
		public void setDrawTwo2(String drawTwo2) {
			this.drawTwo2 = drawTwo2;
		}
		public String getDrawTwo3() {
			return drawTwo3;
		}
		public void setDrawTwo3(String drawTwo3) {
			this.drawTwo3 = drawTwo3;
		}
		public String getDrawTwo4() {
			return drawTwo4;
		}
		public void setDrawTwo4(String drawTwo4) {
			this.drawTwo4 = drawTwo4;
		}
		public String getDrawTwo5() {
			return drawTwo5;
		}
		public void setDrawTwo5(String drawTwo5) {
			this.drawTwo5 = drawTwo5;
		}
		public String getDrawTwo6() {
			return drawTwo6;
		}
		public void setDrawTwo6(String drawTwo6) {
			this.drawTwo6 = drawTwo6;
		}
		public String getDrawTwo7() {
			return drawTwo7;
		}
		public void setDrawTwo7(String drawTwo7) {
			this.drawTwo7 = drawTwo7;
		}
		public String getDrawTwo8() {
			return drawTwo8;
		}
		public void setDrawTwo8(String drawTwo8) {
			this.drawTwo8 = drawTwo8;
		}
		public String getDrawTwo9() {
			return drawTwo9;
		}
		public void setDrawTwo9(String drawTwo9) {
			this.drawTwo9 = drawTwo9;
		}
		public String getDrawTwo10() {
			return drawTwo10;
		}
		public void setDrawTwo10(String drawTwo10) {
			this.drawTwo10 = drawTwo10;
		}
		public String getDrawTwo11() {
			return drawTwo11;
		}
		public void setDrawTwo11(String drawTwo11) {
			this.drawTwo11 = drawTwo11;
		}
		public String getDrawTwo12() {
			return drawTwo12;
		}
		public void setDrawTwo12(String drawTwo12) {
			this.drawTwo12 = drawTwo12;
		}
		public String getDrawPayOne1() {
			return drawPayOne1;
		}
		public void setDrawPayOne1(String drawPayOne1) {
			this.drawPayOne1 = drawPayOne1;
		}
		public String getDrawPayOne2() {
			return drawPayOne2;
		}
		public void setDrawPayOne2(String drawPayOne2) {
			this.drawPayOne2 = drawPayOne2;
		}
		public String getDrawPayOne3() {
			return drawPayOne3;
		}
		public void setDrawPayOne3(String drawPayOne3) {
			this.drawPayOne3 = drawPayOne3;
		}
		public String getDrawPayOne4() {
			return drawPayOne4;
		}
		public void setDrawPayOne4(String drawPayOne4) {
			this.drawPayOne4 = drawPayOne4;
		}
		public String getDrawPayOne5() {
			return drawPayOne5;
		}
		public void setDrawPayOne5(String drawPayOne5) {
			this.drawPayOne5 = drawPayOne5;
		}
		public String getDrawPayOne6() {
			return drawPayOne6;
		}
		public void setDrawPayOne6(String drawPayOne6) {
			this.drawPayOne6 = drawPayOne6;
		}
		public String getDrawPayOne7() {
			return drawPayOne7;
		}
		public void setDrawPayOne7(String drawPayOne7) {
			this.drawPayOne7 = drawPayOne7;
		}
		public String getDrawPayTwo1() {
			return drawPayTwo1;
		}
		public void setDrawPayTwo1(String drawPayTwo1) {
			this.drawPayTwo1 = drawPayTwo1;
		}
		public String getDrawPayTwo2() {
			return drawPayTwo2;
		}
		public void setDrawPayTwo2(String drawPayTwo2) {
			this.drawPayTwo2 = drawPayTwo2;
		}
		public String getDrawPayTwo3() {
			return drawPayTwo3;
		}
		public void setDrawPayTwo3(String drawPayTwo3) {
			this.drawPayTwo3 = drawPayTwo3;
		}
		public String getDrawPayTwo4() {
			return drawPayTwo4;
		}
		public void setDrawPayTwo4(String drawPayTwo4) {
			this.drawPayTwo4 = drawPayTwo4;
		}
		public String getDrawPayTwo5() {
			return drawPayTwo5;
		}
		public void setDrawPayTwo5(String drawPayTwo5) {
			this.drawPayTwo5 = drawPayTwo5;
		}
		public String getDrawPayTwo6() {
			return drawPayTwo6;
		}
		public void setDrawPayTwo6(String drawPayTwo6) {
			this.drawPayTwo6 = drawPayTwo6;
		}
		public String getDrawPayTwo7() {
			return drawPayTwo7;
		}
		public void setDrawPayTwo7(String drawPayTwo7) {
			this.drawPayTwo7 = drawPayTwo7;
		}
		public String getExchange1() {
			return exchange1;
		}
		public void setExchange1(String exchange1) {
			this.exchange1 = exchange1;
		}
		public String getStoreEntering1() {
			return storeEntering1;
		}
		public void setStoreEntering1(String storeEntering1) {
			this.storeEntering1 = storeEntering1;
		}
		public String getStoreEntering2() {
			return storeEntering2;
		}
		public void setStoreEntering2(String storeEntering2) {
			this.storeEntering2 = storeEntering2;
		}
		public String getStoreEntering3() {
			return storeEntering3;
		}
		public void setStoreEntering3(String storeEntering3) {
			this.storeEntering3 = storeEntering3;
		}
		public String getPf1() {
			return pf1;
		}
		public void setPf1(String pf1) {
			this.pf1 = pf1;
		}
		public String getPf2() {
			return pf2;
		}
		public void setPf2(String pf2) {
			this.pf2 = pf2;
		}
		public String getPf3() {
			return pf3;
		}
		public void setPf3(String pf3) {
			this.pf3 = pf3;
		}
		public String getPf4() {
			return pf4;
		}
		public void setPf4(String pf4) {
			this.pf4 = pf4;
		}
		public String getPf5() {
			return pf5;
		}
		public void setPf5(String pf5) {
			this.pf5 = pf5;
		}
		public String getPf6() {
			return pf6;
		}
		public void setPf6(String pf6) {
			this.pf6 = pf6;
		}
		public String getMaintain1() {
			return maintain1;
		}
		public void setMaintain1(String maintain1) {
			this.maintain1 = maintain1;
		}
		public String getMaintain2() {
			return maintain2;
		}
		public void setMaintain2(String maintain2) {
			this.maintain2 = maintain2;
		}
		public String getMaintain3() {
			return maintain3;
		}
		public void setMaintain3(String maintain3) {
			this.maintain3 = maintain3;
		}
		public String getMaintain4() {
			return maintain4;
		}
		public void setMaintain4(String maintain4) {
			this.maintain4 = maintain4;
		}
		public String getMaintain5() {
			return maintain5;
		}
		public void setMaintain5(String maintain5) {
			this.maintain5 = maintain5;
		}
		public String gethMaintain1() {
			return hMaintain1;
		}
		public void sethMaintain1(String hMaintain1) {
			this.hMaintain1 = hMaintain1;
		}
		public String gethMaintain2() {
			return hMaintain2;
		}
		public void sethMaintain2(String hMaintain2) {
			this.hMaintain2 = hMaintain2;
		}
		public String gethMaintain3() {
			return hMaintain3;
		}
		public void sethMaintain3(String hMaintain3) {
			this.hMaintain3 = hMaintain3;
		}
		public String gethMaintain4() {
			return hMaintain4;
		}
		public void sethMaintain4(String hMaintain4) {
			this.hMaintain4 = hMaintain4;
		}
		public String gethMaintain5() {
			return hMaintain5;
		}
		public void sethMaintain5(String hMaintain5) {
			this.hMaintain5 = hMaintain5;
		}
		public String gethMaintain6() {
			return hMaintain6;
		}
		public void sethMaintain6(String hMaintain6) {
			this.hMaintain6 = hMaintain6;
		}
		public String gethMaintain7() {
			return hMaintain7;
		}
		public void sethMaintain7(String hMaintain7) {
			this.hMaintain7 = hMaintain7;
		}
		public String gethMaintain8() {
			return hMaintain8;
		}
		public void sethMaintain8(String hMaintain8) {
			this.hMaintain8 = hMaintain8;
		}
		public String gethMaintain9() {
			return hMaintain9;
		}
		public void sethMaintain9(String hMaintain9) {
			this.hMaintain9 = hMaintain9;
		}
		public String gethMaintain10() {
			return hMaintain10;
		}
		public void sethMaintain10(String hMaintain10) {
			this.hMaintain10 = hMaintain10;
		}
		public String gethMaintain11() {
			return hMaintain11;
		}
		public void sethMaintain11(String hMaintain11) {
			this.hMaintain11 = hMaintain11;
		}
		public String gethMaintain12() {
			return hMaintain12;
		}
		public void sethMaintain12(String hMaintain12) {
			this.hMaintain12 = hMaintain12;
		}*/
		
}
