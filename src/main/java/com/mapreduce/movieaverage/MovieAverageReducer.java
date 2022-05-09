package com.mapreduce.movieaverage;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class MovieAverageReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, DoubleWritable> {
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
		int total = 0;
		int count = 0;
		while (values.hasNext()) {
			IntWritable value = (IntWritable) values.next();
			total += value.get();
			count++;
		}
		output.collect(key, new DoubleWritable(total / (double) count));
	}
}
