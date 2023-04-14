package ma.enset.tp6_micro_services.service;

import ma.enset.tp6_micro_services.dto.BankAccountRequestDTO;
import ma.enset.tp6_micro_services.dto.BankAccountResponseDTO;
import ma.enset.tp6_micro_services.entities.BankAccount;
import ma.enset.tp6_micro_services.mappers.AccountMapper;
import ma.enset.tp6_micro_services.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;


@Service
@Transactional

public class AccountServiceImpl implements AccountService {
   @Autowired
    private  BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;
    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount= BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .type(bankAccountDTO.getType())
                .balance(bankAccountDTO.getBalance())
                .createDate(new Date())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccont(saveBankAccount);
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount= BankAccount.builder()
                .id(id)
                .type(bankAccountDTO.getType())
                .balance(bankAccountDTO.getBalance())
                .createDate(new Date())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccont(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
