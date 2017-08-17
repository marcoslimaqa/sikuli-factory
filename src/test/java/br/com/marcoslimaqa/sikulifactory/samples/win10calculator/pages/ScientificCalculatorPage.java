package br.com.marcoslimaqa.sikulifactory.samples.win10calculator.pages;

import org.sikuli.script.Screen;

import br.com.marcoslimaqa.sikulifactory.FindBy;
import br.com.marcoslimaqa.sikulifactory.SikuliElement;
import br.com.marcoslimaqa.sikulifactory.SikuliFactory;

public class ScientificCalculatorPage {

	Screen sikuli;
	@FindBy(image = "sin-button.png", similarity = 90)
	private SikuliElement sinButton;

	@FindBy(image = "cos-button.png", similarity = 90)
	private SikuliElement cosButton;

	public ScientificCalculatorPage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}

	public boolean isScientificButtonsPresent() {
		boolean isScientificButtonsPresent = true;
		isScientificButtonsPresent = sinButton.exists(5) ? isScientificButtonsPresent : false;
		isScientificButtonsPresent = cosButton.exists(5) ? isScientificButtonsPresent : false;
		return isScientificButtonsPresent;
	}
}
