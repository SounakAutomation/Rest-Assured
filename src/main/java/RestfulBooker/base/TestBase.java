package RestfulBooker.base;

import ExtentReport.ReportLogger;
import RestfulBooker.api.GetHealthCheck;
import RestfulBooker.apihelper.Headers;
import RestfulBooker.apihelper.Resource;
import RestfulBooker.apihelper.RestValidation;
import RestfulBooker.datafactory.DataFactory;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class TestBase {
    private static Properties prop;

    public static DataFactory data = new DataFactory();
    public static List<Header> headers;
    public static String curDir = System.getProperty("user.dir");
    GetHealthCheck getHealthCheck=new GetHealthCheck();

    @BeforeSuite
    public void BeforeSuite() throws Exception {
        initializePropertyFiles();
        String currentFolder = curDir + ReportLogger.reportPropertyMap.get("htmlReportFolder");
        if (!(new File(currentFolder).exists()))
            new File(currentFolder).mkdir();
        ReportLogger.reportFolder = currentFolder;
        ReportLogger.generateReport(ReportLogger.reportFolder + ReportLogger.reportPropertyMap.get("htmlReportName"));
    }


    @BeforeMethod
    public void initializeRestClient(Method method) throws Exception {
        try {
            ReportLogger.newTest(method.getName());
        } catch (Exception e) {
            ReportLogger.error("Error while adding a test in extent report.");
            e.printStackTrace();
        }
        headers = Headers.getHeader();
        Response response=getHealthCheck.getHealthCheck(Resource.getHealth(),headers);
        RestValidation.validateStatusCode(response,201);
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                ReportLogger.fail(result);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("Sucess");
                ReportLogger.pass(result);
            } else {
                ReportLogger.skipped(result);
            }
        } catch (Exception e) {
            ReportLogger.error("Error while adding test result in html report for : " + result.getTestName());
            throw e;
        }
    }
    @AfterSuite
    public void tearDown() {
        try {
            ReportLogger.printReport();
        } catch (Exception e) {
            ReportLogger.error("Error while printing report at desired location.");
            throw e;
        }

    }

    private void initializePropertyFiles() throws FileNotFoundException, IOException {
        try {
            ReportLogger.reportPropertyMap = getProperties(curDir + "/src/main/resources/extentReport.properties");
        } catch (Exception e) {
            ReportLogger.error("Error while reading config properties.");
            throw e;
        }
    }
    public HashMap<String, String> getProperties(String propertyPath) throws FileNotFoundException, IOException {
        prop = new Properties();
        HashMap<String, String> map = new HashMap<String, String>();
        loadProps(propertyPath);
        Set<Object> keys = prop.keySet();
        for (Object k : keys) {
            String key = (String) k;
            map.put(key, (String) prop.getProperty(key));
        }
        return map;
    }
    private void loadProps(String propertyFile) throws FileNotFoundException, IOException {
        File cfgfile = new File(propertyFile);
        if (cfgfile.exists()) {
            FileInputStream propin = new FileInputStream(cfgfile);
            prop.load(propin);
        }
    }
}

