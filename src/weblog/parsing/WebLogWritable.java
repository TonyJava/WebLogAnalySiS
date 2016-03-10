package weblog.parsing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.Writable;

public class WebLogWritable implements Writable {



	private String ipaddress;
	private String dateformat;
	private String request;
	private String response;
	private String sentbyte;
	private String referer;
	private String browser;
	private String keywordsString;
	private String url;
	
	
	public WebLogWritable() {
		super();
		this.ipaddress =null;
		this.dateformat  =null;
		this.request =null;
		this.response=null;
		this.sentbyte=null;
		this.referer=null;
		this.browser=null;
		this.keywordsString =null;
		this.url=null;
	}


	public String getKeywordsString() {
		return keywordsString;
	}

	public void setKeywordsString(String keywordsString) {
		this.keywordsString = keywordsString;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public void set(String ipaddress, String dateformat, String request,
			String response, String sentbyte, String referer,
			String browser,String keywordsString,String url) {
		  // super();

		  this.ipaddress=ipaddress;
		  this.dateformat=dateformat;
		  this.request=request;
		  this.response=response;
		  this.sentbyte=sentbyte;
		  this.referer=referer;
		  this.browser=browser;
		  this.keywordsString=keywordsString;
		  this.url=url;

		 }
	
	public String getIpaddress() {
		return ipaddress;
	}


	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}


	public String getdateformat() {
		return dateformat;
	}


	public void setdateformat(String dateformat) {
		this.dateformat = dateformat;
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
		dateformat=in.readUTF();
		request=in.readUTF();
		response=in.readUTF();
		sentbyte=in.readUTF();
		referer=in.readUTF().toString();
		browser=in.readUTF();
		keywordsString=in.readUTF();
		url=in.readUTF();
		}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeUTF(ipaddress);
		out.writeUTF(dateformat);
		out.writeUTF(request);
		out.writeUTF(response);
		out.writeUTF(sentbyte);
		out.writeUTF(referer);
		out.writeUTF(browser);
		out.writeUTF(keywordsString);
		out.writeUTF(url);
	}


	@Override
	public String toString() {
		return "WebLogWritable [ipaddress=" + ipaddress + ", dateformat="
				+ dateformat + ", request=" + request + ", response=" + response
				+ ", sentbyte=" + sentbyte + ", referer=" + referer
				+ ", browser=" + browser + ", keywordsString=" + keywordsString
				+ ", url=" + url + "]";
	}

	
}
