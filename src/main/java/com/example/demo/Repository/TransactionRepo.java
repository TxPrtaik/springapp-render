package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Member;
import com.example.demo.Model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
public List<Transaction> getTransactionByMember(Member member);
}
