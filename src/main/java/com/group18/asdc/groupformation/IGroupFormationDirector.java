package com.group18.asdc.groupformation;

import com.group18.asdc.entities.ISurveyList;

public interface IGroupFormationDirector {
    public void createGroup(ISurveyList survey, Integer numberOfGroups);
}