package com.gb.task1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;

import java.util.*;
import java.util.function.*;

/**
 * An enumeration representing supported browser types and their corresponding WebDriver suppliers.
 *
 * <p>
 * Each browser type is associated with a specific WebDriver supplier that can be used to instantiate
 * a WebDriver instance for that browser.
 * </p>
 *
 * <p>
 * Supported browser types:
 * <ul>
 *     <li>{@link #CHROME} - Uses {@link org.openqa.selenium.chrome.ChromeDriver}</li>
 *     <li>{@link #FIREFOX} - Uses {@link org.openqa.selenium.firefox.FirefoxDriver}</li>
 *     <li>{@link #EDGE} - Uses {@link org.openqa.selenium.edge.EdgeDriver}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     BrowserType browserType = BrowserType.from("chrome");
 *     WebDriver driver = browserType.getDriver();
 * </pre>
 * </p>
 *
 * <p>
 * This enum also provides a utility method to convert a string representation of a browser type
 * into its corresponding {@link BrowserType} enum value.
 * </p>
 *
 * @see org.openqa.selenium.WebDriver
 */
public enum BrowserType {
    CHROME(ChromeDriver::new),
    FIREFOX(FirefoxDriver::new),
    EDGE(EdgeDriver::new);

    /**
     * Supplier for creating WebDriver instances for the corresponding browser type.
     */
    private final Supplier<WebDriver> driverSupplier;

    /**
     * Constructs a {@link BrowserType} with the specified WebDriver supplier.
     *
     * @param driverSupplier A supplier for creating WebDriver instances.
     */
    BrowserType(Supplier<WebDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }

    /**
     * Converts a string representation of a browser type to its corresponding {@link BrowserType} enum value.
     *
     * @param browserType The string representation of the browser type (case-insensitive).
     * @return The corresponding {@link BrowserType} enum value.
     * @throws NullPointerException if the input is null.
     * @throws IllegalArgumentException if the browser type is not supported.
     */
    public static BrowserType from(String browserType) {
        Objects.requireNonNull(browserType, "BrowserType cannot be null");
        try {
            return BrowserType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType, e);
        }
    }

    /**
     * Creates a new WebDriver instance using the supplier associated with this browser type.
     *
     * @return A new {@link WebDriver} instance.
     */
    public WebDriver getDriver() {
        return driverSupplier.get();
    }

}
