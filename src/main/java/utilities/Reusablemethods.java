package utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import FlightBooking.pages.App;

public class Reusablemethods{

	WebDriver driver;
	
	public Reusablemethods(WebDriver driver) {
		this.driver= driver;
		System.out.println("reusable"+driver.getCurrentUrl());
	}
	
	public String readXml(String objName){
		
		String locatorValue=null;
		File file = new File(System.getProperty("user.dir")+"//src//main//java//resources//Objectrepo.xml");
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Document document = null;
		try {
			document = builder.parse(file);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//         Element rootElement = document.getDocumentElement();
         NodeList nodeList = document.getElementsByTagName("locator");
         for (int i = 0; i < nodeList.getLength(); i++) {
             Node node = nodeList.item(i);
             System.out.println("\nCurrent element: " + node.getNodeName());
             if (node.getNodeType() == node.ELEMENT_NODE) {
                 Element element = (Element) node;
                 locatorValue= element.getElementsByTagName(objName).item(0).getTextContent();
                 break;
             }
         }
         return locatorValue;
         
	}
	
	public String getScreenshot(String Name, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + Name + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + Name + ".png";
	}
}
