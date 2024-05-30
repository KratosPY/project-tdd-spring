package yur.mal.TaskTDD.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @JsonProperty("id")
    private Integer id;
    private String name;
    private String description;
    @JsonProperty("isSolved")
    private boolean isSolved;
}
