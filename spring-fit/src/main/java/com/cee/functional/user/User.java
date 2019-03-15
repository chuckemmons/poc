/**
 * 
 */
package com.cee.functional.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cee.functional.validation.Jsr303Validator;

import lombok.Data;

/**
 * @author chuck
 *
 */
@Data
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -2238967192529827495L;
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max=10)
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[!@#$&*]).+$", message="Password requires at least one special character '!@#$&*'")
    @Pattern(regexp = "^(?=.*[A-Z]).+$",    message="Password requires at least one upper-case character.")
    @Pattern(regexp = "^(?=.*[a-z]).+$",    message="Password requires at least one lower-case character.")
    @Pattern(regexp = "^(?=.*[0-9]).+$",    message="Password requires at least one number.")
    @Pattern(regexp = "^.{8,}$",            message="Password length must be at least 8.")
    private String password;

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public User build() {
            final User user = new User();
            user.email = email;
            user.id = id;
            user.name = name;
            user.password = password;

            return Jsr303Validator.validate(user);
        }
    }

}
