package com.example.demo.Repository;

import java.lang.reflect.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<com.example.demo.Model.Member, Integer> {

}
