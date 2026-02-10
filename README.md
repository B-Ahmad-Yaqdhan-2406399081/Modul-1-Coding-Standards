# Module 1: Coding Standards
Name: Ahmad Yaqdhan \
NPM: 2406399081 \
Class: B

## Reflection 1
I learned that managing data manually just like in ProductRepository is unreliable. It does not save persistently when server is restarted and it takes a lot of effort to build one that saves. One way to solve this is by using external dependencies or DBMS to manage datas.

Looking at my code, I think it already follows clean code practices and coding standards. Everything is made self-explanatory by using well-named variables.

## Reflection 2
I think my unit tests is not enough to cover really-really extreme cases. But, for a simple app like this, the test cases I made should suffice. One way to verify that the unit tests are good enough is by using the code coverage metrics. Basically, it tells you how much of the code is executed when tests are executed. It's pretty helpful to detect cases that you haven't handled in the tests. However, 100% code coverage does not mean that the code has no bugs or errors since code coverage only analyzes the amount of line of codes that are used and not the logics behind it.

If I was about to make the new functional test suite, the potential code issue I can think of is redundancy in the code. This redundancy came from the fact that all three functional test cases will have some common functionality which means that we can make a structure that is reusable for all of them. Possible improvement is to make an abstract class that can be extended or used by each functional tests.
