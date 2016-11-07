/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

/**
 *
 * @author Mauricio
 */
public class DynamicField {
    private String label; // label of the field
    private String fieldKey; // some key to identify the field
    private Object fieldValue; // the value of field
    private String type; // can be input,radio,selectbox etc
    private int id; // can be input,radio,selectbox etc

    public DynamicField(String label, String fieldKey, Object fieldValue, String type) {
        this.label = label;
        this.fieldKey = fieldKey;
        this.fieldValue = fieldValue;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DynamicField(String label, String fieldKey, Object fieldValue, String type, int id) {
        this.label = label;
        this.fieldKey = fieldKey;
        this.fieldValue = fieldValue;
        this.type = type;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
