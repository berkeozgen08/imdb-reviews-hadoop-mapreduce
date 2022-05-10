package com.mapreduce;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.*;

public class ReadWrite {
	public static void readFile(String fileName) throws IOException {
		Path hdfsReadPath = new Path(fileName);
		FSDataInputStream inputStream = Singletons.fileSystem.open(hdfsReadPath);
		byte[] buffer = new byte[100 * 1024];
		while (inputStream.read(buffer, 0, buffer.length) != -1) {
			System.out.print(IOUtils.toString(buffer, "UTF-8"));
		}
		inputStream.close();
	}

	public static void writeFile(String fileName, String src) throws IOException {
		Path hdfsWritePath = new Path(fileName);
		FSDataOutputStream fsDataOutputStream = Singletons.fileSystem.create(hdfsWritePath, true);
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

	public static void listFiles(String path) {
		try {
			RemoteIterator<LocatedFileStatus> iter =
				Singletons.fileSystem.listFiles(new Path(path), true);
			while (iter.hasNext()) {
				System.out.println(iter.next().getPath().toString());
			}
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void createDirectory(String directoryName) throws IOException {
		Path path = new Path(directoryName);
		Singletons.fileSystem.mkdirs(path);
	}

	public static void checkExists(String directoryName) throws IOException {
		Path path = new Path(directoryName);
		if (Singletons.fileSystem.exists(path)) {
			System.out.println("File/Folder Exists : " + path.getName());
		} else {
			System.out.println("File/Folder does not Exists : " + path.getName());
		}
	}
}
