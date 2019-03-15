package com.cee.functional.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIT {
    //private static final Logger log = LoggerFactory.getLogger(UserRepositoryIT.class);

    @Autowired
    private UserRepository repository;

    private final User newUser = new User.Builder()
            .id(3L)
            .email("anemail@domain.com")
            .name("test user")
            .password("W0rkingEm@il")
            .build();

    // fails because it is really in the database so test rollback fails....
    //private User newUser = UserValue.CHUCK.getEntity();

    @Test
    public void testFindByExample() {
        final Optional<User> user = repository.findOne(Example.of(UserData.CHUCK.getEntity()));

        assertTrue("user must exist", user.isPresent());
    }

    @Test
    public void testFindAll() {
        final List<User> users = repository.findAll();
        final int expectedNumberOfStudents = UserData.getAllEntities().size();
        assertTrue(
                "return value wasn't the expected number equal to " + expectedNumberOfStudents,
                users.size() == expectedNumberOfStudents);
    }

    @Test
    public void testSave() {
        repository.save(newUser);

        final Optional<User> user = repository.findByName(newUser.getName());
        assertTrue("User must exist", user.isPresent());
    }

    @Test
    public void testTestsRollback() {
        final Optional<User> user = repository.findByName(newUser.getName());
        assertFalse("User must not exist", user.isPresent());
    }
}