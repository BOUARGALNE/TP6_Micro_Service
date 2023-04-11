package ma.enset.tp6_micro_services.web;

import ma.enset.tp6_micro_services.dto.BankAccountRequestDTO;
import ma.enset.tp6_micro_services.dto.BankAccountResponseDTO;
import ma.enset.tp6_micro_services.entities.BankAccount;
import ma.enset.tp6_micro_services.mappers.AccountMapper;
import ma.enset.tp6_micro_services.repositories.BankAccountRepository;
import ma.enset.tp6_micro_services.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;
    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper){
        this.bankAccountRepository=bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account avec id %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
           return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
          BankAccount account=bankAccountRepository.findById(id).orElseThrow();
          if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
          if(bankAccount.getCreateDate()!=null)  account.setCreateDate(new Date());
          if(bankAccount.getType()!=null)  account.setType(bankAccount.getType());
          if(bankAccount.getCurrency()!=null)  account.setCurrency(bankAccount.getCurrency());
           return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void BankAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
