package mybatiesDemo.model;


import javax.xml.crypto.Data;

public class Orders {

  private Integer id;
  private Integer user_Id;
  private String number;
  private Data createtime;
  private String note;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUser_Id() {
    return user_Id;
  }

  public void setUser_Id(Integer user_Id) {
    this.user_Id = user_Id;
  }


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  public Data getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Data createtime) {
    this.createtime = createtime;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
