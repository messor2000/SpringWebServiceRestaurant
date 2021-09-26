package epam.project.spring;

import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import epam.project.spring.filter.XSSFilter;
import epam.project.spring.repo.AppUserRepository;
import epam.project.spring.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantWebService extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebService.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(RestaurantWebService.class);
//    }

    @Bean
    public FilterRegistrationBean<XSSFilter> xssPreventFilter() {
        FilterRegistrationBean<XSSFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new XSSFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

//    @Bean
//    public UrlBasedViewResolver setupViewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setPrefix("templates/");
//        resolver.setSuffix(".html");
//        resolver.setViewClass(JstlView.class);
//        return resolver;
//    }

//    @Bean
//    public ViewResolver viewResolver() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setTemplateMode("XHTML");
//        templateResolver.setPrefix("templates/");
//        templateResolver.setSuffix(".html");
//
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(engine);
//        return viewResolver;
//    }

//    @Bean
//    public CommandLineRunner addEatPeriod(final UserService userService, final AppUserRepository userRepository) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                AppUser user = userService.findUserByLogin("test").get();
//
//                Purse purse =
//                UserRole userRole = userRoleService.findUserRoleByName("ADMIN").get();
//                user.setRole(userRole);
//                userRepository.save(user);
//            }
//        };
//    }

}
