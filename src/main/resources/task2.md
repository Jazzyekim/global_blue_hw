# Exercise 2: Test Run Stopping Mechanism for TestNG
## Objective:
Automated tests are being executed with TestNG using a suite xml with
parallel:methods. Several projects utilize the same TestNG solution.
Create a mechanism that stops the test run if:
1. 25% or more of the currently executed tests have failed.
   AND
2. At least 20% of the total tests in the test run have been executed.
   Requirements:
1. Dynamic Test Monitoring: Continuously monitor the progress of
   the test execution.
2. Threshold Calculation: Calculate the failure percentage and the
   execution percentage dynamically.
3. Stop Execution: Stop the test execution once the specified
   thresholds are met.
4. Integration with TestNG: Integrate the stopping mechanism
   seamlessly with TestNG.
   Expected delivery: Code sample with explanation
