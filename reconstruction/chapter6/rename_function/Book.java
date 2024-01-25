import java.util.*;
class Book{
  List<Integer> reservations=new ArrayList<>();
  List<Integer> priorityReservations=new ArrayList<>();
  // 当参数需要修改时，可以采用迁移式做法，从而保留旧函数，并在旧函数调用新函数时，给新增的参数默认值
  public void addReservation(int customerID){
    zz_addReservation(customerID,false);
  }

  public void zz_addReservation(int customerID,boolean isPriority){
      if(isPriority){
          this.priorityReservations.add(customerID);
      }else{
          this.reservations.add(customerID);
      }
  }
}
