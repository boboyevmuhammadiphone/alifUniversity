package uz.alif.lesson_jpa.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

    private String groupName;
    private int faculityId;

}
