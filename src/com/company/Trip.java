public class Trip {
    private User user = new User();
    private Driver driver = new Driver();
    private float RatingOfTrip;
    private String Source;
    private String Destination;
    private String Trip_ID;

    //setters
    private void setRatingOfTrip(float rateoftrip){
        this.RatingOfTrip = rateoftrip;
    }
    private void setSource(float source){
        this.Source = source;
    }
    private void setDestination(float destination){
        this.Destination = destination;
    }
    private void setTrip_ID(float trip_id){
        this.Trip_ID = trip_id;
    }
    //getters
    private float getRatingOfTrip(){
        return this.RatingOfTrip ;
    }
    private String getSource(){
        return this.Source;
    }
    private String getDestination(){
        return this.Destination ;
    }
    private String getTrip_ID(){
        return this.Trip_ID;
    }
    //Methods
    public void Start(){ }
    public void End(){ }
}
