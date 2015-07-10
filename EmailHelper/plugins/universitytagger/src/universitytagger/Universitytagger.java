/*
 *  Universitytagger.java
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
 *  Administrator, 8/2/2014
 *
 * For details on the configuration options, see the user guide:
 * http://gate.ac.uk/cgi-bin/userguide/sec:creole-model:config
 */

package universitytagger;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import javax.swing.text.Utilities;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Document;
import gate.DocumentContent;
import gate.FeatureMap;
import gate.Node;
import gate.ProcessingResource;
import gate.Resource;
import gate.creole.AbstractLanguageAnalyser;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.metadata.CreoleResource;
import gate.creole.orthomatcher.AnnotationOrthography;
import gate.util.InvalidOffsetException;

/** 
 * This class is the implementation of the resource MYTESTING.
 */
@CreoleResource(name = "mytesting",
        comment = "Add a descriptive comment about this resource")

public class Universitytagger  extends AbstractLanguageAnalyser
implements ProcessingResource {
	@Override
	  public void execute() throws ExecutionException {
		  
	  String docname= document.getName();
	  System.out.println(docname);
	  
	  int tokens = document.getAnnotations().get("Token").size();
	  System.out.println("This document has"+tokens+"Token");
	  
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
	 
	  @Override
	  public Resource init() throws ResourceInstantiationException {
	  System.out.println(getClass().getName() + " is initialising.");
	   return this;
	   
	    
	 }
	 

	 public static void MyFile(String docname, Document document) throws FileNotFoundException, InvalidOffsetException {
		
				 
		 StringBuilder path=new StringBuilder();
		  
		   path.append("d:/test");
		   
	        File f3 = new File(path.toString());
	  
	        File f4 = new File(path.append("/"+"Dectection Result of "+ docname+".html").toString());
	        
	        f3.mkdir();
	        
	        String content="This is my NE result.";
	        
	        
	       
	        
	        
	        StringBuilder sb = new StringBuilder();
	        
	        try {
	            f4.createNewFile();
	            sb.append("<html><head><title>UniversityResult</title></head><body>");
	            sb.append("<div align='center'>");
	            
	            sb.append(content);
	            sb.append("<br/>");
	            AnnotationSet universitySet = document.getAnnotations().get("University");
	            AnnotationSet universitypersonSet = document.getAnnotations().get("UniversityPerson");
	            AnnotationSet universitypositionSet = document.getAnnotations().get("UniversityPosition");
	            AnnotationSet organizationSet = document.getAnnotations().get("OrganizationUnit");
	            AnnotationSet relationSet = document.getAnnotations().get("belongtoRelation");
	            AnnotationSet relationSet2 = document.getAnnotations().get("hasPositionRelation");
	            sb.append("<font color=\"color1\">");
	            sb.append("<font size=\"20px\">");
	            sb.append("University");
	            sb.append("</font>");
	            sb.append("</font>");
	            sb.append("<br/>");
	            ArrayList<Integer> list1 = new ArrayList <Integer>();
	            
		        for (Annotation ann : universitySet) {
		  		  try {
		  			  
		  			 FeatureMap features = ann.getFeatures ();
		  			
		  			if(!(ann.getFeatures().containsKey("matches")))
		  			{DocumentContent content2= document.getContent().getContent(
		  			          ann.getStartNode().getOffset(),
		  			          ann.getEndNode().getOffset());
		  			sb.append(content2);
		  			sb.append("<br/>");
		  				
		  			}
		  				
		  			else
		  			{
		  			ArrayList<Integer> list2 = (ArrayList<Integer>)(ann.getFeatures().get("matches"));
		  			
		  
		  			if(list1.contains(ann.getId()))
		  			{
		  				
		  				System.out.print("");
		  				
		  			}
		  			else
		  			{DocumentContent content2= document.getContent().getContent(
		  			          ann.getStartNode().getOffset(),
		  			          ann.getEndNode().getOffset());
		  			sb.append(content2);
		  			sb.append("<br/>");
		  			list1.addAll(list2);
		  			}
		  			
		  		} }
		  	
		  		  
		  		  catch (InvalidOffsetException e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		}
		  		  }
		        
		        sb.append("<font color=\"color1\">");
	            sb.append("<font size=\"20px\">");
	            sb.append("University Person");
	            sb.append("</font>");
	            sb.append("</font>");
	            sb.append("<br/>");
	            
		        for (Annotation ann : universitypersonSet) {
		        	FeatureMap features = ann.getFeatures ();
					
					if(!(ann.getFeatures().containsKey("matches")))
					{
					Object content2= ann.getFeatures().get("Title");
					
					Object content3= ann.getFeatures().get("Person");
					sb.append(content2);
					sb.append("&nbsp;");
					sb.append(content3);
					sb.append("<br/>");
						
					}
						
					else
					{
					ArrayList<Integer> list2 = (ArrayList<Integer>)(ann.getFeatures().get("matches"));
					
  
					if(list1.contains(ann.getId()))
					{
						
						System.out.print("");
						
					}
					else
					{
						Object content2= ann.getFeatures().get("Title");
						
						Object content3= ann.getFeatures().get("Person");
						sb.append(content2);
						sb.append("&nbsp;");
						sb.append(content3);
					sb.append("<br/>");
					list1.addAll(list2);
					}
					
		}
			  		  }
		        
		        sb.append("<font color=\"color1\">");
	            sb.append("<font size=\"20px\">");
	            sb.append("University Position");
	            sb.append("</font>");
	            sb.append("</font>");
	            sb.append("<br/>");
	            
		        for (Annotation ann : universitypositionSet) {
		        	try {
			  			  
			  			 FeatureMap features = ann.getFeatures();
			  			
			  			if(!(ann.getFeatures().containsKey("matches")))
			  			{DocumentContent content2= document.getContent().getContent(
			  			          ann.getStartNode().getOffset(),
			  			          ann.getEndNode().getOffset());
			  			sb.append(content2);
			  			sb.append("<br/>");
			  				
			  			}
			  				
			  			else
			  			{
			  			ArrayList<Integer> list2 = (ArrayList<Integer>)(ann.getFeatures().get("matches"));
			  			
			  
			  			if(list1.contains(ann.getId()))
			  			{
			  				
			  				System.out.print("");
			  				
			  			}
			  			else
			  			{DocumentContent content2= document.getContent().getContent(
			  			          ann.getStartNode().getOffset(),
			  			          ann.getEndNode().getOffset());
			  			sb.append(content2);
			  			sb.append("<br/>");
			  			list1.addAll(list2);
			  			}
			  			
			  		} } catch (InvalidOffsetException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  		}
			  		  }
		        
		        sb.append("<font color=\"color1\">");
	            sb.append("<font size=\"20px\">");
	            sb.append("University Organization");
	            sb.append("</font>");
	            sb.append("</font>");
	            sb.append("<br/>");
	            
		        for (Annotation ann : organizationSet) {
		        	try {
			  			  
			  			 FeatureMap features = ann.getFeatures ();
			  			
			  			if(!(ann.getFeatures().containsKey("matches")))
			  			{DocumentContent content2= document.getContent().getContent(
			  			          ann.getStartNode().getOffset(),
			  			          ann.getEndNode().getOffset());
			  			sb.append(content2);
			  			sb.append("<br/>");
			  				
			  			}
			  				
			  			else
			  			{
			  			ArrayList<Integer> list2 = (ArrayList<Integer>)(ann.getFeatures().get("matches"));
			  			
			  
			  			if(list1.contains(ann.getId()))
			  			{
			  				
			  				System.out.print("");
			  				
			  			}
			  			else
			  			{DocumentContent content2= document.getContent().getContent(
			  			          ann.getStartNode().getOffset(),
			  			          ann.getEndNode().getOffset());
			  			sb.append(content2);
			  			sb.append("<br/>");
			  			list1.addAll(list2);
			  			}
			  			
			  		} } catch (InvalidOffsetException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  		}
			  		  }
		        
		        sb.append("<font color=\"color1\">");
	            sb.append("<font size=\"20px\">");
	            sb.append("Belong-to Relation");
	            sb.append("</font>");
	            sb.append("</font>");
	            sb.append("<br/>");
	            
		        for (Annotation ann : relationSet) {
		        	  
		  			 FeatureMap features = ann.getFeatures ();
		  			
		  			if(!(ann.getFeatures().containsKey("matches")))
		  			{ Object content2= ann.getFeatures().get("position");
		  			
					Object content3= ann.getFeatures().get("person");
					sb.append(content2);
					sb.append("&nbsp;");
					sb.append(content3);
					sb.append("<font color=\"color6\">");
		           
		             String belong=" Belongs to ";
				     sb.append(belong);
		          
		            sb.append("</font>");
					Object content4= ann.getFeatures().get("ran");
					sb.append(content4);
					sb.append("<br/>");
		  				
		  			}
		  				
		  			else
		  			{
		  			ArrayList<Integer> list2 = (ArrayList<Integer>)(ann.getFeatures().get("matches"));
		  			
		  
		  			if(list1.contains(ann.getId()))
		  			{
		  				
		  				System.out.print("");
		  				
		  			}
		  			else
		  			{ Object content2= ann.getFeatures().get("position");
					
					Object content3= ann.getFeatures().get("person");
					sb.append(content2);
					sb.append("&nbsp;");
					sb.append(content3);
					sb.append("<font color=\"color1\">");
		           
		           String belong=" Belongs to ";
				     sb.append(belong);
		            
		            sb.append("</font>");
					Object content4= ann.getFeatures().get("ran");
					sb.append(content4);
					sb.append("<br/>");
		  			list1.addAll(list2);
		  			}
		  			
			  		  }}
		        
		        sb.append("<font color=\"color1\">");
	            sb.append("<font size=\"20px\">");
	            sb.append("Has-Position Relation");
	            sb.append("</font>");
	            sb.append("</font>");
	            sb.append("<br/>");
	            
		        for (Annotation ann : relationSet2) {
		        	  
		  			 FeatureMap features = ann.getFeatures ();
		  			
		  			if(!(ann.getFeatures().containsKey("matches")))
		  			{ 
					Object content3= ann.getFeatures().get("dom");
					sb.append(content3);
					sb.append("<font color=\"color1\">");
		    
		           String belong=" Has Position ";
						sb.append(belong);
		           
		            sb.append("</font>");
		           
					Object content4= ann.getFeatures().get("ran");
					sb.append(content4);
					sb.append("<br/>");
		  				
		  			}
		  				
		  			else
		  			{
		  			ArrayList<Integer> list2 = (ArrayList<Integer>)(ann.getFeatures().get("matches"));
		  			
		  
		  			if(list1.contains(ann.getId()))
		  			{
		  				
		  				System.out.print("");
		  				
		  			}
		  			else
		  			{ 
					Object content3= ann.getFeatures().get("dom");
					sb.append(content3);
					sb.append("<font color=\"color1\">");
		        
		           String belong=" Has Position ";
						sb.append(belong);
		           
		            sb.append("</font>");
		           
					Object content4= ann.getFeatures().get("ran");
					sb.append(content4);
					sb.append("<br/>");
		  			list1.addAll(list2);
		  			}
		  			
			  		  }}
		        
	            sb.append("</div>");
	            sb.append("</body></html>");
	           
	            PrintStream printStream = new PrintStream(new FileOutputStream(f4));
	           
	            printStream.println(sb.toString());//将字符串写入文件
	           
	          
	           
	   
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	                                                                                                                                                                  
	       
	    }

}
 // class Universitytagger
