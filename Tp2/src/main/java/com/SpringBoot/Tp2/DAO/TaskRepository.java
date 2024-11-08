package com.SpringBoot.Tp2.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.Tp2.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
