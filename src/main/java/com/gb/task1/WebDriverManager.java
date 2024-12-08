package com.gb.task1;

import lombok.extern.slf4j.*;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.ConcurrentHashMap;


/**
 * A thread-safe singleton class for managing WebDriver instances.
 * This class ensures that each thread gets its own WebDriver instance,
 * providing methods for initialization, cleanup, and global resource management.
 *
 * <p>
 * The class uses {@link ThreadLocal} to store WebDriver instances per thread
 * and a {@link ConcurrentHashMap} to keep track of all created instances for global cleanup.
 * </p>
 *
 * <p>
 * Usage:
 * <pre>
 *     WebDriverManager manager = WebDriverManager.getInstance();
 *     WebDriver driver = manager.getDriver("chrome");
 *     // Perform browser operations
 *     manager.quitDriver(); // Cleanup after usage
 * </pre>
 * </p>
 *
 * <p>
 * Example for global cleanup:
 * <pre>
 *     WebDriverManager.getInstance().cleanupAllDrivers();
 * </pre>
 * </p>
 *
 * <p>
 * This class uses the Singleton design pattern and ensures that only one instance
 * of the class is created during the application's lifecycle.
 * </p>
 *
 * @author YourName
 */
@Slf4j
public class WebDriverManager {

    /**
     * Thread-local variable to store WebDriver instances per thread.
     */
    private static final ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();

    /**
     * Concurrent map to keep track of all created WebDriver instances
     * for proper cleanup.
     */
    private static final ConcurrentHashMap<Long, WebDriver> driverMap = new ConcurrentHashMap<>();

    /**
     * Private constructor to prevent instantiation.
     */
    private WebDriverManager() {}

    /**
     * Static inner class to hold the Singleton instance.
     */
    private static class Holder {
        private static final WebDriverManager INSTANCE = new WebDriverManager();
    }

    /**
     * Provides access to the singleton instance of the WebDriverManager.
     *
     * @return The singleton instance of {@link WebDriverManager}.
     */
    public static WebDriverManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Retrieves the WebDriver instance for the current thread.
     * If no instance exists, it initializes a new one based on the specified browser type.
     *
     * @param browserType The type of browser to initialize (e.g., "chrome", "firefox", "edge").
     * @return The {@link WebDriver} instance for the current thread.
     */
    public WebDriver getDriver(String browserType) {
        log.debug("Getting webdriver for {}", browserType);
        if (threadLocalWebDriver.get() == null) {
            WebDriver driver = BrowserType.from(browserType).getDriver();
            threadLocalWebDriver.set(driver);
            log.debug("Initializing WebDriver for Thread ID: {}", Thread.currentThread().getId());

            driverMap.put(Thread.currentThread().getId(), driver);
        }
        return threadLocalWebDriver.get();
    }

    /**
     * Cleans up the WebDriver instance for the current thread.
     * This method quits the WebDriver and removes it from the thread-local storage and map.
     */
    public void quitDriver() {
        WebDriver driver = threadLocalWebDriver.get();
        if (driver != null) {
            driver.quit();
            threadLocalWebDriver.remove();
            driverMap.remove(Thread.currentThread().getId());
        }
    }

    /**
     * Performs global cleanup for all WebDriver instances created by all threads.
     * This method quits all WebDriver instances and clears the internal map.
     */
    @SuppressWarnings("unused")
    public void cleanupAllDrivers() {
        driverMap.forEach((threadId, driver) -> {
            if (driver != null) {
                driver.quit();
            }
        });
        driverMap.clear();
    }
}
