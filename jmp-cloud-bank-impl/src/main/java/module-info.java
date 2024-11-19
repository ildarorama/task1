import com.epam.jmp.bank.impl.BankImpl;
import com.epam.jmp.bank.Bank;

module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    provides Bank with BankImpl;
}
