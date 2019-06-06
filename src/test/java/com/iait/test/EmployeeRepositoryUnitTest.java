package com.iait.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.iait.testing.entities.EmployeeEntity;
import com.iait.testing.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        EmployeeEntity alex = new EmployeeEntity("alex");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        EmployeeEntity found = employeeRepository.findByName(alex.getName());

        // then
        assertThat(found.getName())
            .isEqualTo(alex.getName());
    }
 
}
