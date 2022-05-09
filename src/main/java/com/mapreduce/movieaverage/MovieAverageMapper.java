package com.mapreduce.movieaverage;

import java.io.IOException;

import com.mapreduce.JSONParser;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.json.JSONObject;

public class MovieAverageMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		String valueString = value.toString();
		JSONObject obj = JSONParser.parse(valueString);
		output.collect(new Text(obj.getString("movie")), new IntWritable(obj.getInt("rating")));
	}
}