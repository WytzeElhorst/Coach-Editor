package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.GQuestion;
import main.java.LanguageController;
import main.java.Module;
import main.java.Option;
import main.java.Question;
import main.java.Window;
import main.java.WindowController;

public class LanguageControllertest {
	WindowController wc = new WindowController();
	LanguageController lc = new LanguageController(wc);
	Window w = new Window();
	
	@Before
	public void setUp() throws InterruptedException {
		wc = new WindowController();
		lc = new LanguageController(wc);
		lc.LanguageButtons();
	}
	
	@Test
	public void testLanguageListL() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		Window.txtLangName.setText("Nederlands");
		Window.btnAddLanguage.doClick();
		wc.modules.add(newMod());
		wc.curmod = wc.modules.get(0);
		wc.curgquest = wc.curmod.getGquestions().get(0);
		wc.curquest = wc.curmod.getQuestions().get(0);
		wc.curopt = wc.curquest.getOptions().get(0);
		click(817, 713);
		assertEquals(1, wc.curlang);
	}

	@Test
	public void testNewLanguage() {
		Window.txtLangName.setText("Nederlands");
		Window.btnAddLanguage.doClick();
		assertEquals("Nederlands", wc.languages.get(0).getName());
	}
	
	@Test
	public void testNewLanguageMod() {
		wc.modules.add(newMod());
		Window.txtLangName.setText("Nederlands");
		Window.btnAddLanguage.doClick();
		assertEquals("test1", wc.modules.get(0).getTitle().get(0));
	}

	@Test
	public void testRenameLanguage() {
		Window.txtLangName.setText("Nederlands");
		Window.btnAddLanguage.doClick();
		Window.list.setSelectedIndex(0);
		lc.enableLanguageButtons();
		Window.txtLangName.setText("English");
		Window.btnNewButton_2.doClick();
		assertEquals("English", wc.languages.get(0).getName());
	}
	
	@Test
	public void testRemoveLanguage() {
		Window.txtLangName.setText("Nederlands");
		Window.btnAddLanguage.doClick();
		Window.list.setSelectedIndex(0);
		lc.enableLanguageButtons();
		wc.modules.add(newMod());
		Window.btnRemoveL.doClick();
		assertTrue(wc.languages.isEmpty());
	}
	
	public static void click(int x, int y) throws AWTException, InterruptedException{
	    Robot bot = new Robot();
	    bot.mouseMove(x, y);
	    Thread.sleep(200);
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    Thread.sleep(200);
	}
	
	public Module newMod() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("test1");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		l.add("test2");
		ArrayList<Option> ol = new ArrayList<Option>();
		ol.add(new Option(l, l, null));
		Question q = new Question(0, l, ol);
		ArrayList<Question> ql = new ArrayList<Question>();
		ql.add(q);
		GQuestion g = new GQuestion(l, l);
		ArrayList<GQuestion> gl = new ArrayList<GQuestion>();
		gl.add(g);
		return new Module(l, ql, gl);
	}
	
	
}
