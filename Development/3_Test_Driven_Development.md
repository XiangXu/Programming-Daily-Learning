# Test Driven Development(TDD)

## What is Test Driven Development?

**Test Driven Development(TDD)** approach first, the test is developed which specifies and validates what code will do. In simple terms, **test cases are created before code is written**. The purpose of TDD is to **make the code clearer, simple and bug-free**.

Test-Driven Development starts with designing and developing tests for every small functionality of an application. TDD instructs developers to write new code only if an automated test has failed. This avoids duplication of code. The full form of TDD is Test-Driven development.

![TDD1](https://www.guru99.com/images/8-2016/081216_0811_TestDrivenD1.png)

The simple concept of TDD is to write and correct failed tests before writing new code(before development). This helps to avoid duplication of code as we write a small amount of code at a time in order to pass tests. (Tests are nothing but requirement conditions that we need to test to fulfill them).

Test-Driven Development is a process of developing and running automated test before actual development of the application. Hence, TDD sometimes also called as **Test First Development**.

## How to perform TDD Test

Following steps define how to perform TDD test,

1. Add a test.
2. Run all tests and see if new test fails.
3. Write some code.
4. Run tests and Refactor code.
5. Repeat.

### TDD cycle defines

1. Write a test.
2. Make it run.
3. Change the code to make it right. Refactor.
4. Repeat process.

### Some clarifications about TDD

* TDD is neither about "Testing" nor about "Design".
* TDD does not mean "write some of the tests, then build a system that passes the tests".
* TDD does not mean "do lots of Testing".

## TDD vs Traditional Testing

TDD approach is primarily a specification technique. It ensures that your source code is thoroughly tested at confirmatory level.

* With traditional testing, a successful test finds one or more defects(瑕疵). It is same as TDD. When a test fails, you have made progress because you know that you need to solve the problem.
* TDD ensures that your system actually meets requirements defined for it. It helps to build your confidence about your system.
* In TDD more focus is on production code that verfies whether testing will work proerly. In traditional testing, more focus is on test case design. Whether the test will show the proper/improper execution of the application in order to fulfill requirements.
* In TDD, you achieve 100% coverage test. Every single line of code is tested, unlike traditional testing.
* The combination of both tradtional testing and TDD leads to the importance of testing system rather than perfection of the system.
* In Agile Modeling (AM), you should "test with a purpose". You should know why you are testing something and what level its need to be tested. 

## What is acceptance TDD and Developer TDD?

There are two levels of TDD

1. **Acceptance TDD(ATDD)**: With ATDD you write a single acceptance test. This test fulfills the requirement of the specification or satisfies the behavior of the system. After that write just enough production/functionality code to fulfil that acceptance test. Acceptance test focuses on the overall behavior of the system. ATDD also was known as **Behavioral Driven Development (BDD)**.

2. **Developer TDD**: With Developer TDD you write single developer test i.e. unit test and then just enough production code to fulfill that test. The unit focus on every small functionality of the system. Developer TDD is simply called as **TDD**.

The main goal of ATDD and TDD is to specify detailed, executable requirements for your solution on a just time basis. JIT means taking only those requirements in consideration that are needed in system, so increase effiency.

![TDD2](https://www.guru99.com/images/8-2016/081216_0811_TestDrivenD3.png)



Reference 

https://www.guru99.com/test-driven-development.html