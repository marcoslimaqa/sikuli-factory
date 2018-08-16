package io.github.marcoslimaqa.sikulifactory.samples.win10calculator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;

import io.github.marcoslimaqa.sikulifactory.samples.win10calculator.pages.DefaultCalculatorPage;
import io.github.marcoslimaqa.sikulifactory.samples.win10calculator.pages.MenuPage;
import io.github.marcoslimaqa.sikulifactory.samples.win10calculator.pages.ScientificCalculatorPage;

public class Win10CalculatorTest {
	
	Screen sikuli;
	App sikuliApp;
	
	@Before
	public void before() {
        sikuli = new Screen();
        ImagePath.add("src/test/resources/sikuli-images/win-10-calculator.sikuli");
		sikuliApp = App.open("calc.exe");
	}
	
	@Test
	public void verifyScientificButtonsPresent() {
		DefaultCalculatorPage defaultCalculatorPage = new DefaultCalculatorPage(sikuli);
		MenuPage menuPage = defaultCalculatorPage.openMenu();
		ScientificCalculatorPage scientificCalculatorPage = menuPage.setScientific();
		
		Assert.assertTrue("Some element was not found", scientificCalculatorPage.isScientificButtonsPresent());
	}
	
	@After
	public void after() {
		sikuliApp = App.focus("Calc");
		sikuliApp.close();
	}
	
}
