A. MyPlaces is an Anroid Application that can query nearby places by using Google Place API
B. You need Android Studio to compile and install to Android Phone or Simulator
  1.  Download and Install Android Studio
  2.  Use Git Clone to clone the repository to your local storage
  3.  Open Project from Android Studio
  4.  Either Connect a Phyical Phone or use Simulator.
  5.  Android OS version has to be 8.0 and UP
  6.  Run the Application to compile and install on your device
  7.  Application will ask you location permission for the first time.  If you deny, application will close, otherwise will display the main uI
  8.  From Main UI, you can type in the keyword on top search area and click search button on keyboard.  Search result will be 10 or less
  9.  You also can change search range to differenct value
  10.  Successful search result will be saved to Database
  11.  If you click history tab, your will see a list of historic search, click any item, app will display that searching results offline
  12.  Click reset button will erase all of history
C. Unit Testing is located at MyPlacesUnitTest, just click the file and run the test.  Testing data is coming from a local sample file
D. Issue and Workaround
  1. Didn't handle network error in detail mode, just return true or false
  2. Only Tested on Simulator, Google Pixel 2 XL, and Samsung Galaxy S20
  3. Didn't handle text wrapping (if name is too long)
  4. Didn't handle location retrieving failure issue
