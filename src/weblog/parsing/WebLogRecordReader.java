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
		RegexMatches newweblog =new RegexMatches();
		value = newweblog.parseWebLog(weblogrecord);
		if(value.getRequest().equals("BAD_RECORD")){
			key =new Text("BAD_RECORD");
		}else
		{
			key =new Text(value.getIpaddress());
		}
//	    if (!matcher.matches() || 
//	      NUM_FIELDS != matcher.groupCount()) {
//	      System.err.println("Bad log entry (or problem with RE?):");
//	      System.err.println(weblogrecord);
//	      return nextKeyValue();
//	 
//	    } 

		
		return true;
	}

	
}
