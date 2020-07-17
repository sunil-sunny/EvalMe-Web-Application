package com.group18.asdc.groupformation;

import java.util.List;

public interface IGroupFormationBuilder {

    public void computeDistance(List answers, List question);
    public void createGroups(List users, Integer numberOfGroups);
    public List getGroups();
  }