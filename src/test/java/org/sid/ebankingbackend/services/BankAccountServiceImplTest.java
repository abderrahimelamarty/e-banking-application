package org.sid.ebankingbackend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sid.ebankingbackend.dtos.CustomerDTO;
import org.sid.ebankingbackend.dtos.CurrentBankAccountDTO;
import org.sid.ebankingbackend.dtos.SavingBankAccountDTO;
import org.sid.ebankingbackend.entities.*;
import org.sid.ebankingbackend.exceptions.CustomerNotFoundException;
import org.sid.ebankingbackend.mappers.BankAccountMapperImpl;
import org.sid.ebankingbackend.repositories.AccountOperationRepository;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.sid.ebankingbackend.repositories.CustomerRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private AccountOperationRepository accountOperationRepository;

    @Mock
    private BankAccountMapperImpl dtoMapper;

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    private CustomerDTO customerDTO;
    private Customer customer;
    private CurrentBankAccountDTO currentBankAccountDTO;
    private SavingBankAccountDTO savingBankAccountDTO;

    @BeforeEach
    void init() {
        // Initialize sample data for testing
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setName("John Doe");

        currentBankAccountDTO = new CurrentBankAccountDTO();
        currentBankAccountDTO.setId(UUID.randomUUID().toString());
        currentBankAccountDTO.setBalance(1000.0);

        savingBankAccountDTO = new SavingBankAccountDTO();
        savingBankAccountDTO.setId(UUID.randomUUID().toString());
        savingBankAccountDTO.setBalance(2000.0);
    }

    @Test
    void saveCustomer() {
        // Mocking repository behavior
        when(dtoMapper.fromCustomerDTO(any(CustomerDTO.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Test service method
        CustomerDTO savedCustomerDTO = bankAccountService.saveCustomer(customerDTO);

        // Assertions
        assertNotNull(savedCustomerDTO);
        // Add more assertions based on your actual implementation
    }

    @Test
    void saveCurrentBankAccount() throws CustomerNotFoundException {
        // Mocking repository behavior
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Customer()));
        when(bankAccountRepository.save(any(CurrentAccount.class))).thenReturn(new CurrentAccount());

        // Test service method
        CurrentBankAccountDTO savedAccountDTO = bankAccountService.saveCurrentBankAccount(1000.0, 500.0, 1L);

        // Assertions
        assertNotNull(savedAccountDTO);
        // Add more assertions based on your actual implementation
    }
    @Test
    void saveSavingBankAccount() throws CustomerNotFoundException {
        // Mocking repository behavior
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Customer()));
        when(bankAccountRepository.save(any(SavingAccount.class))).thenReturn(new SavingAccount());

        // Test service method
        SavingBankAccountDTO savedAccountDTO = bankAccountService.saveSavingBankAccount(2000.0, 0.05, 1L);

        // Assertions
        assertNotNull(savedAccountDTO);
        // Add more assertions based on your actual implementation
    }

    // Add more tests for other methods in BankAccountServiceImpl
}
