package ru.vyukov.myappposition.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import lombok.extern.slf4j.Slf4j;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

/**
 * @author Oleg Vyukov
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8080")
@ActiveProfiles("mvc-test")
@ContextConfiguration(classes = SelenideTest.Config.class)
public abstract class SelenideTest {

    static {
        //use "-Dselenide.browser=firefox" for override in command line
        Properties props = System.getProperties();
        props.setProperty("browser", "chrome");

    }

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @Autowired
    protected User demoUser;


    @TestConfiguration
    public static class Config extends WebSecurityConfigurerAdapter {

        private final String username = "demo_user";


        public Config(@Value("${server.port}") int port, @Value("${tests.hostname}") String hostname) {
            Configuration.baseUrl = String.format("http://%s:%s", hostname, port);


            log.info("SELENIDE: baseUrl = " + Configuration.baseUrl);
            log.info("SELENIDE: browser = " + Configuration.browser);
            log.info("SELENIDE: remote = " + Configuration.remote);
        }


        @Bean
        UserDetails demoUser() {
            return User.withUsername(username).password("qwerty").authorities("USER").build();
        }

        @Bean
        UserDetailsService mockUserDetailsService() {
            return (username) -> demoUser();
        }

        @Bean
        MockUserFilter mockUserFilter() {
            return new MockUserFilter(demoUser());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.addFilterBefore(
                    mockUserFilter(), BasicAuthenticationFilter.class);
        }
    }
}
