package test.java;

import static org.junit.Assert.*;


import java.util.ArrayList;

import main.java.Question;
import main.java.Option;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
	Question quest;
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
	}

	@Test
	public void testSetBody() {
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("res");
		quest.setBody(ar);
		assertEquals("res", quest.getBody().get(0));
	}
	
	@Test
	public void testEquals() {
		ArrayList<String> c = new ArrayList<String>();
		c.add("text1");
		c.add("text2");
		ArrayList<Option> k = new ArrayList<Option>();
		k.add(opt);
		Question newquest = new Question(0, c, k);
		assertTrue(quest.equals(newquest));
	}
	
	@Test
	public void testNotEquals1() {
		ArrayList<String> c = new ArrayList<String>();
		c.add("text1");
		ArrayList<Option> k = new ArrayList<Option>();
		Question newquest = new Question(0, c, k);
		assertFalse(quest.equals(newquest));
	}
	
	@Test
	public void testNotEquals2() {
		ArrayList<String> c = new ArrayList<String>();
		c.add("text1");
		c.add("text3");
		ArrayList<Option> k = new ArrayList<Option>();
		Question newquest = new Question(0, c, k);
		assertFalse(quest.equals(newquest));
	}
	
	@Test
	public void testNotEquals3() {
		ArrayList<String> c = new ArrayList<String>();
		c.add("text1");
		c.add("text2");
		ArrayList<Option> k = new ArrayList<Option>();
		Question newquest = new Question(0, c, k);
		assertFalse(quest.equals(newquest));
	}
	
	@Test
	public void testNotEquals4() {
		ArrayList<String> c = new ArrayList<String>();
		c.add("text1");
		c.add("text2");
		ArrayList<Option> k = new ArrayList<Option>();
		Question newquest = new Question(0, c, k);
		ArrayList<String> b2 = new ArrayList<String>();
		b2.add("text2");
		b2.add("text4");
		ArrayList<String> b3 = new ArrayList<String>();
		b3.add("text5");
		b3.add("text6");
		Option opt2 = new Option(b2, b3, quest);
		k.add(opt2);
		assertFalse(quest.equals(newquest));
	}
	@Test
	public void testAddBody() {
		ArrayList<String> ar = quest.getBody();
		ar.add("text3");
		quest.addBody("test3");
		assertEquals(ar, quest.getBody());
	}
	
	@Test
	public void testSetID() {
		quest.setID(1);
		assertEquals(1, quest.getID());
	}
	
	@Test
	public void testSetOptions() {
		ArrayList<Option> l = new ArrayList<Option>();
		Option opt = new Option(null, null, quest);
		l.add(opt);
		quest.setOptions(l);
		assertEquals(l, quest.getOptions());
	}
	
	@Test
	public void testAddOption() {
		assertEquals(opt, quest.getOptions().get(0));
		
	}
	@Test
	public void testToString() {
		assertEquals("Question 0", quest.toString());
	}
	
	@Test
	public void testToStringhome() {
		quest.setID(-1);
		assertEquals("Home", quest.toString());
	}
	
//	@Test
//	public void testReadWrite() throws IOException {
//		URL url = getClass().getResource("questiontest.txt");
//		File file = new File(url.getPath());
//		Scanner sc = new Scanner(file);
//		Question quest = Question.Read(sc);
//		FileWriter fstream = new FileWriter(file);
//		BufferedWriter out = new BufferedWriter(fstream);
//		ArrayList<Language> languages = new ArrayList<Language>();
//		languages.add(new Language("Dutch"));
//		languages.add(new Language("English"));
//		quest.Print(out, languages);
//		Scanner sc2 = new Scanner(file);
//		Question newquest = Question.Read(sc2);
//		assertEquals(quest, newquest);
//		}
}
