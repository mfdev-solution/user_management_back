package sn.mfdev.usermgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import sn.mfdev.usermgt.config.RsaKeyConfig;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfig.class)
public class UsermgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermgtApplication.class, args);
	}

}
