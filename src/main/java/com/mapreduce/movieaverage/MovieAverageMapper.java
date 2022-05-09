package com.mapreduce.movieaverage;

import java.io.IOException;

import com.mapreduce.JSONParser;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class MovieAverageMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);

	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		String valueString = value.toString();
		output.collect(new Text(JSONParser.parse(valueString).getString("movie")), one);
	}
}