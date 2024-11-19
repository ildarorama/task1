package com.epam.jmp.service;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    default double getAverageUsersAge() {
        LocalDate today = LocalDate.now();

        return getAllUsers().stream().map(User::getBirthday)
                .mapToDouble(bd -> ChronoUnit.YEARS.between(bd, today))
                .average().orElse(0D);
    }

    static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) > 18;
    }

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);
}
