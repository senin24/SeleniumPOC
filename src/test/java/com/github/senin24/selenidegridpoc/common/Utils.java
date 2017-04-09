package com.github.senin24.selenidegridpoc.common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	
	private static final Logger LOG = LogManager.getLogger(Utils.class.getName());

	public void saveToFile(String filename, List <String> www) throws IOException {
		String fileName = "recources/" + filename.replace("/" , "") + ".txt";
		FileWriter writer = new FileWriter(fileName);
		for(String str: www) {
		  writer.write(str + "\n");
		}
		writer.close();
		LOG.info("Created file - " + fileName);
	}
}
