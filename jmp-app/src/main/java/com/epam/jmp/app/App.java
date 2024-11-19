package com.epam.jmp.app;

import com.epam.jmp.bank.Bank;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;

import java.time.LocalDate;
import java.util.ServiceLoader;

public class App {

    public static void main(String[] args) {

        var service = ServiceLoader.load(Service.class).findFirst().orElseThrow(AppException::new);

        var bank = ServiceLoader.load(Bank.class).findFirst().orElseThrow(AppException::new);

        var users = service.getAllUsers();

        var user = new User("John", "Doe", LocalDate.of(2002, 01, 20));

        var card = bank.createBankCard(user, BankCardType.CREDIT);

        service.subscribe(card);

        var foundSubscriptionOp = service.getSubscriptionByBankCardNumber(card.getNumber());

        assert foundSubscriptionOp.isPresent();

        var foundSubscription = foundSubscriptionOp.orElseThrow(SubscriptionNotFoundException::new);

        assert foundSubscription.getBankcard().equals(card.getNumber());

        var listOfSubscription = service.getAllSubscriptionsByCondition(e -> e.getBankcard().equals(card.getNumber()));

        System.out.println("Found subscriptions: " + listOfSubscription.size());
        System.out.println("Avg age: " + service.getAverageUsersAge());
        System.out.println("User count: " + users.size());
    }
}
