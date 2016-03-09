package weblog.parsing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class WebLogWritable implements Writable {



	private String ipaddress;
	private String datetime;
	private String request;
	private String response;
	private String sentbyte;
	private String referer;
	private String browser;
	
	
	public WebLogWritable() {
		super();
		this.ipaddress = new String();
		this.datetime =  new String();
		this.request =  new String();
		this.response =  new String();
		this.sentbyte =  new String();
		this.referer =  new String();
		this.browser =  new String();
	}
	
	public void set(String ipaddress, String datetime, String request,
			String response, String sentbyte, String referer, String browser) {
		  // super();

		  this.ipaddress=ipaddress;
		  this.datetime=datetime;
		  this.request=request;
		  this.response=response;
		  this.sentbyte=sentbyte;
		  this.referer=referer;
		  this.browser=browser;

		 }
	
	public String getIpaddress() {
		return ipaddress;
	}


	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}


	public String getDatetime() {
		return datetime;
	}


	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	public String getRequest() {
		return request;
	}


	public void setRequest(String request) {
		this.request = request;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}


	public String getSentbyte() {
		return sentbyte;
	}


	public void setSentbyte(String sentbyte) {
		this.sentbyte = sentbyte;
	}


	public String getReferer() {
		return referer;
	}


	public void setReferer(String referer) {
		this.referer = referer;
	}


	public String getBrowser() {
		return browser;
	}


	public void setBrowser(String browser) {
		this.browser = browser;
	}


	@Override
	public void readFields(DataInput in) throws IOException {
		ipaddress = in.readUTF();
		datetime=in.readUTF();
		request=in.readUTF();
		response=in.readUTF();
		sentbyte=in.readUTF();
		referer=in.readUTF();
		browser=in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeUTF(ipaddress);
		out.writeUTF(datetime);
		out.writeUTF(request);
		out.writeUTF(response);
		out.writeUTF(sentbyte);
		out.writeUTF(referer);
		out.writeUTF(browser);
		
	}


	@Override
	public String toString() {
		return "[ipaddress=" + ipaddress + ", datetime="
				+ datetime + ", request=" + request + ", response=" + response
				+ ", sentbyte=" + sentbyte + ", referer=" + referer
				+ ", browser=" + browser + "]";
	}

	
}
