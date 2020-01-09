package com.axelor.csv.controller;
import com.axelor.data.Importer;
import com.axelor.data.csv.CSVImporter;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class CsvImportController {

	public void importCsvDate(ActionRequest request, ActionResponse response) throws IOException {

		File configFile = getConfigFile();
		/*
		 * Importer importer = new CSVImporter(configFile.getAbsolutePath());
		 * importer.run(new ImportTask() {
		 * 
		 * @Override public void configure() throws IOException { // input("[country]",
		 * getDataCsvFile("country3.csv").getAbsoluteFile()); // input("[company]",
		 * getDataCsvFile("company2.csv").getAbsoluteFile()); input("[sale.order]",
		 * getDataCsvFile("order.csv").getAbsoluteFile()); } });
		 * response.setFlash("Import csv data");
		 */

		Importer importer1 = new CSVImporter(
				getConfigFile().getAbsolutePath(),
				getDataCsvFile());
		
		// Importer importer1 = new
		// CSVImporter(configFile.getAbsolutePath(),getDataCsvDir().getAbsolutePath());
		importer1.run();
	}

	public File getConfigFile() throws IOException {
		File configFile = File.createTempFile("csv-config", ".xml");
		InputStream is = this.getClass().getResourceAsStream("/data-demo/input-config2.xml");
		FileOutputStream os = new FileOutputStream(configFile);
		IOUtils.copy(is, os);
		return configFile;
	}
	
	public String getDataCsvFile() throws IOException {
		
		/*
		File csvFile = File.createTempFile("temp-file", ".csv");
		InputStream is = this.getClass().getResourceAsStream("/data-demo/input/");				
		FileOutputStream os = new FileOutputStream(csvFile);
		IOUtils.copy(is, os);
		return csvFile;
		*/
		
		URL url = this.getClass().getResource("/data-demo/input/");
		return url.getPath();

	}

	private File getDataCsvDir(String filename) {
		File tempDir = null;
		try {		
			tempDir = Files.createTempDir();				
			InputStream is = this.getClass().getResourceAsStream("/data-demo/input/"+filename);
			FileOutputStream os = new FileOutputStream(tempDir);
			IOUtils.copy(is, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempDir;
	}

	/*
	 * public File getDataXmlFile(String filename) throws IOException { File xmlFile
	 * = File.createTempFile("temp-file", ".xml"); InputStream is =
	 * this.getClass().getResourceAsStream("/data-demo/xml/" + filename);
	 * FileOutputStream os = new FileOutputStream(xmlFile); IOUtils.copy(is, os);
	 * return xmlFile; } public File getXmlConfigFile() throws IOException { File
	 * configFile = File.createTempFile("xml-config", ".xml"); InputStream is =
	 * this.getClass().getResourceAsStream("/data-demo/xml-config.xml");
	 * FileOutputStream os = new FileOutputStream(configFile); IOUtils.copy(is, os);
	 * return configFile; }
	 * 
	 */

}
