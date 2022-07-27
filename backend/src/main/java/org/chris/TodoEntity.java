package org.chris;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@XmlRootElement
@Entity
public class TodoEntity extends PanacheEntityBase {

    @XmlElement
    @SerializedName("id")
    @Expose
    @Id
    @GeneratedValue
    private Long id;
    @XmlElement
    @SerializedName("text")
    @Expose
    private String text;
    @XmlElement
    @SerializedName("status")
    @Expose
    private String status;

    public TodoEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<TodoEntity> findById(Long id) {
        return find("id", id).list();
    }
}
