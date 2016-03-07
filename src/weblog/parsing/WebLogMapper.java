package weblog.parsing;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class WebLogMapper extends Mapper<Text, WebLogWritable, Text, Text> {


	protected void map(Text key, WebLogWritable value, Context context) throws IOException, InterruptedException {
//		String[] tokens = value.toString().split(",");
						
		context.write(key, new Text(value.toString()));
			
		}
	
}
