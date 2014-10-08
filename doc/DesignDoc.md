Design Document
=======

1. Home screen, HomeActivity.java
2. Main screen with the list of products
3. Special offers
4. Order
5. Extra App

## 1) Home screen
HomeActivity will be the default navigation drawer activity from android studio. This screen will contain information about the store, the address, phonenumber etc. When an item in de navigation drawer menu is selected the content/layout changes to the corresponding view.

Usefull classes:
* [Navigation drawer](http://developer.android.com/training/implementing-navigation/nav-drawer.html)
* [Fragments](https://github.com/thecodepath/android_guides/wiki/Fragment-Navigation-Drawer)

![](https://github.com/JulianRR/Kippie/blob/master/doc/Homescreen.jpg)


## 2) Main screen
Here the user gets to see a list of products already ordered in categories. A filter option can be used to change the way the list is ordered. There will also be a sreach balk to search a product by name. When a user selects a product the user gets to see information about the product, allergens, price but also how they should prepare the food.

Usefull classes:
* [List](http://developer.android.com/reference/java/util/List.html)
  * [ListView](http://developer.android.com/reference/android/widget/ListView.html)
  * [Tutorial](http://www.vogella.com/tutorials/AndroidListView/article.html)
* [Search bar](http://www.androidhive.info/2012/09/android-adding-search-functionality-to-listview/)
* [Sorting list](http://www.xtensivearts.com/2009/11/15/quick-tip-2-sorting-lists/)

![](https://github.com/JulianRR/Kippie/blob/master/doc/Allergens.jpg)

## 3) Special offers
Here the user gets to see the posters of the current special offers. The user can scroll through the different poster like a slideshow.

Usefull classes:
* [Slideshow](http://androidopentutorials.com/android-image-slideshow-using-viewpager/)

![](https://github.com/JulianRR/Kippie/blob/master/doc/SpecialOffers.jpg)

## 4) Order
Here the user can order some of the special products we have in store. For now there will be only one product, because this product is the only product we have in store which needs to be ordered a few days before the customer wants it. A simple form will need to be filled in by the user. This information will be send to my boss (most likely by email). If the secod app can be used, the orders van be viewed there.

Usefull classes:
* [Email](http://www.tutorialspoint.com/android/android_sending_email.htm)
* [Form](http://developer.android.com/guide/topics/ui/controls.html)

![](https://github.com/JulianRR/Kippie/blob/master/doc/Order.jpg)

## 5) Extra App
This app will only be made if there is enough time and when the first app is finished. This app will only be used by my boss and colleagues to update the information in the allergens app. A database will be used for all the necessary information. This database will already be used for the first app.

Database:
* [Android database](http://developer.android.com/training/basics/data-storage/databases.html)
* [SQLite](http://www.sqlite.org/)
  * [Tutorial](http://www.vogella.com/tutorials/AndroidSQLite/article.html)    

![](https://github.com/JulianRR/Kippie/blob/master/doc/Database.jpg)
