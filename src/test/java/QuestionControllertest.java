package test.java;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.GQuestion;
import main.java.Language;
import main.java.LanguageController;
import main.java.Module;
import main.java.ModuleController;
import main.java.Option;
import main.java.Question;
import main.java.QuestionController;
import main.java.Window;
import main.java.WindowController;

public class QuestionControllertest {
	Window w = new Window();
	WindowController wc = w.wc;
	ModuleController mc = new ModuleController(wc);
	QuestionController qc = new QuestionController(wc);
	LanguageController lc = new LanguageController(wc);
	
	@Before
	public void setUp() throws InterruptedException {
		mc.ModuleButtons();
		lc.LanguageButtons();
		qc.QuestionButtons();
	}
	
	@Test
	public void testNewQuestion() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("test1"));
		wc.languages.add(new Language("test2"));
		wc.languages.add(new Language("test3"));
		Window.textArea.setText("testq");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);	
		click(980, 550);
		assertEquals(0, Window.modelq.getElementAt(0).getID());
	}
	
	@Test
	public void testNewQuestionList() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("test1"));
		wc.languages.add(new Language("test2"));
		wc.languages.add(new Language("test3"));
		Window.textArea.setText("testq");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);	
		click(980, 550);
		click(980, 230);
		assertEquals(0, wc.curquest.getID());
	}
	
	@Test
	public void testNewQuestionEdit() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("test1"));
		wc.languages.add(new Language("test2"));
		wc.languages.add(new Language("test3"));
		Window.textArea.setText("testq");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);	
		click(980, 550);
		click(980, 230);
		click(1280, 250);
		Robot bot = new Robot();
		bot.keyPress(KeyEvent.VK_1);
		Thread.sleep(100);
		bot.keyRelease(KeyEvent.VK_1);
		assertEquals("1", wc.curquest.getBody().get(0));
	}
	
	@Test
	public void testNewQuestionRemove() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("test1"));
		wc.languages.add(new Language("test2"));
		wc.languages.add(new Language("test3"));
		Window.textArea.setText("testq");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);	
		click(980, 550);
		click(980, 230);
		click(980, 590);
		
		assertTrue(wc.curquest == null);
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
		ArrayList<Question> ql = new ArrayList<Question>();
		ArrayList<GQuestion> gl = new ArrayList<GQuestion>();
		return new Module(l, ql, gl);
	}
	
	
}
