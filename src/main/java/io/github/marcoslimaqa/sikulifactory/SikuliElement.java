package io.github.marcoslimaqa.sikulifactory;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliElement {

	private Screen sikuli;
	private String image;
	private String[] images;
	private float similarity0to100;
	private int x;
	private int y;
	
	public SikuliElement(Screen sikuli, String image, String[] images, float similarity0to100, int x, int y) {
		super();
		this.sikuli = sikuli;
		this.image = image;
		this.images = images;
		this.similarity0to100 = similarity0to100;
		this.x = x;
		this.y = y;
	}
	
	public String getImage() {
		return image;
	}
	
	public String[] getImages() {
		return images;
	}

	public float getSimilarity0to100() {
		return similarity0to100;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public int click() {
		try {
			return sikuli.click(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int click(Integer modifiers) {
		try {
			return sikuli.click(createPattern(this), modifiers);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int click(SikuliElement sikuliElement) {
		try {
			return sikuli.find(createPattern(this)).click(createPattern(sikuliElement));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	public int doubleClick() {
		try {
			return sikuli.doubleClick(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doubleClick(Integer modifiers) {
		try {
			return sikuli.doubleClick(createPattern(this), modifiers);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	public int rightClick() {
		try {
			return sikuli.rightClick(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	public int dragDrop(SikuliElement sikuliElement) {
		try {
			return sikuli.dragDrop(createPattern(this), createPattern(sikuliElement));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int drag() {
		try {
			return sikuli.drag(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int dropAt() {
		try {
			return sikuli.dropAt(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int mouseMove() {
		try {
			return sikuli.mouseMove(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int wheel(int direction, int steps) {
		try {
			return sikuli.wheel(createPattern(this), direction, steps);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int wheel(int direction, int steps, int stepDelay) {
		try {
			return sikuli.wheel(createPattern(this), direction, steps , stepDelay);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int hover() {
		try {
			return sikuli.hover(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean exists(){
		return exists((int) sikuli.getAutoWaitTimeout());
	}
	
	public boolean exists(int timeoutInSeconds) {
		Pattern pattern;
		try {
			pattern = createPattern(this, timeoutInSeconds);
		} catch (Exception e) {
			return false;
		}
		Match imageMatch = sikuli.exists(pattern, timeoutInSeconds);
        boolean imageExists = imageMatch != null;
        return imageExists;
	}
	
	public Match waitit(){
		return wait((int) sikuli.getAutoWaitTimeout());
	}
	
	public Match wait(int timeoutInSeconds){
		try {
			return sikuli.wait(createPattern(this, timeoutInSeconds), timeoutInSeconds);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean waitVanish(){
		return waitVanish((int) sikuli.getAutoWaitTimeout());
	}
	
	public boolean waitVanish(int timeoutInSeconds){
		Pattern pattern;
		try {
			pattern = createPattern(this, 0);
		} catch (Exception e) {
			return true;
		}
		boolean imageVanished = sikuli.waitVanish(pattern, timeoutInSeconds);
		if (imageVanished) {
			return true;
		}
		throw new RuntimeException(new FindFailed("Image \"" + image + "\" not vanished after " + timeoutInSeconds + " seconds."));
	}
	
	public Match find() {
		try {
			return sikuli.find(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public Match findBest() {
		return sikuli.findBest(createPattern(this));
	}
	
	public Iterator<Match> findAll() {
		try {
			return sikuli.findAll(createPattern(this));
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public Match[] findAllByRow() {
		return sikuli.findAllByRow(createPattern(this));
	}
	
	public String onAppear() {
		return sikuli.onAppear(createPattern(this));
	}
	
	public String onAppear(Object observer) {
		return sikuli.onAppear(createPattern(this), observer);
	}
	
	public String onVanish(Object observer) {
		return sikuli.onVanish(createPattern(this), observer);
	}
	
	public String onVanish() {
		return sikuli.onVanish(createPattern(this));
	}
	
	public Match[] findAllByColumn() {
		return sikuli.findAllByColumn(createPattern(this));
	}
	
	public int type(String text) {
		try {
			return sikuli.type(createPattern(this), text);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int type(String text, String modifiers) {
		try {
			return sikuli.type(createPattern(this), text, modifiers);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int type(String text, int modifiers) {
		try {
			return sikuli.type(createPattern(this), text, modifiers);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int paste(String text) {
		try {
			return sikuli.paste(createPattern(this), text);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getText() {
		clearClipboard();
		type("a", Key.CTRL);
		sikuli.type("c", Key.CTRL);
		return getClipboard();
	}
	
	private void clearClipboard() {
	    StringSelection selection = new StringSelection("");
	    Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard2.setContents(selection, selection);
	}
	
	private String getClipboard() {
		String clipboardText = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return clipboardText;
	}
	
	private void setImageIfUrlsIsSet(SikuliElement sikuliElement) {
		setImageIfUrlsIsSet(sikuliElement, (int) sikuli.getAutoWaitTimeout());
	}
	
	private void setImageIfUrlsIsSet(SikuliElement sikuliElement, int timeoutInSeconds) {
		if (sikuliElement.getImages().length > 1) {
			boolean imageFound = false;
			long timeoutExpiredMs = System.currentTimeMillis() + timeoutInSeconds * 1000;
			while (!imageFound) {
				for (int i = 0; i < sikuliElement.getImages().length; i++) {
					imageFound = sikuli.exists(new Pattern(sikuliElement.getImages()[i]).similar(sikuliElement.getSimilarity0to100()/100), 0) != null;
					if (imageFound) {
						sikuliElement.setImage(sikuliElement.getImages()[i]);
						return;
					}
				}
				long waitMs = timeoutExpiredMs - System.currentTimeMillis();
			    if (waitMs <= 0) {
			        break;
			     }
			}
			if (!imageFound) {
				throw new RuntimeException(new FindFailed("Images not found: " + Arrays.toString(sikuliElement.getImages())));
			}
		}
	}
	
	private Pattern createPattern(SikuliElement sikuliElement) {
		setImageIfUrlsIsSet(sikuliElement);
		return new Pattern(sikuliElement.getImage()).similar(sikuliElement.getSimilarity0to100()/100).targetOffset(sikuliElement.getX(),sikuliElement.getY());
	}
	
	private Pattern createPattern(SikuliElement sikuliElement, int timeoutInSeconds) {
		setImageIfUrlsIsSet(sikuliElement, timeoutInSeconds);
		return new Pattern(sikuliElement.getImage()).similar(sikuliElement.getSimilarity0to100()/100).targetOffset(sikuliElement.getX(),sikuliElement.getY());
	}
    
}
