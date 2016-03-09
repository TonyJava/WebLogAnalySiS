package weblog.parsing;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;


public class WebLogRecordReader extends RecordReader<Text, WebLogWritable> {
	public static final int NUM_FIELDS = 9;
    private LineRecordReader lineReader;
    private Text key; 
    private WebLogWritable value; 
    
	@Override
	public void close() throws IOException {
		if (lineReader != null) {
            lineReader.close();
        }
	}

	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	@Override
	public WebLogWritable getCurrentValue() throws IOException,
			InterruptedException {
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		return  lineReader.getProgress();
	}

	@Override
	public void initialize(InputSplit inputSplit, TaskAttemptContext context)
			throws IOException, InterruptedException {
		lineReader = new LineRecordReader();
		lineReader.initialize(inputSplit, context);	
		
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if (!lineReader.nextKeyValue()) {
			return false;
		}
		

		String weblogrecord = lineReader.getCurrentValue().toString();
		
		String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
	   
	    Pattern p = Pattern.compile(logEntryPattern);
	    Matcher matcher = p.matcher(weblogrecord);
	    
		String ipaddress = null;
		String datetime=null;
		String request=null;
		String response=null;
		String sentbyte=null;
		String referer=null;
		String browser=null;
		
	    if (!matcher.matches() || 
	      NUM_FIELDS != matcher.groupCount()) {
	      System.err.println("Bad log entry (or problem with RE?):");
	      System.err.println(weblogrecord);
	      return true;
	 
	    } 
	   /* System.out.println("IP Address: " + matcher.group(1)); //([\\d.]+)
	    System.out.println("Date&Time: " + matcher.group(4));//
	    System.out.println("Request: " + matcher.group(5));
	    System.out.println("Response: " + matcher.group(6));
	    System.out.println("Bytes Sent: " + matcher.group(7));
	    if (!matcher.group(8).equals("-"))
	      System.out.println("Referer: " + matcher.group(8));
	    System.out.println("Browser: " + matcher.group(9));
	    */
	
		
		ipaddress=matcher.group(1);
		datetime=matcher.group(4);
		request=matcher.group(5);
		response=matcher.group(6);
		sentbyte=matcher.group(7);
		 if (!matcher.group(8).equals("-")){
			 referer=matcher.group(8);
			 browser=matcher.group(9);
		 }else{
			 referer="-";
			 browser=matcher.group(9);
		 }
	    
		key =new Text(ipaddress);
		value= new WebLogWritable();
		value.set(ipaddress, datetime, request, response, sentbyte, referer, browser);
				
		return true;
	}

	
}
