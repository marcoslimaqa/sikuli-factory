package io.github.marcoslimaqa.sikulifactory.samples.sampleapp.pages;

import org.sikuli.script.Screen;

import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;

public class StartMenuPage {

	Screen sikuli;
	
	@FindBy(image="start-game-button.png")
	private SikuliElement startGameButton;
	
	public StartMenuPage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}

	public GamePlayPage startGame() {
		startGameButton.wait(20);
		startGameButton.click();
		return new GamePlayPage(sikuli);
	}
	
}
