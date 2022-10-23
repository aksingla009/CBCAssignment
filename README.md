# CBCAssignment
For the requirement to be fullfilled 
I started by creating an empty project and adding required dependencies in app level gradle file.

I then updated all the versions to the latest ones to support

I decided to follow MVVM Architecture for the solution

Then I hit the API and saw response and parsedit using JsonOnline parser to see the type of result

I then Installed JsonToKotlin class plugin to create data class as per response

But since only certain types of data was required I removed unwanted data class files from project and set data class to bare minimum which will be required in future
and placed data classes in model package

So for that I started by Implementing Retrofit for Networking calling and Dagger2 for Dependency Injection

I created di package for Dependency injection,
repositiory package for data related tasks
constants package where i kept constants for project like BASE_URL
retrofit package for network related tasks and created NewsAPIInterface

After retrofit implementation
I implemented recycler view functinality by creating layout file for item and setting up adapter

Once all was in place I implemnted room persistence library functionality to save data in db and fetch dara from DB once network was not there

For Network changes I created network utils class to see If network is present or not and informed the user using Live Data

Once the list was getting populated I implemented filter functionality based on type of news,

For that I added Spinner with list of types of news, On selecting that only those news would be displayed with that type.

At last I cleaned up the code a little.

![image](https://user-images.githubusercontent.com/10522019/197409894-30419bd3-0c51-498e-b2f2-8c6807b35ade.png)

