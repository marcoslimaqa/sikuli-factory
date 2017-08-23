package io.github.marcoslimaqa.sikulifactory;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;

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
	private Pattern target;
	
	public SikuliElement(Screen sikuli, String image, String[] images, float similarity0to100, int x, int y) {
		super();
		this.sikuli = sikuli;
		this.image = image;
		this.images = images;
		this.similarity0to100 = similarity0to100;
		this.x = x;
		this.y = y;
		this.target = new Pattern(image).similar(similarity0to100/100).targetOffset(x,y);
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
		setImageIfUrlsIsSet(this);
		try {
			return sikuli.click(target);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int doubleClick() {
		setImageIfUrlsIsSet(this);
		try {
			return sikuli.doubleClick(target);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int dragDrop(SikuliElement sikuliElement) {
		setImageIfUrlsIsSet(this);
		setImageIfUrlsIsSet(sikuliElement);
		Pattern toDrop = new Pattern(sikuliElement.getImage()).similar(sikuliElement.getSimilarity0to100()/100).targetOffset(sikuliElement.getX(),sikuliElement.getY());
		try {
			return sikuli.dragDrop(target, toDrop);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int click(SikuliElement sikuliElement) {
		setImageIfUrlsIsSet(this);
		setImageIfUrlsIsSet(sikuliElement);
		Pattern toClick = new Pattern(sikuliElement.getImage()).similar(sikuliElement.getSimilarity0to100()/100).targetOffset(sikuliElement.getX(),sikuliElement.getY());
		try {
			return sikuli.find(target).click(toClick);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean exists(int timeOutInSeconds) {
		try {
			setImageIfUrlsIsSet(this, timeOutInSeconds);
		} catch (Exception e) {
			return false;
		}
		Match imageMatch = sikuli.exists(new Pattern(image).similar(similarity0to100/100), timeOutInSeconds);
        boolean imageExists = imageMatch != null;
        return imageExists;
	}
	
	public Match wait(int timeOutInSeconds){
		setImageIfUrlsIsSet(this, timeOutInSeconds);
		try {
			return sikuli.wait(new Pattern(image).similar(similarity0to100/100), timeOutInSeconds);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}
	
	public int type(String text) {
		return sikuli.type(text);
	}
	
	public int type(String text, String modifiers) {
		return sikuli.type(text, modifiers);
	}
	
	public int paste(String text) {
		return sikuli.paste(text);
	}
	
	public String getText() {
		clearClipboard();
		click();
		type("a", Key.CTRL);
		type("c", Key.CTRL);
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
	
	private void setImageIfUrlsIsSet(SikuliElement sikuliElement, int timeOutInSeconds) {
		if (sikuliElement.getImages().length > 1) {
			boolean imageFound = false;
			long timeoutExpiredMs = System.currentTimeMillis() + timeOutInSeconds * 1000;
			while (!imageFound) {
				for (int i = 0; i < sikuliElement.getImages().length; i++) {
					imageFound = sikuli.exists(new Pattern(sikuliElement.getImages()[i]).similar(sikuliElement.getSimilarity0to100()/100), 0) != null;
					if (imageFound) {
						setImage(sikuliElement.getImages()[i]);
						return;
					}
				}
				long waitMs = timeoutExpiredMs - System.currentTimeMillis();
			    if (waitMs <= 0) {
			        break;
			     }
			}
			if (!imageFound) {
				throw new RuntimeException("Images not found: " + Arrays.toString(sikuliElement.getImages()));
			}
		}
	}
    
}
