package com.example.demo.dao;

import com.example.demo.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	User getUserByEmpNum(int empNum);
	User save(User user);

}
