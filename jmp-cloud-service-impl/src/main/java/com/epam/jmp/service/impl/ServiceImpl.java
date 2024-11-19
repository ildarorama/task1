package com.epam.jmp.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ServiceImpl implements Service {
    List<Subscription> subscriptions = new ArrayList<>();
    List<User> users = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        var sub = new Subscription(bankCard.getNumber(), LocalDate.now());
        users.add(bankCard.getUser());
        subscriptions.add(sub);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions.stream().filter(s -> s.getBankcard().equals(cardNumber))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.stream()
                .filter(predicate).collect(Collectors.toUnmodifiableList());
    }
}
