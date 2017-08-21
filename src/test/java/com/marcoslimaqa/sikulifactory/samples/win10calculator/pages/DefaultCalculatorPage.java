package com.marcoslimaqa.sikulifactory.samples.win10calculator.pages;

import org.sikuli.script.Screen;

import com.marcoslimaqa.sikulifactory.FindBy;
import com.marcoslimaqa.sikulifactory.SikuliElement;
import com.marcoslimaqa.sikulifactory.SikuliFactory;

public class DefaultCalculatorPage {

	Screen sikuli;
	
	@FindBy(image="menu.png")
	private SikuliElement menu;
	
	public DefaultCalculatorPage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}

	public MenuPage openMenu() {
		menu.wait(5);
		menu.click();
		return new MenuPage(sikuli);
	}
	
}
