package com.medicarehub.dto;

public class LoginStatus {
	
	private boolean loginStatus;
	private String loginStatusMessage;
	private int patientId;
	
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getLoginStatusMessage() {
		return loginStatusMessage;
	}
	public void setLoginStatusMessage(String loginStatusMessage) {
		this.loginStatusMessage = loginStatusMessage;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
}
