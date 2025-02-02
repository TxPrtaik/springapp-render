package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Member;
import com.example.demo.Model.Membership;

@Repository
public interface MembershipRepo extends JpaRepository<Membership, Integer> {
	List<Membership> getMembershipBym(Member member);

}
