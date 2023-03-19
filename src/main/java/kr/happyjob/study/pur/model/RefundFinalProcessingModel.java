package kr.happyjob.study.pur.model;

import java.util.Date;

public class RefundFinalProcessingModel {

	private int RE_CODE;	//반품코드
	private String loginID;	//반품회사
	private String MODEL_NAME;
	private int RE_AMT;
	private String RE_DATE;
	private int PD_PRICE;
	private String RE_TYPE;
	private String PD_NAME;	//제품명
	private String RE_OUT;
	private Date sdate;
	private Date edate;
	private String cpname;
	private String pdname;
	private String approved;
	private String unapproved;
	
	
	
	public String getRE_OUT() {
		return RE_OUT;
	}
	public void setRE_OUT(String rE_OUT) {
		RE_OUT = rE_OUT;
	}
	public int getRE_CODE() {
		return RE_CODE;
	}
	public void setRE_CODE(int rE_CODE) {
		RE_CODE = rE_CODE;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getMODEL_NAME() {
		return MODEL_NAME;
	}
	public void setMODEL_NAME(String mODEL_NAME) {
		MODEL_NAME = mODEL_NAME;
	}
	public int getRE_AMT() {
		return RE_AMT;
	}
	public void setRE_AMT(int rE_AMT) {
		RE_AMT = rE_AMT;
	}
	public String getRE_DATE() {
		return RE_DATE;
	}
	public void setRE_DATE(String rE_DATE) {
		RE_DATE = rE_DATE;
	}
	public int getPD_PRICE() {
		return PD_PRICE;
	}
	public void setPD_PRICE(int pD_PRICE) {
		PD_PRICE = pD_PRICE;
	}
	public String getRE_TYPE() {
		return RE_TYPE;
	}
	public void setRE_TYPE(String rE_TYPE) {
		RE_TYPE = rE_TYPE;
	}
	public String getPD_NAME() {
		return PD_NAME;
	}
	public void setPD_NAME(String pD_NAME) {
		PD_NAME = pD_NAME;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	public String getPdname() {
		return pdname;
	}
	public void setPdname(String pdname) {
		this.pdname = pdname;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getUnapproved() {
		return unapproved;
	}
	public void setUnapproved(String unapproved) {
		this.unapproved = unapproved;
	}
	@Override
	public String toString() {
		return "RefundFinalProcessingModel [RE_CODE=" + RE_CODE + ", loginID=" + loginID + ", MODEL_NAME=" + MODEL_NAME
				+ ", RE_AMT=" + RE_AMT + ", RE_DATE=" + RE_DATE + ", PD_PRICE=" + PD_PRICE + ", RE_TYPE=" + RE_TYPE
				+ ", PD_NAME=" + PD_NAME + ", RE_OUT=" + RE_OUT + ", sdate=" + sdate + ", edate=" + edate + ", cpname="
				+ cpname + ", pdname=" + pdname + ", approved=" + approved + ", unapproved=" + unapproved + "]";
	}
	
	
	

	
	
	
	
	
	
	
	
}
