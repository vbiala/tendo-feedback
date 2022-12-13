package com.tendo.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class Bundle {
    public Bundle() {

    }
    private UUID id;
    private ZonedDateTime timestamp;

    private List<Resource> entry;

    private String resourceType;

    public ZonedDateTime getTime() {
        return timestamp;
    }

    public void setTime(ZonedDateTime time) {
        this.timestamp = time;
    }

    public List<Resource> getEntry() {
        return entry;
    }

    public void setEntry(List<Resource> entry) {
        this.entry = entry;
    }

    public Optional<TendoResource.Patient> getPatient() {
        for (Resource iterator : entry) {
            if (iterator.resource instanceof TendoResource.Patient) {
                return Optional.of((TendoResource.Patient)iterator.resource);
            }
        }
        return Optional.empty();
    }

    public Optional<TendoResource.Doctor> getDoctor() {
        for (Resource iterator : entry) {
            if (iterator.resource instanceof TendoResource.Doctor) {
                return Optional.of((TendoResource.Doctor)iterator.resource);
            }
        }
        return Optional.empty();
    }

    public Optional<TendoResource.Appointment> getAppointment() {
        for (Resource iterator : entry) {
            if (iterator.resource instanceof TendoResource.Appointment) {
                return Optional.of((TendoResource.Appointment)iterator.resource);
            }
        }
        return Optional.empty();
    }

    public Optional<TendoResource.Diagnosis> getDiagnosis() {
        for (Resource iterator : entry) {
            if (iterator.resource instanceof TendoResource.Diagnosis) {
                return Optional.of((TendoResource.Diagnosis)iterator.resource);
            }
        }
        return Optional.empty();
    }

    @Data
    public static class Resource {

        public Resource() {

        }

        private TendoResource resource;

        public TendoResource getResource() {
            return resource;
        }

        public void setResource(TendoResource resource) {
            this.resource = resource;
        }
    }

    @Data
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "resourceType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = TendoResource.Patient.class, name = "Patient"),
            @JsonSubTypes.Type(value = TendoResource.Appointment.class, name = "Appointment"),
            @JsonSubTypes.Type(value = TendoResource.Diagnosis.class, name = "Diagnosis"),
            @JsonSubTypes.Type(value = TendoResource.Doctor.class, name = "Doctor")
    })
    public static class TendoResource {

        public TendoResource() {

        }

        @Data
        public static class Patient extends TendoResource {
            private String name;
            private boolean active;
            private String gender;
            private String birthDate;
            private String address;

            public String getName() {
                return name;
            }
        }

        @Data
        public static class Appointment extends TendoResource {
            private String status;

            private String type;

            private Reference subject;

            private Reference actor;
        }

        @Data
        public static class Doctor extends TendoResource {
            private String name;
        }

        @Data
        public static class Diagnosis extends TendoResource {
            private String status;
            private String code;
            private Reference appointment;
        }

        @Data
        public static class Reference {
            private String reference;
        }

        private String id;

        private String resourceType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }
    }

}
