package com.mapreduce;

import org.apache.hadoop.mapred.JobClient;

public class JobClientSingleton {
	public static JobClient jobClient = new JobClient();
}
