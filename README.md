# SikuliFactory

A based [PageFactory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory) model for [SikuliX](http://sikulix.com/).

## Download and Install

Download the following JAR and add them to your classpath:
* [sikulifactory.jar](http://search.maven.org/#search%7Cga%7C1%7Csikulifactory)

### Maven Users
Add the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>io.github.marcoslimaqa</groupId>
    <artifactId>sikulifactory</artifactId>
    <version>1.1.0</version>
</dependency>
```

## A Simple Example

Automation Test of Windows 10 Calculator, see [example](src/test/java/io/github/marcoslimaqa/sikulifactory/samples/win10calculator):

```java
package io.github.marcoslimaqa.sikulifactory.samples.win10calculator.pages;

import org.sikuli.script.Screen;
import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;

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

First declare some SikuliElement fields in your [PageObject](https://github.com/SeleniumHQ/selenium/wiki/PageObjects), using annotation @FindBy with an image parameter, for example:

```java
@FindBy(image = "menu.png")
private SikuliElement menu;
```

You can also indicate a similarity:

```java
@FindBy(image = "menu.png", similarity = 85)
private SikuliElement menu;
```

Or coordinates:

```java
@FindBy(image = "menu.png", similarity = 85, x = 10, y = 25)
private SikuliElement menu;
```

Or with more images:

```java
@FindBy(images = {"menu-windows10.png", "menu-windows8.png"}, similarity = 85, x = 10, y = 25)
private SikuliElement menu;
```

If you prefer, you can use annotation @FindByImage or @FindByImages instead of @FindBy. See the examples below:

```java
@FindByImage("menu.png")
private SikuliElement menu;
```

```java
@FindByImages({"menu-windows10.png","menu-windows8.png"})
private SikuliElement menu;
```

However, if you need to indicate similarity or coordinates, you need to add the value parameter to the image:

```java
@FindByImage(value = "menu-windows10.png", similarity = 85, x = 10, y = 25)
private SikuliElement menu;
```

### Step 2
Initialize the PageObject:

```java
SikuliFactory.initElements(sikuli, this);
```

### Step 3
Finally, perform some SikuliX action, similar to Selenium:

```java
menu.click();
```

## Explanation

One of the goals is to make SikuliX more familiar to anyone who already works with Selenium and PageFactory. In addition, reduce the amount of written code and make it cleaner.
In the example above, using SikuliFactory looks like this:

```java
menu.click();
```

In SikuliX, is equivalent to:

```java
sikuli.click(new Pattern("menu.png").similar(0.85F).targetOffset(10,25));
```

The FindBy parameter "images" can be used when a test will run on different operating systems or resolution for example. In this case, SikuliFactory will use the first image that is found. If none of the listed images are found, an exception will be thrown.

The initElements method instantiates the SikuliElements, without it, your code would throw NullPointerException.

## Samples

[win10calculator](src/test/java/io/github/marcoslimaqa/sikulifactory/samples/win10calculator)

## Author

* **Marcos Lima** - [marcoslimaqa@gmail.com](mailto:marcoslimaqa@gmail.com)