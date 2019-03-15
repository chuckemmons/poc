/**
 * 
 */
package com.cee.functional.user;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.boot.test.context.TestComponent;

import com.cee.functional.validation.Jsr303Validator;

/**
 * @author chuck
 *
 */
@TestComponent
public enum UserData {
    CHUCK( new Builder()
            .id( 1L )
            .name( "Chuck" )
            .email( "c.emmons@gmail.com" )
            .password( "W0rkingpas$word" )
    ),
    BRANDON( new Builder()
            .id( 2L )
            .name( "Brandon" )
            .email( "baemmons@uwm.edu" )
            .password( "W0rkingpas$word" )
    );

    @NotNull
    private Long id;
    @NotBlank
    @Size(min=1, max=255)
    private String name;
    @Email
    @Size(min=7, max=255)
    private String email;
    @NotBlank
    //@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$")
    @Pattern(regexp = "^(?=.*[!@#$&*]).+$", message="Password requires at least one special character '!@#$&*'")
    @Pattern(regexp = "^(?=.*[A-Z]).+$", message="Password requires at least one upper-case character.")
    @Pattern(regexp = "^(?=.*[a-z]).+$", message="Password requires at least one lower-case character.")
    @Pattern(regexp = "^(?=.*[0-9]).+$", message="Password requires at least one number.")
    @Pattern(regexp = "^.{8,}$", message="Password length must be at least 8.")
    private String password;

    UserData(final Builder builder) {
        id = builder.id;
        name = builder.name;
        email = builder.email;
        password = builder.password;
        Jsr303Validator.validate(this);
    }

    private static class Builder {
        Long id;
        String name;
        String email;
        String password;

        Builder id(final Long id) {
            this.id = id;
            return this;
        }

        Builder name(final String name) {
            this.name = name;
            return this;
        }

        Builder email(final String email) {
            this.email = email;
            return this;
        }

        Builder password(final String password) {
            this.password = password;
            return this;
        }
    }

    /* Instance methods */

    public User getEntity() {
        return new User.Builder()
                .id(id)
                .email(email)
                .name(name)
                .password(password)
                .build();
    }

    /* Static methods */

    public static Set<User> getAllEntities() {
        final Set<User> entities = new LinkedHashSet<>();
        for (final UserData data : values()) {
        	entities.add(data.getEntity());
        }
        return entities;
    }
	
}
