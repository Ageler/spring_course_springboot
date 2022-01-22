package com.andrewborchenko.spring.springboot.spring_course_springboot.dao;

import com.andrewborchenko.spring.springboot.spring_course_springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {


    private EntityManager entityManager;

    // не находит так как ранее в MyConfig создавали sessionFactory bean
    /*@Autowired
    public void EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        // аналогия Session session = sessionFactory.getCurrentSession();
       /* Session session = entityManager.unwrap(Session.class);
        List<Employee> employees = session
                .createQuery("from Employee", Employee.class)
                .getResultList();*/
        // так как наследуемся от javax.persistence можем не привязываться к сессии и hibernate все происходит на уровне JPA
        Query query = entityManager.createQuery("from Employee");
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        //аналогия
        /*Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);*/
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        // аналогия
        /*Session session = entityManager.unwrap(Session.class);
        return session.get(Employee.class, id);*/
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        //аналогия
       /* Session session = entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();*/
        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

}
