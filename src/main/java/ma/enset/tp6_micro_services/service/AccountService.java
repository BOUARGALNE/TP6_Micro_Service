package ma.enset.tp6_micro_services.service;

import ma.enset.tp6_micro_services.dto.BankAccountResponseDTO;
import ma.enset.tp6_micro_services.dto.BankAccountRequestDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
