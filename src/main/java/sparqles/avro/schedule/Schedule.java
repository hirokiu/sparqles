/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package sparqles.avro.schedule;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Schedule extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Schedule\",\"namespace\":\"sparqles.avro.schedule\",\"fields\":[{\"name\":\"endpoint\",\"type\":{\"type\":\"record\",\"name\":\"Endpoint\",\"namespace\":\"sparqles.avro\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"datasets\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Dataset\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"label\",\"type\":\"string\"}]}}}]}},{\"name\":\"ATask\",\"type\":[\"string\",\"null\"]},{\"name\":\"FTask\",\"type\":[\"string\",\"null\"]},{\"name\":\"PTask\",\"type\":[\"string\",\"null\"]},{\"name\":\"DTask\",\"type\":[\"string\",\"null\"]},{\"name\":\"ITask\",\"type\":[\"string\",\"null\"]},{\"name\":\"ETask\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public sparqles.avro.Endpoint endpoint;
  @Deprecated public java.lang.CharSequence ATask;
  @Deprecated public java.lang.CharSequence FTask;
  @Deprecated public java.lang.CharSequence PTask;
  @Deprecated public java.lang.CharSequence DTask;
  @Deprecated public java.lang.CharSequence ITask;
  @Deprecated public java.lang.CharSequence ETask;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public Schedule() {}

  /**
   * All-args constructor.
   */
  public Schedule(sparqles.avro.Endpoint endpoint, java.lang.CharSequence ATask, java.lang.CharSequence FTask, java.lang.CharSequence PTask, java.lang.CharSequence DTask, java.lang.CharSequence ITask, java.lang.CharSequence ETask) {
    this.endpoint = endpoint;
    this.ATask = ATask;
    this.FTask = FTask;
    this.PTask = PTask;
    this.DTask = DTask;
    this.ITask = ITask;
    this.ETask = ETask;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return endpoint;
    case 1: return ATask;
    case 2: return FTask;
    case 3: return PTask;
    case 4: return DTask;
    case 5: return ITask;
    case 6: return ETask;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: endpoint = (sparqles.avro.Endpoint)value$; break;
    case 1: ATask = (java.lang.CharSequence)value$; break;
    case 2: FTask = (java.lang.CharSequence)value$; break;
    case 3: PTask = (java.lang.CharSequence)value$; break;
    case 4: DTask = (java.lang.CharSequence)value$; break;
    case 5: ITask = (java.lang.CharSequence)value$; break;
    case 6: ETask = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'endpoint' field.
   */
  public sparqles.avro.Endpoint getEndpoint() {
    return endpoint;
  }

  /**
   * Sets the value of the 'endpoint' field.
   * @param value the value to set.
   */
  public void setEndpoint(sparqles.avro.Endpoint value) {
    this.endpoint = value;
  }

  /**
   * Gets the value of the 'ATask' field.
   */
  public java.lang.CharSequence getATask() {
    return ATask;
  }

  /**
   * Sets the value of the 'ATask' field.
   * @param value the value to set.
   */
  public void setATask(java.lang.CharSequence value) {
    this.ATask = value;
  }

  /**
   * Gets the value of the 'FTask' field.
   */
  public java.lang.CharSequence getFTask() {
    return FTask;
  }

  /**
   * Sets the value of the 'FTask' field.
   * @param value the value to set.
   */
  public void setFTask(java.lang.CharSequence value) {
    this.FTask = value;
  }

  /**
   * Gets the value of the 'PTask' field.
   */
  public java.lang.CharSequence getPTask() {
    return PTask;
  }

  /**
   * Sets the value of the 'PTask' field.
   * @param value the value to set.
   */
  public void setPTask(java.lang.CharSequence value) {
    this.PTask = value;
  }

  /**
   * Gets the value of the 'DTask' field.
   */
  public java.lang.CharSequence getDTask() {
    return DTask;
  }

  /**
   * Sets the value of the 'DTask' field.
   * @param value the value to set.
   */
  public void setDTask(java.lang.CharSequence value) {
    this.DTask = value;
  }

  /**
   * Gets the value of the 'ITask' field.
   */
  public java.lang.CharSequence getITask() {
    return ITask;
  }

  /**
   * Sets the value of the 'ITask' field.
   * @param value the value to set.
   */
  public void setITask(java.lang.CharSequence value) {
    this.ITask = value;
  }

  /**
   * Gets the value of the 'ETask' field.
   */
  public java.lang.CharSequence getETask() {
    return ETask;
  }

  /**
   * Sets the value of the 'ETask' field.
   * @param value the value to set.
   */
  public void setETask(java.lang.CharSequence value) {
    this.ETask = value;
  }

  /** Creates a new Schedule RecordBuilder */
  public static sparqles.avro.schedule.Schedule.Builder newBuilder() {
    return new sparqles.avro.schedule.Schedule.Builder();
  }
  
  /** Creates a new Schedule RecordBuilder by copying an existing Builder */
  public static sparqles.avro.schedule.Schedule.Builder newBuilder(sparqles.avro.schedule.Schedule.Builder other) {
    return new sparqles.avro.schedule.Schedule.Builder(other);
  }
  
  /** Creates a new Schedule RecordBuilder by copying an existing Schedule instance */
  public static sparqles.avro.schedule.Schedule.Builder newBuilder(sparqles.avro.schedule.Schedule other) {
    return new sparqles.avro.schedule.Schedule.Builder(other);
  }
  
  /**
   * RecordBuilder for Schedule instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Schedule>
    implements org.apache.avro.data.RecordBuilder<Schedule> {

    private sparqles.avro.Endpoint endpoint;
    private java.lang.CharSequence ATask;
    private java.lang.CharSequence FTask;
    private java.lang.CharSequence PTask;
    private java.lang.CharSequence DTask;
    private java.lang.CharSequence ITask;
    private java.lang.CharSequence ETask;

    /** Creates a new Builder */
    private Builder() {
      super(sparqles.avro.schedule.Schedule.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(sparqles.avro.schedule.Schedule.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.endpoint)) {
        this.endpoint = data().deepCopy(fields()[0].schema(), other.endpoint);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ATask)) {
        this.ATask = data().deepCopy(fields()[1].schema(), other.ATask);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.FTask)) {
        this.FTask = data().deepCopy(fields()[2].schema(), other.FTask);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.PTask)) {
        this.PTask = data().deepCopy(fields()[3].schema(), other.PTask);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.DTask)) {
        this.DTask = data().deepCopy(fields()[4].schema(), other.DTask);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.ITask)) {
        this.ITask = data().deepCopy(fields()[5].schema(), other.ITask);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.ETask)) {
        this.ETask = data().deepCopy(fields()[6].schema(), other.ETask);
        fieldSetFlags()[6] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Schedule instance */
    private Builder(sparqles.avro.schedule.Schedule other) {
            super(sparqles.avro.schedule.Schedule.SCHEMA$);
      if (isValidValue(fields()[0], other.endpoint)) {
        this.endpoint = data().deepCopy(fields()[0].schema(), other.endpoint);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ATask)) {
        this.ATask = data().deepCopy(fields()[1].schema(), other.ATask);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.FTask)) {
        this.FTask = data().deepCopy(fields()[2].schema(), other.FTask);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.PTask)) {
        this.PTask = data().deepCopy(fields()[3].schema(), other.PTask);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.DTask)) {
        this.DTask = data().deepCopy(fields()[4].schema(), other.DTask);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.ITask)) {
        this.ITask = data().deepCopy(fields()[5].schema(), other.ITask);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.ETask)) {
        this.ETask = data().deepCopy(fields()[6].schema(), other.ETask);
        fieldSetFlags()[6] = true;
      }
    }

    /** Gets the value of the 'endpoint' field */
    public sparqles.avro.Endpoint getEndpoint() {
      return endpoint;
    }
    
    /** Sets the value of the 'endpoint' field */
    public sparqles.avro.schedule.Schedule.Builder setEndpoint(sparqles.avro.Endpoint value) {
      validate(fields()[0], value);
      this.endpoint = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'endpoint' field has been set */
    public boolean hasEndpoint() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'endpoint' field */
    public sparqles.avro.schedule.Schedule.Builder clearEndpoint() {
      endpoint = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'ATask' field */
    public java.lang.CharSequence getATask() {
      return ATask;
    }
    
    /** Sets the value of the 'ATask' field */
    public sparqles.avro.schedule.Schedule.Builder setATask(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.ATask = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'ATask' field has been set */
    public boolean hasATask() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'ATask' field */
    public sparqles.avro.schedule.Schedule.Builder clearATask() {
      ATask = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'FTask' field */
    public java.lang.CharSequence getFTask() {
      return FTask;
    }
    
    /** Sets the value of the 'FTask' field */
    public sparqles.avro.schedule.Schedule.Builder setFTask(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.FTask = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'FTask' field has been set */
    public boolean hasFTask() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'FTask' field */
    public sparqles.avro.schedule.Schedule.Builder clearFTask() {
      FTask = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'PTask' field */
    public java.lang.CharSequence getPTask() {
      return PTask;
    }
    
    /** Sets the value of the 'PTask' field */
    public sparqles.avro.schedule.Schedule.Builder setPTask(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.PTask = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'PTask' field has been set */
    public boolean hasPTask() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'PTask' field */
    public sparqles.avro.schedule.Schedule.Builder clearPTask() {
      PTask = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'DTask' field */
    public java.lang.CharSequence getDTask() {
      return DTask;
    }
    
    /** Sets the value of the 'DTask' field */
    public sparqles.avro.schedule.Schedule.Builder setDTask(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.DTask = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'DTask' field has been set */
    public boolean hasDTask() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'DTask' field */
    public sparqles.avro.schedule.Schedule.Builder clearDTask() {
      DTask = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'ITask' field */
    public java.lang.CharSequence getITask() {
      return ITask;
    }
    
    /** Sets the value of the 'ITask' field */
    public sparqles.avro.schedule.Schedule.Builder setITask(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.ITask = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'ITask' field has been set */
    public boolean hasITask() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'ITask' field */
    public sparqles.avro.schedule.Schedule.Builder clearITask() {
      ITask = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'ETask' field */
    public java.lang.CharSequence getETask() {
      return ETask;
    }
    
    /** Sets the value of the 'ETask' field */
    public sparqles.avro.schedule.Schedule.Builder setETask(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.ETask = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'ETask' field has been set */
    public boolean hasETask() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'ETask' field */
    public sparqles.avro.schedule.Schedule.Builder clearETask() {
      ETask = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    public Schedule build() {
      try {
        Schedule record = new Schedule();
        record.endpoint = fieldSetFlags()[0] ? this.endpoint : (sparqles.avro.Endpoint) defaultValue(fields()[0]);
        record.ATask = fieldSetFlags()[1] ? this.ATask : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.FTask = fieldSetFlags()[2] ? this.FTask : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.PTask = fieldSetFlags()[3] ? this.PTask : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.DTask = fieldSetFlags()[4] ? this.DTask : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.ITask = fieldSetFlags()[5] ? this.ITask : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.ETask = fieldSetFlags()[6] ? this.ETask : (java.lang.CharSequence) defaultValue(fields()[6]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
