package be.vdab.retrovideo.web.ajax;

public class AjaxResponse {

	private String status;
	private String response;
	
	public AjaxResponse(final String status, final String response) {
		setStatus(status);
		setResponse(response);
	}
	
	public final void setStatus(final String status) {
		this.status = status;
	}
	
	public final String getStatus() {
		return status;
	}
	
	public final void setResponse(final String response) {
		this.response = response;
	}
	
	public final String getResponse() {
		return response;
	}
}
