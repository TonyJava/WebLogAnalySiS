package weblog.parsing;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WebLogDriver extends Configured implements Tool {


	@SuppressWarnings("deprecation")
	public int run(String[] args) throws Exception {

		Configuration conf = new Configuration();
		FileSystem hdfsfile = FileSystem.get(conf);
		Job job = Job.getInstance(conf);		
		job.setJobName(this.getClass().getName());		
		job.setJarByClass(getClass());
		hdfsfile.delete(new Path(args[1]));
		// configure output and input source
		
		  try{
		        DistributedCache.addFileToClassPath(new Path("/user/cloudera/projects/WebLogAnalySiS/externalJars/gson-2.6.2.jar"), job.getConfiguration());
		        DistributedCache.addFileToClassPath(new Path("/user/cloudera/projects/WebLogAnalySiS/externalJars/gson-2.6.2-sources.jar"), job.getConfiguration());
		        DistributedCache.addFileToClassPath(new Path("/user/cloudera/projects/WebLogAnalySiS/externalJars/gson-2.6.2-javadoc.jar"), job.getConfiguration());
			          }catch(Exception e){
		        	System.out.println(e);
		        }
			
			
			 URI[] cacheFiles= job.getCacheFiles();
			 if(cacheFiles != null) {
				 for (URI cacheFile : cacheFiles) {
					 System.out.println("Cache file ->" + cacheFile);
				 }
			 } 	
			 
		job.setInputFormatClass(WebLogInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		WebLogInputFormat.addInputPath(job, new Path(args[0]));
		TextOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(WebLogMapper.class);
		//job.setReducerClass(ReducerInventory.class);
	  // job.setPartitionerClass(WareHouseIdPartitioner.class);
		job.setNumReduceTasks(0);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		// configure output
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);		
		return job.waitForCompletion(true) ? 0 : 1;
}

public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new WebLogDriver(), args);
		System.exit(exitCode);
	}
}
