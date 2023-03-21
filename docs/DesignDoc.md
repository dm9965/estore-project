---
geometry: margin=1in
---
# PROJECT Design Documentation

## Team Information
* Team name: Team 7
* Team members
  * Domenic Mangano
  * Zach Kroesen
  * Lucas Romero
  * Uttam Bhattarai
  * Connor Bastian

## Executive Summary

This is a summary of the project.

### Purpose
> _Provide a very brief statement about the project and the most
> important user group and user goals._

The purpose of this project is to design the front and backend of a complete website. The website created is a shoe store in which the customer can browse various brands of sneakers and make purchases easily. The website has various features that makes the shopping experience better than competitors.

### Glossary and Acronyms

| Term | Definition |
|------|------------|
| SPA | Single Page |
| MVP | Minimal Viable Product |
| DAO | Data Access Object |


## Requirements


### Definition of MVP
For the minimal viable product, there must be an authentication method and accessibility functionality. The user can browse the inventory as well as add and remove products from their shopping carts. The owner is not given access to the shopping carts and is given the information of admin to log in and receive admin access. 

### MVP Features
* Log In/Out Application 
* Username Assumption
* Add/Remove From Shopping Cart

### Roadmap of Enhancements
> _Provide a list of top-level features in the order you plan to consider them._

* Admin Sign-In Process : Admin Authentication and Functionality
* User Sign-In Process : User Authentication and Functionality
* Add/Remove for Shopping Cart Process : Users can Add/Remove Items to Shopping Cart
* Admin Shopping Cart Process : Admin Cannot Access Shopping Carts
* User Shopping Cart Process : User Can View Items in Shopping Cart

## Application Domain

This section describes the application domain.

![Domain Model](domain-model-placeholder.png)


## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The e-store web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts with the e-store application.

The user is greeted by the homepage to the website where they can browse or login to retrieve previously stored data. There is a navigation bar, a set of highlighted products and deals as well as a set of highlighted brands. The user can then click on any button and be greeted by the appropriate page. When the user clicks on the navigation bar it will give descriptions of products for the user to choose from and narrow down their options. When the user clicks on a product it will show the name and price as well as all of the other respected details of the product. When the user decides to purchase the product it is placed in the shopping cart where the user can view all of their items. 


### View Tier
> _Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

In the View Tier UI the types of components in the tier refer to the various paths on the website. For example there is separation for the navigation bar, the entire homepage, shopping cart, etc. Their responsibilities are given by their names as each file was specifically named after it's purpose.

> _You must also provide sequence diagrams as is relevant to a particular aspects 
> of the design that you are describing.  For example, in e-store you might create a 
> sequence diagram of a customer searching for an item and adding to their cart. 
> Be sure to include an relevant HTTP reuqests from the client-side to the server-side 
> to help illustrate the end-to-end flow._


### ViewModel Tier
> _Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

The ViewModel Tier contains all of the DAO's. These are the interfaces for the files as they act as basic functions for every aspect of the website's functionality. For example, the ViewModel tier contains DAO's for the cart, shoe and user.

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._


### Model Tier
> _Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

The model tier stores all the appropriate data required for the website to function properly. The model contains all of the getters and basic functions of the user, shoe and cart files. The way this is structured is to include the MVP features for the branch as well as any additions we saw fit to improve the functionality of the website.

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._

### Static Code Analysis/Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements._

Design improvements that we would make if the project were to continue include 

> _With the results from the Static Code Analysis exercise, 
> discuss the resulting issues/metrics measurements along with your analysis
> and recommendations for further improvements. Where relevant, include 
> screenshots from the tool and/or corresponding source code that was flagged._

## Testing

### Acceptance Testing

All 35 test cases for various files passed and no errors were found. There were a few issues occurring prior to the completion of testing but they were quickly fixed. There are currently some methods that are still under construction that require testing, however they have not reached this point yet.

### Unit Testing and Code Coverage

For unit testing, the strategy was to cover every common case as well as the few select edge-cases. This strategy is very effective due to it's spanning possibilities in which there are no scenarios that will go untested. The code coverage went well as all tests passed and no anomalies occurred other than the git repository having a few errors later on.
