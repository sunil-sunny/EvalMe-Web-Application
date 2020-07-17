package com.group18.asdc.groupformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupFormation implements IGroupFormation {

    private Float[][] distanceMatrix = null;
    private List<String> usersList = null;
    private Integer groupSize = null;
    private List<Integer> globalUserList = null;
    private Logger logger = Logger.getLogger(IGroupFormation.class.getName());

    public GroupFormation(Float[][] distanceMatrix, List usersList, Integer groupSize) {
        this.distanceMatrix = distanceMatrix;
        this.usersList = usersList;
        this.groupSize = groupSize;
        this.globalUserList = new ArrayList<>();
    }

    @Override
    public ArrayList<ArrayList> formGroups() {
        logger.log(Level.INFO, "Forming groups for the given distance matrix");
        ArrayList<ArrayList> groupIndexList = new ArrayList<>();
        ArrayList<ArrayList> groupList = new ArrayList<>();
        if (distanceMatrix.length == 0) {
            return null;
        } else {
            ArrayList<Integer> subGroupList = null;
            for (Integer userIterator = 0; userIterator < usersList.size(); userIterator++) {
                globalUserList.add(userIterator);
            }
            while (globalUserList.size() > 0) {
                subGroupList = new ArrayList<>();
                subGroupList.add(globalUserList.get(0));
                globalUserList.remove(0);
                while (subGroupList.size() < groupSize && globalUserList.size() > 0) {
                    formSubGroups(subGroupList);
                }
                groupIndexList.add(subGroupList);
            }
            for (ArrayList eachGroup : groupIndexList) {
                ArrayList eachGroupList = new ArrayList<>();
                for (Object eachMember : eachGroup) {
                    eachMember = (Integer) eachMember;
                    eachGroupList.add(usersList.get((int) eachMember));
                }
                groupList.add(eachGroupList);
            }
        }
        logger.log(Level.INFO, "Groups formed for the given distance matrix");
        return groupList;
    }

    private void formSubGroups(ArrayList<Integer> subGroupList) {
        HashMap<Integer, Float> otherMembersDistance = new HashMap<>();
        for (Integer eachOtherMember : this.globalUserList) {
            ArrayList<Float> distanceList = new ArrayList<>();
            for (Integer existingMembers : subGroupList) {
                distanceList.add(this.distanceMatrix[existingMembers][eachOtherMember]);
            }
            Float averageDistance = (float) distanceList.stream().mapToDouble(Float::doubleValue).sum()
                    / subGroupList.size();
            otherMembersDistance.put(eachOtherMember, averageDistance);
        }
        Float minValue = Collections.min(otherMembersDistance.values());
        Integer nextGroupMember = otherMembersDistance.keySet().stream()
                .filter(key -> minValue.equals(otherMembersDistance.get(key))).findFirst().get();

        subGroupList.add(nextGroupMember);
        this.globalUserList.remove(nextGroupMember);
    }

}