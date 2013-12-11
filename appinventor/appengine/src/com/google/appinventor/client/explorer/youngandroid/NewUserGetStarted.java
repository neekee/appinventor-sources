package com.google.appinventor.client.explorer.youngandroid;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.google.appinventor.client.Ode;

import java.util.List;
import java.util.ArrayList;


public class NewUserGetStarted {

  public static class Tutorial {
	ArrayList<TutorialSlide> slides = new ArrayList<TutorialSlide>();
	int currentMessageIndex = 0;

	public Tutorial() {}

	public void addSlide(TutorialSlide slide) {
	  slides.add(slide);
	  slide.setTutorial(this);
	}

	public void nextSlide() {
	  if (currentMessageIndex < this.slides.size() - 1) {
		slides.get(currentMessageIndex).hide();
		currentMessageIndex += 1;
		if (currentMessageIndex == 5) {
		  Ode.getInstance().getDesignToolbar().switchToBlocksEditor();
		}
		slides.get(currentMessageIndex).show();
	  }
	}

	public void lastSlide() {
	  if (currentMessageIndex > 0) {
		slides.get(currentMessageIndex).hide();
		currentMessageIndex -= 1;
		if (currentMessageIndex == 4) {
		  Ode.getInstance().getDesignToolbar().switchToFormEditor();
		}
		slides.get(currentMessageIndex).show();
	  }
	}

	public void exit() {
	  slides.get(currentMessageIndex).hide();
	}

  }

  public static class TutorialSlide extends DialogBox {
  	private ArrayList<Image> images = new ArrayList<Image>();
  	private Image background;
  	private Image continueButton;
  	private Image backButton;
  	private Image exitButton;
  	AbsolutePanel holder = new AbsolutePanel();
  	Tutorial tutorial;

  	public void setTutorial(Tutorial t) {
  	  tutorial = t;
  	}

  	public void setBackgroundImage(Image background) {
  	  this.background = background;
  	  this.holder.add(this.background);
  	}

  	public void setContinueButton(Image button, int x, int y, boolean last) {
  	  this.continueButton = button;
  	  holder.add(this.continueButton);
      if (last) {
        this.continueButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
              tutorial.exit();
          }
        });
      } else {
    	  this.continueButton.addClickListener(new ClickListener() {
    	    public void onClick(Widget sender) {
              tutorial.nextSlide();
    	    }
    	  });
      }
  	  //TODO: do i need this?
  	  holder.setWidgetPosition(this.continueButton, x, y);
  	}

  	public void setBackButton(Image button, int x, int y) {
  	  this.backButton = button;
  	  holder.add(this.backButton);
  	  this.backButton.addClickListener(new ClickListener() {
  	    public void onClick(Widget sender) {
  	      tutorial.lastSlide();
  	    }
  	  });
  	  holder.setWidgetPosition(this.backButton, x, y);
  	}  

  	public void setExitButton(Image button, int x, int y) {
  	  this.exitButton = button;
  	  holder.add(this.exitButton);
  	  this.exitButton.addClickListener(new ClickListener() {
  		public void onClick(Widget sender) {
  		  tutorial.exit();
  		}
  	  });
  	  holder.setWidgetPosition(this.exitButton, x, y);
  	}

  	public void addImage(Image newImage, int x, int y) {
  	  this.images.add(newImage);
  	  this.holder.add(newImage);
  	  holder.setWidgetPosition(newImage, x, y);
  	}

  	public void ready() {
      this.setGlassEnabled(false);
      this.setModal(false);
      this.setAutoHideEnabled(false);
  	  this.setWidget(holder);
      this.setStylePrimaryName("ode-DialogBox-popup");
      this.setAnimationEnabled(true);
    }
  }

  public static class FullWindowTutorialSlide extends TutorialSlide {
  	private int browserHeight = Window.getClientHeight();
  	private int browserWidth = Window.getClientWidth();

  	public void setBackgroundImage(Image background) {
  	  background.setPixelSize(this.browserWidth, this.browserHeight);
  	  super.setBackgroundImage(background);
  	}

    public void ready() {
    
      super.ready();

      Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
      exitButton.setPixelSize(40, 40);
      this.setExitButton(exitButton, this.browserWidth - 40, this.browserHeight);

      Image continueButton = new Image("images/getStarted/Components/NextButton.png");
      continueButton.setPixelSize(190, 96);
      this.setContinueButton(continueButton, this.browserWidth - 230, this.browserHeight - 125, false);

      Image backButton = new Image("images/getStarted/Components/BackButton.png");
      backButton.setPixelSize(190, 96);
      this.setBackButton(backButton, this.browserWidth - 450, this.browserHeight - 125);

    }
  }

  public static class FrameTutorialSlide extends TutorialSlide {
    private int browserHeight = Window.getClientHeight();
    private int browserWidth = Window.getClientWidth();

    public void setBackgroundImage(Image background) {
      background.setPixelSize(this.browserWidth-20, 410);
      super.setBackgroundImage(background);
    }

    public void ready() {
    
      super.ready();

      Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
      exitButton.setPixelSize(40, 40);
      this.setExitButton(exitButton, this.browserWidth - 60, 240);

      Image continueButton = new Image("images/getStarted/Components/NextButton.png");
      continueButton.setPixelSize(190, 96);
      this.setContinueButton(continueButton, this.browserWidth - 250, 280, false);

      Image backButton = new Image("images/getStarted/Components/BackButton.png");
      backButton.setPixelSize(190, 96);
      this.setBackButton(backButton, this.browserWidth - 470, 280);

      this.setStylePrimaryName("ode-DialogBox-getStarted");
    }
  }

  public static class PopupTutorialSlide extends TutorialSlide {
    private ToggleButton hintButton;
    private Image hintImage;
    private DialogBox hintPopup;
    private Image continueButton;

    public DialogBox createHint(int width, int height, int left, int top) {
      DialogBox hint = new DialogBox();
      hint.setStylePrimaryName("ode-DialogBox-getStarted");
      AbsolutePanel hintHolder = new AbsolutePanel();
      this.hintImage.setPixelSize(width, height);
      hintHolder.add(hintImage);
      hint.setGlassEnabled(false);
      hint.setModal(false);
      hint.setAutoHideEnabled(false);
      hint.setWidget(hintHolder);
      hint.setAnimationEnabled(true);
      hint.setPopupPosition(left, top);
      return hint;
    }

    public void addHintButton(Image hintImage, int width, int height, int left, int top) {
      Image showHint = new Image("images/getStarted/Components/ShowHintButton.png");
      showHint.setPixelSize(90, 30);
      Image hideHint = new Image("images/getStarted/Components/HideHintButton.png");
      hideHint.setPixelSize(90, 30);

      final ToggleButton hintButton = new ToggleButton(showHint, hideHint);
      hintButton.setStylePrimaryName("toggle-button");

      this.hintImage = hintImage;
      final DialogBox hint = createHint(width, height, left, top);
      hintPopup = hint;

      hintButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          if (hintButton.isDown()) {
            hint.show();
          } else {
            hint.hide();
          }

        }
      });

      this.hintButton = hintButton;
      this.holder.add(this.hintButton);
      this.holder.setWidgetPosition(this.hintButton, 35, 520);
    }

    public void setContinueButton(Image button, int x, int y) {
      this.continueButton = button;
      holder.add(this.continueButton);
      this.continueButton.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
            tutorial.nextSlide();
            hintPopup.hide();
        }
      });
      holder.setWidgetPosition(this.continueButton, x, y);
    }

    public void ready() {
    
      super.ready();

      Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
      exitButton.setPixelSize(30, 30);
      this.setExitButton(exitButton, 220, 0);

      Image continueButton = new Image("images/getStarted/Components/NextButton.png");
      continueButton.setPixelSize(80, 40);
      setContinueButton(continueButton, 150, 595);

      Image backButton = new Image("images/getStarted/Components/BackButton.png");
      backButton.setPixelSize(80, 40);
      this.setBackButton(backButton, 20, 595);

      this.setStylePrimaryName("ode-DialogBox-popup");
    }
  }

  // Pull out and start own class here, ideally.
  // TODO: is this still used?
  public static int currentMessageIndex;

  /**
   * Creates, visually centers, and optionally displays the dialog box
   * that informs the user how to start learning about using App Inventor
   * @param showDialog Convenience variable to show the created DialogBox.
   * @return The created and optionally displayed Dialog box.
   */
  public static Tutorial createStarterDialog(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final Tutorial tutorial = new Tutorial();
    final TutorialSlide firstSlide= new TutorialSlide(); // DialogBox(autohide, modal)
    firstSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    firstSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen2Popup.png");
    backgroundImage.setPixelSize(835, 470);
    firstSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    firstSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    firstSlide.setContinueButton(continueButton, 625, 370, false);

    firstSlide.ready();
    tutorial.addSlide(firstSlide);

    addDesignTutorialSlides(tutorial);


    firstSlide.show();
    return tutorial;
  }

  public static void addDesignTutorialSlides(Tutorial tutorial) {
    tutorial.addSlide(beginDesignTutorial(true));
    tutorial.addSlide(continueDesignTutorial(true));
    tutorial.addSlide(beginDesignPopup(true));
    tutorial.addSlide(beginDesignPopup2(true));
    tutorial.addSlide(beginProgramTutorial(true));
    tutorial.addSlide(programOverlay(true));
    tutorial.addSlide(beginProgramPopup(true));
    tutorial.addSlide(beginProgramPopup2(true));
    tutorial.addSlide(beginProgramPopup3(true));
    tutorial.addSlide(beginTestSlides(true));
    tutorial.addSlide(testSlide1(true));
    tutorial.addSlide(testSlide2(true));
    tutorial.addSlide(testSlide3(true));
    tutorial.addSlide(testSlide4(true));
    tutorial.addSlide(beginShareSlides(true));
    tutorial.addSlide(shareSlide1(true));
    tutorial.addSlide(lastSlide(true));
  }

  public static TutorialSlide beginDesignTutorial(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide designSlide = new FrameTutorialSlide(); // DialogBox(autohide, modal)
    //designSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    //designSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen3Frame.png");
    designSlide.setBackgroundImage(backgroundImage);

    designSlide.ready();
    //TODO: ask about this; what does it do and should we have it in all slides?
    designSlide.setPopupPosition(0, Window.getClientHeight() - 410);

    return designSlide;
  }

  public static TutorialSlide continueDesignTutorial(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide designSlide = new FullWindowTutorialSlide(); // DialogBox(autohide, modal)
    designSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    designSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen4Overlay.png");
    designSlide.setBackgroundImage(backgroundImage);

    designSlide.ready();

    return designSlide;
  }


  //TODO: I actually should probably define a TutorialSlide subclass for popups.
  public static PopupTutorialSlide beginDesignPopup(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final PopupTutorialSlide designPopup = new PopupTutorialSlide(); // DialogBox(autohide, modal)
    designPopup.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    designPopup.setAnimationEnabled(true);

    int browserWidth=Window.getClientWidth();
    int browserHeight=Window.getClientHeight();

    Image backgroundImage = new Image("images/getStarted/Components/0BlankSideMenu.png");
    backgroundImage.setPixelSize(250, 650);
    designPopup.setBackgroundImage(backgroundImage);

    Image designHeader = new Image("images/getStarted/Components/1DesignerSideMenuHeader.png");
    designHeader.setPixelSize(200, 55);
    designPopup.addImage(designHeader, 10, 0);

    Image designHighlight = new Image("images/getStarted/Components/1DesignerStep1Highlight.png");
    designHighlight.setPixelSize(200, 135);
    designPopup.addImage(designHighlight, 0, 65);

    Image designText = new Image("images/getStarted/Components/1DesignerSideMenuText.png");
    designText.setPixelSize(200, 540);
    designPopup.addImage(designText, 0, 65);

    Image checkBox = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox.setPixelSize(40, 40);
    designPopup.addImage(checkBox, 200, 110);

    Image hintImage = new Image("images/getStarted/Components/1DesignerStep1Hint.png");
    designPopup.addHintButton(hintImage, 400, 250, 100, 320);

    designPopup.ready();

    designPopup.setPopupPosition(browserWidth - 275, 0);

    return designPopup;
  }

  public static TutorialSlide beginDesignPopup2(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide designPopup = new PopupTutorialSlide(); // DialogBox(autohide, modal)
    designPopup.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    designPopup.setAnimationEnabled(true);

    int browserWidth=Window.getClientWidth();
    int browserHeight=Window.getClientHeight();

    Image backgroundImage = new Image("images/getStarted/Components/0BlankSideMenu.png");
    backgroundImage.setPixelSize(250, 650);
    designPopup.setBackgroundImage(backgroundImage);

    Image designHeader = new Image("images/getStarted/Components/1DesignerSideMenuHeader.png");
    designHeader.setPixelSize(200, 55);
    designPopup.addImage(designHeader, 10, 0);

    Image designHighlight = new Image("images/getStarted/Components/1DesignerStep1Highlight.png");
    designHighlight.setPixelSize(200, 135);
    designPopup.addImage(designHighlight, 0, 65);

    Image designText = new Image("images/getStarted/Components/1DesignerSideMenuText.png");
    designText.setPixelSize(200, 540);
    designPopup.addImage(designText, 0, 65);

    Image checkBox = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox.setPixelSize(40, 40);
    designPopup.addImage(checkBox, 200, 110);

    Image check = new Image("images/getStarted/Components/0SideMenuCheck.png");
    check.setPixelSize(50, 43);
    designPopup.addImage(check, 195, 105);

    designPopup.ready();

    designPopup.setPopupPosition(browserWidth - 275, 0);

    return designPopup;
  }


  public static TutorialSlide beginProgramTutorial(boolean showDialog) {
    // Create the UI elements of the DialogBox

    final TutorialSlide programSlide = new FrameTutorialSlide(); // DialogBox(autohide, modal)
    programSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    programSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen8Frame.png");
    programSlide.setBackgroundImage(backgroundImage);

    programSlide.ready();

    programSlide.setPopupPosition(0, Window.getClientHeight() - 410);

    return programSlide;
  }


  public static TutorialSlide programOverlay(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide programSlide = new FullWindowTutorialSlide(); // DialogBox(autohide, modal)
    programSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    programSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen9Overlay.png");
    programSlide.setBackgroundImage(backgroundImage);

    programSlide.ready();

    return programSlide;
  }  

  public static PopupTutorialSlide beginProgramPopup(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final PopupTutorialSlide programPopup = new PopupTutorialSlide(); // DialogBox(autohide, modal)
    programPopup.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    programPopup.setAnimationEnabled(true);

    int browserWidth=Window.getClientWidth();
    int browserHeight=Window.getClientHeight();

    Image backgroundImage = new Image("images/getStarted/Components/0BlankSideMenu.png");
    backgroundImage.setPixelSize(250, 650);
    programPopup.setBackgroundImage(backgroundImage);

    Image programHeader = new Image("images/getStarted/Components/1ProgramSideMenuHeader.png");
    programHeader.setPixelSize(200, 55);
    programPopup.addImage(programHeader, 10, 0);

    Image designHighlight = new Image("images/getStarted/Components/1ProgramStep1Highlight.png");
    designHighlight.setPixelSize(200, 210);
    programPopup.addImage(designHighlight, 0, 55);

    Image designText = new Image("images/getStarted/Components/1ProgramSideMenuText.png");
    designText.setPixelSize(200, 530);
    programPopup.addImage(designText, 0, 65);

    Image checkBox = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox.setPixelSize(40, 40);
    programPopup.addImage(checkBox, 200, 110);

    Image checkBox2 = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox2.setPixelSize(40, 40);
    programPopup.addImage(checkBox2, 200, 250);

    Image hintImage = new Image("images/getStarted/Components/1ProgramStep1Hint.png");
    programPopup.addHintButton(hintImage, 350, 150, 100, 300);

    programPopup.ready();

    programPopup.setPopupPosition(browserWidth - 275, 0);

    return programPopup;
  }

   public static PopupTutorialSlide beginProgramPopup2(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final PopupTutorialSlide programPopup = new PopupTutorialSlide(); // DialogBox(autohide, modal)
    programPopup.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    programPopup.setAnimationEnabled(true);

    int browserWidth=Window.getClientWidth();
    int browserHeight=Window.getClientHeight();

    Image backgroundImage = new Image("images/getStarted/Components/0BlankSideMenu.png");
    backgroundImage.setPixelSize(250, 650);
    programPopup.setBackgroundImage(backgroundImage);

    Image programHeader = new Image("images/getStarted/Components/1ProgramSideMenuHeader.png");
    programHeader.setPixelSize(200, 55);
    programPopup.addImage(programHeader, 10, 0);

    Image designHighlight = new Image("images/getStarted/Components/1ProgramStep1Highlight.png");
    designHighlight.setPixelSize(205, 350);
    programPopup.addImage(designHighlight, 0, 180);

    Image designText = new Image("images/getStarted/Components/1ProgramSideMenuText.png");
    designText.setPixelSize(200, 530);
    programPopup.addImage(designText, 0, 65);

    Image checkBox = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox.setPixelSize(40, 40);
    programPopup.addImage(checkBox, 200, 110);

    Image check = new Image("images/getStarted/Components/0SideMenuCheck.png");
    check.setPixelSize(50, 43);
    programPopup.addImage(check, 195, 105);

    Image checkBox2 = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox2.setPixelSize(40, 40);
    programPopup.addImage(checkBox2, 200, 250);

    Image hintImage = new Image("images/getStarted/Components/1ProgramStep2Hint.png");
    programPopup.addHintButton(hintImage, 325, 135, 100, 200);

    programPopup.ready();

    programPopup.setPopupPosition(browserWidth - 275, 0);

    return programPopup;
  }

  public static TutorialSlide beginProgramPopup3(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide programPopup = new PopupTutorialSlide(); // DialogBox(autohide, modal)
    programPopup.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    programPopup.setAnimationEnabled(true);

    int browserWidth=Window.getClientWidth();
    int browserHeight=Window.getClientHeight();

    Image backgroundImage = new Image("images/getStarted/Components/0BlankSideMenu.png");
    backgroundImage.setPixelSize(250, 650);
    programPopup.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    programPopup.setExitButton(exitButton, 220, 0);

    Image programHeader = new Image("images/getStarted/Components/1ProgramSideMenuHeader.png");
    programHeader.setPixelSize(200, 55);
    programPopup.addImage(programHeader, 10, 0);

    Image designText = new Image("images/getStarted/Components/1ProgramSideMenuText.png");
    designText.setPixelSize(200, 530);
    programPopup.addImage(designText, 0, 65);

    Image checkBox = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox.setPixelSize(40, 40);
    programPopup.addImage(checkBox, 200, 110);

    Image check = new Image("images/getStarted/Components/0SideMenuCheck.png");
    check.setPixelSize(50, 43);
    programPopup.addImage(check, 195, 105);

    Image checkBox2 = new Image("images/getStarted/Components/0SideMenuCheckBox.png");
    checkBox2.setPixelSize(40, 40);
    programPopup.addImage(checkBox2, 200, 250);

    Image check2 = new Image("images/getStarted/Components/0SideMenuCheck.png");
    check2.setPixelSize(50, 43);
    programPopup.addImage(check2, 195, 245);

    programPopup.ready();

    programPopup.setPopupPosition(browserWidth - 275, 0);

    return programPopup;
  }


  public static TutorialSlide beginTestSlides(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide testSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    testSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    testSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen14Popup.png");
    backgroundImage.setPixelSize(835, 470);
    testSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    testSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    testSlide.setContinueButton(continueButton, 625, 370, false);

    Image backButton = new Image("images/getStarted/Components/BackButton.png");
    backButton.setPixelSize(190, 96);
    testSlide.setBackButton(backButton, 400, 370);

    testSlide.ready();

    return testSlide;
  }

  public static TutorialSlide testSlide1(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide testSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    testSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    testSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen15Popup.png");
    backgroundImage.setPixelSize(835, 470);
    testSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    testSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    testSlide.setContinueButton(continueButton, 625, 370, false);

    Image backButton = new Image("images/getStarted/Components/BackButton.png");
    backButton.setPixelSize(190, 96);
    testSlide.setBackButton(backButton, 400, 370);

    testSlide.ready();

    return testSlide;
  }

  public static TutorialSlide testSlide2(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide testSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    testSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    testSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen16Popup.png");
    backgroundImage.setPixelSize(835, 470);
    testSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    testSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    testSlide.setContinueButton(continueButton, 625, 370, false);

    Image backButton = new Image("images/getStarted/Components/BackButton.png");
    backButton.setPixelSize(190, 96);
    testSlide.setBackButton(backButton, 400, 370);

    testSlide.ready();

    return testSlide;
  }

  public static TutorialSlide testSlide3(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide testSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    testSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    testSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen17Popup.png");
    backgroundImage.setPixelSize(835, 470);
    testSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    testSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    testSlide.setContinueButton(continueButton, 625, 370, false);

    Image backButton = new Image("images/getStarted/Components/BackButton.png");
    backButton.setPixelSize(190, 96);
    testSlide.setBackButton(backButton, 400, 370);

    testSlide.ready();

    return testSlide;
  }

  public static TutorialSlide testSlide4(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide testSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    testSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    testSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen18Popup.png");
    backgroundImage.setPixelSize(835, 470);
    testSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    testSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    testSlide.setContinueButton(continueButton, 625, 370, false);

    Image backButton = new Image("images/getStarted/Components/BackButton.png");
    backButton.setPixelSize(190, 96);
    testSlide.setBackButton(backButton, 400, 370);

    testSlide.ready();

    return testSlide;
  }

  public static TutorialSlide beginShareSlides(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide shareSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    shareSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    shareSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen19Popup.png");
    backgroundImage.setPixelSize(835, 470);
    shareSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    shareSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    shareSlide.setContinueButton(continueButton, 625, 370, false);

    Image backButton = new Image("images/getStarted/Components/BackButton.png");
    backButton.setPixelSize(190, 96);
    shareSlide.setBackButton(backButton, 400, 370);

    shareSlide.ready();

    return shareSlide;
  }

  public static TutorialSlide shareSlide1(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide shareSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    shareSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    shareSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen20Popup.png");
    backgroundImage.setPixelSize(835, 470);
    shareSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    shareSlide.setExitButton(exitButton, 805, 0);

    Image continueButton = new Image("images/getStarted/Components/NextButton.png");
    continueButton.setPixelSize(190, 96);
    shareSlide.setContinueButton(continueButton, 625, 370, false);

    Image backButton = new Image("images/getStarted/Components/BackButton.png");
    backButton.setPixelSize(190, 96);
    shareSlide.setBackButton(backButton, 400, 370);

    shareSlide.ready();

    return shareSlide;
  }

  public static TutorialSlide lastSlide(boolean showDialog) {
    // Create the UI elements of the DialogBox
    final TutorialSlide lastSlide = new TutorialSlide(); // DialogBox(autohide, modal)
    lastSlide.setStylePrimaryName("ode-DialogBox-getStarted");
    //dialogBox.setHeight("400px");
    //dialogBox.setWidth("400px");
    //dialogBox.setGlassEnabled(true);  // was true
    lastSlide.setAnimationEnabled(true);

    Image backgroundImage = new Image("images/getStarted/Screen22Popup.png");
    backgroundImage.setPixelSize(835, 470);
    lastSlide.setBackgroundImage(backgroundImage);

    Image exitButton = new Image("images/getStarted/Components/0RedCloseButton.png");
    exitButton.setPixelSize(30, 30);
    lastSlide.setExitButton(exitButton, 805, 0);

    Image startButton = new Image("images/getStarted/Screen22NewProjectButton.png");
    startButton.setPixelSize(225, 126);
    lastSlide.setContinueButton(startButton, 160, 160, true);

    Image moreButton = new Image("images/getStarted/Screen22TutorialsButton.png");
    moreButton.setPixelSize(225, 126);
    moreButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        Window.open("http://dev-explore.appinventor.mit.edu/ai2/tutorials", "_ai2", "");
      }
    });
    lastSlide.setBackButton(moreButton, 450, 160);

    lastSlide.ready();

    return lastSlide;
  }

}
