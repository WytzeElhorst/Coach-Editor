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
import main.java.OptionController;
import main.java.Question;
import main.java.QuestionController;
import main.java.Window;
import main.java.WindowController;

public class OptionControllertest {
	Window w = new Window();
	WindowController wc = w.wc;
	ModuleController mc = new ModuleController(wc);
	QuestionController qc = new QuestionController(wc);
	LanguageController lc = new LanguageController(wc);
	OptionController oc = new OptionController(wc);
	
	@Before
	public void setUp() throws InterruptedException {
		mc.ModuleButtons();
		lc.LanguageButtons();
		qc.QuestionButtons();
	}
	
	@Test
	public void testNewOption() throws AWTException, InterruptedException {
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
		Window.txtChoice.setText("yes");
		Window.textArea_1.setText("okay");
		Window.comboBox.setSelectedIndex(1);
		click(1500, 890);
		assertEquals("yes", Window.modelo.getElementAt(0).getChoice().get(0));
	}
	
	@Test
	public void testNewOptionList() throws AWTException, InterruptedException {
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
		Window.txtChoice.setText("yes");
		Window.textArea_1.setText("okay");
		Window.comboBox.setSelectedIndex(1);
		click(1500, 890);
		click(980, 655);
		assertEquals("yes", wc.curopt.getChoice().get(0));
	}
	
	@Test
	public void testNewOptionListEdit() throws AWTException, InterruptedException {
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
		Window.txtChoice.setText("yes");
		Window.textArea_1.setText("okay");
		Window.comboBox.setSelectedIndex(1);
		click(1500, 890);
		click(980, 655);
		Window.txtChoice.setText("no");
		click(1250, 890);
		assertEquals("no", wc.curopt.getChoice().get(0));
	}
	
	@Test
	public void testNewOptionListRemove() throws AWTException, InterruptedException {
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
		Window.txtChoice.setText("yes");
		Window.textArea_1.setText("okay");
		Window.comboBox.setSelectedIndex(1);
		click(1500, 890);
		click(980, 655);
		Window.txtChoice.setText("no");
		click(980, 890);
		assertTrue(wc.curopt == null);
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
