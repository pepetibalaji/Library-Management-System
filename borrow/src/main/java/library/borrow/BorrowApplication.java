package library.borrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import jakarta.transaction.Transactional;
import lombok.Builder;

@Transactional
@SpringBootApplication
@Builder
@EnableFeignClients(basePackages = "library.borrow.api")
public class BorrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowApplication.class, args);
	}

}
