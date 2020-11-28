package by.shymanel.springlab.service;


import by.shymanel.springlab.dto.ContractDto;
import by.shymanel.springlab.model.Contract;

import java.util.List;

public interface ContractService {
    Contract getContractById(Long id);
    List<Contract> getAllContracts();
    List<Contract> getActiveContract(boolean active);
    List<Contract> getContractByComputer(Long id);
    List<Contract> getContractByUserId(Long id);
    Contract saveContract(ContractDto dto);
    Contract updateContract(ContractDto dto);
    void deleteContract(Long id);
}
