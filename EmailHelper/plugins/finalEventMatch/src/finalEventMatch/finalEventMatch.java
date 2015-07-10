/*
 *  finalEventMatch.java
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
 *  Administrator, 9/4/2014
 *
 * For details on the configuration options, see the user guide:
 * http://gate.ac.uk/cgi-bin/userguide/sec:creole-model:config
 */

package finalEventMatch;

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
import gate.Factory;
import gate.FeatureMap;
import gate.Node;
import gate.ProcessingResource;
import gate.Resource;
import gate.creole.AbstractLanguageAnalyser;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.metadata.CreoleParameter;
import gate.creole.metadata.CreoleResource;
import gate.creole.metadata.Optional;
import gate.creole.metadata.RunTime;
import gate.creole.orthomatcher.AnnotationOrthography;
import gate.util.InvalidOffsetException;
import gate.util.LuckyException;



/** 
 * This class is the implementation of the resource FINALEVENTMATCH.
 */
@CreoleResource(name = "finalEventMatch",
        comment = "Add a descriptive comment about this resource")

public class finalEventMatch  extends AbstractLanguageAnalyser
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
	 AnnotationSet eventSet = document.getAnnotations().get("targetEvent");
	 if(eventSet.size()>0){
	 String[] finalEvent=new String[30];
	 String[] eventRoot=new String[30];
	 int[] AnnoID=new int[30];
	  int[] counter=new int[30];
	  for(int n=0;n<30;n++)
	  {
	  counter[n]=0;
	  finalEvent[n]="";
	  eventRoot[n]="";
	  }
	  
	 int k=0; 
	 for (Annotation event: eventSet) 
	   {
		 Integer eventID =event.getId();
		 System.out.printf( eventID+" the target event ID\n"); 
		 try{String targetEvent = document.getContent().getContent(event.getStartNode().getOffset(),
				 event.getEndNode().getOffset()).toString();
	
		   
	   if (event.getFeatures().containsKey("eventRoot"))
	    { 

		String Root = event.getFeatures().get("eventRoot").toString().toLowerCase();

		   List<String> tmp = new ArrayList<String>(Arrays.asList(eventRoot));
			if(tmp.contains(Root))  
			{
			counter[tmp.indexOf(Root)]++;
			if(targetEvent.length()>finalEvent[tmp.indexOf(Root)].length())  {
				finalEvent[tmp.indexOf(Root)]=targetEvent;
			    AnnoID[tmp.indexOf(Root)]=eventID;
			}
			 } 
	   		
			 else
			 {  eventRoot[k]=Root;
				finalEvent[k]=targetEvent;
				AnnoID[k]=eventID;
				counter[k]++;
				k++;}}
	   
	   else
		 {  eventRoot[k]=targetEvent;
			finalEvent[k]=targetEvent;
			AnnoID[k]=eventID;
			counter[k]++;
			k++;}}
	 catch(InvalidOffsetException e){
	   		  throw new LuckyException(e);}
			  }
	  
	 System.out.printf(k+" is the total number of final events\n");
	 for(int i=0;i<k;i++)
	 {
	System.out.printf(finalEvent[i]+" the" +i +"th event\n");

	}	
	
	  int frequnecy=0;
	  String longestEvent=finalEvent[0];
	  String mostFrequnetEvent=finalEvent[0];
	  String longestEventRoot=eventRoot[0];
	  String mostFrequentEventRoot=eventRoot[0];
 
	  int longestEventID=AnnoID[0];  int mostFrequnetEventID=AnnoID[0];
	 for(int n=0;n<k;n++)
	  {	
	    if(frequnecy<=counter[n])
		 {frequnecy=counter[n];
	    mostFrequnetEvent=finalEvent[n];
	    mostFrequnetEventID=AnnoID[n];
	    mostFrequentEventRoot=eventRoot[n];
	    }
	  }
	 
	 for(int n=0;n<k;n++)
	  {	
	    if(longestEvent.length()<=finalEvent[n].length())
		 {longestEvent=finalEvent[n];
		 longestEventID=AnnoID[n];
	  longestEventRoot=eventRoot[n];
	  }}
	 int firstEventID=AnnoID[0]; String firstEvent=finalEvent[0];String firstEventRoot=eventRoot[0];
	 System.out.printf(mostFrequnetEvent+" is the most frequent event\n"+frequnecy+" the most frequent time\n");
	 System.out.printf(mostFrequentEventRoot+" is the most frequent eventRoot\n");
	 System.out.printf(longestEvent+" is the longest event\n");
	 System.out.printf(firstEvent+" is the first event ID\n");
	 
AnnotationSet outputAS = null;
if(outputASName == null || outputASName.equals("")) {
  	outputAS = document.getAnnotations();
      } else {
          outputAS = document.getAnnotations(outputASName);
      }

AnnotationSet outputAS2 = null;
if(outputASName == null || outputASName.equals("")) {
  	outputAS2 = document.getAnnotations();
      } else {
          outputAS2 = document.getAnnotations(outputASName);
      }

AnnotationSet outputAS3 = null;
if(outputASName == null || outputASName.equals("")) {
  	outputAS3 = document.getAnnotations();
      } else {
          outputAS3 = document.getAnnotations(outputASName);
      }


Annotation longestEvent1=document.getAnnotations().get(longestEventID);
Annotation mostFrequnetEvent1=document.getAnnotations().get(mostFrequnetEventID);
Annotation firstEvent1=document.getAnnotations().get(firstEventID);

FeatureMap newFeatures = Factory.newFeatureMap(); 	
newFeatures.put("longestEvent", longestEvent);
newFeatures.put("EventRoot", longestEventRoot);
newFeatures.put("rule", "finalEventMatch");
try {
	outputAS.add(longestEvent1.getStartNode().getOffset(),longestEvent1.getEndNode().getOffset(),"longestEvent", newFeatures);
} catch (InvalidOffsetException e) {
	throw new LuckyException(e);
} 

FeatureMap newFeatures2 = Factory.newFeatureMap(); 	
newFeatures2.put("topRankingEvent", mostFrequnetEvent);
newFeatures2.put("EventRoot", mostFrequentEventRoot);
newFeatures2.put("rule", "finalEventMatch");
try { 
	outputAS2.add(mostFrequnetEvent1.getStartNode().getOffset(),mostFrequnetEvent1.getEndNode().getOffset(),"topRankingEvent", newFeatures2);
} catch (InvalidOffsetException e) {
	throw new LuckyException(e);
} 

FeatureMap newFeatures3 = Factory.newFeatureMap(); 	
newFeatures3.put("firstEvent", firstEvent);
newFeatures3.put("EventRoot", firstEventRoot);
newFeatures3.put("rule", "finalEventMatch");
try {
	
	outputAS3.add(firstEvent1.getStartNode().getOffset(),firstEvent1.getEndNode().getOffset(),"firstEvent", newFeatures3);
} catch (InvalidOffsetException e) {
	throw new LuckyException(e);
} 
	}	
}} 
		  
		 
		 
		 
	
		 
		 
		 
	
      // class finalEventMatch
