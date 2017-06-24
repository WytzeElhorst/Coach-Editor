package test.java;

import static org.junit.Assert.*;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.GQuestion;
import main.java.Module;
import main.java.Option;
import main.java.Question;
import main.java.Window;
import main.java.WindowController;

public class Moduletest {
	Module mod;
	Question quest;
	GQuestion gquest;
	Option opt;

	@Before
	public void setUp() throws Exception{
		ArrayList<String> body = new ArrayList<String>();
		body.add("text1");
		body.add("text2");
		ArrayList<Option> l = new ArrayList<Option>();
		ArrayList<String> b2 = new ArrayList<String>();
		b2.add("text3");
		b2.add("text4");
		ArrayList<String> b3 = new ArrayList<String>();
		b3.add("text5");
		b3.add("text6");
		quest = new Question(0, body, l);
		opt = new Option(b2, b3, quest);
		l.add(opt);
		ArrayList<Question> q = new ArrayList<Question>();
		q.add(quest);
		ArrayList<GQuestion> gq = new ArrayList<GQuestion>();
		gquest = new GQuestion(b2, b3);
		gq.add(gquest);
		mod = new Module(body, q, gq);
	}
	
	
	@Test
	public void testsetTitle() throws IOException {
		ArrayList<String> t = new ArrayList<String>();
		t.add("test");
		mod.setTitle(t);
		assertEquals("test", mod.getTitle().get(0));
		}
	
	@Test
	public void testgetTitle() throws IOException {
		ArrayList<String> t = mod.getTitle();
		ArrayList<String> t2 = new ArrayList<String>();
		t2.add("text1");
		t2.add("text2");
		assertEquals(t2, t);
		}
	
	@Test
	public void testsetQuestions() throws IOException {
		mod.setQuestions(null);
		ArrayList<Question> q = new ArrayList<Question>();
		q.add(quest);
		mod.setQuestions(q);
		assertEquals(q.get(0), quest);
		}
	
	@Test
	public void testsetGquestions() throws IOException {
		mod.setGquestions(null);
		ArrayList<GQuestion> q = new ArrayList<GQuestion>();
		q.add(gquest);
		mod.setGquestions(q);
		assertEquals(q.get(0), gquest);
		}
	
	@Test
	public void testToString() throws IOException {
		mod.setCurTitle("curtitles");
		assertEquals("curtitles", mod.toString());
		}
	
	@Test
	public void testnotequals() throws IOException {
		ArrayList<String> s = new ArrayList<String>();
		Module newmod = new Module(s, mod.getQuestions(), mod.getGquestions());
		assertFalse(mod.equals(newmod));
	}
	
	@Test
	public void testnotequals2() throws IOException {
		ArrayList<String> s = new ArrayList<String>();
		s.add("text1");
		s.add("text3");
		Module newmod = new Module(s, mod.getQuestions(), mod.getGquestions());
		assertFalse(mod.equals(newmod));
	}
	
	@Test
	public void testnotequals3() throws IOException {
		ArrayList<Question> s = new ArrayList<Question>();
		s.add(new Question(1, null, null));
		s.add(new Question(1, null, null));
		Module newmod = new Module(mod.getTitle(), s, mod.getGquestions());
		assertFalse(mod.equals(newmod));
	}
	
	@Test
	public void testnotequals4() throws IOException {
		ArrayList<Question> s = new ArrayList<Question>();
		s.add(new Question(1, null, null));
		Module newmod = new Module(mod.getTitle(), s, mod.getGquestions());
		assertFalse(mod.equals(newmod));
	}
	
	@Test
	public void testnotequals5() throws IOException {
		ArrayList<GQuestion> s = new ArrayList<GQuestion>();
		s.add(new GQuestion(null, null));
		s.add(new GQuestion(null, null));
		Module newmod = new Module(mod.getTitle(), mod.getQuestions(), s);
		assertFalse(mod.equals(newmod));
	}
	
	@Test
	public void testnotequals6() throws IOException {
		ArrayList<GQuestion> s = new ArrayList<GQuestion>();
		ArrayList<String> body = new ArrayList<String>();
		body.add("text4");
		s.add(new GQuestion(body, body));
		Module newmod = new Module(mod.getTitle(), mod.getQuestions(), s);
		assertFalse(mod.equals(newmod));
	}
	
	@Test
	public void testReadWrite() throws IOException {
		WindowController wc = new WindowController();
		URL url = getClass().getResource("/test/assets/testfile.txt");
		File file = new File(url.getPath());
		URL url2 = getClass().getResource("/test/assets/testfile2.txt");
		File file2 = new File(url2.getPath());
		wc.load(file);
		Module mod = wc.modules.get(0);
		wc.Print(file2);
		wc.load(file2);
		Module newmod = wc.modules.get(0);
		assertTrue(mod.equals(newmod));
		}

}
