# Smack-Android-Model-View-Presenter-
This is Android version of the Smack-iOS(https://github.com/mdadil2019/Smack-iOS) app which is developed with MVP architecture

Architecture: Model View Presenter

Tools & Languages: Java, Android Studio

Libraries: Dagger 2, RxJava2, Retrofit, Butterknife, Socket.IO

API Endpoint : https://adilchat.herokuapp.com/

## Core Features
1. Login and Logout
2. Registration
3. Profile View
4. Channels Management (Add, Create)
5. Chat in specific channels

Remote Database : MongoDB

Local Database : SQLite(RoomDB)

## MVP Design

**User Interface (ui)**

Views
1. LoginActivity implement LoginActivityMVP.View
2. RegistrationActivity implements RegistrationActivityMVP.View
3. ProfileView implements ProfileView.View
4. ChannelActivity implements ChannelActivityMVP.View
5. ChatActivity implements ChatActivityMVP.View

Presenter
1. LoginActivityPresenter implement LoginActivityMVP.Presenter
2. RegistrationActivityPresenter implements RegistrationActivityMVP.Presenter
3. ProfileViewPresenter implements ProfileView.Presenter
4. ChannelActivityPresenter implements ChannelActivityMVP.Presenter
5. ChatActivityPresenter implements ChatActivityMVP.Presenter

**Model(data)**
1. Local Database(db)
   - User(Entity,Dao,UserDatabase)
   - Channel (Entity,Dao,ChannelDatabase)
   - Chat(Entity,Dao,ChatDatabase)
2. Remote Database(network)
   - Models
     - Login Request
     - Login Response
     - Logout Response
     - Registration Request
     - Registration Response
     - Channels Requests
     - Channels Response
     - Message Request
     - API Error
  - API Endpoints (final class)
    - Base URL : https://adilchat.herokuapp.com/v1/ 
    - Login URL : account/login
    - Registration URL : user/add
    - Channels (GET) : channel/
    - Messages (GET):message/byChannel/
  - API Headers
    - Header
    - Bearer Header
  - App API Helper
    - Methods for requesting endpoints with RxJava & Retrofit and returning observables if required
  - Preferences
    - AppPreferencesHelper implements PreferenceHelper
    - Methods
      - getname()
      - getavatar()
      - getusername()
      - getloginstatus()
  - Data Manager (Interface) extend AppPreferenceHelper, APIHelper 
  - AppDataManager implements DataManager
  
*Note: All the methods of APIHelper, PreferencesHelper & RoomDB will be called from data manager by providing the required dependencies by Dagger *

**Dependency Injection (di)**
1. Modules
  - LoginActivityModule
  - RegistrationActivityModule
  - ChannelActivityModule
  - ChatActivityModule
  - ProfileViewModule
2. AppComponent (Interface)


###### Note: The API is designed to handle the sending messages via socket, we can look at the API source code to find the method that is responsible for sending the messages.
