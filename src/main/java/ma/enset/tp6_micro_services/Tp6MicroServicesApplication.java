package ma.enset.tp6_micro_services;

import ma.enset.tp6_micro_services.entities.AccountType;
import ma.enset.tp6_micro_services.entities.BankAccount;
import ma.enset.tp6_micro_services.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class Tp6MicroServicesApplication {
    private final BankAccountRepository bankAccountRepository;

    public Tp6MicroServicesApplication(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Tp6MicroServicesApplication.class, args);
    }
    @Bean
    CommandLineRunner start(){
        return (args -> {
            for (int i = 0; i < 10; i++) {
                BankAccount bankAccount= BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                        .balance(100+Math.random()*90000)
                        .createDate(new Date())
                        .currency("MAD")
                        .build();
                bankAccountRepository.save(bankAccount);

            }
        });
    }
}
