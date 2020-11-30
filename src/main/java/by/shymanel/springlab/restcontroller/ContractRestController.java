package by.shymanel.springlab.restcontroller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/contracts")
public class ContractRestController {
    private static final Logger log = Logger.getLogger(AuthenticationRestController.class);
    ContractService contractService;
    UserService userService;

    @Autowired
    public ContractRestController(ContractService contractService, UserService userService) {
        this.contractService = contractService;
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable(name = "id") Long id)
            throws ContractException {
        try {
            Contract contract = contractService.getContractById(id);

            if (contract == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            ContractDto result = ContractDto.fromContract(contract);
            log.info("contract " + result.getId() + " found");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("error get contract");

            throw new ContractException("getContract", ex);
        }

    }

    @GetMapping
    public ResponseEntity<List<ContractDto>> getContracts(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long computerId) throws ContractException {

        try {
            List<Contract> contracts = null;
            if (active != null) {
                contracts = contractService.getActiveContract(active);
                log.info("active contract found");
            } else if (username != null) {
                User u  =userService.findByUsername(username);
                contracts = contractService.getContractByUserId(u.getId());
                log.info("contract with userName " + username + " found");
            } else if (computerId != null) {
                contracts = contractService.getContractByComputer(computerId);
                log.info("contract with computerId " + computerId + " found");
            } else {
                contracts = contractService.getAllContracts();
                log.info("contracts found");
            }

            if (contracts.size() == 0) {
                log.info("contracts - NO CONTENT");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<ContractDto> result = new ArrayList<ContractDto>();

            contracts.stream().forEach(i -> result.add(ContractDto.fromContract(i)));

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("error get contract");

            throw new ContractException("getContract", ex);
        }

    }

    @PostMapping
    public ContractDto add(@RequestBody ContractDto contractDto) throws ContractException {
        try {
            log.info("contract " + contractDto.toString() + " created");
            return ContractDto.fromContract(contractService.saveContract(contractDto));
        } catch (Exception ex) {
            log.error("error add contract");

            throw new ContractException("addContract", ex);
        }

    }
    @PutMapping
    public ContractDto update(@RequestBody ContractDto contractDto) throws ContractException {
        try {
            log.info("contract " + contractDto.toString() + " updated");
            return ContractDto.fromContract(contractService.updateContract(contractDto));
        } catch (Exception ex) {
            log.error("error update contract");

            throw new ContractException("updateContract", ex);
        }

    }

}
