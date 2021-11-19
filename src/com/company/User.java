package com.company;
public class User extends Person implements Registration,Show_Rating {
    private String Source;
    private String Destination;

    //setters
    public void setSource(String source){
        this.Source = source;
    }
    private void setDestination(String destination){
        this.Destination = destination;
    }
    //getters
    public String getSource(){
        return this.Source;
    }
    private String getDestination(){
        return this.Destination ;
    }
//Methods
@Override
public void Register() {

}
    void Requset(String source,String destination){

          if(source.equals(destination)){
              System.out.println("error! Please enter the correct path");
          }else{
              this.Source = source;
              this.Destination =destination;
          }
    }
    void Rate_Driver(){ }
    @Override
    public void Show() {

    }
}
