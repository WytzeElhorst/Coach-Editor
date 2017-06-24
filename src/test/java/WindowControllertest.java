package test.java;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.GQuestion;
import main.java.Language;
import main.java.LanguageController;
import main.java.Module;
import main.java.ModuleController;
import main.java.Question;
import main.java.QuestionController;
import main.java.Window;
import main.java.WindowController;

public class WindowControllertest {
	Window w = new Window();
	WindowController wc = w.wc;
	
	@Test
	public void testSave() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		File dir = new File(getClass().getResource("/test/assets/testfile.txt").getPath());
		URL url = getClass().getResource("/test/assets/testfile.txt");
		File file2 = new File(url.getPath());
		wc.load(file2);
		Module mod = wc.modules.get(0);
		wc.fc.setCurrentDirectory(dir);
		click(400, 200);
		click(400, 270);
		click(1100, 670);
		Thread.sleep(2000);
		URL url2 = getClass().getResource("/test/assets/output.txt");
		File file3 = new File(url2.getPath());
		wc.load(file3);
		Module newmod = wc.modules.get(0);
		assertTrue(mod.equals(newmod));
	}
	
	@Test
	public void testLoad() throws AWTException, InterruptedException {
		Window.frmGui.setVisible(true);
		File dir = new File(getClass().getResource("/test/assets/testfile.txt").getPath());
		URL url = getClass().getResource("/test/assets/testfile.txt");
		File file2 = new File(url.getPath());
		wc.load(file2);
		Module mod = wc.modules.get(0);
		wc.fc.setCurrentDirectory(dir);
		click(400, 200);
		click(400, 250);
		click(800, 470);
		click(1100, 670);
		Module newmod = wc.modules.get(0);
		assertTrue(mod.equals(newmod));
	}

	public static void click(int x, int y) throws AWTException, InterruptedException{
		Robot bot = new Robot();
		bot.mouseMove(x, y);
		Thread.sleep(200);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(200);
	}
}
