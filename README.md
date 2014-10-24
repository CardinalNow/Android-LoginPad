# Login Pad for Android

iOS "like" login pad.  It's pretty simple to incorporate so I thought I'd share.

## Overview

This pad is similar to the iOS7 lock screen in that the pad circles and numbers swap colors as the user presses them.  Once the required length is met for the login pin, the login button animates in next to the pin field.  If the user chooses to delete a number, the login button animiates back out.  Once the user presses the Login button, an attempt to connect is made.  On successful login you'll simply launch your next activity (although there is some code in the main activity to assist you in storing the pin should you choose to do so).  If the login attempt fails, the pin field is cleared and "pushes" the login button back off screen.

![Screen at Launch](https://github.com/CardinalNow/Android-LoginPad/blob/master/img/main_screen.png)

![Screen with pin populated](https://github.com/CardinalNow/Android-LoginPad/blob/master/img/main_screen_with_button.png)

## Bonus Material

This app is utilizing the [AndroidAutowire](https://github.com/CardinalNow/AndroidAutowire) library to wire up the views.  While not necessary, it's a great tool that will reduce your boilerplate setup for Activities, Fragments, and Views with an annotation based approach.

## Usage

Nothing too fancy here.  You can simply pull the resources and Java files out of this project, drop them in to yours, and add the activity to your mainifest.  Everything in the Login Pad is customizable via the XML resources.  To change the colors on the pad, you can either:

  - Modify the values in the colors.xml (bearing in mind all your text and the pin box background is white, so if you only want to change the rim of your circle I recommend adding a color resource to your colors.xml and doing the next option)
  - Modify the login_pad_circle_pressed & login_pad_circle_normal.xml color attributes to whatever you'd like

The animiations, crossfading text, and any other magic is happening in the LoginPadActivity.

## Author / License

Copyright Cardinal Solutions 2013. Licensed under the MIT license.
<img src="https://raw.github.com/CardinalNow/NSURLConnection-Debug/master/logo_footer.png"/>
