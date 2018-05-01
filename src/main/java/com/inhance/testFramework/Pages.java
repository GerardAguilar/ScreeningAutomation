package com.inhance.testFramework;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;


public class Pages {
	ArrayList<PictureData> pageScreenshots;
	String address;
	List lst;
	
	public Pages(String fileName) {
		
		initializePageScreenshots(fileName);
	}
	
	//Trying out solution from https://stackoverflow.com/questions/16858355/save-pictures-in-a-specific-folder-with-java
	public void initializePageScreenshots(String fileName) {
		pageScreenshots = new ArrayList<PictureData>();
		//first find the directory
		Workbook workbook = new HSSFWorkbook();
		Iterator<Sheet> sheetIterator;
		lst = null;
//		URL resource = this.getClass().getResource("/images/Baseline.xlsx");
		URL resource = getClass().getResource("/images"+fileName);		
		String sheetUrl =resource.toString();
		
		try {
			sheetUrl = resource.getPath();
			System.out.println("initializePageScreenshots(String fileName): " + fileName + "\nsheetUrl: ["+sheetUrl+"]");
			File temp = new File(sheetUrl);
			workbook = WorkbookFactory.create(temp);
			sheetIterator = workbook.sheetIterator();
			pageScreenshots = (ArrayList<PictureData>) workbook.getAllPictures();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(lst!=null) {			
//			for(Iterator it = lst.iterator(); it.hasNext();) {
//				PictureData pic = (PictureData)it.next();
//				
//				String ext = pic.suggestFileExtension();
//				byte[] data = pic.getData();//what am I doing with this data? Can I just use PictureData instead?
//				if(ext.equals("png")) {
//					try (OutputStream out = new FileOutputStream("pict.png")){
//						out.write(data);						
//					} catch (FileNotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//			}
//		}
	}
	
	public BufferedImage getBaselineImage(int index) {
	    BufferedImage img = null;
	    try {
	    	img = ImageIO.read(new ByteArrayInputStream(pageScreenshots.get(index).getData()));
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return img;
		
	}
	
	public int getBaselineImageCount() {		
		return pageScreenshots.size();
	}
	 
	
}
