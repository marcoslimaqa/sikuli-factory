package io.github.marcoslimaqa.sikulifactory.samples.win10calculator.pages;

import org.sikuli.script.Screen;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;

public class MenuPage {

	Screen sikuli;
	
	@FindBy(image="scientific.png")
	private SikuliElement scientific;
	
	public MenuPage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}

	public ScientificCalculatorPage setScientific() {
		scientific.wait(5).click();
		return new ScientificCalculatorPage(sikuli);
	}

}
