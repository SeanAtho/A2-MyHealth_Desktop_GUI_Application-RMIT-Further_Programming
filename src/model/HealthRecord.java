package model;

import java.time.LocalDate;

/**
 * The HealthRecord class represents a record of a patient's health information.
 */
public class HealthRecord {
    private float weight;
    private float temperature;
    private String bloodPressure;
    private String note;
    private LocalDate date;

    public HealthRecord(float weight, float temperature, String bloodPressure, String note, LocalDate date) {
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.note = note;
        this.date = date;
    }

    /**
     * Sets the weight of the patient.
     *
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Sets the temperature of the patient.
     *
     * @param temperature the temperature to set
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    /**
     * Sets the blood pressure of the patient.
     *
     * @param bloodPressure the blood pressure to set
     */
    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    /**
     * Sets the note for the health record.
     *
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Sets the date of the health record.
     *
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the weight of the patient.
     *
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Returns the temperature of the patient.
     *
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * Returns the blood pressure of the patient.
     *
     * @return the blood pressure
     */
    public String getBloodPressure() {
        return bloodPressure;
    }

    /**
     * Returns the note for the health record.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Returns the date of the health record.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }
}
