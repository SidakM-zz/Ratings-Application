# Ratings-Application

This java program uses a simple but effective suggestion algorithm to suggest content to users based upon four factors:

  1) Ratings given by other users.
  
  2) Ratings given by current user.
  
  3) Correlation between users (i.e. if User A and User B like more of the same content User A's ratings 
  will be weighted proportionately heavier than other users when generating suggestions)
  
  4) Overall rating of a piece of content.
  
  The methods sumofMultiply and rateBooks in class Interface encompass the majority of the suggestion algorithm.
  
Text Files:
  
  Books55: Contains the list of content (in this case books). Content can be changed as long as it follows the same format. ie Author,Name
  
  Password: Contains the username and password in the followinf format: Username,Password 
  
  Classes:
  
  Interface: Majority of the UI and suggestion alhgorithm including main. Allows users to login get suggestions, add ratings, change     ratings, add content and view the top 10 or trending content. 
  
  Password: Handles the login menu. User may enter as admin (user: admin pass: admin) and add users to the system through the UI
  
  Book: Stores the book's author, name, ratings and average ratings. Can be changed to accomodate other types of content.
  
  User: Stores user information and ratings during runtime.
    

  
  
