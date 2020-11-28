package by.shymanel.springlab.repository;


import by.shymanel.springlab.model.Computer;
import by.shymanel.springlab.model.ComputerCpuManufacturer;
import by.shymanel.springlab.model.ComputerDriveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
    Computer getComputerById(Long id);

    List<Computer> findComputerByIsFree(boolean free);
    List<Computer> findComputerByCpuManufacturer(ComputerCpuManufacturer m);
    List<Computer> findComputerByRamSizeAfter(int ramSize);
    List<Computer> findComputerByDriveType(ComputerDriveType t);

}