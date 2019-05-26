package by.oreshko.myspring.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonForm {

    @NotBlank(message="First name is required")
    @Size(min=3, message="First name must be at least 3 characters long")
    private String firstName;
    @NotBlank(message="Last name is required")
    @Size(min=3, message="Last name must be at least 3 characters long")
    private String lastName;
    @NotBlank(message="Street is required")
    @Size(min=3, message="Street must be at least 3 characters long")
    private String street;
    @NotBlank(message="City is required")
    @Size(min=3, message="City must be at least 3 characters long")
    private String city;
    @Digits(integer=6, fraction=0, message="Invalid zip code")
    private String zip;
    @NotBlank(message="Email is required")
    @Pattern(regexp="^.+@.+\\..{2,3}$",message="Must be formatted *@*.*")
    private String email;
    @NotBlank(message="Phone is required")
    @Size(min=7, message="Phone must be at least 7 characters long")
    private String phone;
    @NotBlank(message="Birthday is required")
    @Pattern(regexp="^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$",message="Must be formatted DD/MM/YYYY")
    private String birthday;
}
