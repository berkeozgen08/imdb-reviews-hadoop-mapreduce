package com.mapreduce.movieaverage;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class MovieAverageReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text,IntWritable> output, Reporter reporter) throws IOException {
		int total = 0;
		while (values.hasNext()) {
			IntWritable value = (IntWritable) values.next();
			total += value.get();
		}
		output.collect(key, new IntWritable(total));
	}
}
