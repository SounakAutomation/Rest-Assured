package ExtentReport;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

public class ReportLogger {
    public static final Logger LOGGER = Logger.getLogger(ReportLogger.class);
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest extentTest;
    public static HashMap<String, String> reportPropertyMap = new HashMap<>();
    public static String reportFolder;

    public static void generateReport(String reportPath) throws IOException {
        System.out.println("here Before suit");
        new File(ReportLogger.reportFolder).mkdir();
        htmlReporter = new ExtentHtmlReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle(reportPropertyMap.get("htmlReportTitle"));
        htmlReporter.config().setReportName(reportPropertyMap.get("htmlReportTitle"));
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat(reportPropertyMap.get("TimeStampFormat"));
        LOGGER.info("HTML report created : " + reportPropertyMap.get("htmlReportTitle"));
    }
    public static void info(String msg) {
        extentTest.log(Status.INFO, msg);
        if (!msg.contains("RESPONSE"))
            LOGGER.info(msg);
    }

    public static void error(String msg) {
        extentTest.log(Status.ERROR, msg);
        if (!msg.contains("RESPONSE"))
            LOGGER.error(msg);
    }

    public static void pass(ITestResult result) {
        System.out.println("Pass");
        extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        LOGGER.info(result.getName() + " PASSED ");
    }

    public static void fail(ITestResult result) {
        extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
        extentTest.fail(result.getThrowable());
        LOGGER.error(result.getName() + " FAILED ");
        StringWriter sw = new StringWriter();
        result.getThrowable().printStackTrace(new PrintWriter(sw));
        LOGGER.error(sw.toString());
    }

    public static void skipped(ITestResult result) {
        extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
        extentTest.skip(result.getThrowable());
        LOGGER.error(result.getName() + " SKIPPED ");
    }
    public static void newTest(String method) {
        extentTest = extent.createTest(method);
        LOGGER.info("New testcase :" + method);
    }
    public static void printReport() {
        extent.flush();
        LOGGER.info(
                "HTML report saved at " + System.getProperty("user.dir") + reportPropertyMap.get("htmlReportFolder"));

    }

}
