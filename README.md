# SikuliFactory

A based [PageFactory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory) model for [SikuliX](http://sikulix.com/).

## A Simple Example

Windows 10 calculator [example](src/test/java/br/com/marcoslimaqa/sikulifactory/samples/win10calculator):

```
package br.com.marcoslimaqa.sikulifactory.samples.win10calculator.pages;

import org.sikuli.script.Screen;
import br.com.marcoslimaqa.sikulifactory.FindBy;
import br.com.marcoslimaqa.sikulifactory.SikuliElement;
import br.com.marcoslimaqa.sikulifactory.SikuliFactory;

public class DefaultCalculatorPage {
	Screen sikuli;
	
	@FindBy(image = "menu.png")
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
```

### Step 1

First declare some SikuliElement fields in your [PageObject](https://github.com/SeleniumHQ/selenium/wiki/PageObjects), using the annotation @FindBy with an image parameter, for example:

```
@FindBy(image = "menu.png")
private SikuliElement menu;
```

You can also indicate a similarity:

```
@FindBy(image = "menu.png", similarity = 85)
private SikuliElement menu;
```

Or coordinates:

```
@FindBy(image = "menu.png", similarity = 85, x = 10, y = 25)
private SikuliElement menu;
```

Or with more images:

```
@FindBy(images={"menu-windows10.png","menu-windows8.png"}, similarity = 85, x = 10, y = 25)
private SikuliElement menu;
```

### Step 2
Initialize the PageObject:

```
SikuliFactory.initElements(sikuli, this);
```

### Step 3
Finally, perform some SikuliX action, similar to Selenium:

```
menu.click();
```

##Explanation

One of the goals is to make SikuliX more familiar to anyone who already works with Selenium and PageFactory. In addition, reduce the amount of written code and make it cleaner.
In the example above, using SikuliFactory looks like this:

```
menu.click();
```

In SikuliX, is equivalent to:

```
sikuli.click(new Pattern("menu.png").similar(0.85F).targetOffset(10,25));
```

*The FindBy parameter "images" can be used when a test will run on different operating systems or resolution for example. In this case, SikuliFactory will use the first image that is found. If none of the listed images are found, an exception will be thrown.
*The initElements method instantiates the SikuliElements, without it, your code would throw NullPointerException.

## Samples

[win10calculator](src/test/java/br/com/marcoslimaqa/sikulifactory/samples/win10calculator)

## Author

* **Marcos Lima** - [marcoslimaqa@gmail.com](mailto:marcoslimaqa@gmail.com)
