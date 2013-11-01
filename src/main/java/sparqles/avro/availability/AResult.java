/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package sparqles.avro.availability;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class AResult extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AResult\",\"namespace\":\"sparqles.avro.availability\",\"fields\":[{\"name\":\"endpointResult\",\"type\":{\"type\":\"record\",\"name\":\"EndpointResult\",\"namespace\":\"sparqles.avro\",\"fields\":[{\"name\":\"endpoint\",\"type\":{\"type\":\"record\",\"name\":\"Endpoint\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"datasets\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Dataset\",\"fields\":[{\"name\":\"uri\",\"type\":\"string\"},{\"name\":\"label\",\"type\":\"string\"}]}}}]}},{\"name\":\"start\",\"type\":\"long\"},{\"name\":\"end\",\"type\":\"long\"}],\"import\":\"Endpoint.avsc\"}},{\"name\":\"responseTime\",\"type\":\"long\"},{\"name\":\"isAvailable\",\"type\":\"boolean\",\"default\":false},{\"name\":\"isPrivate\",\"type\":\"boolean\",\"default\":false},{\"name\":\"Exception\",\"type\":[\"string\",\"null\"]},{\"name\":\"explanation\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public sparqles.avro.EndpointResult endpointResult;
  @Deprecated public long responseTime;
  @Deprecated public boolean isAvailable;
  @Deprecated public boolean isPrivate;
  @Deprecated public java.lang.CharSequence Exception;
  @Deprecated public java.lang.CharSequence explanation;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public AResult() {}

  /**
   * All-args constructor.
   */
  public AResult(sparqles.avro.EndpointResult endpointResult, java.lang.Long responseTime, java.lang.Boolean isAvailable, java.lang.Boolean isPrivate, java.lang.CharSequence Exception, java.lang.CharSequence explanation) {
    this.endpointResult = endpointResult;
    this.responseTime = responseTime;
    this.isAvailable = isAvailable;
    this.isPrivate = isPrivate;
    this.Exception = Exception;
    this.explanation = explanation;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return endpointResult;
    case 1: return responseTime;
    case 2: return isAvailable;
    case 3: return isPrivate;
    case 4: return Exception;
    case 5: return explanation;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: endpointResult = (sparqles.avro.EndpointResult)value$; break;
    case 1: responseTime = (java.lang.Long)value$; break;
    case 2: isAvailable = (java.lang.Boolean)value$; break;
    case 3: isPrivate = (java.lang.Boolean)value$; break;
    case 4: Exception = (java.lang.CharSequence)value$; break;
    case 5: explanation = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'endpointResult' field.
   */
  public sparqles.avro.EndpointResult getEndpointResult() {
    return endpointResult;
  }

  /**
   * Sets the value of the 'endpointResult' field.
   * @param value the value to set.
   */
  public void setEndpointResult(sparqles.avro.EndpointResult value) {
    this.endpointResult = value;
  }

  /**
   * Gets the value of the 'responseTime' field.
   */
  public java.lang.Long getResponseTime() {
    return responseTime;
  }

  /**
   * Sets the value of the 'responseTime' field.
   * @param value the value to set.
   */
  public void setResponseTime(java.lang.Long value) {
    this.responseTime = value;
  }

  /**
   * Gets the value of the 'isAvailable' field.
   */
  public java.lang.Boolean getIsAvailable() {
    return isAvailable;
  }

  /**
   * Sets the value of the 'isAvailable' field.
   * @param value the value to set.
   */
  public void setIsAvailable(java.lang.Boolean value) {
    this.isAvailable = value;
  }

  /**
   * Gets the value of the 'isPrivate' field.
   */
  public java.lang.Boolean getIsPrivate() {
    return isPrivate;
  }

  /**
   * Sets the value of the 'isPrivate' field.
   * @param value the value to set.
   */
  public void setIsPrivate(java.lang.Boolean value) {
    this.isPrivate = value;
  }

  /**
   * Gets the value of the 'Exception' field.
   */
  public java.lang.CharSequence getException() {
    return Exception;
  }

  /**
   * Sets the value of the 'Exception' field.
   * @param value the value to set.
   */
  public void setException(java.lang.CharSequence value) {
    this.Exception = value;
  }

  /**
   * Gets the value of the 'explanation' field.
   */
  public java.lang.CharSequence getExplanation() {
    return explanation;
  }

  /**
   * Sets the value of the 'explanation' field.
   * @param value the value to set.
   */
  public void setExplanation(java.lang.CharSequence value) {
    this.explanation = value;
  }

  /** Creates a new AResult RecordBuilder */
  public static sparqles.avro.availability.AResult.Builder newBuilder() {
    return new sparqles.avro.availability.AResult.Builder();
  }
  
  /** Creates a new AResult RecordBuilder by copying an existing Builder */
  public static sparqles.avro.availability.AResult.Builder newBuilder(sparqles.avro.availability.AResult.Builder other) {
    return new sparqles.avro.availability.AResult.Builder(other);
  }
  
  /** Creates a new AResult RecordBuilder by copying an existing AResult instance */
  public static sparqles.avro.availability.AResult.Builder newBuilder(sparqles.avro.availability.AResult other) {
    return new sparqles.avro.availability.AResult.Builder(other);
  }
  
  /**
   * RecordBuilder for AResult instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AResult>
    implements org.apache.avro.data.RecordBuilder<AResult> {

    private sparqles.avro.EndpointResult endpointResult;
    private long responseTime;
    private boolean isAvailable;
    private boolean isPrivate;
    private java.lang.CharSequence Exception;
    private java.lang.CharSequence explanation;

    /** Creates a new Builder */
    private Builder() {
      super(sparqles.avro.availability.AResult.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(sparqles.avro.availability.AResult.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.endpointResult)) {
        this.endpointResult = data().deepCopy(fields()[0].schema(), other.endpointResult);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.responseTime)) {
        this.responseTime = data().deepCopy(fields()[1].schema(), other.responseTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.isAvailable)) {
        this.isAvailable = data().deepCopy(fields()[2].schema(), other.isAvailable);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.isPrivate)) {
        this.isPrivate = data().deepCopy(fields()[3].schema(), other.isPrivate);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.Exception)) {
        this.Exception = data().deepCopy(fields()[4].schema(), other.Exception);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.explanation)) {
        this.explanation = data().deepCopy(fields()[5].schema(), other.explanation);
        fieldSetFlags()[5] = true;
      }
    }
    
    /** Creates a Builder by copying an existing AResult instance */
    private Builder(sparqles.avro.availability.AResult other) {
            super(sparqles.avro.availability.AResult.SCHEMA$);
      if (isValidValue(fields()[0], other.endpointResult)) {
        this.endpointResult = data().deepCopy(fields()[0].schema(), other.endpointResult);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.responseTime)) {
        this.responseTime = data().deepCopy(fields()[1].schema(), other.responseTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.isAvailable)) {
        this.isAvailable = data().deepCopy(fields()[2].schema(), other.isAvailable);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.isPrivate)) {
        this.isPrivate = data().deepCopy(fields()[3].schema(), other.isPrivate);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.Exception)) {
        this.Exception = data().deepCopy(fields()[4].schema(), other.Exception);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.explanation)) {
        this.explanation = data().deepCopy(fields()[5].schema(), other.explanation);
        fieldSetFlags()[5] = true;
      }
    }

    /** Gets the value of the 'endpointResult' field */
    public sparqles.avro.EndpointResult getEndpointResult() {
      return endpointResult;
    }
    
    /** Sets the value of the 'endpointResult' field */
    public sparqles.avro.availability.AResult.Builder setEndpointResult(sparqles.avro.EndpointResult value) {
      validate(fields()[0], value);
      this.endpointResult = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'endpointResult' field has been set */
    public boolean hasEndpointResult() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'endpointResult' field */
    public sparqles.avro.availability.AResult.Builder clearEndpointResult() {
      endpointResult = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'responseTime' field */
    public java.lang.Long getResponseTime() {
      return responseTime;
    }
    
    /** Sets the value of the 'responseTime' field */
    public sparqles.avro.availability.AResult.Builder setResponseTime(long value) {
      validate(fields()[1], value);
      this.responseTime = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'responseTime' field has been set */
    public boolean hasResponseTime() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'responseTime' field */
    public sparqles.avro.availability.AResult.Builder clearResponseTime() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'isAvailable' field */
    public java.lang.Boolean getIsAvailable() {
      return isAvailable;
    }
    
    /** Sets the value of the 'isAvailable' field */
    public sparqles.avro.availability.AResult.Builder setIsAvailable(boolean value) {
      validate(fields()[2], value);
      this.isAvailable = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'isAvailable' field has been set */
    public boolean hasIsAvailable() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'isAvailable' field */
    public sparqles.avro.availability.AResult.Builder clearIsAvailable() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'isPrivate' field */
    public java.lang.Boolean getIsPrivate() {
      return isPrivate;
    }
    
    /** Sets the value of the 'isPrivate' field */
    public sparqles.avro.availability.AResult.Builder setIsPrivate(boolean value) {
      validate(fields()[3], value);
      this.isPrivate = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'isPrivate' field has been set */
    public boolean hasIsPrivate() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'isPrivate' field */
    public sparqles.avro.availability.AResult.Builder clearIsPrivate() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'Exception' field */
    public java.lang.CharSequence getException() {
      return Exception;
    }
    
    /** Sets the value of the 'Exception' field */
    public sparqles.avro.availability.AResult.Builder setException(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.Exception = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'Exception' field has been set */
    public boolean hasException() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'Exception' field */
    public sparqles.avro.availability.AResult.Builder clearException() {
      Exception = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'explanation' field */
    public java.lang.CharSequence getExplanation() {
      return explanation;
    }
    
    /** Sets the value of the 'explanation' field */
    public sparqles.avro.availability.AResult.Builder setExplanation(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.explanation = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'explanation' field has been set */
    public boolean hasExplanation() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'explanation' field */
    public sparqles.avro.availability.AResult.Builder clearExplanation() {
      explanation = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    public AResult build() {
      try {
        AResult record = new AResult();
        record.endpointResult = fieldSetFlags()[0] ? this.endpointResult : (sparqles.avro.EndpointResult) defaultValue(fields()[0]);
        record.responseTime = fieldSetFlags()[1] ? this.responseTime : (java.lang.Long) defaultValue(fields()[1]);
        record.isAvailable = fieldSetFlags()[2] ? this.isAvailable : (java.lang.Boolean) defaultValue(fields()[2]);
        record.isPrivate = fieldSetFlags()[3] ? this.isPrivate : (java.lang.Boolean) defaultValue(fields()[3]);
        record.Exception = fieldSetFlags()[4] ? this.Exception : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.explanation = fieldSetFlags()[5] ? this.explanation : (java.lang.CharSequence) defaultValue(fields()[5]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
