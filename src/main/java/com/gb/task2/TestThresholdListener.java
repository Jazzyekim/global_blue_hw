package com.gb.task2;


import lombok.extern.slf4j.*;
import org.testng.*;

import java.util.concurrent.atomic.*;

/**
 * A custom test listener that monitors test execution and enforces thresholds for failures and execution percentage.
 * <p>
 * This listener halts the test execution if the configured thresholds are exceeded:
 * - **FAILURE_THRESHOLD**: Percentage of failed tests among executed tests.
 * - **EXECUTION_THRESHOLD**: Percentage of tests executed among total tests.
 * </p>
 *
 * <p>
 * Usage:
 * Attach this listener to your test suite to monitor test execution thresholds and automatically halt execution
 * if thresholds are breached.
 * </p>
 */
@Slf4j
public class TestThresholdListener implements ITestListener {
    private static final double FAILURE_THRESHOLD = 25.0;
    private static final double EXECUTION_THRESHOLD = 20.0;
    private final AtomicInteger totalTests = new AtomicInteger(0);
    private final AtomicInteger executedTests = new AtomicInteger(0);
    private final AtomicInteger failedTests = new AtomicInteger(0);
    private final AtomicBoolean  stopExecution = new AtomicBoolean(false);

    public TestThresholdListener() {
    }

    @Override
    public void onStart(ITestContext context) {
        int totalNumberOfTests = context.getAllTestMethods().length;
        log.debug("Total number of tests: {}", totalNumberOfTests);
        totalTests.set(totalNumberOfTests);
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (stopExecution.get()) {
            result.setStatus(ITestResult.SKIP);
            throw new SkipException("Test skipped due to threshold breach.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test '{}' is skipped", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        incrementExecutedTests();
        failedTests.incrementAndGet();
        evaluateThresholds();
    }

    private void incrementExecutedTests() {
        executedTests.incrementAndGet();
    }

    /**
     * Evaluates whether the failure and execution thresholds have been breached.
     * If thresholds are breached, halts further execution by setting the stop flag.
     */
    private synchronized void evaluateThresholds() {
        try {
            double executedPercentage = (executedTests.get() / (double) totalTests.get()) * 100;
            double failurePercentage = executedTests.get() > 0
                    ? (failedTests.get() / (double) executedTests.get()) * 100
                    : 0.0;
            log.debug("Executed:  ({}/{})*100 = {}%", executedTests.get(), totalTests.get(), executedPercentage);
            log.debug("Failure:  ({}/{})*100 = {}%", failedTests.get(), executedTests.get(), failurePercentage);

            if (executedPercentage >= EXECUTION_THRESHOLD && failurePercentage >= FAILURE_THRESHOLD) {
                stopExecution.set(true);
                log.error("Thresholds met: Stopping further execution...");
            }
        } catch (Exception e) {
            log.error("Error during threshold evaluation", e);
        }
    }
}
