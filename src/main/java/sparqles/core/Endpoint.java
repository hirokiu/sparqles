/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package sparqles.core;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Endpoint extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Endpoint\",\"namespace\":\"sparqles.core\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"datasets\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Dataset\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"label\",\"type\":\"string\"}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence uri;
  @Deprecated public java.util.List<sparqles.core.Dataset> datasets;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public Endpoint() {}

  /**
   * All-args constructor.
   */
  public Endpoint(java.lang.CharSequence uri, java.util.List<sparqles.core.Dataset> datasets) {
    this.uri = uri;
    this.datasets = datasets;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return uri;
    case 1: return datasets;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: uri = (java.lang.CharSequence)value$; break;
    case 1: datasets = (java.util.List<sparqles.core.Dataset>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'uri' field.
   */
  public java.lang.CharSequence getUri() {
    return uri;
  }

  /**
   * Sets the value of the 'uri' field.
   * @param value the value to set.
   */
  public void setUri(java.lang.CharSequence value) {
    this.uri = value;
  }

  /**
   * Gets the value of the 'datasets' field.
   */
  public java.util.List<sparqles.core.Dataset> getDatasets() {
    return datasets;
  }

  /**
   * Sets the value of the 'datasets' field.
   * @param value the value to set.
   */
  public void setDatasets(java.util.List<sparqles.core.Dataset> value) {
    this.datasets = value;
  }

  /** Creates a new Endpoint RecordBuilder */
  public static sparqles.core.Endpoint.Builder newBuilder() {
    return new sparqles.core.Endpoint.Builder();
  }
  
  /** Creates a new Endpoint RecordBuilder by copying an existing Builder */
  public static sparqles.core.Endpoint.Builder newBuilder(sparqles.core.Endpoint.Builder other) {
    return new sparqles.core.Endpoint.Builder(other);
  }
  
  /** Creates a new Endpoint RecordBuilder by copying an existing Endpoint instance */
  public static sparqles.core.Endpoint.Builder newBuilder(sparqles.core.Endpoint other) {
    return new sparqles.core.Endpoint.Builder(other);
  }
  
  /**
   * RecordBuilder for Endpoint instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Endpoint>
    implements org.apache.avro.data.RecordBuilder<Endpoint> {

    private java.lang.CharSequence uri;
    private java.util.List<sparqles.core.Dataset> datasets;

    /** Creates a new Builder */
    private Builder() {
      super(sparqles.core.Endpoint.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(sparqles.core.Endpoint.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.uri)) {
        this.uri = data().deepCopy(fields()[0].schema(), other.uri);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.datasets)) {
        this.datasets = data().deepCopy(fields()[1].schema(), other.datasets);
        fieldSetFlags()[1] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Endpoint instance */
    private Builder(sparqles.core.Endpoint other) {
            super(sparqles.core.Endpoint.SCHEMA$);
      if (isValidValue(fields()[0], other.uri)) {
        this.uri = data().deepCopy(fields()[0].schema(), other.uri);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.datasets)) {
        this.datasets = data().deepCopy(fields()[1].schema(), other.datasets);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'uri' field */
    public java.lang.CharSequence getUri() {
      return uri;
    }
    
    /** Sets the value of the 'uri' field */
    public sparqles.core.Endpoint.Builder setUri(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.uri = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'uri' field has been set */
    public boolean hasUri() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'uri' field */
    public sparqles.core.Endpoint.Builder clearUri() {
      uri = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'datasets' field */
    public java.util.List<sparqles.core.Dataset> getDatasets() {
      return datasets;
    }
    
    /** Sets the value of the 'datasets' field */
    public sparqles.core.Endpoint.Builder setDatasets(java.util.List<sparqles.core.Dataset> value) {
      validate(fields()[1], value);
      this.datasets = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'datasets' field has been set */
    public boolean hasDatasets() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'datasets' field */
    public sparqles.core.Endpoint.Builder clearDatasets() {
      datasets = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    
    public Endpoint build() {
      try {
        Endpoint record = new Endpoint();
        record.uri = fieldSetFlags()[0] ? this.uri : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.datasets = fieldSetFlags()[1] ? this.datasets : (java.util.List<sparqles.core.Dataset>) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
