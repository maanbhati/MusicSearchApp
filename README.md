# MusicSearchApp
Music search App consists of two screens with below specifications Specification The first screen displays a list of album/artist/track from the last fm music library API (https://www.last.fm/home) and present the result in a scrolling list. Tapping a item from the list navigate to the second screen. The second screen will then load deatils of album/artist/track with images of the selected item. The App runs successfully and works on below scenario's.
As a user running the application I can select album/artist/track from the list So that I can view pictures of that 
Scenario 1: Viewing the item list When I launch the app Then I see a list of items 
Scenario 2: Viewing pictures of album/artist/track Given I have launched the app When I select a item from the list Then I see detailed data of the selected item.

Important: For running the app api_key needs to be generated from here (https://www.last.fm/api/account/create), once api_key is generated repalce that with API_KEY = "<YOUR_API_KEY>" valuse inside Constant.kt fiel inside project
 
Architectural Approach: Clean Architecture
Clear separation with Clean Architecture (presentation/interfaces/data)
Presentation with Android Presenter and Kotlin Synthetic approach
Data with Interactor (impl) and Interactor
Dependency Injection with Dagger2
Mapping data to domain model class
Junit for unit testing
Expresso for UI testing

# External Libraries
Retrofit2
RxJava
RxAndroid
Dagger2
Glide
Okhttp
Mockito

# Testing
Unit tests
Espresso tests
