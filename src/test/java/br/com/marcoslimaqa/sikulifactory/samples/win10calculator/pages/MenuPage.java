package br.com.marcoslimaqa.sikulifactory.samples.win10calculator.pages;

import org.sikuli.script.Screen;

import br.com.marcoslimaqa.sikulifactory.FindBy;
import br.com.marcoslimaqa.sikulifactory.SikuliElement;
import br.com.marcoslimaqa.sikulifactory.SikuliFactory;

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
