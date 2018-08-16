package io.github.marcoslimaqa.sikulifactory.samples.sampleapp.pages;

import org.sikuli.script.Screen;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;

public class GamePlayPage {

	Screen sikuli;
	
	@FindBy(image="honey.png")
	private SikuliElement honey;
	
	@FindBy(image="exit-button.png")
	private SikuliElement exitButton;
	
	@FindBy(image="congratulations.png")
	private SikuliElement congratulations;
	
	public GamePlayPage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}

	public void play() {
		honey.wait(5);
		while (!exitButton.exists(0)) {
			honey.click();
		}
	}

	public boolean hasThePlayerWon() {
		return congratulations.exists(0) ? true : false;
	}
	
}
