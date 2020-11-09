package by.shymanel.springlab.model;


import by.shymanel.springlab.dto.ComputerDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "COMPUTERS")
@Getter
@Setter
@ToString(exclude = {"contracts"})
@AllArgsConstructor
@NoArgsConstructor
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "cpu_manufacturer")
    private ComputerCpuManufacturer cpuManufacturer;
    @Column(name = "cpu_model")
    private String cpuModel;

    @Column(name = "gpu")
    private String gpu;

    @Enumerated(EnumType.STRING)
    @Column(name = "ram_type")
    private ComputerRamType ramType;
    @Column(name = "ram_size")
    private int ramSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "drive_type")
    private ComputerDriveType driveType;
    @Column(name = "drive_size")
    private int driveSize;

    @Column(name = "mother_board")
    private String motherBoard;

    @Column(name = "is_free")
    private boolean isFree;

    @Column(name = "price")
    private int price;
    @OneToMany(mappedBy = "computer", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Collection<Contract> contracts;

    public static void fromComputerDto(ComputerDto dto, Computer computer){
        if(dto.getCpuManufacturer().equals(ComputerCpuManufacturer.AMD.toString())){
            computer.setCpuManufacturer(ComputerCpuManufacturer.AMD);
        }
        else{
            computer.setCpuManufacturer(ComputerCpuManufacturer.INTEL);
        }
        computer.setCpuModel(dto.getCpuModel());

        computer.setGpu(dto.getGpu());

        if(dto.getRamType().equals(ComputerRamType.DDR3.toString())){
            computer.setRamType(ComputerRamType.DDR3);
        }
        else if(dto.getRamType().equals(ComputerRamType.DDR4.toString())){
            computer.setRamType(ComputerRamType.DDR4);
        }
        else{
            computer.setRamType(ComputerRamType.DDR5);
        }
        computer.setRamSize(dto.getRamSize());

        if(dto.getDriveType().equals(ComputerDriveType.HDD.toString())){
            computer.setDriveType(ComputerDriveType.HDD);
        }
        else{
            computer.setDriveType(ComputerDriveType.SSD);
        }
        computer.setDriveSize(dto.getDriveSize());
        computer.setMotherBoard(dto.getMotherBoard());
        computer.setFree(dto.isFree());
        computer.setPrice(dto.getPrice());

    }

}