package by.shymanel.springlab.model;


import by.shymanel.springlab.dto.ContractDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONTRACTS")
@Getter
@Setter
@ToString(exclude = {"computer","user"})
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "computer_id",referencedColumnName = "id")
    private Computer computer;

    public static void fromContractDto(ContractDto dto, Contract contract){
        contract.isActive = dto.isActive();
        contract.startDate = dto.getStartDate();
        contract.endDate = dto.getEndDate();
    }
}
