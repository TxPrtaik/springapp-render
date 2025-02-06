package com.example.demo.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Member;
import com.example.demo.Model.Membership;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.MembershipRepo;

@Service
public class MembershipService {
@Autowired
private MembershipRepo mr;
@Autowired MemberService ms;
@Autowired
private TransactionService ts;
public void saveMembership(Membership ms) {
	mr.save(ms);
}
public List<Membership> getMembershipListByM(Member m){

	
	
	return mr.getMembershipBym(m);
}
public Membership getMembershipById(int id) {
	Optional<Membership> om=mr.findById(id);
	return om.get();
}
public void updatePayment(int mid,int amt) {
	Membership me=getMembershipById(mid);
	me.setPaidFees((Integer.parseInt(me.getPaidFees())+amt)+"");
	if(me.getFees().equals(me.getPaidFees())) {
		me.setFeeStatus("Paid");
	}
    mr.save(me);
	
}
public List<Membership> getMemberships(){
	return mr.findAll();
}
public void expireMembership(int id) {
	Membership m=getMembershipById(id);
	m.setActiveStatus("deactive");
     ms.deactiveMembershipStatus(m.getM().getId());
     mr.save(m);
	
}
public void changeMembership(int id,Membership m) {
Membership mem=getMembershipById(id);
mem.setP(m.getP());
mem.setFees(m.getFees());
mem.setPa(m.getPa());

mem.setPaidFees((Integer.parseInt(mem.getPaidFees())+Integer.parseInt( m.getPaidFees()))+"");
mem.setFeeStatus((mem.getFees().equals(mem.getPaidFees()))?"Paid":"Pending");
mem.setEndDate(m.getEndDate());
Transaction tn=new Transaction();
if(!(m.getPaidFees().equals("0"))) {
	tn.setAmount(Integer.parseInt(m.getPaidFees()));
	LocalDate ld=LocalDate.now();
	DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	tn.setDate(f.format(ld));
	tn.setMember(mem.getM());
	tn.setPlan(m.getP().getName());
ts.saveTransaction(tn);
}

mr.save(mem);
}

public void deleteMembershipByMember(Member mem) {
List<Membership> ms=getMembershipListByM(mem);
for(Membership m:ms) {
	mr.deleteById(m.getId());
}
}
}
