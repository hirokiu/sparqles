/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package sparqles.analytics.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class DiscoverabilityView extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"DiscoverabilityView\",\"namespace\":\"sparqles.analytics.avro\",\"fields\":[{\"name\":\"endpoint\",\"type\":{\"type\":\"record\",\"name\":\"Endpoint\",\"namespace\":\"sparqles.core\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"datasets\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Dataset\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"label\",\"type\":\"string\"}]}}}]}},{\"name\":\"VoID\",\"type\":\"boolean\"},{\"name\":\"SD\",\"type\":\"boolean\"},{\"name\":\"serverName\",\"type\":\"string\"},{\"name\":\"lastUpdate\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public sparqles.core.Endpoint endpoint;
  @Deprecated public boolean VoID;
  @Deprecated public boolean SD;
  @Deprecated public java.lang.CharSequence serverName;
  @Deprecated public long lastUpdate;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public DiscoverabilityView() {}

  /**
   * All-args constructor.
   */
  public DiscoverabilityView(sparqles.core.Endpoint endpoint, java.lang.Boolean VoID, java.lang.Boolean SD, java.lang.CharSequence serverName, java.lang.Long lastUpdate) {
    this.endpoint = endpoint;
    this.VoID = VoID;
    this.SD = SD;
    this.serverName = serverName;
    this.lastUpdate = lastUpdate;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return endpoint;
    case 1: return VoID;
    case 2: return SD;
    case 3: return serverName;
    case 4: return lastUpdate;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: endpoint = (sparqles.core.Endpoint)value$; break;
    case 1: VoID = (java.lang.Boolean)value$; break;
    case 2: SD = (java.lang.Boolean)value$; break;
    case 3: serverName = (java.lang.CharSequence)value$; break;
    case 4: lastUpdate = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'endpoint' field.
   */
  public sparqles.core.Endpoint getEndpoint() {
    return endpoint;
  }

  /**
   * Sets the value of the 'endpoint' field.
   * @param value the value to set.
   */
  public void setEndpoint(sparqles.core.Endpoint value) {
    this.endpoint = value;
  }

  /**
   * Gets the value of the 'VoID' field.
   */
  public java.lang.Boolean getVoID() {
    return VoID;
  }

  /**
   * Sets the value of the 'VoID' field.
   * @param value the value to set.
   */
  public void setVoID(java.lang.Boolean value) {
    this.VoID = value;
  }

  /**
   * Gets the value of the 'SD' field.
   */
  public java.lang.Boolean getSD() {
    return SD;
  }

  /**
   * Sets the value of the 'SD' field.
   * @param value the value to set.
   */
  public void setSD(java.lang.Boolean value) {
    this.SD = value;
  }

  /**
   * Gets the value of the 'serverName' field.
   */
  public java.lang.CharSequence getServerName() {
    return serverName;
  }

  /**
   * Sets the value of the 'serverName' field.
   * @param value the value to set.
   */
  public void setServerName(java.lang.CharSequence value) {
    this.serverName = value;
  }

  /**
   * Gets the value of the 'lastUpdate' field.
   */
  public java.lang.Long getLastUpdate() {
    return lastUpdate;
  }

  /**
   * Sets the value of the 'lastUpdate' field.
   * @param value the value to set.
   */
  public void setLastUpdate(java.lang.Long value) {
    this.lastUpdate = value;
  }

  /** Creates a new DiscoverabilityView RecordBuilder */
  public static sparqles.analytics.avro.DiscoverabilityView.Builder newBuilder() {
    return new sparqles.analytics.avro.DiscoverabilityView.Builder();
  }
  
  /** Creates a new DiscoverabilityView RecordBuilder by copying an existing Builder */
  public static sparqles.analytics.avro.DiscoverabilityView.Builder newBuilder(sparqles.analytics.avro.DiscoverabilityView.Builder other) {
    return new sparqles.analytics.avro.DiscoverabilityView.Builder(other);
  }
  
  /** Creates a new DiscoverabilityView RecordBuilder by copying an existing DiscoverabilityView instance */
  public static sparqles.analytics.avro.DiscoverabilityView.Builder newBuilder(sparqles.analytics.avro.DiscoverabilityView other) {
    return new sparqles.analytics.avro.DiscoverabilityView.Builder(other);
  }
  
  /**
   * RecordBuilder for DiscoverabilityView instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<DiscoverabilityView>
    implements org.apache.avro.data.RecordBuilder<DiscoverabilityView> {

    private sparqles.core.Endpoint endpoint;
    private boolean VoID;
    private boolean SD;
    private java.lang.CharSequence serverName;
    private long lastUpdate;

    /** Creates a new Builder */
    private Builder() {
      super(sparqles.analytics.avro.DiscoverabilityView.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(sparqles.analytics.avro.DiscoverabilityView.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.endpoint)) {
        this.endpoint = data().deepCopy(fields()[0].schema(), other.endpoint);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.VoID)) {
        this.VoID = data().deepCopy(fields()[1].schema(), other.VoID);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.SD)) {
        this.SD = data().deepCopy(fields()[2].schema(), other.SD);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.serverName)) {
        this.serverName = data().deepCopy(fields()[3].schema(), other.serverName);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.lastUpdate)) {
        this.lastUpdate = data().deepCopy(fields()[4].schema(), other.lastUpdate);
        fieldSetFlags()[4] = true;
      }
    }
    
    /** Creates a Builder by copying an existing DiscoverabilityView instance */
    private Builder(sparqles.analytics.avro.DiscoverabilityView other) {
            super(sparqles.analytics.avro.DiscoverabilityView.SCHEMA$);
      if (isValidValue(fields()[0], other.endpoint)) {
        this.endpoint = data().deepCopy(fields()[0].schema(), other.endpoint);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.VoID)) {
        this.VoID = data().deepCopy(fields()[1].schema(), other.VoID);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.SD)) {
        this.SD = data().deepCopy(fields()[2].schema(), other.SD);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.serverName)) {
        this.serverName = data().deepCopy(fields()[3].schema(), other.serverName);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.lastUpdate)) {
        this.lastUpdate = data().deepCopy(fields()[4].schema(), other.lastUpdate);
        fieldSetFlags()[4] = true;
      }
    }

    /** Gets the value of the 'endpoint' field */
    public sparqles.core.Endpoint getEndpoint() {
      return endpoint;
    }
    
    /** Sets the value of the 'endpoint' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder setEndpoint(sparqles.core.Endpoint value) {
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
    public sparqles.analytics.avro.DiscoverabilityView.Builder clearEndpoint() {
      endpoint = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'VoID' field */
    public java.lang.Boolean getVoID() {
      return VoID;
    }
    
    /** Sets the value of the 'VoID' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder setVoID(boolean value) {
      validate(fields()[1], value);
      this.VoID = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'VoID' field has been set */
    public boolean hasVoID() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'VoID' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder clearVoID() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'SD' field */
    public java.lang.Boolean getSD() {
      return SD;
    }
    
    /** Sets the value of the 'SD' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder setSD(boolean value) {
      validate(fields()[2], value);
      this.SD = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'SD' field has been set */
    public boolean hasSD() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'SD' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder clearSD() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'serverName' field */
    public java.lang.CharSequence getServerName() {
      return serverName;
    }
    
    /** Sets the value of the 'serverName' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder setServerName(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.serverName = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'serverName' field has been set */
    public boolean hasServerName() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'serverName' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder clearServerName() {
      serverName = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'lastUpdate' field */
    public java.lang.Long getLastUpdate() {
      return lastUpdate;
    }
    
    /** Sets the value of the 'lastUpdate' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder setLastUpdate(long value) {
      validate(fields()[4], value);
      this.lastUpdate = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'lastUpdate' field has been set */
    public boolean hasLastUpdate() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'lastUpdate' field */
    public sparqles.analytics.avro.DiscoverabilityView.Builder clearLastUpdate() {
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    public DiscoverabilityView build() {
      try {
        DiscoverabilityView record = new DiscoverabilityView();
        record.endpoint = fieldSetFlags()[0] ? this.endpoint : (sparqles.core.Endpoint) defaultValue(fields()[0]);
        record.VoID = fieldSetFlags()[1] ? this.VoID : (java.lang.Boolean) defaultValue(fields()[1]);
        record.SD = fieldSetFlags()[2] ? this.SD : (java.lang.Boolean) defaultValue(fields()[2]);
        record.serverName = fieldSetFlags()[3] ? this.serverName : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.lastUpdate = fieldSetFlags()[4] ? this.lastUpdate : (java.lang.Long) defaultValue(fields()[4]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}