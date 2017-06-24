package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.GQuestion;

public class GQuestiontest {
	GQuestion g;

	@Before
	public void setUp() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		g = new GQuestion(l, l2);
	}

	@Test
	public void testAnswer() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text2");
		l.add("text3");
		g.setAnswer(l);
		assertEquals(l, g.getAnswer());
	}
	
	@Test
	public void testaddResponse() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text3");
		l.add("text4");
		l.add("text5");
		g.addResponse("text5");
		assertEquals(l, g.getAnswer());
	}
	
	@Test
	public void testQuestion() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text2");
		l.add("text3");
		g.setQuestion(l);
		assertEquals(l, g.getQuestion());
	}
	
	@Test
	public void testaddQuestion() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		l.add("text3");
		g.addQuestion("text3");
		assertEquals(l, g.getQuestion());
	}
	
	@Test
	public void testToString() {
		g.setCurname("vraag1");
		assertEquals(g.toString(), "vraag1");
	}
	
	@Test
	public void testCurname() {
		g.setCurname("vraag1");
		assertEquals(g.getCurname(), "vraag1");
	}
	
	@Test
	public void testEquals() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		GQuestion newg = new GQuestion(l, l2);
		assertTrue(newg.equals(g));
	}
	
	@Test
	public void testNotEquals1() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		GQuestion newg = new GQuestion(l, l2);
		assertFalse(newg.equals(g));
	}
	
	@Test
	public void testNotEquals2() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text2");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text4");
		GQuestion newg = new GQuestion(l, l2);
		assertFalse(newg.equals(g));
	}
	
	@Test
	public void testNotEquals3() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		GQuestion newg = new GQuestion(l, l2);
		assertFalse(newg.equals(g));
	}
	
	@Test
	public void testNotEquals4() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("text1");
		l.add("text2");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("text3");
		l2.add("text2");
		GQuestion newg = new GQuestion(l, l2);
		assertFalse(newg.equals(g));
	}

}
