package com.mapreduce.movieaverage;

import com.mapreduce.JSONParser;
import com.mapreduce.XmlInputFormat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextOutputFormat;

public class MovieAverageDriver {
	public static void main(String[] args) {
        JobClient my_client = new JobClient();
        // Create a configuration object for the job
        JobConf job_conf = new JobConf(MovieAverageDriver.class);

		job_conf.set(XmlInputFormat.START_TAG_KEY, JSONParser.prefix);
		job_conf.set(XmlInputFormat.END_TAG_KEY, JSONParser.suffix);

        // Set a name of the Job
        job_conf.setJobName("MovieAverage");

        // Specify data type of output key and value
        job_conf.setOutputKeyClass(Text.class);
        job_conf.setOutputValueClass(IntWritable.class);

        // Specify names of Mapper and Reducer Class
        job_conf.setMapperClass(MovieAverageMapper.class);
        job_conf.setReducerClass(MovieAverageReducer.class);

        // Specify formats of the data type of Input and output
        job_conf.setInputFormat(XmlInputFormat.class);
        job_conf.setOutputFormat(TextOutputFormat.class);

        // Set input and output directories using command line arguments, 
        //arg[0] = name of input directory on HDFS, and arg[1] =  name of output directory to be created to store the output file.

        FileInputFormat.setInputPaths(job_conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(job_conf, new Path(args[1]));

        my_client.setConf(job_conf);
        try {
            // Run the job 
            JobClient.runJob(job_conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
