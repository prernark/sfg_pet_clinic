package guru.springframework5.sfg_pet_clinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //Above 2 needed to use Builder in children Person and Owner
@MappedSuperclass
public class Person extends BaseEntity {
    //Constructor needed to use Builder annotation in child Owner.
    public Person(Long id, String firstName, String lastName){
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Column(name = "first_name") //Hibernate by default uses snake case. We are only enforcing it here.
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
