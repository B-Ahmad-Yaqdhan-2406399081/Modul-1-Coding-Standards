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

# Module 2: CI/CD & DevOps
Deployment link: https://head-kimmy-mednoob-168edaa4.koyeb.app/

## Reflection 1
I fixed the tests not having 100% coverage by adding more test cases. The method that was missing test was `findById` in the `ProductRepository`.

## Reflection 2
With the fact that the workflow runs build and automate tests on push and pull request, it should follow the definition of Continuous Integration (CI). For Continuous Deployment (CD), it states that it is one step further from CI by automating the deployment process to an environment. Using the Koyeb auto deployment that I have set up, it should follow the definition of CD as well. Therefore, it is concluded that the current implementation of workflow follows the definition of CI/CD.

# Module 3: Maintainability & OO Principles
I didn't work on this module

# Module 4: TDD & Refactoring

## Reflection 1
The TDD flow we used here, for me, is pretty useful. It sets a clear todo-like steps that we can follow to implement a feature. It also forces us to think about the test cases before implementing the feature which is pretty helpful to make sure that we have covered all the cases. However, it can be pretty time-consuming if we have to write a lot of test cases for a simple feature. Therefore, I think it's important to find a balance between writing enough test cases and not overdoing it.

## Reflection 2
I think the tests that we wrote in this module followed the FIRST principles. The tests are fast, independent, repeatable, self-validating, and timely. The tests are also pretty simple and easy to understand which is pretty good for maintainability. The naming of the test methods also follows the convention which makes it easier to understand what the test is doing.
