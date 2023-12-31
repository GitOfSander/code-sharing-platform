package platform.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "code")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id;
    @DateTimeFormat
    private Date date;
    @Column(name = "time_restriction")
    private int timeRestriction;
    @Column(name = "views_restriction")
    private int viewsRestriction;
    private String code;

    public Code() {

    }

    public Code(Date date, int timeRestriction, int viewsRestriction, String code) {
        this.date = date;
        this.timeRestriction = timeRestriction;
        this.viewsRestriction = viewsRestriction;
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(int timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public int getViewsRestriction() {
        return viewsRestriction;
    }

    public void setViewsRestriction(int viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
