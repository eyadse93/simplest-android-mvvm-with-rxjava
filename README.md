# simplest-android-mvvm-with-rxjava
This is the simplest example you can find about connecting a view to a view model and data sources with rxjava.

This sample app has four main components:
* Views: 
  - It holds the different views of the app (in this example it holds the fragment).
  - Each "View" has a "ViewModel" (which is the second main component), and is "Subscription" (which receives the data using RXJava).
  - Views are dumb! you should not put any computations here.
  
* ViewModels:
  - This component is the brain of the "View", it handles all calculations and requests and give back a simple value to the "View" to show it in the UI.
  - each "ViewModel" has a reference to the "RepositoryManager" (which is the part of the fourth main component) and a "PublishSubject" (which is component that will send the data back to the view using RXJava).
  
* Models:
  - This component holds the different Models/Entities of the app (for example: Users, Orders, Places).

* Repository:
  - This component is the source of the data in the app, it has a "RepositoryManager" which controls sending and receiving data from "Local" and "Cloud" data sources.
  - The "RepositoryManager" receives the request from the "ViewModel" and handle it, then sends the result back using RXJava to the "View".
  
