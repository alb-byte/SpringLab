package by.shymanel.springlab.service;


import by.shymanel.springlab.dto.ComputerDto;
import by.shymanel.springlab.model.Computer;

import java.util.List;

public interface ComputerService {
    Computer getComputerById(Long id);
    List<Computer> getAllComputers();
    List<Computer> getFreeComputers(boolean free);
    List<Computer> getComputersByCpu(String cpu);
    List<Computer> getComputersByRam(int ram);
    List<Computer> getComputersByDrive(String drive);
    Computer saveComputer(ComputerDto computerDto);
    Computer updateComputer(ComputerDto computerDto);
    void deleteComputerById(Long id);
}