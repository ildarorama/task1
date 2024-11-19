package com.epam.jmp.bank.impl;

import com.epam.jmp.bank.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;

import java.util.UUID;

public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        return switch (cardType) {
            case CREDIT -> new CreditBankCard(UUID.randomUUID().toString(), user, 0);
            case DEBIT -> new DebitBankCard(UUID.randomUUID().toString(), user, 0);
        };
    }
}
