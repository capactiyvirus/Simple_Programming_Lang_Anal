package graph;

import java.util.LinkedList;
import java.util.List;

import util.Message;
import util.Utils;

/**
 * The vertex class; a Vertex object has a vertex and a list of edges started from it;
 * @author wangs and you
 * @param <V> 
 */
public class Vertex<V> {
	
	/**vertex */
    private V v;
    /** edges started from this vertex*/
    private List<Edge<V>> edgeList;
    
    /**
     * constructor
     * @param v
     */
    public Vertex(V v) {
        this.v = v;
        this.edgeList = new LinkedList<Edge<V>>();
    }
    
    public V getV() {
		return v;
	}

	public List<Edge<V>> getEdgeList() {
		return edgeList;
	}

	/**
     * add an edge to the edge list of this vertex.
     * if add successfully return true;
     * if edge exists: print `M3` and return false;
     * if `e`'s src is not this vertex: print `M5` and return false;
     * @param e
     */
    public boolean addEdge(Edge<V> e) {
       /**
        * TODO: add an edge to the edge list;
        */
    	
    	for(Edge<V> edge : edgeList) {
    		
    		//TEST CASE FOR THIS  
//    		System.out.println("START EDGE LIST SEARCh");
//    		System.out.println(e);
//    		System.out.println(edge);
//    		System.out.println(e.getSource() + "e.getsource");
//    		System.out.println(this.v);
    		if(!e.getSource().equals(this.v) ) {
    			Utils.log(Message.M5,this.v);
    			return false;
    		}
    		//System.out.println(edge);
    		if(edge.equals(e)) {
    			Utils.log(Message.M3,e);
    			return false;
    		}
    	}
   
    	
    	edgeList.add(e);
    	return true;
    }
    
    /**
     * get an edge between this vertex and the destination V "dest";
     * if 'dest' does not exist: print `M5` and return null; 
     * if edge does not exist: print `M6` and return null; 
     * @param dest
     * @return 
     */
    public Edge<V> getEdge(V dest) {
    	/**
         * TODO: get the edge between this vertex and the destination V "dest";
         */
    	List<Edge<V>> newEdgeList = getEdgeList();

    	
    	for(Edge<V> newEdge : newEdgeList) {
    		if(newEdge.getDest().equals(dest)) {
    			return newEdge;
    		}
    	}
    	Utils.log(Message.M6,this.v+", "+dest);
     	return null;
    }
    
    /**
     * remove an edge from the edge list of this vertex
     * if 'dest' exists return the removed edge;
     * if 'dest' does not exist: print `M5` and return null; 
     * if edge does not exist: print `M6` and return null; 
     * @param dest
     * @return removed Edge<V>
     */
    public Edge<V> removeEdge(V dest) {
    	/**
         * TODO: removed an edge
         */
    	boolean checker = false;
    	
    	for(Edge e: edgeList) {
    		//System.out.println(e);
    		if(e.getDest().equals(dest)) {
    			checker = true;
    			
    		}
    	}
    	
    	if(dest == null || !checker ) {
    		Utils.log(Message.M5,dest);
    		return null;
    	}
    	List<Edge<V>> newEdgeList = getEdgeList();
    	
    	
    	//check if dest doenst exist
    	
    	for(Edge<V> newEdge : newEdgeList) {
    		if(newEdge.getDest() == dest) {
    			edgeList.remove(newEdge);
    			return newEdge;
    		}
  
    	}
    	
    	Utils.log(Message.M6,dest);    	
     	return null;
    }

    public boolean equals(Vertex<V> o) { 
		/**
		 * TODO: implement the comparison between two vertices
		 * IFF `v` and `edgeList` are the same return true
		 */
    	if(v.equals(o.getV()) && getEdgeList().equals(o.getEdgeList())) {
    		return true;
    	}
		return false;
	}
    
    @Override
    public String toString() {
        String ret = String.format("v : %s , edges: %s", v, edgeList.toString());
        return ret;
    }    
}