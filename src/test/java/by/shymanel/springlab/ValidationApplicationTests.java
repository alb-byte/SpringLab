package by.shymanel.springlab;


import by.shymanel.springlab.dto.ContractDto;
import by.shymanel.springlab.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

public class ValidationApplicationTests {
    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void testContractValidators() {
        final ContractDto contractDto = new ContractDto();
        contractDto.setActive(true);
        contractDto.setStartDate((new Date()));
        contractDto.setEndDate(new Date());

        contractDto.setComputerId(2L);
        contractDto.setUserId(1L);

        Set<ConstraintViolation<ContractDto>> validates = validator.validate(contractDto);
        Assert.assertTrue(validates.size() > 0);
        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }

    @Test
    public void testUserValidators() {
        final UserDto userDto = new UserDto();

        userDto.setEmail("aaa@gmail.com");
        userDto.setFirstName("Albert");
        userDto.setLastName("Shymanel");
        userDto.setUsername("aliii");
        userDto.setPassword("Abc");

        Set<ConstraintViolation<UserDto>> validates = validator.validate(userDto);
        Assert.assertTrue(validates.size() > 0);
        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }
}
