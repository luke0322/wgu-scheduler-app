1. If the course scheduling application was developed for a tablet instead of a phone, I would consider allowing the application to run in multi-window mode or split-screen mode and could set the activity to resizeable. I would also try and make the layouts focusable and able to be focused for better input compatibility on larger screens.

2. The minimum SDK version is Android 26 and target SDK version is Android 29.

3. Some of the challenges I faced during the development of the mobile application was how I would implement the notifications while the app was closed. Other challenges also included how to incorporate both fragments and activities into the application, and how I would tackle the date picker.

4. Ways I overcame the notifications being presented when the app was closed was by incorporating shared preferences into the application. I was able to incorporate both fragments and activities by having some reusable utilities to switch fragments and activities that was reusable throughout the application lifecycle. Tackling the date picker I found was easiest to bind the click and focus events to when an edit text was pressed to create a date picker each time they were pressed, and then populate the edit text in the same reusable function.

5. If I were to do this project again, I would have spent more time thinking of how the application would flow between the different screens, and also spend a bit more time on styling. I also could have potentially reused the same fragments for both the initial insert and the creation of the assessments, courses, and terms.

6. I used a Pixel 3a emulator in addition to a Samsung Galaxy S9 physical device. The nice part about the emulator is I didn't have to do testing on a physical device. The downside of using an emulator is I was having trouble getting the emulator to launch and had to mess around with the launch settings to always start from cold boot. A pro of the development device is being able to interact with the device physically as a user would in the real world.

More info from signing:
- Signing key: key0, password: wgu123
- Generated apk: app-debug.apk
- Screenshots: signing-debug.PNG, signing-key.PNG
- Storyboard: storyboard.png