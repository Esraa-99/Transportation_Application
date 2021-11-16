public class User implements Registration,Show_Rating extends Person {
    private String Source;
    private String Destination;

    //setters
    private void setSource(float source){
        this.Source = source;
    }
    private void setDestination(float destination){
        this.Destination = destination;
    }
    //getters
    private String getSource(){
        return this.Source;
    }
    private String getDestination(){
        return this.Destination ;
    }
//Methods
    void Regitser(){

    }
    void Requset(){

    }
    void Rate_Driver(){ }
    int Show(){ }
}
