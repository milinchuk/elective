package model.entity;

import utils.constants.DateHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Course of some subject
 *
 * @author Anastasia Milinchuk
 */
public class Course {
    private Integer id;
    private String name;
    private String about;
    private Date startDate;
    private Date endDate;
    private User tutor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public User getTutor() {
        return tutor;
    }

    public void setTutor(User tutor) {
        this.tutor = tutor;
    }

    @Override
    public boolean equals(Object course){
        return this.getId().equals(((Course)course).getId());
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartDateToString(){
        return getStringDate(startDate);
    }

    public String getEndDateToString(){
        return getStringDate(endDate);
    }

    private String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateHolder.DATE_FORMAT);
        String stringDate = null;
        if (date != null) {
            stringDate = sdf.format(date);
        }
        return stringDate;
    }
}
