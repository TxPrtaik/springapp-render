package com.example.demo.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Member;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.TransactionRepo;

@Service
public class TransactionService {
@Autowired
private TransactionRepo tr;
public void saveTransaction(Transaction tn) {
	tr.save(tn);
}
public List<Transaction> getTrnasactionOfMember(Member member){
	return tr.getTransactionByMember(member);
}
public Transaction getTransaction(int id) {
	Optional<Transaction> ot=tr.findById(id);
	return ot.get();
}
public void deleteTransactionsByMember(Member m) {
 List<Transaction> ml= tr.getTransactionByMember(m);
 for(Transaction t:ml) {
	 tr.deleteById(t.getId());
 }
}
}
