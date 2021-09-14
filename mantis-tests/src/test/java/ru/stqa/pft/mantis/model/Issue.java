package ru.stqa.pft.mantis.model;

import java.util.Objects;

public class Issue {

    private int id;
    private String sumary;
    private String description;
    private Project project;
    private String subject;
    private String state_name;

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getState_name() {
        return state_name;
    }

    public Issue withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSumary() {
        return sumary;
    }

    public Issue withSumary(String sumary) {
        this.sumary = sumary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && Objects.equals(sumary, issue.sumary) && Objects.equals(description, issue.description) && Objects.equals(project, issue.project) && Objects.equals(subject, issue.subject) && Objects.equals(state_name, issue.state_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sumary, description, project, subject, state_name);
    }
}
