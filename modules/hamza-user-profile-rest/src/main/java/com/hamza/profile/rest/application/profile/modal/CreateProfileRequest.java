package com.hamza.profile.rest.application.profile.modal;

import com.google.gson.Gson;

public class CreateProfileRequest {

    private String firstName;
    private String secondName;
    private String lastName;
    private String firstNameInEnglish;
    private String secondNameInEnglish;
    private String lastNameInEnglish;
    private String emailId;
    private String password;
    private String phoneExtension;
    private String phoneNumber;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private String nationality;
    private String motherTongue;
    private String proofName;
    private String passportNumber;
    private String university;
    private String lastEducationalQualification;
    private String academicSpecialization;
    private String primaryLanguageOfEducation;
    private String timeZone;
    private String country;
    private String state;
    private String province;
    private String city;
    private String street;
    private String postalCode;

    // OTP gate for self-service profile edits (ignored by sign-up). The user
    // requests a code via /request-profile-otp, then submits it here so the
    // update can be verified server-side before changes are applied.
    private String mfaToken;
    private String otp;

    public String getFirstNameInEnglish() {
        return firstNameInEnglish;
    }

    public void setFirstNameInEnglish(String firstNameInEnglish) {
        this.firstNameInEnglish = firstNameInEnglish;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondNameInEnglish() {
        return secondNameInEnglish;
    }

    public void setSecondNameInEnglish(String secondNameInEnglish) {
        this.secondNameInEnglish = secondNameInEnglish;
    }

    public String getLastNameInEnglish() {
        return lastNameInEnglish;
    }

    public void setLastNameInEnglish(String lastNameInEnglish) {
        this.lastNameInEnglish = lastNameInEnglish;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneExtension() {
        return phoneExtension;
    }

    public void setPhoneExtension(String phoneExtension) {
        this.phoneExtension = phoneExtension;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public String getProofName() {
        return proofName;
    }

    public void setProofName(String proofName) {
        this.proofName = proofName;
    }

    public String getLastEducationalQualification() {
        return lastEducationalQualification;
    }

    public void setLastEducationalQualification(String lastEducationalQualification) {
        this.lastEducationalQualification = lastEducationalQualification;
    }

    public String getAcademicSpecialization() {
        return academicSpecialization;
    }

    public void setAcademicSpecialization(String academicSpecialization) {
        this.academicSpecialization = academicSpecialization;
    }

    public String getPrimaryLanguageOfEducation() {
        return primaryLanguageOfEducation;
    }

    public void setPrimaryLanguageOfEducation(String primaryLanguageOfEducation) {
        this.primaryLanguageOfEducation = primaryLanguageOfEducation;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMfaToken() {
        return mfaToken;
    }

    public void setMfaToken(String mfaToken) {
        this.mfaToken = mfaToken;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
