package slovenskoit.zadanie.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import slovenskoit.zadanie.service.customer.CustomerService;
import slovenskoit.zadanie.service.customer.CustomerServiceJPA;
import slovenskoit.zadanie.service.device.DeviceService;
import slovenskoit.zadanie.service.device.DeviceServiceJPA;
import slovenskoit.zadanie.service.diskSpace.DiskSpaceService;
import slovenskoit.zadanie.service.diskSpace.DiskSpaceServiceJPA;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = "slovenskoit.zadanie.entity")
public class serverConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(serverConfiguration.class, args);
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceJPA();
    }

    @Bean
    public DiskSpaceService diskSpaceService() {
        return new DiskSpaceServiceJPA();
    }

    @Bean
    public DeviceService deviceService() { return new DeviceServiceJPA(); }

}
