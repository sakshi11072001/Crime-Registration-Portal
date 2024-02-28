package com.Signup.Signup.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Signup.Signup.models.ComplaintModel;
import com.Signup.Signup.models.UserModel;
import com.Signup.Signup.services.ComplaintService;
import com.Signup.Signup.services.MyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	@Autowired
	private final MyService myService;

	@Autowired
	private final ComplaintService complaintService;
	
	
	@Autowired
	public MyController(MyService myService, ComplaintService complaintService) {
	    this.myService = myService;
	    this.complaintService = complaintService;
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new UserModel());
		return "register_page.html";
	}

	@GetMapping("/login")
	public String getloginPage(Model model) {
		model.addAttribute("loginRequest", new UserModel());
		return "login_page.html";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute UserModel userModel) {
		System.out.println("Register request:" + userModel);
		UserModel registeredUser = myService.registerUser(userModel.getLogin(), userModel.getPassword(),
				userModel.getEmail());
		return registeredUser == null ? "error_page.html" : "redirect:/login";

	}

	@PostMapping("/login")
	public String logins(@ModelAttribute UserModel userModel,Model model,HttpSession session) {
		System.out.println("login request:" + userModel);
		UserModel authenticated = myService.authentication(userModel.getLogin(), userModel.getPassword());
		if (authenticated != null) {
			session.setAttribute("userLogin", authenticated.getLogin());
			model.addAttribute("userLogin",authenticated.getLogin());
			model.addAttribute("message",authenticated.getLogin());
			return "dashboard.html";
		} else {
			return "error_page.html";
		}

	}




	
	@GetMapping("/fileComplaint")
	public String fileComplaint(Model model) {
		model.addAttribute("registerRequest", new ComplaintModel());
		return "fileComplaint.html";
	}
	
	
	
	@PostMapping("/fileComplaint")
	public String fileComplaint(@ModelAttribute ComplaintModel complaintModel,Model model, HttpSession session) {
		 String userLogin = (String) session.getAttribute("userLogin"); // Retrieve userLogin from session
	        
	        // Set userLogin in complaintModel
	        complaintModel.setUserLogin(userLogin);
        System.out.println("UserName is "+ userLogin);
        
		complaintService.saveComplaint(complaintModel);
        model.addAttribute("message", "Complaint submitted successfully!");
        return "complaint_sucess.html";

	}
	
	
	@GetMapping("/fetchComplaints")
	public String fetchComplaints(Model model, String userLogin,HttpSession session) {
		String userLogins = (String) session.getAttribute("userLogin"); // Retrieve userLogin from session
		 List<ComplaintModel> complaints = complaintService.getComplaintsByUserLogin(userLogins);
		    for (ComplaintModel complaint : complaints) {
		        System.out.println(complaint.toString());
		    }
	        model.addAttribute("complaints", complaints);
	        
		return "User_Complaints.html";
		
		
		
	}
	
	@GetMapping("/updateComplaint")
	public String updateComplaint(@RequestParam("id") Integer id, Model model) {
	    // Retrieve the complaint object from the database based on the provided ID
	    ComplaintModel complaint = complaintService.getComplaintById(id);
	    
	    // Add the complaint object to the model
	    model.addAttribute("complaint", complaint);
	    
	    // Return the updateComplaint.html view
	    return "updateComplaint.html";
	}

	
	@PostMapping("/updateMyComplaint")
	public String handleUpdateComplaint(@RequestParam("complaintId") Integer complaintId,
	                                    @RequestParam("userLogin") String userLogin,
	                                    @RequestParam("phone") String phone,
	                                    @RequestParam("city") String city,
	                                    @RequestParam("pincode") String pincode,
	                                    @RequestParam("address") String address,
	                                    @RequestParam("incident") String incident,
	                                    @RequestParam("place") String place,
	                                    @RequestParam("complaintFor") String complaintFor,
	                                    @RequestParam("date") String date,
	                                    @RequestParam("victimName") String victimName,
	                                    @RequestParam("additionalInfo") String additionalInfo) {
	    complaintService.updateComplaint(complaintId, userLogin, phone, city, pincode, address, incident,
	                                     place, complaintFor, date, victimName, additionalInfo);
	    return "update_sucess.html";
	}
	
	
	
	@GetMapping("/deleteComplaint")
	public String deleteComplaint(Model model, String userLogin,HttpSession session) {
		String userLogins = (String) session.getAttribute("userLogin"); 
		// Retrieve userLogin from session
		 List<ComplaintModel> complaints = complaintService.getComplaintsByUserLogin(userLogins);
	        model.addAttribute("complaints", complaints);
	        
		return "deleteComplaint.html";		
	}
	@GetMapping("/deleteComplaints")
	public String deleteComplaint(@RequestParam("id") Integer id) {
	    complaintService.deleteComplaintById(id);
	    return "deleted_sucess.html"; // Redirect to a confirmation page or any other page
	}
	
	@GetMapping("/admin")
	public String login_admin() {
		return "login_admin.html";
	}
	
	@PostMapping("login_admin")
    public String adminLogin(@RequestParam("userLogin") String userLogin,
                             @RequestParam("password") String password) {
        if (userLogin.equals("administrator") && password.equals("1234567")) {
            return "admin_welcome.html";
        } else {
            return "error_page.html";
        }
    }
	@GetMapping("/complaints")
	public String showComplaintsPage(Model model) {
	    // Retrieve all complaints from the service
	    List<ComplaintModel> complaints = complaintService.getAllComplaints();

	    // Add the complaints to the model to pass them to the view
	    model.addAttribute("complaints", complaints);

	    // Return the name of the view to render (e.g., "admin.html")
	    return "admin.html";
	}

	
	@PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("complaintId") Integer complaintId,
                               @RequestParam("newStatus") String newStatus) {
        complaintService.updateStatus(complaintId, newStatus);
        return "admin_welcome.html"; 
    }
	
	
	
	@GetMapping("/checkStatus")
	public String CheckComplaintStatus(Model model, String userLogin,HttpSession session) {
		String userLogins = (String) session.getAttribute("userLogin"); // Retrieve userLogin from session
		 List<ComplaintModel> complaints = complaintService.getComplaintsByUserLogin(userLogins);
		    for (ComplaintModel complaint : complaints) {
		        System.out.println(complaint.toString());
		    }
	        model.addAttribute("complaints", complaints);
	        
		return "checkComplaint_status.html";
		
		
		
	}
}
