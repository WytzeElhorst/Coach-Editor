package test.java;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.GQuestion;
import main.java.GQuestionController;
import main.java.Language;
import main.java.LanguageController;
import main.java.Module;
import main.java.ModuleController;
import main.java.Option;
import main.java.Question;
import main.java.Window;
import main.java.WindowController;

public class GQuestionControllertest {
	Window w = new Window();
	WindowController wc = w.wc;
	ModuleController mc = new ModuleController(wc);
	GQuestionController qc = new GQuestionController(wc);
	LanguageController lc = new LanguageController(wc);
	
	@Before
	public void setUp() throws InterruptedException {
		mc.ModuleButtons();
		lc.LanguageButtons();
		qc.GQuestionButtons();
	}
	
	@Test
	public void testNewGQuestion() throws AWTException, InterruptedException {
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("lel"));
		wc.languages.add(new Language("lel"));
		wc.languages.add(new Language("lel"));
		Window.txtQuestion.setText("testq");
		Window.txtrAnswer.setText("testa");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);	
		Window.btnAddGQ.doClick();
		assertEquals("testa", Window.modelg.getElementAt(0).getAnswer().get(0));
	}
	
	@Test
	public void testGQuestionList() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("lel"));
		Window.txtQuestion.setText("testq");
		Window.txtrAnswer.setText("testa");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);	
		click(650, 590);
		click(700, 300);
		assertTrue(wc.curgquest != null);
	}
	
	@Test
	public void testGQuestionRemove() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("lel"));
		Window.txtQuestion.setText("testq");
		Window.txtrAnswer.setText("testa");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);		
		click(650, 590);
		click(700, 250);
		click(800, 590);
		assertTrue(wc.curgquest == null);
	}
	
	@Test
	public void testGQuestionEdit() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		lc.enableLanguageButtons();
		mc.enableModuleButtons();
		wc.curlang = 0;
		wc.languages.add(new Language("lel"));
		Window.txtQuestion.setText("testq");
		Window.txtrAnswer.setText("testa");
		wc.curmod = newMod();
		wc.modules.add(wc.curmod);		
		click(650, 590);
		Thread.sleep(1000);
		click(700, 300);
		Window.txtrAnswer.setText("testb");
		click(700, 630);
		assertEquals("testb", wc.curgquest.getAnswer().get(0));
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
