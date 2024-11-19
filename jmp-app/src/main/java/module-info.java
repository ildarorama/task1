module jmp.app {
    uses com.epam.jmp.service.Service;
    uses com.epam.jmp.bank.Bank;
    requires jmp.cloud.service.impl;
    requires jmp.cloud.bank.impl;
    requires jmp.dto;
}