package student;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import org.junit.Assert;

import graph.DGraph;
import graph.Edge;
import graph.ListDGraph;
import graph.Vertex;

public class StudentTest {
 /**
  * TODO: 
  * 	write at least 20 Junit test cases to test your implementation of ListDGraph;
  */
	
	
	
	
	
	
DGraph <String> graph = new ListDGraph <String> ();

    @Before
	public void setUp () throws Exception {
    	graph.addV ("1");
    	graph.addV ("2");
    	graph.addV ("3");
    	graph.addV ("4");
    	graph.addV ("5");
    	graph.addV ("6");
    	graph.addV ("7");
    	graph.addV ("8");
    	graph.addV ("9");
    	graph.addV ("10");
    	
        
	}
	
	@Test
	public void test_add_V_outofbounds() {
		int index = graph.addV(String.valueOf(Integer.MIN_VALUE));
		System.out.println(index);
		Assert.assertEquals(-1,index);

	}
	@Test
	public void test_add_V_intandlarge() {
		int index = graph.addV(String.valueOf(Integer.MAX_VALUE));
		System.out.println(index);
		Assert.assertEquals(11,index);

	}
	
	@Test
	public void test_add_E_notexist() {
		Edge<String> e = new Edge<String>(String.valueOf(Integer.MIN_VALUE),String.valueOf(Integer.MAX_VALUE));
		boolean success = graph.addE(e);
		Assert.assertEquals(false,success);
	}
	
	
	@Test
	public void test_remove_E_nonexist() {
		Edge <String> e2 = graph.removeE(new Edge <String> ("1","66"));
		Assert.assertEquals(null,e2);
	}
	
	@Test
	public void test_V_equals() {
		Vertex v = new Vertex("5");
		Vertex t = new Vertex("5");
		Edge<String> e = new Edge<String>("5", "1");
		v.addEdge(e);
		boolean success = v.equals(t);
		
		Assert.assertEquals(false,success);
	}
	
	@Test
	public void test_V_equals2() {
		Vertex v = new Vertex("5");
		Vertex t = new Vertex("5");
		Edge<String> e = new Edge<String>("5", "1");
		//Edge<String> e = new Edge<String>("5", "1");
		v.addEdge(e);
		t.addEdge(e);
		boolean success = v.equals(t);
		
		Assert.assertEquals(true,success);
	}
	
	
	@Test
	public void test_remove_V_nonexist() {
		Assert.assertEquals(null, graph.removeV(String.valueOf(Integer.MAX_VALUE)));
	}
	
	@Test
	public void test_remove_V() {
		graph.addV("213");
		String success = graph.removeV("213");
	
		Assert.assertEquals("213", success);
	}
	
	
	
	
	@Test
	public void test_if() {
		
		graph.addE(new Edge<String>("1","2"));
		graph.addE(new Edge<String>("1","3"));
		graph.addE(new Edge<String>("2","4"));
		graph.addE(new Edge<String>("3","4"));
    	
    	ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("4");
    	b2.add ("1"); b2.add ("3"); b2.add ("4"); 
    	expected.add (b1);
    	expected.add (b2);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);
        

	}
	
	@Test
	public void test_for() {
		graph.addE(new Edge<String>("1","2"));
		graph.addE(new Edge<String>("2","3"));
		graph.addE(new Edge<String>("3","2"));
		graph.addE(new Edge<String>("2","4"));
		
		
		
		ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("3"); b1.add ("2"); b1.add ("4");
    	b2.add ("1"); b2.add ("2"); b2.add ("4"); 
    	expected.add (b1);
    	expected.add (b2);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);

	}
	@Test
	public void test_block_return() {
		graph.addE(new Edge<String>("1","2"));
		//graph.addE(new Edge<String>("2","3"));
		
		ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	//ArrayList <String> b2 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2");
    	
    	expected.add (b1);
    	//expected.add (b2);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);

	}
	
	@Test
	public void test_if_return() {
		
		graph.addE(new Edge<String>("1","2"));
		graph.addE(new Edge<String>("2","3"));
		graph.addE(new Edge<String>("1","4"));
		graph.addE(new Edge<String>("4","5"));
		
		
		ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("3");
    	b2.add ("1"); b2.add ("4"); b2.add ("5"); 
    	expected.add (b1);
    	expected.add (b2);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);
	}
	
	
	
	@Test
	public void test_nestedloops() {
		
		graph.addE(new Edge <String> ("1", "2"));
		graph.addE(new Edge <String> ("2", "3"));
    	graph.addE(new Edge <String> ("3", "4"));
    	graph.addE(new Edge <String> ("4", "3"));
    	graph.addE(new Edge <String> ("3", "5"));
    	graph.addE(new Edge <String> ("1", "6"));
    	graph.addE(new Edge <String> ("6", "7"));
    	graph.addE(new Edge <String> ("7", "8"));
    	graph.addE(new Edge <String> ("8", "7"));
    	graph.addE(new Edge <String> ("7", "9"));
    	graph.addE(new Edge <String> ("5", "10"));
    	graph.addE(new Edge <String> ("9", "10"));
		
		
		
		ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();
    	ArrayList <String> b3 = new ArrayList <String> ();
    	ArrayList <String> b4 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("3"); b1.add ("4"); b1.add ("3"); b1.add ("5"); b1.add ("10");
    	b2.add ("1"); b2.add ("2"); b2.add ("3"); b2.add ("5");  b2.add ("10"); 
    	b3.add ("1"); b3.add ("6"); b3.add ("7"); b3.add ("8");  b3.add ("7"); b3.add ("9"); b3.add ("10"); 
    	b4.add ("1"); b4.add ("6"); b4.add ("7"); b4.add ("9"); b4.add ("10");
    	expected.add (b1);
    	expected.add (b2);
    	expected.add (b3);
    	expected.add (b4);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);
	}
	
	
	
	
	@Test
	public void test_for_if() {
		graph.addE(new Edge <String> ("1", "2"));
		graph.addE(new Edge <String> ("2", "3"));
    	graph.addE(new Edge <String> ("3", "4"));
    	graph.addE(new Edge <String> ("4", "5"));
    	graph.addE(new Edge <String> ("4", "6"));
    	graph.addE(new Edge <String> ("5", "3"));
    	graph.addE(new Edge <String> ("6", "3"));
    	graph.addE(new Edge <String> ("3", "7"));
    	
    	ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();
    	ArrayList <String> b3 = new ArrayList <String> ();
    	ArrayList <String> b4 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("3"); b1.add ("5"); b1.add ("3"); b1.add ("7");
    	b2.add ("1"); b2.add ("2"); b2.add ("3"); b2.add ("7");
    	b3.add ("1"); b3.add ("2"); b3.add ("3"); b3.add ("4");  b3.add ("6"); b3.add ("3"); b3.add ("4"); b3.add ("5"); b3.add ("3"); b3.add ("7");
    	b4.add ("1"); b4.add ("2"); b4.add ("3"); b4.add ("4"); b4.add ("6"); b4.add ("3"); b4.add ("7");
    	expected.add (b1);
    	expected.add (b2);
    	expected.add (b3);
    	expected.add (b4);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);
	}
	
	
	@Test
	public void test_getV() {
		Assert.assertEquals(null, graph.getV(32));
	}
	
	@Test
	public void test_getE() {
		Edge<String> edge = new Edge<String>("7","10");
		graph.addE(edge);
		Edge<String> success = graph.getE(7, 10);
		Assert.assertEquals(true, edge.equals(success));
	}
	
	@Test
	public void test_getEnon() {
		Edge<String> edge = new Edge<String>("7","1111");
		Edge<String> success = graph.getE(7, 1111);
		Assert.assertEquals(false, edge.equals(success));
	}
	
	@Test
	public void test_getV2() {
		Assert.assertEquals("1", graph.getV(1));
		
	}
	
	
	
	@Test
	public void test_2if() {
		graph.addE(new Edge <String> ("1", "2"));
		graph.addE(new Edge <String> ("2", "3"));
    	graph.addE(new Edge <String> ("3", "4"));
    	graph.addE(new Edge <String> ("3", "5"));
    	graph.addE(new Edge <String> ("2", "6"));
    	graph.addE(new Edge <String> ("4", "7"));
    	graph.addE(new Edge <String> ("5", "7"));
    	graph.addE(new Edge <String> ("6", "7"));
    	
		
		
		
		ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();
    	ArrayList <String> b3 = new ArrayList <String> ();
    	

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("3"); b1.add ("4"); b1.add ("7"); 
    	b2.add ("1"); b2.add ("2"); b2.add ("3"); b2.add ("5");  b2.add ("7"); 
    	b3.add ("1"); b3.add ("2"); b3.add ("6"); b3.add ("7");
    	//b4.add ("1"); b4.add ("6"); b4.add ("7"); b4.add ("9"); b4.add ("10");
    	expected.add (b1);
    	expected.add (b2);
    	expected.add (b3);
    	//expected.add (b4);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);

	}
	@Test
	public void test_3if() {
		graph.addE(new Edge <String> ("1", "2"));
		graph.addE(new Edge <String> ("2", "3"));
    	graph.addE(new Edge <String> ("3", "4"));
    	graph.addE(new Edge <String> ("4", "5"));
    	graph.addE(new Edge <String> ("4", "6"));
    	graph.addE(new Edge <String> ("3", "7"));
    	graph.addE(new Edge <String> ("2", "8"));
    	graph.addE(new Edge <String> ("3", "7"));
    	graph.addE(new Edge <String> ("6", "9"));
    	graph.addE(new Edge <String> ("5", "9"));
    	graph.addE(new Edge <String> ("7", "9"));
    	graph.addE(new Edge <String> ("8", "9"));
    	
		
		
		
		ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();
    	ArrayList <String> b3 = new ArrayList <String> ();
    	ArrayList <String> b4 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("3"); b1.add ("4"); b1.add ("5"); b1.add ("9");
    	b2.add ("1"); b2.add ("2"); b2.add ("3"); b2.add ("4");  b2.add ("6"); b2.add ("9");
    	b3.add ("1"); b3.add ("2"); b3.add ("3"); b3.add ("7"); b3.add ("9");
    	b4.add ("1"); b4.add ("2"); b4.add ("8"); b4.add ("9");
    	expected.add (b1);
    	expected.add (b2);
    	expected.add (b3);
    	expected.add (b4);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);

	}
	@Test
	public void test_2loop() {
		graph.addE(new Edge <String> ("1", "2"));
		graph.addE(new Edge <String> ("2", "3"));
    	graph.addE(new Edge <String> ("3", "4"));
    	graph.addE(new Edge <String> ("4", "3"));
    	graph.addE(new Edge <String> ("4", "5"));
    	graph.addE(new Edge <String> ("5", "6"));
    	graph.addE(new Edge <String> ("6", "7"));
    	graph.addE(new Edge <String> ("7", "6"));
    	graph.addE(new Edge <String> ("6", "8"));
    	graph.addE(new Edge <String> ("3", "9"));
    	//graph.addE(new Edge <String> ("7", "9"));
    	//graph.addE(new Edge <String> ("8", "9"));
    	
		
		
		
		ArrayList <ArrayList <String>> branches = new ArrayList<ArrayList <String>>();
    	branches = graph.branches("1");
    	ArrayList <ArrayList <String>> expected = new ArrayList <ArrayList <String>> ();
    	ArrayList <String> b1 = new ArrayList <String> ();
    	ArrayList <String> b2 = new ArrayList <String> ();
    	ArrayList <String> b3 = new ArrayList <String> ();
    	ArrayList <String> b4 = new ArrayList <String> ();

    	
    	b1.add ("1"); b1.add ("2"); b1.add ("3"); b1.add ("4"); b1.add ("3"); b1.add ("9");
    	b2.add ("1"); b2.add ("2"); b2.add ("3"); b2.add ("4");  b2.add ("5"); b2.add ("6"); b2.add ("7"); b2.add ("6"); b2.add ("8");
    	b3.add ("1"); b3.add ("2"); b3.add ("3"); b3.add ("4"); b3.add ("5"); b3.add ("6"); b3.add ("8");
    	b4.add ("1"); b4.add ("2"); b4.add ("3"); b4.add ("9");
    	expected.add (b1);
    	expected.add (b2);
    	expected.add (b3);
    	expected.add (b4);

    	
    	for (ArrayList <String> branch:branches)
    		System.out.println(branch);
    	
        Assert.assertEquals (branches.size (), expected.size ());
        boolean match = branches.stream().anyMatch(branch -> expected.contains(branch));	  
        Assert.assertEquals (match, true);

	}
	
	
	
	
	
	
	
	
}
