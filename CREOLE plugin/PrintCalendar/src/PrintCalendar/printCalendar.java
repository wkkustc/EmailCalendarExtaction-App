/*
 *  printCalendar.java
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
 *  Administrator, 13/4/2014
 *
 * For details on the configuration options, see the user guide:
 * http://gate.ac.uk/cgi-bin/userguide/sec:creole-model:config
 */

package PrintCalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import gate.*;
import gate.creole.*;
import gate.creole.metadata.*;
import gate.util.*;


/** 
 * This class is the implementation of the resource PRINTCALENDAR.
 */
@CreoleResource(name = "PrintCalendar",
        comment = "Add a descriptive comment about this resource")
public class printCalendar  extends AbstractLanguageAnalyser
implements ProcessingResource {
	
	
	public void execute() throws ExecutionException {
		  
		  String docname= document.getName();
		  
		  try {
			MyFile(docname,document);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidOffsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 			
		 }
		 

		 public static void MyFile(String docname, Document document) throws FileNotFoundException, InvalidOffsetException {
			
					 
			 StringBuilder path=new StringBuilder();
			  
			   path.append("d:/");
			
		     
		        
		        StringBuilder sb = new StringBuilder();
		        
		        try {
		        
		            sb.append("<html><head><title>UniversityResult</title></head><body>");
		            sb.append("<div align='center'>");
		            sb.append("<br/>");
		            AnnotationSet finalMatch = document.getAnnotations().get("finalMatch");
		            sb.append("<font color=\"color1\">");
		  			sb.append("file name:");
			         sb.append("</font>");
		  			sb.append("&nbsp;");
		            sb.append(docname);
		            sb.append("<br/>");
		        
			        for (Annotation ann : finalMatch) {
			
			  			String finalLoc=ann.getFeatures().get("finalLoc").toString().toLowerCase();
			  			String finalTime=ann.getFeatures().get("finalTime").toString().toLowerCase();
			  			String finalEvent=ann.getFeatures().get("finalEvent").toString().toLowerCase();
			  			String relationship=ann.getFeatures().get("relationship").toString().toLowerCase();
			  			sb.append("<font color=\"color1\">");
			  			sb.append("finalEvent:");
				         sb.append("</font>");
			  			sb.append("&nbsp;");
			  			sb.append(finalEvent);
			  			sb.append("<br/>");
			  			
			  			sb.append("<font color=\"color1\">");
			  			sb.append("finalTime:");
				         sb.append("</font>");
			  			sb.append("&nbsp;");
			  			sb.append(finalTime);
			  			sb.append("<br/>");
			  			
			  			sb.append("<font color=\"color1\">");
			  			sb.append("finalLoc:");
				         sb.append("</font>");
			  			sb.append("&nbsp;");
			  			sb.append(finalLoc);
			  			sb.append("<br/>");
			  			
			  			sb.append("<font color=\"color1\">");
			  			sb.append("relationship:");
				         sb.append("</font>");
			  			sb.append("&nbsp;");
			  			sb.append(relationship);
			  			sb.append("<br/>");
			  		
			        }	
			  			
		            sb.append("</div>");
		            sb.append("</body></html>");
		           
		            PrintStream printStream = new PrintStream(new FileOutputStream("D:/E-calendar.html",true));
		           
		            printStream.println(sb.toString());//将字符串写入文件
			        }catch (IOException e) {
			            e.printStackTrace();
			        }
		        }}
		           
		          
		           
		    
		                                                                                                                                                                  
		       
	 

 // class printCalendar
