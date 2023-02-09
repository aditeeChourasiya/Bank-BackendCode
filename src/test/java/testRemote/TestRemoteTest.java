package testRemote;

import io.bankbridge.handler.BanksRemoteCalls;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class TestRemoteTest {

    @InjectMocks
    public BanksRemoteCalls banksRemoteCalls;

    @Mock
    Map config;

//    @Test
//    public void testHandle() throws Exception {
//
//
//        Set<String> keys = new HashSet<>();
//
//        config.put("xyz","http://localhost:1234/bes");
//        keys.add("http://localhost:1234/bes");
//        String key = keys.stream().findFirst().get();
//        Whitebox.setInternalState(BanksRemoteCalls.class,"config",config);
//        Mockito.when(config.keySet()).thenReturn(keys);
//        Mockito.when(config.get(key)).thenReturn(key);
//
//        HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
//
//
//        Mockito.when(connection.getInputStream()).thenReturn(null);
//        //BanksRemoteCalls banksRemoteCalls = new BanksRemoteCalls();
//        banksRemoteCalls.handle(null,null);
//    }
    @Test
    public void testHandle1() throws Exception {
        String expected = "[{\"bic\":\"PARIATURDEU0XXX\",\"name\":\"Banco de espiritu santo\",\"countryCode\":\"GB\",\"auth\":\"oauth\"}]";
        Set<String> keys = new HashSet<>();
        keys.add("http://localhost:1234/bes");
        String key = keys.stream().findFirst().get();
        Whitebox.setInternalState(BanksRemoteCalls.class,"config",config);
        Mockito.when(config.keySet()).thenReturn(keys);
        Mockito.when(config.get(key)).thenReturn(key);
        String actual = banksRemoteCalls.handle(null,null);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testHandle2() throws Exception {
        URL url = new URL("http://localhost:8080/v2/banks/all");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responseCode = huc.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);

    }
}