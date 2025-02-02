package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Member;
import com.example.demo.Model.Membership;
import com.example.demo.Repository.MembershipRepo;

@Service
public class MembershipService {
@Autowired
private MembershipRepo mr;
@Autowired MemberService ms;
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
}
