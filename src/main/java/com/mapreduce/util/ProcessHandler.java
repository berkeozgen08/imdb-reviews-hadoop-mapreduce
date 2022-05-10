package com.mapreduce.util;

import java.io.IOException;
import java.util.Scanner;

public class ProcessHandler {
	public static String run(String cmd) throws IOException {
		Process process = new ProcessBuilder(cmd).start();
		Scanner in = new Scanner(process.getInputStream());
		Scanner err = new Scanner(process.getErrorStream());
		String result = "";
		while (in.hasNextLine() || err.hasNextLine()) {
			if (in.hasNextLine())
				result += in.nextLine() + "\n";
			else
				result += err.nextLine() + "\n";
		}
		in.close();
		err.close();
		return result;
	}
}
