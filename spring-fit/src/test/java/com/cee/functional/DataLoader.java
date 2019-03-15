/**
 * 
 */
package com.cee.functional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cee.functional.user.User;
import com.cee.functional.user.UserData;
import com.cee.functional.user.UserRepository;

/**
 * @author chuck
 *
 */
@Component
public class DataLoader implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    UserRepository userRepository;

    /*@Inject
    public DataLoader(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        final Iterable<User> users = UserData.getAllEntities();
        log.info("Loading users: {}", users);
        userRepository.saveAll(users);
    }
}
