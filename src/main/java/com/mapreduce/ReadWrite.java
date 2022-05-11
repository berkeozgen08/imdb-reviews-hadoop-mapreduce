package com.mapreduce;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class ReadWrite {
	public static void readFile(String fileName) throws IOException {
		Path hdfsReadPath = new Path(fileName);
		FSDataInputStream inputStream = Singletons.fileSystem.open(hdfsReadPath);
		byte[] buffer = new byte[100 * 1024];
		while (inputStream.read(buffer, 0, buffer.length) != -1) {
			System.out.print(IOUtils.toString(buffer, "UTF-8").trim());
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
		System.out.println("Bytes written: " + size);
		fis.close();
		fsDataOutputStream.close();
	}

	public static void removeFile(String fileName) throws IOException {
		Path path = new Path(fileName);
		Singletons.fileSystem.delete(path, false);
	}

	public static void listFiles(String path) throws FileNotFoundException, IllegalArgumentException, IOException {
		String[] files = getFiles(path);
		for (String file : files) {
			System.out.println(file);
		}
	}
	
	public static String[] getFiles(String path) throws FileNotFoundException, IllegalArgumentException, IOException {
		RemoteIterator<LocatedFileStatus> iter =
			Singletons.fileSystem.listFiles(new Path(path), true);
		List<String> files = new ArrayList<>();
		while (iter.hasNext()) {
			files.add(Path.getPathWithoutSchemeAndAuthority(iter.next().getPath()).toString());
		}
		files.sort((a, b) -> a.compareTo(b));
		return files.stream().toArray(String[]::new);
	}

	public static void createDirectory(String directoryName) throws IOException {
		Path path = new Path(directoryName);
		Singletons.fileSystem.mkdirs(path);
	}

	public static void removeDirectory(String directoryName) throws IOException {
		Path path = new Path(directoryName);
		Singletons.fileSystem.delete(path, true);
	}
}
