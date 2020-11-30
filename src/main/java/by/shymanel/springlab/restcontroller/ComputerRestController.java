package by.shymanel.springlab.restcontroller;

import by.shymanel.springlab.dto.ComputerDto;
import by.shymanel.springlab.exceptions.ComputerException;
import by.shymanel.springlab.model.Computer;
import by.shymanel.springlab.service.ComputerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/computers")
public class ComputerRestController {
    private static final Logger log = Logger.getLogger(AuthenticationRestController.class);
    private final ComputerService computerService;

    @Autowired
    public ComputerRestController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ComputerDto> getComputerById(@PathVariable(name = "id") Long id)
            throws ComputerException {
        try {
            Computer computer = computerService.getComputerById(id);

            if (computer == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            ComputerDto result = ComputerDto.fromComputer(computer);
            log.info("computer with id " + result.getId() + " found");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("error get computer");

            throw new ComputerException("getComputer", ex);
        }

    }

    @GetMapping
    public ResponseEntity<List<ComputerDto>> getComputers
            (
                    @RequestParam(required = false) Boolean free,
                    @RequestParam(required = false) String cpu,
                    @RequestParam(required = false) Integer ramSize,
                    @RequestParam(required = false) String driveType) throws ComputerException {
        try {
            List<Computer> computers = null;
            if (free != null) {
                computers = computerService.getFreeComputers(free);
            } else if (cpu != null) {
                computers = computerService.getComputersByCpu(cpu);
            } else if (driveType != null) {
                computers = computerService.getComputersByDrive(driveType);
            } else if (ramSize != null) {
                computers = computerService.getComputersByRam(ramSize);
            } else {
                computers = computerService.getAllComputers();
            }

            if (computers.size() == 0) {
                log.info("computers - NO CONTENT");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<ComputerDto> result = new ArrayList<ComputerDto>();

            computers.stream().forEach(i -> result.add(ComputerDto.fromComputer(i)));

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("error get computer");

            throw new ComputerException("getComputer", ex);
        }
    }


}
