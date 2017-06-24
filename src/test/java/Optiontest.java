package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import main.java.Option;
import main.java.Question;

public class Optiontest {
	Option opt;
	Question quest;

	@Before
	public void setUp() {
		quest = new Question(0, null, null);
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		opt = new Option(l, l2, quest);
	}

	@Test
	public void testsetChoice() {
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text4");
		l2.add("text5");
		opt.setChoice(l2);
		assertEquals(opt.getChoice(), l2);
	}
	
	@Test
	public void testsetResponse() {
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text4");
		l2.add("text5");
		opt.setResponse(l2);
		assertEquals(opt.getResponse(), l2);
	}
	
	@Test
	public void testgetLinkint() {
		Option newopt = new Option(null, null, 1);
		assertEquals(1, newopt.getLinkint());
	}
	
	@Test
	public void testToString() {
		assertEquals("Link to 0", opt.toString());
	}
	
	@Test
	public void testToStringHome() {
		quest.setID(-1);
		assertEquals("Link to Home", opt.toString());
	}
	
	@Test
	public void testEquals() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		Option newopt = new Option(l, l2, quest);
		assertTrue(opt.equals(newopt));
	}
	
	@Test
	public void testNotEquals() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		Option newopt = new Option(l, l2, quest);
		assertFalse(opt.equals(newopt));
	}
	
	@Test
	public void testNotEquals2() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text3");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		Option newopt = new Option(l, l2, quest);
		assertFalse(opt.equals(newopt));
	}
	
	@Test
	public void testNotEquals3() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text4");
		Option newopt = new Option(l, l2, quest);
		assertFalse(opt.equals(newopt));
	}
	
	@Test
	public void testNotEquals4() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text2");
		l2.add("text4");
		Option newopt = new Option(l, l2, quest);
		assertFalse(opt.equals(newopt));
	}
}
