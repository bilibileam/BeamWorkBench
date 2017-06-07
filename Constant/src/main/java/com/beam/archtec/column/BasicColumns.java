package com.beam.archtec.column;

import java.io.Serializable;

public interface BasicColumns extends Serializable {

    // field name of the field
    String getColumnName();

    // field class of the field
    Class<?> getColumnClass();

}
