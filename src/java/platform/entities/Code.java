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
    @DateTimeFormat
    @Column(name = "available_before_date")
    private Date availableBeforeDate;
    @Column(name = "views_before_removal")
    private int viewsBeforeRemoval;
    private String code;

    public Code() {

    }

    public Code(Date date, Date availableBeforeDate, int viewsBeforeRemoval, String code) {
        this.date = date;
        this.availableBeforeDate = availableBeforeDate;
        this.viewsBeforeRemoval = viewsBeforeRemoval;
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

    public Date getAvailableBeforeDate() {
        return availableBeforeDate;
    }

    public void setAvailableBeforeDate(Date availableBeforeDate) {
        this.availableBeforeDate = availableBeforeDate;
    }

    public int getViewsBeforeRemoval() {
        return viewsBeforeRemoval;
    }

    public void setViewsBeforeRemoval(int viewsBeforeRemoval) {
        this.viewsBeforeRemoval = viewsBeforeRemoval;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
