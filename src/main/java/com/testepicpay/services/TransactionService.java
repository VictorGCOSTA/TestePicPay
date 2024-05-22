package com.testepicpay.services;

import com.testepicpay.domain.transaction.Transaction;
import com.testepicpay.domain.user.User;
import com.testepicpay.dtos.TransactionDTO;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.testepicpay.repositories.TransactionRepository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        userService.validateTransaction(transaction.senderId(), transaction.value());
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        boolean authorized = this.authorizeTransaction(sender, transaction.value());
        if (!authorized) {
            throw new Exception("Transaction not authorized");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        return newTransaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        //This is an invalid mock url to simulate an external service call
        System.out.println("Calling external service to authorize transaction");
        return true;

    }

}
