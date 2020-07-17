package com.group18.asdc.groupformation;

import java.util.List;

public class BaseGroupFormationBuilder implements IGroupFormationBuilder {
    
    private List groups;
    private Float[][] distanceMatrix;
  
    public void computeDistance(List answers, List question) {
      distanceMatrix = new ComputeDistance(answers, question).compute();
    }
  
    public void createGroups(List users, Integer numberOfGroups) {
      groups = new GroupFormation(distanceMatrix, users, numberOfGroups).formGroups();
    }
  
    public void reset() {
      this.groups = null;
      this.distanceMatrix = null;
    }
  
    public List getGroups() {
      List formedGroups =  this.groups;
      this.reset();
      return formedGroups;
    }
    
}