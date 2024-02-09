package org.bank.bankv2;

import lombok.AllArgsConstructor;
import org.bank.bankv2.interceptors.RequestBodyInterceptor;
import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.services.BankService;
import org.bank.bankv2.services.Impl.BankServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@AllArgsConstructor
public class BankV2Application implements WebMvcConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(BankV2Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestBodyInterceptor());
    }

}
