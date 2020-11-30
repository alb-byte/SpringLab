package by.shymanel.springlab.restcontroller;


import by.shymanel.springlab.dto.ComputerDto;
import by.shymanel.springlab.exceptions.ComputerException;
import by.shymanel.springlab.service.ComputerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/computers")
public class AdminComputerRestController {
    private static final Logger log = Logger.getLogger(AuthenticationRestController.class);

    private final ComputerService computerService;

    @Autowired
    public AdminComputerRestController(ComputerService computerService) {
        this.computerService = computerService;
    }


    @PostMapping
    public ComputerDto add(@RequestBody ComputerDto computerDto) throws ComputerException {
        try {
            log.info("computer " + computerDto.toString() + " created");
            return ComputerDto.fromComputer(computerService.saveComputer(computerDto));
        } catch (Exception ex) {
            log.error("error add computer");

            throw new ComputerException("addComputer", ex);
        }
    }

    @PutMapping
    public ComputerDto update(@RequestBody ComputerDto computerDto) throws ComputerException {
        try {
            log.info("computer " + computerDto.toString() + " updated");
            return ComputerDto.fromComputer(computerService.updateComputer(computerDto));
        } catch (Exception ex) {
            log.error("error update computer");

            throw new ComputerException("updateComputer", ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) throws ComputerException {
        try {
            log.info("computer with id " + id + " deleted");
            computerService.deleteComputerById(id);
        } catch (Exception ex) {
            log.error("error delete computer");

            throw new ComputerException("deleteComputer", ex);
        }
    }
}
