package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.security.entity.User;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_EMPLOYEE")
@Entity(name = "tramservercuba$Employee")
//@Listeners("tramservercuba_EmployeeEntityListener")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -7053556609021843447L;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @Column(name = "STATE")
    protected Integer state;

    @NotNull
    @Column(name = "NUMBER_", nullable = false, unique = true, length = 50)
    protected String number;

    @Column(name = "POSITION_", length = 100)
    protected String position;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_TEAM_ID")
    protected WorkTeam workTeam;

    @Column(name = "IS_INGENER")
    protected Boolean isIngener;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    public void setState(EmployeeState state) {
        this.state = state == null ? null : state.getId();
    }

    public EmployeeState getState() {
        return state == null ? null : EmployeeState.fromId(state);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setWorkTeam(WorkTeam workTeam) {
        this.workTeam = workTeam;
    }

    public WorkTeam getWorkTeam() {
        return workTeam;
    }

    public void setIsIngener(Boolean isIngener) {
        this.isIngener = isIngener;
    }

    public Boolean getIsIngener() {
        return isIngener;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


}