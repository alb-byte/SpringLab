package by.shymanel.springlab.dto;


import by.shymanel.springlab.model.Computer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor

@AllArgsConstructor
public class ComputerDto {
    @Min(value = 0L, message = "Идентификатор не может быть отрицательным")
    private Long id;
    @NotBlank(message = "Заполните поле производитель")
    private String cpuManufacturer;
    @NotBlank(message = "Заполните поле модель процессора")
    private String cpuModel;
    @NotBlank(message = "Заполните поле видеокарта")
    private String gpu;
    @NotBlank(message = "Заполните поле тип оперативной памяти")
    private String ramType;
    @NotBlank(message = "Заполните поле размер оперативной памяти")
    @Digits(integer = 3, fraction = 0,message = "Указанный размер памяти не поддерживается")
    private int ramSize;
    @NotBlank(message = "Заполните поле тип диска")
    private String driveType;
    @NotBlank(message = "Заполните поле размер диска")
    @Digits(integer = 3, fraction = 0,message = "Указанный размер памяти не поддерживается")
    private int driveSize;
    @NotBlank(message = "Заполните поле материнская плата")
    @Size(min=2, max=99,message = "Название материнской платы должно быть больше 2 и меньше 99 символов")
    private String motherBoard;
    @NotNull
    private boolean isFree;
    @Digits(integer = 4, fraction = 0,message = "Указанная цена не поддерживается")
    private int price;
    public static ComputerDto fromComputer(Computer computer){
        ComputerDto dto = new ComputerDto();
        dto.setId(computer.getId());
        dto.setCpuManufacturer(computer.getCpuManufacturer().toString());
        dto.setCpuModel(computer.getCpuModel());
        dto.setGpu(computer.getGpu());
        dto.setRamType(computer.getRamType().toString());
        dto.setRamSize(computer.getRamSize());
        dto.setDriveType(computer.getDriveType().toString());
        dto.setDriveSize(computer.getDriveSize());
        dto.setMotherBoard(computer.getMotherBoard());
        dto.setFree(computer.isFree());
        dto.setPrice(computer.getPrice());

        return dto;
    }
}
