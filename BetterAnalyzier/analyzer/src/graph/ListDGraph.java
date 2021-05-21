package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import util.Message;
import util.Utils;

/**
 * Use LinkedList to implement the directed graph
 * @param <V>
 * @author wangs and you
 */
public class ListDGraph<V> implements DGraph<V>{
   
    /**list of the vertices in a graph*/
    private LinkedList<Vertex<V>> vList;
        
    /**
     * constructor
     */
    public ListDGraph() {
        vList = new LinkedList<Vertex<V>>();
    }
    
    @Override
    public int addV(V v) {
      /**
       * TODO: implement the addV function;
       */
    	
    	///System.out.println(v);
    	if(Integer.valueOf((String) v) <= 0) {
    		Utils.log(Message.M4,v);
    		return -1;
    	}
    	
    	
    	// return the index of the Vertex
    	for(Vertex<V> s : vList) {
    		
    		if(s.getV().equals(v)) {
    			Utils.log(Message.M2,v);
    			return -1;
    		}
    	}
    	Vertex<V> newVertex = new Vertex<V>(v);
    	vList.add(newVertex);
    	//System.out.println(vList);
    	return vList.indexOf(newVertex)+1;
 
    }

    
    @Override
    public boolean addE(Edge<V> e) {
    	/**
        * TODO: implement the addE function;
        */
    	V edgeSrc = e.getSource();
    	V edgeDest = e.getDest();

    	//TEST CASE FOR THIS 
    	List<V> holder = new ArrayList<V>();
    
    	for(Vertex<V> s : vList) {
    		holder.add(s.getV());
    	
    	}
    	
    	if(!holder.contains(edgeSrc) || !holder.contains(edgeDest)) {
    		Utils.log(Message.M5,e);
    		return false;
    	}
    	

    	for(Vertex<V> s : vList) {
    		
       		if(edgeSrc.equals(s.getV()) ) {
    			if(!s.addEdge(e)) {
    				Utils.log(Message.M3,e);
    				return false;	
    			}
    		}	
    	}
    	//System.out.println(vList);
        return true;
    }
    
    @Override
    public V removeV(V v) {
    	/**
         * TODO: implement the removeV function;
         */

    	
    	if(v.equals(null)) {
    		Utils.log(Message.M5,v);
    		return null;
    	}
    	
    	//need to check if vertex does not exist in the list at all
    	List<V> holder = new ArrayList<V>();
    	for(Vertex<V> vert : vList) {
    		holder.add(vert.getV());
    	}
    	//System.out.println(holder);
    	if(!holder.contains(v)) {
    		Utils.log(Message.M5,v);
    		return null;
    	}
    	
    	for(Vertex<V> vert : vList) {
    		//System.out.println(vert.getEdge(v));
    		if(vert.getEdge(v) != null) {
    			vert.removeEdge(v);
    		}
    		
    	}
    	
    	
    	int count = 0;
    	for(Vertex<V> vertA: vList) {
    		//System.out.println(vertA.getV());
    		//System.out.println(v);
    		if(vertA.getV().equals(v)) {
    			break;
    		}
    		count++;
    	}
    	vList.remove(count);
    	count = 0;
    	//System.out.println(vList);
    	return v;
    }

    @Override
    public Edge<V> removeE(Edge<V> e) {
    	/**
         * TODO: implement the removeE function;
         */
    	
    	V EdgeSrc = e.getSource();
    	V EdgeDest = e.getDest();
    	
    	for(Vertex<V> s : vList) {
    		
    		if(EdgeSrc.equals(s.getV())) {
    			if(s.removeEdge(EdgeDest) != null) {
    				return e;
    			}
    		}    		
    	}
    	
    	Utils.log(Message.M6,e);
        return null;
    }

    
    
    
    @Override
    public V getV(int index) {
    	/**
         * TODO: implement the getV function;
         */
    	
    	
    	
    	if(index > vList.size() || index < 0) {
    		Utils.log(Message.M4,index);
    		return null;
    	}
    	
    	
    	return vList.get(index-1).getV();
    	
    }

    @Override
    public Edge<V> getE(int src, int dest) {
    	/**
         * TODO: implement the getE function;
         */
    	
    	for(int i=0; i<vList.get(src-1).getEdgeList().size();i++) {
    		if(vList.get(src-1).getEdgeList().get(i).getDest().equals(vList.get(dest-1).getV())) {
    			//System.out.println(vList.get(src-1).getEdgeList().get(i));
    			return vList.get(src-1).getEdgeList().get(i);
    		}
    		
    		
 
    	}
    	
    	Utils.log(Message.M5,dest + " or " + src);
    	
    	//System.out.println(vList);
        return null;
    }

	@Override
	public ArrayList<ArrayList<V>> branches(V v) {
		/**
		 * TODO: iterate the Graph from a given vertex and return all the branches from it;
		 */
		
		
		
		///CHEKC FOR THE UTILS Bs part
		
		
		
		Map<V, Integer> visitedNodes = new HashMap<V, Integer>();
		
		for( Vertex<V> vertex : vList) {
			visitedNodes.put(vertex.getV(),0);
		}
		
		
		ArrayList<V> vertexHolder = new ArrayList<V>();
		ArrayList<ArrayList<V>> arrayHolder = new ArrayList<ArrayList<V>>();
		
		
		Vertex<V> head = vList.get(Integer.valueOf((String) v)-1);
		
		//Call the helper
		DFSHelper(visitedNodes,head,vertexHolder,arrayHolder);
		
		//System.out.println(visitedNodes);
		
		return arrayHolder;
		
	
	}
	
	
	//The number of tracking will be of Times we need to hit a vertex will be based on edges
	private void DFSHelper(Map<V,Integer> visitedNodes, Vertex<V> currentNode, ArrayList<V> branch, ArrayList<ArrayList<V>> branches) {
	
		//Algo  
		if(currentNode.getEdgeList().isEmpty()) { // check if its a leaf which is only the time we want to add the branches (on leaf hit)
			branch.add(currentNode.getV()); // since its a leaf we want to add this to the branch
			
			ArrayList<V> holder = new ArrayList<V>(); // we need to make a holder copy of the branch since if we dont it will just add to the regular branch
			for(V element: branch) {
				holder.add(element);
			}
			//System.out.println(holder);
			branches.add(holder);
			return ; //return since its the end of the leaf
		}
		
		//VistedNodes Keep track of the nodes and how we visit them
		
		//Currentnode tells me the current node of the instance
		
		//Both arrays keep track of current and all branches
		
		
		
		//First thing we need to do is check that we visited the first node and add it to the branch
		visitedNodes.put(currentNode.getV(),visitedNodes.get(currentNode.getV())+1);
		branch.add(currentNode.getV());
		
		
		
		for(Edge<V> edge: currentNode.getEdgeList()) {
			Vertex<V> newHead = vList.get(Integer.valueOf((String) edge.getDest())-1);
			
			
			
			if(visitedNodes.get(currentNode.getV()) == 1 && visitedNodes.get(newHead.getV()) <2) {
				
				DFSHelper(visitedNodes,newHead,branch,branches);
				visitedNodes.put(branch.get(branch.size() - 1), 0);
				branch.remove(branch.size()-1);
			}
			else if(visitedNodes.get(currentNode.getV()) == 2 && visitedNodes.get(newHead.getV()) <1) {
				
				DFSHelper(visitedNodes,newHead,branch,branches);
				visitedNodes.put(branch.get(branch.size()-1), 0);
				branch.remove(branch.size()-1);
			}
			
			else if (visitedNodes.get(currentNode.getV()) == 0 && visitedNodes.get(newHead.getV()) == 0) {
		
				visitedNodes.put(currentNode.getV(), 1);
				DFSHelper(visitedNodes, newHead, branch, branches);
				visitedNodes.put(branch.get(branch.size()-1), 0);
				branch.remove(branch.size() - 1);
				
			}
		}
	}
	
	

	
    @Override
    public int [][] matrix() {
    	/**
    	 * TODO: generate the adjacency matrix of a graph;
    	 */
    	
    	int[][] a = new int[vList.size()][vList.size()];
    	
    	
    	
    	for(Vertex<V> v: vList) {
    		for(Edge<V> e: v.getEdgeList()) {
    			a[Integer.valueOf((String) e.getSource())-1][Integer.valueOf((String) e.getDest())-1] = 1;
    		}
    	}
    	

//    	for (int[] e: a) {
//    		System.out.println(Arrays.toString(e));
//    	}

    	return a;
 
    }	
}