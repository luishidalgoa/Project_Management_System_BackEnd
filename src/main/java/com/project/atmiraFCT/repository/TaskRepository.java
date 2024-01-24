package com.project.atmiraFCT.repository;

import com.project.atmiraFCT.model.Project;
import com.project.atmiraFCT.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
