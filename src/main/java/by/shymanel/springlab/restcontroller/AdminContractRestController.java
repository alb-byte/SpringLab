package by.shymanel.springlab.restcontroller;


import by.shymanel.springlab.exceptions.ContractException;
import by.shymanel.springlab.service.ContractService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/contracts")
public class AdminContractRestController {
    private static final Logger log = Logger.getLogger(AuthenticationRestController.class);

    private ContractService contractService;

    @Autowired
    public AdminContractRestController(ContractService contractService) {
        this.contractService = contractService;
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) throws ContractException {
        try {
            log.info("contract with id " + id + " deleted");
            contractService.deleteContract(id);
        } catch (Exception ex) {
            log.error("error delete contract");

            throw new ContractException("deleteContract", ex);
        }

    }
}
