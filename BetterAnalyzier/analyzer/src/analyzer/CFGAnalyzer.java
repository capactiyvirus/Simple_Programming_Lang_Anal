package analyzer;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Stream;

import graph.Vertex;
import graph.ListDGraph;
import graph.Edge;


/**
 * @desc build and analyze CFG of a given method.
 * @author you
 */
public class CFGAnalyzer {
	/**
	 * TODO: build and analyze CFG of a given method. 
	 * 		 You can create auxiliary classes/functions if needed. 
	 */
	
	String pathTo;
	private static List<String> keyWords = Arrays.asList("def","if","for","return","else");
	private ArrayList<String> codes = new ArrayList<String>();
	private ListDGraph<String> vList = new ListDGraph<String>();
	private int count = 1;
	
	private int[][] mat;
	
	private ArrayList<ArrayList<String>> holder;
	//private LinkedList<Vertex<V>> vList;
	
	
	public CFGAnalyzer(String path){
		this.pathTo = path;
		
	}
	
	
	private String pathGetter() {
		return this.pathTo;
	}
	
	private ArrayList<String> getCodes() {
		return this.codes;
	}
	
	
	
	
	private void addEdge(int curStr, int curEnd) {
		Edge e = new Edge(String.valueOf(curStr), String.valueOf(curEnd));
		vList.addE(e);
	}
	
	private void addVertex(int curCount) {
		vList.addV(String.valueOf(curCount));
	}
	
	
	
	public void buildCFG() throws Exception {
		String copyPath = pathGetter();
		ArrayList<String> copyCodes = getCodes();
		
		//read to set codes
		readLines(copyPath);
		Pattern retPat = Pattern.compile("return", Pattern.CASE_INSENSITIVE);
		Matcher match = retPat.matcher(copyCodes.get(copyCodes.size()-1));
		if(!match.find()) {
			copyCodes.add("return");
		}
		
		
		
		
	
		int fortracker = -1;
		int elsetracker = -1;
		int iftracker = -1;
		//int iftracker = 0; 
		boolean found = false;
		//boolean trigger = false;
		//int count=1;
		
		boolean tra = false;
		//System.out.println(copyCodes);
		
		
		
		
		//int iftracker = -1; 
		int wordCount = 1;
		
		for(String i : copyCodes) {
			found = false;
			for(String code: keyWords) {
				
				
				
				//System.out.println(iftracker + " this is if TRACKER");
				//System.out.println(fortracker + " this is for Tracker");
				//System.out.println(elsetracker + " this is elseTracker");
				
				
				Pattern pattern = Pattern.compile(code, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(i);
				
				//if we match the codes
				if(matcher.find()) {
					//System.out.println(count);
					//System.out.println(tracker);
					if(code == "def") {
						addVertex(count);
						count++;
						addVertex(count);
					}
					
					//if statement check
					else if(code == "if") {
						addVertex(count);
						iftracker = count;
						tra = true;
					}
					
					//for loop check
					else if(code == "for") {
						
						addVertex(count);
						
						if(tra) {
							addEdge(elsetracker-1,count);
							
							tra = false;
						}
						
						addEdge(count-1,count);
						
						count++;
						if (elsetracker != -1) {
							addVertex(count);
							addEdge(count-1,count);
							count++;
						}
						
						addVertex(count);
						addEdge(count-1,count);
						fortracker = count;
						count++;
						addVertex(count);
						addEdge(count-1,count);
						addEdge(count,count-1);
						
						
					}
					
					//else statement check
					else if(code == "else") {
						addVertex(count);
						addEdge(iftracker-1,count);
						iftracker = -1;
						elsetracker = count;
						
					}
					

					// rest of the commands such as return
					else {
						//System.out.println(" RUN ELSE SHOULD RUN AFTER ELSE");
						addVertex(count);
						if(tra) {
							addEdge(elsetracker-1,count);

							tra = false;
						}
						if(fortracker != -1) {
							
							//System.out.println("RUNNING the For tracker");
							addEdge(fortracker,count);
							//fortracker = -1;
							tra = false;
						}
						///
						elsetracker = -1;
					}
					
					//point to else and if statements 
					
					
					
					if(count>1) {
						
						if(elsetracker == -1 && fortracker == -1) {
							//System.out.println("PRINT ME PAPAY");
							addEdge(count-1,count);
							
						}

					}
					
					count++;
					//System.out.println(code);
					found = true;
				}
				
				
				
				
				// on the times we dont
				else if(wordCount == 5 && found==false) {
					
					//System.out.println(code + " wowza");
					
				}
				

				wordCount++;
			}
			wordCount = 1;
		}
		
		this.mat = vList.matrix();
		this.holder = vList.branches("1");
		
	}
	
	 

	public void Matrix() {

        System.out.println();
        System.out.println("matrix:");
        
        int i = 0;
        for(; i < this.mat.length; i++) {
            if (i == 0)
                System.out.print("  " + (i+1));
            else
                System.out.print(" " + (i+1));
        }
        System.out.println();
        
        for(i = 0; i < this.mat.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < this.mat[i].length; j++) {
                System.out.print(" " + this.mat[i][j]);
            }
           System.out.println();
        }
        
        System.out.println();
    }
	
	public void showBranch() {
		System.out.println();
		//System.out.println(holde);
		String a = "";
		
		
		System.out.println("branches:");
		for (int i = 0;  i < holder.size();i++) {
			a = "";
			//System.out.println(holder.get(i));
			for ( String j : holder.get(i)) {
				
				//System.out.println(j);
				
				//System.out.println(holder.get(i).get(holder.get(i).size()-1) + " SUZES");
				//
				if(j.equals(holder.get(i).get(holder.get(i).size()-1))) {
					a= a+j;
				}
				else {
					a = a + j + " -> ";
				}
				//a = a + j + " -> ";
			}
			System.out.println(a);
		}
		//System.out.println(a);
		
	}
	
	public void nBranch() {
		System.out.println();
		System.out.println("nBranch: " +holder.size());
		
	}
	
	

	
	public void readLines(String path) throws Exception {
	        try (Stream<String> lines = Files.lines(Paths.get(path), Charset.defaultCharset())) {
	            lines.forEachOrdered(line -> codes.add(line));
	        }
	        catch (Exception e) {
	            System.err.println(e.getMessage());
	        }
	        
	        
	        
	     //   System.out.println(codes);
	     //   System.out.println(keyWords);
	    }
	
	public void writeLines(String path) {
		try {
		    Files.write(Paths.get(path), "\n return".getBytes(),  StandardOpenOption.APPEND);
		}catch (Exception e) {
		    //exception handling left as an exercise for the reader
		}
		
	}
	 
	 
	
}
