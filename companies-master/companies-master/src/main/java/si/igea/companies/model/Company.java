package si.igea.companies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {
    private Integer id;
    private String name;
    private String number;
    private Integer countryId;
    private Country country;

}
