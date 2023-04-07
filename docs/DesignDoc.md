PROJECT Design Documentation

## Team Information
¬¬¬
•	Team name: TEAM 7
•	Team members
o	Domenic Mangano
o	Zach Kroesen
o	Connor Bastian
o	Uttam Bhattarai
o	Lucas Romero

### Executive Summary

The 7Shoes shoe store has been built using Angular/TypeScript for the front-end and Java for the back-end. The use of Angular/TypeScript provides a dynamic and responsive user interface, while Java allows for robust and scalable back-end processing. This combination of technologies enables this e-store to provide a seamless shopping experience for customers, with easy navigation, fast load times, and secure transactions. The Java backend supports features such as inventory management, order processing, and customer data management.

Purpose
[Sprint 2 & 4] Provide a very brief statement about the project and the most important user group and user goals.

This project is aimed at giving a soft introduction to fullstack development for novice to intermediate programmers. Learning how a backend interacts with a front end and how to develop both sides of the coin are both major goals for this project.

Glossary and Acronyms
[Sprint 2 & 4]
Provide a table of terms and acronyms.

| Term | Definition |
|------|------------|
| SPA | Single Page |





Requirements

This section describes the features of the application.

Definition of MVP
[Sprint 2 & 4]
Provide a simple description of the Minimum Viable Product.

The in order to meet the requirements of the Sprint 2 MVP (Minimum Viable Product), we had to complete functional user and cart objects with accompanying DAO and Controller methods so as allow the user to log in/out, browse shoes in stock by their attributes using the search function, add shoes to their cart, as well as admin login privileges that allowed for inventory management such adding, updating, and deleting shoes from the inventory.

MVP Features
[Sprint 4]
Provide a list of top-level Epics and/or Stories of the MVP.

Enhancements
[Sprint 4]
Describe what enhancements you have implemented for the project.

 
Application Domain

![image](https://user-images.githubusercontent.com/111752403/230527291-a99b696a-b097-4c55-b3f3-151ca8040db2.png)

This section describes the application domain.

[Sprint 2 & 4] 
Provide a high-level overview of the domain for this application. You can discuss the more important domain entities and their relationship to each other.

Current Domain Analysis from Sprint 2:

  
## Architecture and Design

This section describes the application architecture.

Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

 

The e-store web application is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistence. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The View-Model provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the View-Model and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.

### Overview of User Interface

The e-store's UI was designed so that it is extremely easy for the user to both find exactly what their looking for, add it to their cart, and checkout, but also be able to poke around the website for things they might not have known they were interested in, such as specific brands in the brand bar that spans the top of the page under the navigation bar, or the featured products that include white sneakers, women’s sneakers, and the legendary Stan Smith tennis shoe. The search function is easy to find and use, and is available on every page as it is included in the navigation bar that is pinned to the top of the app. Because we’re more focused on streetwear style, a while brick background throughout gives off a cool and modern aesthetic along with softly edged buttons and a largely monochromatic color palette.

View Tier
[Sprint 4]
Provide a summary of the View Tier UI of your architecture. Describe the types of components in the tier and describe their responsibilities.  This should be a narrative description, i.e. it has a flow or "story line" that the reader can follow.

[Sprint 4]
You must provide at least 2 sequence diagrams as is relevant to a particular aspect of the design that you are describing.  For example, in e-store you might create a sequence diagram of a customer searching for an item and adding to their cart. As these can span multiple tiers, be sure to include an relevant HTTP requests from the client-side to the server-side to help illustrate the end-to-end flow.

[Sprint 4]
To adequately show your system, you will need to present the class diagrams where relevant in your design. Some additional tips: Class diagrams only apply to the View-Model and Model Tier. A single class diagram of the entire system will not be effective. You may start with one but will be need to break it down into smaller sections to account for requirements of each of the Tier static models below. Correct labeling of relationships with proper notation for the relationship type, multiplicities, and navigation information will be important.

## View Model Tier
[Sprint 4]
Provide a summary of this tier of your architecture. This section will follow the same instructions that are given for the View Tier above.

At appropriate places as part of this narrative provide one or more updated and properly labeled static models (UML class diagrams) with some details such as critical attributes and methods.

[Replace with your View-Model Tier class diagram 1, etc.] (model-placeholder.png)

## Model Tier
[Sprint 2, 3 & 4] 
Provide a summary of this tier of your architecture. This section will follow the same instructions that are given for the View Tier above.




Shoe Model:

![image](https://user-images.githubusercontent.com/111752403/230527161-d0550ce2-f25d-47c5-b98b-a8b85cdc3d35.png)


User Model:

![image](https://user-images.githubusercontent.com/111752403/230527195-33186945-e7c2-4eb5-a43e-88d0a5d3f137.png)

 
Cart Model:

![image](https://user-images.githubusercontent.com/111752403/230527205-e9570660-77a3-460d-9664-e79259ea49ba.png)


## OO Design Principles

[Sprint 2]

We strictly adhered to many of the Object-Oriented Principles, but the 4 main principles we adhered to in this project were primarily implemented in the API side of the project:

1)	Single Responsibility Principle: The API follows the single responsibility principle by ensuring that each class has only one responsibility or reason to change. For example, the class responsible for managing customer data is separate from the class that handles inventory management.

2)	Open/Closed Principle: The API adheres to the open/closed principle by allowing for extension without modification. For example, new features can be         added to the API without modifying existing code.

3)	Liskov Substitution Principle: The API follows the Liskov substitution principle by ensuring that any derived class can be substituted for its base class. For example, any subclass of the shoe class can be used in place of the base shoe class.


4)	Dependency Inversion Principle: The API adheres to the dependency inversion principle by relying on abstractions rather than concrete implementations. This allows for flexibility and ease of modification. For example, the API uses interfaces to define dependencies rather than specific classes.

[Sprint 3 & 4]
##OO Design Principles should span across all tiers.

##Static Code Analysis/Future Design Improvements

[Sprint 4] 
With the results from the Static Code Analysis exercise, 

Identify 3-4 areas within your code that have been flagged by the Static Code Analysis Tool (SonarQube) and provide your analysis and recommendations. Include any relevant screenshot(s) with each area.

[Sprint 4]
Discuss future refactoring and other design improvements your team would explore if the team had additional time.


### Testing

This section will provide information about the testing performed and the results of the testing.

## Acceptance Testing
[Sprint 2 & 4]
Report on the number of user stories that have passed all their acceptance criteria tests, the number that have some acceptance criteria tests failing, and the number of user stories that have not had any testing yet. Highlight the issues found during acceptance testing and if there are any concerns.

[Sprint 2]
All user stories that were in the Sprint 2 backlog were completed in a satisfactory manner along with all required functionality






## Unit Testing and Code Coverage
[Sprint 4]
Discuss your unit testing strategy. Report on the code coverage achieved from unit testing of the code base. Discuss the team's coverage targets, why you selected those values, and how well your code coverage met your targets.

[Sprint 2 & 4]

[Sprint 2]

 
