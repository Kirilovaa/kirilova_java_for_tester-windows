package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("46.242.11.185");
        String geoIPcountry = null;
        if (geoIP.contains("RU")) {
            geoIPcountry = "RU";
        }
        Assert.assertEquals(geoIPcountry, "RU");
    }
}
