package kz.shop.test.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties",
        "classpath:test.properties"
})
public interface WebDriverConfig extends Config  {

    @Key("baseUrl")
    @DefaultValue("https://shop.kz")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("pageLoadTimeout")
    @DefaultValue("15000")
    Long getPageLoadTimeout();

    @Key("userLogin")
    String getUserLogin();

    @Key("userPassword")
    String userPassword();

    @Key("unregisteredUserLogin")
    String unregisteredUserLogin();

    @Key("unregisteredUserPassword")
    String unregisteredUserPassword();


}
