package uz.alif.lesson_jpa.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaculityDto {

    private String name;
    private int universityId;


}
