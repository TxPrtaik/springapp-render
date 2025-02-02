package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Plan;
import com.example.demo.Repository.PlanRepo;

@Service
public class PlanServices {
@Autowired
private PlanRepo pr;
public void addPlan(Plan p) {
	pr.save(p);
}
public List<Plan> getPlans(){
	return pr.findAll();
}
public Plan getPlan(int id) {
	Optional<Plan>  op=pr.findById(id);
	return op.get();
}
public void updatePlan(Plan p,int id) {
	Plan pl=getPlan(id);
	pl.setName(p.getName());
	pl.setPrice(p.getPrice());
	pr.save(pl);
}
public void deletePlan(int id) {
	pr.deleteById(id);
	
}
}
