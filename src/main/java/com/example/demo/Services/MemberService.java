package com.example.demo.Services;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Membership;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.MemberRepo;

@Service
public class MemberService {
@Autowired
private MemberRepo mr;
@Autowired
private TransactionService ts;


public void saveMember(com.example.demo.Model.Member m) {
	mr.save(m);
}
public List<com.example.demo.Model.Member> getMembers(){
	return mr.findAll();
}
public com.example.demo.Model.Member getMember(int id){
	Optional<com.example.demo.Model.Member> om=mr.findById(id);
	return om.get();
}
public void updateMember(com.example.demo.Model.Member m,int id) {
	com.example.demo.Model.Member mem=getMember(id);
	mem.setName(m.getName());
	mem.setContact(m.getContact());
	mem.setAddress(m.getAddress());
	mr.save(mem);
	
}
public void deleteMember(int id) {
	com.example.demo.Model.Member mem=getMember(id);
	ts.deleteTransactionsByMember(mem);
	
	mr.deleteById(id);
}
public void updateMembershipStatus(int id) {
	com.example.demo.Model.Member mem=getMember(id);
	mem.setMembershipStatus("active");
	mr.save(mem);
}
public void deactiveMembershipStatus(int id) {
	com.example.demo.Model.Member m=getMember(id);
	m.setMembershipStatus("deactive");
	mr.save(m);
	
}
}

