public class Seat{
  private int id;
  private boolean isOccupied;

  Public Seat(int id, boolean isOccupied){
    this.id = id;
    this.isOccupied = isOccupied;
  }
  Public Seat(){
    
  }
  public int getId(){
    return this.id;
  }
  public boolean getIsOccupied(){
    return this.isOccupied;
  }
  public void setId(int id){
    this.id = id;
  }
  public void setIsOccupied(boolean isOccupied){
    this.isOccupied = isOccupied;
  }
}