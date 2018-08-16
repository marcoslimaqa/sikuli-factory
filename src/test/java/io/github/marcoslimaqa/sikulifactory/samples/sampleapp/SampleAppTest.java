package io.github.marcoslimaqa.sikulifactory.samples.sampleapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;

import io.github.marcoslimaqa.sikulifactory.samples.sampleapp.pages.GamePlayPage;
import io.github.marcoslimaqa.sikulifactory.samples.sampleapp.pages.StartMenuPage;

public class SampleAppTest {
	
	Screen sikuli;
	App sikuliApp;
	
	@Before
	public void before() {
        sikuli = new Screen();
        ImagePath.add("src/test/resources/sikuli-images/sample-app.sikuli");
        Settings.MoveMouseDelay = 0;
		sikuliApp = App.open("java -jar " + System.getProperty("user.dir") + "/sample-app/BeeKeeper.jar");
	}
	
	@Test
	public void playTheGame() {
		StartMenuPage startMenuPage = new StartMenuPage(sikuli);
		GamePlayPage gamePlayPage = startMenuPage.startGame();
		gamePlayPage.play();
		Assert.assertTrue("Game Over! Better luck next time.", gamePlayPage.hasThePlayerWon());
	}
	
	@After
	public void after() {
		sikuliApp.close();
	}
	
}
