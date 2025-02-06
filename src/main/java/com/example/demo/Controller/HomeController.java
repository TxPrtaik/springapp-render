package com.example.demo.Controller;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Membership;
import com.example.demo.Model.Plan;
import com.example.demo.Model.Transaction;
import com.example.demo.Model.User;
import com.example.demo.Services.MemberService;
import com.example.demo.Services.MembershipService;
import com.example.demo.Services.PlanServices;
import com.example.demo.Services.TransactionService;
import com.example.demo.Services.UserService;

@RestController
public class HomeController {
	@Autowired
	private PlanServices ps;
	@Autowired
	private MemberService ms;
	@Autowired
	private MembershipService memSer;
	@Autowired
	private TransactionService tranSer;
	@Autowired
	private UserService us;
@GetMapping("/")
public String index() {
	expireMembership();
	return "hello";
}
@CrossOrigin
@PostMapping("/save-plan")
public boolean savePlan(@RequestBody Plan p) {
	ps.addPlan(p);
	return true;
}
@CrossOrigin
@GetMapping("/get-plans")
public List<Plan> getPlans(){
	expireMembership();
	return ps.getPlans();
}
@CrossOrigin
@GetMapping("/get-plan/{id}")
public Plan getPlan(@PathVariable int id) {
	expireMembership();
	return ps.getPlan(id);
}
@CrossOrigin
@PutMapping("/update-plan/{id}")
public boolean updatePlan(@RequestBody Plan p,@PathVariable int id) {
	ps.updatePlan(p, id);
	return true;
}
@CrossOrigin
@DeleteMapping("/delete-plan/{id}")
public boolean deletePlan(@PathVariable int id) {
	ps.deletePlan(id);
	return true;
}
@CrossOrigin
@PostMapping("/add-member")
public boolean addMember(@RequestBody com.example.demo.Model.Member mem) {
	LocalDate ld=LocalDate.now();
	DateTimeFormatter f=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	mem.setJoinDate(f.format(ld));
	//String date1=f.format(ld);
	mem.setMembershipStatus("deactive");

//LocalDate date=LocalDate.of(2024,6,12);
//System.out.println(ld.isAfter(date));
  ms.saveMember(mem);
	
	return true;
}
@CrossOrigin
@GetMapping("/get-members")
public List<com.example.demo.Model.Member> getMembers(){
	expireMembership();
	return ms.getMembers();
}
@CrossOrigin
@GetMapping("/get-member/{id}")
public com.example.demo.Model.Member getMember(@PathVariable int id){
	return ms.getMember(id);
}
@CrossOrigin
@PutMapping("/update-member/{id}")
public boolean updateMember(@PathVariable int id,@RequestBody com.example.demo.Model.Member m) {
	ms.updateMember(m, id);
	return true;
}
@CrossOrigin
@DeleteMapping("/delete-member/{id}")
public boolean deleteMember(@PathVariable int id) {
	
com.example.demo.Model.Member m=ms.getMember(id);
memSer.deleteMembershipByMember(m);
	ms.deleteMember(id);
	
	return true;
}
@CrossOrigin
@PostMapping("/save-membership/{mid}")
public boolean saveMembership(@RequestBody Membership m, @PathVariable int mid) {
    Plan plan=ps.getPlan(m.getPa());
    Transaction ts=new Transaction();
    com.example.demo.Model.Member mem=ms.getMember(mid);
  m.setFeeStatus((m.getFees().equals(m.getPaidFees()))?"Paid":"Pending");
    ts.setAmount( Long.parseLong(m.getPaidFees().toString()));
    ts.setMember(mem);
    ts.setPlan(plan.getName());
    LocalDate ld=LocalDate.now();
    DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ts.setDate(f.format(ld));
    ms.updateMembershipStatus(mid);
    m.setStartDate(f.format(ld));
    
    m.setM(mem);
    m.setP(plan);
    m.setActiveStatus("active");
    memSer.saveMembership(m);
    tranSer.saveTransaction(ts);
	
	return true;
}
@CrossOrigin
@GetMapping("/get-membership-info/{mid}")
public List<Membership> getMembershipByMember(@PathVariable int mid){
	expireMembership();
com.example.demo.Model.Member m=ms.getMember(mid);

	return memSer.getMembershipListByM(m);
}
@CrossOrigin
@GetMapping("/get-member-transactions/{id}")
public List<Transaction> getMemberTransactions(@PathVariable int id){
	com.example.demo.Model.Member m=ms.getMember(id);
return tranSer.getTrnasactionOfMember(m);
}
@CrossOrigin
@GetMapping("/get-membership/{id}")
public Membership getMembershipById(@PathVariable int id) {
	return memSer.getMembershipById(id);
}
@CrossOrigin
@GetMapping("/add-transaction/{mid}")
public boolean addTransaction(@PathVariable int mid,@RequestParam int amt,@RequestParam int pid) {
Membership mem=memSer.getMembershipById(pid);
LocalDate ld=LocalDate.now();
DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd");
Transaction tn=new Transaction();
tn.setAmount(amt);
tn.setDate(f.format(ld));
tn.setPlan(mem.getP().getName());
com.example.demo.Model.Member m=ms.getMember(mid);

tn.setMember(m);
	memSer.updatePayment(pid, amt);
	
tranSer.saveTransaction(tn);


	return true;
}
public void expireMembership() {
	List<Membership> ml=memSer.getMemberships();

	for(Membership m:ml) {
		if(m.getActiveStatus().equals("active")) {
		
		String date1=m.getEndDate();
		LocalDate date=LocalDate.of( Integer.parseInt(date1.subSequence(0, 4).toString()),Integer.parseInt(date1.subSequence(5, 7).toString()),Integer.parseInt(date1.subSequence(8, 10).toString()));
        LocalDate curDate=LocalDate.now();
        DateTimeFormatter f=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if(curDate.isAfter(date)||f.format(curDate).equals(f.format(date))) {
        	memSer.expireMembership(m.getId());
        }
		}
        
        
	}
}
@CrossOrigin
@GetMapping("/get-payment/{id}")
public Transaction getPayment(@PathVariable int id) {
	return tranSer.getTransaction(id);
}
@CrossOrigin
@GetMapping("/get-memberships")
public List<Membership> getMemberships(){
	expireMembership();
	List<Membership> activeM=new ArrayList<Membership>();
	for(Membership m:memSer.getMemberships()) {
		if(m.getActiveStatus().equals("active")) {
		activeM.add(m);
		}
		}
	return activeM;
}
@CrossOrigin
@GetMapping("/deactive-members")
public List<com.example.demo.Model.Member> getDeactiveMembers(){
	expireMembership();
	List<com.example.demo.Model.Member> ml=new ArrayList<com.example.demo.Model.Member>();
	for(com.example.demo.Model.Member m:ms.getMembers()) {
		if(m.getMembershipStatus().equals("deactive")) {
			ml.add(m);
		}
	}
return ml;
}
@CrossOrigin
@PostMapping("/login-user")
public int userLogin(@RequestBody User u) {
int i=us.findUser(u);
if(i<=0) {
	return 0; 
}
else {
	return i;
}
}
@CrossOrigin
@PutMapping("/chnage-plan/{id}")
public boolean changeMembership(@RequestBody Membership m,@PathVariable int id) {
	Plan p=ps.getPlan(m.getPa());
	m.setP(p);
memSer.changeMembership(id, m);

	
	return true;
}

}
