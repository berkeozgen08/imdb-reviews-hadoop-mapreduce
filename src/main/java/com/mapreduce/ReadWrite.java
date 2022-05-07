package com.mapreduce;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.util.Scanner;

public class ReadWrite {
	public static FileSystem fileSystem;

	public static void main(String[] args) throws IOException {
		Configuration configuration = new Configuration();
		configuration.set("fs.defaultFS", "hdfs://master:9000");
		fileSystem = FileSystem.get(configuration);
		Scanner scanner = new Scanner(System.in);
		String choice = null;
		while (true) {
			System.out.println("0 - exit\n1 - read\n2 - write\n4 - mkdir\n5 - exists\nchoice: ");
			choice = scanner.nextLine();
			if (choice.equals("0")) break;
			System.out.print("file name: ");
			String fileName = scanner.nextLine();
			try {
				switch (choice) {
					case "1":
						readFileFromHDFS(fileName);
						break;
					case "2":
						System.out.print("src file name: ");
						writeFileToHDFS(fileName, scanner.nextLine());
						break;
					case "4":
						createDirectory(fileName);
						break;
					case "5":
						checkExists(fileName);
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		scanner.close();
		fileSystem.close();
	}

	public static void readFileFromHDFS(String fileName) throws IOException {
		Path hdfsReadPath = new Path(fileName);
		FSDataInputStream inputStream = fileSystem.open(hdfsReadPath);
		byte[] buffer = new byte[100 * 1024];
		while (inputStream.read(buffer, 0, buffer.length) != -1) {
			System.out.print(IOUtils.toString(buffer, "UTF-8"));
		}

		/*
		 * BufferedReader bufferedReader = new BufferedReader(
		 * new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		 * 
		 * String line = null;
		 * while ((line=bufferedReader.readLine())!=null){
		 * System.out.println(line);
		 * }
		 */

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
