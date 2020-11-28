package by.shymanel.springlab.service.impl;

import by.shymanel.springlab.dto.ContractDto;
import by.shymanel.springlab.model.Computer;
import by.shymanel.springlab.model.Contract;
import by.shymanel.springlab.model.User;
import by.shymanel.springlab.repository.ComputerRepository;
import by.shymanel.springlab.repository.ContractRepository;
import by.shymanel.springlab.repository.UserRepository;
import by.shymanel.springlab.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private ContractRepository contractRepository;
    private UserRepository userRepository;
    private ComputerRepository computerRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, UserRepository userRepository, ComputerRepository computerRepository) {
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
        this.computerRepository = computerRepository;
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.getContractById(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> getActiveContract(boolean active) {

        return contractRepository.getContractByIsActive(active);
    }

    @Override
    public List<Contract> getContractByComputer(Long id) {

        return contractRepository.getContractByComputerId(id);
    }

    @Override
    public List<Contract> getContractByUserId(Long id) {

        return contractRepository.getContractByUserId(id);
    }

    @Override
    public Contract saveContract(ContractDto dto) {
        Contract contract = new Contract();
        Contract.fromContractDto(dto,contract);
        Computer c = computerRepository.getComputerById(dto.getComputerId());
        contract.setComputer(c);
        User u = userRepository.findByUsername(dto.getUsername());
        contract.setUser(u);
        long millis=System.currentTimeMillis();
        contract.setStartDate(new Date(millis + 9000000));
        contract.setEndDate(new Date(millis + 18000000));
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(ContractDto dto) {

        Contract updatedContract = contractRepository.getContractById(dto.getId());
        Contract.fromContractDto(dto,updatedContract);
        return contractRepository.save(updatedContract);
    }

    @Override
    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }
}
