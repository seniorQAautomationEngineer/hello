package com.hellosign.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reports {
    private static final boolean jenkinsOption = true;
    public static ExtentHtmlReporter htmlReporter;

    public static ExtentReports extent;
    private static ExtentTest currentTest;
    private static String lastAction;
    private static final String ROOT_PATH = "reports/";
    private static String currentTestSuiteResultsPath;

    static {
        createReportFolder();
        LocalDateTime ldt = LocalDateTime.now();
        String formatttedDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));

        if (jenkinsOption) {
            currentTestSuiteResultsPath = "Suite/";
        } else {
            currentTestSuiteResultsPath = "Suite " + formatttedDate + "/";
        }

        new File(ROOT_PATH + currentTestSuiteResultsPath).mkdir();
        htmlReporter = new ExtentHtmlReporter(ROOT_PATH + currentTestSuiteResultsPath + "report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void start(String testName) {
        currentTest = extent.createTest(testName);
    }

    public static void stop() {
        extent.flush();
    }

    public static void log(String message) {
        currentTest.log(Status.PASS, message);
        System.out.println(message);
        lastAction = message;
    }

    public static void fail(WebDriver driver, String methodName) {

        try {
            currentTest.fail("Failed step: " + lastAction);
            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dstpath =
                    Paths.get(ROOT_PATH + currentTestSuiteResultsPath + "fail_" + methodName + ".png");
            Files.copy(imageFile.toPath(), dstpath, StandardCopyOption.REPLACE_EXISTING);

            currentTest.addScreenCaptureFromPath(dstpath.toFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createReportFolder() {
        Path path = Paths.get("reports");
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }
}

