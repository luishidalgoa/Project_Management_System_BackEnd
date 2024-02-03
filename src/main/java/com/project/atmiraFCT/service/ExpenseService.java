package com.project.atmiraFCT.service;

import com.project.atmiraFCT.exception.RecordNotFoundException;
import com.project.atmiraFCT.model.domain.Colaborator;
import com.project.atmiraFCT.model.domain.Expense;
import com.project.atmiraFCT.model.domain.Project;
import com.project.atmiraFCT.model.domain.Task;
import com.project.atmiraFCT.repository.ColaboratorRepository;
import com.project.atmiraFCT.repository.ExpenseRepository;
import com.project.atmiraFCT.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ColaboratorRepository colaboratorRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Expense saveExpenseExistingProyectColaborator(Integer day, Integer month, Integer year, Integer hours, Integer cost,
                                                         String description, Boolean state, String colaboratorId, Long projectId) {
        Optional<Colaborator> colaboratorOptional = colaboratorRepository.findById(colaboratorId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (colaboratorOptional.isPresent() && projectOptional.isPresent()) {
            Expense expense = new Expense();
            expense.setDay(day);
            expense.setMonth(month);
            expense.setYear(year);
            expense.setHours(hours);
            expense.setCost(cost);
            expense.setDescription(description);
            expense.setState(state);
            expense.setColaborator(colaboratorOptional.get());
            expense.setProject(projectOptional.get());

            Expense savedExpense = expenseRepository.save(expense);
            return savedExpense;
        } else {
            throw new RecordNotFoundException("Colaborator or project not found");
        }
    }

    public void deleteExpense(Integer id) {
        Optional<Expense> result =expenseRepository.findById(id);
        if (result.isPresent()) {
            expenseRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No task found with id: " + id);
        }
    }

    public List<Expense> getExpenseByColaborator(String colaboratorId) {
        Optional<Colaborator> colaboratorOptional = colaboratorRepository.findById(colaboratorId);
        if (colaboratorOptional.isPresent()) {
            return expenseRepository.findByColaborator(colaboratorOptional.get());
        } else {
            throw new RecordNotFoundException("Colaborator not found with id: " + colaboratorId);
        }
    }

    public List<Expense> getExpenseByProject(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            return expenseRepository.findByProject(projectOptional.get());
        } else {
            throw new RecordNotFoundException("Project not found with id: " + projectId);
        }
    }

    public List<Expense> getExpenseByColaboratorAndProject(String colaboratorId, Long projectId) {
        Optional<Colaborator> colaboratorOptional = colaboratorRepository.findById(colaboratorId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (colaboratorOptional.isPresent() && projectOptional.isPresent()) {
            return expenseRepository.findByColaboratorAndProject(colaboratorOptional.get(), projectOptional.get());
        } else {
            throw new RecordNotFoundException("Colaborator or project not found");
        }
    }

}
