package by.oreshko.myspring.form;

import by.oreshko.myspring.validator.CellPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonForm {

    @NotNull(message="{valid.firstName.notNull}")
    @Size(min=3, message="{valid.firstName.size.min3}")
    private String firstName;
    @NotBlank(message="{valid.lastName.notBlank}")
    @Size(min=3, message="{valid.lastName.size.min3}")
    private String lastName;
    @NotBlank(message="{valid.street.notBlank}")
    @Size(min=3, message="{valid.street.size.min3}")
    private String street;
    @NotBlank(message="{valid.city.notBlank}")
    @Size(min=3, message="{valid.city.size.min3}")
    private String city;
    @Digits(integer=6, fraction=0, message="{valid.zip.digits}")
    private String zip;
    @NotBlank(message="{valid.email.notBlank}")
    //@Pattern(regexp="^.+@.+\\..{2,3}$",message="Must be formatted *@*.*")
    @Email(message = "{valid.email.email}")
    private String email;
    /*@NotBlank(message="Birthday is required")
    @Pattern(regexp="^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$",message="Must be formatted DD/MM/YYYY")
    private String birthday;*/
    @NotBlank(message="{valid.phone.notBlank}")
    @CellPhone(message = "{valid.phone.cellphone}")
    private String phone;
    //ISO 8601 date format (yyyy-MM-dd)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "{valid.birthday.past}")
    private Date birthday;
}
