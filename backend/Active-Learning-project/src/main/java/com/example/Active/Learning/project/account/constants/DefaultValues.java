package com.example.Active.Learning.project.account.constants;

import com.example.Active.Learning.project.account.models.Course;
import com.example.Active.Learning.project.account.models.ECourse;
import com.example.Active.Learning.project.account.models.ERole;
import com.example.Active.Learning.project.account.models.Role;

public class DefaultValues {
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "5";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";
    public static final Course DEFAULT_COURSE = new Course(ECourse.NO_COURSE);
    public static final Role DEFAULT_ROLE = new Role(ERole.ROLE_TRAINEE);

    public static final String DEFAULT_SEARCH_COLUMN = "id";
}
