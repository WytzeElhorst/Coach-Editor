package test.java;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
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
import main.java.Window;
import main.java.WindowController;

public class ModuleControllertest {
	WindowController wc = new WindowController();
	ModuleController mc = new ModuleController(wc);
	LanguageController lc = new LanguageController(wc);
	Window w = new Window();
	
	@Before
	public void setUp() throws InterruptedException {
		mc.ModuleButtons();
		lc.LanguageButtons();
	}
	
	@Test
	public void testNewModule() throws AWTException, InterruptedException {
		wc.languages.add(new Language("ned"));
		lc.enableLanguageButtons();
		Window.txtModuleName.setText("new Modul");
		wc.curlang = 0;
		Window.btnNewModule.doClick();
		assertEquals("new Modul", Window.modelm.get(0).getTitle().get(0));
	}
	
	@Test
	public void testRenameModule() throws AWTException, InterruptedException {
		wc.languages.add(new Language("ned"));
		lc.enableLanguageButtons();
		Window.txtModuleName.setText("new Modul");
		wc.curlang = 0;
		Window.btnNewModule.doClick();
		Window.list_m.setSelectedIndex(0);
		wc.curmod = wc.modules.get(0);
		mc.enableModuleButtons();
		Window.txtModuleName.setText("new Module");
		Window.btnRenameModule.doClick();
		assertEquals("new Module", Window.modelm.get(0).getTitle().get(0));
	}
	
	@Test
	public void testRemoveModule() throws AWTException, InterruptedException {
		wc.languages.add(new Language("ned"));
		lc.enableLanguageButtons();
		Window.txtModuleName.setText("new Modul");
		wc.curlang = 0;
		Window.btnNewModule.doClick();
		Window.btnNewModule.doClick();
		Window.list_m.setSelectedIndex(0);
		wc.curmod = wc.modules.get(0);
		mc.enableModuleButtons();
		Window.btnRemoveM.doClick();
		assertEquals(1, wc.modules.size());
	}
	
	@Test
	public void testModuleList() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		wc.languages.add(new Language("ned"));
		lc.enableLanguageButtons();
		Window.txtModuleName.setText("new Modul");
		wc.curlang = 0;
		click(480, 560);
		click(450, 260);
		assertTrue(wc.curmod != null);
	}
	
	public static void click(int x, int y) throws AWTException, InterruptedException{
	    Robot bot = new Robot();
	    bot.mouseMove(x, y);
	    Thread.sleep(250);
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    Thread.sleep(250);
	}
	
}
