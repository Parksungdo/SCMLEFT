package kr.happyjob.study.pur.model;

import java.sql.Date;

public class OrdDtManagementVO {

	private int JORD_CODE;
	private int JORD_AMT;
	private String loginID;
	private String MODEL_NAME;
	private String JORD_DATE;
	private String JORD_IN;
	private Date sdate;
	private Date edate;
	private String cpname;
	private String pdname;
	private String PD_NAME;
	
	
	
	public int getJORD_CODE() {
		return JORD_CODE;
	}



	public void setJORD_CODE(int jORD_CODE) {
		JORD_CODE = jORD_CODE;
	}



	public int getJORD_AMT() {
		return JORD_AMT;
	}



	public void setJORD_AMT(int jORD_AMT) {
		JORD_AMT = jORD_AMT;
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



	public String getJORD_DATE() {
		return JORD_DATE;
	}



	public void setJORD_DATE(String jORD_DATE) {
		JORD_DATE = jORD_DATE;
	}



	public String getJORD_IN() {
		return JORD_IN;
	}



	public void setJORD_IN(String jORD_IN) {
		JORD_IN = jORD_IN;
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



	public String getPD_NAME() {
		return PD_NAME;
	}



	public void setPD_NAME(String pD_NAME) {
		PD_NAME = pD_NAME;
	}



	@Override
	public String toString() {
		return "OrdDtManagementVO [JORD_CODE=" + JORD_CODE + ", PD_NAME=" + PD_NAME + ",  JORD_AMT=" + JORD_AMT + ", loginID=" + loginID
				+ ", MODEL_NAME=" + MODEL_NAME + ", JORD_DATE=" + JORD_DATE + ", JORD_IN="
				+ JORD_IN + ", sdate=" + sdate + ", edate=" + edate + ", cpname=" + cpname + ", pdname=" + pdname
				+ "]";
	}
	
	
}
