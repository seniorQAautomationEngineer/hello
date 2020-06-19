import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.*;
import java.nio.file.*;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ZapProxy {
    @Test
    public void zapActions() throws Exception {
        activateZapInDaemoMode();
        test();
        collectZapSites();
        activateActiveScanner();
        saveHTMLreport();
        //Ping status exh 60 seconds
        //Save HTML report
    }

    public void activateZapInDaemoMode() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("cmd /c start BatchFiles\\ActivateZapInDaemonMode.bat");
        } catch (IOException Exception) {
            System.out.println(Exception.getMessage());
        }
        System.out.println("ZAP daemon is active");
    }

    @Test
    public void activateYamlFile() throws Exception {
        //Run a bat file
        Process process = Runtime.getRuntime().exec(
                "cmd /c  DockerComposeUp.bat", null, new File("hellosign"));
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }
        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Success!");
            System.out.println(output);
            System.exit(0);
        } else {
            // abnormal...
        }
    }

    @Test
    public void deactivateYamlFile() throws Exception {
        // Run a bat file
        Process process = Runtime.getRuntime().exec(
                "cmd /c  DockerComposeUp.bat", null, new File("hellosign"));
    }

    @Test
    public void updateZapCertificate() throws Exception {
        BackendProcesses backendProcesses = new BackendProcesses();
        File file = new File("rootcert.cer");
       // HttpResponse response = backendProcesses.login("http://localhost:8080/OTHER/core/other/rootcert?apikey=ueueuei");
       // Files.write(Paths.get("rootcert.cer"), EntityUtils.toString(response.getEntity()).getBytes(), StandardOpenOption.CREATE);

        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = "changeit".toCharArray();
        char sep = File.separatorChar;
        File certFile = new File(System.getProperty("java.home") + sep + "lib" + sep + "security" + sep + "cacerts");
        System.out.println(certFile);
        FileInputStream in = new FileInputStream(certFile);
        ks.load(in, password);
        in.close();
        ks.deleteEntry("owasp");

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream cin = new FileInputStream("rootcert.cer");
        Collection c = cf.generateCertificates(cin);
        Certificate cert = (Certificate) c.iterator().next();
        ks.setCertificateEntry("owasp", cert);
        FileOutputStream out = new FileOutputStream(certFile);
        ks.store(out, password);
        out.close();
        ArrayList<String> list = Collections.list(ks.aliases());
        System.out.println(list);
    }


    @Test
    public void collectZapSites() {
        HttpGet request = new HttpGet("http://localhost:8080/JSON/core/view/sites/?zapapiformat=JSON&apikey=APIkey&formMethod=GET");

        HttpClient client = HttpClientBuilder.create().build();
        List<String> sites = new ArrayList<>();
        try {
            HttpResponse response = client.execute(request);
            JSONObject responseJS = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONArray sitesJS = responseJS.getJSONArray("sites");

            for (Object o : sitesJS.toList()) {
                sites.add(o.toString());
            }
            System.out.println(sites);
            sites = sites.stream()
                    .filter(s -> (s.contains("myid-dev")))
                    .collect(Collectors.toList());
            Assert.assertTrue(sites.size() > 0);
            System.out.println(sites);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAttackStrengthiInAscanPolicy() {
        HttpGet request = new HttpGet("http://localhost:8080/JSON/ascan/action/addScanPolicy/?zapapiformat=JSON&apikey=APIkey&formMethod=GET&scanPolicyName=attackStrength&alertThreshold=&attackStrength=");

        HttpClient client = HttpClientBuilder.create().build();

        try {
            HttpResponse response = client.execute(request);
            String str = EntityUtils.toString(response.getEntity());
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void activateActiveScanner() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/JSON/ascan/action/scan/?zapapiformat=JSON&apikey=APIKey&formMethod=GET&url=https%3A%2F%2Fdomain&recurse=&inScopeOnly=&scanPolicyName=&method=&postData=&contextId=");
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        System.out.println("Activate ascan");

        while (!getStatusScanner().equals("100")) {
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        saveHTMLreport();
    }

    public String getStatusScanner() {
        HttpGet request = new HttpGet("http://localhost:8080/JSON/ascan/view/status/?zapapiformat=JSON&apikey=APIKey&formMethod=GET&scanId=");
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(request);
            JSONObject responseJS = new JSONObject(EntityUtils.toString(response.getEntity()));
            String status = responseJS.getString("status");
            System.out.println(status);
            return status;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void saveHTMLreport() {
        HttpGet request = new HttpGet("http://localhost:8080/OTHER/core/other/htmlreport/?apikey=APIkey&formMethod=GET");

        HttpClient client = HttpClientBuilder.create().build();

        try {
            HttpResponse response = client.execute(request);
            String str = EntityUtils.toString(response.getEntity());
            System.out.println(str);
            LocalDateTime ldt = LocalDateTime.now();
            String formattedDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
            Files.write(Paths.get("zapReports/report " + formattedDate + ".html"), str.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Example of API tests
    // @Test
    public void sendRequestUsingProxy() throws IOException {
        String cookies = "ExampleOfcookies";
        System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.8.0_191\\jre\\lib\\security\\cacerts");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpHost target = new HttpHost("domain", 443, "https");
            HttpHost proxy = new HttpHost("127.0.0.1", 8080, "http");

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet request = new HttpGet("/endpoint");
            request.setConfig(config);
            request.setHeader("Cookie", cookies);
            System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);
            CloseableHttpResponse response = httpclient.execute(target, request);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void test() {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("TestNG.xml");//path to xml..
        testng.setTestSuites(suites);
        testng.run();
    }
}

