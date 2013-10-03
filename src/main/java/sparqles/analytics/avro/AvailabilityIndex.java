/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package sparqles.analytics.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class AvailabilityIndex extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvailabilityIndex\",\"namespace\":\"sparqles.analytics.avro\",\"fields\":[{\"name\":\"key\",\"type\":\"string\"},{\"name\":\"values\",\"type\":{\"type\":\"map\",\"values\":\"double\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence key;
  @Deprecated public java.util.Map<java.lang.CharSequence,java.lang.Double> values;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public AvailabilityIndex() {}

  /**
   * All-args constructor.
   */
  public AvailabilityIndex(java.lang.CharSequence key, java.util.Map<java.lang.CharSequence,java.lang.Double> values) {
    this.key = key;
    this.values = values;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return key;
    case 1: return values;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: key = (java.lang.CharSequence)value$; break;
    case 1: values = (java.util.Map<java.lang.CharSequence,java.lang.Double>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'key' field.
   */
  public java.lang.CharSequence getKey() {
    return key;
  }

  /**
   * Sets the value of the 'key' field.
   * @param value the value to set.
   */
  public void setKey(java.lang.CharSequence value) {
    this.key = value;
  }

  /**
   * Gets the value of the 'values' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.Double> getValues() {
    return values;
  }

  /**
   * Sets the value of the 'values' field.
   * @param value the value to set.
   */
  public void setValues(java.util.Map<java.lang.CharSequence,java.lang.Double> value) {
    this.values = value;
  }

  /** Creates a new AvailabilityIndex RecordBuilder */
  public static sparqles.analytics.avro.AvailabilityIndex.Builder newBuilder() {
    return new sparqles.analytics.avro.AvailabilityIndex.Builder();
  }
  
  /** Creates a new AvailabilityIndex RecordBuilder by copying an existing Builder */
  public static sparqles.analytics.avro.AvailabilityIndex.Builder newBuilder(sparqles.analytics.avro.AvailabilityIndex.Builder other) {
    return new sparqles.analytics.avro.AvailabilityIndex.Builder(other);
  }
  
  /** Creates a new AvailabilityIndex RecordBuilder by copying an existing AvailabilityIndex instance */
  public static sparqles.analytics.avro.AvailabilityIndex.Builder newBuilder(sparqles.analytics.avro.AvailabilityIndex other) {
    return new sparqles.analytics.avro.AvailabilityIndex.Builder(other);
  }
  
  /**
   * RecordBuilder for AvailabilityIndex instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvailabilityIndex>
    implements org.apache.avro.data.RecordBuilder<AvailabilityIndex> {

    private java.lang.CharSequence key;
    private java.util.Map<java.lang.CharSequence,java.lang.Double> values;

    /** Creates a new Builder */
    private Builder() {
      super(sparqles.analytics.avro.AvailabilityIndex.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(sparqles.analytics.avro.AvailabilityIndex.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.key)) {
        this.key = data().deepCopy(fields()[0].schema(), other.key);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.values)) {
        this.values = data().deepCopy(fields()[1].schema(), other.values);
        fieldSetFlags()[1] = true;
      }
    }
    
    /** Creates a Builder by copying an existing AvailabilityIndex instance */
    private Builder(sparqles.analytics.avro.AvailabilityIndex other) {
            super(sparqles.analytics.avro.AvailabilityIndex.SCHEMA$);
      if (isValidValue(fields()[0], other.key)) {
        this.key = data().deepCopy(fields()[0].schema(), other.key);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.values)) {
        this.values = data().deepCopy(fields()[1].schema(), other.values);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'key' field */
    public java.lang.CharSequence getKey() {
      return key;
    }
    
    /** Sets the value of the 'key' field */
    public sparqles.analytics.avro.AvailabilityIndex.Builder setKey(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.key = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'key' field has been set */
    public boolean hasKey() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'key' field */
    public sparqles.analytics.avro.AvailabilityIndex.Builder clearKey() {
      key = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'values' field */
    public java.util.Map<java.lang.CharSequence,java.lang.Double> getValues() {
      return values;
    }
    
    /** Sets the value of the 'values' field */
    public sparqles.analytics.avro.AvailabilityIndex.Builder setValues(java.util.Map<java.lang.CharSequence,java.lang.Double> value) {
      validate(fields()[1], value);
      this.values = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'values' field has been set */
    public boolean hasValues() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'values' field */
    public sparqles.analytics.avro.AvailabilityIndex.Builder clearValues() {
      values = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    public AvailabilityIndex build() {
      try {
        AvailabilityIndex record = new AvailabilityIndex();
        record.key = fieldSetFlags()[0] ? this.key : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.values = fieldSetFlags()[1] ? this.values : (java.util.Map<java.lang.CharSequence,java.lang.Double>) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
