package slovenskoit.zadanie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class StreamData {

    @Id
    @GeneratedValue
    private Long id;

    private String data;



}
