package by.shymanel.springlab.service.impl;


import by.shymanel.springlab.dto.ComputerDto;
import by.shymanel.springlab.model.Computer;
import by.shymanel.springlab.model.ComputerCpuManufacturer;
import by.shymanel.springlab.model.ComputerDriveType;
import by.shymanel.springlab.repository.ComputerRepository;
import by.shymanel.springlab.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {

    private ComputerRepository computerRepository;

    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public Computer saveComputer(ComputerDto computerDto) {
        Computer computer  = new Computer();
        Computer.fromComputerDto(computerDto,computer);
        return computerRepository.save(computer);
    }

    @Override
    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    @Override
    public List<Computer> getFreeComputers(boolean free) {
        return computerRepository.findComputerByIsFree(free);
    }

    @Override
    public List<Computer> getComputersByCpu(String cpu) {
        if(cpu.equals(ComputerCpuManufacturer.AMD.toString())){
            return computerRepository.findComputerByCpuManufacturer(ComputerCpuManufacturer.AMD);
        }
        else{
            return computerRepository.findComputerByCpuManufacturer(ComputerCpuManufacturer.INTEL);
        }
    }

    @Override
    public List<Computer> getComputersByRam(int ram) {
        return computerRepository.findComputerByRamSizeAfter(ram);
    }

    @Override
    public List<Computer> getComputersByDrive(String drive) {
        if(drive.equals(ComputerDriveType.HDD.toString())){
            return computerRepository.findComputerByDriveType(ComputerDriveType.HDD);
        }
        else{
            return computerRepository.findComputerByDriveType(ComputerDriveType.SSD);
        }
    }

    @Override
    public Computer getComputerById(Long id) {
        return computerRepository.getComputerById(id);
    }

    @Override
    public Computer updateComputer(ComputerDto computerDto) {
        Computer updatedComputer = computerRepository.getComputerById(computerDto.getId());
        Computer.fromComputerDto(computerDto,updatedComputer);
        return computerRepository.save(updatedComputer);
    }

    @Override
    public void deleteComputerById(Long id) {
        computerRepository.deleteById(id);
    }

}