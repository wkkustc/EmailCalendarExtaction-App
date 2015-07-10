/*
 *  dateHandler.java
 *
 * Copyright (c) 2000-2012, The University of Sheffield.
 *
 * This file is part of GATE (see http://gate.ac.uk/), and is free
 * software, licenced under the GNU Library General Public License,
 * Version 3, 29 June 2007.
 *
 * A copy of this licence is included in the distribution in the file
 * licence.html, and is also available at http://gate.ac.uk/gate/licence.html.
 *
 *  Administrator, 30/3/2014
 *
 * For details on the configuration options, see the user guide:
 * http://gate.ac.uk/cgi-bin/userguide/sec:creole-model:config
 */

package DateHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import gate.*;
import gate.creole.*;
import gate.creole.metadata.*;
import gate.util.*;
import java.util.*;

import gate.*;
import gate.creole.*;
import gate.creole.metadata.*;
import gate.util.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;
import javax.swing.text.Utilities;
import gate.Annotation;
import gate.AnnotationSet;
import gate.Document;
import gate.DocumentContent;
import gate.FeatureMap;
import gate.Node;
import gate.ProcessingResource;
import gate.creole.AbstractLanguageAnalyser;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.metadata.CreoleResource;
import gate.creole.orthomatcher.AnnotationOrthography;
import gate.util.InvalidOffsetException;

/** 
 * This class is the implementation of the resource DATEHANDLER.
 */
@CreoleResource(name = "DateHandler",
        comment = "Add a descriptive comment about this resource")
public class dateHandler  extends AbstractLanguageAnalyser
  implements ProcessingResource {
 	  private String outputASName;
	  @RunTime
	  @Optional
	  @CreoleParameter(comment = "the annotation set used to store output from this PR")
	  public void setOutputASName(String oasn) {
	    this.outputASName = oasn;
	  }

	  public String getOutputASName() {
	    return outputASName;
	  }

	  private String annotationName = null;

	  @RunTime
	  @CreoleParameter(defaultValue = "Date", comment = "the annotation type produced by the PR")
	  public void setAnnotationName(String name) {
	    annotationName = name;
	  }

	  public String getAnnotationName() {
	    return annotationName;
	  }
	  
	public void execute() throws ExecutionException{
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 gate.AnnotationSet matchedDate=document.getAnnotations().get("Date");  
		  for (Annotation ann : matchedDate) {
	  		  try {
		String content1 = document.getContent().getContent(ann.getStartNode().getOffset(),
		                 ann.getEndNode().getOffset()).toString();
				String targetDate = content1.toLowerCase().replace(" ", "");	
				
		 AnnotationSet sentDate = document.getAnnotations().get("emailSentDate");
		    Annotation emailSent1=sentDate.iterator().next();
		 String baseYear= emailSent1.getFeatures().get("EmailSentYear").toString();   
		  String baseMonth= emailSent1.getFeatures().get("EmailSentMonth").toString();   	
		  String baseDay= emailSent1.getFeatures().get("EmailSentDay").toString();  
		  String baseDate= baseYear+"-"+baseMonth+"-"+baseDay;
		  System.out.println("base date "+baseDate); 
		  
			java.util.Date bd=sdf.parse(baseDate);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(bd);
		    int baseweekday=calendar.get(Calendar.DAY_OF_WEEK);
		    System.out.println(baseweekday); // the day of EmailSent
		
			FeatureMap newFeatures = Factory.newFeatureMap(); 	 
			
		  	 if (targetDate.startsWith("today")){
		  		calendar.add(Calendar.DATE, 0);
		  	 	String Normaldt = sdf.format(calendar.getTime()); 
			   	System.out.println("Normalized Date "+Normaldt);
			   	String[] parts = Normaldt.split("-");
				  	 String targetYear=parts[0];
				  	 String targetMonth=parts[1];
				  	 String targetDay=parts[2];  	   
		  		 newFeatures.put("Year", targetYear);
			      newFeatures.put("Month",targetMonth);
			      newFeatures.put("Day",baseYear);
			      newFeatures.put("rule","DateNormalized");
		  	 }
		  		
		  	 if (targetDate.startsWith("tomorrow")){
		   		 calendar.add(Calendar.DATE, 1);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
		  	 
		  	if (targetDate.startsWith("nextmonday")){
		   		 calendar.add(Calendar.DATE, 2-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
		  	 
			 if (targetDate.startsWith("nexttuesday")){
		   		 calendar.add(Calendar.DATE, 3-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
		     
			 if (targetDate.startsWith("nextwednesday")){
		   		 calendar.add(Calendar.DATE, 4-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
			 
			 if (targetDate.startsWith("nextthursday")){
		   		 calendar.add(Calendar.DATE, 5-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
			 
			 if (targetDate.startsWith("nextfriday")){
		   		 calendar.add(Calendar.DATE, 6-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
			 
			 if (targetDate.startsWith("nextsaturday")){
		   		 calendar.add(Calendar.DATE, 7-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
			 
			 if (targetDate.startsWith("nextsunday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}
			 	 
			 if (targetDate.startsWith("nextweek")){
		   		 calendar.add(Calendar.DATE,7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}	
			 
			 if (targetDate.startsWith("monday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");} 
			  
				 if (targetDate.startsWith("tuesday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}  
			 
			 	 if (targetDate.startsWith("wednesday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");} 
			  
			  	 if (targetDate.startsWith("thursday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");} 
			  
			  	 if (targetDate.startsWith("friday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");} 
			  
			  	 if (targetDate.startsWith("saturday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");} 
			  
				  	 if (targetDate.startsWith("sunday")){
		   		 calendar.add(Calendar.DATE, 8-baseweekday+7);	 
		   	String Normaldt = sdf.format(calendar.getTime()); 
		   	System.out.println("Normalized Date "+Normaldt);
		   	String[] parts = Normaldt.split("-");
			  	 String targetYear=parts[0];
			  	 String targetMonth=parts[1];
			  	 String targetDay=parts[2];  	 
	  		 newFeatures.put("Year", targetYear);
		      newFeatures.put("Month",targetMonth);
		      newFeatures.put("Day",targetDay);
		      newFeatures.put("rule","DateNormalized");}  
			 
		      AnnotationSet outputAS = null;
		      if(outputASName == null || outputASName.equals("")) {
		  	outputAS = document.getAnnotations();
		      } else {
		          outputAS = document.getAnnotations(outputASName);
		      }
		      
	 outputAS.add(ann.getStartNode().getOffset(),ann.getEndNode().getOffset(),"NormalizedDate", newFeatures); 
		    
		    
		}  
	  		catch (InvalidOffsetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  }}

} // class dateHandler
