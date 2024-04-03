package Budget.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

/*	@Configuration
	public static class DataSourceConfig {
		@Bean
		public DataSource getDataSource() {
			return DataSourceBuilder.create()
					.driverClassName("com.mysql.cj.jdbc.Driver")
					.url("jdbc:mysql://localhost:3306/userLogin")
					.username("root")
					.password("")
					.build();
		}
	}*/
}
