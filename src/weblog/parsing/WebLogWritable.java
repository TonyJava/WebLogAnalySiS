package weblog.parsing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class WebLogWritable implements Writable {



	private Text ipaddress;
	private Text datetime;
	private Text request;
	private Text response;
	private Text sentbyte;
	private Text referer;
	private Text browser;
	
	
	public WebLogWritable() {
		super();
		this.ipaddress = new Text();
		this.datetime =  new Text();
		this.request =  new Text();
		this.response =  new Text();
		this.sentbyte =  new Text();
		this.referer =  new Text();
		this.browser =  new Text();
	}
	
	public void set(String ipaddress, String datetime, String request,
			String response, String sentbyte, String referer, String browser) {
		  // super();

		  this.ipaddress.set(ipaddress);
		  this.datetime.set(datetime);
		  this.request.set(request);
		  this.response.set(datetime);
		  this.sentbyte.set(request);
		  this.referer.set(datetime);
		  this.browser.set(request);

		 }
	
	public Text getIpaddress() {
		return ipaddress;
	}


	public void setIpaddress(Text ipaddress) {
		this.ipaddress = ipaddress;
	}


	public Text getDatetime() {
		return datetime;
	}


	public void setDatetime(Text datetime) {
		this.datetime = datetime;
	}


	public Text getRequest() {
		return request;
	}


	public void setRequest(Text request) {
		this.request = request;
	}


	public Text getResponse() {
		return response;
	}


	public void setResponse(Text response) {
		this.response = response;
	}


	public Text getSentbyte() {
		return sentbyte;
	}


	public void setSentbyte(Text sentbyte) {
		this.sentbyte = sentbyte;
	}


	public Text getReferer() {
		return referer;
	}


	public void setReferer(Text referer) {
		this.referer = referer;
	}


	public Text getBrowser() {
		return browser;
	}


	public void setBrowser(Text browser) {
		this.browser = browser;
	}


	@Override
	public void readFields(DataInput in) throws IOException {
		ipaddress.readFields(in);
		datetime.readFields(in);
		request.readFields(in);
		response.readFields(in);
		sentbyte.readFields(in);
		referer.readFields(in);
		browser.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		ipaddress.write(out);
		datetime.write(out);
		request.write(out);
		response.write(out);
		sentbyte.write(out);
		referer.write(out);
		browser.write(out);
		
	}


	@Override
	public String toString() {
		return "[ipaddress=" + ipaddress + ", datetime="
				+ datetime + ", request=" + request + ", response=" + response
				+ ", sentbyte=" + sentbyte + ", referer=" + referer
				+ ", browser=" + browser + "]";
	}

	
}
