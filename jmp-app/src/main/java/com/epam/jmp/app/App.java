package com.epam.jmp.app;

import com.epam.jmp.bank.Bank;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;

import java.time.LocalDate;
import java.util.ServiceLoader;
import java.util.function.Predicate;

public class App {

    public static void main(String[] args) {

        var service = ServiceLoader.load(Service.class).findFirst().orElseThrow(AppException::new);

        var cardService = ServiceLoader.load(Bank.class).findFirst().orElseThrow(AppException::new);

        var user = new User("User name", "User surname", LocalDate.now().minusYears(20));

        var card = cardService.createBankCard(user, BankCardType.CREDIT);

        var userYoung = new User("Young user name", "Young user surname", LocalDate.now().minusYears(15));

        var cardYoung = cardService.createBankCard(userYoung, BankCardType.CREDIT);

        service.subscribe(card);
        service.subscribe(cardYoung);

        Predicate<Subscription> filter = s -> s.getBankcard().equals(card.getNumber());

        var foundCards = service.getAllSubscriptionsByCondition(filter);
        System.out.println("Found cards: " + foundCards.size());

        var cardByNumber = service.getSubscriptionByBankCardNumber(card.getNumber())
                .orElseThrow(SubscriptionNotFoundException::new);
        System.out.println("Found cards with number: " + cardByNumber.getBankcard());

        System.out.println("Average user age " + service.getAverageUsersAge());


        for (User u : service.getAllUsers()) {
            System.out.print("User " + u.getName() + " is: ");
            System.out.println(Service.isPayableUser(u) ? "payable" : "non-payable");
        }

        System.out.println("All cards:");
        service.getAllUsers().stream().map(User::getName).forEach(System.out::println);
    }
}
