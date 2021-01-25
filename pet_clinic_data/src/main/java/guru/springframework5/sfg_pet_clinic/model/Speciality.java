package guru.springframework5.sfg_pet_clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name = "speciality")
public class Speciality extends BaseEntity {
    @Column(name = "description")
    @ManyToMany (mappedBy = "vet_id")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
