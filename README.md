# CraveList

## **A restaurant review app** 

**What will the application do?**
- The application will allow the user to view highly-rated restaurants and add them 
to their restaurant wishlist.
- Users would be able to view restaurants that are in their wishlist, categorized by cuisines,
such as Chinese, Japanese, Italian, Seafood, Korean, Cafe, and Dessert.

**Who will use it?**

Anyone interested in finding a good restaurant for their next occasion can use it to get trustworthy
reviews. Restaurants can also view customer ratings and reviews to improve their service and food. 

**Why is this project of interest to you?**

I enjoy dining out a lot, and I tend to read reviews before going to a restaurant. However, I 
get really disappointed when the restaurant's service or food falls behind my expectations. 
I want to help others like myself to have the most pleasant dining experience. 

## **User Stories** 
As a user, I want to be able to add an arbitrary number of restaurants to my wishlist.    
As a user, I want to be able to add a restaurant to a category in my wishlist, such as the
Italian restaurant category.  
As a user, I want to be able to view a list of restaurants in my wishlist.  
As a user, I want to be able to remove a restaurant on my restaurant wishlist.

PHASE 2
As a user, when I select the quit option, I want to be asked whether I want to save my restaurant wishlist to file or not.
As a user, when I start the application, I want to be able to choose an option to load my restaurant wishlist from a file.

## **Resources** ##
PHASE 2
UI is referenced from TellerApp's UI
persistence package in main and test are both referenced from JsonSerializationDemo

PHASE 3
MainMenu class in ui is referenced from https://www.youtube.com/watch?v=5o3fMLPY7qY
DisplayWishlist is referenced from TablePrintDemo.java https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
AddRemoveWishlist is referenced from ListDemo.java https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
AddRemoveWishlist (table) is referenced from https://youtu.be/skryksKiIK0
Category dropdown spinner is referenced from TextInputDemo https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
AddRemoveList AddListener is referenced from https://youtu.be/CQMpXGwHeYQ 
AddRemoveList RemoveListener is referenced from https://youtu.be/DLJCxmW1M4s
DisplayWishlist DefaultTableModel is referenced from https://youtu.be/skryksKiIK0
JOptionPane in saveWishlistAction and loadWishlistAction is referenced from https://youtu.be/DLJCxmW1M4s

Plate with utensils photo https://i.pinimg.com/originals/56/8c/41/568c41c6b689ce054fefd3304d289d97.png

PHASE 4
WindowListener is referenced from https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by...
Adding a restaurant to the wishlist with the add button
- You can generate the second required action related to adding Xs to a Y by...
Removing a restaurant from the wishlist with the remove button
- You can locate my visual component by...
Starting the application.
- You can save the state of my application by...
Clicking the save to file button
- You can reload the state of my application by...
Clicking the load from file button

Phase 4: Task 2
Wed Apr 05 16:52:54 PDT 2023
Added Resto 1 to wishlist
Wed Apr 05 16:53:05 PDT 2023
Added Restoooooo to wishlist
Wed Apr 05 16:53:15 PDT 2023
Added Resto 2 to wishlist
Wed Apr 05 16:53:25 PDT 2023
Added Resto 3 to wishlist
Wed Apr 05 16:53:29 PDT 2023
Removed Restoooooo from wishlist

Phase 4: Task 3
A refactoring I might do if I had more time would be to split up my AddRemoveList class in the ui into two classes, 
one for adding into the list, and the other for removing from the list. Right now, this class has two 
functionalities, both adding and removing a restaurant from the restaurant list. Splitting the class into 
two will improve cohesion, as the code in each class would be more closely related.

This refactoring can be done by creating the new class and then move the functionality as well as any 
fields related to it into the new class. The refactored code will now follow the Single Responsibility Principle, 
which states that each class should have one responsibility.



## **Stretch Goals: Not Implemented Yet** ##
As a user, I want to be able to add a restaurant to my visited restaurants list and write a review on it (optional).
As a user, I want to be able to search for a restaurant name and view its rating and reviews.
As a user, I want to be able to select a restaurant on my visited restaurants list and rate it on a scale of 5 stars.
As a user, I want to be able to select a restaurant on my visited restaurants list and write a review on it.
As a user, I want to be able to come back to my visited list and write a review or rate a restaurant.
As a user, I want to view a list of highly-rated restaurants.


