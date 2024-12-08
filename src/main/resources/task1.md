# Exercise 1: Thread-Safe Wrapper for Selenium in Java
## Objective:
Create a thread-safe wrapper for Selenium WebDriver to ensure that each
thread has its own instance of WebDriver. This is crucial in parallel test
execution to avoid conflicts and ensure the reliability of test results.
### Requirements:
1. Thread-Safe WebDriver Management: Each thread should have
   its own WebDriver instance.
2. Proper Cleanup: Ensure that WebDriver instances are properly
   closed and resources are released after test execution.
3. Flexibility: The wrapper should support different browser types.
4. Singleton Design Pattern: Implement a Singleton pattern to
   manage the WebDriver instances.
5. Concurrency Management: Use appropriate Java concurrency
   utilities to handle thread safety.
   Expected delivery: Code sample with explanation
