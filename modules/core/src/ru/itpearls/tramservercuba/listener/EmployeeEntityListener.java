package ru.itpearls.tramservercuba.listener;

import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import com.haulmont.cuba.core.listener.AfterUpdateEntityListener;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.Employee;

import javax.inject.Inject;
import java.sql.Connection;
import java.util.List;

@Component("tramservercuba_EmployeeEntityListener")
public class EmployeeEntityListener implements AfterInsertEntityListener<Employee>, AfterUpdateEntityListener<Employee> {

    private static final String SELECT_EMPLOYEES_INGENERS_BY_WORK_TEAM = "select e from tramservercuba$Employee e where e.workTeam.id = :workTeam and e.isIngener = true";
    private static final String WORK_TEAM_PARAM = "workTeam";
    private static final String VIEW = "_local";

    @Inject
    private DataManager dataManager;

    @Override
    public void onAfterInsert(Employee entity, Connection connection) {
        clearOthersIngeners(entity);
    }

    @Override
    public void onAfterUpdate(Employee entity, Connection connection) {
        clearOthersIngeners(entity);
    }

    private void clearOthersIngeners(Employee employee) {
        if (!Boolean.TRUE.equals(employee.getIsIngener())) {
            return;
        }

        List<Employee> employeesList = dataManager
                .load(Employee.class)
                .query(SELECT_EMPLOYEES_INGENERS_BY_WORK_TEAM)
                .parameter(WORK_TEAM_PARAM, employee.getWorkTeam())
                .view(VIEW)
                .list();

        if (employeesList != null
                && !employeesList.isEmpty()) {
            CommitContext context = new CommitContext();

            for (Employee e : employeesList) {
                e.setIsIngener(false);
                context.addInstanceToCommit(e);
            }

            dataManager.commit(context);
        }
    }
}
