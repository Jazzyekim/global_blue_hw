# Exercise 3: Estimation of Total Test Execution Time for Automated Test Suite
## Objective:
Create an estimate for the total test execution time of a fully automated
test suite for an application containing both UI and API tests. Explain the
ideal ratio and execution of UI tests to API tests. Consider the growth of
test cases over multiple sprints.
Requirements:
1. Estimate Test Execution Time: Calculate the execution time for
   UI and API tests.
2. Ratio and Execution: Explain the ideal ratio of UI tests to API
   tests and how they should be executed.
3. Growth Over Sprints: Estimate the execution time in the first
   sprint and the 12th sprint, considering steady growth in the number
   of test cases.
4. Test Data Generation: Consider that both UI and API tests use
   APIs to generate their own test data and are encapsulated.
5. Provide Assumptions: State any assumptions made during the
   estimation process.


Expected delivery: Analysis/Explanation

---

- Assumptions:
    - API tests are faster than UI tests
    - Execution times:
        - A single API test: ~1 second.
        - A single UI test: ~5 seconds.
    - The test framework supports parallel execution.
    - Both UI and API tests generate test data dynamically using APIs.
  

- Ideal Ratio:
    - To be honest, I don't have a definitive answer to this question, but if we take the Pareto Principle (20:80) into account, a 1:4 (UI:API) ratio seems reasonable. 
        For every UI test, there should be approximately four API tests. We can take it as a guideline and adjust to the nature of specific product (e.g. for heavy backend 
        the ratio could be totally different)
  

- Estimation:
    - Let's say we have 50 tests in 1st sprint (10 UI, 40 API).
    - Execution time:
        - UI tests:  10 * 5 = 50  seconds.
        - API tests: 40 * 1 = 40  seconds.
        - Total time (1st sprint):  
            - 1 thread: 50 + 40 = 90 seconds 
            - 4 threads: 90/4 ~ 23 seconds


- Growth Over Sprints:
    - Let;s assume that the test number growth is by 20% per sprint.
    - Initial test suite (Sprint 1): 50 tests (10 UI, 40 API).
    - Growth rate: numberOfTests = numberOfTestsFromPrevSprint * 1.2


- Execution Time Estimate:
    - Sprint 1:
        - Tests: 10 UI, 40 API; Total: 50 tests 
        - Execution time:  90 seconds (1 thread) or ~ 23 seconds (4 threads)
    
    - Sprint 12:
        - Test growth: numberOfTests = 50 * 1.2^11 ~ 372
        - Distribution (1:4 UI:API): 74 UI, 298 API.
        - Time:
            - UI tests:  74 * 5 = 370  seconds.
            - API tests: 298 * 1 = 298 seconds.
            - Total (1 thread): 298 + 370 = 668 seconds.
            - Total (4 threads): 668 / 4 ~ 167 seconds.
