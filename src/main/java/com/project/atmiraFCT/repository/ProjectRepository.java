package com.project.atmiraFCT.repository;

import com.project.atmiraFCT.model.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByName(String name);

   /* List<Project> findByColaboratorProjects_ColaboratorName(String collaboratorName);*/

}




