package api.pojos.dummyrestapipojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestApiDataPojo {
    private String employee_name;
    private Integer employee_salary;
    private Integer employee_age;
    private String profile_image;

    public DummyRestApiDataPojo() {
    }

    public DummyRestApiDataPojo(String employee_name, Integer employee_salary, Integer employee_age, String profile_image) {
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public Integer getEmployee_salary() {
        return employee_salary;
    }

    public Integer getEmployee_age() {
        return employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    @Override
    public String toString() {
        return "DummyRestApiDataPojo{" +
                "employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}