public class Driver implements Registration,Show_Rating extends Person {
    private String National_ID;
    private String Driving_license;
    private String Average_rate;

    //setters
    private void  setNational_ID(String national_id){
        this.National_ID =national_id;
    }
    private void  setDriving_license(String drivinglicense){
        this.Driving_license =drivinglicense;
    }
    private void  setAverage_rate(String averagerate){
        this.Average_rate =averagerate;
    }
    //getters
    private String setNational_ID(){
        return this.National_ID ;
    }
    private String setDriving_license(){
        return this.Driving_license ;
    }
    private String setAverage_rate(){
        return this.Average_rate ;
    }
    //Methods
    void Register(){

    }
    int Offer(){

    }
    void Show(){

    }
    void Put_Faviorate_Source_Areas(){

    }
    void Update_Average_Rating(){

    }
}

