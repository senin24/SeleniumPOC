package com.github.senin24.selenidegridpoc.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	
	private static final Logger LOG = LogManager.getLogger(Utils.class.getName());
	private static final String FILE_NAME = "test-output/files/Memory_leak_" + getFullDate() + ".txt";

	public static void saveToFile(String filename, List <String> www) throws IOException {
		String fileName = "test-output/LINKS_for_" + filename.replace("/" , "") + "" + getFullDate() + ".txt";
		FileWriter writer = new FileWriter(fileName);
		for(String str: www) {
		  writer.write(str + "\n");
		}
		writer.close();
		LOG.info("Created file - " + fileName + getFullDate());
	}
	
	public static void saveToFile(String result) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			bufferedWriter.write(result + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getFullDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		return sdf.format(new Date());
	}
}
