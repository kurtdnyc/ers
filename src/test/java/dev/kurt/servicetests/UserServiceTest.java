package dev.kurt.servicetests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.kurt.daos.EmployeeDAO;
import dev.kurt.daos.ManagerDAO;
import dev.kurt.dtos.LoginDTO;
import dev.kurt.entities.Employee;
import dev.kurt.entities.User;
import dev.kurt.exceptions.InvalidLoginException;
import dev.kurt.services.UserService;
import dev.kurt.services.UserServiceImpl;

class UserServiceTest {

	@Test
	void test() throws InvalidLoginException {
		Employee kurt = new Employee(1,"kd@email.com","password","Kurt","Martinez");
		LoginDTO login= new LoginDTO("kd@email.com","password");
		ManagerDAO manDao = Mockito.mock(ManagerDAO.class);
		EmployeeDAO eDao = Mockito.mock(EmployeeDAO.class);
		
		List<Employee> fakeEmployees = new ArrayList<Employee>();
		fakeEmployees.add(kurt);
		
		
		Mockito.when(eDao.getAllEmployees()).thenReturn(fakeEmployees);
		Mockito.when(eDao.getEmployeeByLogin(kurt.getEmpUsername(), kurt.getEmpPassword())).thenReturn(kurt);
		UserService uServ = new UserServiceImpl(manDao,eDao);
		
		User user = uServ.loginUser(login);
		Assertions.assertEquals("password", ((Employee)user).getEmpPassword());
	}

}
