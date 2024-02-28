package com.Signup.Signup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Signup.Signup.models.ComplaintModel;
import com.Signup.Signup.repositories.ComplaintRepository;

@Service
public class ComplaintService {

	private final ComplaintRepository complaintRepository;

	@Autowired
	public ComplaintService(ComplaintRepository complaintRepository) {
		this.complaintRepository = complaintRepository;
	}

	public void saveComplaint(ComplaintModel complaint) {
		complaintRepository.save(complaint);
	}

	public List<ComplaintModel> getComplaintsByUserLogin(String userLogin) {
		return complaintRepository.findByUserLogin(userLogin);
	}

	public ComplaintModel getComplaintById(Integer id) {
		Optional<ComplaintModel> complaintOptional = complaintRepository.findById(id);
		return complaintOptional.orElse(null); // Return null if complaint is not found
	}

	

	public ComplaintModel updateComplaint(Integer complaintId, String userLogin, String phone, String city,
			String pincode, String address, String incident, String place, String complaintFor, String date,
			String victimName, String additionalInfo) {
		Optional<ComplaintModel> optionalComplaint = complaintRepository.findById(complaintId);
		if (optionalComplaint.isPresent()) {
			ComplaintModel complaint = optionalComplaint.get();
			complaint.setUserLogin(userLogin);
			complaint.setPhone(phone);
			complaint.setCity(city);
			complaint.setPincode(pincode);
			complaint.setAddress(address);
			complaint.setIncident(incident);
			complaint.setPlace(place);
			complaint.setComplaintFor(complaintFor);
			complaint.setDate(date);
			complaint.setVictimName(victimName);
			complaint.setAdditionalInfo(additionalInfo);
			return complaintRepository.save(complaint);
		}
		return null; // Or throw an exception if needed
	}
	public void deleteComplaintById(Integer id) {
        complaintRepository.deleteById(id);
    }
	
	 public List<ComplaintModel> getAllComplaints() {
	        
	        return complaintRepository.findAll();
	    }
	 
	 public void updateStatus(Integer complaintId, String newStatus) {
		    System.out.println("New Status: " + newStatus); // Print newStatus
		    Optional<ComplaintModel> optionalComplaint = complaintRepository.findById(complaintId);
		    if (optionalComplaint.isPresent()) {
		        ComplaintModel complaint = optionalComplaint.get();
		        complaint.setStatus(newStatus);
		        complaintRepository.save(complaint);
		    }
		}

	 /*public void updateStatus(Integer complaintId, String newStatus) {
	        Optional<ComplaintModel> optionalComplaint = complaintRepository.findById(complaintId);
	        if (optionalComplaint.isPresent()) {
	            ComplaintModel complaint = optionalComplaint.get();
	            complaint.setStatus(newStatus);
	            complaintRepository.save(complaint);
	        }
}*/
}
