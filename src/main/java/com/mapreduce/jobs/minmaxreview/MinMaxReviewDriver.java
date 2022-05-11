package com.mapreduce.jobs.minmaxreview;

import com.mapreduce.Singletons;
import com.mapreduce.util.JSONParser;
import com.mapreduce.util.XmlInputFormat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextOutputFormat;

public class MinMaxReviewDriver {
	public static void run(String input, String output) {
        // Create a configuration object for the job
        JobConf jobConf = new JobConf(MinMaxReviewDriver.class);

		jobConf.set(XmlInputFormat.START_TAG_KEY, JSONParser.prefix);
		jobConf.set(XmlInputFormat.END_TAG_KEY, JSONParser.suffix);

        // Set a name of the Job
        jobConf.setJobName("MinMaxReview");

        // Specify data type of output key and value
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);

        // Specify names of Mapper and Reducer Class
        jobConf.setMapperClass(MinMaxReviewMapper.class);
        jobConf.setReducerClass(MinMaxReviewReducer.class);

        // Specify formats of the data type of Input and output
        jobConf.setInputFormat(XmlInputFormat.class);
        jobConf.setOutputFormat(TextOutputFormat.class);

        // Set input and output directories using command line arguments, 
        //arg[0] = name of input directory on HDFS, and arg[1] =  name of output directory to be created to store the output file.

        FileInputFormat.setInputPaths(jobConf, new Path(input));
        FileOutputFormat.setOutputPath(jobConf, new Path(output));

        Singletons.jobClient.setConf(jobConf);
        try {
            // Run the job 
            JobClient.runJob(jobConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}