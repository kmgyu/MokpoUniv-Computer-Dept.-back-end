package org.mokpouniv.computerDept_backend.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
        info = @Info(title = "Mokpo Univ ComputerDept. Back-end API",
            description = "목포대학교 컴퓨터학부 API 명세서",
            version = "v0.1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig{


    @Bean
    public GroupedOpenApi SampleOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Sample v0.1")
                .pathsToMatch(paths)
                .build();
    }


     /*
    Docket: Swagger 설정의 핵심이 되는 Bean
    useDefaultResponseMessages: Swagger 에서 제공해주는 기본 응답 코드 (200, 401, 403, 404). false 로 설정하면 기본 응답 코드를 노출하지 않음
    apis: api 스펙이 작성되어 있는 패키지 (Controller) 를 지정
    paths: apis 에 있는 API 중 특정 path 를 선택
    apiInfo:Swagger UI 로 노출할 정보
    */

//    private static final String SERVICE_NAME = "Make Project";
//    private static final String API_VERSION = "V1";
//    private static final String API_DESCRIPTION = "MakeProject API TEST";
//    private static final String API_URL = "http://localhost:8080/";
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any()) // RequestMapping의 모든 URL LIST
//                .paths(PathSelectors.any()) // .any() -> ant(/api/**") /api/**인 URL만 표시
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title(SERVICE_NAME) // 서비스명
//                .version(API_VERSION)                   // API 버전
//                .description(API_DESCRIPTION)           // API 설명
//                .termsOfServiceUrl(API_URL)             // 서비스 url
//                .licenseUrl("라이센스 표시할 url")
//                .build();
//
//    }// API INFO
//
//    // 아래 부분은 WebMvcConfigure 를 상속받아서 설정하는 Mehtod
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        // -- Static resources
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//
//    }
}
