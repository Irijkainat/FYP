package com.FYP.HealthApp.model;

import java.io.Serializable;
import java.util.Objects;

public class PatientDiseasesId  implements Serializable {    private int patientId;
    private int diseaseId;

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientDiseasesId)) return false;
        PatientDiseasesId that = (PatientDiseasesId) o;
        return patientId == that.patientId && diseaseId == that.diseaseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, diseaseId);
    }
}
