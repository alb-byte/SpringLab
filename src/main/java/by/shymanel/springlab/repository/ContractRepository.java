package by.shymanel.springlab.repository;


import by.shymanel.springlab.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract getContractById(Long id);
    List<Contract> getContractByComputerId(Long id);
    List<Contract> getContractByUserId(Long id);
    List<Contract> getContractByIsActive(boolean active);

}