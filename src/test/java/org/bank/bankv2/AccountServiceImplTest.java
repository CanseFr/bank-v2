//package org.bank.bankv2;
//
//import org.bank.bankv2.models.OverAccount;
//import org.bank.bankv2.repositories.OverAccountRepository;
//import org.bank.bankv2.services.OverAccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class AccountServiceImplTest {
//
//    @Mock
//    private OverAccountRepository overAccountRepository;
//
//    @InjectMocks
//    private OverAccountService overAccountService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testDebitWithSufficientFunds() {
//        // Setup
//        Integer idAccount = 1;
//        Float amount = 500f;
//        OverAccount overaccount = new OverAccount();
//        overaccount.setSolde(1000f);
//        when(overAccountRepository.findById(idAccount)).thenReturn(java.util.Optional.of(overaccount));
//
//        // Test
//        Float newBalance = overAccountService.debitByUserId(amount, idAccount);
//
//        // Verify
//        assertEquals(500f, newBalance);
//        verify(overAccountRepository, times(1)).findById(idAccount);
//        verify(overAccountRepository, times(1)).save(overaccount);
//    }
//
//    @Test
//    public void testDebitWithInsufficientFunds() {
//        // Setup
//        Integer idAccount = 1;
//        Float amount = 1500f;
//        OverAccount overaccount = new OverAccount();
//        overaccount.setSolde(1000f);
//        when(overAccountRepository.findById(idAccount)).thenReturn(java.util.Optional.of(overaccount));
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> overAccountService.debitByUserId(amount, idAccount));
//        assertEquals("Fond insuffisant", exception.getMessage());
//        verify(overAccountRepository, times(1)).findById(idAccount);
//        verify(overAccountRepository, never()).save(any());
//    }
//}
//
//
