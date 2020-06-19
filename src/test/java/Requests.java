import org.testng.annotations.Test;

public class Requests {
    BackendProcesses backendProcesses = new BackendProcesses();

    @Test
    public void testLoginRequest(){
        backendProcesses.samlLoginVerification();
    }
}
