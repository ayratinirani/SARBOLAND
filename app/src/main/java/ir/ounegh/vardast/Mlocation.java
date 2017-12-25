package ir.ounegh.vardast;

import android.location.Location;

/**
 * Created by aseme on 13/12/2017.
 */

public class Mlocation  {
  String name;
  String phone;
  String category;
  int id;

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public int getVijeh() {
    return vijeh;
  }

  public void setVijeh(int vijeh) {
    this.vijeh = vijeh;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  private double distance;

  private int vijeh;
  private String address;
  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  int active;
  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  double latitude;
  double longitude;
    public Mlocation(){

    }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Mlocation{" +
            "name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", category='" + category + '\'' +
            '}';
  }
}
