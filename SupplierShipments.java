/*
  Name: Robert Schwyzer
  Course: CNT 4714 -- Fall 2020 -- Project Four
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: December 4, 2020
*/

/**
 * Simple class which stores a tuple of a supplier and a shipment count associated with that supplier
 */
public class SupplierShipment {
  String supplier;
  int shipments;

  /**
   * Constructor
   * @param s String supplier
   * @param sh  int shipments
   */
  public SupplierShipments(String s, int sh) {
    supplier = s;
    shipments = sh;
  }

  /**
   * @return String supplier
   */
  public String getSupplier() {
    return supplier;
  }

  /**
   * @return int shipments
   */
  public int getShipments() {
    return shipments;
  }

  /**
   * Compare two SupplierShipment objects
   * @param other SupplierShipment object
   * @return true if objects have the same supplier ID and shipment count
   */
  public boolean compare(SupplierShipment other) {
    return (supplier.equals(other.getSupplier()) && shipments == other.getShipments());
  }
}