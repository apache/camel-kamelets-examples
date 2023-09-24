/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.apache.camel.kamelets.eventhubs.models;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** Fact schema of an order */
@org.apache.avro.specific.AvroGenerated
public class Order extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5320411579412574685L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Order\",\"namespace\":\"org.apache.camel.kamelets.eventhubs.models\",\"doc\":\"Fact schema of an order\",\"fields\":[{\"name\":\"orderId\",\"type\":\"int\",\"doc\":\"Unique id of the order.\"},{\"name\":\"itemId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Id of the ordered item.\"},{\"name\":\"userId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Id of the user who ordered the item.\"},{\"name\":\"quantity\",\"type\":\"double\",\"doc\":\"How much was ordered.\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Order> ENCODER =
      new BinaryMessageEncoder<Order>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Order> DECODER =
      new BinaryMessageDecoder<Order>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Order> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Order> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Order>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Order to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Order from a ByteBuffer. */
  public static Order fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Unique id of the order. */
  @Deprecated public int orderId;
  /** Id of the ordered item. */
  @Deprecated public java.lang.String itemId;
  /** Id of the user who ordered the item. */
  @Deprecated public java.lang.String userId;
  /** How much was ordered. */
  @Deprecated public double quantity;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Order() {}

  /**
   * All-args constructor.
   * @param orderId Unique id of the order.
   * @param itemId Id of the ordered item.
   * @param userId Id of the user who ordered the item.
   * @param quantity How much was ordered.
   */
  public Order(java.lang.Integer orderId, java.lang.String itemId, java.lang.String userId, java.lang.Double quantity) {
    this.orderId = orderId;
    this.itemId = itemId;
    this.userId = userId;
    this.quantity = quantity;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return orderId;
    case 1: return itemId;
    case 2: return userId;
    case 3: return quantity;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: orderId = (java.lang.Integer)value$; break;
    case 1: itemId = (java.lang.String)value$; break;
    case 2: userId = (java.lang.String)value$; break;
    case 3: quantity = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'orderId' field.
   * @return Unique id of the order.
   */
  public java.lang.Integer getOrderId() {
    return orderId;
  }

  /**
   * Sets the value of the 'orderId' field.
   * Unique id of the order.
   * @param value the value to set.
   */
  public void setOrderId(java.lang.Integer value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'itemId' field.
   * @return Id of the ordered item.
   */
  public java.lang.String getItemId() {
    return itemId;
  }

  /**
   * Sets the value of the 'itemId' field.
   * Id of the ordered item.
   * @param value the value to set.
   */
  public void setItemId(java.lang.String value) {
    this.itemId = value;
  }

  /**
   * Gets the value of the 'userId' field.
   * @return Id of the user who ordered the item.
   */
  public java.lang.String getUserId() {
    return userId;
  }

  /**
   * Sets the value of the 'userId' field.
   * Id of the user who ordered the item.
   * @param value the value to set.
   */
  public void setUserId(java.lang.String value) {
    this.userId = value;
  }

  /**
   * Gets the value of the 'quantity' field.
   * @return How much was ordered.
   */
  public java.lang.Double getQuantity() {
    return quantity;
  }

  /**
   * Sets the value of the 'quantity' field.
   * How much was ordered.
   * @param value the value to set.
   */
  public void setQuantity(java.lang.Double value) {
    this.quantity = value;
  }

  /**
   * Creates a new Order RecordBuilder.
   * @return A new Order RecordBuilder
   */
  public static org.apache.camel.kamelets.eventhubs.models.Order.Builder newBuilder() {
    return new org.apache.camel.kamelets.eventhubs.models.Order.Builder();
  }

  /**
   * Creates a new Order RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Order RecordBuilder
   */
  public static org.apache.camel.kamelets.eventhubs.models.Order.Builder newBuilder(org.apache.camel.kamelets.eventhubs.models.Order.Builder other) {
    return new org.apache.camel.kamelets.eventhubs.models.Order.Builder(other);
  }

  /**
   * Creates a new Order RecordBuilder by copying an existing Order instance.
   * @param other The existing instance to copy.
   * @return A new Order RecordBuilder
   */
  public static org.apache.camel.kamelets.eventhubs.models.Order.Builder newBuilder(org.apache.camel.kamelets.eventhubs.models.Order other) {
    return new org.apache.camel.kamelets.eventhubs.models.Order.Builder(other);
  }

  /**
   * RecordBuilder for Order instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Order>
    implements org.apache.avro.data.RecordBuilder<Order> {

    /** Unique id of the order. */
    private int orderId;
    /** Id of the ordered item. */
    private java.lang.String itemId;
    /** Id of the user who ordered the item. */
    private java.lang.String userId;
    /** How much was ordered. */
    private double quantity;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.camel.kamelets.eventhubs.models.Order.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.itemId)) {
        this.itemId = data().deepCopy(fields()[1].schema(), other.itemId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.quantity)) {
        this.quantity = data().deepCopy(fields()[3].schema(), other.quantity);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Order instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.camel.kamelets.eventhubs.models.Order other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.itemId)) {
        this.itemId = data().deepCopy(fields()[1].schema(), other.itemId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.quantity)) {
        this.quantity = data().deepCopy(fields()[3].schema(), other.quantity);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'orderId' field.
      * Unique id of the order.
      * @return The value.
      */
    public java.lang.Integer getOrderId() {
      return orderId;
    }

    /**
      * Sets the value of the 'orderId' field.
      * Unique id of the order.
      * @param value The value of 'orderId'.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder setOrderId(int value) {
      validate(fields()[0], value);
      this.orderId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * Unique id of the order.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'orderId' field.
      * Unique id of the order.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder clearOrderId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'itemId' field.
      * Id of the ordered item.
      * @return The value.
      */
    public java.lang.String getItemId() {
      return itemId;
    }

    /**
      * Sets the value of the 'itemId' field.
      * Id of the ordered item.
      * @param value The value of 'itemId'.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder setItemId(java.lang.String value) {
      validate(fields()[1], value);
      this.itemId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'itemId' field has been set.
      * Id of the ordered item.
      * @return True if the 'itemId' field has been set, false otherwise.
      */
    public boolean hasItemId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'itemId' field.
      * Id of the ordered item.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder clearItemId() {
      itemId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'userId' field.
      * Id of the user who ordered the item.
      * @return The value.
      */
    public java.lang.String getUserId() {
      return userId;
    }

    /**
      * Sets the value of the 'userId' field.
      * Id of the user who ordered the item.
      * @param value The value of 'userId'.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder setUserId(java.lang.String value) {
      validate(fields()[2], value);
      this.userId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'userId' field has been set.
      * Id of the user who ordered the item.
      * @return True if the 'userId' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'userId' field.
      * Id of the user who ordered the item.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder clearUserId() {
      userId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'quantity' field.
      * How much was ordered.
      * @return The value.
      */
    public java.lang.Double getQuantity() {
      return quantity;
    }

    /**
      * Sets the value of the 'quantity' field.
      * How much was ordered.
      * @param value The value of 'quantity'.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder setQuantity(double value) {
      validate(fields()[3], value);
      this.quantity = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'quantity' field has been set.
      * How much was ordered.
      * @return True if the 'quantity' field has been set, false otherwise.
      */
    public boolean hasQuantity() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'quantity' field.
      * How much was ordered.
      * @return This builder.
      */
    public org.apache.camel.kamelets.eventhubs.models.Order.Builder clearQuantity() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Order build() {
      try {
        Order record = new Order();
        record.orderId = fieldSetFlags()[0] ? this.orderId : (java.lang.Integer) defaultValue(fields()[0]);
        record.itemId = fieldSetFlags()[1] ? this.itemId : (java.lang.String) defaultValue(fields()[1]);
        record.userId = fieldSetFlags()[2] ? this.userId : (java.lang.String) defaultValue(fields()[2]);
        record.quantity = fieldSetFlags()[3] ? this.quantity : (java.lang.Double) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Order>
    WRITER$ = (org.apache.avro.io.DatumWriter<Order>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Order>
    READER$ = (org.apache.avro.io.DatumReader<Order>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
