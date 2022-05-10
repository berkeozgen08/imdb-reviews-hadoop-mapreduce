package com.mapreduce;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;

public class ReadWrite {
	public static FileSystem fileSystem;

	static {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://master:9000");
		try {
			fileSystem = FileSystem.get(configuration);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFileFromHDFS(String fileName) throws IOException {
		Path hdfsReadPath = new Path(fileName);
		FSDataInputStream inputStream = fileSystem.open(hdfsReadPath);
		byte[] buffer = new byte[100 * 1024];
		while (inputStream.read(buffer, 0, buffer.length) != -1) {
			System.out.print(IOUtils.toString(buffer, "UTF-8"));
		}
		inputStream.close();
	}

	public static void writeFileToHDFS(String fileName, String src) throws IOException {
		Path hdfsWritePath = new Path(fileName);
		FSDataOutputStream fsDataOutputStream = fileSystem.create(hdfsWritePath, true);
		FileInputStream fis = new FileInputStream(src);
		byte[] buffer = new byte[100 * 1024 * 1024];
		int read, size = 0;
		while ((read = fis.read(buffer, 0, buffer.length)) != -1) {
			size += read;
			fsDataOutputStream.write(buffer, 0, read);
		}
		System.out.println(size);
		fis.close();
		fsDataOutputStream.close();
	}

	public static void createDirectory(String directoryName) throws IOException {
		Path path = new Path(directoryName);
		fileSystem.mkdirs(path);
	}

	public static void checkExists(String directoryName) throws IOException {
		Path path = new Path(directoryName);
		if (fileSystem.exists(path)) {
			System.out.println("File/Folder Exists : " + path.getName());
		} else {
			System.out.println("File/Folder does not Exists : " + path.getName());
		}
	}
}
