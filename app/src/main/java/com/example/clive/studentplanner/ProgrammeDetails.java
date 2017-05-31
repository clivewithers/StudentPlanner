package com.example.clive.studentplanner;

/**
 * Created by Clive on 30/05/2017.
 */

public class ProgrammeDetails {

    private String programmeId;
    private String programmeName;

    public ProgrammeDetails(){}

    public ProgrammeDetails(String programmeId, String programmeName) {

        this.programmeId = programmeId;
        this.programmeName = programmeName;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }
}
