package cotato.backend.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI CotatoAPI() {
		Info info = new Info()
			.title("Cotato 12th BE API")
			.description("코테이토 12기 백엔드 네트워킹 과제 API 명세서")
			.version("1.0.0");

		String jwtSchemeName = "JWT TOKEN";
		// API 요청헤더에 인증정보 포함
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
		// SecuritySchemes 등록
		Components components = new Components()
			.addSecuritySchemes(jwtSchemeName, new SecurityScheme()
				.name(jwtSchemeName)
				.type(SecurityScheme.Type.HTTP) // HTTP 방식
				.scheme("bearer")
				.bearerFormat("JWT"));

		return new OpenAPI()
			.addServersItem(new Server().url("/"))
			.info(info)
			.addSecurityItem(securityRequirement)
			.components(components);
	}
}