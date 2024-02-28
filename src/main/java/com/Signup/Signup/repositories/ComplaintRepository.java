package com.Signup.Signup.repositories;

import java.util.List;
import java.util.Optional;

//ComplaintRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Signup.Signup.models.ComplaintModel;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintModel, Integer> {
	List<ComplaintModel> findByUserLogin(String userLogin);
	Optional<ComplaintModel> findById(Integer id);
	void deleteById(Integer id);
 
}
