
# News Breeze

This project is a News app build on single Activity with help of navigation, following the MVVM pattern



## Appendix

This app is created on Single activity following mvvm pattern. This is a light app that uses Navigation Components .Paging is also implimented in this project with the help of RecyclerViewScrollListner
Room is used for storing saved news by the user.LiveData is used to update the data for saved articles list. Search feature is also enabled which allows the user to search based on news title. User's Internet connection is also checked with the help of Connectivity Manager.

Testing Setup
Basic Ui and unit tests, Instrumented Tests to test room database operations. 
Mocked Api response setup for testing with MockWebServer and OkHttpIdling Resource


For deleted a saved news a user can swipe left or right on the savedNewsFragment to delete the article from database . An undo snackbar also apppears in case the user changes his mind.

## Authors

- [@arsalanahmad4](https://github.com/arsalanahmad4)



## 🚀 About Me
I'm a android developer intern at Raaho(Quick Digitals Pvt Ltd.) , a well established startup with an android team of 3 members including me





## 🛠 Skills
Kotlin, Java , Jetpack Compose


## Lessons Learned

Unit testing liveData response




## Screenshots
NavGraph
![App Screenshot](https://github.com/arsalanahmad4/ScreenShots/blob/main/NewsBreeze/NavGraph.jpg)

BreakingNewFragment
![App Screenshot](https://github.com/arsalanahmad4/ScreenShots/blob/main/NewsBreeze/BreakingNewsFragment.jpg)

SearchNewsFragment
![App Screenshot](https://github.com/arsalanahmad4/ScreenShots/blob/main/NewsBreeze/SearchNewsFragment.jpg)

SavedNewsFragment
![App Screenshot](https://github.com/arsalanahmad4/ScreenShots/blob/main/NewsBreeze/SavedNewsFragment.jpg)

ArticleFragment
![App Screenshot](https://github.com/arsalanahmad4/ScreenShots/blob/main/NewsBreeze/ArticleFragment.jpg)


