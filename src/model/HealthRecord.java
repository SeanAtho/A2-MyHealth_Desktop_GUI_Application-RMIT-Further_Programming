package model;

import java.time.LocalDate;

/**
 * Represents a health record for a user. This includes details such as weight, temperature, 
 * blood pressure, additional notes, date of the record, and the user associated with the record.
 */
public class HealthRecord {
    private int id;
    private float weight;
    private float temperature;
    private String bloodPressure;
    private String note;
    private LocalDate date;
    private int userId;

    /**
     * Constructs a new HealthRecord instance with the provided parameters.
     *
     * @param id            the unique identifier of this health record
     * @param weight        the weight of the patient
     * @param temperature   the temperature of the patient
     * @param bloodPressure the blood pressure of the patient
     * @param note          additional notes about the health record
     * @param date          the date when the health record was recorded
     * @param userId        the unique identifier of the user associated with this health record
     */
    public HealthRecord(int id, float weight, float temperature, String bloodPressure, String note, LocalDate date, int userId) { 
        this.id = id;
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.note = note;
        this.date = date;
        this.userId = userId;
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

    /**
     * Gets the user ID associated with this health record.
     * @return The user ID as an int.  // Updated comment to reflect change to int
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with this health record.
     * @param userId The user ID as an int. 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the unique identifier of this health record.
     * @return The ID as an int.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this health record.
     * @param id The new ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the health record.
     *
     * @return a string representation of the health record
     */
    @Override
    public String toString() {
        return "HealthRecord {" +
                "id=" + id +
                ", weight=" + weight +
                ", temperature=" + temperature +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }

}
