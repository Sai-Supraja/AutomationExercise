package FlightBooking.AutomationExercise;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import FlightBooking.pages.App;


public class Apptest extends Basetest
{
	@Test(dataProvider="getData")
	public void flightbooking(HashMap<String,String> testData) throws IOException, SAXException, ParserConfigurationException, InterruptedException {
		//launchApplication();
		System.out.println(driver.getCurrentUrl());
		App app= new App(driver);
		Thread.sleep(5000);
		app.modalPopupClose();
		//app.flightsLinkClick();
		app.selectRoundTrip();
		app.fromFlight(testData);
		app.toFlight(testData);
		app.departureDateSelect(testData, testData.get("departuredate"),testData.get("departuremonth"));
		app.arrivalDateSelect(testData, testData.get("arrivaldate"),testData.get("arrivalmonth"));
		app.addAdults(testData.get("adults"));
		app.addChildren(testData.get("children"));
		app.addInfants(testData.get("infants"));
		app.travelClass();
		app.searchFlights();
		app.bookFlight();
		app.fareSelectionPage();
		
		if(app.errorPopup())
		{
			System.out.println("error pop-up retry logic");
			app.bookFlight();
			app.fareSelectionPage();
			app.verifyFareSummary(testData.get("adults"), testData.get("children"), testData.get("infants"));
			app.verifyTravellerErrors(testData.get("firstnameerror"), testData.get("dateofbirtherror"), testData.get("emailiderror"), testData.get("mobilenumbererror"));
			app.addTravellerDetails(testData.get("adult1"), testData.get("adult2"), testData.get("adult3"), testData.get("child1"), 
					testData.get("child2"), testData.get("infant"), testData.get("dayofbirth"), testData.get("monthofbirth"), 
					testData.get("yearofbirth"), testData.get("emailid"), testData.get("mobilenumber"), 
					testData.get("adultgender"), testData.get("childgender"), testData.get("infantgender"));
		}
		else {
			System.out.println("No pop-up logic- Normal flow");
		app.verifyFareSummary(testData.get("adults"), testData.get("children"), testData.get("infants"));
		app.verifyTravellerErrors(testData.get("firstnameerror"), testData.get("dateofbirtherror"), testData.get("emailiderror"), testData.get("mobilenumbererror"));
		app.addTravellerDetails(testData.get("adult1"), testData.get("adult2"), testData.get("adult3"), testData.get("child1"), 
				testData.get("child2"), testData.get("infant"), testData.get("dayofbirth"), testData.get("monthofbirth"), 
				testData.get("yearofbirth"), testData.get("emailid"), testData.get("mobilenumber"), 
				testData.get("adultgender"), testData.get("childgender"), testData.get("infantgender"));
		}
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data= getJsonData(System.getProperty("user.dir")+"//src//test//java//DataReader//testData.json");
		return new Object[][] {{data.get(0)}};
	}
}
