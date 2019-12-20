package org.acko.utils;

import java.io.File;
import java.util.ArrayList;


public class Utils {
	
	public static ArrayList<String> listFilesInDirectory() {
		File folder = new File(System.getProperty("user.dir")+"/src/main/java/org/acko/resources");
		File[] listOfFiles = folder.listFiles();
        ArrayList<String>fileList = new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				  fileList.add(listOfFiles[i].getName());
			    System.out.println("File " + listOfFiles[i].getName());
			  } else if (listOfFiles[i].isDirectory()) {
			    System.out.println("Directory " + listOfFiles[i].getName());
			  }
			}
		return fileList;
	}

	
}
